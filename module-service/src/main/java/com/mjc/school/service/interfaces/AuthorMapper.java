package com.mjc.school.service.interfaces;

import com.mjc.school.repository.implementation.model.AuthorModel;
import com.mjc.school.service.implementation.Dto.AuthorDto;
import org.mapstruct.Mapper;


@Mapper
public interface AuthorMapper{
    AuthorDto modelToDto(AuthorModel authorModel);
    AuthorModel dtoToModel(AuthorDto authorDto);
}
