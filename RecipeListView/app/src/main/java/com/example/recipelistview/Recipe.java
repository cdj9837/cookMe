package com.example.recipelistview;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    String Directions;
    double InventoryNum;
    double RecipeID;
    String RecipeName;
    ArrayList<com.example.recipelistview.Ingredients> Ingredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String Directions, double InventoryNum, double RecipeID, String RecipeName, ArrayList<com.example.recipelistview.Ingredients> Ingredients) {
        this.Directions = Directions;
        this.InventoryNum = InventoryNum;
        this.RecipeID = RecipeID;
        this.RecipeName = RecipeName;
        this.Ingredients = Ingredients;
    }

    public String getDirections() {
        return Directions;
    }

    public void setDirections(String directions) {
        Directions = directions;
    }

    public double getInventoryNum() {
        return InventoryNum;
    }

    public void setInventoryNum(double inventoryNum) {
        InventoryNum = inventoryNum;
    }

    public double getRecipeID() {
        return RecipeID;
    }

    public void setRecipeID(double recipeID) {
        RecipeID = recipeID;
    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public ArrayList<com.example.recipelistview.Ingredients> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(ArrayList<com.example.recipelistview.Ingredients> ingredients) {
        Ingredients = ingredients;
    }
}
