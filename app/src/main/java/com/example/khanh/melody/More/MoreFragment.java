package com.example.khanh.melody.More;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khanh.melody.R;

public class MoreFragment extends Fragment implements View.OnClickListener {

    ImageView setting;
    TextView viewProfile;
    LinearLayout qualified;
    LinearLayout rates;
    LinearLayout calculator;
    LinearLayout help;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        setting=(ImageView)view.findViewById(R.id.img_setting);
        setting.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), PorterDuff.Mode.SRC_ATOP);
        viewProfile=(TextView)view.findViewById(R.id.txt_view_profile);
        qualified=(LinearLayout)view.findViewById(R.id.ln_get_qualified);
        rates=(LinearLayout)view.findViewById(R.id.ln_rates);
        calculator=(LinearLayout)view.findViewById(R.id.ln_calculator);
        help=(LinearLayout)view.findViewById(R.id.ln_help);
        setting.setOnClickListener(this);
        viewProfile.setOnClickListener(this);
        qualified.setOnClickListener(this);
        rates.setOnClickListener(this);
        calculator.setOnClickListener(this);
        help.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_setting:
                Log.d("setting ", "go go");
                intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_view_profile:

                break;
            case R.id.ln_get_qualified:

                break;
            case R.id.ln_rates:

                break;
            case R.id.ln_calculator:

                break;
            case R.id.ln_help:

                break;
        }
    }
}
