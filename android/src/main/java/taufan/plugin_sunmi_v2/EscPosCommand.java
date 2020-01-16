package taufan.plugin_sunmi_v2;

import android.util.Log;

public class EscPosCommand {
    final static private byte LF = 10;
    final static private byte ESC = 27;
    final static private byte exclamation = 33;
    final static private byte strip = 45;
    final static private byte at = 64;
    final static private byte E = 69;
    final static private byte G = 71;
    final static private byte M = 77;
    final static private byte a = 97;

    public static byte[] lineFeed() {
        return new byte[]{LF};
    }

    public static byte[] setFont(String fontType) {
        byte[] command = new byte[3];
        command[0] = ESC;
        command[1] = M;

        switch (fontType) {
            case "A":
                command[2] = 0;
                break;
            case "B":
                command[2] = 1;
                break;
            case "C":
                command[2] = 2;
                break;
            default:
                break;

        }

        return command;
    }

    public static byte[] setEmphasized(Boolean emphasize) {
        final byte[] command = new byte[3];
        command[0] = ESC;
        command[1] = E;
        if (emphasize) {
            command[2] = 1;
        } else {
            command[2] = 0;
        }

        return command;
    }
}
