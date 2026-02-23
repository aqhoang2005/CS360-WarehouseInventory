package com.example.cs360warehouseinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseActivity extends SQLiteOpenHelper {

    private static final String DB_NAME = "inventory.db";
    private static final int DB_VERSION = 3;

    public static final String T_ITEMS = "items";
    public static final String C_ITEM_ID = "id";
    public static final String C_NAME = "name";
    public static final String C_QTY = "qty";
    public static final String C_DESC = "description";
    public static final String C_TAGS = "tags";
    public static final String C_IMAGE_URI = "image_uri";

    public static final String T_USERS = "users";
    public static final String C_USER_ID = "id";
    public static final String C_USERNAME = "username";
    public static final String C_PASSWORD = "password";

    public DatabaseActivity(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // Forces DB creation early:
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + T_USERS + " (" +
                        C_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        C_USERNAME + " TEXT NOT NULL UNIQUE, " +
                        C_PASSWORD + " TEXT NOT NULL" +
                        ");"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + T_ITEMS + " (" +
                        C_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        C_NAME + " TEXT NOT NULL, " +
                        C_QTY + " INTEGER NOT NULL DEFAULT 0, " +
                        C_DESC + " TEXT, " +
                        C_TAGS + " TEXT, " +
                        C_IMAGE_URI + " TEXT" +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + T_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + T_USERS);
        onCreate(db);
    }

    public Cursor getAllItemsCursor() {
        return getReadableDatabase().query(
                T_ITEMS,
                new String[]{C_ITEM_ID, C_NAME, C_QTY, C_DESC, C_TAGS, C_IMAGE_URI},
                null, null, null, null,
                C_NAME + " COLLATE NOCASE ASC"
        );
    }

    public int deleteItem(long id) {
        return getWritableDatabase().delete(
                T_ITEMS,
                C_ITEM_ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    public long insertItem(String name, int qty, String desc, String tags, String imageUri) {
        ContentValues v = new ContentValues();
        v.put(C_NAME, name);
        v.put(C_QTY, qty);
        v.put(C_DESC, desc);
        v.put(C_TAGS, tags);
        v.put(C_IMAGE_URI, imageUri);
        return getWritableDatabase().insert(T_ITEMS, null, v);
    }

    public Cursor getItemById(long editId) {
        return getReadableDatabase().query(
                T_ITEMS,
                new String[]{C_ITEM_ID, C_NAME, C_QTY, C_DESC, C_TAGS, C_IMAGE_URI},
                C_ITEM_ID + "=?",
                new String[]{String.valueOf(editId)},
                null,
                null,
                null
        );
    }

    public int updateItem(long id, String name, int qty, String desc, String tags, String imageUri) {

        ContentValues values = new ContentValues();
        values.put(C_NAME, name);
        values.put(C_QTY, qty);
        values.put(C_DESC, desc);
        values.put(C_TAGS, tags);
        values.put(C_IMAGE_URI, imageUri);

        return getWritableDatabase().update(
                T_ITEMS,
                values,
                C_ITEM_ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    public long createUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(C_USERNAME, username);
        values.put(C_PASSWORD, password);
        return getWritableDatabase().insert(T_USERS, null, values); // returns -1 if fails
    }

    public boolean userExists(String username) {
        Cursor c = getReadableDatabase().query(
                T_USERS,
                new String[]{C_USER_ID},
                C_USERNAME + "=?",
                new String[]{username},
                null, null, null
        );
        boolean exists = (c != null && c.moveToFirst());
        if (c != null) c.close();
        return exists;
    }

    public boolean validateLogin(String username, String password) {
        Cursor c = getReadableDatabase().query(
                T_USERS,
                new String[]{C_USER_ID},
                C_USERNAME + "=? AND " + C_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null
        );
        boolean ok = (c != null && c.moveToFirst());
        if (c != null) c.close();
        return ok;
    }

}