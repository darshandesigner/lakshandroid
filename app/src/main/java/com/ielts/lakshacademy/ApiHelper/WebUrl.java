package com.ielts.lakshacademy.ApiHelper;

public class WebUrl {



    private static final String IP_ADDRESS = "http://192.168.1.15:8000/";
    private static final String KEY_MAIN_URL = "http://192.168.1.15:8000/api/";


    public static final String LOGIN_URL = KEY_MAIN_URL+"login";
    public static final String TEST_SCORE_DATE_URL = KEY_MAIN_URL+"testscoredate";
    public static final String TEST_SCORE_URL = KEY_MAIN_URL+"testscore/";
    public static final String LECTURE_COUNT_URL = KEY_MAIN_URL+"lecture/";
    public static final String UPDATE_URL = KEY_MAIN_URL+"update/";
    public static final String PAPER_URL = IP_ADDRESS+"storage/exams/";
    public static final String COUNSELOR_URL = KEY_MAIN_URL+"counselor/";
    public static final String BOOK_MATERIAL_URL = KEY_MAIN_URL+"bookmaterial";
    public static final String BOOK_PATH = IP_ADDRESS+"storage/material/";
    public static final String VIDEO_MATERIAL_URL = KEY_MAIN_URL+"videomaterial";
    public static final String VIEW_CERTIFICATE_URL = KEY_MAIN_URL+"certificate/";
    public static final String CERTIFICATE_URL = IP_ADDRESS+"storage/student/";


}
