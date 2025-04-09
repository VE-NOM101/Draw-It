package com.example.drawit.WebView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.drawit.R;

import java.util.List;

public class Firebase_Notifications_Adapter_List extends ArrayAdapter {



    private Context context;
    private boolean isDark=false;

    private List<Firebase_Notifications_List> feedbacks;

    public Firebase_Notifications_Adapter_List(@NonNull Context context, List<Firebase_Notifications_List>  arrayList) {
        super(context, R.layout.firebase_notifications_single_row, arrayList);

        this.context = context;
        this.feedbacks = arrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {    //ati only arta row ka return kora

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.firebase_notifications_single_row, parent,false);

        Firebase_Notifications_List notifications  = feedbacks.get(position);

        ImageView imageView = convertView.findViewById(R.id.image_View_ID_notification);

        TextView tv1 = convertView.findViewById(R.id.name_notification);
        TextView tv2 = convertView.findViewById(R.id.address_notification);
        TextView tv3 = convertView.findViewById(R.id.notification_date_time);
        LinearLayout linearLayout = convertView.findViewById(R.id.container_single_notification_item);

        tv1.setText(notifications.getUsername());
        tv2.setText(notifications.getLink());
        tv3.setText(notifications.getDate());


        return convertView ;
    }
}
