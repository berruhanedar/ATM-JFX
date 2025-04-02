package com.berru.app.atmjfx.dao;

import com.berru.app.atmjfx.database.SingletonDBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface IDaoImplements<T> extends ILogin{
    // CREATE
    Optional<T> create(T t);

    // LIST
    Optional<List<T>> list();

    // FIND
    Optional<T> findByName(String name);
    Optional<T> findById(int id);

    // UPDATE
    Optional<T> update(int id, T t);

    // DELETE
    Optional<T> delete(int id);

    // GENERICS METOT (LIST , FIND)
    T mapToObjectDTO(ResultSet resultSet);
    Optional<T> selectSingle(String sql , Object... params);


    default Connection iDaoImplementsDatabaseConnection(){
        return SingletonDBConnection.getInstance().getConnection();
    }
}
