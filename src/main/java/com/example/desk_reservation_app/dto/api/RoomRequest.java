package com.example.desk_reservation_app.dto.api;

import lombok.Data;

@Data
public class RoomRequest {
    private String roomName;
    private int roomNumber;
}
