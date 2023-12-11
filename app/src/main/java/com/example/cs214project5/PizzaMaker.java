package com.example.cs214project5;

/**
 * This class contains the createPizza() function
 * @author Seth Yeh
 * @author Vinh Pham
 */

public class PizzaMaker {
    /**
     * createPizza: "com.example.cs214project5.Pizza Factory" method that uses only a string to identify which instance of com.example.cs214project5.Pizza to make.
     * @param pizzaType String used to identify com.example.cs214project5.Pizza subclass
     * @return respective com.example.cs214project5.Pizza Object according to pizzaType String, otherwise null
     */
    public static Pizza createPizza(String pizzaType) {
        Pizza pizza;
        if ("deluxe".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Deluxe();
        } else if ("supreme".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Supreme();
        } else if ("meatzza".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Meatzza();
        } else if ("seafood".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Seafood();
        } else if ("pepperoni".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Pepperoni();
        } else if ("hawaiian".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Hawaiian();
        } else if ("stronteroni".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Stronteroni();
        } else if ("gracon".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Gracon();
        } else if ("stracon".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Stracon();
        } else if ("strimp".equalsIgnoreCase(pizzaType)) {
            pizza = new PizzaTypes.Strimp();
        } else if ("buildyourown".equalsIgnoreCase(pizzaType)) {
            pizza = new BuildYourOwn();
        } else {
            pizza = null; // Default to null if the type is unknown
        }
        return pizza;
    }
}
