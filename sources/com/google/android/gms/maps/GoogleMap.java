package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.internal.C0044a.C0045a;
import com.google.android.gms.internal.C0048ab;
import com.google.android.gms.internal.C0053ae;
import com.google.android.gms.internal.C0069al.C0070a;
import com.google.android.gms.internal.C0072am;
import com.google.android.gms.internal.C0075an.C0076a;
import com.google.android.gms.internal.C0088as;
import com.google.android.gms.internal.C0091at;
import com.google.android.gms.internal.C0110f;
import com.google.android.gms.internal.C0118j.C0119a;
import com.google.android.gms.internal.C0122l.C0123a;
import com.google.android.gms.internal.C0129o.C0130a;
import com.google.android.gms.internal.C0139s;
import com.google.android.gms.internal.C0148u.C0149a;
import com.google.android.gms.internal.C0165z.C0166a;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.ILocationSourceDelegate.C0188a;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class GoogleMap {
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;

    /* renamed from: cE */
    private final IGoogleMapDelegate f159cE;

    /* renamed from: cF */
    private UiSettings f160cF;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface InfoWindowAdapter {
        View getInfoContents(Marker marker);

        View getInfoWindow(Marker marker);
    }

    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$a */
    private static final class C0177a extends C0070a {

        /* renamed from: cx */
        private final CancelableCallback f179cx;

        C0177a(CancelableCallback cancelableCallback) {
            this.f179cx = cancelableCallback;
        }

        public void onCancel() {
            this.f179cx.onCancel();
        }

        public void onFinish() {
            this.f179cx.onFinish();
        }
    }

    protected GoogleMap(IGoogleMapDelegate iGoogleMapDelegate) {
        this.f159cE = (IGoogleMapDelegate) C0091at.m107c(iGoogleMapDelegate);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: ab */
    public IGoogleMapDelegate mo1449ab() {
        return this.f159cE;
    }

    public final GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        try {
            C0053ae addGroundOverlay = this.f159cE.addGroundOverlay(groundOverlayOptions);
            if (addGroundOverlay != null) {
                return new GroundOverlay(addGroundOverlay);
            }
            return null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Marker addMarker(MarkerOptions markerOptions) {
        try {
            C0139s addMarker = this.f159cE.addMarker(markerOptions);
            if (addMarker != null) {
                return new Marker(addMarker);
            }
            return null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polygon addPolygon(PolygonOptions polygonOptions) {
        try {
            return new Polygon(this.f159cE.addPolygon(polygonOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polyline addPolyline(PolylineOptions polylineOptions) {
        try {
            return new Polyline(this.f159cE.addPolyline(polylineOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        try {
            C0072am addTileOverlay = this.f159cE.addTileOverlay(tileOverlayOptions);
            if (addTileOverlay != null) {
                return new TileOverlay(addTileOverlay);
            }
            return null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate) {
        try {
            this.f159cE.animateCamera(cameraUpdate.mo1448G());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, int i, CancelableCallback cancelableCallback) {
        try {
            this.f159cE.animateCameraWithDurationAndCallback(cameraUpdate.mo1448G(), i, cancelableCallback == null ? null : new C0177a(cancelableCallback));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        try {
            this.f159cE.animateCameraWithCallback(cameraUpdate.mo1448G(), cancelableCallback == null ? null : new C0177a(cancelableCallback));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void clear() {
        try {
            this.f159cE.clear();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final CameraPosition getCameraPosition() {
        try {
            return this.f159cE.getCameraPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getMapType() {
        try {
            return this.f159cE.getMapType();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final float getMaxZoomLevel() {
        try {
            return this.f159cE.getMaxZoomLevel();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final float getMinZoomLevel() {
        try {
            return this.f159cE.getMinZoomLevel();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Location getMyLocation() {
        try {
            return this.f159cE.getMyLocation();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Projection getProjection() {
        try {
            return new Projection(this.f159cE.getProjection());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final UiSettings getUiSettings() {
        try {
            if (this.f160cF == null) {
                this.f160cF = new UiSettings(this.f159cE.getUiSettings());
            }
            return this.f160cF;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isIndoorEnabled() {
        try {
            return this.f159cE.isIndoorEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isMyLocationEnabled() {
        try {
            return this.f159cE.isMyLocationEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isTrafficEnabled() {
        try {
            return this.f159cE.isTrafficEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void moveCamera(CameraUpdate cameraUpdate) {
        try {
            this.f159cE.moveCamera(cameraUpdate.mo1448G());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean setIndoorEnabled(boolean z) {
        try {
            return this.f159cE.setIndoorEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setInfoWindowAdapter(final InfoWindowAdapter infoWindowAdapter) {
        if (infoWindowAdapter == null) {
            try {
                this.f159cE.setInfoWindowAdapter(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.f159cE.setInfoWindowAdapter(new C0123a() {
                /* renamed from: a */
                public C0110f mo1345a(C0139s sVar) {
                    return C0048ab.m22a(infoWindowAdapter.getInfoWindow(new Marker(sVar)));
                }

                /* renamed from: b */
                public C0110f mo1346b(C0139s sVar) {
                    return C0048ab.m22a(infoWindowAdapter.getInfoContents(new Marker(sVar)));
                }
            });
        }
    }

    public final void setLocationSource(final LocationSource locationSource) {
        if (locationSource == null) {
            try {
                this.f159cE.setLocationSource(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.f159cE.setLocationSource(new C0188a() {
                public void activate(final C0088as asVar) {
                    locationSource.activate(new OnLocationChangedListener() {
                        public void onLocationChanged(Location location) {
                            try {
                                asVar.mo1274d(C0048ab.m22a(location));
                            } catch (RemoteException e) {
                                throw new RuntimeRemoteException(e);
                            }
                        }
                    });
                }

                public void deactivate() {
                    locationSource.deactivate();
                }
            });
        }
    }

    public final void setMapType(int i) {
        try {
            this.f159cE.setMapType(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMyLocationEnabled(boolean z) {
        try {
            this.f159cE.setMyLocationEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnCameraChangeListener(final OnCameraChangeListener onCameraChangeListener) {
        if (onCameraChangeListener == null) {
            try {
                this.f159cE.setOnCameraChangeListener(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.f159cE.setOnCameraChangeListener(new C0045a() {
                public void onCameraChange(CameraPosition cameraPosition) {
                    onCameraChangeListener.onCameraChange(cameraPosition);
                }
            });
        }
    }

    public final void setOnInfoWindowClickListener(final OnInfoWindowClickListener onInfoWindowClickListener) {
        if (onInfoWindowClickListener == null) {
            try {
                this.f159cE.setOnInfoWindowClickListener(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.f159cE.setOnInfoWindowClickListener(new C0149a() {
                /* renamed from: h */
                public void mo1406h(C0139s sVar) {
                    onInfoWindowClickListener.onInfoWindowClick(new Marker(sVar));
                }
            });
        }
    }

    public final void setOnMapClickListener(final OnMapClickListener onMapClickListener) {
        if (onMapClickListener == null) {
            try {
                this.f159cE.setOnMapClickListener(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.f159cE.setOnMapClickListener(new C0130a() {
                public void onMapClick(LatLng latLng) {
                    onMapClickListener.onMapClick(latLng);
                }
            });
        }
    }

    public final void setOnMapLongClickListener(final OnMapLongClickListener onMapLongClickListener) {
        if (onMapLongClickListener == null) {
            try {
                this.f159cE.setOnMapLongClickListener(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.f159cE.setOnMapLongClickListener(new C0119a() {
                public void onMapLongClick(LatLng latLng) {
                    onMapLongClickListener.onMapLongClick(latLng);
                }
            });
        }
    }

    public final void setOnMarkerClickListener(final OnMarkerClickListener onMarkerClickListener) {
        if (onMarkerClickListener == null) {
            try {
                this.f159cE.setOnMarkerClickListener(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.f159cE.setOnMarkerClickListener(new C0076a() {
                /* renamed from: f */
                public boolean mo1255f(C0139s sVar) {
                    return onMarkerClickListener.onMarkerClick(new Marker(sVar));
                }
            });
        }
    }

    public final void setOnMarkerDragListener(final OnMarkerDragListener onMarkerDragListener) {
        if (onMarkerDragListener == null) {
            try {
                this.f159cE.setOnMarkerDragListener(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.f159cE.setOnMarkerDragListener(new C0166a() {
                /* renamed from: c */
                public void mo1442c(C0139s sVar) {
                    onMarkerDragListener.onMarkerDragStart(new Marker(sVar));
                }

                /* renamed from: d */
                public void mo1443d(C0139s sVar) {
                    onMarkerDragListener.onMarkerDragEnd(new Marker(sVar));
                }

                /* renamed from: e */
                public void mo1444e(C0139s sVar) {
                    onMarkerDragListener.onMarkerDrag(new Marker(sVar));
                }
            });
        }
    }

    public final void setTrafficEnabled(boolean z) {
        try {
            this.f159cE.setTrafficEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void stopAnimation() {
        try {
            this.f159cE.stopAnimation();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
