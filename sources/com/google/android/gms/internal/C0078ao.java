package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.internal.C0133q.C0134a;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.google.android.gms.internal.ao */
public class C0078ao {

    /* renamed from: bA */
    private Bundle f35bA;

    /* renamed from: bB */
    private String f36bB;

    /* renamed from: bC */
    private String f37bC;

    /* renamed from: bD */
    private Intent f38bD;

    /* renamed from: bE */
    private String f39bE;

    /* renamed from: bF */
    private boolean f40bF;

    /* renamed from: bG */
    private int f41bG = 10000;

    /* renamed from: bx */
    C0080a f42bx = new C0080a();

    /* renamed from: by */
    private Account f43by;

    /* renamed from: bz */
    private String f44bz;

    /* renamed from: com.google.android.gms.internal.ao$a */
    private static final class C0080a implements ServiceConnection {

        /* renamed from: bw */
        private BlockingQueue<C0133q> f45bw;

        private C0080a() {
            this.f45bw = new LinkedBlockingQueue();
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0133q mo1265a(Context context, String str, int i) throws InterruptedException {
            if (!context.bindService(new Intent().setComponent(new ComponentName(str + ".android.gms", str + ".android.gms.auth.GetToken")), this, 1)) {
                return null;
            }
            return (C0133q) this.f45bw.take();
        }

        /* renamed from: h */
        public void mo1266h(Context context) {
            context.unbindService(this);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f45bw.put(C0134a.m199e(iBinder));
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public C0078ao(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.f44bz = str2;
        this.f35bA = bundle;
        this.f43by = new Account(str, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
    }

    /* renamed from: a */
    private void m87a(Bundle bundle) {
        this.f36bB = bundle.getString("session");
        this.f37bC = bundle.getString("authtoken");
        this.f39bE = bundle.getString("Error");
        this.f38bD = (Intent) bundle.getParcelable("userRecoveryIntent");
        this.f40bF = bundle.getBoolean(GoogleAuthUtil.KEY_HANDLE_NOTIFICATION);
    }

    /* renamed from: H */
    public boolean mo1259H() {
        if (this.f37bC != null) {
            return false;
        }
        if (this.f39bE == null) {
            return true;
        }
        return "BadAuthentication".equals(this.f39bE) || "CaptchaRequired".equals(this.f39bE) || "DeviceManagementRequiredOrSyncDisabled".equals(this.f39bE) || "NeedPermission".equals(this.f39bE) || "NeedsBrowser".equals(this.f39bE) || "UserCancel".equals(this.f39bE) || "AppDownloadRequired".equals(this.f39bE);
    }

    /* renamed from: I */
    public String mo1260I() {
        return this.f39bE;
    }

    public Intent getIntent() {
        if (this.f38bD == null) {
            this.f38bD = new Intent().setComponent(new ComponentName(this.f43by.type + ".android.gms", this.f43by.type + ".android.gms.auth.TokenActivity"));
            this.f38bD.setFlags(this.f38bD.getFlags() & -268435457);
            this.f38bD.putExtra("authAccount", this.f43by.name);
            this.f38bD.putExtra("service", this.f44bz);
            this.f38bD.putExtra("callerExtras", this.f35bA);
            this.f38bD.putExtra("session", this.f36bB);
        }
        return this.f38bD;
    }

    public boolean hasHardError() {
        return this.f37bC == null && !mo1259H() && !hasSoftError();
    }

    public boolean hasSoftError() {
        if (this.f37bC != null) {
            return false;
        }
        return "NetworkError".equals(this.f39bE) || "ServiceUnavailable".equals(this.f39bE) || "Timeout".equals(this.f39bE);
    }

    /* renamed from: i */
    public String mo1264i(Context context) {
        if (this.f37bC != null) {
            return this.f37bC;
        }
        this.f39bE = null;
        this.f38bD = null;
        try {
            C0133q a = this.f42bx.mo1265a(context, this.f43by.type, this.f41bG);
            if (a == null) {
                this.f39bE = "AppDownloadRequired";
                return null;
            }
            if (!this.f35bA.containsKey("androidPackageName")) {
                this.f35bA.putString("androidPackageName", context.getPackageName());
            }
            m87a(a.mo1360a(this.f43by.name, this.f44bz, this.f35bA));
            this.f42bx.mo1266h(context);
            return this.f37bC;
        } catch (RemoteException e) {
            Log.i("GoogleAuthToken", "GMS remote exception ", e);
            this.f39bE = "InternalError";
            return null;
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            this.f39bE = "Interrupted";
            return null;
        } catch (Throwable th) {
            this.f42bx.mo1266h(context);
            throw th;
        }
    }
}
