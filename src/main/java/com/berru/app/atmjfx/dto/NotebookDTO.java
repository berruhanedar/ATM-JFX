package com.berru.app.atmjfx.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotebookDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String category;
    private boolean pinned;
    private UserDTO userDTO;

    // Constructos
    // Getter and Setter
}
