
# Plugin Sunmi V2  
  
Ini adalah plugin untuk Mobile Printer Sunmi V2, plugin ini masih dalam tahap pengembangan.  
  
## Perangkat yang sudah dicoba  
  
 - [x] Sunmi V2  
  
## Penggunaan  
  
### 1. Import Plugin  
  
```dart  
import 'package:plugin_sunmi_v2/plugin_sunmi_v2.dart';
```  
  
  
### 2. Instasiasi Class `PluginSunmiV2` lalu `.bind()`  
```dart  
 PluginSunmiV2 _printer = PluginSunmiV2();  
  
 _printer.bind(); // ini harus dilakukan  
```  
  
### 3. Mulai Print  
  
```dart  
 try {    
  await _printer.start(); // ini harus dilakukan sebelum memasukan method lainnya
  await _printer.printText("Lorem ipsum dolor sit amet.");
  await _printer.commit();   ini harus dilakukan untuk melakukan print
 } catch (e) {
  await _printer.cancel();
 }  
```  
  
#### Method Tersedia  

Semuanya masih dalam tahap pengembangan, belum seluruh fitur tersedia.

---    
```dart  
 Future<void> sendRaw(Uint8List bytes);  
```  
---  
```dart  
 Future<void> printText(String text);  
```  
---  
```dart  
 Future<void> lineFeed({int lines = 1});  
```  
---  
  
```dart  
 Future<void> printColumn(List<String> text, {Int32List columnWidth, Int32List columnAlignment});  
```  
---  
```dart  
 Future<void> setAlignment({int position: ALIGNMENT_LEFT})  
```  
---  
```dart  
 Future<void> setFontSize({double fontSize = 14})  
```  
---  
```dart  
 Future<void> setBoldFont({bool bold = true})  
```  
---  
```dart  
 Future<void> setFontType({String fontType: FONT_TYPE_A})
```
---  
```dart  
 Future<void> setEmphasized({bool emphasized: false})
```
---  
```dart  
 Future<void> commit()
```  
---  
```dart  
 Future<void> start() 
```  
---  
```dart  
 Future<void> cancel()
```
