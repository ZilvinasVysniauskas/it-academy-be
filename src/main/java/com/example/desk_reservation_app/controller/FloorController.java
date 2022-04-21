package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.places.FloorDto;
import com.example.desk_reservation_app.dto.requests.FloorRequest;
import com.example.desk_reservation_app.services.DesksService;
import com.example.desk_reservation_app.services.FloorService;
import com.example.desk_reservation_app.services.ReservationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/floors")
public class FloorController {

    private final FloorService floorService;
    private final DesksService desksService;
    private final ReservationsService reservationsService;

    public FloorController(FloorService floorService, DesksService desksService, ReservationsService reservationsService) {
        this.floorService = floorService;
        this.desksService = desksService;
        this.reservationsService = reservationsService;
    }

    @GetMapping("/{buildingId}")
    public List<FloorDto> getAllFloorsByBuilding(@PathVariable Long buildingId) {
        return floorService.getAllFloorsInBuilding(buildingId);
    }

    @PostMapping()
    public void addNewFloor(@RequestBody FloorRequest floorRequest) {
        floorService.addNewFloor(floorRequest);
    }

    @DeleteMapping("/{floorId}")
    public void deleteFloor(@PathVariable Long floorId) {
        this.reservationsService.deleteFloorById(floorId);
   }

}
