package com.google.android.gms.plus;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.C0068ak;
import com.google.android.gms.internal.C0132p;

public class GooglePlusUtil {
    public static final int APP_DISABLED = 3;
    public static final int APP_MISSING = 1;
    public static final int APP_UPDATE_REQUIRED = 2;
    public static final String GOOGLE_PLUS_PACKAGE = "com.google.android.apps.plus";
    public static final int SUCCESS = 0;
    private static final String TAG = GooglePlusUtil.class.getSimpleName();

    private GooglePlusUtil() {
        throw new AssertionError();
    }

    /* renamed from: a */
    static String m402a(Context context, String str, String str2) {
        try {
            Resources resources = context.createPackageContext(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, 0).getResources();
            return resources.getString(resources.getIdentifier(str, "string", GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE));
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Unable to load resources from GMS: GMS not installed.", e);
            return str2;
        } catch (NotFoundException e2) {
            Log.e(TAG, "Unable to load resources from GMS: Resource \"" + str + "\" not found.", e2);
            return str2;
        } catch (SecurityException e3) {
            Log.e(TAG, "Unable to load resources from GMS.", e3);
            return str2;
        }
    }

    /* renamed from: b */
    private static int m403b(Context context, int i) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(GOOGLE_PLUS_PACKAGE, 0);
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(GOOGLE_PLUS_PACKAGE, 0);
            if (packageInfo.versionCode < i) {
                return 2;
            }
            return !applicationInfo.enabled ? 3 : 0;
        } catch (NameNotFoundException e) {
            return 1;
        }
    }

    public static int checkGooglePlusApp(Context context) {
        return m403b(context, 320000000);
    }

    public static Dialog getErrorDialog(int i, Activity activity, int i2) {
        switch (i) {
            case 0:
                return null;
            case 1:
                return new Builder(activity).setTitle(m402a(activity, "install_google_plus_title", "Get Google+")).setMessage(m402a(activity, "install_google_plus_text", "Download Google+ from Google Play so you can share this?")).setPositiveButton(m402a(activity, "install_google_plus_button", "Get Google+"), new C0068ak(activity, C0132p.m196d(GOOGLE_PLUS_PACKAGE), i2)).create();
            case 2:
                return new Builder(activity).setTitle(m402a(activity, "update_google_plus_title", "Update Google+")).setMessage(m402a(activity, "update_google_plus_text", "Update Google+ from Google Play so you can share this?")).setPositiveButton(m402a(activity, "update_google_plus_button", "Update"), new C0068ak(activity, C0132p.m196d(GOOGLE_PLUS_PACKAGE), i2)).create();
            case 3:
                return new Builder(activity).setTitle(m402a(activity, "enable_google_plus_title", "Enable Google+")).setMessage(m402a(activity, "enable_google_plus_text", "Enable Google+ from Google Play so you can share this?")).setPositiveButton(m402a(activity, "enable_google_plus_button", "Enable Google+"), new C0068ak(activity, C0132p.m194b(GOOGLE_PLUS_PACKAGE), i2)).create();
            default:
                throw new IllegalArgumentException("Unexpected errorCode " + i);
        }
    }
}
