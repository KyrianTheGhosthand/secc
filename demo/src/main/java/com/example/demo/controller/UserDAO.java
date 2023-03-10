package com.example.demo.controller;

import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class UserDAO implements DAO<User>{
    private List<User> users = new ArrayList<>();

//    public UserDAO(){
//        users.add(new User("a1", "1234"));
//        users.add(new User("a2", "5678"));
//    }
@Override
public Optional<User> get(long id) {
    return Optional.ofNullable(users.get((int) id));
}

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, String[] params) {
        user.setUsername(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        user.setPassword(Objects.requireNonNull(
                params[1], "Password cannot be null"));

        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
