# Plugin Sunmi V2

Ini ada plugin untuk Mobile Printer Sunmi V2, plugin ini masih dalam tahap pengembangan.

## Perangkat yang sudah dicoba

 - [x] Sunmi V2

## Penggunaan

### 1. Import Plugin

```dart
import 'package:plugin_sunmi_v2/plugin_sunmi_v2.dart';
```


### 2. Instasiasi Class `PluginSunmiV2`
```dart
PluginSunmiV2 _printer = PluginSunmiV2();
```

### 3. Mulai Print

```dart
try {
  await _printer.start();
  await _printer.printText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
  await _printer.commit();
} catch (e) {
  await printer.cancel();
}
```