package org.yaledining.app;

import android.util.Log;

public class Logger {
    private static boolean isDebug = true;
    static final boolean isTesting = false;

    public static void vLog(String tag, String msg) {
        if (isDebug) {
            Log.v("Yale Dining : ", new StringBuilder(String.valueOf(tag)).append(" => ").append(msg).toString());
        }
    }

    public static void wLog(String tag, String msg) {
        if (isDebug) {
            Log.w("Yale Dining : ", new StringBuilder(String.valueOf(tag)).append(" => ").append(msg).toString());
        }
    }

    public static void eLog(String tag, String msg) {
        if (isDebug) {
            Log.e("Yale Dining : ", new StringBuilder(String.valueOf(tag)).append(" => ").append(msg).toString());
        }
    }

    public static void sLog(String msg) {
        if (isDebug) {
            System.out.println("Yale Dining : " + msg);
        }
    }
}
