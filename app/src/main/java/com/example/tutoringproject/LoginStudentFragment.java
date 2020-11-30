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
import android.widget.Toast;


public class LoginStudentFragment extends Fragment {
    SQLiteDatabase db;
    Cursor cursor;
    String user;
    String pass;
    View v;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_login_student, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button BackToMain = v.findViewById(R.id.BackButton);
        View.OnClickListener MainMenu = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).HomeScreen();
            }
        };
        BackToMain.setOnClickListener(MainMenu);
        View.OnClickListener onclicklogin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (v != null) {


                        EditText username = v.findViewById(R.id.usernameIn);
                        user = username.getText().toString();

                        EditText password = v.findViewById(R.id.passwordIn);
                        pass = password.getText().toString();

                    }
                    SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getActivity());
                    db = helper.getReadableDatabase();
                    cursor = db.query("STUDENTS", new String[]{"_id", "USERNAME", "PASSWORD"}, null, null, null, null, null);


                    if (cursor.moveToFirst()) {
                        while (!cursor.isLast()) {
                            if (cursor.getString(1).equals(user) && cursor.getString(2).equals(pass)) {
                                id = Integer.parseInt(cursor.getString(0));
                                ((MainActivity) getActivity()).flipStudentAccount(id);

                                //Pass Student ID
                            }
                            cursor.moveToNext();
                        }
                        if (cursor.getString(1).equals(user) && cursor.getString(2).equals(pass)) {
                            ((MainActivity) getActivity()).flipStudentAccount(id);
                        } else {
                            Toast.makeText(getContext(), "Incorrect username or password! Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Button buttonlogin = v.findViewById(R.id.login);

        buttonlogin.setOnClickListener(onclicklogin);

        View.OnClickListener onclickSignup = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipStudentSignup();
            }
        };

        Button buttonsignup = v.findViewById(R.id.Signup);
        buttonsignup.setOnClickListener(onclickSignup);
    }
}