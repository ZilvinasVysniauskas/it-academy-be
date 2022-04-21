package com.example.desk_reservation_app.dto.requests;

import lombok.Data;

@Data
public class FloorRequest {

    private Long id = null;

    private int floorNumber;

    private Long buildingId;

}
