package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;

public class LatLngCreator implements Creator<LatLng> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m381a(LatLng latLng, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, latLng.mo1737e());
        C0151v.m243a(parcel, 2, latLng.latitude);
        C0151v.m243a(parcel, 3, latLng.longitude);
        C0151v.m256c(parcel, a);
    }

    public LatLng createFromParcel(Parcel parcel) {
        double d = 0.0d;
        int c = C0094aw.m118c(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < c) {
            int b = C0094aw.m116b(parcel);
            switch (C0094aw.m121e(b)) {
                case 1:
                    i = C0094aw.m125h(parcel, b);
                    break;
                case 2:
                    d2 = C0094aw.m127j(parcel, b);
                    break;
                case 3:
                    d = C0094aw.m127j(parcel, b);
                    break;
                default:
                    C0094aw.m122e(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new LatLng(i, d2, d);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public LatLng[] newArray(int i) {
        return new LatLng[i];
    }
}
