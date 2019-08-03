package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.C0110f.C0111a;
import com.google.android.gms.internal.C0114h.C0115a;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate.C0184a;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate.C0190a;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.IMapViewDelegate.C0192a;

/* renamed from: com.google.android.gms.internal.ad */
public interface C0050ad extends IInterface {

    /* renamed from: com.google.android.gms.internal.ad$a */
    public static abstract class C0051a extends Binder implements C0050ad {

        /* renamed from: com.google.android.gms.internal.ad$a$a */
        private static class C0052a implements C0050ad {

            /* renamed from: a */
            private IBinder f17a;

            C0052a(IBinder iBinder) {
                this.f17a = iBinder;
            }

            /* renamed from: a */
            public IMapViewDelegate mo1176a(C0110f fVar, GoogleMapOptions googleMapOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    obtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                    if (googleMapOptions != null) {
                        obtain.writeInt(1);
                        googleMapOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f17a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return C0192a.m359C(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f17a;
            }

            /* renamed from: b */
            public void mo1177b(C0110f fVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    obtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                    this.f17a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: c */
            public IMapFragmentDelegate mo1178c(C0110f fVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    obtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                    this.f17a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return C0190a.m358h(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: r */
            public ICameraUpdateFactoryDelegate mo1179r() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    this.f17a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return C0184a.m355l(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: s */
            public C0114h mo1180s() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    this.f17a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return C0115a.m160c(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public C0051a() {
            attachInterface(this, "com.google.android.gms.maps.internal.ICreator");
        }

        /* renamed from: m */
        public static C0050ad m29m(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0050ad)) ? new C0052a(iBinder) : (C0050ad) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IBinder iBinder = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    mo1177b(C0111a.m152D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    IMapFragmentDelegate c = mo1178c(C0111a.m152D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (c != null) {
                        iBinder = c.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    IMapViewDelegate a = mo1176a(C0111a.m152D(parcel.readStrongBinder()), parcel.readInt() != 0 ? GoogleMapOptions.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    ICameraUpdateFactoryDelegate r = mo1179r();
                    parcel2.writeNoException();
                    if (r != null) {
                        iBinder = r.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 5:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    C0114h s = mo1180s();
                    parcel2.writeNoException();
                    if (s != null) {
                        iBinder = s.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.ICreator");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: a */
    IMapViewDelegate mo1176a(C0110f fVar, GoogleMapOptions googleMapOptions) throws RemoteException;

    /* renamed from: b */
    void mo1177b(C0110f fVar) throws RemoteException;

    /* renamed from: c */
    IMapFragmentDelegate mo1178c(C0110f fVar) throws RemoteException;

    /* renamed from: r */
    ICameraUpdateFactoryDelegate mo1179r() throws RemoteException;

    /* renamed from: s */
    C0114h mo1180s() throws RemoteException;
}
