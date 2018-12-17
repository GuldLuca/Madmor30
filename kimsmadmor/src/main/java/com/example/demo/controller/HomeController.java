package com.example.demo.controller;

import com.example.demo.models.DBManager;
import com.example.demo.models.Customer;
import com.example.demo.models.Event;
import com.example.demo.models.Meal;
import com.example.demo.models.MealOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private DBManager dbManager = new DBManager();
    private List<Customer> customers = new ArrayList<>();
    private static final String customerStr = "customers";
    private List<Meal> meals = new ArrayList<>();
    private static final String mealStr = "meals";
    private List<MealOrder> mealOrders = new ArrayList<>();
    private static final String mealOrderStr = "mealOrders";


    @RequestMapping("/")
    public String getIndex()
    {
        return "index"; //
    }

    @RequestMapping(value = "/mealMenu")
    public String getMealList(Model model){
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "mealMenu";
    }

    @RequestMapping(value = "/mealAdd", method = RequestMethod.GET)
    public String getAddMeal()
    {
        return "mealAdd"; // henviser til addperson.html
    }

    @RequestMapping(value = "/mealAdd", method = RequestMethod.POST)
    public String addMeal(Model model, Meal meal){
        dbManager.addMeal(meal);
        System.out.println("modtaget meal " + meal.getName());
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "mealMenu"; // henviser til list.html
    }
    @RequestMapping(value = "/mealDelete", method = RequestMethod.GET)
    public String getMealDelete(Model model, Meal meal)
    {
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meal);
        return "mealDelete"; //html
    }

    @RequestMapping(value = "/mealDelete", method = RequestMethod.POST)
    public String mealDelete(Model model, Meal meal)
    {
        dbManager.deleteMeal(meal);
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "mealMenu";
    }
    @RequestMapping(value = "/mealUpdate", method = RequestMethod.GET)
    public String getUpdateMeal(@RequestParam(value = "id", defaultValue = "1") int id, Model model)
    {
        meals = dbManager.readAllMeals();
        for(Meal m : meals)
        {
            if(m.getId() == id)
            {
                model.addAttribute("meal", m);
            }
        }

        return "mealUpdate"; //html
    }

    @RequestMapping(value = "/mealUpdate",  method = RequestMethod.POST)
    public String updateMeal(Model model, Meal meal){
        System.out.println(meal.getId());
        dbManager.updateMeal(meal);
        System.out.println("Meal " + meal.getName() + " modtaget");
        meals = dbManager.readAllMeals();
        System.out.println("meal hertil");
        model.addAttribute(mealStr, meals);
        System.out.println("hertil sidst");
        return "mealMenu";
    }

    @RequestMapping("/mealDetails")
    public String mealDetails(@RequestParam(value = "id", defaultValue = "1") int id, Model model) {

        for(Meal m : meals)
        {
            if(m.getId() == id)
            {
                model.addAttribute("meal", m);
            }
        }

        return "mealDetails";
    }

    @RequestMapping("/mealPackageMenu")
    public String mealPackageMenu(Model model) {
        customers = dbManager.getAllCustomersWithMealOrders();
        model.addAttribute(customerStr,customers);
        return "mealPackageMenu";
    }
    @RequestMapping("/mealPackageContent")
    public String mealPackageContent(@RequestParam(value = "id", defaultValue = "1") int id, Model model) {
        mealOrders= dbManager.getMealOrdersFromID(id);
        model.addAttribute(mealOrderStr,mealOrders);
        return "mealPackageContent";
    }

}
