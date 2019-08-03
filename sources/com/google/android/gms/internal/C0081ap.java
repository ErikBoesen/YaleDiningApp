package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.google.android.gms.internal.ap */
public final class C0081ap {

    /* renamed from: com.google.android.gms.internal.ap$a */
    public static final class C0083a {

        /* renamed from: cv */
        private final List<String> f46cv;

        /* renamed from: cw */
        private final Object f47cw;

        private C0083a(Object obj) {
            this.f47cw = C0091at.m107c(obj);
            this.f46cv = new ArrayList();
        }

        /* renamed from: a */
        public C0083a mo1269a(String str, Object obj) {
            this.f46cv.add(((String) C0091at.m107c(str)) + "=" + String.valueOf(obj));
            return this;
        }

        public String toString() {
            StringBuilder append = new StringBuilder(100).append(this.f47cw.getClass().getSimpleName()).append('{');
            int size = this.f46cv.size();
            for (int i = 0; i < size; i++) {
                append.append((String) this.f46cv.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }

    private C0081ap() {
        throw new AssertionError("Uninstantiable");
    }

    /* renamed from: d */
    public static C0083a m93d(Object obj) {
        return new C0083a(obj);
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }
}
