package com.mjc.school.service.implementation.Dto;

import com.mjc.school.service.annatation.Size;
import com.mjc.school.service.interfaces.ModelDtoInterface;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto implements ModelDtoInterface {
    private String id;
    @Size(min = 5, max = 30)
    private String title;
    @Size(min = 5, max = 255)
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private String authorId;
}
