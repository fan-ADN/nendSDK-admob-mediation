//
//  BannerViewController.m
//  NendSDK_AdMobMediationSample
//
//  Copyright © 2017年 F@N Communications. All rights reserved.
//

#import "BannerViewController.h"
@import GoogleMobileAds;

#define AD_BANNER_UNIT_ID         @"Your unit id"

@interface BannerViewController () <GADBannerViewDelegate>

@property (nonatomic) GADBannerView *bannerView;

@end

@implementation BannerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    self.bannerView.delegate = nil;
}

- (IBAction)showBanner:(id)sender {
    
    if (self.bannerView) {
        [self.bannerView removeFromSuperview];
        self.bannerView.delegate = nil;
        self.bannerView = nil;
    }
    
    self.bannerView = [[GADBannerView alloc] initWithAdSize:kGADAdSizeBanner origin:CGPointMake((self.view.frame.size.width - kGADAdSizeBanner.size.width)/2, self.view.frame.size.height - kGADAdSizeBanner.size.height)];
    self.bannerView.adUnitID = AD_BANNER_UNIT_ID;
    self.bannerView.delegate = self;
    self.bannerView.rootViewController = self;
    [self.view addSubview:self.bannerView];
    
    GADRequest *request = [GADRequest request];
    [self.bannerView loadRequest:request];
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

@end
