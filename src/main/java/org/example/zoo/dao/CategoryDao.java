package org.example.zoo.dao;

import org.example.zoo.entity.Animal;
import org.example.zoo.entity.Category;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDao {

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

    public List<Category> index() {
        List<Category> categories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM category";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Category category = new Category();

                category.setCategoryId(resultSet.getInt("CategoryId"));
                category.setCategoryName(resultSet.getString("CategoryName"));
                categories.add(category);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return categories;
    }

    public Category show(final int categoryId) {
        Category category = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM category WHERE CategoryId=?");

            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            category = new Category();
            category.setCategoryId(resultSet.getInt("CategoryId"));
            category.setCategoryName(resultSet.getString("CategoryName"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }
}
