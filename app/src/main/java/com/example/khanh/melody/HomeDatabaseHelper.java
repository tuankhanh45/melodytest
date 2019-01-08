package com.example.khanh.melody;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HomeDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";


    // Version
    private static final int DATABASE_VERSION = 1;


    // DB name
    private static final String DATABASE_NAME = "Home_Manager";


    // Table Name: Home.
    private static final String TABLE_HOME = "Home";

    private static final String COLUMN_HOME_PRICE ="Home_Price";
    private static final String COLUMN_HOME_ID ="Home_Id";
    private static final String COLUMN_HOME_DESCRIPTION ="Home_Description";
    private static final String COLUMN_HOME_IMG_URL = "Home_Img_Img";

    public HomeDatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "HomeDatabaseHelper.onCreate ... ");
        // Script create table
        String script = "CREATE TABLE " + TABLE_HOME + "("
                + COLUMN_HOME_ID + " TEXT,"
                + COLUMN_HOME_DESCRIPTION + " TEXT," + COLUMN_HOME_PRICE+ " TEXT,"
                + COLUMN_HOME_IMG_URL + " TEXT," + "PRIMARY KEY("+COLUMN_HOME_ID+ ")"+")";
        // creating
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "HomeDatabaseHelper.onUpgrade ... ");

        // Drop old table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOME);


        // recreate.
        onCreate(db);
    }

//    // Add data if it not exist
//
//    public void createDefaultHomesIfNeed()  {
//        int count = this.getHomesCount();
//        if(count ==0 ) {
//            Home home1 = new Home("Firstly see Android ListView",
//                    "See Android ListView Example in o7planning.org");
//            Home home2 = new Home("Learning Android SQLite",
//                    "See Android SQLite Example in o7planning.org");
//            this.addHome(home1);
//            this.addHome(home2);
//        }
//    }


    public void addHome(Home home) {
        Log.i(TAG, "HomeDatabaseHelper.addHome ... " + home.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_HOME_ID, home.getHomeId());
        values.put(COLUMN_HOME_PRICE,home.getPrice());
        values.put(COLUMN_HOME_DESCRIPTION, home.getDescription());
        values.put(COLUMN_HOME_IMG_URL, home.getUrlImg());


        // Insert a column
        db.insert(TABLE_HOME, null, values);


        // close connect database.
        db.close();
    }


    public Home getHome(int id) {
        Log.i(TAG, "HomeDatabaseHelper.getHome ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HOME, new String[] { COLUMN_HOME_ID, COLUMN_HOME_PRICE,
                        COLUMN_HOME_DESCRIPTION,COLUMN_HOME_IMG_URL }, COLUMN_HOME_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Home home = new Home(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return note
        return home;
    }


    public List<Home> getAllHomes() {
        Log.i(TAG, "HomeDatabaseHelper.getAllHomes ... " );

        List<Home> homeList = new ArrayList<Home>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HOME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Home home = new Home();
                home.setHomeId(cursor.getString(0));
                home.setPrice(cursor.getString(1));
                home.setDescription(cursor.getString(2));
                home.setUrlImg(cursor.getString(3));

                // add to list
                homeList.add(home);
            } while (cursor.moveToNext());
        }

        // return note list
        return homeList;
    }

    public int getNotesCount() {
        Log.i(TAG, "MyDatabaseHelper.getHomesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_HOME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateHome(Home home) {
        Log.i(TAG, "HomeDatabaseHelper.updateHome ... "  + home.getHomeId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HOME_ID, home.getHomeId());
        values.put(COLUMN_HOME_PRICE, home.getPrice());
        values.put(COLUMN_HOME_DESCRIPTION,home.getDescription());
        values.put(COLUMN_HOME_IMG_URL,home.getUrlImg());
        // updating row
        return db.update(TABLE_HOME, values, COLUMN_HOME_ID + " = ?",
                new String[]{String.valueOf(home.getHomeId())});
    }

    public void deleteHome(Home home) {
        Log.i(TAG, "HomeDatabaseHelper.updateHome ... " + home.getHomeId() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOME, COLUMN_HOME_ID+ " = ?",
                new String[] { String.valueOf(home.getHomeId()) });
        db.close();
    }
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOME, "1", null);
    }
}
