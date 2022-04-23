package com.example.desk_reservation_app.dto.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NotificationDto {

    private String message;

    private LocalDate date;
}
