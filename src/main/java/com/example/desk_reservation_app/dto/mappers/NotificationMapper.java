package com.example.desk_reservation_app.dto.mappers;

import com.example.desk_reservation_app.dto.api.NotificationDto;
import com.example.desk_reservation_app.dto.requests.NotificationRequest;
import com.example.desk_reservation_app.models.Notification;

import java.time.LocalDate;

public class NotificationMapper {

    public static NotificationDto notificationToNotificationDto(Notification notification) {
        return NotificationDto.builder()
                .date(notification.getDate())
                .message(notification.getMessage())
                .build();
    }

    public static Notification notificationRequestToNotification(NotificationRequest notificationRequest) {
        return Notification.builder()
                .date(LocalDate.now())
                .message(notificationRequest.getMessage())
                .userId(notificationRequest.getUserId())
                .build();
    }


}
