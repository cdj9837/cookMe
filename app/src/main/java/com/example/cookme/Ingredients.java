package com.example.cookme;

import java.io.Serializable;

class Ingredients implements Serializable {
    String Name;
    String Unit;
    long Amount;

    public Ingredients() {

    }

    public Ingredients(String name, String unit, long amount) {
        Name = name;
        Unit = unit;
        Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public long getAmount() {
        return Amount;
    }

    public void setAmount(long amount) {
        Amount = amount;
    }
}