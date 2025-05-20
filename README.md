🏃‍♂️ WhatsApp Sunday Run Bot
This project automatically sends a weekly WhatsApp message announcing a Sunday run via a Node.js + Spring Boot app.
The Node.js script uses Baileys to connect to WhatsApp Web and receive/send messages.
The Spring Boot service sends HTTP POST requests to the local Node server to trigger WhatsApp messages.




⚙️ How It Works
🧠 Logic
1. Spring Boot uses `Scheduled` to send a message **every Wednesday at 11:00 AM**
2. It sends an HTTP POST request to the Node.js server (`index.js`)
3. Node.js uses Baileys to send a WhatsApp message to the selected phone number or group


  Requirements:  
       Backend (Java), 
       Java 17+,    Spring Boot 3.4+,
               Maven,  
  WhatsApp Bot (Node.js),
  Node.js v18+ ,   npm





📦 Install Dependencies
1. Node.js (for WhatsApp bot)
Install all required packages:

			bash
			npm install @whiskeysockets/baileys express qrcode-terminal
These are used for:

 @whiskeysockets/baileys: WhatsApp Web API.

express: HTTP server to receive POST requests.

qrcode-terminal: Show QR code in terminal on first launch.

🚀 Run the Application
1. Start the WhatsApp Bot (Node.js)
bash
Копировать
Редактировать
node index.js
On first run, scan the QR code with WhatsApp on your phone.

2. Start Spring Boot App
 
				bash
				./mvnw spring-boot:run

Or from IntelliJ / Eclipse by running RunningBotApplication.java.

The Spring app will automatically send a WhatsApp message every Wednesday at 11:00 AM with next Sunday's run info.
