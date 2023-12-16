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
    @JoinColumn(name = "id")
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


    public Recipe() {
    }

    public Recipe(int idRecipe, User user, String title, String description, String ingredients, String stepPreparation, ArrayList<String> image, String category) {
        this.idRecipe = idRecipe;
        this.user = user;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.stepPreparation = stepPreparation;
        this.image = image;
        this.category = category;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
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

    @Override
    public String toString() {
        return "Recipe{" +
                "idRecipe=" + idRecipe +
                ", idUser=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", stepPreparation='" + stepPreparation + '\'' +
                ", image=" + image +
                ", category='" + category + '\'' +
                '}';
    }
}
