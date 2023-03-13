package com.example.demo.controller;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(int id);
    Optional<User> get(long id);

    List<T> getAll();
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);
}
