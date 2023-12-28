package com.example.Project.Service;

import com.example.Project.Model.Favorite;
import com.example.Project.Model.Recipe;
import com.example.Project.dao.FavoriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Favorite> result = favoriteDao.findById(id);

        Favorite favorite = null;

        if (result.isPresent()) {
            favorite = result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return favorite;
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
