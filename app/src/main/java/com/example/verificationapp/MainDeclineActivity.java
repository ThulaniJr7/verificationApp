package com.example.verificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainDeclineActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_decline);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_logos_02);

    }

    public void newApplicationForm(View view)
    {

//      Changing to a new activity
        startActivity(new Intent(MainDeclineActivity.this, MainActivity2.class));
        finish();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.navigation_home:
                Intent intent = new Intent(MainDeclineActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return false;
    }
}