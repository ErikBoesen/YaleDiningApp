package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.C0049ac;
import com.google.android.gms.internal.C0081ap;

public final class VisibleRegion implements C0049ac {
    public static final VisibleRegionCreator CREATOR = new VisibleRegionCreator();

    /* renamed from: Q */
    private final int f290Q;
    public final LatLng farLeft;
    public final LatLng farRight;
    public final LatLngBounds latLngBounds;
    public final LatLng nearLeft;
    public final LatLng nearRight;

    VisibleRegion(int i, LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds2) {
        this.f290Q = i;
        this.nearLeft = latLng;
        this.nearRight = latLng2;
        this.farLeft = latLng3;
        this.farRight = latLng4;
        this.latLngBounds = latLngBounds2;
    }

    public VisibleRegion(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds2) {
        this(1, latLng, latLng2, latLng3, latLng4, latLngBounds2);
    }

    public int describeContents() {
        VisibleRegionCreator visibleRegionCreator = CREATOR;
        return 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1902e() {
        return this.f290Q;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisibleRegion)) {
            return false;
        }
        VisibleRegion visibleRegion = (VisibleRegion) obj;
        return this.nearLeft.equals(visibleRegion.nearLeft) && this.nearRight.equals(visibleRegion.nearRight) && this.farLeft.equals(visibleRegion.farLeft) && this.farRight.equals(visibleRegion.farRight) && this.latLngBounds.equals(visibleRegion.latLngBounds);
    }

    public int hashCode() {
        return C0081ap.hashCode(this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds);
    }

    public String toString() {
        return C0081ap.m93d(this).mo1269a("nearLeft", this.nearLeft).mo1269a("nearRight", this.nearRight).mo1269a("farLeft", this.farLeft).mo1269a("farRight", this.farRight).mo1269a("latLngBounds", this.latLngBounds).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        VisibleRegionCreator visibleRegionCreator = CREATOR;
        VisibleRegionCreator.m399a(this, parcel, i);
    }
}
