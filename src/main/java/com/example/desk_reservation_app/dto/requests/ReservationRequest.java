package com.example.desk_reservation_app.dto.requests;

import com.example.desk_reservation_app.models.enums.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ReservationRequest {
    //todo do validations
    private Long id = null;

    private Long userId;

    private Long deskId;

    private LocalDate date;

}

