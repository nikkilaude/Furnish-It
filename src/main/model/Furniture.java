package model;

import java.util.ArrayList;

// Represents a piece of furniture having a type, brand, price, and color.
public class Furniture {
    private String type;
    private String brand;
    private int cost;
    private String color;
    private int furnitureLength;
    private int furnitureWidth;

    // EFFECTS: creates a new piece of furniture with a type, brand, cost, color, length, and width
    public Furniture(String type, String brand, int cost, String color, int length, int width) {
        this.type = type;
        this.brand = brand;
        this.cost = cost;
        this.color = color;
        this.furnitureLength = length;
        this.furnitureWidth = width;
    }

    // MODIFIES: this
    // EFFECTS: sets furniture type to parameter
    public void setType(String type) {
        this.type = type;
    }

    // MODIFIES: this
    // EFFECTS: sets furniture brand to parameter
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // MODIFIES: this
    // EFFECTS: sets furniture cost to parameter
    public void setCost(int cost) {
        this.cost = cost;
    }

    // MODIFIES: this
    // EFFECTS: sets furniture color to parameter
    public void setColor(String color) {
        this.color = color;
    }

    // MODIFIES: this
    // EFFECTS: sets furniture length to parameter
    public void setFurnitureLength(int length) {
        this.furnitureLength = length;
    }

    // MODIFIES: this
    // EFFECTS: sets furniture width to parameter
    public void setFurnitureWidth(int width) {
        this.furnitureWidth = width;
    }

    // EFFECTS: returns furniture type
    public String getType() {
        return this.type;
    }

    // EFFECTS: returns furniture brand
    public String getBrand() {
        return this.brand;
    }

    // EFFECTS: returns furniture cost
    public int getCost() {
        return this.cost;
    }

    // EFFECTS: returns furniture color
    public String getColor() {
        return this.color;
    }

    // EFFECTS: returns furniture length
    public int getFurnitureLength() {
        return this.furnitureLength;
    }

    // EFFECTS: returns furniture width
    public int getFurnitureWidth() {
        return this.furnitureWidth;
    }

}
