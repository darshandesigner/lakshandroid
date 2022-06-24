package com.ielts.lakshacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

public class PdfViewActivity extends AppCompatActivity {

    String pdf_url;
//    PDFView pdfViewer;
    WebView pdfWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        pdfWebView = findViewById(R.id.pdfWebView);

        pdfWebView.getSettings().setJavaScriptEnabled(true);
        pdf_url = getIntent().getStringExtra("pdfUrl");

        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("PDF");
        pd.setMessage("Opening Pdf...");

        pdfWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(PdfViewActivity.this, "loading", Toast.LENGTH_SHORT).show();
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(PdfViewActivity.this, "loading done", Toast.LENGTH_SHORT).show();

                pd.dismiss();
            }
        });

        String url = "";
        try {

            url = URLEncoder.encode(pdf_url,"ISO-8859-1");
            Toast.makeText(this, url, Toast.LENGTH_SHORT).show();

        }catch (Exception ex){

        }

        pdfWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);


//        pdfViewer = findViewById(R.id.pdfViewer);

//        new pdfDownload().execute(pdf_url);
    }

//    private class pdfDownload extends AsyncTask<String,Void,InputStream>{
//
//        @Override
//        protected InputStream doInBackground(String... strings) {
//            InputStream inputStream = null;
//
//            try {
//                URL url = new URL(strings[0]);
//                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
//
//                if(httpsURLConnection.getResponseCode() == 100){
//
//                    inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());
//
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return  inputStream;
//        }
//
//        @Override
//        protected void onPostExecute(InputStream inputStream) {
////            pdfViewer.fromStream(inputStream).load();
//        }
//    }
}