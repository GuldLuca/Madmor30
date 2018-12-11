package com.example.demo.models;

public class MealOrder {
    private Customer customer;
    private Meal meal;
    private int numberOfAdults;
    private int numberOfChildren;

    public MealOrder() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
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

    public String getCustomerName(){
        return this.customer.getFirstname() +" " + this.customer.getLastname();
    }
    public String getCustomerAdresse(){
        return this.customer.getAdress();
    }
    public String getMealName(){
        return  this.meal.getName();
    }
    public String getMealElements(){
        return this.meal.getElements();
    }
    public  String getDateString(){
        return this.meal.getDate().toString();
    }
}
