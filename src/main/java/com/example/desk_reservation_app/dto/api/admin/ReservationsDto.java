package com.example.desk_reservation_app.dto.api.admin;

import com.example.desk_reservation_app.models.enums.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationsDto {

    private Long reservationId;

    private String roomName;

    private String buildingName;

    private int floorNumber;

    private int deskNumber;

    private Long deskId;

    private LocalDate date;

    private ReservationStatus reservationStatus;

}
