package com.example.Project.Service;

import com.example.Project.Model.Recipe;
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
        Optional<User> user = userDao.findByEmail(email);
        return user.orElse(null);
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

    public User validateUser(String email, String password) {
        User user = userDao.findByEmail(email).orElse(null);
        if (user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
    public void update(User user) {
        userDao.save(user);
    }


}
