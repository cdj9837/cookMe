package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class GroceryInfo extends AppCompatActivity {

    //ListView ingredientLV;
    String groupId;
    Button backButton;
    Button editButton;
    TextView nameEdit, amountEdit, unitEdit,textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_info);
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
                Intent intent=new Intent();
                setResult(1,intent);
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GroceryInfo.this, editItem.class);
                intent.putExtra("IngredientObj", ingr);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==1)
        {
            Intent intent=new Intent();
            setResult(1,intent);
            finish();
        }

        Bundle extract = data.getExtras();

        Ingredients ingred = (Ingredients) extract.getSerializable("IngrOBJ");

        Bundle send = new Bundle();
        send.putSerializable("IngrOBJ", ingred);

        Intent intent=new Intent();
        intent.putExtras(send);
        setResult(2,intent);
        finish();//finishing activity*/

    }
}
