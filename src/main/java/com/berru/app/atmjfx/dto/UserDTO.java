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
    private String usermame;
    private String password;
    private String email;

}
