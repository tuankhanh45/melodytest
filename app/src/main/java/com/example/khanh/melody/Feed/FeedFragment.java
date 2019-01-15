package com.example.khanh.melody.Feed;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khanh.melody.R;
import com.example.khanh.melody.Saved.Home;
import com.example.khanh.melody.Saved.HomeAdapter;
import com.example.khanh.melody.Saved.HomesFragment;
import com.example.khanh.melody.Ultis.Http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.khanh.melody.Ultis.Link.PORT;


public class FeedFragment extends Fragment {

    private RecyclerView rchomes;
    RecyclerListAdapterHome homeAdapter;
    ArrayList<Home> homeArrayList;
    public FeedFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        rchomes=(RecyclerView) view.findViewById(R.id.rchomes);
        // set  orientation for rc
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rchomes.setLayoutManager(horizontalLayoutManager);
        homeArrayList = new ArrayList<>();
        //creat arrlist home
        new GetList().execute(PORT + "/api/estate/user-history/20/1", "GET", "");
        return view;
    }
    private class GetList extends AsyncTask<String, Void, String> {

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
                            home.setDescription(jsonDoc.optString("map_address"));
                            home.setPrice(jsonDoc.optString("price"));
                            homeArrayList.add(home);
                        }
                        homeAdapter=new RecyclerListAdapterHome(homeArrayList,getActivity());
                        rchomes.setAdapter(homeAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("TAG", result); // this is expecting a response code to be sent from your server upon receiving the POST data
            }
        }


    }
}
