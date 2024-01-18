
package com.example.drawit.viewHolder;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawit.Interface.ViewOnClick;
import com.example.drawit.R;

public class ToolsViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView name;
    private ViewOnClick viewOnClick;

    public void setViewOnClick(ViewOnClick viewOnClick) {
        this.viewOnClick = viewOnClick;
    }

    public ToolsViewHolder(@NonNull View itemView) {
        super(itemView);
        icon=itemView.findViewById(R.id.tools_icon);
        name=itemView.findViewById(R.id.tools_name);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewOnClick.onClick(getAdapterPosition());
            }
        });
    }
}
