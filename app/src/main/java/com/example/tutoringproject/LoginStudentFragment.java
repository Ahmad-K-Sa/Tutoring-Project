package com.example.tutoringproject;

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


public class LoginStudentFragment extends Fragment {
    SQLiteDatabase db;
    Cursor cursor;
    String user;
    String pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_student, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view!=null) {
            EditText username = view.findViewById(R.id.usernameIn);
            user = username.getText().toString();
            EditText password = view.findViewById(R.id.passwordIn);
            pass = password.getText().toString();
        }
        View.OnClickListener onclicklogin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getActivity());
                    db = helper.getReadableDatabase();

                    cursor = db.query("STUDENTS", new String[]{"_id", "USERNAME", "PASSWORD"}, null, null, null, null, null);
                    if (cursor.moveToFirst()) {

                        while (!cursor.isLast()) {
                            cursor.moveToNext();
                            if (cursor.getString(1).equals(user) && cursor.getString(2).equals(pass)) {
                                ((MainActivity) getActivity()).flipStudentAccount();
                                //Pass Student ID
                            }

                        }
                        cursor.moveToNext();
                        if (cursor.getString(1).equals(user) && cursor.getString(2).equals(pass)) {
                            ((MainActivity) getActivity()).flipStudentAccount();
                        }

                        TextView error = view.findViewById(R.id.error);
                        error.setText("Incorrect username or password! Please try again");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Button buttonlogin = view.findViewById(R.id.login);

        buttonlogin.setOnClickListener(onclicklogin);

        View.OnClickListener onclickSignup = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipStudentSignup();
            }
        };

        Button buttonsignup = view.findViewById(R.id.Signup);

        buttonsignup.setOnClickListener(onclickSignup);
    }
}