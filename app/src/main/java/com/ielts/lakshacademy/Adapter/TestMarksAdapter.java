package com.ielts.lakshacademy.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ielts.lakshacademy.ApiHelper.WebUrl;
import com.ielts.lakshacademy.Model.TestMarksData;
import com.ielts.lakshacademy.PdfViewActivity;
import com.ielts.lakshacademy.R;

import java.util.ArrayList;

public class TestMarksAdapter extends RecyclerView.Adapter<TestMarksAdapter.TestMarksViewHolder> {

    Context context;
    ArrayList<TestMarksData> testMarksDataArrayList;


    public TestMarksAdapter(Context context, ArrayList<TestMarksData> testMarksDataArrayList) {
        this.context = context;
        this.testMarksDataArrayList = testMarksDataArrayList;
    }

    @NonNull
    @Override
    public TestMarksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.test_marks_list,parent,false);
        return new TestMarksViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TestMarksViewHolder holder, int position) {
        holder.tvModule.setText(testMarksDataArrayList.get(position).getModule());
        holder.tvDate.setText(testMarksDataArrayList.get(position).getExam_date());
        holder.tvTotalMarks.setText(testMarksDataArrayList.get(position).getTotal_marks());
        holder.tvObtainMarks.setText(testMarksDataArrayList.get(position).getObtain_marks());
        String que_url = WebUrl.PAPER_URL+testMarksDataArrayList.get(position).getExam_detail_id()+"/"+testMarksDataArrayList.get(position).getQue_paper();
        String ans_url = WebUrl.PAPER_URL+testMarksDataArrayList.get(position).getExam_detail_id()+"/"+testMarksDataArrayList.get(position).getAns_key();

        holder.buttonAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, ans_url, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PdfViewActivity.class);
                intent.putExtra("pdfUrl",ans_url);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.buttonQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, que_url, Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(context, PdfViewActivity.class);
//                intent.putExtra("pdfUrl",que_url);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                context.startActivity(intent);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(que_url));
                context.startActivity(intent);
            }
        });

        if(position % 2 == 1){

            holder.testCard.setCardBackgroundColor(Color.parseColor("#E7F3FC"));
            holder.buttonQue.setBackgroundResource(R.drawable.text_btn_1);
            holder.buttonAns.setBackgroundResource(R.drawable.text_btn_1);

            holder.buttonQue.setTextColor(Color.parseColor("#19232B"));
            holder.buttonAns.setTextColor(Color.parseColor("#19232B"));



        }else {
            holder.testCard.setCardBackgroundColor(Color.parseColor("#FEEEE2"));

            holder.buttonQue.setBackgroundResource(R.drawable.text_btn_2);
            holder.buttonAns.setBackgroundResource(R.drawable.text_btn_2);
            holder.buttonQue.setTextColor(Color.parseColor("#EF8534"));
            holder.buttonAns.setTextColor(Color.parseColor("#EF8534"));

        }

    }

    @Override
    public int getItemCount() {
        return testMarksDataArrayList.size();
    }

    public static final class TestMarksViewHolder extends RecyclerView.ViewHolder{

        TextView tvModule,tvDate,tvTotalMarks,tvObtainMarks,buttonQue,buttonAns;
        CardView testCard;

        public TestMarksViewHolder(@NonNull View itemView) {

            super(itemView);
            tvModule = itemView.findViewById(R.id.tvModule);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTotalMarks = itemView.findViewById(R.id.tvTotalMarks);
            tvObtainMarks = itemView.findViewById(R.id.tvObtainMarks);
            testCard = itemView.findViewById(R.id.testCard);
            buttonAns = itemView.findViewById(R.id.buttonAns);
            buttonQue = itemView.findViewById(R.id.buttonQue);

        }
    }

}
