package com.example.verificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//  THIS Activity will be the second page that allows the user to fill in their details for the verification process.
//  The user should fill in their bank, account number, branch code and submit.
//  The layout functionality can be found in the "activity_main4.xml" file for editing.
//  The string values can be found in the "strings.xml" file for all the configurations
//  This java class is used for any functional calls and methods that need to be executed to perform
//  a specific action on the runtime environment.

public class MainActivity4 extends AppCompatActivity {

//  Creating the variables needed for the Shared Preferences section
    SharedPreferences Sprefs;
    EditText bankName;
    EditText bankAccNum;
    EditText bankBranchCode;
    String value;
    Button btnNext;
    private String prefName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

//      Initializing the variables and using the information that was stored in the id and saving them
        bankName = findViewById(R.id.bankInputForm);
        bankAccNum = findViewById(R.id.bankAccNumInputForm);
        bankBranchCode = findViewById(R.id.bankBranchCodeInputForm);
        btnNext = findViewById(R.id.bankButton);

//        Spinner staticSpinner = (Spinner) findViewById(R.id.bank_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
//        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.bank_names_array,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
//        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
//        staticSpinner.setAdapter(staticAdapter);

    }

    public void formDisplay (View view) {

        String bankNameStr = bankName.getText().toString();
        String bankAccNumStr = bankAccNum.getText().toString();
        String bankBranchCodeStr = bankBranchCode.getText().toString();

        Sprefs = getSharedPreferences("prefName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Sprefs.edit();

//      If statement to check if the user has left anything empty on the input form
        if (bankName.getText().toString().equals("") || bankAccNum.getText().toString().equals("") ||
                bankBranchCode.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please make sure you've completed all the fields!",Toast.LENGTH_SHORT).show();
        }
        else
//          Else statement that will take all the string values and save them accordingly and then
//          navigate to the next page
        {

            editor.putString("bankName", bankNameStr);
            editor.putString("bankAccNum", bankAccNumStr);
            editor.putString("bankBranchCode", bankBranchCodeStr);
            editor.commit();

            startActivity(new Intent(MainActivity4.this, MainActivity5.class));
        }

    }
}