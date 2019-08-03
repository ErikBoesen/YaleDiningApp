package com.google.android.gms.auth;

import android.accounts.AccountManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.C0078ao;
import java.io.IOException;
import java.net.URISyntaxException;

public final class GoogleAuthUtil {
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_HANDLE_NOTIFICATION = "handle_notification";

    private GoogleAuthUtil() {
    }

    /* renamed from: a */
    private static String m2a(Context context, String str, String str2, Bundle bundle) throws IOException, GoogleAuthException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            return getToken(context, str, str2, bundle);
        } catch (GooglePlayServicesAvailabilityException e) {
            PendingIntent errorPendingIntent = GooglePlayServicesUtil.getErrorPendingIntent(e.getConnectionStatusCode(), context, 0);
            Notification notification = new Notification(17301642, "Play Services error", System.currentTimeMillis());
            notification.flags |= 16;
            notification.setLatestEventInfo(context, "Install/Update/Enable GooglePlayServices", "Requested by " + context.getPackageName(), errorPendingIntent);
            ((NotificationManager) context.getSystemService("notification")).notify(39789, notification);
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
    }

    /* renamed from: a */
    private static void m3a(Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("Callack cannot be null.");
        }
        try {
            Intent.parseUri(intent.toUri(1), 1);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }

    /* renamed from: f */
    private static void m4f(Context context) throws GoogleAuthException {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable != 0) {
            Intent a = GooglePlayServicesUtil.m7a(context, isGooglePlayServicesAvailable, -1);
            String str = "GooglePlayServices not available due to error " + isGooglePlayServicesAvailable;
            Log.e("GoogleAuthUtil", str);
            throw (a == null ? new GoogleAuthException(str) : new GooglePlayServicesAvailabilityException(isGooglePlayServicesAvailable, "GooglePlayServicesNotAvailable", a));
        }
    }

    /* renamed from: g */
    private static void m5g(Context context) {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == context.getMainLooper()) {
            IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
            Log.e("GoogleAuthUtil", "Calling this from your main thread can lead to deadlock and/or ANRs", illegalStateException);
            throw illegalStateException;
        }
    }

    public static String getToken(Context context, String str, String str2) throws IOException, GoogleAuthException {
        return getToken(context, str, str2, new Bundle());
    }

    public static String getToken(Context context, String str, String str2, Bundle bundle) throws IOException, GoogleAuthException {
        Context applicationContext = context.getApplicationContext();
        m5g(applicationContext);
        m4f(applicationContext);
        C0078ao aoVar = new C0078ao(str, str2, bundle);
        String i = aoVar.mo1264i(applicationContext);
        if (aoVar.mo1259H()) {
            if (bundle.getBoolean(KEY_HANDLE_NOTIFICATION, false)) {
                throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
            }
            throw new UserRecoverableAuthException(aoVar.mo1260I(), aoVar.getIntent());
        } else if (aoVar.hasSoftError()) {
            throw new IOException(aoVar.mo1260I());
        } else if (!aoVar.hasHardError()) {
            return i;
        } else {
            throw new GoogleAuthException(aoVar.mo1260I());
        }
    }

    public static String getTokenWithNotification(Context context, String str, String str2, Bundle bundle) throws IOException, GoogleAuthException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(KEY_HANDLE_NOTIFICATION, true);
        return m2a(context, str, str2, bundle);
    }

    public static String getTokenWithNotification(Context context, String str, String str2, Bundle bundle, Intent intent) throws IOException, GoogleAuthException {
        m3a(intent);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putParcelable("callback_intent", intent);
        bundle.putBoolean(KEY_HANDLE_NOTIFICATION, true);
        return m2a(context, str, str2, bundle);
    }

    public static String getTokenWithNotification(Context context, String str, String str2, Bundle bundle, String str3, Bundle bundle2) throws IOException, GoogleAuthException {
        if (TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Authority cannot be empty or null.");
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        ContentResolver.validateSyncExtrasBundle(bundle2);
        bundle.putString("authority", str3);
        bundle.putBundle("sync_extras", bundle2);
        bundle.putBoolean(KEY_HANDLE_NOTIFICATION, true);
        return m2a(context, str, str2, bundle);
    }

    public static void invalidateToken(Context context, String str) {
        AccountManager.get(context).invalidateAuthToken(GOOGLE_ACCOUNT_TYPE, str);
    }
}
