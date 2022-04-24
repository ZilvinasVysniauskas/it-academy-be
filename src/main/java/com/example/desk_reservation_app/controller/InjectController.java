package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.models.enums.Department;
import com.example.desk_reservation_app.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("inj")
public class InjectController {

    private final UserRepository userRepository;

    public InjectController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping()
    public void x() {
        int sales[] = { 1, 2, 5 };
        int management[] = {3, 10, 12 };
        int marketing[] = {4, 8, 9 };
        int devs[] = {6, 7, 11 };
        this.userRepository.findAll().forEach(user -> {
            if (user.getDepartment().equals(Department.DEVELOPERS)){
                user.setDefaultFloorId((long) getRandom(devs));
            }
            if (user.getDepartment().equals(Department.MANAGEMENT)){
                user.setDefaultFloorId((long) getRandom(management));
            }
            if (user.getDepartment().equals(Department.MARKETING)){
                user.setDefaultFloorId((long) getRandom(marketing));
            }
            if (user.getDepartment().equals(Department.SALES)){
                user.setDefaultFloorId((long) getRandom(sales));
            }
            userRepository.save(user);
        });

    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
