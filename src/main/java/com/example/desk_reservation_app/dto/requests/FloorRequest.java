package com.example.desk_reservation_app.dto.requests;

import com.example.desk_reservation_app.models.enums.Department;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@Builder
public class FloorRequest {

    private Long id = null;

    private int floorNumber;

    private Long buildingId;

    private String floorName;

    private Department department;

    private byte[] floorPlan;


}
