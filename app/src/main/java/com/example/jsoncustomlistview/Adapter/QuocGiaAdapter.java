package com.example.jsoncustomlistview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jsoncustomlistview.Model.Quocgia;
import com.example.jsoncustomlistview.R;

import java.util.List;

public class QuocGiaAdapter extends BaseAdapter {
    int myLayout;
    Context context;

    List<Quocgia> list;

    @Override
    public int getCount() {
        return list.size();
    }

    public QuocGiaAdapter( Context context,int myLayout, List<Quocgia> list) {
        this.myLayout = myLayout;
        this.context = context;
        this.list = list;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(myLayout, null);
        TextView textView = view.findViewById(R.id.textView);
        ImageView imageView = view.findViewById(R.id.imageView);
        Quocgia quocgia = list.get(i);
        Glide.with(context).load(quocgia.hinhquocgia).into(imageView);

        textView.setText(quocgia.tenquocgia);

        return view;
    }
}
