package com.example.cs214project5;


import java.util.ArrayList;
/**
 * This class contains the functions that is related to ordering
 * @author Seth Yeh
 * @author Vinh Pham
 */

public class Order {

    private static int nextOrderNumber = 0;

    private int orderNumber;
    private ArrayList<Pizza> pizzas;

    /**
     * Order Object Constructor
     * Holds multiple Pizza Objects
     */
    public Order() {
        this.orderNumber = nextOrderNumber++;
        this.pizzas = new ArrayList<>();
    }

    /**
     * addPizza: adds Pizza to Order objects list of pizzas
     * @param pizza pizza to be added
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * removePizza: removes Pizza from Order objects list of pizzas
     * @param pizza pizza to be removed
     */
    public void removePizza(Pizza pizza) {
        if (pizzas != null) {
            pizzas.remove(pizza);
        }
    }
    /**
     * removePizza: removes Pizza from Order objects list of pizzas
     * @param index index of pizza to be removed
     */
    public void removePizza(int index) {
        if (pizzas != null) {
            if (index >= 0 && index < pizzas.size()) {
                pizzas.remove(index);
            }
        }
    }

    /**
     * getOrderNumber: returns the unique order number of the current Order Object
     * @return orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * getNextOrderNumber: returns the next order number after the current one.
     * @return nextOrderNumber
     */
    public int getNextOrderNumber() {
        return nextOrderNumber;
    }

    /**
     * getPizzas: Method to get the list of pizzas in the order as an ArrayList
     * @return
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * sets current order number to another integer value
     * @param orderNumber number of current order
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * toString Override: writes Order in string format to be read easily
     * @return String that lists all pizzas in string form and all their qualities.
     */
    @Override
    public String toString(){
        String fin = "";
        for(Pizza p : pizzas){
            fin = fin.concat(p.toString()+"\n");
        }
        return fin;
    }

    /**
     * caluclateSubTotalPrice: Method to calculate the subtotal price of the order
     * @return subtotal price of pizzas in order
     */
    public double calculateSubTotalPrice() {
        double totalPrice = 0.0;
        for (Pizza pizza : pizzas) {
            totalPrice += pizza.price();
        }
        return totalPrice;
    }

    /**
     * calculateSalesTax: calculates the sales tax of an order
     * @return sales tax of pizzas in order
     */
    public double calculateSalesTax(){
        return calculateSubTotalPrice()*0.0625;
    }

    /**
     * calculateTotal: calculates the total cost of all pizzas in an order
     * @return total cost
     */
    public double calculateTotal(){
        return calculateSalesTax() + calculateSubTotalPrice();
    }
}
