package com.mjc.school.controller;


import com.mjc.school.service.implementation.Dto.ResponseDto;

public interface BaseController {
    ResponseDto readAll();
    ResponseDto readById();
    ResponseDto create();
    ResponseDto update();
    ResponseDto delete();
}
