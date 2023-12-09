package com.mjc.school.service.implementation.Dto;

import com.mjc.school.service.interfaces.ModelDtoInterface;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto implements ModelDtoInterface {
    private int code;
    private String message;
}
