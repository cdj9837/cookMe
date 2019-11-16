package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class addItem extends AppCompatActivity {
    Button doneButton;
    Button backButton;
    EditText name,amount, unit;
    String Name, Amount, Unit;
    TextView textView, textView2, textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);



        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });


    }

    public void openActivity1 ()
    {
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }

    public void openActivity2 ()
    {
        name = (EditText)findViewById(R.id.itemNameEdit);
        amount =(EditText)findViewById(R.id.itemAmountEdit);
        unit = (EditText)findViewById(R.id.itemUnitEdit);

        Name = name.getText().toString();
        Amount = amount.getText().toString();
        Unit = unit.getText().toString();


        Intent intent = new Intent(this, addItem.class);
        startActivity(intent);
    }
}