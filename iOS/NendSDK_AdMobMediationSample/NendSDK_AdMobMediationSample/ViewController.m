//
//  ViewController.m
//  NendSDK_AdMobMediationSample
//
//  Copyright (c) 2015年 F@N Communications. All rights reserved.
//

#import "ViewController.h"

#define AD_BANNER_UNIT_ID         @"Your unit id"
#define AD_INTERSTITIAL_UNIT_ID   @"Your unit id"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self createAndLoadInterstitial];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (void)dealloc {
    bannerView_.delegate = nil;
    
    // Release the bannerView_ if you are not using ARC in your project
//    [bannerView_ release];
//    [super dealloc];
}

- (void)createAndLoadInterstitial {
    
    if (interstitial_) {
        interstitial_.delegate = nil;
        interstitial_ = nil;
    }

    interstitial_ = [[GADInterstitial alloc] initWithAdUnitID:AD_INTERSTITIAL_UNIT_ID];
    interstitial_.delegate = self;
    
    GADRequest *request = [GADRequest request];
    [interstitial_ loadRequest:request];
}

- (IBAction)showBanner:(id)sender {
    
    if (bannerView_) {
        [bannerView_ removeFromSuperview];
        bannerView_.delegate = nil;
        bannerView_ = nil;
    }
    
    bannerView_ = [[GADBannerView alloc] initWithAdSize:kGADAdSizeBanner origin:CGPointMake((self.view.frame.size.width - kGADAdSizeBanner.size.width)/2, self.view.frame.size.height - kGADAdSizeBanner.size.height)];
    bannerView_.adUnitID = AD_BANNER_UNIT_ID;
    bannerView_.delegate = self;
    bannerView_.rootViewController = self;
    [self.view addSubview:bannerView_];
    
    GADRequest *request = [GADRequest request];
    [bannerView_ loadRequest:request];
}

- (IBAction)showInterstitial:(id)sender {
    
    if (interstitial_ && interstitial_.isReady) {
        [interstitial_ presentFromRootViewController:self];
    } else {
        [self createAndLoadInterstitial];
    }
}

#pragma mark - GADBannerViewDelegate
- (void)adViewDidReceiveAd:(GADBannerView *)bannerView{
    NSLog(@"adViewDidReceiveAd");
}
- (void)adView:(GADBannerView *)bannerView didFailToReceiveAdWithError:(GADRequestError *)error{
    NSLog(@"adView:didFailToReceiveAdWithError:%@",error);
}
- (void)adViewWillPresentScreen:(GADBannerView *)bannerView{
    NSLog(@"adViewWillPresentScreen");
}
- (void)adViewDidDismissScreen:(GADBannerView *)bannerView{
    NSLog(@"adViewDidDismissScreen");
}
- (void)adViewWillDismissScreen:(GADBannerView *)bannerView{
    NSLog(@"adViewWillDismissScreen");
}
- (void)adViewWillLeaveApplication:(GADBannerView *)bannerView{
    NSLog(@"adViewWillLeaveApplication");
}

#pragma mark - GADInterstitialDelegate
- (void)interstitialDidReceiveAd:(GADInterstitial *)interstitial{
    NSLog(@"interstitialDidReceiveAd");
}
- (void)interstitial:(GADInterstitial *)interstitial didFailToReceiveAdWithError:(GADRequestError *)error{
    if (error && [error localizedDescription]) {
        NSLog(@"interstitial:didFailToReceiveAdWithError:domain:%@ , code:%ld, userInfo:%@",[error domain],(long)[error code],[[error userInfo] description]);
    }
}
- (void)interstitialWillPresentScreen:(GADInterstitial *)interstitial{
    NSLog(@"interstitialWillPresentScreen");
}
- (void)interstitialWillDismissScreen:(GADInterstitial *)interstitial{
    NSLog(@"interstitialWillDismissScreen");
    [self createAndLoadInterstitial];
}
- (void)interstitialDidDismissScreen:(GADInterstitial *)interstitial{
    NSLog(@"interstitialDidDismissScreen");
}
- (void)interstitialWillLeaveApplication:(GADInterstitial *)interstitial{
    NSLog(@"interstitialWillLeaveApplication");
}

@end
