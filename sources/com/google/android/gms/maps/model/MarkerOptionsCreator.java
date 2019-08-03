package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;

public class MarkerOptionsCreator implements Creator<MarkerOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m384a(MarkerOptions markerOptions, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, markerOptions.mo1776e());
        C0151v.m247a(parcel, 2, markerOptions.getPosition(), i);
        C0151v.m248a(parcel, 3, markerOptions.getTitle());
        C0151v.m248a(parcel, 4, markerOptions.getSnippet());
        C0151v.m246a(parcel, 5, markerOptions.mo1777f());
        C0151v.m244a(parcel, 6, markerOptions.getAnchorU());
        C0151v.m244a(parcel, 7, markerOptions.getAnchorV());
        C0151v.m250a(parcel, 8, markerOptions.isDraggable());
        C0151v.m250a(parcel, 9, markerOptions.isVisible());
        C0151v.m256c(parcel, a);
    }

    public MarkerOptions createFromParcel(Parcel parcel) {
        float f = BitmapDescriptorFactory.HUE_RED;
        boolean z = false;
        IBinder iBinder = null;
        int c = C0094aw.m118c(parcel);
        boolean z2 = false;
        float f2 = 0.0f;
        String str = null;
        String str2 = null;
        LatLng latLng = null;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int b = C0094aw.m116b(parcel);
            switch (C0094aw.m121e(b)) {
                case 1:
                    i = C0094aw.m125h(parcel, b);
                    break;
                case 2:
                    latLng = (LatLng) C0094aw.m114a(parcel, b, LatLng.CREATOR);
                    break;
                case 3:
                    str2 = C0094aw.m128k(parcel, b);
                    break;
                case 4:
                    str = C0094aw.m128k(parcel, b);
                    break;
                case 5:
                    iBinder = C0094aw.m129l(parcel, b);
                    break;
                case 6:
                    f2 = C0094aw.m126i(parcel, b);
                    break;
                case 7:
                    f = C0094aw.m126i(parcel, b);
                    break;
                case 8:
                    z2 = C0094aw.m123f(parcel, b);
                    break;
                case 9:
                    z = C0094aw.m123f(parcel, b);
                    break;
                default:
                    C0094aw.m122e(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new MarkerOptions(i, latLng, str2, str, iBinder, f2, f, z2, z);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public MarkerOptions[] newArray(int i) {
        return new MarkerOptions[i];
    }
}
