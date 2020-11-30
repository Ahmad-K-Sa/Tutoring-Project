package com.example.tutoringproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class TutorFragment extends Fragment {
    int TUTOR_ID;
    View v;
    public TutorFragment(int TUTOR_ID) {
        this.TUTOR_ID = TUTOR_ID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_tutor, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        View.OnClickListener onclickSetSched = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipSetSchedule(TUTOR_ID);

            }
        };
        Button buttonSched = v.findViewById(R.id.setSchedule);

        buttonSched.setOnClickListener(onclickSetSched);


        View.OnClickListener onclickViewSched = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipViewSchedule(TUTOR_ID);
            }
        };
        Button buttonsched = v.findViewById(R.id.viewschedule);
        buttonsched.setOnClickListener(onclickViewSched);

        Button Logout = v.findViewById(R.id.LogoutButton);

        View.OnClickListener LogoutButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).HomeScreen();
            }
        };
        Logout.setOnClickListener(LogoutButton);

        Button ViewInfo = v.findViewById(R.id.Info);

        View.OnClickListener Info = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).TutorInfo(TUTOR_ID);
            }
        };
        ViewInfo.setOnClickListener(Info);

    }
}