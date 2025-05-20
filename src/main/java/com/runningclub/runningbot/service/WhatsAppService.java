package com.runningclub.runningbot.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


    @Service
    public class WhatsAppService {
        private final RestTemplate restTemplate;

        public WhatsAppService(RestTemplateBuilder builder) {
            this.restTemplate = builder.build();
        }

        public void sendMessage(String to, String message) {
            String url = "http://localhost:3000/api/messages/send";

            Map<String, String> payload = new HashMap<>();
            payload.put("to", to);
            payload.put("message", message);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

            try {
                restTemplate.postForEntity(url, request, String.class);
                System.out.println("✅ Повідомлення надіслано");
            } catch (Exception e) {
                System.out.println("❌ Помилка при надсиланні: " + e.getMessage());
            }
        }

}
