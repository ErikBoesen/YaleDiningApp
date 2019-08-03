package org.yaledining.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetUtil {
    public static boolean isNetAvailable(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService("connectivity");
        if (mgr == null) {
            return false;
        }
        boolean mobileNetwork = false;
        boolean wifiNetwork = false;
        boolean wiMaxNetwork = false;
        boolean mobileNetworkConnecetd = false;
        boolean wifiNetworkConnecetd = false;
        boolean wiMaxNetworkConnected = false;
        NetworkInfo mobileInfo = mgr.getNetworkInfo(0);
        NetworkInfo wifiInfo = mgr.getNetworkInfo(1);
        NetworkInfo wiMaxInfo = mgr.getNetworkInfo(6);
        if (mobileInfo != null) {
            mobileNetwork = mobileInfo.isAvailable();
        }
        if (wifiInfo != null) {
            wifiNetwork = wifiInfo.isAvailable();
        }
        if (wiMaxInfo != null) {
            wiMaxNetwork = wiMaxInfo.isAvailable();
        }
        if (wifiNetwork || mobileNetwork || wiMaxNetwork) {
            mobileNetworkConnecetd = mobileInfo.isConnectedOrConnecting();
            wifiNetworkConnecetd = wifiInfo.isConnectedOrConnecting();
            try {
                wiMaxNetworkConnected = wiMaxInfo.isConnectedOrConnecting();
            } catch (Exception e) {
            }
        }
        if (mobileNetworkConnecetd || wifiNetworkConnecetd || wiMaxNetworkConnected) {
            return true;
        }
        return false;
    }
}
