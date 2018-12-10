package com.example.demo.controller;

import com.example.demo.models.DBManager;
import com.example.demo.models.Customer;
import com.example.demo.models.Meal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private List<Customer> customers = new ArrayList<>();
    private static final String customerStr = "customers";
    private static final String mealStr = "meals";
    private DBManager dbManager = new DBManager();
    private List<Meal> meals = new ArrayList<>();


    @RequestMapping("/")
    public String getIndex(Model model)
    {
        return "index"; //
    }

    @RequestMapping(value = "/", params = "list")
    public String getList(Model model){
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "list";
    }

    @RequestMapping(value = "/addmeal", method = RequestMethod.GET)
    public String getAddPerson()
    {
        return "addmeal"; // henviser til addperson.html
    }

    @RequestMapping(value = "/addmeal", method = RequestMethod.POST)
    public String addMeal(Model model, Meal meal){
        dbManager.addMeal(meal);
        System.out.println("modtaget Person " + meal.getName());
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "list"; // henviser til list.html
    }

    @RequestMapping(value = "/updatemeal", params = "deletemeal", method = RequestMethod.POST)
    public String deletePerson(Model model, Meal meal)
    {
        dbManager.deleteMeal(meal);
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "list";
    }

    @RequestMapping(value = "/updatemeal", method = RequestMethod.GET)
    public String getUpdatePerson(Model model, Meal meal)
    {
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "updatemeal"; //html
    }

    @RequestMapping(value = "/updatemeal",  method = RequestMethod.POST)
    public String updatePerson(Model model, Meal meal){
        dbManager.updateMeal(meal);
        System.out.println("Person " + meal.getName() + " modtaget");
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "list";
    }



}
