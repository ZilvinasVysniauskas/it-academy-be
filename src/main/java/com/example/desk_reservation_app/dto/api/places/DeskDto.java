package com.example.desk_reservation_app.dto.api.places;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeskDto {
    private Long id;
    private String  deskName;
    private boolean isAvailable;
    private ReservationInfo info = null;

    public void reserveTable(String userName, String additionalInfo) {
        this.isAvailable = false;
        this.info = new ReservationInfo(userName, additionalInfo);
    }
}
