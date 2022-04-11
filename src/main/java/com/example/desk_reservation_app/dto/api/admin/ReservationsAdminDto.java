package com.example.desk_reservation_app.dto.api.admin;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationsAdminDto {

    private Long reservationId;

    private String roomName;

    private int deskNumber;

    private LocalDate date;

    private Long userId;

    private String userName;

    private String userLastName;

    private String userEmail;

}
