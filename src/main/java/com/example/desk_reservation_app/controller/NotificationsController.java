package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.NotificationDto;
import com.example.desk_reservation_app.dto.requests.NotificationRequest;
import com.example.desk_reservation_app.models.enums.Department;
import com.example.desk_reservation_app.services.NotificationService;
import org.springframework.web.bind.annotation.*;

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

}
