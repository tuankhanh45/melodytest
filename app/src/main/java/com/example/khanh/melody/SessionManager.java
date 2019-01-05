package com.example.khanh.melody;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SessionManager {
	// Sharedpref file name
	private static final String PREF_NAME = "login_pref";


	// Shared Preferences
	private SharedPreferences mPref;
	private Editor mEditor;
	// Context
	private Context mContext;

	// Constructor
	@SuppressLint("CommitPrefEdits")
	public SessionManager(Context context) {
		try {
			this.mContext = context;
			mPref = mContext.getSharedPreferences(PREF_NAME,
					Context.MODE_PRIVATE);
			mEditor = mPref.edit();
		} catch (NullPointerException e) {
		}
	}

	public void putBoolean(String key, boolean value) {
		mEditor.putBoolean(key, value);
		mEditor.commit();
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return mPref.getBoolean(key, defaultValue);
	}

	public void putString(String key, String value) {
		mEditor.putString(key, value);
		mEditor.commit();
	}

	public String getString(String key, String defaultValue) {
		return mPref.getString(key, defaultValue);
	}

	public void removeKey(String key) {
		mEditor.remove(key);
		mEditor.commit();
	}
}
