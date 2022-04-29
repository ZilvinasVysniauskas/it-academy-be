package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByOpenedFalseAndUserIdAndMessageFromAdminFalse(Long id);
    List<Notification> findAllByUserIdAndMessageFromAdminFalseAndMessageDeletedFalse(Long id);
    List<Notification> findAllByUserIdAndMessageFromAdminTrueAndMessageDeletedFalseOrderByCreateDateDesc(Long id);
}
