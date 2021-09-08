package com.example.verificationapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity5 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{

    Button btn_getAllTheData;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        btn_getAllTheData = findViewById(R.id.btnVerificationButton);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_logos_02 );

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

//      This is extracting the information from the shared preference and creating an editor to allow for cache clearing
        SharedPreferences Sprefs = getApplicationContext().getSharedPreferences("prefName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Sprefs.edit();

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

        btn_getAllTheData.setOnClickListener(new View.OnClickListener()
        {
            JSONArray validInfo;
            JSONObject nameInfo;

            @Override
            public void onClick(View v)
            {
//              Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity5.this);

//              JSON Object - "data". This is the test API we're running to use for data retrieval
                String url ="https://www.tgpdc.com/api/post/read.php";

//              Declare the variables needed and converting them into String and long
                String inputIdNumber = idNumber;
                Long finalIdNumber = Long.parseLong(inputIdNumber);

//              This request is for a JSON Object that has a JSON Array within it and we're trying to pull the data from it
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {

                        try
                        {
//                          Code below retrieves the JSON Object "data" and puts it into a JSON Array
                            validInfo = response.getJSONArray("data");

                            for (int i = 0; i < validInfo.length(); i++)
                            {

                                String verifyCredScore;
                                String verifyIdNum;
                                Long userIdNumber;
                                Long userCreditScore;
                                Long lastCredScore;

//                              Getting the JSON Object based on the index position of the JSON Array "validInfo" and storing it in "nameInfo"
                                nameInfo = validInfo.getJSONObject(i);

//                              Storing the data received based on the headers "creditScore" and "name" in their own variables as Strings
                                verifyCredScore = nameInfo.getString("creditScore");
                                verifyIdNum = nameInfo.getString("idNumber");

//                              Converting the string to a long
                                userCreditScore = Long.parseLong(verifyCredScore);
                                userIdNumber = Long.parseLong(verifyIdNum);

//                              If statement to check if the two strings are equal
                                if (finalIdNumber.equals(userIdNumber))
                                {
                                    lastCredScore = userCreditScore;
                                    long numOne = 599;
                                    long numTwo = 600;
                                    long numThree = 700;

                                    if(lastCredScore <= numOne)
                                    {
                                        startActivity(new Intent(MainActivity5.this, MainDeclineActivity.class));
                                        editor.clear().commit();
                                        finish();
                                    }

                                    if(lastCredScore >= numTwo && lastCredScore < numThree)
                                    {
                                        startActivity(new Intent(MainActivity5.this, MainActivityPending.class));
                                        editor.clear().commit();
                                        finish();
                                    }

                                    if(lastCredScore >= numThree)
                                    {
                                        startActivity(new Intent(MainActivity5.this, MainActivityProvApproved.class));
                                        editor.clear().commit();
                                        finish();
                                    }

                                }

                            }

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity5.this,"An error occurred! Please try again!", Toast.LENGTH_SHORT).show();

                    }
                });

                queue.add(request);

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.navigation_home:
                Intent intent = new Intent(MainActivity5.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return false;
    }

}