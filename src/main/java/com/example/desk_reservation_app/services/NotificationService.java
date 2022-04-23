package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.NotificationDto;
import com.example.desk_reservation_app.dto.mappers.NotificationMapper;
import com.example.desk_reservation_app.models.Notification;
import com.example.desk_reservation_app.repositories.NotificationsRepository;
import com.example.desk_reservation_app.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationsRepository notificationsRepository;
    private final JwtUtil jwtUtil;

    public NotificationService(NotificationsRepository notificationsRepository, JwtUtil jwtUtil) {
        this.notificationsRepository = notificationsRepository;
        this.jwtUtil = jwtUtil;
    }

    public List<NotificationDto> getAllNotifications(String auth) {
        return this.notificationsRepository.findAllByUserId(this.jwtUtil.getSubject(auth)).stream()
                .map(NotificationMapper::notificationToNotificationDto).collect(Collectors.toList());
    }

    public List<NotificationDto> getUnopenedNotification(String auth) {
        return this.notificationsRepository.findAllByOpenedFalseAndUserId(this.jwtUtil.getSubject(auth)).stream()
                .map(notification -> {
                    notification.setOpened(true);
                    this.notificationsRepository.save(notification);
                    return NotificationMapper.notificationToNotificationDto(notification);
                }).collect(Collectors.toList());
    }
}
