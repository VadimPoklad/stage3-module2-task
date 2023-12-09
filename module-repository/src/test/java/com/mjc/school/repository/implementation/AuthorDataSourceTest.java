package com.mjc.school.repository.implementation;

import com.mjc.school.repository.RepositoryConfig;
import com.mjc.school.repository.implementation.model.AuthorModel;
import com.mjc.school.repository.interfaces.ModelInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDataSourceTest {
    static AuthorDataSource authorDataSource = new AnnotationConfigApplicationContext(RepositoryConfig.class).getBean(AuthorDataSource.class);
    @BeforeEach
    void before() throws Exception {
        Field field = authorDataSource.getClass().getDeclaredField("authors");
        field.setAccessible(true);
        List<ModelInterface> list  = new ArrayList<>();
        list.add(AuthorModel.builder()
                .id(1L)
                .name("one")
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build());
        list.add(AuthorModel.builder()
                .id(2L)
                .name("two")
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build());
        list.add(AuthorModel.builder()
                .id(3L)
                .name("three")
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build());
        field.set(new ArrayList<ModelInterface>(), list);
    }
    @Test
    void executeSelectQuery() throws Exception {
        Field field = authorDataSource.getClass().getDeclaredField("authors");
        field.setAccessible(true);
        assertEquals(field.get(authorDataSource), authorDataSource.executeSelectQuery());
    }

    @Test
    void executeDeleteQuery() throws Exception {
        authorDataSource.executeDeleteQuery(1L);
        assertNotEquals(authorDataSource.executeSelectQuery().get(0).getId(), 1L);
    }

    @Test
    void executeUpdateQuery() throws Exception {
        AuthorModel model = AuthorModel.builder()
                .id(1L)
                .name("new")
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();
        authorDataSource.executeUpdateQuery(model);
        AuthorModel newModel = (AuthorModel) authorDataSource.executeSelectQuery().get(0);
        assertEquals(model.getName(), newModel.getName());
    }

    @Test
    void executeCreateQuery() throws Exception {
        AuthorModel model = AuthorModel.builder()
                .id(4L)
                .name("new")
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();
        authorDataSource.executeCreateQuery(model);
        List<ModelInterface> authors = authorDataSource.executeSelectQuery();
        assertEquals(model, authors.get(authors.size()-1));
    }

    @Test
    void modelExistIfExist() {
        assertNotNull(authorDataSource.modelExist(1L));
    }
}