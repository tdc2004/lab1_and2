package com.chinhtd.lab1_and2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoAdapter extends BaseAdapter {
    Context context;
    ArrayList<ToDo> list;

    public static int Key_Position;

    public ToDoAdapter(Context context, ArrayList<ToDo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.itemlv,parent,false);
        TextView textView_tile = convertView.findViewById(R.id.tv_title);
        TextView textView_content = convertView.findViewById(R.id.tv_content);
        TextView textView_date = convertView.findViewById(R.id.tv_date);

        textView_tile.setText(list.get(position).getTiltel());
        textView_content.setText(list.get(position).getContent());
        textView_date.setText(list.get(position).getDate());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)context;
                Key_Position = position;
                mainActivity.text_title.setText(list.get(position).getTiltel());
                mainActivity.text_content.setText(list.get(position).getContent());
                mainActivity.text_date.setText(list.get(position).getDate());
                mainActivity.text_type.setText("Dễ");
            }
        });
        return convertView;
    }
}
