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
    private List<Event> events = new ArrayList<>();
    private static final String eventStr ="events";
    private List<MealOrder> mealOrders = new ArrayList<>();
    private static final String mealOrderStr = "mealOrders";


    @RequestMapping("/")
    public String getIndex(Model model)
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
    public String getUpdateMeal(Model model, Meal meal)
    {
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meal);
        return "mealUpdate"; //html
    }

    @RequestMapping(value = "/mealUpdate",  method = RequestMethod.POST)
    public String updateMeal(Model model, Meal meal){
        dbManager.updateMeal(meal);
        System.out.println("Meal " + meal.getName() + " modtaget");
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "mealMenu";
    }

    @RequestMapping("/mealDetails")
    public String mealDetails(@RequestParam(value = "id", defaultValue = "1") int id, Model model) {
        model.addAttribute("meal", meals.get(id - 1));
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
