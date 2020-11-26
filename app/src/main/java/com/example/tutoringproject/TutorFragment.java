package com.example.tutoringproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TutorFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutor, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        View.OnClickListener onclickSetSched = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipSetSchedule();

            }
        };
        Button buttonSched = view.findViewById(R.id.setSchedule);

        buttonSched.setOnClickListener(onclickSetSched);


        View.OnClickListener onclickViewSched = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipViewSchedule();

            }
        };
        Button buttonsched = view.findViewById(R.id.viewschedule);
        buttonsched.setOnClickListener(onclickViewSched);
    }
}