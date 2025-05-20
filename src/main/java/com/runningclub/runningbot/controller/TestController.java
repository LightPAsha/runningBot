package com.runningclub.runningbot.controller;

import com.runningclub.runningbot.service.WhatsAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    private final WhatsAppService whatsAppService;

    public TestController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @GetMapping("/send")
    public String sendTestMessage() {
        String to = "123456789@s.whatsapp.net"; // змінити на реальний номер
        String message = """
                🚨Sunday Run Announcement!
                👇Join us for a chill run on Sunday %s

                📍 Meeting point: Lila Bakery, Rotterdam
                🕚 Time: 11:00 AM
                📏 Distance: 3/6/10 km – you choose!

                🛍 You can drop off your bags at the bakery before going for a run.
                ☕ After the run, we’ll head to Lila Bakery for coffee, cakes, and great chats (10%% discount for members!).
                👍 React with a thumbs up if you’re in!

                We also have an account on MeetUp, so feel free to join there if it’s more convenient for you. 😊

                
                """;
        whatsAppService.sendMessage(to, message);
        return "✅ Тестове повідомлення надіслано!";
    }
}