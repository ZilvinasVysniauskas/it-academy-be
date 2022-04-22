package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.places.BuildingDto;
import com.example.desk_reservation_app.dto.api.places.FloorDto;
import com.example.desk_reservation_app.dto.requests.BuildingRequest;
import com.example.desk_reservation_app.dto.requests.FloorRequest;
import com.example.desk_reservation_app.services.BuildingService;
import com.example.desk_reservation_app.services.DesksService;
import com.example.desk_reservation_app.services.ReservationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/buildings")
public class BuildingController {

    private final BuildingService buildingService;
    private final ReservationsService reservationsService;

    public BuildingController(BuildingService buildingService, ReservationsService reservationsService) {
        this.buildingService = buildingService;
        this.reservationsService = reservationsService;
    }

    @GetMapping()
    public List<BuildingDto> getAllBuilding() {
        return buildingService.getAllBuildings();
    }

    @PostMapping()
    public void addNewBuilding(@RequestBody BuildingRequest buildingRequest) {
        buildingService.addNewBuilding(buildingRequest);
    }

    @PutMapping()
    public void editBuilding(@RequestBody BuildingRequest buildingRequest) {
        this.buildingService.editBuilding(buildingRequest);
    }

    @DeleteMapping("/{buildingId}")
    public void deleteBuilding(@PathVariable Long buildingId) {
        this.reservationsService.deleteBuildingById(buildingId);
    }

}
