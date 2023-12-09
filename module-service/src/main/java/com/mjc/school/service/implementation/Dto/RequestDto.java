package com.mjc.school.service.implementation.Dto;

import com.mjc.school.service.interfaces.ModelDtoInterface;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class RequestDto implements ModelDtoInterface {
    private String lookupId;
    private ModelDtoInterface inputData;
    public RequestDto(String lookupId, @NonNull ModelDtoInterface inputData) {
        this.lookupId = lookupId;
        this.inputData = inputData;
    }
}
