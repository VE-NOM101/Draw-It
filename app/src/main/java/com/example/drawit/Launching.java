package com.example.drawit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.drawit.Account.Key_SharedPreference;
import com.example.drawit.Account.Login;
import com.example.drawit.Account.Register;
import com.example.drawit.Bookmark.Sqlite_StudentListActivity;
import com.example.drawit.WebView.Firebase_Notification_List_Class;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class Launching extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    BottomNavigationView btmView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton fbtn;
    ConstraintLayout constraintLayout;

    CardView card_paint,card_edit,card_web,card_update,card_pixabay,card_sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);


        card_paint = findViewById(R.id.card_paint);
        card_edit = findViewById(R.id.card_edit);
        card_web = findViewById(R.id.card_web);
        card_update = findViewById(R.id.card_update);
        card_pixabay = findViewById(R.id.card_pixabay);
        card_sms = findViewById(R.id.card_share);

        toolbar=findViewById(R.id.toolbar_home);
        btmView=findViewById(R.id.bottom_navigation);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navigationView);
        fbtn=findViewById(R.id.fbtn);

        constraintLayout=findViewById(R.id.constraint_layout);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //navigation item listener
        navigationView.setNavigationItemSelectedListener(this);

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });


        card_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web_method();
            }
        });

        card_paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Launching.this, MainActivity.class);
                startActivity(intent);
            }
        });

        card_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_method();
            }
        });

        card_pixabay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Launching.this,Pixabayhome.class);
                startActivity(intent);
            }
        });

        card_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Launching.this,EditHome.class);
                startActivity(intent);
            }
        });

        card_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Launching.this,SMSAct.class);
                startActivity(intent);
            }
        });

        btmView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.art1){
                    Drawable backgroundImage = getResources().getDrawable(R.drawable.launching_background1);
                    constraintLayout.setBackground(backgroundImage);
                    return true;
                }
                else if(id==R.id.art2){
                    Drawable backgroundImage = getResources().getDrawable(R.drawable.launching_background2);
                    constraintLayout.setBackground(backgroundImage);
                    return true;
                }
                return false;
            }
        });

    }


    private void web_method() {
        Intent intent = new Intent(Launching.this, Firebase_Notification_List_Class.class);
        startActivity(intent);
    }

    private void update_method() {
        Intent intent = new Intent(Launching.this, Register.class);
        intent.putExtra("update","2007101");
        startActivity(intent);
    }

    private void logout_method() {

        SharedPreferences spp = getSharedPreferences(Key_SharedPreference.PREFS_KEY,MODE_PRIVATE);
        spp.edit().putBoolean(Key_SharedPreference.FIRST_TIME_APP_OPEN_SPLASH_SCREEN, true).apply();//First Time App Open

        Intent intent = new Intent(Launching.this, Login.class);
        startActivity(intent);
    }

    private void share_method() {

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String bodyText="http://play.google.com/store/apps/details?id="+getPackageName();
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT,bodyText);
        startActivity(Intent.createChooser(intent,"share this app"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemid=item.getItemId();
        if(itemid==R.id.share){
            share_method();
        }else if(itemid==R.id.webverse) {
            web_method();
        }else if(itemid==R.id.update){
            update_method();
        }else if(itemid==R.id.feedback){
            Intent intent=new Intent(Launching.this,FeedbackAct.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout ImageLayout = dialog.findViewById(R.id.layoutImage);
        LinearLayout ShowLayout = dialog.findViewById(R.id.layoutShow);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        ImageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent= new Intent(Launching.this,UploadFirebase.class);
                startActivity(intent);
            }
        });

        ShowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent= new Intent(Launching.this,RetriveFirebase.class);
                startActivity(intent);
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private void back_alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Confirmation")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // Finish the activity if "Yes" is clicked
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog if "No" is clicked
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemid=item.getItemId();
        if(itemid==R.id.about_drawer){
            Intent intent_about= new Intent(Launching.this, About.class);
            startActivity(intent_about);
        }else if(itemid==R.id.bookmark_drawer){
            Intent intent_bookmark= new Intent(Launching.this, Sqlite_StudentListActivity.class);
            startActivity(intent_bookmark);
        }else if(itemid==R.id.profile_drawer){
            update_method();
        }else if(itemid==R.id.logout_drawer){
            logout_method();
        }else if(itemid==R.id.upload_cloud){
            Intent intent = new Intent(Launching.this, UploadFirebase.class);
            startActivity(intent);
        }else if(itemid==R.id.cloud_storage){
            Intent intent = new Intent(Launching.this, RetriveFirebase.class);
            startActivity(intent);
        }
        return true;
    }
}