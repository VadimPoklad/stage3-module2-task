package com.mjc.school.controller.implementation.builders;

import com.mjc.school.controller.annatation.CommandBody;
import com.mjc.school.controller.interfaces.RequestBuilderInterface;
import com.mjc.school.service.implementation.Dto.AuthorDto;
import com.mjc.school.service.implementation.Dto.RequestDto;
import com.mjc.school.service.interfaces.ModelDtoInterface;
import com.mjc.school.controller.implementation.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthorRequestBuilder implements RequestBuilderInterface {
    private final View view;
    @Autowired
    public AuthorRequestBuilder(View view) {
        this.view = view;
    }
    @Override
    public RequestDto getAllRequest() {
        return buildRequest(new AuthorDto());
    }
    @Override
    public RequestDto getByIdRequest() {
        return buildRequest(view.inputId(), new AuthorDto());
    }
    @Override
    public RequestDto createRequest() {
        return buildRequest(AuthorDto.builder()
                .name(view.inputName())
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build());
    }
    @Override
    public RequestDto updateRequest() {
        return buildRequest(AuthorDto.builder()
                .id(view.inputId())
                .name(view.inputName())
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build());
    }
    @Override
    public RequestDto removeByIdRequest() {
        return buildRequest(view.inputId(), new AuthorDto());
    }
    private RequestDto buildRequest(@CommandBody String id, ModelDtoInterface model){
        return RequestDto.builder()
                .lookupId(id)
                .inputData(model)
                .build();
    }
    private RequestDto buildRequest(@CommandBody ModelDtoInterface model){
        return RequestDto.builder()
                .inputData(model)
                .build();
    }
}
