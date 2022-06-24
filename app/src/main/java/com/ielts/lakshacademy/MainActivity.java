package com.ielts.lakshacademy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int currentYear = 0;
    private int currentMonth = 0;
    private int currentDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = findViewById(R.id.calenderView);
        TextView tvSelectDate = findViewById(R.id.tvSelectDate);
        EditText etWrite = findViewById(R.id.etWrite);
        Button btnSave = findViewById(R.id.btnSave);

        List<String> calenderString = new ArrayList<>();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                tvSelectDate.setText(String.valueOf(dayOfMonth));
                tvSelectDate.setText(String.valueOf(month));
                tvSelectDate.setText(String.valueOf(year));

                currentDay = dayOfMonth;
                currentMonth = month;
                currentYear = year;

                long saveDate = Long.parseLong(calenderString.get(0));
                if(view.getDate() == saveDate){
                    etWrite.setText(calenderString.get(1));
                }
                view.getDate();

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calenderString.add(String.valueOf(calendarView.getDate()));

                calenderString.add(etWrite.getText().toString());
                etWrite.setText("");
            }
        });
    }
}