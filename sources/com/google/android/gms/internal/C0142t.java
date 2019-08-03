package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.C0060ah.C0061a;
import com.google.android.gms.internal.C0125m.C0126a;
import java.util.ArrayList;

/* renamed from: com.google.android.gms.internal.t */
public abstract class C0142t<T extends IInterface> implements GooglePlayServicesClient {

    /* renamed from: aA */
    public static final String[] f89aA = {"service_esmobile", "service_googleme"};
    /* access modifiers changed from: private */

    /* renamed from: aq */
    public T f90aq;
    /* access modifiers changed from: private */

    /* renamed from: ar */
    public ArrayList<ConnectionCallbacks> f91ar;

    /* renamed from: as */
    final ArrayList<ConnectionCallbacks> f92as = new ArrayList<>();

    /* renamed from: at */
    private boolean f93at = false;

    /* renamed from: au */
    private ArrayList<OnConnectionFailedListener> f94au;

    /* renamed from: av */
    private boolean f95av = false;
    /* access modifiers changed from: private */

    /* renamed from: aw */
    public final ArrayList<C0145c<?>> f96aw = new ArrayList<>();
    /* access modifiers changed from: private */

    /* renamed from: ax */
    public ServiceConnection f97ax;

    /* renamed from: ay */
    private final String[] f98ay;

    /* renamed from: az */
    boolean f99az = false;
    /* access modifiers changed from: private */
    public final Context mContext;
    final Handler mHandler;

    /* renamed from: com.google.android.gms.internal.t$a */
    protected final class C0143a extends C0061a {
        protected C0143a() {
        }

        /* renamed from: a */
        public void mo1210a(int i, IBinder iBinder, Bundle bundle) {
            C0142t.this.mHandler.sendMessage(C0142t.this.mHandler.obtainMessage(1, new C0144b(i, iBinder, bundle)));
        }
    }

    /* renamed from: com.google.android.gms.internal.t$b */
    protected final class C0144b extends C0145c<Boolean> {

        /* renamed from: aF */
        public final Bundle f102aF;

        /* renamed from: aG */
        public final IBinder f103aG;
        public final int statusCode;

