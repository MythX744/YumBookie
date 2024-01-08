package com.example.Project.dao;

import com.example.Project.Model.Favorite;
import com.example.Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteDao extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser(User user);

}
