package com.example.desk_reservation_app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    @ToString.Exclude
    private List<Desk> desks = List.of();

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    private Floor floor;


    public Room(String roomName, List<Desk> desks) {
        this.roomName = roomName;
        this.desks = desks;
    }
}
