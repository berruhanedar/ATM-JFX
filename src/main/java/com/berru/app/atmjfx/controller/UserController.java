package com.berru.app.atmjfx.controller;

import com.berru.app.atmjfx.dao.ICrud;
import com.berru.app.atmjfx.dao.ILogin;
import com.berru.app.atmjfx.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public class UserController implements ICrud<UserDTO>, ILogin<UserDTO> {


    @Override
    public Optional<UserDTO> create(UserDTO userDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<List<UserDTO>> list() {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> update(int id, UserDTO userDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> delete(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> loginUser(String username, String password) {
        return Optional.empty();
    }
}
