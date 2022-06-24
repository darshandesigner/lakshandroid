package com.ielts.lakshacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookMaterialActivity extends AppCompatActivity {

    Button btnVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_material);

        btnVideos = findViewById(R.id.btnVideos);
        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookMaterialActivity.this,VideoMaterialActivity.class);
                startActivity(intent);
            }
        });

    }
}