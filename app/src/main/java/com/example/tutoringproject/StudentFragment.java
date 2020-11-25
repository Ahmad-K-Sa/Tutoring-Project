package com.example.tutoringproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StudentFragment extends Fragment {
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
        db = helper.getWritableDatabase();
        cursor = db.query("TUTORS", new String[]{"_id", "USERNAME", "PASSWORD","COURSES","FIRSTNAME","LASTNAME"}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            while(!cursor.isLast()){

            }
        }
    }
}