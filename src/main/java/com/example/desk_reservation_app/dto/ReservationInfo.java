package com.example.desk_reservation_app.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReservationInfo {
    private String username = "test";
    private String someInfo;

    public ReservationInfo(String username, String someInfo) {
        this.username = username;
        this.someInfo = someInfo;
    }
}
