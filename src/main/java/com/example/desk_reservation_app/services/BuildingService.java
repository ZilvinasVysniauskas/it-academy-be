package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.places.BuildingDto;
import com.example.desk_reservation_app.dto.mappers.BuildingMapper;
import com.example.desk_reservation_app.dto.requests.BuildingRequest;
import com.example.desk_reservation_app.models.Building;
import com.example.desk_reservation_app.repositories.AddressRepository;
import com.example.desk_reservation_app.repositories.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final AddressRepository addressRepository;

    public BuildingService(BuildingRepository buildingRepository, AddressRepository addressRepository) {
        this.buildingRepository = buildingRepository;
        this.addressRepository = addressRepository;
    }

    public List<BuildingDto> getAllBuildings() {
        return this.buildingRepository.findAllByBuildingDeletedFalse().stream().map(BuildingMapper::buildingToBuildingDto)
                .collect(Collectors.toList());
    }

    public void addNewBuilding(BuildingRequest buildingRequest) {
        System.out.println(buildingRequest);
        Building building = BuildingMapper.BuildingRequestToBuilding(buildingRequest);
        System.out.println(building);
        this.addressRepository.save(building.getAddress());
        this.buildingRepository.save(building);
    }
}
