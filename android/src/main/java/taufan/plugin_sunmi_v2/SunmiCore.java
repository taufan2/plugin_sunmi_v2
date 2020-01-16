package taufan.plugin_sunmi_v2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

public class SunmiCore {

    final private String TAG = SunmiCore.class.getSimpleName();
    private IWoyouService woyouService;
    private Context context;

    SunmiCore(Context context) {
        this.context = context;
        Log.d("RESOURCES", context.getFilesDir().getPath());
    }

    private ServiceConnection connService = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                woyouService = IWoyouService.Stub.asInterface(service);
                String serviceVersion = woyouService.getServiceVersion();
                Toast.makeText(context, "Service Connected. Version :" + serviceVersion, Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(context, "Service Disconnected", Toast.LENGTH_LONG).show();
            bindService();
        }
    };

    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("woyou.aidlservice.jiuiv5");
        intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");
        context.bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        context.unbindService(connService);
    }

    public void bind() {
        this.bindService();
    }

    public void unBind() {
        this.unbindService();
    }

    public void initPrinter() {
        try {
            woyouService.printerInit(this._callback());
        } catch (RemoteException e) {

        }
    }

    public void selfCheck() {
        try {
            woyouService.printerSelfChecking(this._callback());
        } catch (RemoteException e) {

        }
    }

    public void printText(String text) {
        try {
            woyouService.printText(text, this._callback());
        } catch (RemoteException e) {

        }
    }

    public void setAlignment(Integer alignment) {
        try {
            woyouService.setAlignment(alignment, this._callback());
        } catch (RemoteException e) {

        }
    }

    public void setFontSize(Double fontSize) {
        double v = fontSize;
        try {
            woyouService.setFontSize((float) v, this._callback());
        } catch (RemoteException e) {

        }
    }

    public void setFontBold() {
        try {
            woyouService.sendRAWData(new byte[]{0x1B, 0x45, 0x1}, this._callback());
        } catch (RemoteException e) {

        }
    }

    public void setFontUnBold() {
        try {
            woyouService.sendRAWData(new byte[]{0x1B, 0x45, 0x0}, this._callback());
        } catch (RemoteException e) {

        }
    }

    public void printColumn(String[] stringColumns, int[] columnWidth, int[] columnAlignment) {
        try {
            woyouService.printColumnsString(stringColumns, columnWidth, columnAlignment, this._callback());
        } catch (RemoteException e) {

        }
    }

    public void lineFeed() {
        byte[] command = EscPosCommand.lineFeed();
        try {
            woyouService.sendRAWData(command, this._callback());
        } catch (RemoteException e) {

        }
    }

    public void setFontType(String fontType) {
        byte[] command = EscPosCommand.setFont(fontType);
        try {
            woyouService.sendRAWData(command, _callback());
        } catch (RemoteException e) {

        }
    }

    public void setEmphasized(Boolean emphasized) {
        byte[] command = EscPosCommand.setEmphasized(emphasized);
        try {
            woyouService.sendRAWData(command, _callback());
        } catch (RemoteException e) {

        }
    }

    public void printImage(String pathName) {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmapFactory = BitmapFactory.decodeFile(pathName, bmOptions);

        Bitmap scaledBitmap = Utilities.scaleDownBitmap(bitmapFactory, 300, true);

        try {
            woyouService.printBitmapCustom(scaledBitmap, 2, this._callback());
            woyouService.lineWrap(3, null);
        } catch (RemoteException e) {

        }
    }

    private ICallback _callback() {
        return new ICallback() {
            @Override
            public void onRunResult(boolean isSuccess) throws RemoteException {
                Log.d("SunmiV2onRunResult","");
            }

            @Override
            public void onReturnString(String result) throws RemoteException {
                Log.d("SunmiV2onReturnString","");
            }

            @Override
            public void onRaiseException(int code, String msg) throws RemoteException {
                Log.d("SunmiV2onRaiseException",msg);
            }

            @Override
            public void onPrintResult(int code, String msg) throws RemoteException {
                Log.d("SunmiV2onPrintResult",msg);
            }

            @Override
            public IBinder asBinder() {
                return null;
            }
        };
    }
}
