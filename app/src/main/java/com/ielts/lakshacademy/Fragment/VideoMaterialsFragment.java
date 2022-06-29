package com.ielts.lakshacademy.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ielts.lakshacademy.Adapter.VideoAdapter;
import com.ielts.lakshacademy.ApiHelper.JsonField;
import com.ielts.lakshacademy.ApiHelper.WebUrl;
import com.ielts.lakshacademy.Model.VideoData;
import com.ielts.lakshacademy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoMaterialsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoMaterialsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VideoMaterialsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoMaterialsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoMaterialsFragment newInstance(String param1, String param2) {
        VideoMaterialsFragment fragment = new VideoMaterialsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView rvVideo;
    VideoAdapter videoAdapter;
    ArrayList<VideoData> arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_materials, container, false);
        getVideoData();
        return view;
    }

    private void getVideoData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, WebUrl.VIDEO_MATERIAL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJSON(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void parseJSON(String toString) {

        try {
            JSONObject jsonObject = new JSONObject(toString);
            int flag = jsonObject.optInt(JsonField.FLAG);
            if(flag == 1)
            {
                JSONArray jsonArray = jsonObject.getJSONArray(JsonField.VIDEO_MATERIAL_ARRAY);
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String video_name = object.getString(JsonField.VIDEO_NAME);
                    String video_url = object.getString(JsonField.VIDEO_URL);

                    arrayList = new ArrayList<>();
                    VideoData videoData = new VideoData();
                    videoData.setVideoName(video_name);
                    videoData.setVideoUrl(video_url);

                    arrayList.add(videoData);
                }
                setVideoData(arrayList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setVideoData(ArrayList<VideoData> arrayList) {

        rvVideo = getView().findViewById(R.id.rvVideo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvVideo.setLayoutManager(layoutManager);

        videoAdapter = new VideoAdapter(getContext(),arrayList);
        rvVideo.setAdapter(videoAdapter);
    }
}