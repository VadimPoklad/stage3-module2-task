package com.mjc.school.repository.implementation.model;


import com.mjc.school.repository.interfaces.ModelInterface;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AuthorModel implements ModelInterface {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    public AuthorModel(String name) {
        this.name = name;
        createDate = LocalDateTime.now();
        lastUpdateDate = LocalDateTime.now();
    }
}
