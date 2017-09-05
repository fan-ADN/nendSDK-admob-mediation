package com.nendmediationtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class InterstitialActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        mInterstitialAd = createInterstitialAd(this, getString(R.string.interstitial_ad_unit_id));
        findViewById(R.id.button_interstitial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    mInterstitialAd = createInterstitialAd(InterstitialActivity.this, getString(R.string.interstitial_ad_unit_id));
                }
            }
        });
    }

    private InterstitialAd createInterstitialAd(final Context context, final String unitId) {
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR); // エミュレータテスト用
        adRequestBuilder.addTestDevice("YOUR_DEVICE_ID"); // 実機テスト用

        AdRequest adRequest = adRequestBuilder.build();
        // インタースティシャル作成
        InterstitialAd interstitial = new InterstitialAd(context);
        interstitial.setAdUnitId(unitId);
        interstitial.loadAd(adRequest);

        // admobリスナを設定
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(MainActivity.LOG_TAG, "onAdLoaded_interstitial");
                Toast.makeText(context, "onAdLoaded_interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("LOG_TAG","errorCode:"+ errorCode);
                String message = String.format("onAdFailedToLoad_interstitial (%s)", AdUtil.getErrorReason(errorCode));
                Log.d(MainActivity.LOG_TAG, message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened(){
                Log.d(MainActivity.LOG_TAG, "onAdOpened_interstitial");
                Toast.makeText(context, "onAdOpened_interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed(){
                Log.d(MainActivity.LOG_TAG, "onAdClosed_interstitial");
                Toast.makeText(context, "onAdClosed_interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication(){
                Log.d(MainActivity.LOG_TAG, "onAdLeftApplication_interstitial");
                Toast.makeText(context, "onAdLeftApplication_interstitial", Toast.LENGTH_SHORT).show();
            }
        });

        return interstitial;
    }
}
