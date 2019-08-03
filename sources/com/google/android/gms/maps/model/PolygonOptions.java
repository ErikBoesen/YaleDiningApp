package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.C0049ac;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolygonOptions implements C0049ac {
    public static final PolygonOptionsCreator CREATOR = new PolygonOptionsCreator();

    /* renamed from: Q */
    private final int f259Q;

    /* renamed from: Y */
    private boolean f260Y;

    /* renamed from: aM */
    private final List<LatLng> f261aM;

    /* renamed from: aN */
    private final List<List<LatLng>> f262aN;

    /* renamed from: aO */
    private float f263aO;

    /* renamed from: aP */
    private int f264aP;

    /* renamed from: aQ */
    private int f265aQ;

    /* renamed from: aR */
    private float f266aR;

    /* renamed from: aS */
    private boolean f267aS;

    public PolygonOptions() {
        this.f263aO = 10.0f;
        this.f264aP = -16777216;
        this.f265aQ = 0;
        this.f266aR = BitmapDescriptorFactory.HUE_RED;
        this.f260Y = true;
        this.f267aS = false;
        this.f259Q = 1;
        this.f261aM = new ArrayList();
        this.f262aN = new ArrayList();
    }

    PolygonOptions(int i, List<LatLng> list, List list2, float f, int i2, int i3, float f2, boolean z, boolean z2) {
        this.f263aO = 10.0f;
        this.f264aP = -16777216;
        this.f265aQ = 0;
        this.f266aR = BitmapDescriptorFactory.HUE_RED;
        this.f260Y = true;
        this.f267aS = false;
        this.f259Q = i;
        this.f261aM = list;
        this.f262aN = list2;
        this.f263aO = f;
        this.f264aP = i2;
        this.f265aQ = i3;
        this.f266aR = f2;
        this.f260Y = z;
        this.f267aS = z2;
    }

    public PolygonOptions add(LatLng latLng) {
        this.f261aM.add(latLng);
        return this;
    }

    public PolygonOptions add(LatLng... latLngArr) {
        this.f261aM.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f261aM.add(add);
        }
        return this;
    }

    public PolygonOptions addHole(Iterable<LatLng> iterable) {
        ArrayList arrayList = new ArrayList();
        for (LatLng add : iterable) {
            arrayList.add(add);
        }
        this.f262aN.add(arrayList);
        return this;
    }

    public int describeContents() {
        PolygonOptionsCreator polygonOptionsCreator = CREATOR;
        return 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1819e() {
        return this.f259Q;
    }

    public PolygonOptions fillColor(int i) {
        this.f265aQ = i;
        return this;
    }

    public PolygonOptions geodesic(boolean z) {
        this.f267aS = z;
        return this;
    }

    public int getFillColor() {
        return this.f265aQ;
    }

    public List<List<LatLng>> getHoles() {
        return this.f262aN;
    }

    public List<LatLng> getPoints() {
        return this.f261aM;
    }

    public int getStrokeColor() {
        return this.f264aP;
    }

    public float getStrokeWidth() {
        return this.f263aO;
    }

    public float getZIndex() {
        return this.f266aR;
    }

    public boolean isGeodesic() {
        return this.f267aS;
    }

    public boolean isVisible() {
        return this.f260Y;
    }

    public PolygonOptions strokeColor(int i) {
        this.f264aP = i;
        return this;
    }

    public PolygonOptions strokeWidth(float f) {
        this.f263aO = f;
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: t */
    public List mo1832t() {
        return this.f262aN;
    }

    public PolygonOptions visible(boolean z) {
        this.f260Y = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        PolygonOptionsCreator polygonOptionsCreator = CREATOR;
        PolygonOptionsCreator.m387a(this, parcel, i);
    }

    public PolygonOptions zIndex(float f) {
        this.f266aR = f;
        return this;
    }
}
