package com.example.desk_reservation_app.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int deskNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    public Desk(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public Desk() {
    }
}
