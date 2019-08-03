package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.C0049ac;
import com.google.android.gms.internal.C0110f.C0111a;

public final class MarkerOptions implements C0049ac {
    public static final MarkerOptionsCreator CREATOR = new MarkerOptionsCreator();

    /* renamed from: Q */
    private final int f249Q;

    /* renamed from: R */
    private LatLng f250R;

    /* renamed from: S */
    private String f251S;

    /* renamed from: T */
    private String f252T;

    /* renamed from: U */
    private BitmapDescriptor f253U;

    /* renamed from: V */
    private float f254V;

    /* renamed from: W */
    private float f255W;

    /* renamed from: X */
    private boolean f256X;

    /* renamed from: Y */
    private boolean f257Y;

    public MarkerOptions() {
        this.f254V = 0.5f;
        this.f255W = 1.0f;
        this.f257Y = true;
        this.f249Q = 1;
    }

    MarkerOptions(int i, LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2) {
        this.f254V = 0.5f;
        this.f255W = 1.0f;
        this.f257Y = true;
        this.f249Q = i;
        this.f250R = latLng;
        this.f251S = str;
        this.f252T = str2;
        this.f253U = iBinder == null ? null : new BitmapDescriptor(C0111a.m152D(iBinder));
        this.f254V = f;
        this.f255W = f2;
        this.f256X = z;
        this.f257Y = z2;
    }

    public MarkerOptions anchor(float f, float f2) {
        this.f254V = f;
        this.f255W = f2;
        return this;
    }

    public int describeContents() {
        MarkerOptionsCreator markerOptionsCreator = CREATOR;
        return 0;
    }

    public MarkerOptions draggable(boolean z) {
        this.f256X = z;
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1776e() {
        return this.f249Q;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public IBinder mo1777f() {
        if (this.f253U == null) {
            return null;
        }
        return this.f253U.mo1676G().asBinder();
    }

    public float getAnchorU() {
        return this.f254V;
    }

    public float getAnchorV() {
        return this.f255W;
    }

    public BitmapDescriptor getIcon() {
        return this.f253U;
    }

    public LatLng getPosition() {
        return this.f250R;
    }

    public String getSnippet() {
        return this.f252T;
    }

    public String getTitle() {
        return this.f251S;
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        this.f253U = bitmapDescriptor;
        return this;
    }

    public boolean isDraggable() {
        return this.f256X;
    }

    public boolean isVisible() {
        return this.f257Y;
    }

    public MarkerOptions position(LatLng latLng) {
        this.f250R = latLng;
        return this;
    }

    public MarkerOptions snippet(String str) {
        this.f252T = str;
        return this;
    }

    public MarkerOptions title(String str) {
        this.f251S = str;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.f257Y = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        MarkerOptionsCreator markerOptionsCreator = CREATOR;
        MarkerOptionsCreator.m384a(this, parcel, i);
    }
}
