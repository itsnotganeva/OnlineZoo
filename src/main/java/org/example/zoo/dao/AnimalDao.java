package org.example.zoo.dao;

import org.example.zoo.entity.Animal;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class AnimalDao {
    private static int ANIMAL_COUNT;

    private static final String URL = "jdbc:mysql://localhost:3306/online_zoo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2878899mg";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Animal> index() {
        List<Animal> animals = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM animals";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Animal animal = new Animal();

                animal.setAnimalId(resultSet.getInt("AnimalsId"));
                animal.setCategoryId(resultSet.getInt("CategoryId"));
                animal.setAnimalName(resultSet.getString("AnimalName"));
                animal.setPopulation(resultSet.getString("Population"));
                animal.setHabitat(resultSet.getString("Habitat"));
                animal.setDiet(resultSet.getString("Diet"));
                animal.setImagePath(resultSet.getString("ImagePath"));
                animal.setVideoLink(resultSet.getString("VideoLink"));

                animals.add(animal);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return animals;
    }

    public Animal show(final int animalId) {
        Animal animal = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM animals WHERE AnimalsId=?");

            preparedStatement.setInt(1, animalId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            animal = new Animal();
            animal.setAnimalId(resultSet.getInt("AnimalsId"));
            animal.setCategoryId(resultSet.getInt("CategoryId"));
            animal.setAnimalName(resultSet.getString("AnimalName"));
            animal.setPopulation(resultSet.getString("Population"));
            animal.setHabitat(resultSet.getString("Habitat"));
            animal.setDiet(resultSet.getString("Diet"));
            animal.setImagePath(resultSet.getString("ImagePath"));
            animal.setVideoLink(resultSet.getString("VideoLink"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return animal;
    }

    public void save(Animal animal) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO animals VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, animal.getAnimalId());
            preparedStatement.setInt(2, animal.getCategoryId());
            preparedStatement.setString(3, animal.getAnimalName());
            preparedStatement.setString(4, animal.getPopulation());
            preparedStatement.setString(5, animal.getHabitat());
            preparedStatement.setString(6, animal.getDiet());
            preparedStatement.setString(7, animal.getImagePath());
            preparedStatement.setString(8, animal.getVideoLink());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(int animalId, Animal updatedAnimal) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE animals SET CategoryId=?, AnimalName=?," +
                            "Population=?, Habitat=?, Diet=? WHERE AnimalsId=?");
            preparedStatement.setInt(1, updatedAnimal.getCategoryId());
            preparedStatement.setString(2, updatedAnimal.getAnimalName());
            preparedStatement.setString(3, updatedAnimal.getPopulation());
            preparedStatement.setString(4, updatedAnimal.getHabitat());
            preparedStatement.setString(5, updatedAnimal.getDiet());
            preparedStatement.setInt(6, animalId);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int animalId){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM animals WHERE AnimalsId=?");

            preparedStatement.setInt(1,animalId);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
