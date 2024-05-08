#import <Cordova/CDV.h>
#import "AppDelegate.h"

@interface BlanccoSDKPlugin : CDVPlugin

- (void)activate:(CDVInvokedUrlCommand *)command;
- (void)runSequence:(CDVInvokedUrlCommand *)command;

@end
