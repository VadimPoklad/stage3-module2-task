package com.mjc.school.service.implementation.Dto;

import com.mjc.school.service.interfaces.ModelDtoInterface;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto implements ModelDtoInterface {
    private String status;
    private List<ModelDtoInterface> resultSet;
    private ErrorDto error;
}
