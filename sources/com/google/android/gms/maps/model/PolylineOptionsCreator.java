package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;
import java.util.List;

public class PolylineOptionsCreator implements Creator<PolylineOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m389a(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, polylineOptions.mo1859e());
        C0151v.m249a(parcel, 2, polylineOptions.getPoints());
        C0151v.m244a(parcel, 3, polylineOptions.getWidth());
        C0151v.m254b(parcel, 4, polylineOptions.getColor());
        C0151v.m244a(parcel, 5, polylineOptions.getZIndex());
        C0151v.m250a(parcel, 6, polylineOptions.isVisible());
        C0151v.m250a(parcel, 7, polylineOptions.isGeodesic());
        C0151v.m256c(parcel, a);
    }

    public PolylineOptions createFromParcel(Parcel parcel) {
        float f = BitmapDescriptorFactory.HUE_RED;
        boolean z = false;
        int c = C0094aw.m118c(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int b = C0094aw.m116b(parcel);
            switch (C0094aw.m121e(b)) {
                case 1:
                    i2 = C0094aw.m125h(parcel, b);
                    break;
                case 2:
                    list = C0094aw.m117b(parcel, b, LatLng.CREATOR);
                    break;
                case 3:
                    f2 = C0094aw.m126i(parcel, b);
                    break;
                case 4:
                    i = C0094aw.m125h(parcel, b);
                    break;
                case 5:
                    f = C0094aw.m126i(parcel, b);
                    break;
                case 6:
                    z2 = C0094aw.m123f(parcel, b);
                    break;
                case 7:
                    z = C0094aw.m123f(parcel, b);
                    break;
                default:
                    C0094aw.m122e(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new PolylineOptions(i2, list, f2, i, f, z2, z);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public PolylineOptions[] newArray(int i) {
        return new PolylineOptions[i];
    }
}
