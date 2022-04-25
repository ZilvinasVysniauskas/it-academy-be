package com.example.desk_reservation_app.dto.mappers;

import com.example.desk_reservation_app.dto.api.places.FloorDto;
import com.example.desk_reservation_app.dto.requests.FloorRequest;
import com.example.desk_reservation_app.models.Floor;
import com.example.desk_reservation_app.repositories.BuildingRepository;


public class FloorMapper {



    public static FloorDto floorToFloorDto(Floor floor) {

        return FloorDto.builder()
                .floorNumber(floor.getFloorNumber())
                .id(floor.getId())
                .floorName(floor.getFloorName())
                .buildingName(floor.getBuilding().getName())
                .department(floor.getDepartment())
                .floorPlan(floor.getFloorPlan())
                .build();
    }


    public static Floor floorRequestToFloor(FloorRequest floorRequest, BuildingRepository buildingRepository)  {
        return Floor.builder()
                .building(buildingRepository.getById(floorRequest.getBuildingId()))
                .floorNumber(floorRequest.getFloorNumber())
                .floorName(floorRequest.getFloorName())
                .department(floorRequest.getDepartment())
                .floorPlan(floorRequest.getFloorPlan())
                .build();
    }

}
