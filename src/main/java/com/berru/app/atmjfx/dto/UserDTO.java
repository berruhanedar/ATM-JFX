package com.berru.app.atmjfx.dto;

import lombok.*;

// Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder


public class UserDTO {
    // Field
    private Integer id;
    private String username;
    private String password;
    private String email;

    public static void main(String[] args) {
        UserDTO userDTO = UserDTO.builder()
                .id(1)
                .username("Berru")
                .password("123456")
                .email("berru@gmail.com")
                .build();
        System.out.println(userDTO);
    }

}
