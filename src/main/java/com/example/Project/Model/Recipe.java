package com.example.Project.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecipe;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column (name = "ingredients")
    private String ingredients;
    @Column (name = "stepPreparation")
    private String stepPreparation;
    @ElementCollection
    @CollectionTable(name = "recipe_images", joinColumns = @JoinColumn(name = "idRecipe"))
    @Column(name = "imageUrl")
    private ArrayList<String> image;

    @Column (name = "category")
    private String category;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Favorite> favorites = new HashSet<>();

    @Column(name = "cooking")
    private int cooking;

    @Column(name = "serving")
    private int serving;

    @Column(name = "preparation")
    private int preparation;

    public Recipe() {
    }

    public Recipe(int idRecipe, User user, String title, String description, String ingredients, String stepPreparation, ArrayList<String> image, String category, Set<Comment> comments, Set<Favorite> favorites, int cooking, int serving, int prepartation) {
        this.idRecipe = idRecipe;
        this.user = user;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.stepPreparation = stepPreparation;
        this.image = image;
        this.category = category;
        this.comments = comments;
        this.favorites = favorites;
        this.cooking = cooking;
        this.serving = serving;
        this.preparation = prepartation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getStepPreparation() {
        return stepPreparation;
    }

    public void setStepPreparation(String stepPreparation) {
        this.stepPreparation = stepPreparation;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public int getCooking() {
        return cooking;
    }

    public void setCooking(int cooking) {
        this.cooking = cooking;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public int getPreparation() {
        return preparation;
    }

    public void setPreparation(int prepartation) {
        this.preparation = prepartation;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "idRecipe=" + idRecipe +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", stepPreparation='" + stepPreparation + '\'' +
                ", image=" + image +
                ", category='" + category + '\'' +
                ", comments=" + comments +
                ", favorites=" + favorites +
                ", cooking=" + cooking +
                ", serving=" + serving +
                ", prepartation=" + preparation +
                '}';
    }
}
