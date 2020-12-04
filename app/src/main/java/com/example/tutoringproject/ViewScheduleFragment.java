package com.example.tutoringproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tutoringproject.R;

import java.util.ArrayList;

public class ViewScheduleFragment extends Fragment {
    int TUTOR_ID;
    SQLiteDatabase db;
    Cursor cursor;
    View v;

    public ViewScheduleFragment(int TUTOR_ID) {
        this.TUTOR_ID = TUTOR_ID;
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
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_view_scheduel, container, false);
        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        LinearLayout layout = v.findViewById(R.id.RelContainer);
        RecyclerView RV = v.findViewById(R.id.Sched_Recycler);
        Button Home = v.findViewById(R.id.homeButton);
        if (RV.getParent() != null) {
            ((ViewGroup) RV.getParent()).removeView(RV); // <- fix
        }
        layout.addView(RV);
        View.OnClickListener BackHome = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipTutorHomePage(TUTOR_ID);
            }
        };
        Home.setOnClickListener(BackHome);

        Button Logout = v.findViewById(R.id.LogoutButton);

        View.OnClickListener LogoutButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).HomeScreen();
            }
        };
        Logout.setOnClickListener(LogoutButton);

        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
        db = helper.getWritableDatabase();
        cursor = db.query("SCHEDULES", new String[]{"_id", "TIME", "SUBJECT", "TUTOR_ID", "DATE", "STUDENT_ID"}, null, null, null, null, null);
        ArrayList<String> SchedItems = new ArrayList<>();
        int size = 0;
        if (cursor.moveToFirst()) {
            while (!cursor.isLast()) {
                if (cursor.getInt(3) == TUTOR_ID)
                    size++;
                cursor.moveToNext();
            }
            if (cursor.getInt(3) == TUTOR_ID)
                size++;
        }
        String[] Time = new String[size];
        String[] Date = new String[size];
        Integer[] S_IDs = new Integer[size];
        String[] Courses = new String[size];
        int pos = 0;
        cursor = db.query("SCHEDULES", new String[]{"_id", "TIME", "SUBJECT", "TUTOR_ID", "DATE", "STUDENT_ID"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isLast()) {
                if (cursor.getInt(3) == TUTOR_ID) {
                    Time[pos] = cursor.getString(1);
                    Courses[pos] = cursor.getString(2);
                    S_IDs[pos] = cursor.getInt(5);
                    Date[pos] = cursor.getString(4);
                    pos++;
                }
                cursor.moveToNext();

            }
            if (cursor.getInt(3) == TUTOR_ID) {
                Time[pos] = cursor.getString(1);
                Courses[pos] = cursor.getString(2);
                S_IDs[pos] = (cursor.getInt(5));
                Date[pos] = cursor.getString(4);
            }
            Toast.makeText(getActivity(),S_IDs.toString(),Toast.LENGTH_LONG);
            ScheduleCardAdapter Adapter = new ScheduleCardAdapter(Time, Date, S_IDs, Courses);
            RV.setAdapter(Adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            RV.setLayoutManager(layoutManager);
        }
    }
}