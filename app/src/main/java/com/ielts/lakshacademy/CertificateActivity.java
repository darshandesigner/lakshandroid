package com.ielts.lakshacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class CertificateActivity extends AppCompatActivity {

    PDFView pdfView;
    String certificateUrl = "http://www.bwrs.co.nz/content/docs/signatures/marc.karapanovic.pdf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);

        Uri uri = Uri.parse(certificateUrl);
        pdfView = findViewById(R.id.certificatePdf);
        pdfView.fromUri(uri);


    }


}