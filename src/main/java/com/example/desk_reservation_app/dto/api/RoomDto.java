package com.example.desk_reservation_app.dto.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class  RoomDto {
    private Long id;

    private List<DeskDto> deskDtoList;
}
