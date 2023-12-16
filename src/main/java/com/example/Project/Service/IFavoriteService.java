package com.example.Project.Service;

import com.example.Project.Model.Favorite;

import java.util.List;

public interface IFavoriteService {
    public List<Favorite> findAll();
    public Favorite findById(int id);
    public Favorite save(Favorite favorite);
    public void deleteById(int id);
}
