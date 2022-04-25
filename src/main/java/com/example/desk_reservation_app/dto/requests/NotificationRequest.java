package com.example.desk_reservation_app.dto.requests;

import com.example.desk_reservation_app.models.enums.Department;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {

    private String message;

    private Long userId;

    private boolean sentFromAdmin;

}
