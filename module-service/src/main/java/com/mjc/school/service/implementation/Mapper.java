package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.model.AuthorModel;
import com.mjc.school.repository.implementation.model.NewsModel;
import com.mjc.school.repository.interfaces.ModelInterface;
import com.mjc.school.service.implementation.Dto.AuthorDto;
import com.mjc.school.service.implementation.Dto.NewsDto;
import com.mjc.school.service.interfaces.AuthorMapper;
import com.mjc.school.service.interfaces.ModelDtoInterface;
import com.mjc.school.service.interfaces.NewsMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private final AuthorMapper authorMapper;
    private final NewsMapper newsMapper;
    private Mapper() {
        this.authorMapper = Mappers.getMapper(AuthorMapper.class);
        this.newsMapper = Mappers.getMapper(NewsMapper.class);
    }

    public ModelInterface dtoToModel(ModelDtoInterface modelDtoInterface){
        if(modelDtoInterface.getClass() == AuthorDto.class)
            return authorMapper.dtoToModel((AuthorDto) modelDtoInterface);
        else if (modelDtoInterface.getClass() == NewsDto.class)
            return newsMapper.dtoToModel((NewsDto) modelDtoInterface);
        throw new IllegalArgumentException("Unsupported mapper");
    }

    public ModelDtoInterface modelToDto(ModelInterface modelInterface){
        if(modelInterface.getClass() == AuthorModel.class)
            return authorMapper.modelToDto((AuthorModel) modelInterface);
        else if (modelInterface.getClass() == NewsModel.class)
            return newsMapper.modelToDto((NewsModel) modelInterface);
        throw new IllegalArgumentException("Unsupported mapper");
    }
}
