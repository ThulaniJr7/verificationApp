package com.example.verificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//  THIS Activity will be the first page that allows the user to fill in their details for the verification process.
//  The user should be able to enter their name, surname, email address, phone number and ID number and proceed.
//  The layout functionality can be found in the "activity_main3.xml" file for editing.
//  The string values can be found in the "strings.xml" file for all the configurations
//  This java class is used for any functional calls and methods that need to be executed to perform
//  a specific action on the runtime environment.

public class MainActivity3 extends AppCompatActivity {

//  ----- Variables for the user inputs will be saved and utilized below -----

    SharedPreferences Sprefs;
    EditText username;
    EditText surname;
    EditText emailAddress;
    EditText idNumber;
    Button btnSave;
    String Value;
    private String prefName;
    int c;
    int validChecks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//      We use the following declarations to store information into the variables from the information
//      that was retrieved from the relevant ID names and the inputs

        username = findViewById(R.id.nameInputForm);
        surname = findViewById(R.id.surnameInputForm);
        emailAddress = findViewById(R.id.emailAddressInputForm);
        idNumber = findViewById(R.id.idNumberInputForm);
        btnSave = findViewById(R.id.nextButton);
        c = 0;
        validChecks = 0;

//      Need to create all the parameters that will initiate the saving of the variables that are inputted by
//      the user.

    }

//  The code below is used to navigate from this current page to the next one while executing the
//  necessary code to save the information that was inputted by the user
    public void formPage2 (View view) {

        String nameStr = username.getText().toString();
        String surnameStr = surname.getText().toString();
        String emailAddressStr = emailAddress.getText().toString();
        String idNumberStr = idNumber.getText().toString();

        Sprefs = getSharedPreferences("prefName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Sprefs.edit();

//      Need to create an IF statement to check if there are 13 numbers that were entered to the ID Number

        int idNum = idNumberStr.length();

        for (int i = 0; i != idNumberStr.length(); i++)
        {
            c++;
        }

        if (c == 13)
        {
            validChecks = validChecks + 1;
            c = 0;
        }
        else
        {
            validChecks = validChecks + 2;
            c = 0;
        }


//      If statement to check if the user has left anything empty on the input form
        if (username.getText().toString().equals("") || surname.getText().toString().equals("") ||
                emailAddress.getText().toString().equals("") || idNumber.getText().toString().equals(""))
        {
            validChecks = validChecks + 3;
        }
        else
//          Else statement that will take all the string values and save them accordingly and then
//          navigate to the next page
        {

            editor.putString("username", nameStr);
            editor.putString("surname", surnameStr);
            editor.putString("emailAddress", emailAddressStr);
            editor.putString("idNumber", idNumberStr);
            editor.commit();
            validChecks = validChecks + 1;

        }

        if (validChecks == 3)
        {
            Toast.makeText(getApplicationContext(), "Please check that you've put in the correct amount of numbers for your ID!",Toast.LENGTH_SHORT).show();
            validChecks = 0;
        }
        else if (validChecks == 4)
        {
            Toast.makeText(getApplicationContext(), "Please make sure you've completed all the fields!",Toast.LENGTH_SHORT).show();
            validChecks = 0;
        }
        else if (validChecks == 5)
        {
            Toast.makeText(getApplicationContext(), "Please make sure you've completed all the fields and the correct amount of numbers for your ID!",Toast.LENGTH_SHORT).show();
            validChecks = 0;
        }
        else if (validChecks == 2)
        {
            startActivity(new Intent(MainActivity3.this, MainActivity4.class));
            finish();
            validChecks = 0;
        }


    }

}