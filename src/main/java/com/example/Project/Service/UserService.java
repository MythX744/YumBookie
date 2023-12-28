package com.example.Project.Service;

import com.example.Project.Model.User;
import com.example.Project.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    private UserDao userDao;
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    @Override
    public User findById(int id) {
            Optional<User> result = userDao.findById(id);

            User user = null;

            if (result.isPresent()) {
                user = result.get();
            }
            else {
                throw new RuntimeException("Did not find employee id - " + id);
            }
            return user;
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }
}
