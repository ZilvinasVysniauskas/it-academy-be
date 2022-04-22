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
    private final ReservationsService reservationsService;

    public FloorController(FloorService floorService, ReservationsService reservationsService) {
        this.floorService = floorService;
        this.reservationsService = reservationsService;
    }

    @GetMapping("/{buildingId}")
    public List<FloorDto> getAllFloorsByBuilding(@PathVariable Long buildingId) {
        return floorService.getAllFloorsInBuilding(buildingId);
    }

    @GetMapping("/floor/{floorId}")
    public FloorDto getFloor(@PathVariable Long floorId) {
        return floorService.getFloorById(floorId);
    }

    @GetMapping("/first")
    public FloorDto getFirstFloor() {
        return floorService.getFirstFloor();
    }

    @PostMapping()
    public void addNewFloor(@RequestBody FloorRequest floorRequest) {
        floorService.addNewFloor(floorRequest);
    }

    @PutMapping()
    public void editFloor(@RequestBody FloorRequest floorRequest) {
        this.floorService.editFloor(floorRequest);
    }

    @DeleteMapping("/{floorId}/{replaceFloorId}")
    public void deleteFloor(@PathVariable Long floorId, @PathVariable Long replaceFloorId) {
        this.reservationsService.deleteFloorById(floorId, replaceFloorId);
   }


}
