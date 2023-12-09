package com.mjc.school.service.interfaces;

import com.mjc.school.repository.implementation.model.NewsModel;
import com.mjc.school.service.implementation.Dto.NewsDto;
import org.mapstruct.Mapper;


@Mapper
public interface NewsMapper{
    NewsDto modelToDto(NewsModel newsModel);
    NewsModel dtoToModel(NewsDto newsDto);
}
