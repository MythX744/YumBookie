package com.example.Project.Service;

import com.example.Project.Model.Favorite;
import com.example.Project.dao.FavoriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FavoriteService implements IFavoriteService{
    private FavoriteDao favoriteDao;
    @Autowired
    public FavoriteService(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }
    @Override
    public List<Favorite> findAll() {
        return favoriteDao.findAll();
    }

    @Override
    public Favorite findById(int id) {
        return favoriteDao.findById(id).get();
    }

    @Override
    public Favorite save(Favorite favorite) {
        return favoriteDao.save(favorite);
    }

    @Override
    public void deleteById(int id) {
        favoriteDao.deleteById(id);
    }
}
