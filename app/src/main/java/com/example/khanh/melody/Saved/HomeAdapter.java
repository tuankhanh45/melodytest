package com.example.khanh.melody.Saved;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.example.khanh.melody.R;
import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {
    ArrayList<Home> homeArrayList;
    Context mContext;

    public HomeAdapter(ArrayList<Home> data, Context context) {
        this.homeArrayList = data;
        this.mContext = context;
    }

    private static class ViewHolder {
        TextView txtPrice;
        TextView txtDescription;
        ImageView imgHome;
    }

    @Override
    public int getCount() {
        return homeArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return homeArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.txt_price);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.txt_title);
            viewHolder.imgHome = (ImageView) convertView.findViewById(R.id.img_home);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Home item = (Home) getItem(position);
        viewHolder.txtPrice.setText(item.getPrice());
        viewHolder.txtDescription.setText(item.getDescription());
        if (!item.getUrlImg().equals("")) {

            Picasso.with(mContext).load(item.getUrlImg()).into(viewHolder.imgHome);
        } else {
            viewHolder.imgHome.setImageResource(R.drawable.v2);
        }
        return result;
    }
}
