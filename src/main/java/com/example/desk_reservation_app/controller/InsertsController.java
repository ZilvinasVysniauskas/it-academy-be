package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Reservation;
import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.models.enums.Role;
import com.example.desk_reservation_app.repositories.DeskRepository;
import com.example.desk_reservation_app.repositories.ReservationsRepository;
import com.example.desk_reservation_app.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("api/v1/inserts")
public class InsertsController {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeskRepository deskRepository;

    @Autowired
    private ReservationsRepository reservationsRepository;

    public InsertsController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("users")
    public void dostuff1() {
        Faker faker = new Faker();
        Random random = new Random();
        int i = 0;
        while (i < 500) {
            String s2 = faker.name().firstName();
            String s1 = faker.name().lastName();
            String s = faker.name().firstName();
            try {
                userRepository.save(
                        User.builder()
                                .password(passwordEncoder.encode(stringPass()))
                                .defaultFloorId(random.nextLong(1, 13))
                                .role(Role.STANDARD_USER)
                                .middleName(s2)
                                .lastName(s1)
                                .firstName(s)
                                .email(s+ "." + s1 + "@corporate.com")
                                .userId(random.nextLong(10000000, 99999999))
                                .active(true)
                                .build());
            }catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("ups");
            }
            i++;
        }
    }
    @GetMapping
    public void doStuff() {
        List<User> all = userRepository.findAll();
        all.forEach(user -> {
            Random random = new Random();
            int max = random.nextInt(5, 50);
            for (int i = 0; i < max; i++){
                Desk desk = deskRepository.getById(random.nextLong(0, 393));
                try {
                    reservationsRepository.save(Reservation.builder()
                            .user(user)
                            .desk(desk)
                            .date(this.randomDate())
                            .build());
                }catch (Exception e) {
                    System.out.println("error");
                }

            }
        });
    }
    private LocalDate randomDate() {
        long minDay = LocalDate.of(2022, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 10, 20).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public String stringPass() {
        byte[] array = new byte[8]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
