package com.example.cs214project5;

/**
 * This class contains the prices of different com.example.cs214project5.Pizza types
 * @author Seth Yeh
 * @author Vinh Pham
 */

public class PizzaTypes {

    public static class Supreme extends Pizza {
        /**
         * price(): calculates price of supreme pizza
         * @return supreme pizza price
         */
        @Override
        public double price() {
            // Calculate the price for SupremePizza
            double price = 15.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            }
            if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }

    public static class Meatzza extends Pizza {
        /**
         * price(): calculates price of meatzaa pizza
         * @return supreme meatzza price
         */
        @Override
        public double price() {
            // Calculate the price for MeatzzaPizza
            double price = 16.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }

    public static class Deluxe extends Pizza {
        /**
         * price(): calculates price of deluxe pizza
         * @return deluxe pizza price
         */
        @Override
        public double price() {
            double price = 14.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }

    public static class Pepperoni extends Pizza {
        @Override
        public double price() {
            /**
             * price(): calculates price of pepperoni pizza
             * @return supreme pepperoni price
             */
            double price = 10.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }
    public static class Seafood extends Pizza {
        /**
         * price(): calculates price of seafood pizza
         * @return seafood pizza price
         */
        @Override
        public double price() {
            double price = 17.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.ALFREDO;
            return price;
        }
    }
    public static class Stronteroni extends Pizza {
        /**
         * price(): calculates price of Stronteroni pizza
         * @return Stronteroni pizza price
         */
        @Override
        public double price() {
            double price = 1001.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }
    public static class Hawaiian extends Pizza {
        /**
         * price(): calculates price of Hawaiian pizza
         * @return Hawaiian pizza price
         */
        @Override
        public double price() {
            double price = 14.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }
    public static class Gracon extends Pizza {
        /**
         * price(): calculates price of Gracon pizza
         * @return Gracon pizza price
         */
        @Override
        public double price() {
            double price = 11.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }
    public static class Stracon extends Pizza {
        /**
         * price(): calculates price of Stracon pizza
         * @return Stracon pizza price
         */
        @Override
        public double price() {
            double price = 200.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }
    public static class Strimp extends Pizza {
        /**
         * price(): calculates price of Strimp pizza
         * @return Strimp pizza price
         */
        @Override
        public double price() {
            double price = 301.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
            }
            if(extraCheese){
                price+= 1.00;
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }


}
