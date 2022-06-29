package com.ielts.lakshacademy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ielts.lakshacademy.ApiHelper.WebUrl;
import com.ielts.lakshacademy.Model.VideoData;
import com.ielts.lakshacademy.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    Context context;
    ArrayList<VideoData> arrayList;

    public VideoAdapter(Context context, ArrayList<VideoData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_list,parent,false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
            holder.videoName.setText(arrayList.get(position).getVideoName());
            String video_url = WebUrl.BOOK_PATH + arrayList.get(position).getVideoUrl();

            holder.videoName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(video_url);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setDataAndType(uri,"video/mp4");
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder{
    TextView videoName;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            videoName = itemView.findViewById(R.id.videoName);

        }
    }
}
