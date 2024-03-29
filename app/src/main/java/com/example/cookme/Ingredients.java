package com.example.cookme;

import java.io.Serializable;

class Ingredients implements Serializable {
    String Name;
    String Notes;
    String Unit;
    double IngredientID;
    String DisplayAmount;
    double Amount;

    public Ingredients(){

    }

    public Ingredients(String Name, String Notes, String Unit, double Amount) {
        this.Name = Name;
        this.Notes = Notes;
        this.Unit = Unit;
        this.IngredientID = 0;
        this.DisplayAmount = "";
        this.Amount = Amount;
    }

    public Ingredients(String name, String unit, double amount) {
        Name = name;
        Unit = unit;
        Amount = amount;
        DisplayAmount = "";
        IngredientID = 0;
        Notes = "";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public double getIngredientID() {
        return IngredientID;
    }

    public void setIngredientID(long ingredientID) {
        IngredientID = ingredientID;
    }

    public String getDisplayAmount() {
        return DisplayAmount;
    }

    public void setDisplayAmount(String displayAmount) {
        DisplayAmount = displayAmount;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }
}