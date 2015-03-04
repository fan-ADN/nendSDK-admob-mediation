//
//  ViewController.h
//  NendSDK_AdMobMediationSample
//
//  Copyright (c) 2015年 F@N Communications. All rights reserved.
//

#import <UIKit/UIKit.h>
@import GoogleMobileAds;

@interface ViewController : UIViewController <GADBannerViewDelegate,GADInterstitialDelegate>{
    
    GADBannerView *bannerView_;
    GADInterstitial *interstitial_;
}
@end
