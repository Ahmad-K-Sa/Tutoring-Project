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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TutorRegisterFragment extends Fragment {
    SQLiteDatabase db;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_tutor_register, container, false);
       return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (view != null) {
            final EditText Username = view.findViewById(R.id.usernameIn);
            final EditText Password = view.findViewById(R.id.passwordIn);
            final EditText Fisrtname = view.findViewById(R.id.firstNameInput);
            final EditText Courses = view.findViewById(R.id.coursesInput);
            final EditText LastName = view.findViewById(R.id.lastNameInput);
            final EditText Address = view.findViewById(R.id.addressInput);
            final EditText PhoneNumber = view.findViewById(R.id.phoneNumberInput);
            View.OnClickListener onclickRegisterTutor = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getActivity());
                        db = helper.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("USERNAME", Username.getText().toString());
                        cv.put("PASSWORD", Password.getText().toString());
                        cv.put("FIRSTNAME", Fisrtname.getText().toString());
                        cv.put("COURSES", Courses.getText().toString());
                        cv.put("PHONENUMBER", Integer.parseInt(PhoneNumber.getText().toString()));
                        cv.put("ADDRESS", Address.getText().toString());
                        cv.put("LASTNAME", LastName.getText().toString());
                        db.insert("TUTORS", null, cv);
                        ((MainActivity) getActivity()).flipTutorHomePage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            Button Register = view.findViewById(R.id.Register);
            Register.setOnClickListener(onclickRegisterTutor);
        }
    }
}