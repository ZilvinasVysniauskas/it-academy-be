package com.example.desk_reservation_app.dto.requests;

import lombok.Data;

@Data
public class DeskRequest {

    private Long roomId;

    private int deskNumber;

    private boolean isAvailable = true;

}
