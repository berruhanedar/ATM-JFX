package com.berru.app.atmjfx.dao;

import com.berru.app.atmjfx.database.SingletonDBConnection;
import com.berru.app.atmjfx.dto.UserDTO;

import java.sql.Connection;
import java.util.Optional;

public interface IDaoImplements<T> extends ICrud, IGenericsMethod, ILogin {
    default Connection iDaoImplementsDatabaseConnection() {
        return SingletonDBConnection.getInstance().getConnection();
    }
    Optional<UserDTO> update(int id, UserDTO userDTO);
}
