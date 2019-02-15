package com.example.khanh.melody.Feed;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
import static com.example.khanh.melody.Ultis.Link.PORT;


public class FeedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rchomes;
    private RecyclerListAdapterHome homeAdapter;
    private ArrayList<Home> homeArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;
    int mPage = 1;

    ProgressDialog mProgressDialog;
    boolean isLoadMore = false;
    boolean isLoading = false;
    private LinearLayoutManager mLayoutManager;

    public FeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        rchomes = (RecyclerView) view.findViewById(R.id.rchomes);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sw);

        // set  orientation for rc
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rchomes.setLayoutManager(horizontalLayoutManager);
        homeArrayList = new ArrayList<>();
        showProgressDialog();
        //creat arrlist home
        new GetList().execute(PORT + "/api/estate/user-history/20/" + mPage, "GET", "");

        // refresh view
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#0000CC"));

        // add load more rc
        initScrollListener();
        return view;
    }

    // load more
    private void initScrollListener() {
        rchomes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == homeArrayList.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        mPage++;
        isLoadMore = true;
        homeArrayList.add(null);
        homeAdapter.notifyItemInserted(homeArrayList.size() - 1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                homeArrayList.remove(homeArrayList.size() - 1);
                int scrollPosition = homeArrayList.size();
                homeAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;
                new GetList().execute(PORT + "/api/estate/user-history/20/" + mPage, "GET", "");

            }
        }, 1500);
    }

    // refresh view
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        homeArrayList.clear();
        mPage = 1;
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

                        // new adapter if refreshing
                        if (isLoadMore == false) {
                            homeAdapter = new RecyclerListAdapterHome(homeArrayList, getActivity());
                            rchomes.setAdapter(homeAdapter);
                        }

                        homeAdapter.notifyDataSetChanged();
                        rchomes.setAdapter(homeAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                        hideProgressDialog();
                        isLoading = false;
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
