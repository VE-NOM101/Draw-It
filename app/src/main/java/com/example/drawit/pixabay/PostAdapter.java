package com.example.drawit.pixabay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.drawit.Pixabayshow;
import com.example.drawit.R;
import com.example.drawit.WebImage;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    Context context;
    List<item> postList;

    public PostAdapter(Context context , List<item> postList){
        this.context = context;
        this.postList = postList;
    }
    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.eachpost , parent , false);
        return new PostHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        item i = postList.get(position);
        holder.setImageView(i.getImageUrl());
        holder.setmTags(i.getTags());
        holder.setmLikes(i.getLikes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,WebImage.class);
                intent.putExtra("url",i.getPageUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView mLikes, mTags;
        View view;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

        }

        public void setImageView(String url){
            imageView = view.findViewById(R.id.imageview);
            Glide.with(context).load(url).into(imageView);
        }
        public void setmLikes(int likes){
            mLikes = view.findViewById(R.id.likes);
            mLikes.setText(likes + " Likes ❤️");
        }
        public void setmTags(String tag){
            mTags = view.findViewById(R.id.tags);
            mTags.setText(tag);
        }
    }
}