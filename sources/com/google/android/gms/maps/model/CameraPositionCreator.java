package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;

public class CameraPositionCreator implements Creator<CameraPosition> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m366a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, cameraPosition.mo1678e());
        C0151v.m247a(parcel, 2, cameraPosition.target, i);
        C0151v.m244a(parcel, 3, cameraPosition.zoom);
        C0151v.m244a(parcel, 4, cameraPosition.tilt);
        C0151v.m244a(parcel, 5, cameraPosition.bearing);
        C0151v.m256c(parcel, a);
    }

    public CameraPosition createFromParcel(Parcel parcel) {
        float f = BitmapDescriptorFactory.HUE_RED;
        int c = C0094aw.m118c(parcel);
        int i = 0;
        LatLng latLng = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
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
                    f3 = C0094aw.m126i(parcel, b);
                    break;
                case 4:
                    f2 = C0094aw.m126i(parcel, b);
                    break;
                case 5:
                    f = C0094aw.m126i(parcel, b);
                    break;
                default:
                    C0094aw.m122e(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new CameraPosition(i, latLng, f3, f2, f);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public CameraPosition[] newArray(int i) {
        return new CameraPosition[i];
    }
}
