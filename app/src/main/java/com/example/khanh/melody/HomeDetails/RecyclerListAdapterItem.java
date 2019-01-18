package com.example.khanh.melody.HomeDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.khanh.melody.R;

import java.util.ArrayList;

public class RecyclerListAdapterItem extends RecyclerView.Adapter<RecyclerListAdapterItem.ItemViewHolder> {
    private ArrayList<Items> ItemList;
    Context context;

    public RecyclerListAdapterItem(ArrayList<Items> ItemList, Context context) {
        this.ItemList = ItemList;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_items, parent, false);
        ItemViewHolder ith = new ItemViewHolder(ItemView);
        return ith;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {

        Items item = ItemList.get(position);
        holder.txtName.setText(item.getName());
        holder.imgItem.setBackgroundResource(item.getIcon());
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView imgItem;

        public ItemViewHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txt_name);
            imgItem = view.findViewById(R.id.img_item);
        }
    }
}

