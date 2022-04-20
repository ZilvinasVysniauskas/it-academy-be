package com.example.desk_reservation_app.dto.requests;

import lombok.Data;

@Data
public class DeskRequest {

    private Long id = null;

    private Long roomId = null;

    private String  deskName;

    private boolean isAvailable = true;

}
