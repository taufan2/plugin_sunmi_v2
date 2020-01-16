import 'dart:async';

import 'package:flutter/services.dart';

class PluginSunmiV2 {
  static const MethodChannel _channel = const MethodChannel('plugin_sunmi_v2');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> bind() async {
    await _channel.invokeMethod('bind');
    return "test";
  }

  static Future<void> unBind() async {
    await _channel.invokeMethod('unBind');
    return ;
  }

  static Future<void> initPrinter() async {
    await _channel.invokeMethod('initPrinter');
    return ;
  }

  static Future<void> selfCheck() async {
    await _channel.invokeMethod("selfCheck");
    return ;
  }

  static Future<void> lineFeed() async{
    await _channel.invokeMethod("lineFeed");
    return ;
  }

  static Future<void> printText(String text) async {
    await _channel.invokeMethod("printText", <String, dynamic>{"text": text});
    return;
  }

  static Future<void> printImage(String pathName) async {
    await _channel.invokeMethod("printImage", {'pathName': pathName});
    return;
  }

  static Future<void> printColumn(List<String> texts, {List<int> width = const[1, 2], List<int> alignment = const[0, 2]}) async {
    await _channel.invokeMethod("printColumn", {
      'texts' : texts,
      'width': width,
      'alignment': alignment
    });
    return ;
  }

  static Future<void> setAlignment({int position: 0}) async{
    await _channel.invokeMethod("setAlignment", <String, dynamic>{'alignment': position});
    return ;
  }

  static Future<void> setFontSize(double fontSize) async{
    await _channel.invokeMethod("setFontSize", {'fontSize' : fontSize});
    return;
  }

  static Future<void> setBoldFont() async{
    await _channel.invokeMethod("setBoldFont");
    return;
  }

  static Future<void> setUnBoldFont() async{
    await _channel.invokeMethod("setUnBoldFont");
    return;
  }

  static Future<void> setFontType({String fontType: "A"}) async {
    await _channel.invokeMethod("setFontType", {
      'fontType': fontType
    });
  }

  static Future<void> setEmphasized({bool emphasized: false}) async {
    await _channel.invokeMethod("setEmphasized", {
      'emphasized': emphasized
    });
    return;
  }

}
