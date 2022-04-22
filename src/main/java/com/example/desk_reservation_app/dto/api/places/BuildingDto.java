package com.example.desk_reservation_app.dto.api.places;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuildingDto {

    private Long id;

    private String streetName;

    private String city;

    private int buildingNumber;

    private String buildingName;

}
