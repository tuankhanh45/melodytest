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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.khanh.melody.HomeDetails.HomeDetailsActivity;
import com.example.khanh.melody.R;
import com.example.khanh.melody.Saved.Home;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerListAdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Home> HomeList;
    Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public RecyclerListAdapterHome(List<Home> HomeList, Context context) {
        this.HomeList = HomeList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
            return new HomeViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return HomeList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HomeViewHolder) {
            populateItemRows((HomeViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return HomeList.size();
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar);
        }
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView txtPrice;
        TextView txtDescription;
        ImageView imgHome;
        LinearLayout lnhomes;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            txtPrice = view.findViewById(R.id.txt_price);
            txtDescription = view.findViewById(R.id.txt_title);
            imgHome = view.findViewById(R.id.img_home);
            lnhomes = (LinearLayout) view.findViewById(R.id.lnhomes);
        }
    }

    private void populateItemRows(HomeViewHolder holder, int position) {

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
    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }
}

