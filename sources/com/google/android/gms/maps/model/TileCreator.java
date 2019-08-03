package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0094aw;
import com.google.android.gms.internal.C0094aw.C0095a;
import com.google.android.gms.internal.C0151v;

public class TileCreator implements Creator<Tile> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* renamed from: a */
    static void m391a(Tile tile, Parcel parcel, int i) {
        int a = C0151v.m240a(parcel);
        C0151v.m254b(parcel, 1, tile.mo1874e());
        C0151v.m254b(parcel, 2, tile.width);
        C0151v.m254b(parcel, 3, tile.height);
        C0151v.m251a(parcel, 4, tile.f277bM);
        C0151v.m256c(parcel, a);
    }

    public Tile createFromParcel(Parcel parcel) {
        int i = 0;
        int c = C0094aw.m118c(parcel);
        byte[] bArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < c) {
            int b = C0094aw.m116b(parcel);
            switch (C0094aw.m121e(b)) {
                case 1:
                    i3 = C0094aw.m125h(parcel, b);
                    break;
                case 2:
                    i2 = C0094aw.m125h(parcel, b);
                    break;
                case 3:
                    i = C0094aw.m125h(parcel, b);
                    break;
                case 4:
                    bArr = C0094aw.m130m(parcel, b);
                    break;
                default:
                    C0094aw.m122e(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new Tile(i3, i2, i, bArr);
        }
        throw new C0095a("Overread allowed size end=" + c, parcel);
    }

    public Tile[] newArray(int i) {
        return new Tile[i];
    }
}
