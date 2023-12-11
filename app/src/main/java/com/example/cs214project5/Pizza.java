package com.example.cs214project5;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * This abstract class contains the attributes of com.example.cs214project5.Pizza, and functions that is relevant to com.example.cs214project5.Pizza
 * @author Seth Yeh
 * @author Vinh Pham
 */

public abstract class Pizza {
    protected ArrayList<Topping> toppings; //com.example.cs214project5.Topping is a enum class
    protected Size size; //com.example.cs214project5.Size is a enum class
    protected Sauce sauce; //com.example.cs214project5.Sauce is a enum class
    protected boolean extraSauce;
    protected boolean extraCheese;
    public abstract double price(); //polymorphism

    /**
     * toString Override: prints pizza object and all its qualities in string format.
     * @return String
     */
    @Override
    public String toString(){ // try not to print it all to one string please thanks!!!
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String fin = "["+getClass().getSimpleName()+"]";
        String a = "";
        for(Topping top : toppings){
            a = a.concat(top+", ");
        }
        fin = fin.concat(" ["+size+"]");
        fin = fin.concat(" ["+sauce+"]");
        fin = fin.concat(" ["+a);
        if(extraSauce){
            fin = fin.concat("extra sauce, ");
        }
        if(extraCheese){
            fin = fin.concat("extra cheese, ");
        }
        fin = fin.concat("][$"+df.format(price())+"]");
        return fin;
    }

}