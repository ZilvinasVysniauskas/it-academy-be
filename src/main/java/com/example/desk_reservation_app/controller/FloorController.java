package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.places.FloorDto;
import com.example.desk_reservation_app.dto.requests.FloorRequest;
import com.example.desk_reservation_app.models.enums.Department;
import com.example.desk_reservation_app.services.DesksService;
import com.example.desk_reservation_app.services.FloorService;
import com.example.desk_reservation_app.services.ReservationsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    @PostMapping()
    public void addNewFloor(@RequestParam("myFile") MultipartFile image,
                            @RequestParam("floorName")String  floorName,
                            @RequestParam("floorNumber")String  floorNumber,
                            @RequestParam("buildingId")String  buildingId,
                            @RequestParam("department")String  department ) throws IOException {
        FloorRequest floorRequest = FloorRequest.builder()
                .floorName(floorName)
                .buildingId(Long.parseLong(buildingId))
                .department(Department.valueOf(department))
                .floorNumber(Integer.parseInt(floorNumber))
                .floorPlan(image.getBytes())
                .build();
        floorService.addNewFloor(floorRequest);
    }

    @PutMapping()
    public void editFloorName(@RequestBody FloorRequest floorRequest) {
        this.floorService.editFloor(floorRequest);
    }

    @PutMapping("/plan")
    public void editFloorImage(@RequestParam("floorPlan") MultipartFile image,
                               @RequestParam("floorId") String id) throws IOException {
        this.floorService.changeFloorPlan(image, Long.parseLong(id));
    }

    @DeleteMapping("/{floorId}/{replaceFloorId}")
    public void deleteFloor(@PathVariable Long floorId, @PathVariable Long replaceFloorId) {
        this.reservationsService.deleteFloorById(floorId, replaceFloorId);
   }


}
