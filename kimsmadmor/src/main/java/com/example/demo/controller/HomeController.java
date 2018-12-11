package com.example.demo.controller;

import com.example.demo.models.DBManager;
import com.example.demo.models.Customer;
import com.example.demo.models.Event;
import com.example.demo.models.Meal;
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

    @RequestMapping(value = "/addmeal", method = RequestMethod.GET)
    public String getAddMeal()
    {
        return "addmeal"; // henviser til addperson.html
    }

    @RequestMapping(value = "/addmeal", method = RequestMethod.POST)
    public String addMeal(Model model, Meal meal){
        dbManager.addMeal(meal);
        System.out.println("modtaget Person " + meal.getName());
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "mealMenu"; // henviser til list.html
    }

    @RequestMapping(value = "/mealDelete", method = RequestMethod.POST)
    public String deleteMeal(Model model, Meal meal)
    {
        dbManager.deleteMeal(meal);
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "mealMenu";
    }
    @GetMapping("/mealUpdate")
    public String mealUpdate(@RequestParam(value = "id", defaultValue = "1") int id, Model model) {
        model.addAttribute("meal", meals.get(id - 1));
        return "mealUpdate";
    }

    @PostMapping("/mealUpdate")
    public String mealUpdate(@ModelAttribute Meal meal) {
        int index = meal.getId();
        meal.setId(index);
        meals.set(index - 1, meal);
        return "redirect:/";
    }

   /* @RequestMapping(value= "/mealUpdate", method = RequestMethod.GET)
    public String getUpdateMeal(Model model, Meal meal)
    {
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "mealUpdate"; //html
    }

    @RequestMapping(value= "/mealUpdate", method = RequestMethod.POST)
    public String updateMeal(Model model, Meal meal){
        dbManager.updateMeal(meal);
        System.out.println("Person " + meal.getName() + " modtaget");
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meals);
        return "mealMenu";
    }*/

    @RequestMapping("/mealDetails")
    public String mealDetails(@RequestParam(value = "id", defaultValue = "1") int id, Model model) {
        model.addAttribute("meal", meals.get(id - 1));
        return "mealDetails";
    }

    @RequestMapping(value = "/addevent", method = RequestMethod.GET)
    public String getAddEvent()
    {
        return "addevent"; // henviser til addperson.html
    }

    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    public String addEvent(Model model, Event event){
        dbManager.addEvent(event);
        System.out.println("modtaget Event " + event.getName());
        events = dbManager.readAllEvents();
        model.addAttribute(eventStr, events);
        return "list"; // henviser til list.html
    }

    @RequestMapping(value = "/updateevent", method = RequestMethod.GET)
    public String getUpdateEvent(Model model, Event event)
    {
        events = dbManager.readAllEvents();
        model.addAttribute(eventStr, events);
        return "updateevent"; //html
    }

    @RequestMapping(value = "/updateevent",  method = RequestMethod.POST)
    public String updateEvent(Model model, Event event){
        dbManager.updateEvent(event);
        System.out.println("Event " + event.getName() + " modtaget");
        events = dbManager.readAllEvents();
        model.addAttribute(eventStr, events);
        return "list";
    }



}
