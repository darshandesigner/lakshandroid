package com.ielts.lakshacademy.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ielts.lakshacademy.R;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter <CalendarAdapter.CalendarViewHolder>{

    ArrayList<String> dayOfMonth;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> dayOfMonth, OnItemListener onItemListener) {
        this.dayOfMonth = dayOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.calender_cell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        holder.dayOfMonth.setText(dayOfMonth.get(position));

    }

    @Override
    public int getItemCount() {
        return dayOfMonth.size();
    }

    public interface OnItemListener{
        void onItemClick(int position,String dayText);
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView dayOfMonth;
        private final CalendarAdapter.OnItemListener onItemListener;

        public CalendarViewHolder(@NonNull View itemView, OnItemListener onItemListener) {

            super(itemView);
            dayOfMonth = itemView.findViewById(R.id.dayOfMonth);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition(),(String) dayOfMonth.getText());
        }
    }
}
