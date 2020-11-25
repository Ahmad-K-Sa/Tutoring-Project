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

public class LoginTutorFragment extends Fragment {
    SQLiteDatabase db;
    Cursor cursor;
    String user;
    String pass;
    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_login_student, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
View view= getView();
        View.OnClickListener onclicklogin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if(v!=null) {
                        EditText username = v.findViewById(R.id.usernameIn);
                        user = username.getText().toString();
                        EditText password = v.findViewById(R.id.passwordIn);
                        pass = password.getText().toString();
                    }
                    SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getActivity());
                    db = helper.getReadableDatabase();

                    cursor = db.query("TUTORS", new String[]{"_id", "USERNAME", "PASSWORD"}, null, null, null, null, null);
                    if (cursor.moveToFirst()) {

                        while (!cursor.isLast()) {
                            cursor.moveToNext();
                            if (cursor.getString(1).equals(user) && cursor.getString(2).equals(pass)) {
                                ((MainActivity) getActivity()).flipTutorAccount();
                                //Pass Student ID
                            }
                            cursor.moveToNext();

                        }
                        if (cursor.getString(1).equals(user) && cursor.getString(2).equals(pass)) {
                            ((MainActivity) getActivity()).flipTutorAccount();
                        } else {
                            TextView error = v.findViewById(R.id.error);
                            error.setText("Incorrect username or password! Please try again");
                        }
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
                ((MainActivity) getActivity()).flipTutorSignup();
            }
        };

        Button buttonsignup = view.findViewById(R.id.Signup);

        buttonsignup.setOnClickListener(onclickSignup);
    }
}