package com.example.tutoringproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tutoringproject.R;

public class ViewTutorFragment extends Fragment {
    int TUTOR_ID;
    SQLiteDatabase db;
    Cursor cursor;
    View v;

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }

    public ViewTutorFragment(int T_ID) {
        this.TUTOR_ID = T_ID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_view_tutor, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
        db = helper.getWritableDatabase();
        cursor = db.query("TUTORS", new String[]{"_id", "USERNAME", "FIRSTNAME", "LASTNAME", "COURSES", "PHONENUMBER", "ADDRESS"}, "_id=?", new String[]{Integer.toString(TUTOR_ID)}, null, null, null);
        cursor.moveToFirst();
        TextView Name = v.findViewById(R.id.Name);
        Name.setText(cursor.getString(2) +" "+ cursor.getString(3));
        TextView User = v.findViewById(R.id.UserName);
        User.setText(cursor.getString(1));
        TextView Phone = v.findViewById(R.id.PhoneNumber);
        Phone.setText(cursor.getString(5));
        TextView Address = v.findViewById(R.id.Address);
        Address.setText(cursor.getString(6));
        TextView Courses = v.findViewById(R.id.Courses);
        Courses.setText(cursor.getString(4));
    }
}