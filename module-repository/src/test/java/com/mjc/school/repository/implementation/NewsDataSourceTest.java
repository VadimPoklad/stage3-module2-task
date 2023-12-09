package com.mjc.school.repository.implementation;

import com.mjc.school.repository.RepositoryConfig;
import com.mjc.school.repository.implementation.model.NewsModel;
import com.mjc.school.repository.interfaces.ModelInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsDataSourceTest {

    static NewsDataSource newsDataSource = new AnnotationConfigApplicationContext(RepositoryConfig.class).getBean(NewsDataSource.class);
    @BeforeEach
    void before() throws Exception {
        Field field = newsDataSource.getClass().getDeclaredField("news");
        field.setAccessible(true);
        List<ModelInterface> list  = new ArrayList<>();
        list.add(NewsModel.builder()
                .id(1L)
                .title("firstTitle")
                .content("firstContent")
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build());
        list.add(NewsModel.builder()
                .id(2L)
                .title("secondTitle")
                .content("secondContent")
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build());
        field.set(new ArrayList<ModelInterface>(), list);
    }
    @Test
    void executeSelectQuery() throws Exception {
        Field field = newsDataSource.getClass().getDeclaredField("news");
        field.setAccessible(true);
        assertEquals(field.get(newsDataSource), newsDataSource.executeSelectQuery());
    }

    @Test
    void executeDeleteQuery() throws Exception {
        newsDataSource.executeDeleteQuery(1L);
        assertNotEquals(newsDataSource.executeSelectQuery().get(0).getId(), 1L);
    }

    @Test
    void executeUpdateQuery() throws Exception {
        NewsModel model = NewsModel.builder()
                .id(1L)
                .title("newFirstTitle")
                .content("newFirstContent")
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();
        newsDataSource.executeUpdateQuery(model);
        NewsModel newModel = (NewsModel) newsDataSource.executeSelectQuery().get(0);
        assertEquals(model.getTitle(), newModel.getTitle());
    }

    @Test
    void executeCreateQuery() throws Exception {
        NewsModel model = NewsModel.builder()
                .id(3L)
                .title("newTitle")
                .content("newContent")
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();
        newsDataSource.executeCreateQuery(model);
        List<ModelInterface> news = newsDataSource.executeSelectQuery();
        assertEquals(model, news.get(news.size()-1));
    }

    @Test
    void modelExistIfExist(){
        assertNotNull(newsDataSource.modelExist(1L));
    }
}