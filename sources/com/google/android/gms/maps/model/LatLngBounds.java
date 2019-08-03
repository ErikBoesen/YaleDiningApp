package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.C0049ac;
import com.google.android.gms.internal.C0081ap;
import com.google.android.gms.internal.C0091at;

public final class LatLngBounds implements C0049ac {
    public static final LatLngBoundsCreator CREATOR = new LatLngBoundsCreator();

    /* renamed from: Q */
    private final int f243Q;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {

        /* renamed from: A */
        private double f244A = Double.POSITIVE_INFINITY;

        /* renamed from: B */
        private double f245B = Double.NEGATIVE_INFINITY;

        /* renamed from: C */
        private double f246C = Double.NaN;

        /* renamed from: D */
        private double f247D = Double.NaN;

        /* renamed from: a */
        private boolean m379a(double d) {
            boolean z = false;
            if (this.f246C <= this.f247D) {
                return this.f246C <= d && d <= this.f247D;
            }
            if (this.f246C <= d || d <= this.f247D) {
                z = true;
            }
            return z;
        }

        public LatLngBounds build() {
            C0091at.m104a(!Double.isNaN(this.f246C), (Object) "no included points");
            return new LatLngBounds(new LatLng(this.f244A, this.f246C), new LatLng(this.f245B, this.f247D));
        }

        public Builder include(LatLng latLng) {
            this.f244A = Math.min(this.f244A, latLng.latitude);
            this.f245B = Math.max(this.f245B, latLng.latitude);
            double d = latLng.longitude;
            if (Double.isNaN(this.f246C)) {
                this.f246C = d;
                this.f247D = d;
            } else if (!m379a(d)) {
                if (LatLngBounds.m372a(this.f246C, d) < LatLngBounds.m374b(this.f247D, d)) {
                    this.f246C = d;
                } else {
                    this.f247D = d;
                }
            }
            return this;
        }
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        C0091at.m102a(latLng, (Object) "null southwest");
        C0091at.m102a(latLng2, (Object) "null northeast");
        C0091at.m105a(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude));
        this.f243Q = i;
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public static double m372a(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    /* renamed from: a */
    private boolean m373a(double d) {
        boolean z = false;
        if (this.southwest.longitude <= this.northeast.longitude) {
            return this.southwest.longitude <= d && d <= this.northeast.longitude;
        }
        if (this.southwest.longitude <= d || d <= this.northeast.longitude) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static double m374b(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    /* renamed from: b */
    private boolean m375b(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean contains(LatLng latLng) {
        return m375b(latLng.latitude) && m373a(latLng.longitude);
    }

    public int describeContents() {
        LatLngBoundsCreator latLngBoundsCreator = CREATOR;
        return 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1744e() {
        return this.f243Q;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public int hashCode() {
        return C0081ap.hashCode(this.southwest, this.northeast);
    }

    public LatLngBounds including(LatLng latLng) {
        double d;
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        double d4 = latLng.longitude;
        if (m373a(d4)) {
            d4 = d3;
            d = d2;
        } else if (m372a(d3, d4) < m374b(d2, d4)) {
            d = d2;
        } else {
            double d5 = d3;
            d = d4;
            d4 = d5;
        }
        return new LatLngBounds(new LatLng(min, d4), new LatLng(max, d));
    }

    public String toString() {
        return C0081ap.m93d(this).mo1269a("southwest", this.southwest).mo1269a("northeast", this.northeast).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        LatLngBoundsCreator latLngBoundsCreator = CREATOR;
        LatLngBoundsCreator.m380a(this, parcel, i);
    }
}
