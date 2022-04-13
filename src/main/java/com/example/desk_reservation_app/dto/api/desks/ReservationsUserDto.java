package com.example.desk_reservation_app.dto.api.desks;

import com.example.desk_reservation_app.models.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ReservationsUserDto {

    private Long reservationId;

    private String roomName;

    private int deskNumber;

    private LocalDate date;

    private Long deskId;

    //todo reikia dar vieno fieldo ar dar aktyvi (ar dar data nepraejo)

}
