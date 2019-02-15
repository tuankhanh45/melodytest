package com.example.khanh.melody.Saved;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khanh.melody.Ultis.Http;
import com.example.khanh.melody.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.khanh.melody.Ultis.Link.PORT;


public class HomesFragment extends Fragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {

    ListView lvHomeSave;
    ProgressDialog mProgressDialog;
    private View mLoadMoreView;
    int mPage = 1;
    boolean isLoadMore = false;
    SwipeRefreshLayout swipeRefreshLayout;

    HomeAdapter homeAdapter;
    ArrayList<Home> homeArrayList;

    public HomesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        mLoadMoreView = inflater.inflate(R.layout.loading_footer_item, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homes, container, false);
        lvHomeSave = (ListView) view.findViewById(R.id.lv_homes_saved);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sw_homes_saved);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#0000CC"));
        lvHomeSave.setOnScrollListener(this);
        showProgressDialog();
        if (lvHomeSave.getFooterViewsCount() <= 0) {
            lvHomeSave.addFooterView(mLoadMoreView);
            mLoadMoreView.setVisibility(View.GONE);
        }
        homeArrayList = new ArrayList<>();
        //creat arrlist home
        new GetList().execute(PORT + "/api/estate/user-history/20/" + mPage, "GET", "");

        return view;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        int threshold = 1;
        int count = lvHomeSave.getCount();
        if (scrollState == SCROLL_STATE_IDLE) {
            if (lvHomeSave.getLastVisiblePosition() >= count - threshold)
                loadMore();
        }
    }

    private void loadMore() {
            mPage++;
            isLoadMore = true;
            mLoadMoreView.setVisibility(View.VISIBLE);
            lvHomeSave.addFooterView(mLoadMoreView);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {

                    new GetList().execute(PORT + "/api/estate/user-history/20/" + mPage, "GET", "");

                }
            }, 1500);
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mPage = 1;
        homeArrayList.clear();
        new GetList().execute(PORT + "/api/estate/user-history/20/" + mPage, "GET", "");
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
                swipeRefreshLayout.setRefreshing(false);
                hideProgressDialog();
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
                        if (isLoadMore == false) {
                            homeAdapter = new HomeAdapter(homeArrayList, getActivity());
                            lvHomeSave.setAdapter(homeAdapter);
                        }
                        homeAdapter.notifyDataSetChanged();

                        swipeRefreshLayout.setRefreshing(false);
                        hideProgressDialog();
                        mLoadMoreView.setVisibility(View.GONE);
                        lvHomeSave.removeFooterView(mLoadMoreView);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("TAG", result); // this is expecting a response code to be sent from your server upon receiving the POST data
            }
        }


    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Loading....");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
