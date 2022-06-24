package com.ielts.lakshacademy;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences studentSession;
    SharedPreferences.Editor editor;
    Context context;

    //session name
    public static final String STUDENT_LOGIN_KEY = "studentLoginSession";
    public static final String REMEMBER_ME = "remember_me";

    //session variable
    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String BATCH_ID = "batch_id";

    public SessionManager(Context context,String sessionName){
        this.context = context;
        studentSession = context.getSharedPreferences(sessionName,Context.MODE_PRIVATE);
        editor = studentSession.edit();

    }

    public void createLoginSession(String id,String email,String batch_id){

        editor.putBoolean(IS_LOGIN,true);
        editor.putString(ID,id);
        editor.putString(EMAIL,email);
        editor.putString(BATCH_ID,batch_id);
        editor.commit();

    }

    public HashMap<String,String> getStudentDataFromSession(){

        HashMap<String,String> studentData = new HashMap<>();
        studentData.put(ID,studentSession.getString(ID,null));
        studentData.put(EMAIL,studentSession.getString(EMAIL,null));
        studentData.put(BATCH_ID,studentSession.getString(BATCH_ID,null));

        return studentData;
    }

    public boolean checkLogin(){
        if(studentSession.getBoolean(IS_LOGIN,false)){
            return true;

        }else {
            return false;
        }
    }

    public void logoutAdminSession(){
        editor.clear();
        editor.commit();

    }

}
