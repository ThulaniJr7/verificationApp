package com.example.verificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

    public void newApplicationForm(View view){

//      Need to add an IF statement that only works when the Checkbox status is true
        startActivity(new Intent(MainActivity6.this, MainActivity2.class));

    }
}