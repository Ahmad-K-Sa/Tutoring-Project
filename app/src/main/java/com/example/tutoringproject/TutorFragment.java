package com.example.tutoringproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class TutorFragment extends Fragment {
    int TUTOR_ID;
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
        return inflater.inflate(R.layout.fragment_tutor, container, false);
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
        Button buttonSched = view.findViewById(R.id.setSchedule);

        buttonSched.setOnClickListener(onclickSetSched);


        View.OnClickListener onclickViewSched = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipViewSchedule(TUTOR_ID);
            }
        };
        Button buttonsched = view.findViewById(R.id.viewschedule);
        buttonsched.setOnClickListener(onclickViewSched);
    }
}