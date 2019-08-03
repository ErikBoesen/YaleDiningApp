package com.google.android.gms.internal;

/* renamed from: com.google.android.gms.internal.av */
public final class C0093av {
    private C0093av() {
    }

    /* renamed from: a */
    public static Boolean m112a(byte b) {
        switch (b) {
            case 0:
                return Boolean.FALSE;
            case 1:
                return Boolean.TRUE;
            default:
                return null;
        }
    }

    /* renamed from: b */
    public static byte m113b(Boolean bool) {
        if (bool != null) {
            return bool.booleanValue() ? (byte) 1 : 0;
        }
        return -1;
    }
}
