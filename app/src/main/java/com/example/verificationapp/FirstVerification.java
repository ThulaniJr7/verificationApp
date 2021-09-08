package com.example.verificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FirstVerification extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{

    BottomNavigationView bottomNavigationView;

    ImageView imageView;
    Button button;
    SharedPreferences Sprefs;
    EditText phoneNum;
    Spinner loanSpinner;
    private String prefName;
    int b;
    int validCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_verification);

        imageView = findViewById(R.id.photoId);
        button = findViewById(R.id.btnIdPhoto);
        phoneNum = findViewById(R.id.editTextNumber);
        b = 0;
        validCheck = 0;

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_logos_02);

//      Request for camera runtime permission
        if(ContextCompat.checkSelfPermission(FirstVerification.this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(FirstVerification.this, new String[] {
                Manifest.permission.CAMERA
            }, 100);
        }

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 100);

            }
        });

        Spinner loanSpinner = (Spinner) findViewById(R.id.loanAmount);

//     Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.loan_list_array,android.R.layout.simple_spinner_item);

//     Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//     Apply the adapter to the spinner
        loanSpinner.setAdapter(staticAdapter);

        loanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Sprefs = getSharedPreferences("prefName", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = Sprefs.edit();

                editor.putString("loanValue", loanSpinner.getSelectedItem().toString());

                String loanValue = loanSpinner.getSelectedItem().toString();

                for (int i = 0; i < 10; i++)
                    if (loanValue.equals(loanSpinner.getItemAtPosition(i).toString()))
                    {
                        loanSpinner.setSelection(i);
                        break;
                    }

                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }


    public void nextPageNow(View view) {


        String phoneNumberStr = phoneNum.getText().toString();

        Sprefs = getSharedPreferences("prefName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Sprefs.edit();

        int cellNum = phoneNumberStr.length();

        for (int i = 0; i != phoneNumberStr.length(); i++)
        {
            b++;
        }

        if (b == 10)
        {
            validCheck = validCheck + 1;
            b = 0;
        }
        else
        {
            validCheck = validCheck + 2;
            b = 0;
        }

        if (validCheck == 2)
        {

            Toast.makeText(getApplicationContext(), "Please make sure you've entered the correct amount of numbers for your Cellphone number!",Toast.LENGTH_SHORT).show();
            validCheck = 0;

        }
        else if (validCheck == 1)
        {

            editor.putString("phoneNum", phoneNumberStr);
            editor.commit();

            startActivity(new Intent(FirstVerification.this, MainActivity3.class));
            finish();
            validCheck = 0;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.navigation_home:
                Intent intent = new Intent(FirstVerification.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return false;
    }

}