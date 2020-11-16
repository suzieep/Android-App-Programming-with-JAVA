package com.example.note1116_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDBManager extends SQLiteOpenHelper {
    static final String STUDENT_DB = "Students.db";
    static final String STUDENT_TABLE = "Students";
    Context context = null;
    private static StudentDBManager dbManager = null;
    static final String CREATE_DB = " CREATE TABLE " + STUDENT_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " student_id TEXT NOT NULL, name TEXT NOT NULL, phone_number TEXT);";

    public static StudentDBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new StudentDBManager(context, STUDENT_DB, null, 1);
        }
        return dbManager;
    }

    public StudentDBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
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
        return getWritableDatabase().insert(STUDENT_TABLE, null, addValue);
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return getReadableDatabase().query(STUDENT_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return getWritableDatabase().delete(STUDENT_TABLE, whereClause, whereArgs);
    }
}

