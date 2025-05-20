package com.runningclub.runningbot.scheduler;

import com.runningclub.runningbot.service.WhatsAppService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

@Component
class ScheduledTasks {

    private final WhatsAppService whatsAppService;

    public ScheduledTasks(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    // Щонеділі о 11:00 ранку
    @Scheduled(cron = "0 0 11 ? * WED")
    public void sendRunAnnouncement() {
        String to = "123456789@s.whatsapp.net"; // заміни на актуальний WhatsApp ID

        // Знаходимо дату наступної неділі
        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        String formattedDate = nextSunday.format(DateTimeFormatter.ofPattern("dd/MM"));

        // Повідомлення з оновленою датою
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

                https://meetu.ps/e/P34NF/1d6V45/i
                """.formatted(formattedDate);

        whatsAppService.sendMessage(to, message);
    }
}
