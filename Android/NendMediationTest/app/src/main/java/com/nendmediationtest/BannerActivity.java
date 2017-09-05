package com.nendmediationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class BannerActivity extends AppCompatActivity implements View.OnClickListener {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        // バナー作成
        mAdView = (AdView) findViewById(R.id.adView);

        // admobリスナを設定
        mAdView.setAdListener(new AdListener() {
            /** Called when the user is about to return to the application after clicking on an ad. */
            @Override
            public void onAdClosed() {
                Log.d(MainActivity.LOG_TAG, "onAdClosed");
                Toast.makeText(BannerActivity.this, "onAdClosed", Toast.LENGTH_SHORT).show();
            }

            /** Called when an ad failed to load. */
            @Override
            public void onAdFailedToLoad(int error) {
                String message = "onAdFailedToLoad:" + AdUtil.getErrorReason(error);
                Log.d(MainActivity.LOG_TAG, message);
                Toast.makeText(BannerActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an ad is clicked and going to start a new Activity that will
             * leave the application (e.g. breaking out to the Browser or Maps
             * application).
             */
            @Override
            public void onAdLeftApplication() {
                Log.d(MainActivity.LOG_TAG, "onAdLeftApplication");
                Toast.makeText(BannerActivity.this, "onAdLeftApplication", Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an Activity is created in front of the app (e.g. an
             * interstitial is shown, or an ad is clicked and launches a new Activity).
             */
            @Override
            public void onAdOpened() {
                Log.d(MainActivity.LOG_TAG, "onAdOpened");
                Toast.makeText(BannerActivity.this, "onAdOpened", Toast.LENGTH_SHORT).show();
            }

            /** Called when an ad is loaded. */
            @Override
            public void onAdLoaded() {
                Log.d(MainActivity.LOG_TAG, "onAdLoaded");
                Toast.makeText(BannerActivity.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
            }
        });
        displayBanner();

        findViewById(R.id.button_bunner).setOnClickListener(this);
    }

    @Override
    public void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdView.resume();
    }

    @Override
    public void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        displayBanner();
    }

    public void displayBanner(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // エミュレータテスト用
                .addTestDevice("YOUR_DEVICE_ID") // 実機テスト用
                .build();
        mAdView.loadAd(adRequest);
    }

}
