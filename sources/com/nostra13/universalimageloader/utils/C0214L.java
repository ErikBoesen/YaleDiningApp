package com.nostra13.universalimageloader.utils;

import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;

/* renamed from: com.nostra13.universalimageloader.utils.L */
public final class C0214L {
    private static final String LOG_FORMAT = "%1$s\n%2$s";

    private C0214L() {
    }

    /* renamed from: d */
    public static void m410d(String message, Object... args) {
        log(3, null, message, args);
    }

    /* renamed from: i */
    public static void m414i(String message, Object... args) {
        log(4, null, message, args);
    }

    /* renamed from: w */
    public static void m415w(String message, Object... args) {
        log(5, null, message, args);
    }

    /* renamed from: e */
    public static void m412e(Throwable ex) {
        log(6, ex, null, new Object[0]);
    }

    /* renamed from: e */
    public static void m411e(String message, Object... args) {
        log(6, null, message, args);
    }

    /* renamed from: e */
    public static void m413e(Throwable ex, String message, Object... args) {
        log(6, ex, message, args);
    }

    private static void log(int priority, Throwable ex, String message, Object... args) {
        String logMessage;
        String log;
        if (args.length > 0) {
            message = String.format(message, args);
        }
        if (ex == null) {
            log = message;
        } else {
            if (message == null) {
                logMessage = ex.getMessage();
            } else {
                logMessage = message;
            }
            String logBody = Log.getStackTraceString(ex);
            log = String.format(LOG_FORMAT, new Object[]{logMessage, logBody});
        }
        Log.println(priority, ImageLoader.TAG, log);
    }
}
