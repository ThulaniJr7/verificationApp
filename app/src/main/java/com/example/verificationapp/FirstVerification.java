package com.example.verificationapp;

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
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class FirstVerification extends AppCompatActivity {

    ImageView imageView;
    Button button;
    SharedPreferences Sprefs;
    EditText phoneNum;
    private String prefName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_verification);

        imageView = findViewById(R.id.photoId);
        button = findViewById(R.id.btnIdPhoto);
        phoneNum = findViewById(R.id.editTextNumber);

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

        Spinner staticSpinner = findViewById(R.id.loanAmount);

//     Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.loan_list_array,android.R.layout.simple_spinner_item);

//     Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//     Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

    }


    public void nextPageNow(View view) {


        String phoneNumberStr = phoneNum.getText().toString();

        Sprefs = getSharedPreferences("prefName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Sprefs.edit();

        if (phoneNum.getText().toString().equals(""))
        {

            Toast.makeText(getApplicationContext(), "Please make sure you've completed all the fields!",Toast.LENGTH_SHORT).show();

        }
        else
            {

                editor.putString("phoneNum", phoneNumberStr);
                editor.commit();

                startActivity(new Intent(FirstVerification.this, MainActivity3.class));

            }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

}