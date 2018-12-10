package com.example.demo.models;


import java.sql.Date;

public class Meal
{
    private int id;
    private Date date;
    private String description;
    private String name;
    private String elements;


    public Meal(int id, Date date, String description, String name, String elements)
    {
        this.id = id;
        this.date = date;
        this.description = description;
        this.name = name;
        this.elements = elements;
    }

    public Meal()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
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

    public void Hej(){
        System.out.println("Hej");
    }
}
