package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.C0084aq.C0085a;
import com.google.android.gms.internal.C0099c.C0100a;
import com.google.android.gms.panorama.PanoramaClient.C0202a;
import com.google.android.gms.panorama.PanoramaClient.OnPanoramaInfoLoadedListener;

/* renamed from: com.google.android.gms.internal.y */
public class C0161y extends C0142t<C0084aq> {

    /* renamed from: com.google.android.gms.internal.y$a */
    final class C0162a extends C0100a {

        /* renamed from: ac */
        private final C0202a f146ac = null;

        /* renamed from: ad */
        private final OnPanoramaInfoLoadedListener f147ad;

        /* renamed from: ae */
        private final Uri f148ae;

        public C0162a(OnPanoramaInfoLoadedListener onPanoramaInfoLoadedListener, Uri uri) {
            this.f147ad = onPanoramaInfoLoadedListener;
            this.f148ae = uri;
        }

        /* renamed from: a */
        public void mo1304a(int i, Bundle bundle, int i2, Intent intent) {
            if (this.f148ae != null) {
                C0161y.this.getContext().revokeUriPermission(this.f148ae, 1);
            }
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent) bundle.getParcelable("pendingIntent");
            }
            ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
            if (this.f146ac != null) {
                C0161y.this.mo1387a((C0145c<?>) new C0163b<Object>(this.f146ac, connectionResult, i2, intent));
            } else {
                C0161y.this.mo1387a((C0145c<?>) new C0164c<Object>(this.f147ad, connectionResult, intent));
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.y$b */
    final class C0163b extends C0145c<C0202a> {

        /* renamed from: aH */
        public final ConnectionResult f150aH;

        /* renamed from: aI */
        public final Intent f151aI;
        public final int type;

        public C0163b(C0202a aVar, ConnectionResult connectionResult, int i, Intent intent) {
            super(aVar);
            this.f150aH = connectionResult;
            this.type = i;
            this.f151aI = intent;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo1400b(C0202a aVar) {
            if (aVar != null) {
                aVar.mo1930a(this.f150aH, this.type, this.f151aI);
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.y$c */
    final class C0164c extends C0145c<OnPanoramaInfoLoadedListener> {

        /* renamed from: aH */
        private final ConnectionResult f153aH;

        /* renamed from: aI */
        private final Intent f154aI;

        public C0164c(OnPanoramaInfoLoadedListener onPanoramaInfoLoadedListener, ConnectionResult connectionResult, Intent intent) {
            super(onPanoramaInfoLoadedListener);
            this.f153aH = connectionResult;
            this.f154aI = intent;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo1400b(OnPanoramaInfoLoadedListener onPanoramaInfoLoadedListener) {
            if (onPanoramaInfoLoadedListener != null) {
                onPanoramaInfoLoadedListener.onPanoramaInfoLoaded(this.f153aH, this.f154aI);
            }
        }
    }

    public C0161y(Context context, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, connectionCallbacks, onConnectionFailedListener, null);
    }

    /* renamed from: A */
    public C0084aq mo1391j(IBinder iBinder) {
        return C0085a.m96J(iBinder);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1386a(C0125m mVar, C0143a aVar) throws RemoteException {
        mVar.mo1352a(aVar, 1, getContext().getPackageName(), new Bundle());
    }

    /* renamed from: a */
    public void mo1438a(C0162a aVar, Uri uri, Bundle bundle, boolean z) {
        mo1397n();
        if (z) {
            getContext().grantUriPermission(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, uri, 1);
        }
        try {
            ((C0084aq) mo1398o()).mo1271a(aVar, uri, bundle, z);
        } catch (RemoteException e) {
            aVar.mo1304a(8, null, 0, null);
        }
    }

    /* renamed from: a */
    public void mo1439a(OnPanoramaInfoLoadedListener onPanoramaInfoLoadedListener, Uri uri, boolean z) {
        mo1438a(new C0162a(onPanoramaInfoLoadedListener, z ? uri : null), uri, null, z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public String mo1390i() {
        return "com.google.android.gms.panorama.service.START";
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public String mo1392j() {
        return "com.google.android.gms.panorama.internal.IPanoramaService";
    }
}
