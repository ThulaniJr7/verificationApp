package com.example.verificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


//  THIS Activity will be the page that gives user consent information.
//  This page should make the user aware of the consent that they are giving us by ticking the box
//  before they start the verification process
//  The layout functionality can be found in the "activity_main2.xml" file for editing.
//  The string values can be found in the "strings.xml" file for all the configurations
//  This java class is used for any functional calls and methods that need to be executed to perform
//  a specific action on the runtime environment.

public class MainActivity2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{

    RadioButton radioButton;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        radioButton = findViewById(R.id.tsAndCsCheck);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_logos_02);

    }

//  The code below is used to navigate from this current page to the next one

    public void formPage(View view)
    {

        int radioCheck;

        if(radioButton.isChecked())
        {
            radioCheck = 1;
        }
        else
        {
            radioCheck = 2;
        }

        if (radioCheck == 1)
        {
            startActivity(new Intent(MainActivity2.this, FirstVerification.class));
            finish();
        }
        else if (radioCheck == 2)
        {
            Toast.makeText(MainActivity2.this, "You have not accepted the Terms and Conditions! Please select to continue.", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.navigation_home:
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                return true;
        }

        return false;
    }

}