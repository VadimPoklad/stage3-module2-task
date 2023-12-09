package com.mjc.school.service;

import com.mjc.school.service.implementation.Dto.RequestDto;
import com.mjc.school.service.implementation.Dto.ResponseDto;

public interface BaseService {
    ResponseDto create(RequestDto requestDto);
    ResponseDto getAll(RequestDto requestDto);
    ResponseDto getById(RequestDto requestDto);
    ResponseDto updateById(RequestDto requestDto);
    ResponseDto removeById(RequestDto requestDto);
    ResponseDto buildErrorResponse(int code, Exception e);
}
