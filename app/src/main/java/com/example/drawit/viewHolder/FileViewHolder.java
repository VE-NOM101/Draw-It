package com.example.drawit.viewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawit.Interface.ViewOnClick;
import com.example.drawit.R;
import com.example.drawit.common.Common;

public class FileViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    private ViewOnClick viewOnClick;

    public void setViewOnClick(ViewOnClick viewOnClick) {

        this.viewOnClick = viewOnClick;
    }

    public FileViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.image);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewOnClick.onClick(getAdapterPosition());
            }
        });
        itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,0,getAdapterPosition(), Common.DELETE);
            }
        });
    }
}
