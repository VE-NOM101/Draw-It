package com.example.drawit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.drawit.pixabay.PostAdapter;
import com.example.drawit.pixabay.VolleySingleton;
import com.example.drawit.pixabay.item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pixabayshow extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<item> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixabayshow);
        toolbar=findViewById(R.id.pixabay_toolbar);
        recyclerView = findViewById(R.id.recyler_view_pixbay);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = VolleySingleton.getmInstance(Pixabayshow.this).getRequestQueue();
        mList = new ArrayList<>();
        fetchData();

    }
    private void fetchData() {

        Intent intent=getIntent();
        String selected=intent.getStringExtra("selected");
        String search=intent.getStringExtra("search");
        String url = "https://pixabay.com/api/?key=40451731-606bee60716585a3311682c0a&q="+search+"&image_type="+selected+"&pretty=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for(int i = 0 ; i<jsonArray.length() ; i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String imageUrl = jsonObject.getString("webformatURL");
                        int likes = jsonObject.getInt("likes");
                        String tags = jsonObject.getString("tags");
                        String pageUrl = jsonObject.getString("pageURL");

                        item post = new item(imageUrl , tags , likes,pageUrl);
                        mList.add(post);

                    }
                    PostAdapter adapter = new PostAdapter(Pixabayshow.this , mList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Pixabayshow.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.pixabay_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemid=item.getItemId();
        if(itemid==R.id.pixabay_home){
            Intent intent=new Intent(Pixabayshow.this,Launching.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}