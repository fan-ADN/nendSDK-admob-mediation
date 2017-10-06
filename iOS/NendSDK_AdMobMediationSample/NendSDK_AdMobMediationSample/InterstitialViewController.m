//
//  InterstitialViewController.m
//  NendSDK_AdMobMediationSample
//
//  Copyright © 2017年 F@N Communications. All rights reserved.
//

#import "InterstitialViewController.h"
@import GoogleMobileAds;

#define AD_INTERSTITIAL_UNIT_ID   @"Your unit id"

@interface InterstitialViewController () <GADInterstitialDelegate>

@property (nonatomic) GADInterstitial *interstitial;

@end

@implementation InterstitialViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self createAndLoadInterstitial];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)createAndLoadInterstitial {
    
    if (self.interstitial) {
        self.interstitial.delegate = nil;
        self.interstitial = nil;
    }
    
    self.interstitial = [[GADInterstitial alloc] initWithAdUnitID:AD_INTERSTITIAL_UNIT_ID];
    self.interstitial.delegate = self;
    
    GADRequest *request = [GADRequest request];
    [self.interstitial loadRequest:request];
}

- (IBAction)showInterstitial:(id)sender {
    
    if (self.interstitial && self.interstitial.isReady) {
        [self.interstitial presentFromRootViewController:self];
    } else {
        [self createAndLoadInterstitial];
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
