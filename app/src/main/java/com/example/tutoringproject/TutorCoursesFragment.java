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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class TutorCoursesFragment extends Fragment {
    int ID;
    int studentId;
    SQLiteDatabase db;
    Cursor cursor;

    public void getStudentId(int id) {
        this.studentId = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutor_courses, container, false);
    }

    public void setId(int id) {
        this.ID = id;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }
    @Override
    public void onStart() {
        View view = getView();
        super.onStart();
        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getActivity());
        db = helper.getReadableDatabase();
        cursor = db.query("SCHEDULES", new String[]{"_id", "SUBJECT", "DATE", "TIME", "STUDENT_ID"}, "TUTOR_ID=?", new String[]{Integer.toString(ID)}, null, null, null);

        ArrayList<String> Data = new ArrayList();
        final ArrayList<Integer> Sched_IDs = new ArrayList();
        Boolean ItemsExist = false;
        if (cursor.moveToFirst()) {
            ItemsExist = true;
            while (!cursor.isLast()) {
                Data.add("Subject: " + cursor.getString(1) + "\n" + "Date: " + cursor.getString(2) + " " + "Time: " + cursor.getString(3) + "\n" + (cursor.getInt(4) != 0 ? "Reserverd" : "Not Reserved"));
                Sched_IDs.add(cursor.getInt(0));
                cursor.moveToNext();
            }
            Data.add("Subject: " + cursor.getString(1) + "\n" + "Date: " + cursor.getString(2) + " " + "Time: " + cursor.getString(3) + "\n" + (cursor.getInt(4) != 0 ? "Reserverd" : "Not Reserved"));
            Sched_IDs.add(cursor.getInt(0));
        }
        ArrayAdapter items = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, Data);
        final ListView list = view.findViewById(R.id.list);
        list.setAdapter(items);
        if (ItemsExist) {
            final AdapterView.OnItemClickListener adapter = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    int id2 = (int) id;
                    SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getActivity());
                    db = helper.getWritableDatabase();
                    cursor = db.query("SCHEDULES", new String[]{"_id", "SUBJECT", "DATE", "TIME", "STUDENT_ID", "TUTOR_ID"}, "TUTOR_ID=?", new String[]{Integer.toString(ID)}, null, null, null);
                    if (cursor.moveToFirst()) {
                        if (cursor.getInt(4) == 0) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("SUBJECT", cursor.getString(1));
                            contentValues.put("DATE", cursor.getString(2));
                            contentValues.put("TIME", cursor.getString(3));
                            contentValues.put("STUDENT_ID", Integer.parseInt(studentId + ""));
                            contentValues.put("TUTOR_ID", Integer.parseInt(cursor.getString(5)));
                            String whereClause = "_id=?";
                            String whereArgs[] = {Integer.toString(Sched_IDs.get(position))};
                            int a = db.update("SCHEDULES", contentValues, whereClause, whereArgs);
                            if (a == -1 || a == 0)
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getContext(), "You registered the course. Thank you!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "That course is already registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            };
            list.setOnItemClickListener(adapter);
        }
    }
}