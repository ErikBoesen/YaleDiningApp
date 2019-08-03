package com.google.android.gms.internal;

import android.graphics.Color;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

/* renamed from: com.google.android.gms.internal.e */
public final class C0109e {

    /* renamed from: h */
    public static final int f72h = Color.argb(30, 0, 0, 0);

    /* renamed from: i */
    private static final Uri f73i = new Builder().scheme("android.resource").authority(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE).appendPath(ImageDownloader.SCHEME_DRAWABLE).build();

    /* renamed from: a */
    public static Uri m151a(String str) {
        C0091at.m102a(str, (Object) "Resource name must not be null.");
        return f73i.buildUpon().appendPath(str).build();
    }
}
