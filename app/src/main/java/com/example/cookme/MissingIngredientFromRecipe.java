package com.example.cookme;

import java.util.Comparator;

public class MissingIngredientFromRecipe { //new object to populate recipe listview
    String recipeName;
    int numMissingIngredients;
    String recipeKey;

    public void incrementNum(){
        numMissingIngredients++;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getNumMissingIngredients() {
        return numMissingIngredients;
    }

    public void setNumMissingIngredients(int numMissingIngredients) {
        this.numMissingIngredients = numMissingIngredients;
    }

    public String getRecipeKey() {
        return recipeKey;
    }

    public void setRecipeKey(String recipeKey) {
        this.recipeKey = recipeKey;
    }

    public MissingIngredientFromRecipe(String recipeName, int numMissingIngredients, String recipeKey) {
        this.recipeName = recipeName;
        this.numMissingIngredients = numMissingIngredients;
        this.recipeKey = recipeKey;
    }

    public MissingIngredientFromRecipe(String recipeName, String recipeKey){
        this.recipeName = recipeName;
        numMissingIngredients = 0;
        this.recipeKey = recipeKey;
    }

    public MissingIngredientFromRecipe() {
    }
}

class Sortbymissing implements Comparator<MissingIngredientFromRecipe> //This lets me sort the arraylist of objects by the num of missing ingredients in each object.
{
    // Used for sorting in ascending order of
    // missing ingredients
    public int compare(MissingIngredientFromRecipe a, MissingIngredientFromRecipe b)
    {
        return a.numMissingIngredients - b.numMissingIngredients;
    }
}
