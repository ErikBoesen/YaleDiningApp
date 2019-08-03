package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.C0049ac;
import com.google.android.gms.internal.C0091at;
import com.google.android.gms.internal.C0110f.C0111a;

public final class GroundOverlayOptions implements C0049ac {
    public static final GroundOverlayOptionsCreator CREATOR = new GroundOverlayOptionsCreator();
    public static final float NO_DIMENSION = -1.0f;

    /* renamed from: Q */
    private final int f230Q;

    /* renamed from: V */
    private float f231V;

    /* renamed from: W */
    private float f232W;

    /* renamed from: Y */
    private boolean f233Y;

    /* renamed from: aR */
    private float f234aR;

    /* renamed from: bu */
    private float f235bu;

    /* renamed from: cG */
    private BitmapDescriptor f236cG;

    /* renamed from: cH */
    private LatLng f237cH;

    /* renamed from: cI */
    private float f238cI;

    /* renamed from: cJ */
    private LatLngBounds f239cJ;

    /* renamed from: cK */
    private float f240cK;

    /* renamed from: t */
    private float f241t;

    public GroundOverlayOptions() {
        this.f233Y = true;
        this.f240cK = BitmapDescriptorFactory.HUE_RED;
        this.f231V = 0.5f;
        this.f232W = 0.5f;
        this.f230Q = 1;
    }

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7) {
        this.f233Y = true;
        this.f240cK = BitmapDescriptorFactory.HUE_RED;
        this.f231V = 0.5f;
        this.f232W = 0.5f;
        this.f230Q = i;
        this.f236cG = new BitmapDescriptor(C0111a.m152D(iBinder));
        this.f237cH = latLng;
        this.f235bu = f;
        this.f238cI = f2;
        this.f239cJ = latLngBounds;
        this.f241t = f3;
        this.f234aR = f4;
        this.f233Y = z;
        this.f240cK = f5;
        this.f231V = f6;
        this.f232W = f7;
    }

    /* renamed from: a */
    private GroundOverlayOptions m367a(LatLng latLng, float f, float f2) {
        this.f237cH = latLng;
        this.f235bu = f;
        this.f238cI = f2;
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: ac */
    public IBinder mo1710ac() {
        return this.f236cG.mo1676G().asBinder();
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        this.f231V = f;
        this.f232W = f2;
        return this;
    }

    public GroundOverlayOptions bearing(float f) {
        this.f241t = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public int describeContents() {
        GroundOverlayOptionsCreator groundOverlayOptionsCreator = CREATOR;
        return 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1714e() {
        return this.f230Q;
    }

    public float getAnchorU() {
        return this.f231V;
    }

    public float getAnchorV() {
        return this.f232W;
    }

    public float getBearing() {
        return this.f241t;
    }

    public LatLngBounds getBounds() {
        return this.f239cJ;
    }

    public float getHeight() {
        return this.f238cI;
    }

    public BitmapDescriptor getImage() {
        return this.f236cG;
    }

    public LatLng getLocation() {
        return this.f237cH;
    }

    public float getTransparency() {
        return this.f240cK;
    }

    public float getWidth() {
        return this.f235bu;
    }

    public float getZIndex() {
        return this.f234aR;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        this.f236cG = bitmapDescriptor;
        return this;
    }

    public boolean isVisible() {
        return this.f233Y;
    }

    public GroundOverlayOptions position(LatLng latLng, float f) {
        boolean z = true;
        C0091at.m104a(this.f239cJ == null, (Object) "Position has already been set using positionFromBounds");
        C0091at.m106b(latLng != null, "Location must be specified");
        if (f < BitmapDescriptorFactory.HUE_RED) {
            z = false;
        }
        C0091at.m106b(z, "Width must be non-negative");
        return m367a(latLng, f, -1.0f);
    }

    public GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        boolean z = true;
        C0091at.m104a(this.f239cJ == null, (Object) "Position has already been set using positionFromBounds");
        C0091at.m106b(latLng != null, "Location must be specified");
        C0091at.m106b(f >= BitmapDescriptorFactory.HUE_RED, "Width must be non-negative");
        if (f2 < BitmapDescriptorFactory.HUE_RED) {
            z = false;
        }
        C0091at.m106b(z, "Height must be non-negative");
        return m367a(latLng, f, f2);
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        C0091at.m104a(this.f237cH == null, (Object) "Position has already been set using position: " + this.f237cH);
        this.f239cJ = latLngBounds;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        C0091at.m106b(f >= BitmapDescriptorFactory.HUE_RED && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.f240cK = f;
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.f233Y = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        GroundOverlayOptionsCreator groundOverlayOptionsCreator = CREATOR;
        GroundOverlayOptionsCreator.m370a(this, parcel, i);
    }

    public GroundOverlayOptions zIndex(float f) {
        this.f234aR = f;
        return this;
    }
}
