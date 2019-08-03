package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.C0042R;
import com.google.android.gms.internal.C0049ac;
import com.google.android.gms.internal.C0081ap;
import com.google.android.gms.internal.C0091at;

public final class CameraPosition implements C0049ac {
    public static final CameraPositionCreator CREATOR = new CameraPositionCreator();

    /* renamed from: Q */
    private final int f224Q;
    public final float bearing;
    public final LatLng target;
    public final float tilt;
    public final float zoom;

    public static final class Builder {

        /* renamed from: q */
        private LatLng f225q;

        /* renamed from: r */
        private float f226r;

        /* renamed from: s */
        private float f227s;

        /* renamed from: t */
        private float f228t;

        public Builder() {
        }

        public Builder(CameraPosition cameraPosition) {
            this.f225q = cameraPosition.target;
            this.f226r = cameraPosition.zoom;
            this.f227s = cameraPosition.tilt;
            this.f228t = cameraPosition.bearing;
        }

        public Builder bearing(float f) {
            this.f228t = f;
            return this;
        }

        public CameraPosition build() {
            return new CameraPosition(this.f225q, this.f226r, this.f227s, this.f228t);
        }

        public Builder target(LatLng latLng) {
            this.f225q = latLng;
            return this;
        }

        public Builder tilt(float f) {
            this.f227s = f;
            return this;
        }

        public Builder zoom(float f) {
            this.f226r = f;
            return this;
        }
    }

    CameraPosition(int i, LatLng latLng, float f, float f2, float f3) {
        C0091at.m102a(latLng, (Object) "null camera target");
        C0091at.m106b(BitmapDescriptorFactory.HUE_RED <= f2 && f2 <= 90.0f, "Tilt needs to be between 0 and 90 inclusive");
        this.f224Q = i;
        this.target = latLng;
        this.zoom = f;
        this.tilt = f2 + BitmapDescriptorFactory.HUE_RED;
        if (((double) f3) <= 0.0d) {
            f3 = (f3 % 360.0f) + 360.0f;
        }
        this.bearing = f3 % 360.0f;
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        this(1, latLng, f, f2, f3);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(CameraPosition cameraPosition) {
        return new Builder(cameraPosition);
    }

    public static CameraPosition createFromAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C0042R.styleable.MapAttrs);
        LatLng latLng = new LatLng((double) (obtainAttributes.hasValue(2) ? obtainAttributes.getFloat(2, BitmapDescriptorFactory.HUE_RED) : 0.0f), (double) (obtainAttributes.hasValue(3) ? obtainAttributes.getFloat(3, BitmapDescriptorFactory.HUE_RED) : 0.0f));
        Builder builder = builder();
        builder.target(latLng);
        if (obtainAttributes.hasValue(5)) {
            builder.zoom(obtainAttributes.getFloat(5, BitmapDescriptorFactory.HUE_RED));
        }
        if (obtainAttributes.hasValue(1)) {
            builder.bearing(obtainAttributes.getFloat(1, BitmapDescriptorFactory.HUE_RED));
        }
        if (obtainAttributes.hasValue(4)) {
            builder.tilt(obtainAttributes.getFloat(4, BitmapDescriptorFactory.HUE_RED));
        }
        return builder.build();
    }

    public static final CameraPosition fromLatLngZoom(LatLng latLng, float f) {
        return new CameraPosition(latLng, f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
    }

    public int describeContents() {
        CameraPositionCreator cameraPositionCreator = CREATOR;
        return 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1678e() {
        return this.f224Q;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        return this.target.equals(cameraPosition.target) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(cameraPosition.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(cameraPosition.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(cameraPosition.bearing);
    }

    public int hashCode() {
        return C0081ap.hashCode(this.target, Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing));
    }

    public String toString() {
        return C0081ap.m93d(this).mo1269a("target", this.target).mo1269a("zoom", Float.valueOf(this.zoom)).mo1269a("tilt", Float.valueOf(this.tilt)).mo1269a("bearing", Float.valueOf(this.bearing)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        CameraPositionCreator cameraPositionCreator = CREATOR;
        CameraPositionCreator.m366a(this, parcel, i);
    }
}
