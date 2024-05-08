#import "BlanccoSDKPlugin.h"
#import <InhanceFramework/InhanceManager.h>

@implementation BlanccoSDKPlugin

- (void)pluginInitialize {
    NSLog(@"Starting BlanccoSDKPlugin");
}

- (void)activate:(CDVInvokedUrlCommand *)command {
    [[DeviceHealthUIManager shared] validateLicenseKey:@"KetOiB5YLx9s0xOcbQJMff358hrfJqR5lMR0Iu8a" success:^{
        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:nil];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    } failure:^(int responseCode, NSString *responseString) {
        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:responseString];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)runSequence:(CDVInvokedUrlCommand *)command {
    NSArray *testsArray = @[DeviceHealthButtonsTest]; // Assuming DeviceHealthButtonsTest is defined somewhere
    [[DeviceHealthUIManager shared] initialDeviceHealthScreen:testsArray license:@"KetOiB5YLx9s0xOcbQJMff358hrfJqR5lMR0Iu8a" success:^(UIViewController *testScreen) {

        // ERROR! This is not working
        [self.viewController presentViewController:testScreen animated:true completion:nil];

        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"OK"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    } failure:^(int responseCode, NSString *responseString) {
        // Handle failure case
        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:responseString];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

@end