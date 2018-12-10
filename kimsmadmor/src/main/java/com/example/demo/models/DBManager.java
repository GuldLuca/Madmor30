package com.example.demo.models;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class DBManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/kimsmadmor?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PW = "glade5Hjorte!";
    private Connection connection = null;


    public static void main(String[] args)
    {
        DBManager db = new DBManager();
        db.dbInit();
        /*String str="2015-03-31";
        Date date=Date.valueOf(str);//converting string into sql date
        Meal meal = new Meal(3, date,"ålålålål", "Noget med Karry", "Karry, gulerødder", "2");
        db.addMeal(meal);*/
        System.out.println(db.readAllMeals());

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
        List<Meal> mealList = new ArrayList<>();
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
                mealList.add(new Meal(id,date, description, name, elements, mealType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealList;
    }

   /* public Meal readMeal(int id)
    {
        return null;
    }*/



    public void updateMeal(Meal meal)
    {
        String sql = "UPDATE meal SET meal_date = ? , meal_description = ?, meal_name = ?, meal_elements = ?, meal_type_id = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, (java.sql.Date) meal.getDate());
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

    public void addEvent(Event event){
        String sql = "INSERT INTO event (event_name, event_description, event_start, event_address, max_capacity, price_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, event.getName());
            statement.setString(2, event.getDescription());
            statement.setDate(3, event.getStartDato());
            statement.setString(4, event.getAddress());
            statement.setInt(5, event.getCapacity());
            statement.setInt(6, event.getPrice_id());
            int rows = statement.executeUpdate();
            System.out.println("Rows added: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> readAllEvents(){
        String sql = "SELECT * FROM event";
        List<Event> eventList = new ArrayList<>();
        try {
            //connection = DriverManager.getConnection(DB_URL,DB_USER, DB_PW);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                Date startDato = resultSet.getDate(4);
                String address = resultSet.getString(5);
                int capacity = resultSet.getInt(6);
                int price_id = resultSet.getInt(7);
                eventList.add(new Event(id,name, description, (java.sql.Date) startDato, address, capacity, price_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public Meal readMeal(int id)
    {
        return null;
    }

    public void updateEvent(Event event)
    {
        String sql = "UPDATE event SET event_name = ? , event_description = ?, event_start = ?, event_address = ?, max_capacity = ?, price_id = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2,event.getDescription());
            preparedStatement.setDate(3, event.getStartDato());
            preparedStatement.setString(4, event.getAddress());
            preparedStatement.setInt(5, event.getCapacity());
            preparedStatement.setInt(6, event.getPrice_id());
            preparedStatement.executeUpdate();
            System.out.println(event.getName() + " opdateret");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(Event event){
        String sql = "DELETE FROM event WHERE id = ?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, event.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Slettet " + rowsAffected + " række(r).");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}


