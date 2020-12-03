package com.example.tutoringproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TutorCardAdapter extends RecyclerView.Adapter
        <TutorCardAdapter.ViewHolder> {
    String[] Usernames;
    Integer[] IDS;
    int StudentID;
    String[] Firstnames;
    String[] Lastnames;
    Integer[] PhoneNums;
    String[] Addresses;
    String[] Courses;

    @NonNull
    @Override
    public TutorCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_tutor, viewGroup, false);
        return new TutorCardAdapter.ViewHolder(cv);
    }

    public TutorCardAdapter(int StudentID,Integer ID[],String[] usernames, String[] firstnames, String[] lastnames, Integer[] phoneNums, String[] addresses, String[] courses) {
        Usernames = usernames;
        this.IDS = ID;
        this.StudentID = StudentID;
        Firstnames = firstnames;
        Lastnames = lastnames;
        PhoneNums = phoneNums;
        Addresses = addresses;
        Courses = courses;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cv) {
            super(cv);
            cardView = cv;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
    CardView cardView = viewHolder.cardView;

        TextView Username = cardView.findViewById(R.id.UserName);
        Username.setText(Usernames[position]);

        TextView FirstName = cardView.findViewById(R.id.FirstName);
        FirstName.setText(Firstnames[position]);

        TextView LastName = cardView.findViewById(R.id.lastName);
        LastName.setText(Usernames[position]);

        TextView Phone = cardView.findViewById(R.id.PhoneNumber);
        Phone.setText(PhoneNums[position].toString());

        TextView Address = cardView.findViewById(R.id.Address);
        Address.setText(Addresses[position]);

        TextView Course = cardView.findViewById(R.id.Courses);
        Course.setText(Courses[position]);

        View.OnClickListener ViewSchedule = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext()).flipTutorCourses(IDS[position],StudentID);
            }
        };
        cardView.setOnClickListener(ViewSchedule);
    }

    public int getItemCount() {
        return Usernames.length;
    }
}
