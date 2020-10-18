package com.jcupzz.mycoviddiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Covid_Today extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid__today);

        webView = findViewById(R.id.web_id);

        WebView web = (WebView) findViewById(R.id.web_id);
        web.loadUrl("https://dashboard.kerala.gov.in/");


    }
}