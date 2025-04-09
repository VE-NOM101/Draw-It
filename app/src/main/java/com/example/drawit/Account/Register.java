package com.example.drawit.Account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drawit.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText et_email,et_pass,et_name,et_dob, et_country;
    String email,pass,name,dob, country;
    TextView already_register,register_text;
    Button register,update,delete;
    SharedPreferences spp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spp = getSharedPreferences(Key_SharedPreference.PREFS_KEY,MODE_PRIVATE);

        et_email = findViewById(R.id.email);
        et_pass = findViewById(R.id.password);
        et_name = findViewById(R.id.name);
        et_dob = findViewById(R.id.dob);
        et_country = findViewById(R.id.country);

        register = findViewById(R.id.register);
        already_register = findViewById(R.id.already_register);
        register_text = findViewById(R.id.register_text);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        String str = getIntent().getStringExtra("update");
        if(str != null && str.equals("2007101")){
            fetchData();

            register.setVisibility(View.GONE);
            already_register.setVisibility(View.GONE);
            register_text.setText("Update Or Delete");
        }else {
            update.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_email.getText().toString().equals("") && !et_pass.getText().toString().equals("") && !et_name.getText().toString().equals("") && !et_dob.getText().toString().equals("")  && !et_country.getText().toString().equals("")){

                    if(Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()){
                        email = et_email.getText().toString();
                        pass = et_pass.getText().toString();
                        name = et_name.getText().toString();
                        dob = et_dob.getText().toString();
                        country = et_country.getText().toString();

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                        Users users = new Users(email,pass,name,dob, country);
                        databaseReference.push().setValue(users);

                        Toast.makeText(Register.this, "Register Done ✅", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this,Login.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Register.this, "Incorrect Email Format", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Register.this, "Fill All Data Correctly !!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        already_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {

                            for(DataSnapshot snap : snapshot.getChildren()){
                                Users user = snap.getValue(Users.class);
                                if(user != null && user.email.equals(spp.getString(Key_SharedPreference.ACCOUNT_NAME,""))){

                                    if(!et_email.getText().toString().equals(spp.getString(Key_SharedPreference.ACCOUNT_NAME,""))){
                                        Toast.makeText(Register.this, "Email Can't be Edited !!", Toast.LENGTH_LONG).show();
                                    }else {
                                        snap.getRef().child("pass").setValue(et_pass.getText().toString());
                                        snap.getRef().child("name").setValue(et_name.getText().toString());
                                        snap.getRef().child("dob").setValue(et_dob.getText().toString());
                                        snap.getRef().child("security").setValue(et_country.getText().toString());
                                    }
                                }
                            }

                        } catch (NullPointerException e){

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        try {
                            for(DataSnapshot snap : snapshot.getChildren()){
                                Users user = snap.getValue(Users.class);

                                if(user != null && user.email.equals(spp.getString(Key_SharedPreference.ACCOUNT_NAME,""))){

                                    snap.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Register.this, "Account Deleted !!", Toast.LENGTH_SHORT).show();

                                            spp.edit().putBoolean(Key_SharedPreference.FIRST_TIME_APP_OPEN_SPLASH_SCREEN, true).apply();//First Time App Open
                                            Intent intent = new Intent(Register.this,Login.class);
                                            startActivity(intent);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Register.this, "Failed !!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }catch (NullPointerException e){

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void fetchData(){
        String account = spp.getString(Key_SharedPreference.ACCOUNT_NAME,"");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {

                    for(DataSnapshot snap : snapshot.getChildren()){
                        Users users = snap.getValue(Users.class);
                        if(users !=null && users.email.equals(account)){

                            et_email.setText(users.email);
                            et_pass.setText(users.pass);
                            et_name.setText(users.name);
                            et_dob.setText(users.dob);
                            et_country.setText(users.country);
                            return;
                        }
                    }

                } catch (NullPointerException e){

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}