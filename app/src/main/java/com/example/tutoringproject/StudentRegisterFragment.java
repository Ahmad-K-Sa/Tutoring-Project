package com.example.tutoringproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentRegisterFragment extends Fragment {
SQLiteDatabase db;
int ph=0;
int le=0;
View v;
Cursor cursor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v=inflater.inflate(R.layout.fragment_student_register, container, false);
         return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        View.OnClickListener onclickRegister = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
                db = helper.getWritableDatabase();


                    ContentValues content = new ContentValues();
                    if (v != null) {
                        EditText Fname = v.findViewById(R.id.FnameIn);
                        String Fn = Fname.getText().toString();
                        EditText Lname = v.findViewById(R.id.LnameIn);
                        String Ln = Lname.getText().toString();
                        EditText Username = v.findViewById(R.id.userIn);
                        String user = Username.getText().toString();
                        EditText Password = v.findViewById(R.id.PasswordIn);
                        String pass = Password.getText().toString();
                        EditText Address = v.findViewById(R.id.AddressIn);
                        String addr = Address.getText().toString();
                        EditText phone = v.findViewById(R.id.phoneIn);
                        if (!phone.getText().toString().equals("")) {
                            ph = Integer.parseInt(phone.getText().toString());
                        }
                        EditText level = v.findViewById(R.id.LevelIn);
                        if (!level.getText().toString().equals("")) {
                            le = Integer.parseInt(level.getText().toString());
                        }
                        content.put("FIRSTNAME", Fn);
                        content.put("LASTNAME", Ln);
                        content.put("USERNAME", user);
                        content.put("PASSWORD", pass);
                        content.put("ADDRESS", addr);
                        content.put("PHONENUMBER", ph);
                        content.put("LEVEL", le);
                        long err = db.insert("STUDENTS", null, content);

                        if (err == -1) Toast.makeText(getContext(), "ERRORR", Toast.LENGTH_LONG);
                        db.close();
                        ((MainActivity) getActivity()).flipStudentAccount((int)err);
                    }

                }
            }

            ;

            View view = getView();

            Button buttonregister = view.findViewById(R.id.sign);

                buttonregister.setOnClickListener(onclickRegister);

        }
    }



