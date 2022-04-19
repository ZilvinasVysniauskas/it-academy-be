package com.example.desk_reservation_app;

import com.example.desk_reservation_app.models.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DeskReservationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeskReservationAppApplication.class, args);
    }

}
