package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.NotificationDto;
import com.example.desk_reservation_app.dto.mappers.NotificationMapper;
import com.example.desk_reservation_app.dto.requests.NotificationRequest;
import com.example.desk_reservation_app.models.Notification;
import com.example.desk_reservation_app.models.enums.Department;
import com.example.desk_reservation_app.repositories.NotificationsRepository;
import com.example.desk_reservation_app.repositories.UserRepository;
import com.example.desk_reservation_app.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationsRepository notificationsRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public NotificationService(NotificationsRepository notificationsRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.notificationsRepository = notificationsRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public List<NotificationDto> getAllNotifications(String auth) {
        return this.notificationsRepository.findAllByUserIdAndMessageFromAdminFalseAndMessageDeletedFalse(this.jwtUtil.getSubject(auth)).stream()
                .map(NotificationMapper::notificationToNotificationDto).collect(Collectors.toList());
    }

    public List<NotificationDto> getAllNotificationsFromAdmin(String auth) {
        return this.notificationsRepository.findAllByUserIdAndMessageFromAdminTrueAndMessageDeletedFalseOrderByCreateDateDesc(this.jwtUtil.getSubject(auth)).stream()
                .map(NotificationMapper::notificationToNotificationDto).collect(Collectors.toList());
    }

    public List<NotificationDto> getUnopenedNotification(String auth) {
        return this.notificationsRepository.findAllByOpenedFalseAndUserIdAndMessageFromAdminFalse(this.jwtUtil.getSubject(auth)).stream()
                .map(notification -> {
                    notification.setOpened(true);
                    this.notificationsRepository.save(notification);
                    return NotificationMapper.notificationToNotificationDto(notification);
                }).collect(Collectors.toList());
    }

    public void sendNotificationToUser(NotificationRequest notificationRequest) {
        notificationRequest.setSentFromAdmin(true);
        this.notificationsRepository.save(NotificationMapper.notificationRequestToNotification(notificationRequest));
    }

    public void sendNotificationToDepartment(NotificationRequest notificationRequest, Department department) {
        notificationRequest.setSentFromAdmin(true);
        this.userRepository.findAllByDepartment(department).forEach(user -> {
            notificationRequest.setUserId(user.getUserId());
            this.notificationsRepository.save(NotificationMapper.notificationRequestToNotification(notificationRequest));
        });

    }

    public void sendNotificationToAll(NotificationRequest notificationRequest) {
        userRepository.findAll().forEach( user -> {
            notificationRequest.setUserId(user.getUserId());
            notificationRequest.setSentFromAdmin(true);
            this.notificationsRepository.save(NotificationMapper.notificationRequestToNotification(notificationRequest));
        });
    }

    public void deleteNotificationById(Long id) {
        Notification notificationToDelete = this.notificationsRepository.getById(id);
        notificationToDelete.setMessageDeleted(true);
        this.notificationsRepository.save(notificationToDelete);
    }
}
