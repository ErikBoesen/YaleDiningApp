package com.google.android.gms.panorama;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.internal.C0161y;

public class PanoramaClient implements GooglePlayServicesClient {

    /* renamed from: cM */
    private final C0161y f292cM;

    public interface OnPanoramaInfoLoadedListener {
        void onPanoramaInfoLoaded(ConnectionResult connectionResult, Intent intent);
    }

    /* renamed from: com.google.android.gms.panorama.PanoramaClient$a */
    public interface C0202a {
        /* renamed from: a */
        void mo1930a(ConnectionResult connectionResult, int i, Intent intent);
    }

    public PanoramaClient(Context context, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this.f292cM = new C0161y(context, connectionCallbacks, onConnectionFailedListener);
    }

    public void connect() {
        this.f292cM.connect();
    }

    public void disconnect() {
        this.f292cM.disconnect();
    }

    public boolean isConnected() {
        return this.f292cM.isConnected();
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        return this.f292cM.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        return this.f292cM.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    public void loadPanoramaInfo(OnPanoramaInfoLoadedListener onPanoramaInfoLoadedListener, Uri uri) {
        this.f292cM.mo1439a(onPanoramaInfoLoadedListener, uri, false);
    }

    public void loadPanoramaInfoAndGrantAccess(OnPanoramaInfoLoadedListener onPanoramaInfoLoadedListener, Uri uri) {
        this.f292cM.mo1439a(onPanoramaInfoLoadedListener, uri, true);
    }

    public void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.f292cM.registerConnectionCallbacks(connectionCallbacks);
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.f292cM.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.f292cM.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.f292cM.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
}
