package com.example.Project.Model;

import jakarta.persistence.*;
@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFavor;
    @ManyToOne
    @JoinColumn(name = "idRecipe")
    private Recipe recipe;
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;
    public Favorite() {
    }
    public Favorite(int idFavor, Recipe recipe, User user) {
        this.idFavor = idFavor;
        this.recipe = recipe;
        this.user = user;
    }

    public int getIdFavor() {
        return idFavor;
    }

    public void setIdFavor(int idFavor) {
        this.idFavor = idFavor;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "idFavor=" + idFavor +
                ", recipe=" + recipe +
                ", user=" + user +
                '}';
    }
}
