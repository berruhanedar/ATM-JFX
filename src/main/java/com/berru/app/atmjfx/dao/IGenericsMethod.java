package com.berru.app.atmjfx.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface IGenericsMethod<T> {
    public T mapToObjectDTO(ResultSet resultSet) throws SQLException;
    public Optional<T> selectSingle(String sql, Object... params);
}
