package com.example.Project.Service;

import java.util.List;
import java.util.Optional;

import com.example.Project.Model.User;

public interface IUserService {
    List<User> findAll();

    Optional<User> findByEmail(String email);

    User findById(int id);
    User save(User user);
    void deleteById(int id);

    boolean validateUser(String email, String password);
}
