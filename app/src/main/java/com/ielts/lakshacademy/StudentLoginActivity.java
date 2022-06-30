package com.ielts.lakshacademy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ielts.lakshacademy.ApiHelper.JsonField;
import com.ielts.lakshacademy.ApiHelper.WebUrl;
import com.ielts.lakshacademy.Model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentLoginActivity extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        EditText etEmail,etPassword;
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email =etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(!email.isEmpty()) {

                    if(!password.isEmpty()){

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebUrl.LOGIN_URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d("api",response.toString());
                                parseJson(response.toString());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Log.d("api", String.valueOf(error));
                                if(error instanceof NetworkError){
                                    Toast.makeText(StudentLoginActivity.this, "network error", Toast.LENGTH_SHORT).show();

                                }else if(error instanceof ServerError){
                                    Toast.makeText(StudentLoginActivity.this, "server error", Toast.LENGTH_SHORT).show();

                                }else if(error instanceof AuthFailureError){
                                    Toast.makeText(StudentLoginActivity.this, "auth error", Toast.LENGTH_SHORT).show();

                                }else if(error instanceof ParseError){
                                    Toast.makeText(StudentLoginActivity.this, "parse error", Toast.LENGTH_SHORT).show();

                                }else if(error instanceof NoConnectionError){
                                    Toast.makeText(StudentLoginActivity.this, "no connection error", Toast.LENGTH_SHORT).show();

                                }else if(error instanceof TimeoutError){
                                    Toast.makeText(StudentLoginActivity.this, "time out error", Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(StudentLoginActivity.this, "ops", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                               String email = etEmail.getText().toString();
                               String password = etPassword.getText().toString();

                               Map<String,String> params = new HashMap<>();
                               params.put(JsonField.EMAIL,email);
                               params.put("password",password);
                                return params;
                            }
                        };

//                        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                        RequestQueue requestQueue = Volley.newRequestQueue(StudentLoginActivity.this);
                        requestQueue.add(stringRequest);

                    }

                }
            }
        });


    }

    private void parseJson(String toString) {

        try {
            JSONObject jsonObject = new JSONObject(toString);
            int flag = jsonObject.optInt(JsonField.FLAG);
            if(flag==1){

//                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                JSONObject object = jsonObject.getJSONObject(JsonField.STUDENT_ARRAY);
                String id = object.getString(JsonField.STUDENT_ID);
                String unique_id = object.getString(JsonField.UNIQUE_ID);
                String name = object.getString(JsonField.STU_NAME);
                String email = object.getString(JsonField.EMAIL);
                String contact = object.getString(JsonField.STU_CONTACT);
                String batch_id = jsonObject.getString(JsonField.BATCH_ID);
//                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();

                Student student = new Student();
                student.setStudent_id(id);
                student.setUnique_id(unique_id);
                student.setEmail(email);
                student.setStu_name(name);
                student.setStu_contact(contact);
//                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();


                SessionManager sessionManager = new SessionManager(StudentLoginActivity.this,SessionManager.STUDENT_LOGIN_KEY);
                sessionManager.createLoginSession(id,email,batch_id,unique_id,name);
//                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(StudentLoginActivity.this, HomeActivity.class);
                startActivity(intent);
//                Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();


            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "error hai bhai", Toast.LENGTH_SHORT).show();

        }
    }
}