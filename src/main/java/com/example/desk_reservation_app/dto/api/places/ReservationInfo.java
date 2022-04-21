package com.example.desk_reservation_app.dto.api.places;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReservationInfo {
    private String userFirstName;
    private String userLastName;

    public ReservationInfo(String userFirstName, String userLastName) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }
}
