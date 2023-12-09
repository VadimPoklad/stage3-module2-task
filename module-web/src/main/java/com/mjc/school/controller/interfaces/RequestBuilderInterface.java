package com.mjc.school.controller.interfaces;

import com.mjc.school.service.implementation.Dto.RequestDto;

public interface RequestBuilderInterface {
    RequestDto getAllRequest();
    RequestDto getByIdRequest();
    RequestDto createRequest();
    RequestDto updateRequest();
    RequestDto removeByIdRequest();
}
