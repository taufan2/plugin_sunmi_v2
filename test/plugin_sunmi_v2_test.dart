import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_sunmi_v2/plugin_sunmi_v2.dart';

void main() {
  const MethodChannel channel = MethodChannel('plugin_sunmi_v2');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await PluginSunmiV2.platformVersion, '42');
  });
}
