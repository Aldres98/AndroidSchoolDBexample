package com.example.aldres.androidschooldbexample;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aldres on 29.06.2018.
 */

public class MyDbManager {

    private MyDbHelper dbHelper;

    public MyDbManager(Context context){
        this.dbHelper = new MyDbHelper(context);
    }

    public void addNote(Note note){
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            ContentValues contentValues = getContentValues(note);
            addNoteInternal(db, contentValues);
            db.beginTransaction();
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
    }

    public Note getNote() {
        Note note = null;
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("NOTES", null, null,
                    null, null, null, null,
                    null);
            note = parseCursos(cursor);
            cursor.close();
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if (db != null) {
                if (db.inTransaction()){
                    db.endTransaction();
                }
             db.close();
            }
        }
        return note;
    }

    public ArrayList<Note> getNotesList(){
        SQLiteDatabase db = null;
        ArrayList<Note> notesList = new ArrayList<>();
        try {
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("NOTES", null, null,
                    null, null, null, null,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    notesList.add(parseCursos(cursor));
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if (db != null) {
                if (db.inTransaction()){
                    db.endTransaction();
                }
                db.close();
            }
        }
        return notesList;
    }

    public void removeNote(){

    }

    private ContentValues getContentValues(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", note.getId());
        contentValues.put("title", note.getTitle());
        contentValues.put("body", note.getBody());
        return contentValues;
    }

    private void addNoteInternal (SQLiteDatabase db, ContentValues contentValues){
        db.insert("NOTES", null, contentValues);
    }

    private Note parseCursos(Cursor cursor){
        if (cursor.moveToFirst()){
            return new Note(cursor.getInt(cursor.getColumnIndex("id")) ,cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("body")));
        }
        return null;
    }
}
