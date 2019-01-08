package com.example.khanh.melody;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.khanh.melody.Link.PORT;


public class OpenHomesFragment extends Fragment {

    ListView lvHomeSave;
    HomeAdapter homeAdapter;
    Button btnSaveSqlite;
    Button btnDeleteSqlite;
    Button btnShow;
    ArrayList<Home> homeArrayList;
    HomeDatabaseHelper homeDatabaseHelper;

    public OpenHomesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_homes, container, false);
        lvHomeSave = (ListView) view.findViewById(R.id.lv_homes_saved);
        btnSaveSqlite = (Button) view.findViewById(R.id.btn_save_sqlite);
        btnDeleteSqlite = (Button) view.findViewById(R.id.btn_delete_sqlite);
        btnShow=(Button)view.findViewById(R.id.btn_show_sqlite);
        homeDatabaseHelper= new HomeDatabaseHelper(getActivity());
        homeArrayList = new ArrayList<>();

        btnSaveSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SaveHomeToSQLite().execute(PORT + "/api/estate/user-history/10/1", "GET", "");

            }
        });
        btnDeleteSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeDatabaseHelper.deleteAll();
                homeAdapter= new HomeAdapter((ArrayList<Home>) homeDatabaseHelper.getAllHomes(),getActivity());
                homeAdapter.notifyDataSetChanged();
                lvHomeSave.setAdapter(homeAdapter);
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeAdapter= new HomeAdapter((ArrayList<Home>) homeDatabaseHelper.getAllHomes(),getActivity());
                lvHomeSave.setAdapter(homeAdapter);
            }
        });
        return view;
    }

    private class SaveHomeToSQLite extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String mJsonString = "";
            try {
                Http mJsonParser = new Http(getActivity());
                mJsonString = mJsonParser.makeHttpRequestJson(params[0], params[1], params[2]);

                Log.d("jsonStringLog: ", mJsonString);

                return mJsonString;

            } catch (NullPointerException e) {
                e.printStackTrace();
                return "";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("-1")) {
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "Vui lòng đăng nhập !", Toast.LENGTH_LONG).show();
                }
            } else {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    if (jsonObject.optString("code").equals("0")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("docs");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonDoc = jsonArray.getJSONObject(i);
                            // set data
                            Home home = new Home();
                            JSONArray jsonArrayImv = jsonDoc.getJSONObject("media").getJSONArray("image_uri");
                            if (jsonArrayImv.length() > 0) {
                                home.setUrlImg(jsonArrayImv.optString(0));
                            } else {
                                home.setUrlImg("");
                            }
                            home.setHomeId(jsonDoc.optString("_id"));
                            home.setDescription(jsonDoc.optString("map_address"));
                            home.setPrice(jsonDoc.optString("price"));
                            homeDatabaseHelper.addHome(home);
                        }
                        homeAdapter= new HomeAdapter((ArrayList<Home>) homeDatabaseHelper.getAllHomes(),getActivity());
                        lvHomeSave.setAdapter(homeAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("TAG", result); // this is expecting a response code to be sent from your server upon receiving the POST data
            }
        }


    }
}

