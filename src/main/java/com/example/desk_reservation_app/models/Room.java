package com.example.desk_reservation_app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    private int roomNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<Desk> desks = List.of();

}
