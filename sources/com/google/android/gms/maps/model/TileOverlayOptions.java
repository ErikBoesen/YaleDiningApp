package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.C0049ac;
import com.google.android.gms.internal.C0056af;
import com.google.android.gms.internal.C0056af.C0057a;

public final class TileOverlayOptions implements C0049ac {
    public static final TileOverlayOptionsCreator CREATOR = new TileOverlayOptionsCreator();

    /* renamed from: Q */
    private final int f279Q;

    /* renamed from: Y */
    private boolean f280Y;

    /* renamed from: aR */
    private float f281aR;
    /* access modifiers changed from: private */

    /* renamed from: bl */
    public C0056af f282bl;

    /* renamed from: bm */
    private TileProvider f283bm;

    public TileOverlayOptions() {
        this.f280Y = true;
        this.f279Q = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f) {
        this.f280Y = true;
        this.f279Q = i;
        this.f282bl = C0057a.m40I(iBinder);
        this.f283bm = this.f282bl == null ? null : new TileProvider() {

            /* renamed from: bJ */
            private final C0056af f284bJ = TileOverlayOptions.this.f282bl;

            public Tile getTile(int i, int i2, int i3) {
                try {
                    return this.f284bJ.getTile(i, i2, i3);
                } catch (RemoteException e) {
                    return null;
                }
            }
        };
        this.f280Y = z;
        this.f281aR = f;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: F */
    public IBinder mo1887F() {
        return this.f282bl.asBinder();
    }

    public int describeContents() {
        TileOverlayOptionsCreator tileOverlayOptionsCreator = CREATOR;
        return 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1889e() {
        return this.f279Q;
    }

    public TileProvider getTileProvider() {
        return this.f283bm;
    }

    public float getZIndex() {
        return this.f281aR;
    }

    public boolean isVisible() {
        return this.f280Y;
    }

    public TileOverlayOptions tileProvider(final TileProvider tileProvider) {
        this.f283bm = tileProvider;
        this.f282bl = this.f283bm == null ? null : new C0057a() {
            public Tile getTile(int i, int i2, int i3) {
                return tileProvider.getTile(i, i2, i3);
            }
        };
        return this;
    }

    public TileOverlayOptions visible(boolean z) {
        this.f280Y = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        TileOverlayOptionsCreator tileOverlayOptionsCreator = CREATOR;
        TileOverlayOptionsCreator.m395a(this, parcel, i);
    }

    public TileOverlayOptions zIndex(float f) {
        this.f281aR = f;
        return this;
    }
}
