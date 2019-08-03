package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcelable;

/* renamed from: com.google.android.gms.internal.g */
public final class C0113g {
    private C0113g() {
    }

    /* renamed from: a */
    public static void m153a(Bundle bundle, String str, Parcelable parcelable) {
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(C0113g.class.getClassLoader());
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }
}
