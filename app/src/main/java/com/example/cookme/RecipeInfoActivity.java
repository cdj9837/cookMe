package com.example.cookme;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeInfoActivity extends AppCompatActivity {

    ListView ingredientLV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);

        Recipe r = (Recipe) getIntent().getSerializableExtra("RecipeObj");

        ingredientLV = (ListView)findViewById(R.id.ingredientLV);
        TextView recipeInfoNameTV = (TextView)findViewById(R.id.recipeInfoNameTV);
        TextView recipeInfoDirectionsTV = (TextView)findViewById(R.id.recipeInfoDirectionsTV);

        recipeInfoNameTV.setText(r.RecipeName);
        recipeInfoDirectionsTV.setText(r.Directions);

        recipeInfoDirectionsTV.setMovementMethod(new ScrollingMovementMethod());

        IngredientsListAdapter adapter = new IngredientsListAdapter(this, R.layout.ingredient_list_layout, r.Ingredients);
        ingredientLV.setAdapter(adapter);
    }
}
