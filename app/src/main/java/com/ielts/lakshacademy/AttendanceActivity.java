package com.ielts.lakshacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ielts.lakshacademy.Adapter.AttendanceAdapter;
import com.ielts.lakshacademy.ApiHelper.JsonField;
import com.ielts.lakshacademy.ApiHelper.WebUrl;
import com.ielts.lakshacademy.Model.AttendanceData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {

    TextView listingTotalText,readingTotalText,writingTotalText,speakingTotalText,listingPresentText,readingPresentText,writingPresentText,speakingPresentText,listingAbsentText,readingAbsentText,writingAbsentText,speakingAbsentText;
    RecyclerView attendanceList;
    AttendanceAdapter attendanceAdapter;
    ArrayList<AttendanceData> attendanceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        listingTotalText = findViewById(R.id.listingTotal);
        listingPresentText = findViewById(R.id.listingPresent);
        listingAbsentText = findViewById(R.id.listingAbsent);
        speakingTotalText = findViewById(R.id.speakingTotal);
        speakingPresentText = findViewById(R.id.speakingPresent);
        speakingAbsentText = findViewById(R.id.speakingAbsent);
        readingTotalText = findViewById(R.id.readingTotal);
        readingPresentText = findViewById(R.id.readingPresent);
        readingAbsentText = findViewById(R.id.readingAbsent);
        writingPresentText = findViewById(R.id.writingPresent);
        writingTotalText = findViewById(R.id.writingTotal);
        writingAbsentText = findViewById(R.id.writingAbsent);

        attendanceData = new ArrayList<>();

        attendanceData.add(new AttendanceData("02 Apr","P","A","P","A"));
        attendanceData.add(new AttendanceData("02 Apr","P","A","P","A"));
        attendanceData.add(new AttendanceData("02 Apr","P","A","P","A"));
        attendanceData.add(new AttendanceData("02 Apr","P","A","P","A"));
        attendanceData.add(new AttendanceData("02 Apr","P","A","P","A"));
        attendanceData.add(new AttendanceData("02 Apr","P","A","P","A"));
        attendanceData.add(new AttendanceData("02 Apr","P","A","P","A"));

        setAttendanceDate(attendanceData);
        getLectureCount();


    }

    private void setAttendanceDate(ArrayList<AttendanceData> attendanceData) {
        attendanceList = findViewById(R.id.attendanceList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        attendanceList.setLayoutManager(layoutManager);
        attendanceAdapter = new AttendanceAdapter(this,attendanceData);
        attendanceList.setAdapter(attendanceAdapter);
    }

    private void getLectureCount() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, WebUrl.LECTURE_COUNT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showLectureCount(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(AttendanceActivity.this);
        requestQueue.add(stringRequest);

    }

    private void showLectureCount(String toString) {

        try {
            JSONObject jsonObject = new JSONObject(toString);
            int flag = jsonObject.optInt(JsonField.FLAG);

            if(flag == 1){

                String listingTotal = jsonObject.optString(JsonField.TOTAL_LISTING_LECTURE);
                String listingPresent = jsonObject.optString(JsonField.PRESENT_LISTING_LECTURE);
                int listingAbsentInt = Integer.parseInt(listingTotal) - Integer.parseInt(listingPresent);
                String listingAbsent = String.valueOf(listingAbsentInt);

                String readingTotal = jsonObject.optString(JsonField.TOTAL_READING_LECTURE);
                String readingPresent = jsonObject.optString(JsonField.PRESENT_READING_LECTURE);
                int readingAbsentInt = Integer.parseInt(readingTotal) - Integer.parseInt(readingPresent);
                String readingAbsent = String.valueOf(listingAbsentInt);

                String writingTotal = jsonObject.optString(JsonField.TOTAL_WRITING_LECTURE);
                String writingPresent = jsonObject.optString(JsonField.PRESENT_WRITING_LECTURE);
                int writingAbsentInt = Integer.parseInt(writingTotal) - Integer.parseInt(writingPresent);
                String writingAbsent = String.valueOf(listingAbsentInt);

                String speakingTotal = jsonObject.optString(JsonField.TOTAL_SPEAKING_LECTURE);
                String speakingPresent = jsonObject.optString(JsonField.PRESENT_SPEAKING_LECTURE);
                int speakingAbsentInt = Integer.parseInt(speakingTotal) - Integer.parseInt(speakingPresent);
                String speakingAbsent = String.valueOf(listingAbsentInt);

                listingTotalText.setText(listingTotal);
                listingPresentText.setText(listingPresent);
                listingAbsentText.setText(listingAbsent);

                readingTotalText.setText(readingTotal);
                readingPresentText.setText(readingPresent);
                readingAbsentText.setText(readingAbsent);

                writingTotalText.setText(writingTotal);
                writingPresentText.setText(writingPresent);
                writingAbsentText.setText(writingAbsent);

                speakingTotalText.setText(speakingTotal);
                speakingPresentText.setText(speakingPresent);
                speakingAbsentText.setText(speakingAbsent);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}