package com.example.desk_reservation_app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean is_available = true;

    private int deskNumber;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    private boolean deskDeleted = false;


    public Desk(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public Desk(int deskNumber, Room room) {
        this.deskNumber = deskNumber;
        this.room = room;
    }
}
