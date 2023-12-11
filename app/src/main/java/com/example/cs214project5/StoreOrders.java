package com.example.cs214project5;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the functions that is used for the StoreOrdersController
 * @author Seth Yeh
 * @author Vinh Pham
 */

public class StoreOrders {
    private final static StoreOrders instance = new StoreOrders();
    private static int nextAvailableOrderNumber = 0;
    private int orderNum;
    private final ArrayList<Order> orders;

    /**
     * Default Constructor of com.example.cs214project5.StoreOrders
     */
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Instance of com.example.cs214project5.StoreOrders to have the same storeOrders accessible throughout Controllers and classes.
     * @return instance of storeorders instance.
     */
    public StoreOrders getInstance(){
        return instance;
    }

    /**
     * placeOrder: places an Order by adding it to Store Orders
     * @param order Order to be placed
     */
    public void placeOrder(Order order) {
        order.setOrderNumber(generateNextOrderNumber());
        orders.add(order);
    }

    /**
     * cancelOrder: cancels an Order by removing it to Store Orders
     * @param order Order to be cancelled
     */
    public void cancelOrder(Order order){
        orders.remove(order);
    }

    /**
     * Method to get the list of all orders in the storeOrders instance.
     * @return orders list
     */
    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    /**
     * toString Override: writes all orders in storeOrders instance to String
     * @return string containing all orders in the store orders instance.
     */
    public String toString(){
        String order = "";
        for(Order a : orders){
            order = order.concat("Order Number "+a.getOrderNumber()+"| Order Total: "+a.calculateTotal()+"\n");
            order = order.concat(a.toString()+"\n");
        }
        return order;
    }

    /**
     * generateNextOrderNumber: Private method to generate the next available order number
     * @return
     */
    private static int generateNextOrderNumber() {
        return nextAvailableOrderNumber++;
    }

    /**
     * testbed
     * @param args
     */
    public static void main(String[] args) {
        StoreOrders storeOrders = new StoreOrders();

        Order order1 = new Order();
        Order order2 = new Order();

        Pizza deluxePizza = PizzaMaker.createPizza("Deluxe");
        Pizza meatzzaPizza = PizzaMaker.createPizza("Meatzza");

        order1.addPizza(deluxePizza);
        order1.addPizza(deluxePizza);
        order2.addPizza(meatzzaPizza);

        storeOrders.placeOrder(order1);
        storeOrders.placeOrder(order2);

        // Display all orders and their details
        List<Order> allOrders = storeOrders.getAllOrders();
        for (Order order : allOrders) {
            System.out.println("Order Number: " + order.getOrderNumber());
            System.out.println("Pizzas in the Order:");
            for (Pizza pizza : order.getPizzas()) {
                System.out.println("- " + pizza.getClass().getSimpleName() + ": $" + pizza.price());
            }
            System.out.println("Total Order Price: $" + order.calculateTotal());
            System.out.println("-------------");
        }
    }


}