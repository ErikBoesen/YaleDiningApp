package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.gms.internal.aw */
public class C0094aw {

    /* renamed from: com.google.android.gms.internal.aw$a */
    public static class C0095a extends RuntimeException {
        public C0095a(String str, Parcel parcel) {
            super(str + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
        }
    }

    private C0094aw() {
    }

    /* renamed from: a */
    public static <T extends Parcelable> T m114a(Parcel parcel, int i, Creator<T> creator) {
        T t = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(m120d(parcel, i) + parcel.dataPosition());
        return t;
    }

    /* renamed from: a */
    public static void m115a(Parcel parcel, int i, List list, ClassLoader classLoader) {
        int d = m120d(parcel, i);
        int dataPosition = parcel.dataPosition();
        parcel.readList(list, classLoader);
        parcel.setDataPosition(d + dataPosition);
    }

    /* renamed from: b */
    public static int m116b(Parcel parcel) {
        return parcel.readInt();
    }

    /* renamed from: b */
    public static <T> List<T> m117b(Parcel parcel, int i, Creator<T> creator) {
        int d = m120d(parcel, i);
        int dataPosition = parcel.dataPosition();
        ArrayList createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(d + dataPosition);
        return createTypedArrayList;
    }

    /* renamed from: c */
    public static int m118c(Parcel parcel) {
        int b = m116b(parcel);
        int d = m120d(parcel, b);
        int dataPosition = parcel.dataPosition();
        if (m121e(b) != 20293) {
            throw new C0095a("Expected object header. Got 0x" + Integer.toHexString(b), parcel);
        }
        int i = dataPosition + d;
        if (i >= dataPosition && i <= parcel.dataSize()) {
            return i;
        }
        throw new C0095a("Size read is invalid start=" + dataPosition + " end=" + i, parcel);
    }

    /* renamed from: c */
    private static void m119c(Parcel parcel, int i, int i2) {
        int d = m120d(parcel, i);
        if (d != i2) {
            throw new C0095a("Expected size 4 got " + d + " (0x" + Integer.toHexString(d) + ")", parcel);
        }
    }

    /* renamed from: d */
    public static int m120d(Parcel parcel, int i) {
        return (i & -65536) != -65536 ? (i >> 16) & 65535 : parcel.readInt();
    }

    /* renamed from: e */
    public static int m121e(int i) {
        return 65535 & i;
    }

    /* renamed from: e */
    public static void m122e(Parcel parcel, int i) {
        parcel.setDataPosition(parcel.dataPosition() + m120d(parcel, i));
    }

    /* renamed from: f */
    public static boolean m123f(Parcel parcel, int i) {
        m119c(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    /* renamed from: g */
    public static byte m124g(Parcel parcel, int i) {
        m119c(parcel, i, 4);
        return (byte) parcel.readInt();
    }

    /* renamed from: h */
    public static int m125h(Parcel parcel, int i) {
        m119c(parcel, i, 4);
        return parcel.readInt();
    }

    /* renamed from: i */
    public static float m126i(Parcel parcel, int i) {
        m119c(parcel, i, 4);
        return parcel.readFloat();
    }

    /* renamed from: j */
    public static double m127j(Parcel parcel, int i) {
        m119c(parcel, i, 8);
        return parcel.readDouble();
    }

    /* renamed from: k */
    public static String m128k(Parcel parcel, int i) {
        int d = m120d(parcel, i);
        int dataPosition = parcel.dataPosition();
        String readString = parcel.readString();
        parcel.setDataPosition(d + dataPosition);
        return readString;
    }

    /* renamed from: l */
    public static IBinder m129l(Parcel parcel, int i) {
        int d = m120d(parcel, i);
        int dataPosition = parcel.dataPosition();
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(d + dataPosition);
        return readStrongBinder;
    }

    /* renamed from: m */
    public static byte[] m130m(Parcel parcel, int i) {
        int d = m120d(parcel, i);
        int dataPosition = parcel.dataPosition();
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(d + dataPosition);
        return createByteArray;
    }
}
