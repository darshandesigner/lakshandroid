package com.ielts.lakshacademy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    RelativeLayout rlTestReport,rlAttendance,rlMaterials,rlExamUpdate,rlCounselor,rlCertificate;
    FrameLayout update_icon;

    //Drawer Menu
    DrawerLayout drawer_layout;
    NavigationView nav_view;
    ImageView navigation_icon;

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

//                String pdf_url = "http://www.bwrs.co.nz/content/docs/signatures/marc.karapanovic.pdf";
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
//                startActivity(browserIntent);

                Intent intent = new Intent(HomeActivity.this,CertificateActivity.class);
                startActivity(intent);
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


        }

        return true;
    }
}