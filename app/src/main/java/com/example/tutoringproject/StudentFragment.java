package com.example.tutoringproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentFragment extends Fragment {
    SQLiteDatabase db;
    Cursor cursor;
    int StudentId;

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

    public void getId(int id) {
        StudentId = id;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        Button Logout = view.findViewById(R.id.LogoutButton);

        View.OnClickListener LogoutButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).HomeScreen();
            }
        };
        Logout.setOnClickListener(LogoutButton);

        ListView lv = view.findViewById(R.id.tutorsListView);
        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
        db = helper.getWritableDatabase();
        cursor = db.query("TUTORS", new String[]{"_id", "FIRSTNAME", "LASTNAME", "COURSES"}, null, null, null, null, null);
        ArrayList<String> TutorsInfo = new ArrayList<>();
        final ArrayList<Integer> TutuorIDs = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isLast()) {
                TutorsInfo.add(cursor.getString(1) + " " + cursor.getString(2));
                TutuorIDs.add(cursor.getInt(0));
                cursor.moveToNext();
            }
            TutorsInfo.add(cursor.getString(1) + " " + cursor.getString(2));
            TutuorIDs.add(cursor.getInt(0));
            ArrayAdapter Tutors = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, TutorsInfo);
            lv.setAdapter(Tutors);
        }
        AdapterView.OnItemClickListener adapter = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ((MainActivity) getActivity()).flipTutorCourses(TutuorIDs.get(position), StudentId);
            }
        };
        lv.setOnItemClickListener(adapter);
    }
}