package com.example.desk_reservation_app.dto.requests;

import lombok.Data;

@Data
public class RoomRequest {

    private Long id = null;

    private String roomName;

    private Long floorId;
}
