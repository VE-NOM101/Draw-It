package com.example.drawit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Pixabayhome extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    Spinner spinner;
    ArrayList<String> spinnerItem;
    ImageView go_btn;
    String selected_item="all";
    TextInputEditText textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixabayhome);
        toolbar=findViewById(R.id.pixabay_toolbar);
        spinner=findViewById(R.id.pixaspinner);
        go_btn=findViewById(R.id.go_btn);
        textInputEditText=findViewById(R.id.search_box);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerItem=new ArrayList<>();
        spinnerItem.add("All");
        spinnerItem.add("Illustration");
        spinnerItem.add("Photo");
        spinnerItem.add("Vector");

        ArrayAdapter<String> adapter=new ArrayAdapter<>(Pixabayhome.this,R.layout.item_selected_layout,spinnerItem);
        adapter.setDropDownViewResource(R.layout.item_dropdown_layout);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(Pixabayhome.this);
        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pixabayhome.this, Pixabayshow.class);
                String search_item=textInputEditText.getText().toString();
                intent.putExtra("selected",selected_item);
                intent.putExtra("search",search_item);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==0) selected_item="all";
        else if(i==1) selected_item="illustration";
        else if(i==2) selected_item="photo";
        else if(i==3) selected_item="vector";
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}