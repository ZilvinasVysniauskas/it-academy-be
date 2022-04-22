package com.example.desk_reservation_app.dto.mappers;

import com.example.desk_reservation_app.dto.api.places.BuildingDto;
import com.example.desk_reservation_app.dto.requests.BuildingRequest;
import com.example.desk_reservation_app.models.Address;
import com.example.desk_reservation_app.models.Building;

public class BuildingMapper {

    public static BuildingDto buildingToBuildingDto(Building building) {
        return BuildingDto.builder()
                .buildingName(building.getName())
                .id(building.getId())
                .buildingNumber(building.getAddress().getNumber())
                .city(building.getAddress().getCity())
                .streetName(building.getAddress().getStreet())
                .build();
    }

    public static Building BuildingRequestToBuilding(BuildingRequest buildingRequest) {
        return Building.builder()
                .name(buildingRequest.getName())
                .address(Address.builder()
                        .city(buildingRequest.getCity())
                        .street(buildingRequest.getStreetName())
                        .number(buildingRequest.getBuildingNumber())
                        .build())
                .build();
    }
}
