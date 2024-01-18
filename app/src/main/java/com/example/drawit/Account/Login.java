package com.example.drawit.Account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drawit.Launching;
import com.example.drawit.MainActivity;
import com.example.drawit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText et_email, et_pass;
    Button login;
    TextView register;

    boolean flag_wrong_pass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.email_login_page_et);
        et_pass = findViewById(R.id.pass_login_page_et);

        login = findViewById(R.id.login);
        register = findViewById(R.id.creat_account);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 code_for_login();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }


    public void code_for_login() {
        Log.d("For_login_error", "code_for_login: ");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Log.d("For_login_error", "code_for_login: Condition False");
                    if (!et_email.getText().toString().equals("") && !et_pass.getText().toString().equals("")) {
                        Log.d("For_login_error", "code_for_login: Condition True");
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Log.d("For_login_error", "code_for_login: SnapShot exists");
                            Users users = snap.getValue(Users.class);
                            if (users != null && et_email.getText().toString().equals(users.email) && et_pass.getText().toString().equals(users.pass)) {
                                flag_wrong_pass = true;
                                Toast.makeText(Login.this, "Login Successful ✅", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, Launching.class);
                                startActivity(intent);


                                SharedPreferences spp = getSharedPreferences(Key_SharedPreference.PREFS_KEY,MODE_PRIVATE);

                                Log.d("For_login_error", "code_for_login: SnapShot Matcheced 1");
                                spp.edit().putString(Key_SharedPreference.ACCOUNT_NAME,et_email.getText().toString()).apply();

                                spp.edit().putBoolean(Key_SharedPreference.FIRST_TIME_APP_OPEN_SPLASH_SCREEN, false).apply();//First Time App Open
                                Log.d("For_login_error", "code_for_login: SnapShot Matcheced 2");

                            }
                        }
                        if(flag_wrong_pass==false){
                            Toast.makeText(Login.this, "Wrong Email or Password !!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "Fill All Data Correctly !!", Toast.LENGTH_SHORT).show();
                    }

                } catch (NullPointerException e) {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}