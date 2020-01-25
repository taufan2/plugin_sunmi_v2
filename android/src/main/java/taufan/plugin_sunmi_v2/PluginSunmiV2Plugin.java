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

    private TransactionPrint transactionPrint;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "plugin_sunmi_v2");
        this.transactionPrint = new TransactionPrint(flutterPluginBinding.getApplicationContext());
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
        pluginSunmiV2Plugin.transactionPrint = new TransactionPrint(registrar.context());

        channel.setMethodCallHandler(pluginSunmiV2Plugin);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        switch (call.method) {

            case "BIND_SERVICE":
                this.transactionPrint.bindService();
                result.success(true);

                break;

            case "UNBIND_SERVICE":
                this.transactionPrint.unbindService();
                result.success(true);

                break;

            case "PRINT_TEXT":
                String text = call.argument("text");

                this.transactionPrint.printText(text);
                result.success(true);

                break;

            case "SET_ALIGNMENT":
                Integer alignment = call.argument("alignment");

                this.transactionPrint.setAlignment(alignment);
                result.success(true);

                break;

            case "SET_FONT_SIZE":
                Double font_size = call.argument("font_size");

                this.transactionPrint.setFontSize(font_size);
                result.success(true);

                break;

            case "SET_FONT_BOLD":
                Boolean font_bold = call.argument("font_bold");

                this.transactionPrint.setFontBold(font_bold);
                result.success(true);

                break;

            case "PRINT_COLUMN":
                ArrayList<String> string_column = call.argument("text_column");
                int[] column_width = call.argument("column_width");
                int[] column_alignment = call.argument("column_alignment");

                String[] strings = Utilities.arrayListToString(string_column);

                this.transactionPrint.printColumn(strings, column_width, column_alignment);

                result.success(true);

                break;

            case "LINE_FEED":
                Integer lines = call.argument("lines");

                this.transactionPrint.lineFeed(lines);
                result.success(true);

                break;

            case "SET_FONT_TYPE":
                String font_type = call.argument("font_type");

                this.transactionPrint.setFontType(font_type);
                result.success(true);

                break;

            case "SET_FONT_EMPHASIZED":
                Boolean font_emphasized = call.argument("font_emphasized");

                this.transactionPrint.setEmphasized(font_emphasized);
                result.success(true);

                break;

            case "PRINT_IMAGE":
                String image_path = call.argument("path");

                this.transactionPrint.printImage(image_path);
                result.success(true);

                break;

            case "COMMIT_PRINT":
                this.transactionPrint.commit();
                result.success(true);

                break;

            case "START":
                this.transactionPrint.start();
                result.success(true);

                break;

            case "CANCEL":
                this.transactionPrint.cancel();
                result.success(true);

                break;

            default:
                result.notImplemented();
                break;
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
