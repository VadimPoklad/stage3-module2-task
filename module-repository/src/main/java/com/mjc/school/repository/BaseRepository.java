package com.mjc.school.repository;


import com.mjc.school.repository.interfaces.ModelInterface;

import java.util.List;


public interface BaseRepository {
     List<ModelInterface> executeSelectQuery() throws Exception;
     Boolean executeDeleteQuery(Long id) throws Exception;
     ModelInterface executeUpdateQuery(ModelInterface model) throws Exception;
     ModelInterface executeCreateQuery(ModelInterface model) throws Exception;
     ModelInterface modelExist(Long id) throws Exception;
     Long generateId();
}
