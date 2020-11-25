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
    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.fragment_tutor_register, container, false);
       return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        v= getView();
        if (v != null) {

            View.OnClickListener onclickRegisterTutor = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        final EditText Username = v.findViewById(R.id.usernameIn);
                        final EditText Password = v.findViewById(R.id.passwordInput);
                        final EditText Fisrtname = v.findViewById(R.id.firstNameInput);
                        final EditText Courses = v.findViewById(R.id.coursesInput);
                        final EditText LastName = v.findViewById(R.id.lastNameInput);
                        final EditText Address = v.findViewById(R.id.addressInput);
                        final EditText PhoneNumber = v.findViewById(R.id.phoneNumberInput);

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
            Button Register = v.findViewById(R.id.Register);
            Register.setOnClickListener(onclickRegisterTutor);
        }
    }
}