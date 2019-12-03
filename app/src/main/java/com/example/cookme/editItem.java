package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class editItem extends AppCompatActivity {
    private static final String TAG = "addItem";

    Button doneButton;
    Button backButton;
    EditText name,amount, unit;
    String Name, Amount, Unit;
    String groupId = MainActivity.groupID;

    DatabaseReference mRootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        name = (EditText)findViewById(R.id.itemNameEdit);
        amount =(EditText)findViewById(R.id.itemAmountEdit);
        unit = (EditText)findViewById(R.id.itemUnitEdit);
        doneButton = (Button) findViewById(R.id.doneButton);
        backButton = (Button) findViewById(R.id.backButton);


        final Ingredients ingr = (Ingredients) getIntent().getSerializableExtra("IngredientObj");

        name.setText(ingr.getName());
        amount.setText(Double.toString(ingr.getAmount()));
        unit.setText(ingr.getUnit());


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameSet = name.getText().toString();
                //Long amountSet = Long.parseLong(amount.getText().toString());
                String Amount = amount.getText().toString();
                //long LAmount = Long.parseLong(Amount);
                String unitSet = unit.getText().toString();
                ingr.setName(nameSet);
                //ingr.setAmount(amountSet);
                ingr.setUnit(unitSet);

                Ingredients sendBack = new Ingredients(nameSet, unitSet, 22.0);

                /*
                Bundle bundle = new Bundle;
                bundle.putSerializable("KEY", YOUR_OBJECT);
                intent.putExtras(bundle);
                 */
                Bundle bundle = new Bundle();
                bundle.putSerializable("IngrOBJ", sendBack);


                //String message=ingr.getName();
                String message = sendBack.getName();
                Intent intent=new Intent();
                intent.putExtras(bundle);
                setResult(2,intent);
                finish();
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String message=ingr.getName();
                Intent intent=new Intent();
                //intent.putExtra("MESSAGE",message);
                setResult(1,intent);
                finish();
            }
        });

    }
}