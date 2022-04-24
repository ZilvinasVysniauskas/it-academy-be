package com.example.desk_reservation_app.dto.requests;

import com.example.desk_reservation_app.models.enums.Department;
import lombok.Data;

@Data
public class FloorRequest {

    private Long id = null;

    private int floorNumber;

    private Long buildingId;

    private String floorName;

    private Department department;

}
