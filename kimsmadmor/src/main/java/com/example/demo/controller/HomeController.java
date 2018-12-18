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
/*Controller klassen håndterer requests fra klienter. Det er controlleren der "ved" hvilken HTML side der skal vises
hvornår, og hvilke metoder der skal kaldes hvornår. Det er Controlleren der sørger for at der bliver læst informationer
databasen, og at disse informationer bliver overført til HTML'en.
 */
@Controller
public class HomeController {

    //DBManager objektet bliver brugt hver gang der er behov for kontakt til databasen.
    private DBManager dbManager = new DBManager();
    //Arraylister der bliver brugt til at opbevare informationer fra databasen, og til at sende information til HTML
    //gennem Thymeleaf
    private List<Customer> customers = new ArrayList<>();
    // de private static final strings bliver brugt når arraylisterne bliver tilføjet spring modellerne.
    private static final String customerStr = "customers";
    private List<Meal> meals = new ArrayList<>();
    private static final String mealStr = "meals";
    private List<MealOrder> mealOrders = new ArrayList<>();
    private static final String mealOrderStr = "mealOrders";

    //Denne metode definerer hvilken HTML side, index, serveren skal vise når en klient requester "/".
    @RequestMapping("/")        // @RequestMapping("/") definerer hvilket request der skal udløse metoden LUCA
    public String getIndex()
    {
        return "index";         //Den returnerede String er identisk med navnet på den HTML fil vi ønsker vist.
    }

    @RequestMapping(value = "/mealMenu")
    public String getMealList(Model model){
        meals = dbManager.readAllMeals();       //Læser alle måltider i databasen og lægger dem i arraylisten Meals LUCA
        model.addAttribute(mealStr, meals);     // Tilføjer Meals til spring Modellen, så info fra meals kan vises i HTML filen
        return "mealMenu";
    }

    // "method = RequestMethod.GET" bruges når der skal sendes informationer fra server til klient LUCA
    @RequestMapping(value = "/mealAdd", method = RequestMethod.GET)
    public String getAddMeal()
    {
        return "mealAdd";
    }

    //"method = RequestMethod.POST" bliver brugt når der overføres information fra clienten til serveren
    @RequestMapping(value = "/mealAdd", method = RequestMethod.POST)
    public String addMeal(Meal meal){   //Der bliver oprettet et nyt meal objekt gennem HTML formularen, der blive givet som parameter LUCA
        dbManager.addMeal(meal);        //Meal objektet fra HTML formularen bliver gemt i databsen
        System.out.println("modtaget meal " + meal.getName());      //er blevet brugt til at test metoden. kan fjernes
        return "mealMenu"; // Sender brugeren tilbage til MealMenu efter at måltidet er blevet gemt.
    }
    @RequestMapping(value = "/mealDelete", method = RequestMethod.GET)
    public String getMealDelete(Model model, Meal meal)
    {
        meals = dbManager.readAllMeals();
        model.addAttribute(mealStr, meal);
        return "mealDelete";    //Sender brugeren til HTML sider der spørger om de ønsker at slette det valgte måltid LUCA
    }

    //Bliver kaldt hvis brugerne vælger "Ja" på mealDelete.html
    @RequestMapping(value = "/mealDelete", method = RequestMethod.POST)
    public String mealDelete(Meal meal)
    {
        dbManager.deleteMeal(meal);     //Slette det valgte måltid fra databasen 
        return "mealMenu";              //Sender brugeren tilbage til måltidsmenuen efter sletning
    }
    //Metode der bliver kaldt når brugeren trykker på "opdater knappen". Det pågældende måltids ID bliver givet med som parameter
    @RequestMapping(value = "/mealUpdate", method = RequestMethod.GET)
    public String getUpdateMeal(@RequestParam(value = "id", defaultValue = "1") int id, Model model)
    {
        for(Meal m : meals)                     // "Meals" traverseres indtil måltidet med det valgte id bliver fundet. LUCA LASSE
        {                                       //Dette Måltid tilføjes Spring modellen, der overfører oplysningerne
            if(m.getId() == id)                 // fra denne til html'en.
            {
                model.addAttribute("meal", m);
            }
        }

        return "mealUpdate";        //sender brugeren til mealUpdate.html med info fra det valgte måltid
    }
    //Metode der bliver kaldt når brugeren har ændret på det ønskede måltid og trykke opdater
    @RequestMapping(value = "/mealUpdate",  method = RequestMethod.POST)
    public String updateMeal(Meal meal){
        dbManager.updateMeal(meal);     //Gemmer det ændrede måltid, som er overført fra HTML formularen, i databasen LUCA
        System.out.println("Meal " + meal.getName() + " modtaget");     //Test metode
        return "mealMenu";              //Returnerer brugeren til måltidsmenu efter ændringer er gemt.
    }

    //Metode der bliver kaldt når brugeren trykker "Detaljer". Metoden bliver kaldt med det valgte måltidds ID.
    @RequestMapping("/mealDetails")
    public String mealDetails(@RequestParam(value = "id", defaultValue = "1") int id, Model model) {

        for(Meal m : meals)                     // "Meals" traverseres indtil måltidet med det valgte id bliver fundet. LUCA
        {                                       //Det valgte måltid tilføjes Spring modellen, der overfører
            if(m.getId() == id)                 // oplysningerne fra denne til html'en.
            {
                model.addAttribute("meal", m);
            }
        }

        return "mealDetails";                   //sender brugeren til mealDetails.html med info fra det valgte måltid
    }
    //Bliver kaldt når Måltidskasse menu bliver valgt i index.
    @RequestMapping("/mealPackageMenu")
    public String mealPackageMenu(Model model) {
        customers = dbManager.getAllCustomersWithMealOrders();  //Fylder "Customers" op med kunder der har bestilt måltider LUCA LASSE
        model.addAttribute(customerStr,customers);      //tilføjer "Cusomers" listen til Spring modellen
        return "mealPackageMenu";
    }
    //Bliver kaldt med den valgte kundes ID
    @RequestMapping("/mealPackageContent")
    public String mealPackageContent(@RequestParam(value = "id", defaultValue = "1") int id, Model model) {
        mealOrders= dbManager.getMealOrdersFromID(id); //Fylder "MealOrders" op med de ordre den valgte kunde har afgivet. LUCA LASSE
        model.addAttribute(mealOrderStr,mealOrders);    //tilføjer "MealOrders" til Spring Modellen.
        return "mealPackageContent";
    }

}
