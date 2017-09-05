package com.nendmediationtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.ads.mediation.nend.NendAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class VideoInterstitialActivity extends AppCompatActivity {

    InterstitialAd mVideoInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_interstitial);

        mVideoInterstitialAd = createInterstitialAd(this, getString(R.string.interstitial_video_ad_unit_id));
        findViewById(R.id.button_video_interstitial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVideoInterstitialAd.isLoaded()) {
                    mVideoInterstitialAd.show();
                } else {
                    mVideoInterstitialAd = createInterstitialAd(VideoInterstitialActivity.this, getString(R.string.interstitial_video_ad_unit_id));
                }
            }
        });
    }

    private InterstitialAd createInterstitialAd(final Context context, final String unitId) {
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        Bundle bundle = new Bundle();
        bundle.putString(NendAdapter.KEY_USER_ID, "YOUR_USER_ID");
        // 動画インタースティシャル広告を使う場合は、こちらのExtrasBundleを有効化する
        bundle.putSerializable(NendAdapter.KEY_INTERSTITIAL_TYPE, NendAdapter.InterstitialType.TYPE_VIDEO);
        adRequestBuilder.addNetworkExtrasBundle(NendAdapter.class, bundle);

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
                Log.d(MainActivity.LOG_TAG, "onAdLoaded_video_interstitial");
                Toast.makeText(context, "onAdLoaded_video_interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("LOG_TAG","errorCode:"+ errorCode);
                String message = String.format("onAdFailedToLoad_video_interstitial (%s)", AdUtil.getErrorReason(errorCode));
                Log.d(MainActivity.LOG_TAG, message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened(){
                Log.d(MainActivity.LOG_TAG, "onAdOpened_video_interstitial");
                Toast.makeText(context, "onAdOpened_video_interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed(){
                Log.d(MainActivity.LOG_TAG, "onAdClosed_video_interstitial");
                Toast.makeText(context, "onAdClosed_video_interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication(){
                Log.d(MainActivity.LOG_TAG, "onAdLeftApplication_video_interstitial");
                Toast.makeText(context, "onAdLeftApplication_video_interstitial", Toast.LENGTH_SHORT).show();
            }
        });

        return interstitial;
    }
}
