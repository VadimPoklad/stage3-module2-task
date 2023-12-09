package com.mjc.school.repository.implementation;

import com.mjc.school.repository.implementation.model.NewsModel;
import com.mjc.school.repository.interfaces.ModelInterface;
import com.mjc.school.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class NewsDataSource implements BaseRepository {
    private static Long maxID;
    private static List<ModelInterface> news;
    @Value("${file.source.news}")
    private String newsPath;
    @Value("${file.source.content}")
    private String contentPath;
    @PostConstruct
    private void init(){
        try(Scanner newsScanner = new Scanner( new File(newsPath));
            Scanner contentScanner = new Scanner(new File(contentPath))) {
            long authorId = 1L;
            while (newsScanner.hasNext() && contentScanner.hasNext()){
                executeCreateQuery(new NewsModel(
                        newsScanner.nextLine(),
                        contentScanner.nextLine(),
                        authorId++));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public NewsDataSource(){
        maxID = 0L;
        news = new ArrayList<>();
    }
    @Override
    public List<ModelInterface> executeSelectQuery(){
        return news;
    }
    @Override
    public Boolean executeDeleteQuery(Long id){
        news.removeIf(news -> Objects.equals(news.getId(), id));
        if(Objects.equals(maxID, id)) maxID--;
        return true;
    }
    @Override
    public ModelInterface executeUpdateQuery(ModelInterface model){
        NewsModel oldModel = (NewsModel) modelExist(model.getId());
        NewsModel newModel = (NewsModel) model;
        oldModel.setTitle(newModel.getTitle());
        oldModel.setContent(newModel.getContent());
        oldModel.setAuthorId(newModel.getAuthorId());
        oldModel.setLastUpdateDate(newModel.getLastUpdateDate());
        return oldModel;
    }
    @Override
    public ModelInterface executeCreateQuery(ModelInterface model){
        NewsModel newsModel = (NewsModel) model;
        newsModel.setId(generateId());
        news.add(model);
        return newsModel;
    }
    @Override
    public ModelInterface modelExist(Long id){
        List<ModelInterface> list = executeSelectQuery()
                .stream()
                .filter(val -> Objects.equals(val.getId(),id))
                .toList();
        if(list.isEmpty()) return null;
        return list.get(0);
    }
    @Override
    public Long generateId() {
        return ++maxID;
    }
}
