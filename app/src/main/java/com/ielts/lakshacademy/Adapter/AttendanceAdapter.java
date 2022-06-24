package com.ielts.lakshacademy.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ielts.lakshacademy.Model.AttendanceData;
import com.ielts.lakshacademy.R;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {
    Context context;
    ArrayList<AttendanceData> attendanceDataArrayList;

    public AttendanceAdapter(Context context, ArrayList<AttendanceData> attendanceDataArrayList) {
        this.context = context;
        this.attendanceDataArrayList = attendanceDataArrayList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.attendance_list,parent,false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {

        holder.dateText.setText(attendanceDataArrayList.get(position).getDate());
        holder.listingText.setText(attendanceDataArrayList.get(position).getListing());
        holder.readingText.setText(attendanceDataArrayList.get(position).getReading());
        holder.writingText.setText(attendanceDataArrayList.get(position).getWriting());
        holder.speakingText.setText(attendanceDataArrayList.get(position).getSpeaking());

        if(position % 2 == 1){
            holder.attendanceLinear.setBackgroundResource(R.color.soft_blue);

        }else {
            holder.attendanceLinear.setBackgroundResource(R.color.soft_orange);
        }

    }

    @Override
    public int getItemCount() {
        return attendanceDataArrayList.size();
    }


    public static final class AttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView dateText,listingText,readingText,writingText,speakingText;
        CardView attendanceCard;
        LinearLayout attendanceLinear;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            listingText = itemView.findViewById(R.id.listingText);
            readingText = itemView.findViewById(R.id.readingText);
            writingText = itemView.findViewById(R.id.writingText);
            speakingText = itemView.findViewById(R.id.speakingText);
            attendanceCard = itemView.findViewById(R.id.attendanceCard);
            attendanceLinear = itemView.findViewById(R.id.attendanceLinear);

        }
    }
}
