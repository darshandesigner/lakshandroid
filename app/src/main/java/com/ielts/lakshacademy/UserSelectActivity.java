package com.ielts.lakshacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class UserSelectActivity extends AppCompatActivity {

    CardView student_card,trainer_card,counselor_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);

        student_card = findViewById(R.id.student_card);
        trainer_card = findViewById(R.id.trainer_card);
        counselor_card = findViewById(R.id.counselor_card);

//        SessionManager sessionManager = new SessionManager(UserSelectActivity.this,SessionManager.REMEMBER_ME);
//        if(sessionManager.checkLogin()){
//
//            HashMap<String,String> rememberMeDetail = sessionManager.getStudentDataFromSession();
//            Intent intent = new Intent(UserSelectActivity.this,HomeActivity.class);
//            startActivity(intent);
//        }

        student_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSelectActivity.this,StudentLoginActivity.class);
                startActivity(intent);
            }
        });


        trainer_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent1 = new Intent(UserSelectActivity.this,TrainerLoginActivity.class);
                Intent intent1 = new Intent(UserSelectActivity.this,HomeActivity.class);

                startActivity(intent1);
            }
        });


        counselor_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(UserSelectActivity.this,CounselorLoginActivity.class);
                startActivity(intent2);
            }
        });

    }
}