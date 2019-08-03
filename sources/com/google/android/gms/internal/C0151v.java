package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* renamed from: com.google.android.gms.internal.v */
public class C0151v {
    private C0151v() {
    }

    /* renamed from: a */
    public static int m240a(Parcel parcel) {
        return m241a(parcel, 20293);
    }

    /* renamed from: a */
    private static int m241a(Parcel parcel, int i) {
        parcel.writeInt(-65536 | i);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    /* renamed from: a */
    public static void m242a(Parcel parcel, int i, byte b) {
        m245a(parcel, i, 4);
        parcel.writeInt(b);
    }

    /* renamed from: a */
    public static void m243a(Parcel parcel, int i, double d) {
        m245a(parcel, i, 8);
        parcel.writeDouble(d);
    }

    /* renamed from: a */
    public static void m244a(Parcel parcel, int i, float f) {
        m245a(parcel, i, 4);
        parcel.writeFloat(f);
    }

    /* renamed from: a */
    private static void m245a(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(-65536 | i);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt((i2 << 16) | i);
    }

    /* renamed from: a */
    public static void m246a(Parcel parcel, int i, IBinder iBinder) {
        if (iBinder != null) {
            int a = m241a(parcel, i);
            parcel.writeStrongBinder(iBinder);
            m253b(parcel, a);
        }
    }

    /* renamed from: a */
    public static void m247a(Parcel parcel, int i, Parcelable parcelable, int i2) {
        if (parcelable != null) {
            int a = m241a(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            m253b(parcel, a);
        }
    }

    /* renamed from: a */
    public static void m248a(Parcel parcel, int i, String str) {
        if (str != null) {
            int a = m241a(parcel, i);
            parcel.writeString(str);
            m253b(parcel, a);
        }
    }

    /* renamed from: a */
    public static <T extends Parcelable> void m249a(Parcel parcel, int i, List<T> list) {
        if (list != null) {
            int a = m241a(parcel, i);
            parcel.writeTypedList(list);
            m253b(parcel, a);
        }
    }

    /* renamed from: a */
    public static void m250a(Parcel parcel, int i, boolean z) {
        m245a(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    /* renamed from: a */
    public static void m251a(Parcel parcel, int i, byte[] bArr) {
        if (bArr != null) {
            m252a(parcel, i, bArr, 0, bArr.length);
        }
    }

    /* renamed from: a */
    public static void m252a(Parcel parcel, int i, byte[] bArr, int i2, int i3) {
        if (bArr != null) {
            int a = m241a(parcel, i);
            parcel.writeByteArray(bArr, i2, i3);
            m253b(parcel, a);
        }
    }

    /* renamed from: b */
    private static void m253b(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        int i2 = dataPosition - i;
        parcel.setDataPosition(i - 4);
        parcel.writeInt(i2);
        parcel.setDataPosition(dataPosition);
    }

    /* renamed from: b */
    public static void m254b(Parcel parcel, int i, int i2) {
        m245a(parcel, i, 4);
        parcel.writeInt(i2);
    }

    /* renamed from: b */
    public static void m255b(Parcel parcel, int i, List list) {
        if (list != null) {
            int a = m241a(parcel, i);
            parcel.writeList(list);
            m253b(parcel, a);
        }
    }

    /* renamed from: c */
    public static void m256c(Parcel parcel, int i) {
        m253b(parcel, i);
    }
}
