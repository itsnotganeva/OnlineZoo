package org.example.zoo.entity;

public class Animal {
    private int categoryId;
    private int animalId;
    private String animalName;
    private String population;
    private String habitat;
    private String diet;
    private String imagePath;
    private String videoLink;


    private Category category;

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Animal() {
    }

    public Animal(int categoryId, int animalId, String animalName, String population, String habitat, String diet) {
        this.categoryId = categoryId;
        this.animalId = animalId;
        this.animalName = animalName;
        this.population = population;
        this.habitat = habitat;
        this.diet = diet;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalsId) {
        this.animalId = animalsId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }
}