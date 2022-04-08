package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.admin.ReservationsAdminDto;
import com.example.desk_reservation_app.repositories.ReservationsRepository;
import com.example.desk_reservation_app.services.DeskReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {

    private DeskReservationService deskReservationService;

    @Autowired
    private ReservationsRepository reservationsRepository;


    public AdminController(DeskReservationService deskReservationService) {
        this.deskReservationService = deskReservationService;
    }

    @GetMapping("/reservations")
    @CrossOrigin
    public List<ReservationsAdminDto> getRoomsWithDesks() {
        return deskReservationService.getAllReservationsForAdmin();
    }

}
