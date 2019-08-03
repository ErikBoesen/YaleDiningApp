package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;

public class VisibleRegionCreator implements Creator<VisibleRegion> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m399a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, visibleRegion.mo1902e());
        C0151v.m247a(parcel, 2, visibleRegion.nearLeft, i);
        C0151v.m247a(parcel, 3, visibleRegion.nearRight, i);
        C0151v.m247a(parcel, 4, visibleRegion.farLeft, i);
        C0151v.m247a(parcel, 5, visibleRegion.farRight, i);
        C0151v.m247a(parcel, 6, visibleRegion.latLngBounds, i);
        C0151v.m256c(parcel, a);
    }

    public VisibleRegion createFromParcel(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int c = C0094aw.m118c(parcel);
        int i = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < c) {
            int b = C0094aw.m116b(parcel);
            switch (C0094aw.m121e(b)) {
                case 1:
                    i = C0094aw.m125h(parcel, b);
                    break;
                case 2:
                    latLng4 = (LatLng) C0094aw.m114a(parcel, b, LatLng.CREATOR);
                    break;
                case 3:
                    latLng3 = (LatLng) C0094aw.m114a(parcel, b, LatLng.CREATOR);
                    break;
                case 4:
                    latLng2 = (LatLng) C0094aw.m114a(parcel, b, LatLng.CREATOR);
                    break;
                case 5:
                    latLng = (LatLng) C0094aw.m114a(parcel, b, LatLng.CREATOR);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) C0094aw.m114a(parcel, b, LatLngBounds.CREATOR);
                    break;
                default:
                    C0094aw.m122e(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new VisibleRegion(i, latLng4, latLng3, latLng2, latLng, latLngBounds);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public VisibleRegion[] newArray(int i) {
        return new VisibleRegion[i];
    }
}
