package com.ielts.lakshacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ielts.lakshacademy.Adapter.CounselorAdapter;
import com.ielts.lakshacademy.ApiHelper.JsonField;
import com.ielts.lakshacademy.ApiHelper.WebUrl;
import com.ielts.lakshacademy.Model.CounselorData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CounselorActivity extends AppCompatActivity {
    
    RecyclerView counselorList;
    CounselorAdapter counselorAdapter;
    ArrayList<CounselorData> counselorDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counselor);

        counselorDataArrayList = new ArrayList<>();

        counselorDataArrayList.add(new CounselorData("url","Darshan","4"));
        setCounselorData(counselorDataArrayList);
        getCounselorData();
        
    }

    private void getCounselorData() {
        SessionManager sessionManager = new SessionManager(CounselorActivity.this,SessionManager.STUDENT_LOGIN_KEY);
        HashMap<String,String> studentSessionDetail = sessionManager.getStudentDataFromSession();
        String student_id = studentSessionDetail.get(SessionManager.ID);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, WebUrl.COUNSELOR_URL + student_id, new Response.Listener<String>() {
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

                JSONArray jsonArray = jsonObject.optJSONArray(JsonField.COUNSELOR_INFO_ARRAY);
                if(jsonArray.length() > 0){

                    for(int i=0;i<jsonArray.length();i++){

                        JSONObject object = jsonArray.getJSONObject(i);
                        String counselor_name = object.getString(JsonField.COUNSELOR_NAME);

                        CounselorData counselorData = new CounselorData();
                        counselorData.setName(counselor_name);

                        counselorDataArrayList.add(counselorData);
                    }
                    setCounselorData(counselorDataArrayList);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setCounselorData(ArrayList<CounselorData> counselorDataArrayList) {
        counselorList = findViewById(R.id.counselorList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        counselorList.setLayoutManager(layoutManager);
        counselorAdapter = new CounselorAdapter(this,counselorDataArrayList);
        counselorList.setAdapter(counselorAdapter);
    }
}