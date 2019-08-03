package com.google.android.gms.internal;

import java.util.Iterator;

/* renamed from: com.google.android.gms.internal.i */
class C0117i implements C0059ag<T> {

    /* renamed from: b */
    final /* synthetic */ C0102d f76b;

    C0117i(C0102d dVar) {
        this.f76b = dVar;
    }

    /* renamed from: b */
    public void mo1209b(T t) {
        this.f76b.f53cp = t;
        Iterator it = this.f76b.f55cr.iterator();
        while (it.hasNext()) {
            ((C0108a) it.next()).mo1320a(this.f76b.f53cp);
        }
        this.f76b.f55cr.clear();
        this.f76b.f54cq = null;
    }
}
