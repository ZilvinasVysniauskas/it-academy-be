package com.example.desk_reservation_app.dto.api.places;

import com.example.desk_reservation_app.models.enums.Department;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FloorDto {

    private Long id;

    private int floorNumber;

    private String floorName;

    private String buildingName;

    private Department department;

    private byte[] floorPlan;


}
