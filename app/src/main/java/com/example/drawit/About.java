package com.example.drawit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class About extends AppCompatActivity {

    Toolbar tb;

    BottomNavigationView btmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tb = findViewById(R.id.about_toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btmView = findViewById(R.id.bottom_navigation_about);

        btmView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home_about) {
                    // Replace 'your_menu_item_id' with the actual ID of your menu item
                    Toast.makeText(About.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(About.this,Launching.class);
                    startActivity(intent);
                    finish();
                    return true;// Indicate that the item selection has been handled
                }
                else if(id == R.id.feedback_about){
                    Intent intent=new Intent(About.this,FeedbackAct.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(About.this, "Feedback", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false; // If no item is selected or handled, return false
            }
        });


    }
}