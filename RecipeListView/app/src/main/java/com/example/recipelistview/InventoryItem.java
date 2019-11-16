package com.example.recipelistview;

public class InventoryItem {
    String name;
    String unit;
    int amount;

    public InventoryItem(){

    }

    public InventoryItem(String name, String unit, int amount){
        this.name = name;
        this.unit = unit;
        this.amount = amount;
    }
}
