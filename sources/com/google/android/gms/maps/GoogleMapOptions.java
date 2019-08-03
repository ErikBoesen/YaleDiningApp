package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.C0042R;
import com.google.android.gms.internal.C0049ac;
import com.google.android.gms.internal.C0093av;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions implements C0049ac {
    public static final GoogleMapOptionsCreator CREATOR = new GoogleMapOptionsCreator();

    /* renamed from: Q */
    private final int f180Q;

    /* renamed from: aW */
    private Boolean f181aW;

    /* renamed from: aX */
    private Boolean f182aX;

    /* renamed from: aY */
    private int f183aY;

    /* renamed from: aZ */
    private CameraPosition f184aZ;

    /* renamed from: ba */
    private Boolean f185ba;

    /* renamed from: bb */
    private Boolean f186bb;

    /* renamed from: bc */
    private Boolean f187bc;

    /* renamed from: bd */
    private Boolean f188bd;

    /* renamed from: be */
    private Boolean f189be;

    /* renamed from: bf */
    private Boolean f190bf;

    public GoogleMapOptions() {
        this.f183aY = -1;
        this.f180Q = 1;
    }

    GoogleMapOptions(int i, byte b, byte b2, int i2, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        this.f183aY = -1;
        this.f180Q = i;
        this.f181aW = C0093av.m112a(b);
        this.f182aX = C0093av.m112a(b2);
        this.f183aY = i2;
        this.f184aZ = cameraPosition;
        this.f185ba = C0093av.m112a(b3);
        this.f186bb = C0093av.m112a(b4);
        this.f187bc = C0093av.m112a(b5);
        this.f188bd = C0093av.m112a(b6);
        this.f189be = C0093av.m112a(b7);
        this.f190bf = C0093av.m112a(b8);
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C0042R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(0)) {
            googleMapOptions.mapType(obtainAttributes.getInt(0, -1));
        }
        if (obtainAttributes.hasValue(13)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(13, false));
        }
        if (obtainAttributes.hasValue(12)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(12, false));
        }
        if (obtainAttributes.hasValue(6)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(6, true));
        }
        if (obtainAttributes.hasValue(7)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(7, true));
        }
        if (obtainAttributes.hasValue(8)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(8, true));
        }
        if (obtainAttributes.hasValue(9)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(9, true));
        }
        if (obtainAttributes.hasValue(11)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(11, true));
        }
        if (obtainAttributes.hasValue(10)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(10, true));
        }
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: A */
    public byte mo1498A() {
        return C0093av.m113b(this.f188bd);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: B */
    public byte mo1499B() {
        return C0093av.m113b(this.f189be);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: C */
    public byte mo1500C() {
        return C0093av.m113b(this.f190bf);
    }

    public GoogleMapOptions camera(CameraPosition cameraPosition) {
        this.f184aZ = cameraPosition;
        return this;
    }

    public GoogleMapOptions compassEnabled(boolean z) {
        this.f186bb = Boolean.valueOf(z);
        return this;
    }

    public int describeContents() {
        GoogleMapOptionsCreator googleMapOptionsCreator = CREATOR;
        return 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo1504e() {
        return this.f180Q;
    }

    public CameraPosition getCamera() {
        return this.f184aZ;
    }

    public Boolean getCompassEnabled() {
        return this.f186bb;
    }

    public int getMapType() {
        return this.f183aY;
    }

    public Boolean getRotateGesturesEnabled() {
        return this.f190bf;
    }

    public Boolean getScrollGesturesEnabled() {
        return this.f187bc;
    }

    public Boolean getTiltGesturesEnabled() {
        return this.f189be;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.f182aX;
    }

    public Boolean getZOrderOnTop() {
        return this.f181aW;
    }

    public Boolean getZoomControlsEnabled() {
        return this.f185ba;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.f188bd;
    }

    public GoogleMapOptions mapType(int i) {
        this.f183aY = i;
        return this;
    }

    public GoogleMapOptions rotateGesturesEnabled(boolean z) {
        this.f190bf = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabled(boolean z) {
        this.f187bc = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions tiltGesturesEnabled(boolean z) {
        this.f189be = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions useViewLifecycleInFragment(boolean z) {
        this.f182aX = Boolean.valueOf(z);
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: v */
    public byte mo1520v() {
        return C0093av.m113b(this.f181aW);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: w */
    public byte mo1521w() {
        return C0093av.m113b(this.f182aX);
    }

    public void writeToParcel(Parcel parcel, int i) {
        GoogleMapOptionsCreator googleMapOptionsCreator = CREATOR;
        GoogleMapOptionsCreator.m341a(this, parcel, i);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: x */
    public byte mo1523x() {
        return C0093av.m113b(this.f185ba);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: y */
    public byte mo1524y() {
        return C0093av.m113b(this.f186bb);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: z */
    public byte mo1525z() {
        return C0093av.m113b(this.f187bc);
    }

    public GoogleMapOptions zOrderOnTop(boolean z) {
        this.f181aW = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions zoomControlsEnabled(boolean z) {
        this.f185ba = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions zoomGesturesEnabled(boolean z) {
        this.f188bd = Boolean.valueOf(z);
        return this;
    }
}
