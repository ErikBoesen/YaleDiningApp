package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;

public class LatLngBoundsCreator implements Creator<LatLngBounds> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m380a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, latLngBounds.mo1744e());
        C0151v.m247a(parcel, 2, latLngBounds.southwest, i);
        C0151v.m247a(parcel, 3, latLngBounds.northeast, i);
        C0151v.m256c(parcel, a);
    }

    public LatLngBounds createFromParcel(Parcel parcel) {
        LatLng latLng;
        LatLng latLng2;
        int i;
        LatLng latLng3 = null;
        int c = C0094aw.m118c(parcel);
        int i2 = 0;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < c) {
            int b = C0094aw.m116b(parcel);
            switch (C0094aw.m121e(b)) {
                case 1:
                    LatLng latLng5 = latLng3;
                    latLng2 = latLng4;
                    i = C0094aw.m125h(parcel, b);
                    latLng = latLng5;
                    break;
                case 2:
                    i = i2;
                    LatLng latLng6 = (LatLng) C0094aw.m114a(parcel, b, LatLng.CREATOR);
                    latLng = latLng3;
                    latLng2 = latLng6;
                    break;
                case 3:
                    latLng = (LatLng) C0094aw.m114a(parcel, b, LatLng.CREATOR);
                    latLng2 = latLng4;
                    i = i2;
                    break;
                default:
                    C0094aw.m122e(parcel, b);
                    latLng = latLng3;
                    latLng2 = latLng4;
                    i = i2;
                    break;
            }
            i2 = i;
            latLng4 = latLng2;
            latLng3 = latLng;
        }
        if (parcel.dataPosition() == c) {
            return new LatLngBounds(i2, latLng4, latLng3);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public LatLngBounds[] newArray(int i) {
        return new LatLngBounds[i];
    }
}
