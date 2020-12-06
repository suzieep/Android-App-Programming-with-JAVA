package com.example.suziestraveldiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PostDBManager extends SQLiteOpenHelper {
    static final String POST_DB = "Posts.db";
    static final String POST_TABLE = "Posts";
    Context context = null;
    private static PostDBManager dbManager = null;
    static final String CREATE_DB = " CREATE TABLE " + POST_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,_title TEXT NOT NULL, " + " _details TEXT, _image TEXT, _latitude TEXT NOT NULL, _longitude TEXT NOT NULL);";

    public static PostDBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new PostDBManager(context, POST_DB, null, 1);
        }
        return dbManager;
    }

    public PostDBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

    }

    public long insert(ContentValues addValue) {
        return getWritableDatabase().insert(POST_TABLE, null, addValue);
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return getReadableDatabase().query(POST_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return getWritableDatabase().delete(POST_TABLE, whereClause, whereArgs);
    }
}

