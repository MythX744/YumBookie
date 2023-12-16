package com.example.Project.dao;

import com.example.Project.Model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteDao extends JpaRepository<Favorite, Integer> {
}
