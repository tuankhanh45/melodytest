package com.example.khanh.melody;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khanh.melody.Alerts.AlertsFragment;
import com.example.khanh.melody.Feed.FeedFragment;
import com.example.khanh.melody.More.MoreFragment;
import com.example.khanh.melody.Saved.SavedFragment;
import com.example.khanh.melody.Saved.Saved_Fragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Fragment fragment = null;
    FragmentManager fragmentManager = null;
    FragmentTransaction fragmentTransaction = null;


    LinearLayout ln1, ln2, ln3, ln4, ln5;
    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView im1, im2, im3, im4, im5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ln1 = (LinearLayout) findViewById(R.id.l1);
        ln1.setOnClickListener(this);
        ln2 = (LinearLayout) findViewById(R.id.l2);
        ln2.setOnClickListener(this);
        ln3 = (LinearLayout) findViewById(R.id.l3);
        ln3.setOnClickListener(this);
        ln4 = (LinearLayout) findViewById(R.id.l4);
        ln4.setOnClickListener(this);
        ln5 = (LinearLayout) findViewById(R.id.l5);
        ln5.setOnClickListener(this);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setTextColor(Color.parseColor("#0033FF"));
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        im1 = (ImageView) findViewById(R.id.im1);
        im1.setColorFilter(ContextCompat.getColor(this, R.color.blue), PorterDuff.Mode.SRC_ATOP);
        im2 = (ImageView) findViewById(R.id.im2);
        im3 = (ImageView) findViewById(R.id.im3);
        im4 = (ImageView) findViewById(R.id.im4);
        im5 = (ImageView) findViewById(R.id.im5);

        fragment = new FeedFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.l1:
                tv1.setTextColor(Color.parseColor("#0033FF"));
                tv2.setTextColor(Color.GRAY);
                tv3.setTextColor(Color.GRAY);
                tv4.setTextColor(Color.GRAY);
                tv5.setTextColor(Color.GRAY);
                im1.setColorFilter(ContextCompat.getColor(this, R.color.blue), PorterDuff.Mode.SRC_ATOP);
                im2.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im3.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im4.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im5.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);

                fragment = new FeedFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
                break;
            case R.id.l2:
                tv2.setTextColor(Color.parseColor("#0033FF"));
                tv1.setTextColor(Color.GRAY);
                tv3.setTextColor(Color.GRAY);
                tv4.setTextColor(Color.GRAY);
                tv5.setTextColor(Color.GRAY);
                im2.setColorFilter(ContextCompat.getColor(this, R.color.blue), PorterDuff.Mode.SRC_ATOP);
                im1.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im3.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im4.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im5.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);

                fragment = new com.example.khanh.melody.SearchesFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
                break;
            case R.id.l3:
                tv3.setTextColor(Color.parseColor("#0033FF"));
                tv2.setTextColor(Color.GRAY);
                tv1.setTextColor(Color.GRAY);
                tv4.setTextColor(Color.GRAY);
                tv5.setTextColor(Color.GRAY);
                im3.setColorFilter(ContextCompat.getColor(this, R.color.blue), PorterDuff.Mode.SRC_ATOP);
                im2.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im1.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im4.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im5.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);

                fragment = new Saved_Fragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
                break;
            case R.id.l4:
                tv4.setTextColor(Color.parseColor("#0033FF"));
                tv2.setTextColor(Color.GRAY);
                tv3.setTextColor(Color.GRAY);
                tv1.setTextColor(Color.GRAY);
                tv5.setTextColor(Color.GRAY);
                im4.setColorFilter(ContextCompat.getColor(this, R.color.blue), PorterDuff.Mode.SRC_ATOP);
                im2.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im3.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im1.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im5.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);

                fragment = new AlertsFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
                break;
            case R.id.l5:
                tv5.setTextColor(Color.parseColor("#0033FF"));
                tv2.setTextColor(Color.GRAY);
                tv3.setTextColor(Color.GRAY);
                tv4.setTextColor(Color.GRAY);
                tv1.setTextColor(Color.GRAY);
                im5.setColorFilter(ContextCompat.getColor(this, R.color.blue), PorterDuff.Mode.SRC_ATOP);
                im2.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im3.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im4.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                im1.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                fragment = new MoreFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
    }
}

