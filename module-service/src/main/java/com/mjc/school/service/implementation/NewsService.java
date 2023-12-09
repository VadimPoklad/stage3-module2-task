package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.NewsDataSource;
import com.mjc.school.repository.interfaces.ModelInterface;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.implementation.Dto.ErrorDto;
import com.mjc.school.service.implementation.Dto.RequestDto;
import com.mjc.school.service.implementation.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class NewsService implements BaseService {
    private final NewsDataSource newsDataSource;
    private final Mapper mapper;
    private final Validator validator;

    @Autowired
    public NewsService(NewsDataSource newsDataSource, Mapper mapper, Validator validator) {
        this.newsDataSource = newsDataSource;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public ResponseDto create(RequestDto requestDto) {
        try {
            validator.validateInput(requestDto.getInputData());
            ModelInterface createSource = mapper.dtoToModel(requestDto.getInputData());
            ModelInterface createdModel = newsDataSource.executeCreateQuery(createSource);
            return ResponseDto.builder()
                    .status("OK")
                    .resultSet(Collections.singletonList(mapper.modelToDto(createdModel)))
                    .error(new ErrorDto())
                    .build();

        } catch (Exception e) {
            return buildErrorResponse(1,e);
        }
    }
    @Override
    public ResponseDto getAll(RequestDto requestDto) {
        try {
            List<ModelInterface> list = newsDataSource.executeSelectQuery();
            return ResponseDto.builder()
                    .status("OK")
                    .resultSet(list.stream().map(mapper::modelToDto).toList())
                    .error(new ErrorDto())
                    .build();
        } catch (Exception e) {
            return buildErrorResponse(2,e);
        }
    }
    @Override
    public ResponseDto getById(RequestDto requestDto) {
        try {
            Long id = Long.valueOf(requestDto.getLookupId());
            ModelInterface lookupModel = newsDataSource.modelExist(id);
            if(lookupModel == null){
                throw new IllegalArgumentException("Can not get, because id does not exist");
            }
            return ResponseDto.builder()
                    .status("OK")
                    .resultSet(Collections.singletonList(mapper.modelToDto(lookupModel)))
                    .error(new ErrorDto())
                    .build();
        } catch (Exception e) {
            return buildErrorResponse(3,e);
        }
    }
    @Override
    public ResponseDto updateById(RequestDto requestDto) {
        try {
            validator.validateInput(requestDto.getInputData());
            ModelInterface updateSource = mapper.dtoToModel(requestDto.getInputData());
            ModelInterface updatedModel = newsDataSource.executeUpdateQuery(updateSource);
            if(updatedModel == null)
                throw new IllegalArgumentException("Can not update, because id does not exist");
            return ResponseDto.builder()
                    .status("OK")
                    .resultSet(Collections.singletonList(mapper.modelToDto(updatedModel)))
                    .error(new ErrorDto())
                    .build();
        } catch (Exception e) {
            return buildErrorResponse(4,e);
        }
    }
    @Override
    public ResponseDto removeById(RequestDto requestDto) {
        try {
            newsDataSource.executeDeleteQuery(Long.parseLong(requestDto.getLookupId()));
            return ResponseDto.builder()
                    .status("OK")
                    .resultSet(new ArrayList<>())
                    .error(new ErrorDto())
                    .build();
        } catch (Exception e) {
            return buildErrorResponse(5,e);
        }
    }
    @Override
    public ResponseDto buildErrorResponse(int code, Exception e) {
        return ResponseDto.builder()
                .status("Failed")
                .error(new ErrorDto(code, e.getMessage()))
                .build();
    }
}