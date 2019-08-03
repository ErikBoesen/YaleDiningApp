package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.C0099c.C0100a;

/* renamed from: com.google.android.gms.internal.aq */
public interface C0084aq extends IInterface {

    /* renamed from: com.google.android.gms.internal.aq$a */
    public static abstract class C0085a extends Binder implements C0084aq {

        /* renamed from: com.google.android.gms.internal.aq$a$a */
        private static class C0086a implements C0084aq {

            /* renamed from: a */
            private IBinder f48a;

            C0086a(IBinder iBinder) {
                this.f48a = iBinder;
            }

            /* renamed from: a */
            public void mo1271a(C0099c cVar, Uri uri, Bundle bundle, boolean z) throws RemoteException {
                IBinder iBinder = null;
                int i = 1;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.panorama.internal.IPanoramaService");
                    if (cVar != null) {
                        iBinder = cVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.f48a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f48a;
            }
        }

        public C0085a() {
            attachInterface(this, "com.google.android.gms.panorama.internal.IPanoramaService");
        }

        /* renamed from: J */
        public static C0084aq m96J(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0084aq)) ? new C0086a(iBinder) : (C0084aq) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.panorama.internal.IPanoramaService");
                    mo1271a(C0100a.m135G(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.panorama.internal.IPanoramaService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: a */
    void mo1271a(C0099c cVar, Uri uri, Bundle bundle, boolean z) throws RemoteException;
}
