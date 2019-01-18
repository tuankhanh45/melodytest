package com.example.khanh.melody.HomeDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.khanh.melody.R;

import java.util.ArrayList;

public class HomeDetailsActivity extends AppCompatActivity {
    int[] imv={R.drawable.app_icon,R.drawable.alert,R.drawable.bathroom,R.drawable.app_icon,R.drawable.alert,R.drawable.bathroom};
    String[] name={"hello1","h2","asjf","hello1","h2","asjf"};
    RecyclerView rcitems;
    Items items;
    ArrayList<Items> itemsList;
    RecyclerListAdapterItem recyclerListAdapterItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
        rcitems=(RecyclerView)findViewById(R.id.rc_item);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcitems.setLayoutManager(horizontalLayoutManager);
        itemsList = new ArrayList<>();

        for (int i=0;i<6;i++){
            items=new Items();
            items.setIcon(imv[i]);
            items.setName(name[i]);
            itemsList.add(items);
        }
        recyclerListAdapterItem =new RecyclerListAdapterItem(itemsList,HomeDetailsActivity.this);
        rcitems.setAdapter(recyclerListAdapterItem);
//        recyclerListAdapterItem.notifyDataSetChanged();

    }
}
