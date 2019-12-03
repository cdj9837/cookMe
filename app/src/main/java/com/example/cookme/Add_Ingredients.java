package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Add_Ingredients extends AppCompatActivity {

    EditText ingredient_name;
    EditText ingredient_note;
    EditText ingredient_unit;
    EditText ingredient_amount;
    Ingredients ing_object;
    Recipe ingredient_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__ingredients);

        ingredient_name = (EditText)findViewById(R.id.ingredient_name_edit);
        ingredient_amount = (EditText)findViewById(R.id.ingredient_amount_edit);
        ingredient_unit = (EditText)findViewById(R.id.ingredient_unit_edit);
        ingredient_note = (EditText)findViewById(R.id.ingredient_note_edit);

        Button done_ingredient_button = (Button) findViewById(R.id.done_ingredient_button);
        Button add_ingredient_button = (Button) findViewById(R.id.add_ingredient_button);
        final Recipe recipe_2 = (Recipe)getIntent().getSerializableExtra("key");
        ingredient_add = new Recipe();
        final ArrayList<Ingredients> ingredients_temp = new ArrayList<>();
        add_ingredient_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(ingredient_name.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please type an ingredient name", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(ingredient_amount.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please type an ingredient amount", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(ingredient_unit.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please type ingredient unit", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String ingredient_note2 = "";
                    String ingredient_name2 = ingredient_name.getText().toString();
                    String ingredient_unit2 = ingredient_unit.getText().toString();
                    Double ingredient_amount2 = Double.parseDouble(ingredient_amount.getText().toString());
                    if(!TextUtils.isEmpty(ingredient_note.getText().toString())){
                        ingredient_note2 = ingredient_note.getText().toString();
                    }

                    ing_object = new Ingredients(ingredient_name2,ingredient_note2, ingredient_unit2, ingredient_amount2);
                    ingredients_temp.add(ing_object);
                    Toast.makeText(getApplicationContext(),"Ingredients Added", Toast.LENGTH_LONG).show();
                }
                ingredient_name.getText().clear();
                ingredient_amount.getText().clear();
                ingredient_note.getText().clear();
                ingredient_unit.getText().clear();
            }
        });
        done_ingredient_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipe_2.setIngredients(ingredients_temp);
                Intent i = new Intent(getApplicationContext(), Add_Recipe_Direction.class);
                i.putExtra("key2", recipe_2);
                startActivity(i);

            }
        });
    }
    private void getValues()
    {
        ing_object.setName(ingredient_name.getText().toString());
        ing_object.setNotes(ingredient_note.getText().toString());
        ing_object.setUnit(ingredient_unit.getText().toString());
        ing_object.setAmount(Double.parseDouble(ingredient_amount.getText().toString()));
    }
}
