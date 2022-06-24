package com.ielts.lakshacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VideoMaterialActivity extends AppCompatActivity {

    Button btnBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_material);

        btnBooks = findViewById(R.id.btnBooks);
        btnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideoMaterialActivity.this,BookMaterialActivity.class);
                startActivity(intent);
            }
        });
    }
}