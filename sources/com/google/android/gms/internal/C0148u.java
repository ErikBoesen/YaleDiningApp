package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.C0139s.C0140a;

/* renamed from: com.google.android.gms.internal.u */
public interface C0148u extends IInterface {

    /* renamed from: com.google.android.gms.internal.u$a */
    public static abstract class C0149a extends Binder implements C0148u {

        /* renamed from: com.google.android.gms.internal.u$a$a */
        private static class C0150a implements C0148u {

            /* renamed from: a */
            private IBinder f107a;

            C0150a(IBinder iBinder) {
                this.f107a = iBinder;
            }

            public IBinder asBinder() {
                return this.f107a;
            }

            /* renamed from: h */
            public void mo1406h(C0139s sVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
                    obtain.writeStrongBinder(sVar != null ? sVar.asBinder() : null);
                    this.f107a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public C0149a() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
        }

        /* renamed from: q */
        public static C0148u m238q(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0148u)) ? new C0150a(iBinder) : (C0148u) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
                    mo1406h(C0140a.m209r(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: h */
    void mo1406h(C0139s sVar) throws RemoteException;
}
