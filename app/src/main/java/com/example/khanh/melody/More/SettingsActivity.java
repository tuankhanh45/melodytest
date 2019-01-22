package com.example.khanh.melody.More;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.khanh.melody.R;

public class SettingsActivity extends AppCompatActivity {

    private ImageView back;
    private CheckBox cbWifi;
    private CheckBox cbSounds;
    private Intent intent;

    private static final String PREF_NAME = "settings_pref";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        back = (ImageView) findViewById(R.id.back);
        cbSounds = (CheckBox) findViewById(R.id.cb_sounds);
        cbWifi = (CheckBox) findViewById(R.id.cb_wifi);

        editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        String a = prefs.getString("play_video_wifi_only", "-1");
        if (prefs.getString("play_video_wifi_only", "-1").equals("1")) cbWifi.setChecked(true);
        if (prefs.getString("sounds_in_app", "-1").equals("1")) cbSounds.setChecked(true);

        back.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go back
                onBackPressed();
            }
        });

        cbWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbWifi.isChecked()) {
                    editor.putString("play_video_wifi_only", "1");
                    editor.apply();
                } else {
                    editor.putString("play_video_wifi_only", "0");
                    editor.apply();
                }
            }
        });
        cbSounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbSounds.isChecked()) {
                    editor.putString("sounds_in_app", "1");
                    editor.apply();
                } else {
                    editor.putString("sounds_in_app", "0");
                    editor.apply();
                }
            }
        });
    }

}
