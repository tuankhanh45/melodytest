package com.example.khanh.melody.More;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.khanh.melody.R;

public class SettingsActivity extends AppCompatActivity {
    ImageView back ;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        back=(ImageView)findViewById(R.id.back);
        back.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go back
                onBackPressed();
            }
        });

    }

}
