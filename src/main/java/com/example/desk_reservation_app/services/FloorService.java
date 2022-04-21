package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.places.FloorDto;
import com.example.desk_reservation_app.dto.mappers.FloorMapper;
import com.example.desk_reservation_app.dto.requests.FloorRequest;
import com.example.desk_reservation_app.repositories.BuildingRepository;
import com.example.desk_reservation_app.repositories.FloorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloorService {

    private final FloorRepository floorRepository;
    private final BuildingRepository buildingRepository;

    public FloorService(FloorRepository floorRepository, BuildingRepository buildingRepository) {
        this.floorRepository = floorRepository;
        this.buildingRepository = buildingRepository;
    }

    public List<FloorDto> getAllFloorsInBuilding(Long buildingId) {
        return floorRepository.findAllByBuildingIdAndFloorDeletedFalse(buildingId)
                .stream().map(FloorMapper::floorToFloorDto).collect(Collectors.toList());
    }

    public void addNewFloor(FloorRequest floorRequest) {
        this.floorRepository.save(FloorMapper.floorRequestToFloor(floorRequest, buildingRepository));
    }
}
