package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annatation.CommandHandler;
import com.mjc.school.controller.implementation.builders.NewsRequestBuilder;
import com.mjc.school.service.implementation.Dto.ResponseDto;
import com.mjc.school.service.implementation.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsController implements BaseController {
    private final NewsService newsService;
    private final NewsRequestBuilder newsRequestBuilder;

    @Autowired
    public NewsController(NewsService newsService, NewsRequestBuilder newsRequestBuilder) {
        this.newsService = newsService;
        this.newsRequestBuilder = newsRequestBuilder;
    }

    @Override
    @CommandHandler
    public ResponseDto readAll() {
        return newsService.getAll(newsRequestBuilder.getAllRequest());
    }

    @Override
    @CommandHandler
    public ResponseDto readById() {
        return newsService.getById(newsRequestBuilder.getByIdRequest());
    }

    @Override
    @CommandHandler
    public ResponseDto create() {
        return newsService.create(newsRequestBuilder.createRequest());
    }

    @Override
    @CommandHandler
    public ResponseDto update() {
        return newsService.updateById(newsRequestBuilder.updateRequest());
    }
    @Override
    @CommandHandler
    public ResponseDto delete() {
        return newsService.removeById(newsRequestBuilder.removeByIdRequest());
    }

}
