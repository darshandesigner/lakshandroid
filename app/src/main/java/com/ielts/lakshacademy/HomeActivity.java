package com.ielts.lakshacademy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.ielts.lakshacademy.ApiHelper.JsonField;
import com.ielts.lakshacademy.ApiHelper.WebUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    RelativeLayout rlTestReport,rlAttendance,rlMaterials,rlExamUpdate,rlCounselor,rlCertificate;
    FrameLayout update_icon;

    //Drawer Menu
    DrawerLayout drawer_layout;
    NavigationView nav_view;
    ImageView navigation_icon;
    SessionManager sessionManager;
    HashMap<String,String> studentDetail;
    String student_id,name,unique_id;
    TextView tvStudentId,tvStudentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Menu Hooks
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        navigation_icon = findViewById(R.id.navigation_icon);

        navigationDrawer();

        //Main Option Hooks
        rlTestReport = findViewById(R.id.rlTestReport);
        update_icon = findViewById(R.id.update_icon);
        rlExamUpdate = findViewById(R.id.rlExamUpdate);
        rlAttendance = findViewById(R.id.rlAttendance);
        rlMaterials = findViewById(R.id.rlMaterials);
        rlCounselor = findViewById(R.id.rlCounselor);
        rlCertificate = findViewById(R.id.rlCertificate);

        tvStudentId = findViewById(R.id.tvStudentId);
        tvStudentName = findViewById(R.id.tvStudentName);



        sessionManager = new SessionManager(HomeActivity.this,SessionManager.STUDENT_LOGIN_KEY);
        studentDetail = sessionManager.getStudentDataFromSession();
        student_id = studentDetail.get(SessionManager.ID);
        unique_id = studentDetail.get(SessionManager.UNIQUE_ID);
        name = studentDetail.get(SessionManager.NAME);
      
        tvStudentId.setText("ID: "+unique_id);
        tvStudentName.setText(name);
        rlMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,MaterialsActivity.class);
                startActivity(intent);
            }
        });

        rlCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.GET, WebUrl.VIEW_CERTIFICATE_URL+student_id, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int flag = jsonObject.optInt(JsonField.FLAG);
                            if(flag == 1){
                                String certificate = WebUrl.CERTIFICATE_URL+student_id+"/" + jsonObject.optString(JsonField.CERTIFCATE);
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(certificate));
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
//                String pdf_url = "http://www.bwrs.co.nz/content/docs/signatures/marc.karapanovic.pdf";
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
//                startActivity(browserIntent);
                RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);
                requestQueue.add(stringRequest);

            }
        });

        rlCounselor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,CounselorActivity.class);
                startActivity(intent);
            }
        });

        rlAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AttendanceActivity.class);
                startActivity(intent);
            }
        });

        rlExamUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ExamUpdateActivity.class);
                startActivity(intent);
            }
        });

        rlTestReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,TestMarksActivity.class);
                startActivity(intent);
            }
        });

        update_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,UpdateActivity.class);
                startActivity(intent);
            }
        });

    }


    //Navigation Drawer
    private void navigationDrawer() {

        //Navigation Drawer
        nav_view.bringToFront();
        nav_view.setNavigationItemSelectedListener(this);
        nav_view.setCheckedItem(R.id.homeItem);

        navigation_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawer_layout.isDrawerVisible(GravityCompat.START)){
                    drawer_layout.closeDrawer(GravityCompat.START);

                }else {
                    drawer_layout.openDrawer(GravityCompat.START);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawer_layout.isDrawerVisible(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START);

        }else{

        super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.testScoreItem:
                Intent intent = new Intent(HomeActivity.this,TestMarksActivity.class);
                startActivity(intent);
                break;

            case R.id.logoutItem:
                SessionManager sessionManager = new SessionManager(HomeActivity.this,SessionManager.STUDENT_LOGIN_KEY);
                sessionManager.logoutAdminSession();

                Intent intent1 = new Intent(HomeActivity.this,UserSelectActivity.class);
                startActivity(intent1);
                break;

            case R.id.profileItem:
                Intent intent2 = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent2);
                break;


        }

        return true;
    }
}