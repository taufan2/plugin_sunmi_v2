#import "PluginSunmiV2Plugin.h"
#if __has_include(<plugin_sunmi_v2/plugin_sunmi_v2-Swift.h>)
#import <plugin_sunmi_v2/plugin_sunmi_v2-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "plugin_sunmi_v2-Swift.h"
#endif

@implementation PluginSunmiV2Plugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftPluginSunmiV2Plugin registerWithRegistrar:registrar];
}
@end
