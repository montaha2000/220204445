package com.afq.a220204445;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adap extends BaseAdapter {

    ArrayList<Conc>data;
    Context context;

    public Adap(ArrayList<Conc> data, Context context) {
        this.data = data;
        this.context = context;
    }




    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(data.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        TextView textViewname=view.findViewById(R.id.textView);
        TextView textViewad=view.findViewById(R.id.textView2);
        TextView textViewph=view.findViewById(R.id.textView3);


        Conc conc = data.get(position);

        textViewname.setText(conc.getName());
        textViewad.setText( conc.getAddress());
        textViewph.setText( conc.getPhone());


        return view;
    }
}

