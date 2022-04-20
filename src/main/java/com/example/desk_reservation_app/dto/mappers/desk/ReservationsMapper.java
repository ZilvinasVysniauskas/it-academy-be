package com.example.desk_reservation_app.dto.mappers.desk;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.api.desks.DeskDto;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.DeskRequest;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.dto.requests.RoomRequest;
import com.example.desk_reservation_app.models.*;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationsMapper {

    public static DeskDto DeskToDeskToDto(Desk desk) {
        return DeskDto.builder()
                .id(desk.getId())
                .deskName(desk.getDeskName())
                .isAvailable(desk.is_available())
                .build();
    }

    public static Desk DeskRequestToDesk(DeskRequest deskRequest, Room room) {
        return Desk.builder()
                .deskName(deskRequest.getDeskName())
                .room(room)
                .is_available(deskRequest.isAvailable())
                .build();
    }

    public static RoomDto RoomToRoomDto(Room room) {
        return RoomDto.builder()
                .roomId(room.getId())
                .roomName(room.getRoomName())
                .desks(List.of())
                .build();
    }

    public static ReservationsDto ReservationToReservationDto(Reservation reservations) {
        return ReservationsDto.builder()
                .reservationId(reservations.getId())
                .date(reservations.getDate())
                .deskName(reservations.getDesk().getDeskName())
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

    public static Room RoomRequestToRoom(RoomRequest roomRequest, Floor floor) {
        return Room.builder()
                .id(roomRequest.getId())
                .roomName(roomRequest.getRoomName())
                .floor(floor)
                .build();
    }
}
