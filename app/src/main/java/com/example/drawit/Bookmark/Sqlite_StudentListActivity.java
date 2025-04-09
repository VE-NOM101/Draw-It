package com.example.drawit.Bookmark;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drawit.Launching;
import com.example.drawit.R;
import com.example.drawit.WebView.All_Web_Pages_NoAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Sqlite_StudentListActivity extends AppCompatActivity {

    String string_bookmark_link;
    RelativeLayout relativeLayout;
    Sqlite_StudentDatabaseSource source;
    ListView listView;
    ArrayList<Sqlite_StudentModel> arrayList;
    FloatingActionButton floatingActionButton_home;

    Button refresh_bookmark,delete_all_bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_activity_student_list);

        home_button_caller();


        listView = findViewById(R.id.ListView);
        refresh_bookmark = findViewById(R.id.refreshing_ID_bookmarks_page);
        delete_all_bookmark = findViewById(R.id.delete_all_bookmarks);

        source = new Sqlite_StudentDatabaseSource(this);
        arrayList = source.getAllStudent();


        Sqlite_StudentAdapter studentAdapter = new Sqlite_StudentAdapter(Sqlite_StudentListActivity.this, source.getAllStudent());
        listView.setAdapter(studentAdapter);

        registerForContextMenu(listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Sqlite_StudentModel studentModel = arrayList.get(position);
                string_bookmark_link = studentModel.getAddress().toString();

                Toast.makeText(Sqlite_StudentListActivity.this, "Loading....", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Sqlite_StudentListActivity.this, All_Web_Pages_NoAds.class);
                if (string_bookmark_link != null) {
                    intent.putExtra("Web", string_bookmark_link);
                    startActivity(intent);
                }
            }
        });

        refresh_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Sqlite_StudentListActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Sqlite_StudentListActivity.this,Sqlite_StudentListActivity.class);
                startActivity(intent);
            }
        });

        delete_all_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            source.delete_All_Student();
                Intent intent = new Intent(Sqlite_StudentListActivity.this,Sqlite_StudentListActivity.class);
                startActivity(intent);
            }
        });

    }

    //this is for home button
    public void home_button_caller(){
        floatingActionButton_home =  findViewById(R.id.bottom_nav_home_bookmark);
        floatingActionButton_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_home = new Intent(Sqlite_StudentListActivity.this, Launching.class);
                startActivity(intent_home);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.manu_delete_bookmark,menu);
        menu.setHeaderTitle("Delete Bookmark");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId()== R.id.delete_bookmark_manu)
        {
            Boolean status = source.deleteStudent(arrayList.get(adapterContextMenuInfo.position));

            if(status)
            {
                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, "Failed To Delete", Toast.LENGTH_SHORT).show();
            }

        }
        return super.onContextItemSelected(item);
    }


}