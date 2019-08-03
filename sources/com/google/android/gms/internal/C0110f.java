package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.gms.internal.f */
public interface C0110f extends IInterface {

    /* renamed from: com.google.android.gms.internal.f$a */
    public static abstract class C0111a extends Binder implements C0110f {

        /* renamed from: com.google.android.gms.internal.f$a$a */
        private static class C0112a implements C0110f {

            /* renamed from: a */
            private IBinder f74a;

            C0112a(IBinder iBinder) {
                this.f74a = iBinder;
            }

            public IBinder asBinder() {
                return this.f74a;
            }
        }

        public C0111a() {
            attachInterface(this, "com.google.android.gms.dynamic.IObjectWrapper");
        }

        /* renamed from: D */
        public static C0110f m152D(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0110f)) ? new C0112a(iBinder) : (C0110f) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.dynamic.IObjectWrapper");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }
}
