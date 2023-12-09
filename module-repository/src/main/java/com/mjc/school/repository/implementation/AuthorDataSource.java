package com.mjc.school.repository.implementation;


import com.mjc.school.repository.implementation.model.AuthorModel;
import com.mjc.school.repository.implementation.model.NewsModel;
import com.mjc.school.repository.interfaces.ModelInterface;
import com.mjc.school.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.*;

@Component
public class AuthorDataSource implements BaseRepository {
    private static Long maxID;
    private static List<ModelInterface> authors;
    @Value("${file.source.author}")
    private String authorPath;
    private final NewsDataSource newsDataSource;
    @Autowired
    public AuthorDataSource(NewsDataSource newsDataSource){
        maxID = 0L;
        authors = new ArrayList<>();
        this.newsDataSource = newsDataSource;
    }
    @PostConstruct
    private void init(){
        try(Scanner scanner = new Scanner(new File(authorPath))) {
            while (scanner.hasNext()){
                executeCreateQuery(new AuthorModel(scanner.nextLine()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<ModelInterface> executeSelectQuery(){
        return authors;
    }
    @Override
    public Boolean executeDeleteQuery(Long id) throws Exception {
        List<ModelInterface> models = newsDataSource.executeSelectQuery()
                .stream()
                .filter(news -> ((NewsModel) news).getAuthorId().equals(id))
                .toList();
        for (ModelInterface model : models) {
            newsDataSource.executeDeleteQuery(model.getId());
        }
        authors.removeIf(author -> Objects.equals(author.getId(), id));
        if(Objects.equals(maxID, id)) maxID--;
        return true;
    }
    @Override
    public ModelInterface executeUpdateQuery(ModelInterface model){
        AuthorModel old = (AuthorModel) modelExist(model.getId());
        AuthorModel newModel = (AuthorModel) model;
        old.setName(newModel.getName());
        old.setLastUpdateDate(newModel.getLastUpdateDate());
        return old;

    }
    @Override
    public ModelInterface executeCreateQuery(ModelInterface model){
        AuthorModel authorModel = (AuthorModel) model;
        authorModel.setId(generateId());
        authors.add(authorModel);
        return authorModel;
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
