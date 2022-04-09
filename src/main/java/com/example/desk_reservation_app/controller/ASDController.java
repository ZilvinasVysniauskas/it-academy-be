package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.admin.ReservationsAdminDto;
import com.example.desk_reservation_app.models.*;
import com.example.desk_reservation_app.repositories.*;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1")
public class ASDController {

    private BuildingRepository buildingRepository;
    private DeskRepository deskRepository;
    private FloorRepository floorRepository;
    private ReservationsRepository reservationsRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;

    public ASDController(BuildingRepository buildingRepository, DeskRepository deskRepository, FloorRepository floorRepository, ReservationsRepository reservationsRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.buildingRepository = buildingRepository;
        this.deskRepository = deskRepository;
        this.floorRepository = floorRepository;
        this.reservationsRepository = reservationsRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/buildings")
    @CrossOrigin
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @GetMapping("/desks")
    @CrossOrigin
    public List<Desk> getAllDesks() {
        return deskRepository.findAll();
    }

    @GetMapping("/floors")
    @CrossOrigin
    public List<Floor> getAllFloors() {
        Floor floor = floorRepository.findAll().get(0);
        floor.getBuilding().getAddress();
        return floorRepository.findAll();
    }

    @GetMapping("/reservations")
    @CrossOrigin
    public List<Reservations> getAllReservations() {
        return reservationsRepository.findAll();
    }

    @GetMapping("/rooms")
    @CrossOrigin
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/users")
    @CrossOrigin
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/reservations/{id}")
    @CrossOrigin
    public Reservations getAllReservations(@RequestParam Long id) {
        return reservationsRepository.findById(id).get();
    }

}
