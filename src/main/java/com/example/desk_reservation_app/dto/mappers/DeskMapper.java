package com.example.desk_reservation_app.dto.mappers;

import com.example.desk_reservation_app.dto.api.DeskDto;
import com.example.desk_reservation_app.dto.api.RoomDto;
import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Room;

import java.util.stream.Collectors;

public class DeskMapper {

    public static DeskDto DeskToDeskToDto(Desk desk) {
        return DeskDto.builder()
                .id(desk.getId())
                .isAvailable(desk.is_available())
                .build();
    }

    public static RoomDto RoomToRoomDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .deskDtoList(room.getDesks().stream()
                        .map(DeskMapper::DeskToDeskToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
