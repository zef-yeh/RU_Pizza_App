package com.example.cs214project5;

/**
 * com.example.cs214project5.Sauce Enum: Defines Toppings available to choose from
 * @author Seth Yeh
 */
public enum Topping {
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREEN_PEPPER("Green Peppers"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    HAM("Ham"),
    BLACK_OLIVE("Black Olive"),
    BEEF("Beef"),
    SHRIMP("Shrimp"),
    SQUID("Squid"),
    CRAB_MEATS("Crab Meat"),
    PINEAPPLE("Pineapple"),
    BACON("Bacon"),
    STRONTIUM90("Strontium 90");
    /**
     * Default constructor
     * @param ToppingName string name of topping enum.
     */
    final String ToppingName;
    Topping(String ToppingName) {
        this.ToppingName = ToppingName;
    }
}
