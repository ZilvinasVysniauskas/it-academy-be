package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.places.RoomDto;
import com.example.desk_reservation_app.dto.mappers.desk.ReservationsMapper;
import com.example.desk_reservation_app.dto.requests.DeskRequest;
import com.example.desk_reservation_app.dto.requests.RoomRequest;
import com.example.desk_reservation_app.models.*;
import com.example.desk_reservation_app.models.enums.ReservationStatus;
import com.example.desk_reservation_app.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DesksService {

    private final FloorRepository floorRepository;
    private final RoomRepository roomRepository;
    private final ReservationsRepository reservationsRepository;
    private final DeskRepository deskRepository;
    private final BuildingRepository buildingRepository;

    public DesksService(FloorRepository floorRepository, ReservationsRepository reservationsRepository, DeskRepository deskRepository, UserRepository userRepository, RoomRepository roomRepository, BuildingRepository buildingRepository) {
        this.floorRepository = floorRepository;
        this.reservationsRepository = reservationsRepository;
        this.deskRepository = deskRepository;
        this.roomRepository = roomRepository;
        this.buildingRepository = buildingRepository;
    }


    public void addNewDesk(DeskRequest deskRequest) {
        deskRepository.save(ReservationsMapper.DeskRequestToDesk(deskRequest, roomRepository));
    }

    public void editDesk(DeskRequest deskRequest) {
        Desk desk = this.deskRepository.getById(deskRequest.getId());
        desk.setDeskName(deskRequest.getDeskName());
        this.deskRepository.save(desk);
    }

}

