package com.berru.app.atmjfx.dto;

import com.berru.app.atmjfx.utils.ERole;
import lombok.*;

// Lombok
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

// UserDTO
public class UserDTO {
    // Field
    private Integer id;
    private String username;
    private String password;
    private String email;
    private ERole role;

    public UserDTO(Integer id, String username, String password, String email, ERole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getter And Setter
    // Method

    /*
    public static void main(String[] args) {
        UserDTO userDTO= UserDTO.builder()
                .id(0)
                .username("username")
                .email("hamitmizrak@gmail.com")
                .password("root")
                .build();

        System.out.println(userDTO);
    }
    */
}
