package com.example.khanh.melody.Ultis;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 12/19/2017.
 */

public class Http {
    private Context mContext;
    private SessionManager mSessionManager;
    ProgressDialog mProgressDialog;
    public Http(Context context) {
        mSessionManager = new SessionManager(context);
        mContext = context;
    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("Loading....");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    public String makeHttpRequestJson(String url,String type, String params){

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            System.setProperty("http.keepAlive", "false");
            httpURLConnection.setRequestProperty("x-access-token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjlmMmM1MGQxYjk3MDJhMDFkYzNkNWQiLCJlbWFpbCI6Ikprbm9ubzE5OTVAZ21haWwuY29tICIsImlhdCI6MTU1MDE5NTk2OSwiZXhwIjoxNTUyNzg3OTY5fQ.rj9Smx1elNyR7WvLLAaKI13QvjAXAI5bLqGV9S6UjPc");
            Log.d("token",mSessionManager.getString("token",""));
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            if(type.equals("POST")) {
                httpURLConnection.setRequestMethod(type);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(params);
                writer.flush();
                writer.close();
                os.close();
            } else if(type.equals("GET")){
                httpURLConnection.setRequestMethod(type);
            }
            httpURLConnection.connect();
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            is.close();
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return "-1";
    }
}
