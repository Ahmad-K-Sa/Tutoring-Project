package com.example.tutoringproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleCardAdapter extends RecyclerView.Adapter
        <ScheduleCardAdapter.ViewHolder> {

    private String[] Times;
    private String[] Dates;
    private Integer[] StudentIDs;
    private String[] Subjects;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cv) {
            super(cv);
            cardView = cv;
        }
    }

    public ScheduleCardAdapter(String[] times, String[] dates, Integer[] studentIDs, String[] subjects) {
        Times = times;
        Dates = dates;
        StudentIDs = studentIDs;
        Subjects = subjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        CardView cardView = viewHolder.cardView;
        TextView Time = cardView.findViewById(R.id.Time);

        Time.setText(Times[position]);
        TextView Date = cardView.findViewById(R.id.Date);

        Date.setText(Dates[position]);
        TextView SID = cardView.findViewById(R.id.StudentID);

        if (StudentIDs[position] == 0)
            SID.setText("Not Reserved");
        else {
            SID.setText("Reserved");
            View.OnClickListener Info = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) view.getContext()).StudentInfo(StudentIDs[position]);
                }
            };
            cardView.setOnClickListener(Info);
        }
        TextView Subject = cardView.findViewById(R.id.Subject);
        Subject.setText(Subjects[position]);

    }

    @Override
    public int getItemCount() {
        return Times.length;
    }

}
