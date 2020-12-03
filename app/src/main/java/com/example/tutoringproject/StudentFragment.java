package com.example.tutoringproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentFragment extends Fragment {
    SQLiteDatabase db;
    Cursor cursor;
    int StudentId;
    String[] Usernames;
    String[] Firstnames;
    String[] Lastnames;
    Integer[] PhoneNums;
    String[] Addresses;
    String[] Courses;
    Integer[] IDs;

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
        Button ViewInfo = view.findViewById(R.id.Info);
        View.OnClickListener Info = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).StudentInfo(StudentId);
            }
        };
        ViewInfo.setOnClickListener(Info);
        Button Logout = view.findViewById(R.id.LogoutButton);
        View.OnClickListener LogoutButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).HomeScreen();
            }
        };
        Logout.setOnClickListener(LogoutButton);

        LinearLayout layout = view.findViewById(R.id.RelContainer);
        RecyclerView TutorList = view.findViewById(R.id.TutorList);
        if (TutorList.getParent() != null) {
            ((ViewGroup) TutorList.getParent()).removeView(TutorList); // <- fix
        }
        layout.addView(TutorList);
        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
        db = helper.getWritableDatabase();
        cursor = db.query("TUTORS", new String[]{"_id", "USERNAME", "FIRSTNAME", "LASTNAME", "PHONENUMBER", "ADDRESS", "COURSES"}, null, null, null, null, null);

        int size = cursor.getCount();
        IDs = new Integer[size];
        Usernames = new String[size];
        Firstnames = new String[size];
        Lastnames = new String[size];
        PhoneNums = new Integer[size];
        Addresses = new String[size];
        Courses = new String[size];

        int pos = 0;

        if (cursor.moveToFirst()) {
            while (!cursor.isLast()) {
                IDs[pos] = cursor.getInt(0);
                Usernames[pos] = cursor.getString(1);
                Firstnames[pos] = cursor.getString(2);
                Lastnames[pos] = cursor.getString(3);
                PhoneNums[pos] = cursor.getInt(4);
                Addresses[pos] = cursor.getString(5);
                Courses[pos] = cursor.getString(6);
                cursor.moveToNext();
                pos++;
            }
            Usernames[pos] = cursor.getString(1);
            Firstnames[pos] = cursor.getString(2);
            Lastnames[pos] = cursor.getString(3);
            PhoneNums[pos] = cursor.getInt(4);
            Addresses[pos] = cursor.getString(5);
            Courses[pos] = cursor.getString(6);
            IDs[pos] = cursor.getInt(0);

            TutorCardAdapter adapter = new TutorCardAdapter(StudentId, IDs, Usernames, Firstnames, Lastnames, PhoneNums, Addresses, Courses);
            TutorList.setAdapter(adapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            TutorList.setLayoutManager(layoutManager);

            Button EditInfo = view.findViewById(R.id.Edit);

            View.OnClickListener Edit = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).StudentEditInfo(StudentId);
                }
            };
            EditInfo.setOnClickListener(Edit);
        }
    }
}