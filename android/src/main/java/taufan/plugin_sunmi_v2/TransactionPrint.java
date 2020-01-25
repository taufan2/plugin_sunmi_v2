package taufan.plugin_sunmi_v2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import java.util.ArrayList;

import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

public class TransactionPrint {
    private ArrayList<Boolean> transaction = new ArrayList<Boolean>();

    final private String TAG = TransactionPrint.class.getSimpleName();
    private IWoyouService woyouService;
    private Context context;

    public TransactionPrint(Context context) {
        this.context = context;
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
        }
    };

    public void bindService() {
        Intent intent = new Intent();
        intent.setPackage("woyou.aidlservice.jiuiv5");
        intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");
        context.bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }

    public void unbindService() {
        context.unbindService(connService);
    }

    public void printText(String text) {
        this.transaction.add(this._printText(text));
    }

    public Boolean _printText(String text) {
        try {
            woyouService.printText(text, this._callback());
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    public Boolean setAlignment(Integer alignment) {
        try {
            woyouService.setAlignment(alignment, this._callback());
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    public Boolean setFontSize(Double fontSize) {
        double v = fontSize;
        try {
            woyouService.setFontSize((float) v, this._callback());
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    public Boolean setFontBold(Boolean bold) {

        if (bold == null) {
            bold = false;
        }

        byte[] command = new byte[]{0x1B, 0x45, 0x1};

        if (bold == false) {
            command = new byte[]{0x1B, 0x45, 0x0};
        }

        try {
            woyouService.sendRAWData(command, this._callback());
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    public Boolean printColumn(String[] stringColumns, int[] columnWidth, int[] columnAlignment) {
        try {
            woyouService.printColumnsString(stringColumns, columnWidth, columnAlignment, this._callback());
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    public Boolean lineFeed(Integer lines) {
        byte[] command = EscPosCommand.lineFeed(lines);
        try {
            woyouService.sendRAWData(command, this._callback());
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    public Boolean setFontType(String fontType) {
        byte[] command = EscPosCommand.setFont(fontType);
        try {
            woyouService.sendRAWData(command, _callback());
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    public Boolean setEmphasized(Boolean emphasized) {
        byte[] command = EscPosCommand.setEmphasized(emphasized);
        try {
            woyouService.sendRAWData(command, _callback());
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    public Boolean printImage(String path) {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmapFactory = BitmapFactory.decodeFile(path, bmOptions);

        Bitmap scaledBitmap = Utilities.scaleDownBitmap(bitmapFactory, 300, true);

        try {
            woyouService.printBitmapCustom(scaledBitmap, 2, this._callback());
            woyouService.lineWrap(3, null);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    public void commit() {
        try {
            this.woyouService.lineWrap(5, null);
            this.woyouService.commitPrinterBuffer();
            this.woyouService.exitPrinterBuffer(true);
        } catch (RemoteException e) {

        }
    }

    public void start() {
        try {
            this.woyouService.enterPrinterBuffer(true);
        } catch (RemoteException e) {

        }
    }

    public void cancel() {
        try {
            this.woyouService.exitPrinterBuffer(false);
        } catch (RemoteException e) {

        }
    }

    private ICallback _callback() {
        return new ICallback() {
            @Override
            public void onRunResult(boolean isSuccess) throws RemoteException {

            }

            @Override
            public void onReturnString(String result) throws RemoteException {

            }

            @Override
            public void onRaiseException(int code, String msg) throws RemoteException {

            }

            @Override
            public void onPrintResult(int code, String msg) throws RemoteException {

            }

            @Override
            public IBinder asBinder() {
                return null;
            }
        };
    }
}
