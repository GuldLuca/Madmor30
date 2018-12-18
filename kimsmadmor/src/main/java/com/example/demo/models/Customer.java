package com.example.demo.models;
//Objekt Type hvor vi samler informationer om kunder og oplysninger om disse
public class Customer
{
    private int id;
    private String firstname;
    private String lastname;
    private String fullName;
    private String adress;
    private String email;
    private int phonenumber;
    private int quantityAdults;
    private int quantityChildren;
    private boolean active;
    private String password;

    public Customer()                   //tom constructor til spring
    {

    }

    public Customer(int id, String firstname, String lastname, String adress, int phonenumber, String email, int quantityAdults, int quantityChildren, boolean active, String password)
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullName= firstname + " " +lastname;
        this.adress = adress;
        this.email = email;
        this.phonenumber = phonenumber;
        this.quantityAdults = quantityAdults;
        this.quantityChildren = quantityChildren;
        this.active = active;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
        this.fullName = this.firstname+ " "+ this.lastname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        this.fullName = this.firstname+ " "+ this.lastname;
    }

    public String getAdress()
    {
        return adress;
    }

    public void setAdress(String adress)
    {
        this.adress = adress;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public int getQuantityAdults()
    {
        return quantityAdults;
    }

    public void setQuantityAdults(int quantityAdults)
    {
        this.quantityAdults = quantityAdults;
    }

    public int getQuantityChildren()
    {
        return quantityChildren;
    }

    public void setQuantityChildren(int quantityChildren)
    {
        this.quantityChildren = quantityChildren;
    }

    public boolean getActive(){return active;}

    public void setActive(boolean active){this.active = active;}

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFullName(){
        return fullName;
    }

}
