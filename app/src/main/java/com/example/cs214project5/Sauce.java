package com.example.cs214project5;

/**
 * com.example.cs214project5.Sauce Enum: Defines sauces available to choose from
 * @author Seth Yeh
 */

public enum Sauce {
    TOMATO("Tomato"),
    ALFREDO("Alfredo")
    ;
    final String sauceVal;

    /**
     * Default constructor
     * @param sauceVal string name of sauce enum.
     */
    Sauce(String sauceVal){
        this.sauceVal = sauceVal;
    }
}
