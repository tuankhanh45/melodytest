package com.example.khanh.melody.More;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        setting=(ImageView)view.findViewById(R.id.img_setting);
        viewProfile=(TextView)view.findViewById(R.id.txt_view_profile);
        qualified=(LinearLayout)view.findViewById(R.id.ln_get_qualified);
        rates=(LinearLayout)view.findViewById(R.id.ln_rates);
        calculator=(LinearLayout)view.findViewById(R.id.ln_calculator);
        help=(LinearLayout)view.findViewById(R.id.ln_help);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_setting:

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
