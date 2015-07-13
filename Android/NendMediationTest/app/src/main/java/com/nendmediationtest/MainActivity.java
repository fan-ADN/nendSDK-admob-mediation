package com.nendmediationtest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String LOG_TAG = "AdMobMediationTest";

    private AdView mAdView;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // バナー作成
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // エミュレータテスト用
//                .addTestDevice("") // 実機テスト用
                .build();
        mAdView.loadAd(adRequest);

        // admobリスナを設定
        mAdView.setAdListener(new AdListener() {
            /** Called when the user is about to return to the application after clicking on an ad. */
            @Override
            public void onAdClosed() {
                Log.d(LOG_TAG, "onAdClosed");
                Toast.makeText(MainActivity.this, "onAdClosed", Toast.LENGTH_SHORT).show();
            }

            /** Called when an ad failed to load. */
            @Override
            public void onAdFailedToLoad(int error) {
                String message = "onAdFailedToLoad:" + getErrorReason(error);
                Log.d(LOG_TAG, message);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an ad is clicked and going to start a new Activity that will
             * leave the application (e.g. breaking out to the Browser or Maps
             * application).
             */
            @Override
            public void onAdLeftApplication() {
                Log.d(LOG_TAG, "onAdLeftApplication");
                Toast.makeText(MainActivity.this, "onAdLeftApplication", Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an Activity is created in front of the app (e.g. an
             * interstitial is shown, or an ad is clicked and launches a new Activity).
             */
            @Override
            public void onAdOpened() {
                Log.d(LOG_TAG, "onAdOpened");
                Toast.makeText(MainActivity.this, "onAdOpened", Toast.LENGTH_SHORT).show();
            }

            /** Called when an ad is loaded. */
            @Override
            public void onAdLoaded() {
                Log.d(LOG_TAG, "onAdLoaded");
                Toast.makeText(MainActivity.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
            }
        });

        // インタースティシャル作成
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitial.loadAd(adRequest);

        // admobリスナを設定
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(LOG_TAG, "onAdLoaded_interstitial");
                Toast.makeText(MainActivity.this, "onAdLoaded_interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("LOG_TAG","errorCode:"+ errorCode);
                String message = String.format("onAdFailedToLoad_interstitial (%s)", getErrorReason(errorCode));
                Log.d(LOG_TAG, message);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened(){
                Log.d(LOG_TAG, "onAdOpened_interstitial");
                Toast.makeText(MainActivity.this, "onAdOpened_interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed(){
                Log.d(LOG_TAG, "onAdClosed_interstitial");
                Toast.makeText(MainActivity.this, "onAdClosed_interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication(){
                Log.d(LOG_TAG, "onAdLeftApplication_interstitial");
                Toast.makeText(MainActivity.this, "onAdLeftApplication_interstitial", Toast.LENGTH_SHORT).show();
            }

        });

        Button bt_show_banner = (Button)this.findViewById(R.id.button);
        bt_show_banner.setOnClickListener(this);
        bt_show_banner.setTag("banner");

        Button bt_show_interstitial = (Button)this.findViewById(R.id.button2);
        bt_show_interstitial.setOnClickListener(this);
        bt_show_interstitial.setTag("interstitial");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        String str = (String)v.getTag();

        if(str.equals("banner")){
            displayBanner();
        }else if (str.equals("interstitial")){
            displayInterstitial();
        }else{

        }
    }

    public void displayBanner(){

      AdRequest adRequest = new AdRequest.Builder()
      .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // エミュレータテスト用
//      .addTestDevice("") // 実機テスト用
      .build();
        mAdView.loadAd(adRequest);
    }

    public void displayInterstitial() {

        if (interstitial.isLoaded()) {
            interstitial.show();
        }else{
            AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // エミュレータテスト用
//          .addTestDevice("") // 実機テスト用
          .build();
            interstitial.loadAd(adRequest);
            interstitial.show();
        }
    }

    /** Gets a string error reason from an error code. */
    private String getErrorReason(int errorCode) {
        String errorReason = "";
        switch(errorCode) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorReason = "Internal error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorReason = "Invalid request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorReason = "Network Error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                errorReason = "No fill";
                break;
        }
        return errorReason;
    }
}
