package com.example.desk_reservation_app.dto.mappers.desk;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.api.desks.DeskDto;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Reservation;
import com.example.desk_reservation_app.models.Room;
import com.example.desk_reservation_app.models.User;
import java.util.stream.Collectors;

public class ReservationsMapper {

    public static DeskDto DeskToDeskToDto(Desk desk) {
        return DeskDto.builder()
                .id(desk.getId())
                .deskNumber(desk.getDeskNumber())
                .isAvailable(desk.is_available())
                .build();
    }

    public static RoomDto RoomToRoomDto(Room room) {
        return RoomDto.builder()
                .roomId(room.getId())
                .roomName(room.getRoomName())
                .desks(room.getDesks().stream()
                        .map(ReservationsMapper::DeskToDeskToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static ReservationsDto ReservationToReservationDto(Reservation reservations) {
        return ReservationsDto.builder()
                .reservationId(reservations.getId())
                .date(reservations.getDate())
                .deskNumber(reservations.getDesk().getDeskNumber())
                .roomName(reservations.getDesk().getRoom().getRoomName())
                .buildingName(reservations.getDesk().getRoom().getFloor().getBuilding().getName())
                .reservationStatus(reservations.getReservationStatus())
                .floorNumber(reservations.getDesk().getRoom().getFloor().getFloorNumber())
                .deskId(reservations.getDesk().getId())
                .build();
    }

    public static Reservation reservationRequestToReservation(ReservationRequest reservationRequest, Desk desk, User user) {
        return Reservation.builder()
                .date(reservationRequest.getDate())
                .id(reservationRequest.getId())
                .desk(desk)
                .user(user)
                .build();
    }

}
