package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;

public class TileOverlayOptionsCreator implements Creator<TileOverlayOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m395a(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, tileOverlayOptions.mo1889e());
        C0151v.m246a(parcel, 2, tileOverlayOptions.mo1887F());
        C0151v.m250a(parcel, 3, tileOverlayOptions.isVisible());
        C0151v.m244a(parcel, 4, tileOverlayOptions.getZIndex());
        C0151v.m256c(parcel, a);
    }

    public TileOverlayOptions createFromParcel(Parcel parcel) {
        boolean z = false;
        int c = C0094aw.m118c(parcel);
        IBinder iBinder = null;
        float f = BitmapDescriptorFactory.HUE_RED;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int b = C0094aw.m116b(parcel);
            switch (C0094aw.m121e(b)) {
                case 1:
                    i = C0094aw.m125h(parcel, b);
                    break;
                case 2:
                    iBinder = C0094aw.m129l(parcel, b);
                    break;
                case 3:
                    z = C0094aw.m123f(parcel, b);
                    break;
                case 4:
                    f = C0094aw.m126i(parcel, b);
                    break;
                default:
                    C0094aw.m122e(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new TileOverlayOptions(i, iBinder, z, f);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public TileOverlayOptions[] newArray(int i) {
        return new TileOverlayOptions[i];
    }
}
