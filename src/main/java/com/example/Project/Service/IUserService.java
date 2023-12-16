package com.example.Project.Service;

import java.util.List;
import com.example.Project.Model.User;

public interface IUserService {
    List<User> findAll();
    User findById(int id);
    User save(User user);
    void deleteById(int id);

}
