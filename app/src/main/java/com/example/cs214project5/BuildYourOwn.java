package com.example.cs214project5;

/**
 * This class is to adjust the price of the com.example.cs214project5.BuildYourOwn com.example.cs214project5.Pizza
 * @author Seth Yeh
 * @author Vinh Pham
 */

public class BuildYourOwn extends Pizza {
    /**
     * price(): calculates price of buildYourOwn pizza
     * @return calculated price, otherwise 0 if toppings is empty, less than size 3, or more than size 7.
     */
        @Override
        public double price() {
            double price = 8.99;
            if(toppings != null) {
                if (!toppings.isEmpty()) {
                    if (size == Size.SMALL) {
                        price += 0;
                    } else if (size == Size.MEDIUM) {
                        price += 2.00;
                    } else if (size == Size.LARGE) {
                        price += 4.00;
                    } else {
                        price = 0;
                    }
                    if (extraCheese) {
                        price += 1.00;
                    }
                    if (extraSauce) {
                        price += 1.00;
                    }
                    if (toppings.size() < 3 || toppings.size() > 7) {
                        price = 0;
                    } else {
                        for (int i = 0; i < toppings.size(); i++) {
                            if (i > 2) {
                                price += 1.49;
                            }
                        }
                    }
                } else {
                    price = 0;
                }
            } else {
                price = 0;
            }
            return price;
        }
}