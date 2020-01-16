import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:image_picker/image_picker.dart';
import 'package:intl/date_symbol_data_local.dart';
import 'package:intl/intl.dart';
import 'package:plugin_sunmi_v2/plugin_sunmi_v2.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  File _image ;

  DateFormat _dateFormat ;

  @override
  void initState() {
    super.initState();
    initializeDateFormatting('id_ID', null).then((_) => {});
    initPlatformState();
    bind();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await PluginSunmiV2.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  Future<void> bind() async {
    await PluginSunmiV2.bind();
    return;
  }

  Future<void> selfCheck() async {
    await PluginSunmiV2.selfCheck();
    return;
  }

  Future<void> printText() async {
    await PluginSunmiV2.printText("Test");
    return;
  }

  Future getImage() async {
    var image = await ImagePicker.pickImage(source: ImageSource.camera);

    setState(() {
      _image = image;
    });
  }

  Future printImage() async {
    await PluginSunmiV2.initPrinter();
    await PluginSunmiV2.printImage("/storage/emulated/0/Android/data/taufan.plugin_sunmi_v2_example/files/Pictures/530af75027660e394b684fb9c4b19c35.png");
  }

  Future<void> printFull() async {

    var now = new DateTime.now();
    var formatter = new DateFormat('EEEE, d MMMM yyyy [HH.mm]');
    String formatted = formatter.format(now);

    await PluginSunmiV2.initPrinter();
    await PluginSunmiV2.setAlignment(position: 1);
    await PluginSunmiV2.setFontType(fontType: "A");
    await PluginSunmiV2.setEmphasized(emphasized: true);
    await PluginSunmiV2.printText("BERITA ACARA SERAH TERIMA");
    await PluginSunmiV2.lineFeed();
    await PluginSunmiV2.printText("BAST#SJ015-013400");
    await PluginSunmiV2.lineFeed();
    await PluginSunmiV2.printText("PT AMARTA MITRA SEJATI");
    await PluginSunmiV2.lineFeed();
    await PluginSunmiV2.setAlignment(position: 0);
    await PluginSunmiV2.printText("════════════════════════════════");
    await PluginSunmiV2.lineFeed();
    await PluginSunmiV2.setFontSize(20);
    await PluginSunmiV2.printText(formatted);
    await PluginSunmiV2.lineFeed();
    await PluginSunmiV2.printText("Driver : Dummy Driver");

    await PluginSunmiV2.lineFeed();
    await PluginSunmiV2.lineFeed();

    await PluginSunmiV2.printColumn(["SPK", "SPK009201901005"]);
    await PluginSunmiV2.printColumn(["TYPE", "BeAT Sporty CBS ISS IV"]);
    await PluginSunmiV2.printColumn(["MERK/JENIS", "Honda/Matic - Beat Plus"]);
    await PluginSunmiV2.printColumn(["WARNA", "BIRU HITAM - BH"]);

    await PluginSunmiV2.lineFeed();

    await PluginSunmiV2.printText("Telah diserahkan dengan keadaan baik dan\ntanpa cacat kepada yang terhormat :");

    await PluginSunmiV2.lineFeed();

    await PluginSunmiV2.printColumn(["NAMA", "AI SURYATI."]);
    await PluginSunmiV2.printColumn(["ALAMAT", "KP CIKABODAS RT01 RW01"]);
    await PluginSunmiV2.printColumn(["TELEPON", "08814069766"]);
    await PluginSunmiV2.printColumn(["NO IDENTITAS", "3217156202690001"]);

    await PluginSunmiV2.lineFeed();

    await PluginSunmiV2.printImage("/storage/emulated/0/Android/data/taufan.plugin_sunmi_v2_example/files/Pictures/dumm-tanda-tangan-3.png");


    return ;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[
              RaisedButton(
                child: Text('Self Check'),
                onPressed: this.selfCheck,
              ),
              RaisedButton(
                child: Text('Print Dummy Text'),
                onPressed: this.printText,
              ),
              RaisedButton(child: Text('Print Full'), onPressed: this.printFull),

              RaisedButton(child: Text('Print Image 2'), onPressed: this.getImage),
              RaisedButton(child: Text('Print Image'), onPressed: this.printImage),
            ],
          ),
        ),
      ),
    );
  }
}
