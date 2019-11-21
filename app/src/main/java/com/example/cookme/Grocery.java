package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Grocery extends AppCompatActivity {
    private static final String TAG = "Grocery";
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    //FirebaseAuth firebaseAuth;
    //FirebaseUser firebaseUser;

    Button addButton;
    Button backButton;
    ListView groceryLV;
    //String Uid;
    String groupId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
    }
}