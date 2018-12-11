package com.example.demo.models;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Event
{
    private int id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDato;
    private String address;
    private int capacity;
    private int price_id;

    public Event(int id, String name, String description, LocalDate startDato, String address, int capacity, int price_id)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDato = startDato;
        this.address = address;
        this.capacity = capacity;
        this.price_id = price_id;
    }

    public Event()
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDate getStartDato()
    {
        return startDato;
    }

    public void setStartDato(LocalDate startDato)
    {
        this.startDato = startDato;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public int getPrice_id()
    {
        return price_id;
    }

    public void setPrice_id(int price_id)
    {
        this.price_id = price_id;
    }
}
