package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.places.BuildingDto;
import com.example.desk_reservation_app.dto.api.places.FloorDto;
import com.example.desk_reservation_app.dto.requests.BuildingRequest;
import com.example.desk_reservation_app.dto.requests.FloorRequest;
import com.example.desk_reservation_app.services.BuildingService;
import com.example.desk_reservation_app.services.DesksService;
import com.example.desk_reservation_app.services.ReservationsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    @GetMapping("x")
    public  void x() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://motivational-quotes1.p.rapidapi.com/motivation"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Host", "motivational-quotes1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "ab305e437cmshf2a99448d6f2adfp163a5ejsn0000cdc3f0e2")
                .method("POST", HttpRequest.BodyPublishers.ofString("    {\\r\\n        \\\"key1\\\": \\\"value\\\",\\r\\n        \\\"key2\\\": \\\"value\\\",\\r\\n    }"))
            .build();
    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
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


}
