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
public class AuthorDto implements ModelDtoInterface {
    private String id;
    @Size(min = 3, max = 15)
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
