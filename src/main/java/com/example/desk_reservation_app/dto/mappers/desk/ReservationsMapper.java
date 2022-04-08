package com.example.desk_reservation_app.dto.mappers.desk;

import com.example.desk_reservation_app.dto.api.admin.ReservationsAdminDto;
import com.example.desk_reservation_app.dto.api.desks.DeskDto;
import com.example.desk_reservation_app.dto.api.desks.ReservationsUserDto;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Reservations;
import com.example.desk_reservation_app.models.Room;
import com.example.desk_reservation_app.models.User;

import java.util.stream.Collectors;

public class ReservationsMapper {

    public static DeskDto DeskToDeskToDto(Desk desk) {
        return DeskDto.builder()
                .id(desk.getId())
                .isAvailable(desk.is_available())
                .build();
    }

    public static RoomDto RoomToRoomDto(Room room) {
        return RoomDto.builder()
                .roomId(room.getId())
                .roomName(room.getRoomName())
                .deskDtoList(room.getDesks().stream()
                        .map(ReservationsMapper::DeskToDeskToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static ReservationsUserDto ReservationToReservationUserDto(Reservations reservations) {
        return ReservationsUserDto.builder()
                .reservationId(reservations.getId())
                .deskNumber(reservations.getDesk().getDeskNumber())
                .roomName(reservations.getDesk().getRoom().getRoomName())
                .date(reservations.getDate())
                .build();
    }

    public static ReservationsAdminDto ReservationToReservationAdminDto(Reservations reservations) {
        return ReservationsAdminDto.builder()
                .reservationId(reservations.getId())
                .userEmail(reservations.getUser().getEmail())
                .date(reservations.getDate())
                .deskNumber(reservations.getDesk().getDeskNumber())
                .userLastName(reservations.getUser().getLastName())
                .userName(reservations.getUser().getFirstName())
                .userId(reservations.getUser().getUserId())
                .roomName(reservations.getDesk().getRoom().getRoomName())
                .build();
    }

    public static Reservations reservationRequestToReservation(ReservationRequest reservationRequest, Desk desk, User user) {
        return Reservations.builder()
                .date(reservationRequest.getDate())
                .id(reservationRequest.getId())
                .desk(desk)
                .user(user)
                .build();
    }
}
