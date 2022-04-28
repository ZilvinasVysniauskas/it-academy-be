package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.NotificationDto;
import com.example.desk_reservation_app.dto.requests.NotificationRequest;
import com.example.desk_reservation_app.models.enums.Department;
import com.example.desk_reservation_app.services.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationsController {

    private final NotificationService notificationService;

    public NotificationsController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/new")
    public List<NotificationDto> getAllNotifications(@RequestHeader("Authorization") String auth) {
        return this.notificationService.getUnopenedNotification(auth);
    }

    @GetMapping("/motivate")
    public void motivate() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://motivational-quotes1.p.rapidapi.com/motivation"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Host", "motivational-quotes1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "ab305e437cmshf2a99448d6f2adfp163a5ejsn0000cdc3f0e2")
                .method("POST", HttpRequest.BodyPublishers.ofString("    {\\r\\n        \\\"key1\\\": \\\"value\\\",\\r\\n        \\\"key2\\\": \\\"value\\\",\\r\\n    }"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String quote = response.body();
        if (quote.endsWith("null")){
            quote = quote.substring(0, quote.length() - 5) + "-Unknown Author";
        }
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .message(quote)
                .sentFromAdmin(true)
                .build();
        this.notificationService.sendNotificationToAll(notificationRequest);
    }

    @GetMapping("/admin")
    public List<NotificationDto> getAllNotificationsFromAdmin(@RequestHeader("Authorization") String auth) {
        return this.notificationService.getAllNotificationsFromAdmin(auth);
    }

    @GetMapping
    public List<NotificationDto> getUnopenedNotifications(@RequestHeader("Authorization") String auth) {
        return this.notificationService.getAllNotifications(auth);
    }

    @PostMapping
    public void sendNotificationToUser(@RequestBody NotificationRequest notificationRequest) {
        this.notificationService.sendNotificationToUser(notificationRequest);
    }

    @PostMapping("/{department}")
    public void sendNotificationToDepartment(@RequestBody NotificationRequest notificationRequest, @PathVariable Department department) {
        this.notificationService.sendNotificationToDepartment(notificationRequest, department);
    }

    @PostMapping("/all")
    public void sendNotificationToAll(@RequestBody NotificationRequest notificationRequest) {
        this.notificationService.sendNotificationToAll(notificationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteNotificationById(@PathVariable Long id ) {
        this.notificationService.deleteNotificationById(id);
    }


}
