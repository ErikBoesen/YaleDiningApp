package com.google.android.gms.plus;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.internal.C0092au;
import com.google.android.gms.internal.C0156x;

public class PlusClient implements GooglePlayServicesClient {
    public static final String DEFAULT_ACCOUNT = "<<default account>>";

    /* renamed from: bn */
    private final C0156x f293bn;

    /* renamed from: com.google.android.gms.plus.PlusClient$a */
    public interface C0203a {
        /* renamed from: a */
        void mo1230a(ConnectionResult connectionResult, ParcelFileDescriptor parcelFileDescriptor);
    }

    /* renamed from: com.google.android.gms.plus.PlusClient$b */
    public interface C0204b {
        /* renamed from: a */
        void mo1426a(ConnectionResult connectionResult, C0092au auVar);
    }

    public PlusClient(Context context, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, connectionCallbacks, onConnectionFailedListener, "plus_one_placeholder_scope");
    }

    public PlusClient(Context context, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this(context, DEFAULT_ACCOUNT, connectionCallbacks, onConnectionFailedListener, strArr);
    }

    public PlusClient(Context context, String str, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this(context, context.getPackageName(), str, connectionCallbacks, onConnectionFailedListener, strArr);
    }

    public PlusClient(Context context, String str, String str2, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this(context, str, str, str2, connectionCallbacks, onConnectionFailedListener, strArr);
    }

    public PlusClient(Context context, String str, String str2, String str3, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this.f293bn = new C0156x(context, str, str2, str3, connectionCallbacks, onConnectionFailedListener, strArr);
    }

    /* renamed from: a */
    public void mo1931a(C0203a aVar, Uri uri, int i) {
        this.f293bn.mo1431a(aVar, uri, i);
    }

    /* renamed from: a */
    public void mo1932a(C0204b bVar, String str) {
        this.f293bn.mo1432a(bVar, str);
    }

    public void clearDefaultAccount() {
        this.f293bn.clearDefaultAccount();
    }

    public void connect() {
        this.f293bn.connect();
    }

    public void disconnect() {
        this.f293bn.disconnect();
    }

    public String getAccountName() {
        return this.f293bn.getAccountName();
    }

    public boolean isConnected() {
        return this.f293bn.isConnected();
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        return this.f293bn.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        return this.f293bn.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    public void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.f293bn.registerConnectionCallbacks(connectionCallbacks);
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.f293bn.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.f293bn.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.f293bn.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
}
