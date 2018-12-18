package com.example.demo.models;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
// DBManager klassen har til opgave at kommunikerer med databasen
public class DBManager {
    // De 3 static final Strings udgør de nødvendige parametre der skal til for at oprette Connection objektet.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kimsmadmor?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PW = "glade5Hjorte!";
    // Tomt Connection objekt der bliver sat i dbInit metoden. Det er dette objekt der bliver brugt til at kommunikere
    //med databasen
    private Connection connection = null;

    //Overskrivning af defualt constructuren. Ved at kalde dbInit i constructuren sikres det at tomme connection objekt
    //bliver overskrevet når der laves et nyt objekt af DBManager.
    public DBManager(){
        dbInit();
    }
    //Sætter Connection til ikke at være et tomt Connection objekt. Printer status besked i tilfælde af fejl og succes
    private void dbInit(){
        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER, DB_PW);
            System.out.println("OK dbInit() " + connection.getCatalog());
        }catch (Exception e){
            System.out.println("fejl i dbInit() " + e);
        }
    }
    // Tager i mod et Meal Objekt og gemmer det i databasen.
    public void addMeal(Meal meal){
        String sql = "INSERT INTO meal (meal_date, meal_description, meal_name, meal_elements, meal_type_id) VALUES (?, ?, ?, ?, ?)";
        try     //SQL-stringen indeholder et query med "?" i stedet for værdier. "?" bliver defineret statement.set
        {       //linjerne nedenunder. Dette kaldes prepared statement og det undgår SQL Injection.
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, meal.getDate());     //bruger setObject i stedet for setDate,da Local Date er et objekt
            statement.setString(2, meal.getDescription());
            statement.setString(3, meal.getName());
            statement.setString(4, meal.getElements());
            statement.setString(5, meal.getMealType());
            int rows = statement.executeUpdate();   //query eksekveres. hvis det lykkes sættes row til et, ellers sættes den som 0
            System.out.println("Rows added: " + rows);      //printer statusbesked
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // læser alle meals i databasen, lægger dem i en arrayliste, og returnerer listen.
    public List<Meal> readAllMeals(){
        String sql = "SELECT * FROM meal";          //Query der læser alle rækker fra meal tabellen.
        List<Meal> mealList = new ArrayList<>();    //Opretter en arrayliste med Meal objekter.
        try
        {
            Statement statement = connection.createStatement();     //opretter statement objekt fra connection.
            ResultSet resultSet = statement.executeQuery(sql);      //Får resultset ved at kalde executeQuery med sql
            while (resultSet.next()){                               //stringen på statement objektet.
                int id = resultSet.getInt(1);
                LocalDate date = resultSet.getObject(2, LocalDate.class);
                String description = resultSet.getString(3);        //gemmer værdierne fra resultset
                String name = resultSet.getString(4);               //i variabler der bliver brugt
                String elements = resultSet.getString(5);           // til oprette meal objekter
                String mealType = resultSet.getString(6);           //der bliver lagt i arraylisten.
                mealList.add(new Meal(id,date, description, name, elements, mealType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealList;            //Arrayliste returneres
    }
    //Tager i mod et måltid, og ændre den række der har det samme ID til at have de samme værdier
    // som det medsendte måltid.
    public void updateMeal(Meal meal)
    {   //SQL query med udefinerede værdier.
        String sql = "UPDATE meal SET meal_date = ? , meal_description = ?, meal_name = ?, meal_elements = ?, meal_type_id = ? WHERE meal_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);     //prepared statements oprettes.
            preparedStatement.setObject(1, meal.getDate());                 //værdier sættes.
            preparedStatement.setString(2,meal.getDescription());
            preparedStatement.setString(3, meal.getName());
            preparedStatement.setString(4, meal.getElements());
            preparedStatement.setString(5, meal.getMealType());
            preparedStatement.setInt(6, meal.getId());
            int rows = preparedStatement.executeUpdate();               //query eksekveres.
            System.out.println(rows + " linjer opdateret");             //status besked.

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Tager imod et måltid og bruger dennes id til at slette en række i databasen.
    public void deleteMeal(Meal meal){
        String sql = "DELETE FROM meal WHERE meal_id = ?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, meal.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Slettet " + rowsAffected + " række(r).");       //statusbesked.
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Returnere en arrayliste med alle kunder der har afgivet en måltids bestilling.
    public List<Customer> getAllCustomersWithMealOrders(){
        String sql = "" +
                "SELECT customer_id, customer_firstname, customer_lastname, customer_address " +
                "FROM customer " +              //Vi har ikke valgt at selecte alle fields fra Customer, men kun dem der
                "WHERE customer_id " +          //skal vises i oversigten i måltidskasse menu.
                "IN (" +                        //WHERE X IN (Y)condition kræver at x findes i listen y for at den er sand.
                    "SELECT customer_id " +             //Indholdet af parantesen efter IN udgør den liste som customer_id
                    "FROM customer_meal_linkedlist)" +  //skal være indholdt i for blive returneret. Den er i dette tilfælde
                " ORDER BY customer_firstname";         //et subquery.
        List<Customer> customerList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){                                   //Resultatet af SQL-querien gemmes i variabler
                int id = resultSet.getInt(1);               // der bliver brugt til at oprette objekter
                String firstName = resultSet.getString(2);  //der bliver lagt i arraylisten.
                String lastName = resultSet.getString(3);
                String adress = resultSet.getString(4);
                    Customer c = new Customer();
                    c.setId(id);
                    c.setFirstname(firstName);
                    c.setLastname(lastName);
                    c.setAdress(adress);
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;        //arraylisten returneres
    }
    //Tager i mod et ID og bruger dette til at samle de nødvendige informationer fra 3 tabeller for at give nødvendige info
    //om måltiddskassers indhold. Hver række i resultset repræsenterer en kundes bestilling.
    public List<MealOrder> getMealOrdersFromID(int id){
        String sql =    //SQL Query der samler information fra customer, meal og customer_meal_linkedlist.
                "SELECT customer_firstname, customer_lastname, customer_address, meal_name, meal_elements, meal_number_of_adults, meal_number_of_children " +
                        "FROM customer_meal_linkedlist " +
                        "INNER JOIN customer ON customer_meal_linkedlist.customer_id= customer.customer_id " +
                        "INNER JOIN meal ON customer_meal_linkedlist.meal_id = meal.meal_id " +
                        "WHERE customer_meal_linkedlist.customer_id="+id;
        List<MealOrder> mealOrderList = new ArrayList<>();      //opretter MealOrder Arrayliste.
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String firstName = resultSet.getString(1);      //resultater gemmes i variabler.
                String lastName = resultSet.getString(2);
                String adress = resultSet.getString(3);
                String mealName = resultSet.getString(4);
                String mealElements = resultSet.getString(5);
                int numberOfAdults = resultSet.getInt(6);
                int numberOfChildren = resultSet.getInt(7);
                mealOrderList.add(new MealOrder(firstName,lastName, adress, mealName, mealElements,
                        numberOfAdults,numberOfChildren));          //variabler bruges til oprette objekter
            }                                                       // objekter bliver lagt i arrayliste.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealOrderList;                           //arrayliste returneres.
    }



}


