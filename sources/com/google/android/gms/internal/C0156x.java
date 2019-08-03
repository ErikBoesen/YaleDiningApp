package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.internal.C0063ai.C0064a;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusClient.C0203a;
import com.google.android.gms.plus.PlusClient.C0204b;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.x */
public final class C0156x extends C0142t<C0063ai> {

    /* renamed from: cA */
    private final String f133cA;

    /* renamed from: cy */
    private final String f134cy;

    /* renamed from: cz */
    private final String f135cz;

    /* renamed from: com.google.android.gms.internal.x$a */
    final class C0157a extends C0047aa {

        /* renamed from: aa */
        private final C0204b f136aa;

        public C0157a(C0204b bVar) {
            this.f136aa = bVar;
        }

        /* renamed from: a */
        public void mo1173a(int i, Bundle bundle, Bundle bundle2) {
            C0092au auVar = null;
            ConnectionResult connectionResult = new ConnectionResult(i, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null);
            if (bundle2 != null) {
                auVar = new C0092au(bundle2);
            }
            C0156x.this.mo1387a((C0145c<?>) new C0160d<Object>(this.f136aa, connectionResult, auVar));
        }
    }

    /* renamed from: com.google.android.gms.internal.x$b */
    final class C0158b extends C0047aa {

        /* renamed from: aT */
        private final C0203a f138aT;

        public C0158b(C0203a aVar) {
            this.f138aT = aVar;
        }

        /* renamed from: a */
        public void mo1174a(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) {
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent) bundle.getParcelable("pendingIntent");
            }
            C0156x.this.mo1387a((C0145c<?>) new C0159c<Object>(this.f138aT, new ConnectionResult(i, pendingIntent), parcelFileDescriptor));
        }
    }

    /* renamed from: com.google.android.gms.internal.x$c */
    private final class C0159c extends C0145c<C0203a> {

        /* renamed from: br */
        private final ConnectionResult f141br;

        /* renamed from: bs */
        private final ParcelFileDescriptor f142bs;

        public C0159c(C0203a aVar, ConnectionResult connectionResult, ParcelFileDescriptor parcelFileDescriptor) {
            super(aVar);
            this.f141br = connectionResult;
            this.f142bs = parcelFileDescriptor;
        }

        /* renamed from: a */
        public void mo1400b(C0203a aVar) {
            if (aVar != null) {
                aVar.mo1230a(this.f141br, this.f142bs);
                return;
            }
            try {
                this.f142bs.close();
            } catch (IOException e) {
                Log.e("PlusClientImpl", "failed close", e);
            }
        }

        /* renamed from: q */
        public void mo1402q() {
            super.mo1402q();
        }
    }

    /* renamed from: com.google.android.gms.internal.x$d */
    final class C0160d extends C0145c<C0204b> {

        /* renamed from: aH */
        public final ConnectionResult f143aH;

        /* renamed from: ck */
        public final C0092au f145ck;

        public C0160d(C0204b bVar, ConnectionResult connectionResult, C0092au auVar) {
            super(bVar);
            this.f143aH = connectionResult;
            this.f145ck = auVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo1400b(C0204b bVar) {
            if (bVar != null) {
                bVar.mo1426a(this.f143aH, this.f145ck);
            }
        }
    }

    public C0156x(Context context, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this(context, PlusClient.DEFAULT_ACCOUNT, connectionCallbacks, onConnectionFailedListener, strArr);
    }

    public C0156x(Context context, String str, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this(context, context.getPackageName(), str, connectionCallbacks, onConnectionFailedListener, strArr);
    }

    public C0156x(Context context, String str, String str2, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this(context, str, str, str2, connectionCallbacks, onConnectionFailedListener, strArr);
    }

    public C0156x(Context context, String str, String str2, String str3, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        super(context, connectionCallbacks, onConnectionFailedListener, strArr);
        this.f134cy = str;
        this.f135cz = str2;
        this.f133cA = str3;
    }

    /* access modifiers changed from: protected */
    /* renamed from: E */
    public C0063ai mo1391j(IBinder iBinder) {
        return C0064a.m56w(iBinder);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1386a(C0125m mVar, C0143a aVar) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putBoolean("skip_oob", false);
        mVar.mo1353a(aVar, 1, this.f134cy, this.f135cz, mo1394k(), this.f133cA, bundle);
    }

    /* renamed from: a */
    public void mo1431a(C0203a aVar, Uri uri, int i) {
        mo1397n();
        Bundle bundle = new Bundle();
        bundle.putInt("bounding_box", i);
        C0158b bVar = new C0158b(aVar);
        try {
            ((C0063ai) mo1398o()).mo1215a((C0136r) bVar, uri, bundle);
        } catch (RemoteException e) {
            bVar.mo1174a(8, null, null);
        }
    }

    /* renamed from: a */
    public void mo1432a(C0204b bVar, String str) {
        mo1397n();
        C0157a aVar = new C0157a(bVar);
        try {
            ((C0063ai) mo1398o()).mo1216a((C0136r) aVar, str);
        } catch (RemoteException e) {
            aVar.mo1173a(8, null, null);
        }
    }

    public void clearDefaultAccount() {
        mo1397n();
        try {
            ((C0063ai) mo1398o()).clearDefaultAccount();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public String getAccountName() {
        mo1397n();
        try {
            return ((C0063ai) mo1398o()).getAccountName();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public String mo1390i() {
        return "com.google.android.gms.plus.service.START";
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public String mo1392j() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }
}
