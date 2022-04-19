package com.example.desk_reservation_app.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building")
    @ToString.Exclude
    private List<Floor> floors;

    @OneToOne
    private Address address;

    private String name;
}
