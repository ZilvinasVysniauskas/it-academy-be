package com.example.desk_reservation_app.dto.requests;

import lombok.Data;

@Data
public class PasswordChangeRequest {

    private String currentPassword;

    private String newPassword;

    private String newPasswordRepeat;

}
