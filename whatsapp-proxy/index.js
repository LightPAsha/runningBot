import {
    makeWASocket,
    useMultiFileAuthState,
    fetchLatestBaileysVersion,
    makeInMemoryStore,
    DisconnectReason
} from '@whiskeysockets/baileys';

import express from 'express';
import bodyParser from 'body-parser';
import pino from 'pino';
import { Boom } from '@hapi/boom';

const app = express();
const port = 3000;

const store = makeInMemoryStore({ logger: pino({ level: 'silent' }) });

const startSock = async () => {
    const { state, saveCreds } = await useMultiFileAuthState('auth');
    const { version } = await fetchLatestBaileysVersion();

    const sock = makeWASocket({
        version,
        auth: state,
        printQRInTerminal: true,
        logger: pino({ level: 'info' }),
    });

    store.bind(sock.ev);
    sock.ev.on('creds.update', saveCreds);

    sock.ev.on('connection.update', (update) => {
        const { connection, lastDisconnect } = update;
        if (connection === 'close') {
            const shouldReconnect = lastDisconnect?.error?.output?.statusCode !== DisconnectReason.loggedOut;
            console.log('🔌 Disconnected. Reconnecting:', shouldReconnect);
            if (shouldReconnect) startSock();
        } else if (connection === 'open') {
            console.log('✅ WhatsApp connected');
        }
    });

    // HTTP API для прийому запитів від Java/Spring
    app.use(bodyParser.json());

    app.post('/api/messages/send', async (req, res) => {
        const { to, message } = req.body;

        if (!to || !message) {
            return res.status(400).json({ error: 'Missing "to" or "message"' });
        }

        try {
            await sock.sendMessage(to, { text: message });
            console.log(`📤 Message sent to ${to}: ${message}`);
            res.json({ status: 'success' });
        } catch (err) {
            console.error('❌ Error sending message:', err);
            res.status(500).json({ error: 'Failed to send message' });
        }
    });

    app.listen(port, () => {
        console.log(`🚀 API server running on http://localhost:${port}`);
    });
};

startSock();