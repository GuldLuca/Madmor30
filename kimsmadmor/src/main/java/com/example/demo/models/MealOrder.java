package com.example.demo.models;
import java.time.LocalDate;

//Objekt Type hvor vi samler informationer om kunder og de måltider de har bestilt. ET MealOrder objekt Svarer til en
// i HTML tabellen.
public class MealOrder {
    private String firstName;
    private String  lastName;
    private String address;
    private String mealName;
    private String mealElements;
    private LocalDate date;
    private int numberOfAdults;
    private int numberOfChildren;

    public MealOrder() {                        //Tom constructor til spring

    }

    public MealOrder(String firstName, String lastName, String address, String mealName, String mealElements, int numberOfAdults, int numberOfChildren) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mealName = mealName;
        this.mealElements = mealElements;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealElements() {
        return mealElements;
    }

    public void setMealElements(String mealElements) {
        this.mealElements = mealElements;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

}
