package com.example.drawit.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawit.Interface.ViewOnClick;
import com.example.drawit.R;
import com.example.drawit.ViewFileAct;
import com.example.drawit.viewHolder.FileViewHolder;

import java.io.File;
import java.util.List;

public class FilesAdapters extends RecyclerView.Adapter<FileViewHolder> {

    private Context mContext;
    private List<File> fileList;

    public FilesAdapters(Context mContext, List<File> fileList) {
        this.mContext = mContext;
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_file,parent,false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        holder.imageView.setImageURI(Uri.fromFile(fileList.get(position)));
        holder.setViewOnClick(new ViewOnClick() {
            @Override
            public void onClick(int pos) {
                Intent intent=new Intent(mContext, ViewFileAct.class);
                intent.setData(Uri.fromFile(fileList.get(pos)));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return fileList.size();
    }
}
