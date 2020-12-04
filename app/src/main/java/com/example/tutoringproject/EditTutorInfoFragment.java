package com.example.tutoringproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tutoringproject.R;

public class EditTutorInfoFragment extends Fragment {

    int TUTOR_ID;
    SQLiteDatabase db;
    Cursor cursor;
    View v;

    public EditTutorInfoFragment(int id) {
        this.TUTOR_ID = id;
    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_edit_tutor_info, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
        db = helper.getWritableDatabase();
        cursor = db.query("TUTORS", new String[]{"_id", "USERNAME", "PASSWORD", "FIRSTNAME", "LASTNAME", "COURSES", "PHONENUMBER", "ADDRESS"}, "_id=?", new String[]{Integer.toString(TUTOR_ID)}, null, null, null);
        if (cursor.moveToFirst()) {
            EditText USERNAME = v.findViewById(R.id.UserName);
            EditText PASSWORD = v.findViewById(R.id.PASSWORD);
            EditText FIRSTNAME = v.findViewById(R.id.FIRSTNAME);
            EditText LASTNAME = v.findViewById(R.id.LASTNAME);
            EditText COURSES = v.findViewById(R.id.COURSES);
            EditText PHONENUMBER = v.findViewById(R.id.PHONENUMBER);
            EditText ADDRESS = v.findViewById(R.id.ADDRESS);

            USERNAME.setText(cursor.getString(1));
            PASSWORD.setText(cursor.getString(2));
            FIRSTNAME.setText(cursor.getString(3));
            LASTNAME.setText(cursor.getString(4));
            COURSES.setText(cursor.getString(5));
            PHONENUMBER.setText(Integer.toString(cursor.getInt(6)));
            ADDRESS.setText(cursor.getString(7));


            View.OnClickListener editDetails = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText USERNAME = v.findViewById(R.id.UserName);
                    EditText PASSWORD = v.findViewById(R.id.PASSWORD);
                    EditText FIRSTNAME = v.findViewById(R.id.FIRSTNAME);
                    EditText LASTNAME = v.findViewById(R.id.LASTNAME);
                    EditText COURSES = v.findViewById(R.id.COURSES);
                    EditText PHONENUMBER = v.findViewById(R.id.PHONENUMBER);
                    EditText ADDRESS = v.findViewById(R.id.ADDRESS);
                    ContentValues cv = new ContentValues();
                    cv.put("USERNAME", USERNAME.getText().toString());
                    cv.put("PASSWORD", PASSWORD.getText().toString());
                    cv.put("FIRSTNAME", FIRSTNAME.getText().toString());
                    cv.put("LASTNAME", LASTNAME.getText().toString());
                    cv.put("COURSES", COURSES.getText().toString());
                    cv.put("PHONENUMBER", Integer.parseInt(PHONENUMBER.getText().toString()));
                    cv.put("ADDRESS", ADDRESS.getText().toString());
                    String whereClause = "_id=?";
                    String whereArgs[] = {Integer.toString(TUTOR_ID)};
                    db.update("TUTORS", cv, whereClause, whereArgs);
                    ((MainActivity) getActivity()).flipTutorHomePage(TUTOR_ID);

                }
            };
            Button Save = v.findViewById(R.id.Submit);
            Save.setOnClickListener(editDetails);
        }
    }
}