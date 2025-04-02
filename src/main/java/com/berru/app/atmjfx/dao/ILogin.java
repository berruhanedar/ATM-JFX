package com.berru.app.atmjfx.dao;

import java.util.Optional;

public interface ILogin<T> {
    Optional<T> login(String username, String password);

}
