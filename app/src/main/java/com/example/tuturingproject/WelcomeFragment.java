package com.example.tuturingproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class WelcomeFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welocme, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view= getView();
        View.OnClickListener onclickStudent= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipStudent();

            }
        };
        Button buttonStudent = view.findViewById(R.id.student);

        buttonStudent.setOnClickListener(onclickStudent);

        View.OnClickListener onclickTutor= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).flipTutor();

            }
        };
        Button buttonTutor = view.findViewById(R.id.tutor);

        buttonTutor.setOnClickListener(onclickTutor);
    }
}