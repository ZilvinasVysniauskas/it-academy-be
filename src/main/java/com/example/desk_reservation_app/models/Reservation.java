package com.example.desk_reservation_app.models;

import com.example.desk_reservation_app.models.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Desk desk;

    @ManyToOne
    private User user;

    private LocalDate date;

    private ReservationStatus reservationStatus = null;
}
