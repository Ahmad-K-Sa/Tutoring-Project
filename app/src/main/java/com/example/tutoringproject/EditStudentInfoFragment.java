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

public class EditStudentInfoFragment extends Fragment {
    int Student_ID;
    SQLiteDatabase db;
    Cursor cursor;
    View v;

    public EditStudentInfoFragment(int student_ID) {
        Student_ID = student_ID;
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
        v = inflater.inflate(R.layout.fragment_edit_student_info, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
        db = helper.getWritableDatabase();
        cursor = db.query("STUDENTS", new String[]{"_id", "USERNAME", "PASSWORD", "FIRSTNAME", "LASTNAME", "LEVEL", "PHONENUMBER", "ADDRESS"}, "_id=?", new String[]{Integer.toString(Student_ID)}, null, null, null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            EditText USERNAME = v.findViewById(R.id.UserName);
            EditText PASSWORD = v.findViewById(R.id.PASSWORD);
            EditText FIRSTNAME = v.findViewById(R.id.FIRSTNAME);
            EditText LASTNAME = v.findViewById(R.id.LASTNAME);
            EditText LEVEL = v.findViewById(R.id.LEVEL);
            EditText PHONENUMBER = v.findViewById(R.id.PHONENUMBER);
            EditText ADDRESS = v.findViewById(R.id.ADDRESS);

            USERNAME.setText(cursor.getString(1));
            PASSWORD.setText(cursor.getString(2));
            FIRSTNAME.setText(cursor.getString(3));
            LASTNAME.setText(cursor.getString(4));
            LEVEL.setText(Integer.toString(cursor.getInt(5)));
            PHONENUMBER.setText(Integer.toString(cursor.getInt(6)));
            ADDRESS.setText(cursor.getString(7));
            View.OnClickListener editDetails = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText USERNAME = v.findViewById(R.id.UserName);
                    EditText PASSWORD = v.findViewById(R.id.PASSWORD);
                    EditText FIRSTNAME = v.findViewById(R.id.FIRSTNAME);
                    EditText LASTNAME = v.findViewById(R.id.LASTNAME);
                    EditText LEVEL = v.findViewById(R.id.LEVEL);
                    EditText PHONENUMBER = v.findViewById(R.id.PHONENUMBER);
                    EditText ADDRESS = v.findViewById(R.id.ADDRESS);
                    ContentValues cv = new ContentValues();
                    cv.put("USERNAME", USERNAME.getText().toString());
                    cv.put("PASSWORD", PASSWORD.getText().toString());
                    cv.put("FIRSTNAME", FIRSTNAME.getText().toString());
                    cv.put("LASTNAME", LASTNAME.getText().toString());
                    cv.put("LEVEL", Integer.parseInt(LEVEL.getText().toString()));
                    cv.put("PHONENUMBER", Integer.parseInt(PHONENUMBER.getText().toString()));
                    cv.put("ADDRESS", ADDRESS.getText().toString());
                    String whereClause = "_id=?";
                    String whereArgs[] = {Integer.toString(Student_ID)};
                    db.update("STUDENTS", cv, whereClause, whereArgs);
                }
            };
            Button Save = v.findViewById(R.id.Submit);
            Save.setOnClickListener(editDetails);
        }
    }
}