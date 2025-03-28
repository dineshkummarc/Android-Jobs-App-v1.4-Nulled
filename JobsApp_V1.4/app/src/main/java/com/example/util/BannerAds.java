package com.example.util;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.ixidev.gdpr.GDPRChecker;
import com.wortise.ads.banner.BannerAd;

public class BannerAds {
    public static void ShowBannerAds(Context context, LinearLayout mAdViewLayout) {
        if (Constant.isBanner) {
            switch (Constant.bannerAdType) {
                case "admob":
                    AdView mAdView = new AdView(context);
                    mAdView.setAdSize(AdSize.BANNER);
                    mAdView.setAdUnitId(Constant.bannerId);
                    AdRequest.Builder builder = new AdRequest.Builder();
                    GDPRChecker.Request request = GDPRChecker.getRequest();
                    if (request == GDPRChecker.Request.NON_PERSONALIZED) {
                        // load non Personalized ads
                        Bundle extras = new Bundle();
                        extras.putString("npa", "1");
                        builder.addNetworkExtrasBundle(AdMobAdapter.class, extras);
                    } // else do nothing , it will load PERSONALIZED ads
                    mAdView.loadAd(builder.build());
                    mAdViewLayout.addView(mAdView);
                    mAdViewLayout.setGravity(Gravity.CENTER);
                    break;
                case "facebook":
                    com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, Constant.bannerId, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                    adView.loadAd();
                    mAdViewLayout.addView(adView);
                    mAdViewLayout.setGravity(Gravity.CENTER);
                    break;
                case "wortise":
                    BannerAd mBannerAd = new BannerAd(context);
                    mBannerAd.setAdSize(com.wortise.ads.AdSize.HEIGHT_50);
                    mBannerAd.setAdUnitId(Constant.bannerId);
                    mBannerAd.loadAd();
                    mAdViewLayout.addView(mBannerAd);
                    mAdViewLayout.setGravity(Gravity.CENTER);
                    break;
            }
        } else {
            mAdViewLayout.setVisibility(View.GONE);
        }
    }
}
