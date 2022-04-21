package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.places.FloorDto;
import com.example.desk_reservation_app.dto.requests.FloorRequest;
import com.example.desk_reservation_app.services.BuildingService;
import com.example.desk_reservation_app.services.DesksService;
import com.example.desk_reservation_app.services.ReservationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/buildings")
public class BuildingController {

    private final DesksService desksService;
    private final BuildingService buildingService;
    private final ReservationsService reservationsService;

    public BuildingController(DesksService desksService, BuildingService buildingService, ReservationsService reservationsService) {
        this.desksService = desksService;
        this.buildingService = buildingService;
        this.reservationsService = reservationsService;
    }

    @GetMapping()
    public List<FloorDto> getAllFloorsByBuilding(@PathVariable Long buildingId) {
        return buildingService.getAllFloorsInBuilding(buildingId);
    }

    @DeleteMapping("/{floorId}")
    public void deleteFloor(@PathVariable Long floorId) {
        this.reservationsService.deleteFloorById(floorId);
    }

}
