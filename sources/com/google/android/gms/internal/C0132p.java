package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;
import android.support.p000v4.view.accessibility.AccessibilityEventCompat;
import com.google.android.gms.common.GooglePlayServicesUtil;

/* renamed from: com.google.android.gms.internal.p */
public class C0132p {
    private C0132p() {
    }

    /* renamed from: b */
    public static Intent m194b(String str) {
        Uri fromParts = Uri.fromParts("package", str, null);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }

    /* renamed from: c */
    public static Uri m195c(String str) {
        return Uri.parse("http://play.google.com/store/apps/details").buildUpon().appendQueryParameter("id", str).build();
    }

    /* renamed from: d */
    public static Intent m196d(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(m195c(str));
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
        intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        return intent;
    }

    /* renamed from: e */
    public static Intent m197e(String str) {
        Uri parse = Uri.parse("bazaar://search?q=pname:" + str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(parse);
        intent.setFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        return intent;
    }
}
