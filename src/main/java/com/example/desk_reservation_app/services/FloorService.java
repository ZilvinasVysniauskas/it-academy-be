package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.places.FloorDto;
import com.example.desk_reservation_app.dto.mappers.FloorMapper;
import com.example.desk_reservation_app.dto.requests.FloorRequest;
import com.example.desk_reservation_app.models.Floor;
import com.example.desk_reservation_app.repositories.BuildingRepository;
import com.example.desk_reservation_app.repositories.FloorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public FloorDto getFloorById(Long floorId) {
        return FloorMapper.floorToFloorDto(this.floorRepository.getById(floorId));
    }

    public void editFloor(FloorRequest floorRequest) {
        Floor floor = this.floorRepository.getById(floorRequest.getId());
        floor.setFloorName(floorRequest.getFloorName());
        this.floorRepository.save(floor);
    }

    public void changeFloorPlan(MultipartFile image, Long id) throws IOException {
        Floor floor = this.floorRepository.getById(id);
        floor.setFloorPlan(image.getBytes());
        floorRepository.save(floor);
    }
}
