package com.example.kiit.myapplication;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Movies> movielist;

    public MovieAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Movies> movielist) {
        super(context, resource, movielist);
        this.context = context;
        this.movielist = movielist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movielist, parent, false);
        } else {
            view = convertView;

        }
        TextView tvname = (TextView) view.findViewById(R.id.textview);
        String name = movielist.get(position).getMname();
        tvname.setText(name);

        TextView tvrating = (TextView) view.findViewById(R.id.textView4);
        String rating = movielist.get(position).getRating();
        tvrating.setText(rating);

        TextView tvdate = (TextView) view.findViewById(R.id.textView2);
        String date = movielist.get(position).getDate();
        tvdate.setText(date);

        ImageView image=(ImageView)view.findViewById(R.id.imageView);
        String img=movielist.get(position).getImgurl();
        Glide.with(context).load(img).thumbnail(0.2f).into(image);
        return view;
    }
}
