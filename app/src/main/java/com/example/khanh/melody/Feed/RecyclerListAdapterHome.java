package com.example.khanh.melody.Feed;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khanh.melody.HomeDetails.HomeDetailsActivity;
import com.example.khanh.melody.R;
import com.example.khanh.melody.Saved.Home;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerListAdapterHome extends RecyclerView.Adapter<RecyclerListAdapterHome.HomeViewHolder> {
    private List<Home> HomeList;
    Context context;

    public RecyclerListAdapterHome(List<Home> HomeList, Context context) {
        this.HomeList = HomeList;
        this.context = context;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View HomeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        HomeViewHolder hvh = new HomeViewHolder(HomeView);
        return hvh;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, final int position) {

        Home item = HomeList.get(position);
        holder.txtPrice.setText(item.getPrice());
        holder.txtDescription.setText(item.getDescription());

        if (!item.getUrlImg().equals("")) {
            Picasso.with(context).load(item.getUrlImg()).into(holder.imgHome);
        } else {
            holder.imgHome.setImageResource(R.drawable.v2);
        }
        holder.lnhomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return HomeList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView txtPrice;
        TextView txtDescription;
        ImageView imgHome;
        LinearLayout lnhomes;

        public HomeViewHolder(View view) {
            super(view);
            txtPrice = view.findViewById(R.id.txt_price);
            txtDescription = view.findViewById(R.id.txt_title);
            imgHome=view.findViewById(R.id.img_home);
            lnhomes=(LinearLayout) view.findViewById(R.id.lnhomes);
        }
    }
}

