package com.sukhov.android.ainsoft;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sukhov on 24.01.2018.
 */

public class SQLiteConnector extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AinSoft.db";
    public static final String TABLE_NAME = "Products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";

    public SQLiteConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " integer unique, " +
                COLUMN_NAME + " varchar(20), " +
                COLUMN_PRICE + " varchar(20));"
        );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public ArrayList<Product> getProducts(){

        String query = "SELECT  * FROM " + "Products";

        SQLiteDatabase mSQLiteDatabase = this.getWritableDatabase();
        ArrayList<Product> productsArrayList = new ArrayList<>();
        Cursor c = mSQLiteDatabase.rawQuery(query, null);
        Product product;
        if (c.moveToFirst()) {
            do {
                product = new Product(c.getString(c.getColumnIndex("name")), c.getString(c.getColumnIndex("price")));
                productsArrayList.add(product);
            } while (c.moveToNext());
        }
        return productsArrayList;
    }

    public void updatePrice(int personId, Context context, String updated) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET price ='" + updated + "' WHERE _id='" + personId + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();
    }
}
