package com.example.homework_platform.login.dto;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private String role;
    private String status;
}
