package org.yaledining.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import java.lang.Thread.UncaughtExceptionHandler;

public class BaseActivity extends Activity {

    public class TopExceptionHandler implements UncaughtExceptionHandler {
        public TopExceptionHandler(Activity app) {
            Thread.getDefaultUncaughtExceptionHandler();
        }

        public void uncaughtException(Thread t, Throwable e) {
            StackTraceElement[] arr = e.getStackTrace();
            String report = new StringBuilder(String.valueOf(e.toString() + "\n\n")).append("--------- Stack trace ---------\n\n").toString();
            for (StackTraceElement stackTraceElement : arr) {
                report = new StringBuilder(String.valueOf(report)).append("    ").append(stackTraceElement.toString()).append("\n").toString();
            }
            String report2 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(report)).append("-------------------------------\n\n").toString())).append("--------- Cause ---------\n\n").toString();
            Throwable cause = e.getCause();
            if (cause != null) {
                report2 = new StringBuilder(String.valueOf(report2)).append(cause.toString()).append("\n\n").toString();
                StackTraceElement[] arr2 = cause.getStackTrace();
                for (StackTraceElement stackTraceElement2 : arr2) {
                    report2 = new StringBuilder(String.valueOf(report2)).append("    ").append(stackTraceElement2.toString()).append("\n").toString();
                }
            }
            ActivityManager activityManager = (ActivityManager) BaseActivity.this.getSystemService("activity");
            MemoryInfo mi = new MemoryInfo();
            activityManager.getMemoryInfo(mi);
            Log.v("MEMORY", mi.availMem);
            String report3 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(report2)).append("-------------------------------\n\n").toString())).append("-------------------------------\n\n").toString())).append("--------- Memery ---------\n\n").toString())).append("Available Memory: ").append(mi.availMem).append("\n").toString())).append("-------------------------------\n\n").toString())).append("-------------------------------\n\n").toString())).append("--------- Device ---------\n\n").toString())).append("Brand: ").append(Build.BRAND).append("\n").toString())).append("Device: ").append(Build.DEVICE).append("\n").toString())).append("Model: ").append(Build.MODEL).append("\n").toString())).append("Id: ").append(Build.ID).append("\n").toString())).append("Product: ").append(Build.PRODUCT).append("\n").toString())).append("-------------------------------\n\n").toString())).append("--------- Firmware ---------\n\n").toString())).append("SDK: ").append(VERSION.SDK).append("\n").toString())).append("Release: ").append(VERSION.RELEASE).append("\n").toString())).append("Incremental: ").append(VERSION.INCREMENTAL).append("\n").toString())).append("-------------------------------\n\n").toString();
            Log.v("Report ::", report3);
            Intent i1 = new Intent(BaseActivity.this, Error.class);
            i1.putExtra("Error", report3.toString());
            i1.addFlags(268435456);
            i1.addFlags(67108864);
            BaseActivity.this.startActivity(i1);
            System.exit(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new TopExceptionHandler(this));
    }
}
