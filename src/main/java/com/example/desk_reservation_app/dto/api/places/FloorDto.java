package com.example.desk_reservation_app.dto.api.places;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FloorDto {

    private Long id;

    private int floorNumber;

}
