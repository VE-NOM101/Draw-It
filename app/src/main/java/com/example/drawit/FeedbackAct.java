package com.example.drawit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FeedbackAct extends AppCompatActivity {


    TextInputEditText EditText1,EditText2;
    Button sendBtn,clearBtn;
    String myMail="choyanbaruakuetcse@gmail.com";
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        EditText1=findViewById(R.id.headline_text);
        EditText2=findViewById(R.id.details_text);
        sendBtn=findViewById(R.id.send_btn);
        clearBtn=findViewById(R.id.clear_btn);
        tb=findViewById(R.id.toolbar_feedback);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMethod();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearMethod();
            }
        });

    }

    private void clearMethod() {
        EditText1.setText("");
        EditText2.setText("");
    }

    private void sendMethod() {
        String headline = EditText1.getText().toString();
        String details = EditText2.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { myMail });
        intent.putExtra(Intent.EXTRA_SUBJECT, headline);
        intent.putExtra(Intent.EXTRA_TEXT, details);
        try {
            startActivity(Intent.createChooser(intent, "Send Mail"));
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(FeedbackAct.this, "There are no email clients", Toast.LENGTH_SHORT).show();
        }
    }

}