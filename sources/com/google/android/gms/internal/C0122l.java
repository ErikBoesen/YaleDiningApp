package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.C0110f.C0111a;
import com.google.android.gms.internal.C0139s.C0140a;

/* renamed from: com.google.android.gms.internal.l */
public interface C0122l extends IInterface {

    /* renamed from: com.google.android.gms.internal.l$a */
    public static abstract class C0123a extends Binder implements C0122l {

        /* renamed from: com.google.android.gms.internal.l$a$a */
        private static class C0124a implements C0122l {

            /* renamed from: a */
            private IBinder f81a;

            C0124a(IBinder iBinder) {
                this.f81a = iBinder;
            }

            /* renamed from: a */
            public C0110f mo1345a(C0139s sVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    obtain.writeStrongBinder(sVar != null ? sVar.asBinder() : null);
                    this.f81a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return C0111a.m152D(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f81a;
            }

            /* renamed from: b */
            public C0110f mo1346b(C0139s sVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    obtain.writeStrongBinder(sVar != null ? sVar.asBinder() : null);
                    this.f81a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return C0111a.m152D(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public C0123a() {
            attachInterface(this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
        }

        /* renamed from: g */
        public static C0122l m173g(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0122l)) ? new C0124a(iBinder) : (C0122l) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IBinder iBinder = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    C0110f a = mo1345a(C0140a.m209r(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    C0110f b = mo1346b(C0140a.m209r(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (b != null) {
                        iBinder = b.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: a */
    C0110f mo1345a(C0139s sVar) throws RemoteException;

    /* renamed from: b */
    C0110f mo1346b(C0139s sVar) throws RemoteException;
}
