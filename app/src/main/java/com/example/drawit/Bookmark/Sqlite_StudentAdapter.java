package com.example.drawit.Bookmark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.drawit.R;

import java.util.ArrayList;

public class Sqlite_StudentAdapter extends ArrayAdapter<Sqlite_StudentModel> {



    private Context context;
    private boolean isDark=false;
    RelativeLayout relativeLayout;

    private ArrayList<Sqlite_StudentModel> arrayList;


    public Sqlite_StudentAdapter(@NonNull Context context, ArrayList<Sqlite_StudentModel>  arrayList) {
        super(context, R.layout.sqlite_single_student_row, arrayList);

        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {    //ati only arta row ka return kora


        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.sqlite_single_student_row, parent,false);



        TextView nameTV = v.findViewById(R.id.name);
        TextView addressTV = v.findViewById(R.id.address);

        nameTV.setText(arrayList.get(position).getName());
        addressTV.setText(arrayList.get(position).getAddress());


        TextView tv1 = v.findViewById(R.id.name);
        TextView tv2 = v.findViewById(R.id.address);
        ImageView imageView = v.findViewById(R.id.image_View_ID);



        return v ;


    }
}
