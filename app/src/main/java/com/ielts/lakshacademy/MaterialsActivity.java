package com.ielts.lakshacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ielts.lakshacademy.Fragment.BooksMaterialsFragment;
import com.ielts.lakshacademy.Fragment.VideoMaterialsFragment;

public class MaterialsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnVideos,btnBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

        //btn hooks
        btnBooks = findViewById(R.id.btnBooks);
        btnVideos = findViewById(R.id.btnVideos);


        //btn listener
        btnBooks.setOnClickListener(this);
        btnVideos.setOnClickListener(this);

        activeVideo();



    }

    private void activeVideo() {

        VideoMaterialsFragment videoMaterialsFragment = new VideoMaterialsFragment();
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
        transaction1.replace(R.id.linearLayout,videoMaterialsFragment);
        transaction1.commit();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnBooks:
                BooksMaterialsFragment booksMaterialsFragment = new BooksMaterialsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.linearLayout,booksMaterialsFragment);
                transaction.commit();
                break;

            case R.id.btnVideos:
                VideoMaterialsFragment videoMaterialsFragment = new VideoMaterialsFragment();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.linearLayout,videoMaterialsFragment);
                transaction1.commit();
                break;


        }

    }
}