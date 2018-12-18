package com.example.demo.models;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
// Objekt type hvor vi samler og opbevarer informationer om måltider
public class Meal
{
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")     //Date Time Format skal specificeres så det passer med HTML
    private LocalDate date;
    private String description;
    private String name;
    private String elements;
    private String mealType;


    public Meal(int id, LocalDate date, String description, String name, String elements, String mealType)
    {
        this.id = id;
        this.date = date;
        this.description = description;
        this.name = name;
        this.elements = elements;
        this.mealType = mealType;
    }

    public Meal()                       //Tom constructor til spring
    {

    }

    public Meal(int id)
    {
        this.id = id;
    }

    public String getMealType()
    {
        return mealType;
    }

    public void setMealType(String mealType)
    {
        this.mealType = mealType;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getElements()
    {
        return elements;
    }

    public void setElements(String elements)
    {
        this.elements = elements;
    }

}
