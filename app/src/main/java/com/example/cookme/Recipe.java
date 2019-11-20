package com.example.cookme;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    String Directions;
    double InventoryNum;
    double RecipeID;
    String RecipeName;
    ArrayList<Ingredients> Ingredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String Directions, double InventoryNum, double RecipeID, String RecipeName, ArrayList<Ingredients> Ingredients) {
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

    public void setInventoryNum(long inventoryNum) {
        InventoryNum = inventoryNum;
    }

    public double getRecipeID() {
        return RecipeID;
    }

    public void setRecipeID(long recipeID) {
        RecipeID = recipeID;
    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public ArrayList<Ingredients> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        Ingredients = ingredients;
    }
}