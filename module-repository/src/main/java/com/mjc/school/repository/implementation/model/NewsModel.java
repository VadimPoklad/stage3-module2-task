package com.mjc.school.repository.implementation.model;

import com.mjc.school.repository.interfaces.ModelInterface;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class NewsModel implements ModelInterface {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;
    public NewsModel(String title, String content, Long authorId) {
        this.title = title;
        this.content = content;
        createDate = LocalDateTime.now();
        lastUpdateDate = LocalDateTime.now();
        this.authorId = authorId;
    }
}