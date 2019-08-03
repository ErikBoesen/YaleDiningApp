package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.C0049ac;

public final class Tile implements C0049ac {
    public static final TileCreator CREATOR = new TileCreator();

    /* renamed from: Q */
    private final int f276Q;

    /* renamed from: bM */
    public final byte[] f277bM;
    public final int height;
    public final int width;

    Tile(int i, int i2, int i3, byte[] bArr) {
        this.f276Q = i;
        this.width = i2;
        this.height = i3;
        this.f277bM = bArr;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr);
    }

    public int describeContents() {
        TileCreator tileCreator = CREATOR;
        return 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1874e() {
        return this.f276Q;
    }

    public void writeToParcel(Parcel parcel, int i) {
        TileCreator tileCreator = CREATOR;
        TileCreator.m391a(this, parcel, i);
    }
}
