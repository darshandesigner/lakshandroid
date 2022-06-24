package com.ielts.lakshacademy.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ielts.lakshacademy.Model.CounselorData;
import com.ielts.lakshacademy.R;

import java.util.ArrayList;

public class CounselorAdapter extends RecyclerView.Adapter <CounselorAdapter.CounselorViewHolder>{

    Context context;
    ArrayList<CounselorData> counselorDataArrayList;

    public CounselorAdapter(Context context, ArrayList<CounselorData> counselorDataArrayList) {
        this.context = context;
        this.counselorDataArrayList = counselorDataArrayList;
    }

    @NonNull
    @Override
    public CounselorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.counselor_list,parent,false);
        return new CounselorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounselorViewHolder holder, int position) {

        holder.counselorName.setText(counselorDataArrayList.get(position).getName());

        holder.counselorRatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(
                        Color.TRANSPARENT
                ));

                View viewDialog = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.rating_dailog,null);
                dialog.setContentView(viewDialog);
                dialog.show();
                RatingBar ratingBar = viewDialog.findViewById(R.id.ratingBar);
                Button btnSubmit = viewDialog.findViewById(R.id.btnSubmit);
                TextView tv_rating = viewDialog.findViewById(R.id.tv_rating);


                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        tv_rating.setText(String.format("(%s)",rating));
                    }
                });

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String rating = String.valueOf(ratingBar.getRating());
                        Toast.makeText(context,rating, Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return counselorDataArrayList.size();
    }

    public static final class CounselorViewHolder extends RecyclerView.ViewHolder{

        ImageView counselorImage;
        TextView counselorName,counselorRatting;

        public CounselorViewHolder(@NonNull View itemView) {
            super(itemView);
            counselorImage = itemView.findViewById(R.id.counselorImage);
            counselorName = itemView.findViewById(R.id.counselorName);
            counselorRatting = itemView.findViewById(R.id.counselorRatting);
        }
    }
}


