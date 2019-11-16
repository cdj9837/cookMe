package com.example.cookme;

public class Recipe_Official {
    private String RECIPE_NAME;
    private String RECIPE_DIRECTION;
    //Added later 3:40PM 11/13/2019 Everything else worked well
    private String INGREDIENT_NAME;
    private String INGREDIENT_AMOUNT;
    private String INGREDIENT_UNIT;
    private String INGREDIENT_NOTES;


    public Recipe_Official(String INGREDIENT_NAME, String INGREDIENT_AMOUNT, String INGREDIENT_UNIT, String INGREDIENT_NOTES) {
        this.INGREDIENT_NAME = INGREDIENT_NAME;
        this.INGREDIENT_AMOUNT = INGREDIENT_AMOUNT;
        this.INGREDIENT_UNIT = INGREDIENT_UNIT;
        this.INGREDIENT_NOTES = INGREDIENT_NOTES;
    }

    public String getINGREDIENT_UNIT() {
        return INGREDIENT_UNIT;
    }

    public void setINGREDIENT_UNIT(String INGREDIENT_UNIT) {
        this.INGREDIENT_UNIT = INGREDIENT_UNIT;
    }

    public String getINGREDIENT_NAME() {
        return INGREDIENT_NAME;
    }

    public void setINGREDIENT_NAME(String INGREDIENT_NAME) {
        this.INGREDIENT_NAME = INGREDIENT_NAME;
    }

    public String getINGREDIENT_AMOUNT() {
        return INGREDIENT_AMOUNT;
    }

    public void setINGREDIENT_AMOUNT(String INGREDIENT_AMOUNT) {
        this.INGREDIENT_AMOUNT = INGREDIENT_AMOUNT;
    }

    public String getINGREDIENT_NOTES() {
        return INGREDIENT_NOTES;
    }

    public void setINGREDIENT_NOTES(String INGREDIENT_NOTES) {
        this.INGREDIENT_NOTES = INGREDIENT_NOTES;
    }

    public Recipe_Official() {
    }

    public Recipe_Official(String RECIPE_NAME) {
        this.RECIPE_NAME = RECIPE_NAME;
    }

    public String getRECIPE_NAME() {
        return RECIPE_NAME;
    }

    public void setRECIPE_NAME(String RECIPE_NAME) {
        this.RECIPE_NAME = RECIPE_NAME;
    }


    public String getRECIPE_DIRECTION() {
        return RECIPE_DIRECTION;
    }

    public void setRECIPE_DIRECTION(String RECIPE_DIRECTION) {
        this.RECIPE_DIRECTION = RECIPE_DIRECTION;
    }
}