package com.example.drawit.WebView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drawit.Account.Key_SharedPreference;
import com.example.drawit.Bookmark.Sqlite_StudentListActivity;
import com.example.drawit.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Firebase_Notification_List_Class extends AppCompatActivity {

    boolean bool = false;
    ListView listView;
    DatabaseReference databaseReference;
    FloatingActionButton floatingActionButton_home;

    String string_notification_link;

    private ArrayList<Firebase_Notifications_List> feedbacks;
    private Firebase_Notifications_Adapter_List customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_notifications_activity_list);

        bool = true;

        bookmark_caller();

        //Here Id loading is not necessary because it comes from previous activity
        SharedPreferences sp = getSharedPreferences(Key_SharedPreference.PREFS_KEY, Context.MODE_PRIVATE);

        listView = findViewById(R.id.listView_firebase_notification_list);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Recommendation");

        feedbacks = new ArrayList<>();
        customAdapter = new Firebase_Notifications_Adapter_List(Firebase_Notification_List_Class.this, feedbacks);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Firebase_Notifications_List feedback_members = feedbacks.get(position);
                string_notification_link = feedback_members.getLink().toString();

                Toast.makeText(Firebase_Notification_List_Class.this, "Loading....", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Firebase_Notification_List_Class.this,All_Web_Pages_NoAds.class);
                if (string_notification_link != null) {
                    intent.putExtra("Web", string_notification_link);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    protected void onStart() {

        databaseReference.limitToLast(20).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                feedbacks.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    try {
                        Firebase_Notifications_List fd = dataSnapshot.getValue(Firebase_Notifications_List.class);
                        feedbacks.add(fd);
                    } catch (NullPointerException e){ //VVI
                        e.printStackTrace();
                        break;
                    }catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }

                }
                Collections.reverse(feedbacks);
                listView.setAdapter(customAdapter);


                //Update the notification count view
                if (snapshot.exists())
                {
                    try {

                        int counter = (int)snapshot.getChildrenCount();

                        if(bool == true)
                        {
                            bool = false;
                        }

                    } catch (NullPointerException e){  //VVI
                        e.printStackTrace();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Firebase_Notification_List_Class.this, "Sorry something going wrong", Toast.LENGTH_SHORT).show();
            }
        });
        super.onStart();
    }

    //this is for home button
    public void bookmark_caller(){
        floatingActionButton_home =  findViewById(R.id.bottom_nav_home_bookmark);
        floatingActionButton_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_home = new Intent(Firebase_Notification_List_Class.this, Sqlite_StudentListActivity.class);
                startActivity(intent_home);
            }
        });

    }
}