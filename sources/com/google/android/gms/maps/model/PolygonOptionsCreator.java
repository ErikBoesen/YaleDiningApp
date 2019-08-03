package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;
import java.util.ArrayList;
import java.util.List;

public class PolygonOptionsCreator implements Creator<PolygonOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m387a(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, polygonOptions.mo1819e());
        C0151v.m249a(parcel, 2, polygonOptions.getPoints());
        C0151v.m255b(parcel, 3, polygonOptions.mo1832t());
        C0151v.m244a(parcel, 4, polygonOptions.getStrokeWidth());
        C0151v.m254b(parcel, 5, polygonOptions.getStrokeColor());
        C0151v.m254b(parcel, 6, polygonOptions.getFillColor());
        C0151v.m244a(parcel, 7, polygonOptions.getZIndex());
        C0151v.m250a(parcel, 8, polygonOptions.isVisible());
        C0151v.m250a(parcel, 9, polygonOptions.isGeodesic());
        C0151v.m256c(parcel, a);
    }

    public PolygonOptions createFromParcel(Parcel parcel) {
        float f = BitmapDescriptorFactory.HUE_RED;
        boolean z = false;
        int c = C0094aw.m118c(parcel);
        List list = null;
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < c) {
            int b = C0094aw.m116b(parcel);
            switch (C0094aw.m121e(b)) {
                case 1:
                    i3 = C0094aw.m125h(parcel, b);
                    break;
                case 2:
                    list = C0094aw.m117b(parcel, b, LatLng.CREATOR);
                    break;
                case 3:
                    C0094aw.m115a(parcel, b, arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    f2 = C0094aw.m126i(parcel, b);
                    break;
                case 5:
                    i2 = C0094aw.m125h(parcel, b);
                    break;
                case 6:
                    i = C0094aw.m125h(parcel, b);
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
            return new PolygonOptions(i3, list, arrayList, f2, i2, i, f, z2, z);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public PolygonOptions[] newArray(int i) {
        return new PolygonOptions[i];
    }
}
