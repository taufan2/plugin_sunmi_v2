import 'dart:async';
import 'dart:typed_data';

import 'package:flutter/services.dart';

class PluginSunmiV2 {
  static const int ALIGNMENT_LEFT = 0;
  static const int ALIGNMENT_CENTER = 1;
  static const int ALIGNMENT_RIGHT = 2;

  static const String FONT_TYPE_A = "A";
  static const String FONT_TYPE_B = "B";
  static const String FONT_TYPE_C = "C";

  final MethodChannel _channel = const MethodChannel('plugin_sunmi_v2');

  Future<void> bind() async {
    await _channel.invokeMethod('BIND_SERVICE');
    return;
  }

  Future<void> unBind() async {
    await _channel.invokeMethod('UNBIND_SERVICE');
    return;
  }

  Future<void> lineFeed({int lines = 1}) async {
    Map<String, dynamic> arguments = <String, dynamic>{"lines": lines};

    await _channel.invokeMethod("LINE_FEED", arguments);
    return;
  }

  Future<void> printText(String text) async {
    Map<String, dynamic> arguments = <String, dynamic>{"text": text};

    await _channel.invokeMethod("PRINT_TEXT", arguments);
    return;
  }

  Future<void> printImage(String path) async {
    Map<String, dynamic> arguments = <String, dynamic>{"path": path};

    await _channel.invokeMethod("PRINT_IMAGE", arguments);
    return;
  }

  Future<void> printColumn(List<String> text, {Int32List columnWidth, Int32List columnAlignment}) async {
    columnWidth = columnWidth ?? Int32List.fromList([1, 2]);
    columnAlignment = columnAlignment ?? Int32List.fromList([0, 2]);
    Map<String, dynamic> arguments = <String, dynamic>{'text_column': text, 'column_width': columnWidth, 'column_alignment': columnAlignment};

    await _channel.invokeMethod("PRINT_COLUMN", arguments);
    return;
  }

  Future<void> setAlignment({int position: ALIGNMENT_LEFT}) async {
    Map<String, dynamic> arguments = <String, dynamic>{'alignment': position};

    await _channel.invokeMethod("SET_ALIGNMENT", arguments);
    return;
  }

  Future<void> setFontSize({double fontSize = 14}) async {
    Map<String, dynamic> arguments = <String, dynamic>{'font_size': fontSize};

    await _channel.invokeMethod("SET_FONT_SIZE", arguments);
    return;
  }

  Future<void> setBoldFont({bool bold = true}) async {
    Map<String, dynamic> arguments = <String, dynamic>{'font_bold': bold};

    await _channel.invokeMethod("SET_FONT_BOLD", arguments);
    return;
  }

  Future<void> setFontType({String fontType: FONT_TYPE_A}) async {
    Map<String, dynamic> arguments = <String, dynamic>{'font_type': fontType};

    await _channel.invokeMethod("SET_FONT_TYPE", arguments);
    return;
  }

  Future<void> setEmphasized({bool emphasized: false}) async {
    Map<String, dynamic> arguments = <String, dynamic>{'font_emphasized': emphasized};

    await _channel.invokeMethod("SET_FONT_EMPHASIZED", arguments);
    return;
  }

  Future<void> commit() async {
    await _channel.invokeMethod("COMMIT_PRINT");
    return;
  }

  Future<void> start() async {
    await _channel.invokeMethod("START");
    return;
  }

  Future<void> cancel() async {
    await _channel.invokeMethod("CANCEL");
    return;
  }
}
