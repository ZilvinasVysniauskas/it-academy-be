package com.example.desk_reservation_app.dto.api.places;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class  RoomDto {
    private Long roomId;

    private String roomName;

    private List<DeskDto> desks;
}
