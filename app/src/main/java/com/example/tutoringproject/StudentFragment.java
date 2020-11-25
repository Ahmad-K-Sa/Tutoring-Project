package com.example.tutoringproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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
        View view = getView();
        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
        db = helper.getWritableDatabase();
        cursor = db.query("TUTORS", new String[]{"_id","FIRSTNAME","LASTNAME","COURSES"}, null, null, null, null, null);
        ArrayList<String> TutorsInfo = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(!cursor.isLast()){
                TutorsInfo.add(cursor.getString(0) +" "+cursor.getString(1)+"\n" +cursor.getString(3));
                cursor.moveToNext();
            }
            TutorsInfo.add(cursor.getString(0) +" "+cursor.getString(1)+"\n" +cursor.getString(3));

            ArrayAdapter Tutors = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, TutorsInfo);

            ListView lv = view.findViewById(R.id.tutorsListView);
            lv.setAdapter(Tutors);
        }
    }
}