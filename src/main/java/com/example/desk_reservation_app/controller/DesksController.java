package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.places.RoomDto;
import com.example.desk_reservation_app.dto.requests.DeskRequest;
import com.example.desk_reservation_app.services.DesksService;
import com.example.desk_reservation_app.services.ReservationsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/desks")
public class DesksController {

    private final DesksService desksService;
    private final ReservationsService reservationsService;

    public DesksController(DesksService desksService, ReservationsService reservationsService) {
        this.desksService = desksService;
        this.reservationsService = reservationsService;
    }

    @GetMapping("/{floorId}")
    public List<RoomDto> getAllDesks(@PathVariable Long floorId) {
        return reservationsService.getAllRoomsByFloor(floorId);
    }

    @GetMapping("/{floorId}/{date}")
    public List<RoomDto> getReservations(@PathVariable Long floorId, @PathVariable String date) {
        return reservationsService.getAllDesksWithReservationsByDate(floorId, LocalDate.parse(date));
    }

    @DeleteMapping("/{id}")
    public void deleteDeskById(@PathVariable String id) {
        reservationsService.deleteDeskById(Long.parseLong(id));
    }

    @PostMapping()
    public void addDesk(@RequestBody DeskRequest deskRequest) {
        desksService.addNewDesk(deskRequest);
    }
    @PutMapping()
    public void editDesk(@RequestBody DeskRequest deskRequest) {
        desksService.editDesk(deskRequest);
    }

}
