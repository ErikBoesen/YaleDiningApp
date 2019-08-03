package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.C0049ac;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolylineOptions implements C0049ac {
    public static final PolylineOptionsCreator CREATOR = new PolylineOptionsCreator();

    /* renamed from: Q */
    private final int f269Q;

    /* renamed from: Y */
    private boolean f270Y;

    /* renamed from: aM */
    private final List<LatLng> f271aM;

    /* renamed from: aR */
    private float f272aR;

    /* renamed from: aS */
    private boolean f273aS;

    /* renamed from: bu */
    private float f274bu;

    /* renamed from: bv */
    private int f275bv;

    public PolylineOptions() {
        this.f274bu = 10.0f;
        this.f275bv = -16777216;
        this.f272aR = BitmapDescriptorFactory.HUE_RED;
        this.f270Y = true;
        this.f273aS = false;
        this.f269Q = 1;
        this.f271aM = new ArrayList();
    }

    PolylineOptions(int i, List list, float f, int i2, float f2, boolean z, boolean z2) {
        this.f274bu = 10.0f;
        this.f275bv = -16777216;
        this.f272aR = BitmapDescriptorFactory.HUE_RED;
        this.f270Y = true;
        this.f273aS = false;
        this.f269Q = i;
        this.f271aM = list;
        this.f274bu = f;
        this.f275bv = i2;
        this.f272aR = f2;
        this.f270Y = z;
        this.f273aS = z2;
    }

    public PolylineOptions add(LatLng latLng) {
        this.f271aM.add(latLng);
        return this;
    }

    public PolylineOptions add(LatLng... latLngArr) {
        this.f271aM.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f271aM.add(add);
        }
        return this;
    }

    public PolylineOptions color(int i) {
        this.f275bv = i;
        return this;
    }

    public int describeContents() {
        PolylineOptionsCreator polylineOptionsCreator = CREATOR;
        return 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1859e() {
        return this.f269Q;
    }

    public PolylineOptions geodesic(boolean z) {
        this.f273aS = z;
        return this;
    }

    public int getColor() {
        return this.f275bv;
    }

    public List<LatLng> getPoints() {
        return this.f271aM;
    }

    public float getWidth() {
        return this.f274bu;
    }

    public float getZIndex() {
        return this.f272aR;
    }

    public boolean isGeodesic() {
        return this.f273aS;
    }

    public boolean isVisible() {
        return this.f270Y;
    }

    public PolylineOptions visible(boolean z) {
        this.f270Y = z;
        return this;
    }

    public PolylineOptions width(float f) {
        this.f274bu = f;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        PolylineOptionsCreator polylineOptionsCreator = CREATOR;
        PolylineOptionsCreator.m389a(this, parcel, i);
    }

    public PolylineOptions zIndex(float f) {
        this.f272aR = f;
        return this;
    }
}
