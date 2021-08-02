package com.example.verificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


//  THIS Activity will be the page that gives user consent information.
//  This page should make the user aware of the consent that they are giving us by ticking the box
//  before they start the verification process
//  The layout functionality can be found in the "activity_main2.xml" file for editing.
//  The string values can be found in the "strings.xml" file for all the configurations
//  This java class is used for any functional calls and methods that need to be executed to perform
//  a specific action on the runtime environment.

public class MainActivity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }

//  The code below is used to navigate from this current page to the next one

    public void formPage(View view){

//      Need to add an IF statement that only works when the Checkbox status is true
        startActivity(new Intent(MainActivity2.this, FirstVerification.class));

    }

}