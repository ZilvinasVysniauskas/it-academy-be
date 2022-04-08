package com.example.desk_reservation_app.models;

import com.example.desk_reservation_app.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "users_table")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private Long userId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private Role role;

    private boolean active;

    private String password;

}
