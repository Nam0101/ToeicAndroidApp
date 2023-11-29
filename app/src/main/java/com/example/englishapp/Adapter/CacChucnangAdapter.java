package com.example.englishapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.englishapp.Model.CacChnang;
import com.example.englishapp.R;

import java.util.List;

public class CacChucnangAdapter extends BaseAdapter {

    List<CacChnang> array;
    Context context;

    public CacChucnangAdapter( Context context,List<CacChnang> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder {
        TextView texttencn;
        ImageView imghinhanh;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_chucnang,null);
            viewHolder.texttencn = view.findViewById(R.id.item_tenchucnang);
            viewHolder.imghinhanh = view.findViewById(R.id.item_imgchucnang);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.texttencn.setText(array.get(i).getTenchucnang());
        Glide.with(context).load(array.get(i).getHinhanh()).into(viewHolder.imghinhanh);
        return view;
    }
}
