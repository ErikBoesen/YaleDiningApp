package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;

public class GroundOverlayOptionsCreator implements Creator<GroundOverlayOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m370a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, groundOverlayOptions.mo1714e());
        C0151v.m246a(parcel, 2, groundOverlayOptions.mo1710ac());
        C0151v.m247a(parcel, 3, groundOverlayOptions.getLocation(), i);
        C0151v.m244a(parcel, 4, groundOverlayOptions.getWidth());
        C0151v.m244a(parcel, 5, groundOverlayOptions.getHeight());
        C0151v.m247a(parcel, 6, groundOverlayOptions.getBounds(), i);
        C0151v.m244a(parcel, 7, groundOverlayOptions.getBearing());
        C0151v.m244a(parcel, 8, groundOverlayOptions.getZIndex());
        C0151v.m250a(parcel, 9, groundOverlayOptions.isVisible());
        C0151v.m244a(parcel, 10, groundOverlayOptions.getTransparency());
        C0151v.m244a(parcel, 11, groundOverlayOptions.getAnchorU());
        C0151v.m244a(parcel, 12, groundOverlayOptions.getAnchorV());
        C0151v.m256c(parcel, a);
    }

    public GroundOverlayOptions createFromParcel(Parcel parcel) {
        int c = C0094aw.m118c(parcel);
        int i = 0;
        IBinder iBinder = null;
        LatLng latLng = null;
        float f = BitmapDescriptorFactory.HUE_RED;
        float f2 = BitmapDescriptorFactory.HUE_RED;
        LatLngBounds latLngBounds = null;
        float f3 = BitmapDescriptorFactory.HUE_RED;
        float f4 = BitmapDescriptorFactory.HUE_RED;
        boolean z = false;
        float f5 = BitmapDescriptorFactory.HUE_RED;
        float f6 = BitmapDescriptorFactory.HUE_RED;
        float f7 = BitmapDescriptorFactory.HUE_RED;
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
                    latLng = (LatLng) C0094aw.m114a(parcel, b, LatLng.CREATOR);
                    break;
                case 4:
                    f = C0094aw.m126i(parcel, b);
                    break;
                case 5:
                    f2 = C0094aw.m126i(parcel, b);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) C0094aw.m114a(parcel, b, LatLngBounds.CREATOR);
                    break;
                case 7:
                    f3 = C0094aw.m126i(parcel, b);
                    break;
                case 8:
                    f4 = C0094aw.m126i(parcel, b);
                    break;
                case 9:
                    z = C0094aw.m123f(parcel, b);
                    break;
                case 10:
                    f5 = C0094aw.m126i(parcel, b);
                    break;
                case 11:
                    f6 = C0094aw.m126i(parcel, b);
                    break;
                case 12:
                    f7 = C0094aw.m126i(parcel, b);
                    break;
                default:
                    C0094aw.m122e(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new GroundOverlayOptions(i, iBinder, latLng, f, f2, latLngBounds, f3, f4, z, f5, f6, f7);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public GroundOverlayOptions[] newArray(int i) {
        return new GroundOverlayOptions[i];
    }
}
