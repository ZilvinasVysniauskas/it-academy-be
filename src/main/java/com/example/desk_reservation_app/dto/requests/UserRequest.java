package com.example.desk_reservation_app.dto.requests;
import com.example.desk_reservation_app.models.enums.Role;
import com.sun.istack.NotNull;
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class UserRequest {

    @NotNull
    private Long userId;

    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String middleName;

    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String lastName;

    private String password;

    @NotNull
    private int isActive;

    @NotNull
    @NotEmpty
    @Pattern(regexp=".*@corporate\\.com$",message="must end with @corporate.com")
    private String email;

    @NotNull
    private Role role;

}
