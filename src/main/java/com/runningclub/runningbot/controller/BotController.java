package com.runningclub.runningbot.controller;

import com.runningclub.runningbot.service.WhatsAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/bot")
    public class BotController {

        private final WhatsAppService whatsappService;

        public BotController(WhatsAppService whatsappService) {
            this.whatsappService = whatsappService;
        }

        @PostMapping("/send")
        public ResponseEntity<String> send(@RequestParam String to, @RequestParam String message) {
            whatsappService.sendMessage(to, message);
            return ResponseEntity.ok("Надіслано");
        }
    }