        public C0144b(int i, IBinder iBinder, Bundle bundle) {
            super(Boolean.valueOf(true));
            this.statusCode = i;
            this.f103aG = iBinder;
            this.f102aF = bundle;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo1400b(Boolean bool) {
            if (bool != null) {
                switch (this.statusCode) {
                    case 0:
                        try {
                            if (C0142t.this.mo1392j().equals(this.f103aG.getInterfaceDescriptor())) {
                                Log.d("GmsClient", "bound to service broker");
                                C0142t.this.f90aq = C0142t.this.mo1391j(this.f103aG);
                                if (C0142t.this.f90aq != null) {
                                    C0142t.this.mo1395l();
                                    return;
                                }
                            }
                        } catch (RemoteException e) {
                        }
                        C0142t.this.mContext.unbindService(C0142t.this.f97ax);
                        C0142t.this.f97ax = null;
                        C0142t.this.f90aq = null;
                        C0142t.this.mo1385a(new ConnectionResult(8, null));
                        return;
                    default:
                        C0142t.this.mo1385a(new ConnectionResult(this.statusCode, (PendingIntent) this.f102aF.getParcelable("pendingIntent")));
                        return;
                }
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.t$c */
    protected abstract class C0145c<TListener> {
        private TListener mListener;

        public C0145c(TListener tlistener) {
            this.mListener = tlistener;
            synchronized (C0142t.this.f96aw) {
                C0142t.this.f96aw.add(this);
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public abstract void mo1400b(TListener tlistener);

        /* renamed from: p */
        public void mo1401p() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.mListener;
            }
            mo1400b(tlistener);
        }

        /* renamed from: q */
        public void mo1402q() {
            synchronized (this) {
                this.mListener = null;
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.t$d */
    final class C0146d implements ServiceConnection {
        C0146d() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("GmsClient", "service broker connected, binder: " + iBinder);
            C0142t.this.mo1393k(iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("GmsClient", "service disconnected: " + componentName);
            C0142t.this.f90aq = null;
            C0142t.this.mo1396m();
        }
    }

    /* renamed from: com.google.android.gms.internal.t$e */
    final class C0147e extends Handler {
        C0147e() {
        }

        public void handleMessage(Message message) {
            if (message.what == 3) {
                C0142t.this.mo1385a(new ConnectionResult(((Integer) message.obj).intValue(), null));
            } else if (message.what == 4) {
                synchronized (C0142t.this.f91ar) {
                    if (C0142t.this.f99az && C0142t.this.isConnected() && C0142t.this.f91ar.contains(message.obj)) {
                        ((ConnectionCallbacks) message.obj).onConnected();
                    }
                }
            } else if (message.what == 2 && !C0142t.this.isConnected()) {
            } else {
                if (message.what == 2 || message.what == 1) {
                    ((C0145c) message.obj).mo1401p();
                }
            }
        }
    }

    protected C0142t(Context context, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Clients must be created on the UI thread.");
        }
        this.mContext = (Context) C0091at.m107c(context);
        this.f91ar = new ArrayList<>();
        this.f91ar.add(C0091at.m107c(connectionCallbacks));
        this.f94au = new ArrayList<>();
        this.f94au.add(C0091at.m107c(onConnectionFailedListener));
        this.mHandler = new C0147e();
        mo1388b(strArr);
        this.f98ay = strArr;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1385a(ConnectionResult connectionResult) {
        this.mHandler.removeMessages(4);
        synchronized (this.f94au) {
            this.f95av = true;
            ArrayList<OnConnectionFailedListener> arrayList = this.f94au;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                if (this.f99az) {
                    if (this.f94au.contains(arrayList.get(i))) {
                        ((OnConnectionFailedListener) arrayList.get(i)).onConnectionFailed(connectionResult);
                    }
                    i++;
                } else {
                    return;
                }
            }
            this.f95av = false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo1386a(C0125m mVar, C0143a aVar) throws RemoteException;

    /* renamed from: a */
    public final void mo1387a(C0145c<?> cVar) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, cVar));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo1388b(String... strArr) {
    }

    public void connect() {
        this.f99az = true;
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(isGooglePlayServicesAvailable)));
            return;
        }
        Intent intent = new Intent(mo1390i());
        if (this.f97ax != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect().");
            this.f90aq = null;
            this.mContext.unbindService(this.f97ax);
        }
        this.f97ax = new C0146d();
        Log.i("GmsClient", "connect: bindService returned " + this.mContext.bindService(intent, this.f97ax, 129) + " for " + intent);
    }

    public void disconnect() {
        this.f99az = false;
        synchronized (this.f96aw) {
            int size = this.f96aw.size();
            for (int i = 0; i < size; i++) {
                ((C0145c) this.f96aw.get(i)).mo1402q();
            }
            this.f96aw.clear();
        }
        this.f90aq = null;
        if (this.f97ax != null) {
            this.mContext.unbindService(this.f97ax);
            this.f97ax = null;
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public abstract String mo1390i();

    public boolean isConnected() {
        return this.f90aq != null;
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        C0091at.m107c(connectionCallbacks);
        synchronized (this.f91ar) {
            contains = this.f91ar.contains(connectionCallbacks);
        }
        return contains;
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        C0091at.m107c(onConnectionFailedListener);
        synchronized (this.f94au) {
            contains = this.f94au.contains(onConnectionFailedListener);
        }
        return contains;
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public abstract T mo1391j(IBinder iBinder);

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public abstract String mo1392j();

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public final void mo1393k(IBinder iBinder) {
        try {
            mo1386a(C0126a.m180d(iBinder), new C0143a<>());
        } catch (RemoteException e) {
            Log.w("GmsClient", "service died");
        }
    }

    /* renamed from: k */
    public final String[] mo1394k() {
        return this.f98ay;
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public final void mo1395l() {
        boolean z = true;
        synchronized (this.f91ar) {
            C0091at.m103a(!this.f93at);
            this.mHandler.removeMessages(4);
            this.f93at = true;
            if (this.f92as.size() != 0) {
                z = false;
            }
            C0091at.m103a(z);
            ArrayList<ConnectionCallbacks> arrayList = this.f91ar;
            int size = arrayList.size();
            for (int i = 0; i < size && this.f99az && isConnected(); i++) {
                this.f92as.size();
                if (!this.f92as.contains(arrayList.get(i))) {
                    ((ConnectionCallbacks) arrayList.get(i)).onConnected();
                }
            }
            this.f92as.clear();
            this.f93at = false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public final void mo1396m() {
        this.mHandler.removeMessages(4);
        synchronized (this.f91ar) {
            this.f93at = true;
            ArrayList<ConnectionCallbacks> arrayList = this.f91ar;
            int size = arrayList.size();
            for (int i = 0; i < size && this.f99az; i++) {
                if (this.f91ar.contains(arrayList.get(i))) {
                    ((ConnectionCallbacks) arrayList.get(i)).onDisconnected();
                }
            }
            this.f93at = false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public final void mo1397n() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public final T mo1398o() {
        mo1397n();
        return this.f90aq;
    }

    public void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        C0091at.m107c(connectionCallbacks);
        synchronized (this.f91ar) {
            if (this.f91ar.contains(connectionCallbacks)) {
                Log.w("GmsClient", "registerConnectionListener(): listener " + connectionCallbacks + " is already registered");
            } else {
                if (this.f93at) {
                    this.f91ar = new ArrayList<>(this.f91ar);
                }
                this.f91ar.add(connectionCallbacks);
            }
        }
        if (isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(4, connectionCallbacks));
        }
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        C0091at.m107c(onConnectionFailedListener);
        synchronized (this.f94au) {
            if (this.f94au.contains(onConnectionFailedListener)) {
                Log.w("GmsClient", "registerConnectionFailedListener(): listener " + onConnectionFailedListener + " is already registered");
            } else {
                if (this.f95av) {
                    this.f94au = new ArrayList<>(this.f94au);
                }
                this.f94au.add(onConnectionFailedListener);
            }
        }
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        C0091at.m107c(connectionCallbacks);
        synchronized (this.f91ar) {
            if (this.f91ar != null) {
                if (this.f93at) {
                    this.f91ar = new ArrayList<>(this.f91ar);
                }
                if (!this.f91ar.remove(connectionCallbacks)) {
                    Log.w("GmsClient", "unregisterConnectionListener(): listener " + connectionCallbacks + " not found");
                } else if (this.f93at && !this.f92as.contains(connectionCallbacks)) {
                    this.f92as.add(connectionCallbacks);
                }
            }
        }
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        C0091at.m107c(onConnectionFailedListener);
        synchronized (this.f94au) {
            if (this.f94au != null) {
                if (this.f95av) {
                    this.f94au = new ArrayList<>(this.f94au);
                }
                if (!this.f94au.remove(onConnectionFailedListener)) {
                    Log.w("GmsClient", "unregisterConnectionFailedListener(): listener " + onConnectionFailedListener + " not found");
                }
            }
        }
    }
}
