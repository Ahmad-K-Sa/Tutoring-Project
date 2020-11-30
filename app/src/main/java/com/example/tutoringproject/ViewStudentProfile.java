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
public class ViewStudentProfile extends Fragment {
    int STUDENT_ID;
    SQLiteDatabase db;
    Cursor cursor;
    View v;

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public ViewStudentProfile(int SID){
        this.STUDENT_ID = SID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_view_student_profile, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
            super.onStart();
            SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
            db = helper.getWritableDatabase();
            cursor = db.query("STUDENTS", new String[]{"_id", "USERNAME", "FIRSTNAME", "LASTNAME", "LEVEL", "PHONENUMBER", "ADDRESS"}, "_id=?", new String[]{Integer.toString(STUDENT_ID)}, null, null, null);
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
            Courses.setText("Level: "+cursor.getInt(4));
    }
}