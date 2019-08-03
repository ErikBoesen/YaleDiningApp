package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;
import com.google.android.gms.maps.model.CameraPosition;

public class GoogleMapOptionsCreator implements Creator<GoogleMapOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m341a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, googleMapOptions.mo1504e());
        C0151v.m242a(parcel, 2, googleMapOptions.mo1520v());
        C0151v.m242a(parcel, 3, googleMapOptions.mo1521w());
        C0151v.m254b(parcel, 4, googleMapOptions.getMapType());
        C0151v.m247a(parcel, 5, googleMapOptions.getCamera(), i);
        C0151v.m242a(parcel, 6, googleMapOptions.mo1523x());
        C0151v.m242a(parcel, 7, googleMapOptions.mo1524y());
        C0151v.m242a(parcel, 8, googleMapOptions.mo1525z());
        C0151v.m242a(parcel, 9, googleMapOptions.mo1498A());
        C0151v.m242a(parcel, 10, googleMapOptions.mo1499B());
        C0151v.m242a(parcel, 11, googleMapOptions.mo1500C());
        C0151v.m256c(parcel, a);
    }

    public GoogleMapOptions createFromParcel(Parcel parcel) {
        byte b = 0;
        int c = C0094aw.m118c(parcel);
        CameraPosition cameraPosition = null;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        byte b5 = 0;
        byte b6 = 0;
        int i = 0;
        byte b7 = 0;
        byte b8 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int b9 = C0094aw.m116b(parcel);
            switch (C0094aw.m121e(b9)) {
                case 1:
                    i2 = C0094aw.m125h(parcel, b9);
                    break;
                case 2:
                    b8 = C0094aw.m124g(parcel, b9);
                    break;
                case 3:
                    b7 = C0094aw.m124g(parcel, b9);
                    break;
                case 4:
                    i = C0094aw.m125h(parcel, b9);
                    break;
                case 5:
                    cameraPosition = (CameraPosition) C0094aw.m114a(parcel, b9, CameraPosition.CREATOR);
                    break;
                case 6:
                    b6 = C0094aw.m124g(parcel, b9);
                    break;
                case 7:
                    b5 = C0094aw.m124g(parcel, b9);
                    break;
                case 8:
                    b4 = C0094aw.m124g(parcel, b9);
                    break;
                case 9:
                    b3 = C0094aw.m124g(parcel, b9);
                    break;
                case 10:
                    b2 = C0094aw.m124g(parcel, b9);
                    break;
                case 11:
                    b = C0094aw.m124g(parcel, b9);
                    break;
                default:
                    C0094aw.m122e(parcel, b9);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new GoogleMapOptions(i2, b8, b7, i, cameraPosition, b6, b5, b4, b3, b2, b);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public GoogleMapOptions[] newArray(int i) {
        return new GoogleMapOptions[i];
    }
}
