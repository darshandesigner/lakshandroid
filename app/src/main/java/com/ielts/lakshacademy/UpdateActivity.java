package com.ielts.lakshacademy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ielts.lakshacademy.Adapter.UpdatesAdapter;
import com.ielts.lakshacademy.ApiHelper.JsonField;
import com.ielts.lakshacademy.ApiHelper.WebUrl;
import com.ielts.lakshacademy.Model.UpdatesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    RecyclerView rvUpdates;
    ArrayList<UpdatesData> updatesDataArrayList;
    UpdatesAdapter updatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getUpdateDate();
        updatesDataArrayList = new ArrayList<>();

//        updatesDataArrayList.add(new UpdatesData("20 Apr 2020","10:14 AM","Lorem Ipsum is simply industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make "));
//        updatesDataArrayList.add(new UpdatesData("29 Apr 2020","10:14 AM","Lorem Ipsum is simply industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make "));
//        updatesDataArrayList.add(new UpdatesData("02 May 2020","11:15 AM","Lorem Ipsum is simply industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make "));
//        updatesDataArrayList.add(new UpdatesData("20 May 2020","01:14 PM","Lorem Ipsum is simply industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make "));
//        updatesDataArrayList.add(new UpdatesData("20 Jun 2020","05:14 PM","Lorem Ipsum is simply industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make "));
//        updatesDataArrayList.add(new UpdatesData("29 Jun 2020","07:14 AM","Lorem Ipsum is simply industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make "));
//
//        setUpdateData(updatesDataArrayList);

    }

    private void getUpdateDate() {

        SessionManager sessionManager = new SessionManager(UpdateActivity.this,SessionManager.STUDENT_LOGIN_KEY);
        HashMap<String,String> studentSessionDetail = sessionManager.getStudentDataFromSession();
        String batch_id = studentSessionDetail.get(SessionManager.BATCH_ID);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, WebUrl.UPDATE_URL+batch_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                parseJson(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        });

    }

    private void parseJson(String toString) {

        try {
            JSONObject jsonObject = new JSONObject(toString);
            int flag = jsonObject.optInt(JsonField.FLAG);
            if(flag == 1){
                JSONArray jsonArray = jsonObject.optJSONArray(JsonField.UPDATES);
                if(jsonArray.length()>0){

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String update_description = object.getString(JsonField.LECTURE_UPDATE);
                        String update_datetime = object.getString(JsonField.UPDATE_DATE_TIME);
                        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Date datetimeForm =  datetime.parse(update_datetime);
                        SimpleDateFormat dt = new SimpleDateFormat("dd MMM yyyy");
                        String update_date = dt.format(datetimeForm);
                        SimpleDateFormat dt2 = new SimpleDateFormat("HH:mm aa");
                        String update_time = dt2.format(datetimeForm);

                        UpdatesData updatesData = new UpdatesData();
                        updatesData.setDesc(update_description);
                        updatesData.setDate(update_date);
                        updatesData.setDate(update_time);
                        updatesDataArrayList.add(updatesData);

                    }

                    setUpdateData(updatesDataArrayList);

                }
            }

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }

    }

    private void setUpdateData(ArrayList<UpdatesData> updatesDataArrayList) {

        rvUpdates = findViewById(R.id.rvUpdates);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvUpdates.setLayoutManager(layoutManager);
        updatesAdapter = new UpdatesAdapter(this,updatesDataArrayList);
        rvUpdates.setAdapter(updatesAdapter);

    }
}