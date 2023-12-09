package com.mjc.school.controller.implementation.builders;

import com.mjc.school.controller.annatation.CommandBody;
import com.mjc.school.controller.interfaces.RequestBuilderInterface;
import com.mjc.school.service.implementation.Dto.NewsDto;
import com.mjc.school.service.implementation.Dto.RequestDto;
import com.mjc.school.service.interfaces.ModelDtoInterface;
import com.mjc.school.controller.implementation.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NewsRequestBuilder implements RequestBuilderInterface {
    private final View view;
    @Autowired
    public NewsRequestBuilder(View view) {
        this.view = view;
    }
    @Override
    public RequestDto getAllRequest() {
        return buildRequest(new NewsDto());
    }
    @Override
    public RequestDto getByIdRequest() {
        return buildRequest(view.inputId(), new NewsDto());
    }
    @Override
    public RequestDto createRequest() {
        return buildRequest(NewsDto.builder()
                .title(view.inputTitle())
                .content(view.inputContent())
                .authorId(view.inputAuthorId())
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build());
    }
    @Override
    public RequestDto updateRequest() {
        return buildRequest(NewsDto.builder()
                .id(view.inputId())
                .title(view.inputTitle())
                .content(view.inputContent())
                .authorId(view.inputAuthorId())
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build());
    }
    @Override
    public RequestDto removeByIdRequest() {
        return buildRequest(view.inputId(), new NewsDto());
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

