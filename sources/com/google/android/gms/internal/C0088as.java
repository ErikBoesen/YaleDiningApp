package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.C0110f.C0111a;

/* renamed from: com.google.android.gms.internal.as */
public interface C0088as extends IInterface {

    /* renamed from: com.google.android.gms.internal.as$a */
    public static abstract class C0089a extends Binder implements C0088as {

        /* renamed from: com.google.android.gms.internal.as$a$a */
        private static class C0090a implements C0088as {

            /* renamed from: a */
            private IBinder f49a;

            C0090a(IBinder iBinder) {
                this.f49a = iBinder;
            }

            public IBinder asBinder() {
                return this.f49a;
            }

            /* renamed from: d */
            public void mo1274d(C0110f fVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnLocationChangeListener");
                    obtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                    this.f49a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public C0089a() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnLocationChangeListener");
        }

        /* renamed from: H */
        public static C0088as m100H(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0088as)) ? new C0090a(iBinder) : (C0088as) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener");
                    mo1274d(C0111a.m152D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnLocationChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: d */
    void mo1274d(C0110f fVar) throws RemoteException;
}
