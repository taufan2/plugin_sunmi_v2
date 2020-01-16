package taufan.plugin_sunmi_v2;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * PluginSunmiV2Plugin
 */
public class PluginSunmiV2Plugin implements FlutterPlugin, MethodCallHandler {

    private SunmiCore sunmiCore;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "plugin_sunmi_v2");
        this.sunmiCore = new SunmiCore(flutterPluginBinding.getApplicationContext());
        channel.setMethodCallHandler(this);
    }

    /***
     *
     * Gunakan fungsi statis ini untuk Flutter di bawah versi 1-.12
     *
     * Baca Matakanana!!  https://flutter.dev/go/android-project-migration
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "plugin_sunmi_v2");

        PluginSunmiV2Plugin pluginSunmiV2Plugin = new PluginSunmiV2Plugin();
        pluginSunmiV2Plugin.sunmiCore = new SunmiCore(registrar.context());

        channel.setMethodCallHandler(pluginSunmiV2Plugin);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        }

        else if (call.method.equals("bind")) {
            this.sunmiCore.bind();
            result.success(null);
        }

        else if (call.method.equals("unBind")) {
            this.sunmiCore.unBind();
            result.success(null);
        }

        else if (call.method.equals("initPrinter")) {
            this.sunmiCore.initPrinter();
            result.success(null);
        }

        else if (call.method.equals("selfCheck")) {
            this.sunmiCore.selfCheck();
            result.success(null);
        }

        else if (call.method.equals("printText")) {
            String text = call.argument("text");
            this.sunmiCore.printText(text);
            result.success(null);
        }

        else if (call.method.equals("printColumn")) {
            ArrayList<String> stringList = call.argument("texts");
            ArrayList<Integer> columnWidth = call.argument("width");
            ArrayList<Integer> alignment = call.argument("alignment");

            this.sunmiCore.printColumn(Utilities.arrayListToString(stringList), Utilities.arrayListToIntList(columnWidth), Utilities.arrayListToIntList(alignment));
            result.success(null);
        }

        else if (call.method.equals("setAlignment")) {
            Integer alignment = call.argument("alignment");
            this.sunmiCore.setAlignment(alignment);
            result.success(null);
        }

        else if (call.method.equals("setFontSize")) {
            Double fontSize = call.argument("fontSize");
            this.sunmiCore.setFontSize(fontSize);
            result.success(null);
        }

        else if (call.method.equals("setBoldFont")){
            this.sunmiCore.setFontBold();
            result.success(null);
        }

        else if (call.method.equals("setUnBoldFont")){
            this.sunmiCore.setFontUnBold();
            result.success(null);
        }

        else if(call.method.equals("lineFeed")) {
            this.sunmiCore.lineFeed();
            result.success(null);
        }

        else if(call.method.equals("setFontType")) {
            String fontType = call.argument("fontType");
            this.sunmiCore.setFontType(fontType);
            result.success(null);
        }

        else if(call.method.equals("setEmphasized")) {
            Boolean fontType = call.argument("emphasized");
            this.sunmiCore.setEmphasized(fontType);
            result.success(null);
        }

        else if (call.method.equals("printImage")) {
            String pathName = call.argument("pathName");
            this.sunmiCore.printImage(pathName);
            result.success(null);
        }

        else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
