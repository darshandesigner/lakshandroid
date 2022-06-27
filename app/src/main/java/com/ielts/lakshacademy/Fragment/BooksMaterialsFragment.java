package com.ielts.lakshacademy.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ielts.lakshacademy.Adapter.BookAdapter;
import com.ielts.lakshacademy.ApiHelper.JsonField;
import com.ielts.lakshacademy.ApiHelper.WebUrl;
import com.ielts.lakshacademy.Model.BookData;
import com.ielts.lakshacademy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BooksMaterialsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BooksMaterialsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BooksMaterialsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BooksMaterialsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BooksMaterialsFragment newInstance(String param1, String param2) {
        BooksMaterialsFragment fragment = new BooksMaterialsFragment();
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

    RecyclerView bookList;
    BookAdapter bookAdapter;
    ArrayList<BookData> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_books_materials, container, false);




//        arrayList = new ArrayList<>();
//
//        arrayList.add(new BookData("Book Name","book url","29 nov 2022"));
//        arrayList.add(new BookData("Book Name","book url","29 nov 2022"));
//        arrayList.add(new BookData("Book Name","book url","29 nov 2022"));

        getBookData(view);
//        setBookData(arrayList,view);

        return view;
    }

    private void getBookData(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, WebUrl.BOOK_MATERIAL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJSON(response.toString(),view);
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

    private void parseJSON(String toString,View view) {

        try {
            JSONObject jsonObject = new JSONObject(toString);
            int flag = jsonObject.optInt(JsonField.FLAG);
            if(flag == 1)
            {
                JSONArray jsonArray = jsonObject.getJSONArray(JsonField.BOOK_MATERIAL_ARRAY);
                if(jsonArray.length() > 0)
                {
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject object =  jsonArray.getJSONObject(i);
                        String book_name = object.getString(JsonField.BOOK_NAME);
                        String pdf_name = object.getString(JsonField.Book_URL);
                        String book_date = object.getString(JsonField.BOOK_DATE);

                        arrayList = new ArrayList<>();
                        BookData bookData = new BookData();
                        bookData.setBookName(book_name);
                        bookData.setBookUrl(pdf_name);
                        bookData.setCreatedDate(book_date);

                        arrayList.add(bookData);
                    }
                    setBookData(arrayList,view);
                }
            }
            else
            {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setBookData(ArrayList<BookData> arrayList, View view) {
        bookList = view.findViewById(R.id.bookList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        bookList.setLayoutManager(layoutManager);

        bookAdapter = new BookAdapter(getContext(),arrayList);
        bookList.setAdapter(bookAdapter);


    }


}