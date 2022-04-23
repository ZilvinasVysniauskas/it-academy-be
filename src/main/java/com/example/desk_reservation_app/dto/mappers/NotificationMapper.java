package com.example.desk_reservation_app.dto.mappers;

import com.example.desk_reservation_app.dto.api.NotificationDto;
import com.example.desk_reservation_app.models.Notification;

public class NotificationMapper {

    public static NotificationDto notificationToNotificationDto(Notification notification) {
        return NotificationDto.builder()
                .date(notification.getDate())
                .message(notification.getMessage())
                .build();
    }

}
