//package com.example.desk_reservation_app.controller;
//
//import com.example.desk_reservation_app.models.*;
//import com.example.desk_reservation_app.models.enums.Role;
//import com.example.desk_reservation_app.repositories.*;
//import com.github.javafaker.Faker;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.lang.reflect.Array;
//import java.nio.charset.Charset;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;
//
//@RestController
//@RequestMapping("api/v1/inserts")
//public class InsertsController {
//
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private DeskRepository deskRepository;
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//    @Autowired
//    private FloorRepository floorRepository;
//
//    @Autowired
//    private ReservationsRepository reservationsRepository;
//
//    public InsertsController(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @GetMapping("rooms")
//    public void insertRooms() {
//        Random random = new Random();
//        List<Floor> floorList = Arrays.asList(
//                floorRepository.getById(12L),
//                floorRepository.getById(13L),
//                floorRepository.getById(14L),
//                floorRepository.getById(15L),
//                floorRepository.getById(16L),
//                floorRepository.getById(17L),
//                floorRepository.getById(18L)
//        );
//        floorList.forEach(floor -> {
//            for (int i = 0; i < random.nextInt(3, 7); i++) {
//                Room room = Room.builder()
//                        .floor(floor)
//                        .roomName("Room Nr" + random.nextInt(123, 700))
//                        .desks(List.of())
//                        .roomDeleted(false)
//                        .build();
//                roomRepository.save(room);
////                List<Desk> deskList = new java.util.ArrayList<>(List.of());
////                for (int j = 0; j < random.nextInt(5, 8); i++) {
////                    Desk desk = Desk.builder()
////                            .room(room)
////                            .is_available(true)
////                            .deskName("desk Nr " + random.nextInt(100, 1000))
////                            .build();
////                    deskRepository.save(desk);
////                }
//            }
//
//        });
//    }
//
//    @GetMapping("users")
//    public void dostuff1() {
//        Faker faker = new Faker();
//        Random random = new Random();
//        int i = 0;
//        while (i < 500) {
//            String s2 = faker.name().firstName();
//            String s1 = faker.name().lastName();
//            String s = faker.name().firstName();
//            try {
//                userRepository.save(
//                        User.builder()
//                                .password(passwordEncoder.encode(stringPass()))
//                                .defaultFloorId(random.nextLong(1, 13))
//                                .role(Role.STANDARD_USER)
//                                .middleName(s2)
//                                .lastName(s1)
//                                .firstName(s)
//                                .email(s + "." + s1 + "@corporate.com")
//                                .userId(random.nextLong(10000000, 99999999))
//                                .active(true)
//                                .build());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                System.out.println("ups");
//            }
//            i++;
//        }
//    }
//
//    @GetMapping
//    public void doStuff() {
//        List<User> all = userRepository.findAll();
//        all.forEach(user -> {
//            Random random = new Random();
//            int max = random.nextInt(5, 50);
//            for (int i = 0; i < max; i++) {
//                Desk desk = deskRepository.getById(random.nextLong(0, 375));
//                try {
//                    reservationsRepository.save(Reservation.builder()
//                            .user(user)
//                            .desk(desk)
//                            .date(this.randomDate())
//                            .build());
//                } catch (Exception e) {
//                    System.out.println("error");
//                }
//
//            }
//        });
//    }
//
//    private LocalDate randomDate() {
//        long minDay = LocalDate.of(2022, 3, 1).toEpochDay();
//        long maxDay = LocalDate.of(2022, 6, 20).toEpochDay();
//        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
//        return LocalDate.ofEpochDay(randomDay);
//    }
//
//    public String stringPass() {
//        byte[] array = new byte[8]; // length is bounded by 7
//        new Random().nextBytes(array);
//        return new String(array, Charset.forName("UTF-8"));
//    }
//}
