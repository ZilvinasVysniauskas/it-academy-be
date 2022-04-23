package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.models.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByDefaultFloorId(Long id);
    List<User> findAllByDepartment(Department department);
}
