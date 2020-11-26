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

import com.example.tutoringproject.R;

public class SetScheduleFragment extends Fragment {
    SQLiteDatabase db;
    View v;
    int TUTOR_ID;

    public SetScheduleFragment(int TUTOR_ID) {
        this.TUTOR_ID = TUTOR_ID;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_set_schedule, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (v != null) {
            Button submit = v.findViewById(R.id.Submit);
            View.OnClickListener onclickRegisterSched = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        EditText time = v.findViewById(R.id.editTextTime);
                        EditText subject = v.findViewById(R.id.editTextSubject);

                        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getActivity());
                        db = helper.getWritableDatabase();

                        ContentValues cv = new ContentValues();
                        cv.put("Subject",subject.getText().toString());
                        cv.put("TUTOR_ID",TUTOR_ID);
                        cv.put("TIME",time.getText().toString());
                        db.insert("SCHEDULES", null, cv);
                        db.close();

                        ((MainActivity) getActivity()).flipTutorAccount(TUTOR_ID);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            submit.setOnClickListener(onclickRegisterSched);
        }

    }
}