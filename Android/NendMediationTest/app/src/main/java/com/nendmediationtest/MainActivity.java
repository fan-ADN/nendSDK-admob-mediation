package com.nendmediationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    protected static final String LOG_TAG = "AdMobMediationTest";
    private Class[] sampleMenus = {
        BannerActivity.class,
        InterstitialActivity.class,
        VideoInterstitialActivity.class
    };
    private String[] sampleMenusName = {
            "Banner",
            "Interstitial",
            "Video Interstitial"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupListView();
    }

    private void setupListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sampleMenusName);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, sampleMenus[position]));
            }
        });
        listView.setAdapter(adapter);
    }
}
