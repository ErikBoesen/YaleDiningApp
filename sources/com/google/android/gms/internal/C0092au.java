package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

/* renamed from: com.google.android.gms.internal.au */
public class C0092au {

    /* renamed from: cL */
    Bundle f50cL;

    public C0092au(Bundle bundle) {
        this.f50cL = bundle;
    }

    /* renamed from: ad */
    public boolean mo1277ad() {
        return this.f50cL.getBoolean("has_plus_one", false);
    }

    /* renamed from: ae */
    public String mo1278ae() {
        return this.f50cL.getString("bubble_text");
    }

    /* renamed from: af */
    public String[] mo1279af() {
        return this.f50cL.getStringArray("inline_annotations");
    }

    /* renamed from: ag */
    public Uri[] mo1280ag() {
        Parcelable[] parcelableArray = this.f50cL.getParcelableArray("profile_photo_uris");
        if (parcelableArray == null) {
            return null;
        }
        Uri[] uriArr = new Uri[parcelableArray.length];
        System.arraycopy(parcelableArray, 0, uriArr, 0, parcelableArray.length);
        return uriArr;
    }

    public Intent getIntent() {
        return (Intent) this.f50cL.getParcelable("intent");
    }
}
