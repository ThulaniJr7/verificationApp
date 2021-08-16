package com.example.verificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//  THIS Activity will be the page that shows the user the information that they about to submit.
//  The user will be displayed with a table and the final submission button.
//  The layout functionality can be found in the "activity_main5.xml" file for editing.
//  The string values can be found in the "strings.xml" file for all the configurations
//  This java class is used for any functional calls and methods that need to be executed to perform
//  a specific action on the runtime environment.

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

//      Creating the variables to save the content in
        TextView finDisplayName, finDisplaySurname, finDisplayEmail, finDisplayPhoneNumber, finDisplayIdNum, finDisplayBankName, finDisplayBankAccNum, finDisplayBankBranchCode, finDisplayLoanAmount;

//      Saving the variables with the information extracted from the user input in relation to the ID name
        finDisplayName = findViewById(R.id.displayName);
        finDisplaySurname = findViewById(R.id.displaySurname);
        finDisplayEmail = findViewById(R.id.displayEmailAddress);
        finDisplayPhoneNumber = findViewById(R.id.displayPhoneNumber);
        finDisplayIdNum = findViewById(R.id.displayIdNum);
        finDisplayBankName = findViewById(R.id.displayBankName);
        finDisplayBankAccNum = findViewById(R.id.displayBankAccNum);
        finDisplayBankBranchCode = findViewById(R.id.displayBranchCode);
        finDisplayLoanAmount = findViewById(R.id.displayLoanAmount);

//      This is extracting the information from the shared preference variable
        SharedPreferences Sprefs = getApplicationContext().getSharedPreferences("prefName", Context.MODE_PRIVATE);

//      Getting the string value associated to the relevant key name and saving it into the variable
        String username = Sprefs.getString("username", "");
        String surname = Sprefs.getString("surname", "");
        String emailAddress = Sprefs.getString("emailAddress", "");
        String phoneNumber = Sprefs.getString("phoneNum", "");
        String idNumber = Sprefs.getString("idNumber", "");
        String bankName = Sprefs.getString("bankNameValue", "");
        String bankAccNum = Sprefs.getString("bankAccNum", "");
        String bankBranchCode = Sprefs.getString("bankBranchCode", "");
        String loanAmount = Sprefs.getString("loanValue", "");

//      Setting the text to display on the front end based on the input information
        finDisplayName.setText(username);
        finDisplaySurname.setText(surname);
        finDisplayEmail.setText(emailAddress);
        finDisplayPhoneNumber.setText(phoneNumber);
        finDisplayIdNum.setText(idNumber);
        finDisplayBankName.setText(bankName);
        finDisplayBankAccNum.setText(bankAccNum);
        finDisplayBankBranchCode.setText(bankBranchCode);
        finDisplayLoanAmount.setText(loanAmount);

    }

//  Function to initiate
    public void last_verification (View view)
    {

//      Need to create a function that will initiate the backend methods that will take in the users ID number,
//      run it through the XDS system, retrieve that credit score number, convert it to a readable integer from either
//      XML/JSON when it comes in from XDS environment

        startActivity(new Intent(MainActivity5.this, MainActivity6.class));
        finish();

    }

}