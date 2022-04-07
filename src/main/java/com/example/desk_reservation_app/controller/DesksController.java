package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.RoomDto;
import com.example.desk_reservation_app.models.*;
import com.example.desk_reservation_app.repositories.*;
import com.example.desk_reservation_app.services.DeskReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/desks")
public class DesksController {

    private DeskReservationService deskReservationService;

    public DesksController(DeskReservationService deskReservationService) {
        this.deskReservationService = deskReservationService;
    }

    @GetMapping
    public List<RoomDto> getReservations(@RequestParam String date){
        return deskReservationService.getTablesByDate(LocalDate.parse(date));
    }


}
