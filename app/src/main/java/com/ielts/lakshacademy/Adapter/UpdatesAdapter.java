package com.ielts.lakshacademy.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ielts.lakshacademy.Model.UpdatesData;
import com.ielts.lakshacademy.R;

import java.util.ArrayList;

public class UpdatesAdapter extends RecyclerView.Adapter<UpdatesAdapter.UpdatesViewHolder> {

    Context context;
    ArrayList<UpdatesData> updatesDataArrayList;

    public UpdatesAdapter(Context context, ArrayList<UpdatesData> updatesDataArrayList) {
        this.context = context;
        this.updatesDataArrayList = updatesDataArrayList;
    }

    @NonNull
    @Override
    public UpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.update_list,parent,false);

        return new UpdatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdatesViewHolder holder, int position) {

        holder.tvDate.setText(updatesDataArrayList.get(position).getDate());
        holder.tvTime.setText(updatesDataArrayList.get(position).getTime());
        holder.tvDescription.setText(updatesDataArrayList.get(position).getDesc());

        if(position % 2 == 1){

            holder.conDescription.setBackgroundColor(Color.parseColor("#E7F3FC"));


        }else {

            holder.conDescription.setBackgroundColor(Color.parseColor("#FEEEE2"));

        }

    }

    @Override
    public int getItemCount() {
        return updatesDataArrayList.size();
    }


    public static final class UpdatesViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate,tvTime,tvDescription;
        ConstraintLayout conDescription;

        public UpdatesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            conDescription = itemView.findViewById(R.id.conDescription);


        }
    };
}
