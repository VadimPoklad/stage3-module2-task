package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annatation.CommandHandler;
import com.mjc.school.controller.implementation.builders.AuthorRequestBuilder;
import com.mjc.school.service.implementation.AuthorService;
import com.mjc.school.service.implementation.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AuthorController implements BaseController {
    private final AuthorService authorService;
    private final AuthorRequestBuilder authorRequestBuilder;

    @Autowired
    public AuthorController(AuthorService authorService, AuthorRequestBuilder authorRequestBuilder) {
        this.authorService = authorService;
        this.authorRequestBuilder = authorRequestBuilder;
    }
    @Override
    @CommandHandler
    public ResponseDto readAll() {
        return authorService.getAll(authorRequestBuilder.getAllRequest());
    }

    @Override
    @CommandHandler
    public ResponseDto readById() {
        return authorService.getById(authorRequestBuilder.getByIdRequest());
    }

    @Override
    @CommandHandler
    public ResponseDto create() {
        return authorService.create(authorRequestBuilder.createRequest());
    }

    @Override
    @CommandHandler
    public ResponseDto update() {
        return authorService.updateById(authorRequestBuilder.updateRequest());
    }
    @Override
    @CommandHandler
    public ResponseDto delete() {
        return authorService.removeById(authorRequestBuilder.removeByIdRequest());
    }
}
