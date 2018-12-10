package com.example.demo.models;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DBManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/kimsmadmor?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PW = "glade5Hjorte!";
    private Connection connection = null;


    public static void main(String[] args)
    {

    }

    public DBManager(){
        dbInit();
    }

    private void dbInit(){
        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER, DB_PW);
            System.out.println("OK dbInit() " + connection.getCatalog());
        }catch (Exception e){
            System.out.println("fejl i dbInit() " + e);
        }
    }

    public void addMeal(Meal meal){
        String sql = "INSERT INTO meal (meal_date, meal_description, meal_name, meal_elements, meal_type_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, meal.getDate());
            statement.setString(2, meal.getDescription());
            statement.setString(3, meal.getName());
            statement.setString(4, meal.getElements());
            statement.setString(5, meal.getMealType());
            int rows = statement.executeUpdate();
            System.out.println("Rows added: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Meal> readAllMeals(){
        String sql = "SELECT * FROM meal";
        List<Meal> list = new ArrayList<>();
        try {
            //connection = DriverManager.getConnection(DB_URL,DB_USER, DB_PW);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                Date date = resultSet.getDate(2);
                String description = resultSet.getString(3);
                String name = resultSet.getString(4);
                String elements = resultSet.getString(5);
                String mealType = resultSet.getString(6);
                list.add(new Meal(id,date, description, name, elements, mealType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Meal readMeal(int id)
    {
        return null;
    }



    public void updateMeal(Meal meal)
    {
        String sql = "UPDATE meal SET meal_date = ? , meal_description = ?, meal_name = ?, meal_elements = ?, meal_type_id = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, meal.getDate());
            preparedStatement.setString(2,meal.getDescription());
            preparedStatement.setString(3, meal.getName());
            preparedStatement.setString(3, meal.getElements());
            preparedStatement.setString(3, meal.getMealType());
            preparedStatement.executeUpdate();
            System.out.println(meal.getName() + " opdateret");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMeal(Meal meal){
        String sql = "DELETE FROM meal WHERE id = ?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, meal.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Slettet " + rowsAffected + " række(r).");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(Person person)
    {
        String sql = "SELECT * FROM person WHERE uname=? AND password=?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getUname());
            preparedStatement.setString(2, person.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                return true;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}


