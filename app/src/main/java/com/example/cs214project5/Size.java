package com.example.cs214project5;

/**
 * com.example.cs214project5.Sauce Enum: Defines sauces available to choose from
 * @author Seth Yeh
 */
public enum Size {
    SMALL(0),
    MEDIUM(1),
    LARGE(2)
    ;
    final int size;
    /**
     * Default constructor
     * @param size string name of size enum.
     */
    Size(int size){
        this.size = size;
    }
}
