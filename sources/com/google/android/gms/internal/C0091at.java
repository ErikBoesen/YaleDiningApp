package com.google.android.gms.internal;

/* renamed from: com.google.android.gms.internal.at */
public final class C0091at {
    private C0091at() {
        throw new AssertionError("Uninstantiable");
    }

    /* renamed from: a */
    public static <T> T m102a(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    /* renamed from: a */
    public static void m103a(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    public static void m104a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    /* renamed from: a */
    public static void m105a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    /* renamed from: b */
    public static void m106b(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    /* renamed from: c */
    public static <T> T m107c(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException("null reference");
    }
}
