package com.example.Project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;
    @ManyToOne
    @JoinColumn(name = "idRecipe")
    private Recipe recipe;
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;
    @Column(name = "comment")
    private String comment;
    @Column(name = "ratings")
    private int ratings;

    public Comment() {
    }
    public Comment(int idComment, Recipe recipe, User user, String comment, int ratings) {
        this.idComment = idComment;
        this.recipe = recipe;
        this.user = user;
        this.comment = comment;
        this.ratings = ratings;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", recipe=" + recipe +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", ratings=" + ratings +
                '}';
    }
}
