package com.berru.app.atmjfx.dao;

import com.berru.app.atmjfx.database.SingletonPropertiesDBConnection;

import java.sql.Connection;

public interface IDaoImplements<T> extends ICrud<T>, IGenericsMethod<T> {
    default Connection iDaoImplementsDatabaseConnection() {
        return SingletonPropertiesDBConnection.getInstance().getConnection();
    }
}
