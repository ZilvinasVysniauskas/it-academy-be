package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByOpenedFalseAndUserId(Long id);
    List<Notification> findAllByUserId(Long id);
}
