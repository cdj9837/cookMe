package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class InventoryInfo extends AppCompatActivity {

    //ListView ingredientLV;
    String groupId;
    Button backButton;
    Button editButton;
    TextView nameEdit, amountEdit, unitEdit,textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_info);
        final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        final Ingredients ingr = (Ingredients) getIntent().getSerializableExtra("IngredientObj");

        backButton =(Button) findViewById(R.id.backButton);
        editButton = (Button) findViewById(R.id.editButton);
        nameEdit = (TextView) findViewById(R.id.nameEdit);
        amountEdit = (TextView) findViewById(R.id.amountEdit);
        unitEdit = (TextView) findViewById(R.id.unitEdit);
        textView1 = (TextView) findViewById(R.id.textView1);

        String name, amount, unit;
        double am;

        am = ingr.getAmount();
        name = ingr.getName();
        unit = ingr.getUnit();
        amount = Double.toString(am);
        groupId=MainActivity.groupID;

        nameEdit.setText(name);
        amountEdit.setText(amount);
        unitEdit.setText(unit);

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

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(InventoryInfo.this, editItem.class);
                intent.putExtra("IngredientObj", ingr);
                startActivityForResult(intent,1);


                /*String message=ingr.getName();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",message);
                setResult(2,intent);
                finish();//finishing activity*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        final Ingredients ingr = (Ingredients) getIntent().getSerializableExtra("IngredientObj");

        String message=ingr.getName();
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
        //textView1.setText(message);
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",message);
        setResult(2,intent);
        finish();//finishing activity*/

        // check if the request code is same as what is passed  here it is 2
        //if(requestCode==2)
        //{
        //String message=data.getStringExtra("MESSAGE");
        //textView1.setText(message);
        //Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();

        //String message=data.getStringExtra("MESSAGE");
        //textView1.setText(message);
        //}
    }
}