package com.example.desk_reservation_app.dto.requests;

import lombok.Data;

@Data
public class BuildingRequest {

    private Long id = null;

    private String name;

    private String city;

    private String streetName;

    private int buildingNumber;

}
