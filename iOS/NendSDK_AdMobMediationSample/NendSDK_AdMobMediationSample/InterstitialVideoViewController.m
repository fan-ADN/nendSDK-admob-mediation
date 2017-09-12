//
//  InterstitialVideoViewController.m
//  NendSDK_AdMobMediationSample
//
//  Copyright © 2017年 F@N Communications. All rights reserved.
//

#import "InterstitialVideoViewController.h"
@import GoogleMobileAds;
@import NendAdapter;

#define AD_INTERSTITIAL_VIDEO_UNIT_ID   @"Your unit id"

@interface InterstitialVideoViewController () <GADInterstitialDelegate>

@property (nonatomic) GADInterstitial *interstitialVideo;

@end

@implementation InterstitialVideoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)createAndLoadInterstitialVideo {
    
    if (self.interstitialVideo) {
        self.interstitialVideo.delegate = nil;
        self.interstitialVideo = nil;
    }
    
    self.interstitialVideo = [[GADInterstitial alloc] initWithAdUnitID:AD_INTERSTITIAL_VIDEO_UNIT_ID];
    self.interstitialVideo.delegate = self;
    
    GADRequest *request = [GADRequest request];
    GADMAdapterNendExtras *extras = [[GADMAdapterNendExtras alloc] init];
    extras.interstitialType = GADMNendInterstitialTypeVideo;
    extras.userId = @"Your user id";
    [request registerAdNetworkExtras:extras];
    
    [self.interstitialVideo loadRequest:request];
}

- (IBAction)showInterstitial:(id)sender {
    
    if (self.interstitialVideo && self.interstitialVideo.isReady) {
        [self.interstitialVideo presentFromRootViewController:self];
    } else {
        [self createAndLoadInterstitialVideo];
    }
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
}

- (void)interstitialDidDismissScreen:(GADInterstitial *)interstitial{
    NSLog(@"interstitialDidDismissScreen");
}

- (void)interstitialWillLeaveApplication:(GADInterstitial *)interstitial{
    NSLog(@"interstitialWillLeaveApplication");
}

@end
