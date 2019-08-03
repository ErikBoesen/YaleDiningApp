package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.C0139s.C0140a;

/* renamed from: com.google.android.gms.internal.an */
public interface C0075an extends IInterface {

    /* renamed from: com.google.android.gms.internal.an$a */
    public static abstract class C0076a extends Binder implements C0075an {

        /* renamed from: com.google.android.gms.internal.an$a$a */
        private static class C0077a implements C0075an {

            /* renamed from: a */
            private IBinder f34a;

            C0077a(IBinder iBinder) {
                this.f34a = iBinder;
            }

            public IBinder asBinder() {
                return this.f34a;
            }

            /* renamed from: f */
            public boolean mo1255f(C0139s sVar) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMarkerClickListener");
                    obtain.writeStrongBinder(sVar != null ? sVar.asBinder() : null);
                    this.f34a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public C0076a() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerClickListener");
        }

        /* renamed from: y */
        public static C0075an m85y(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0075an)) ? new C0077a(iBinder) : (C0075an) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
                    boolean f = mo1255f(C0140a.m209r(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(f ? 1 : 0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnMarkerClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: f */
    boolean mo1255f(C0139s sVar) throws RemoteException;
}
