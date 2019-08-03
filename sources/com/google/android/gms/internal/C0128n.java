package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.C0050ad.C0051a;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* renamed from: com.google.android.gms.internal.n */
public class C0128n {

    /* renamed from: x */
    private static Context f83x;

    /* renamed from: y */
    private static C0050ad f84y;

    /* renamed from: a */
    public static C0050ad m185a(Context context) throws GooglePlayServicesNotAvailableException {
        C0091at.m107c(context);
        m191c(context);
        if (f84y == null) {
            m192d(context);
        }
        if (f84y != null) {
            return f84y;
        }
        f84y = C0051a.m29m((IBinder) m187a(getRemoteContext(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
        m188b(context);
        return f84y;
    }

    /* renamed from: a */
    private static <T> T m186a(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException("Unable to instantiate the dynamic class " + cls.getName());
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unable to call the default constructor of " + cls.getName());
        }
    }

    /* renamed from: a */
    private static <T> T m187a(ClassLoader classLoader, String str) {
        try {
            return m186a(((ClassLoader) C0091at.m107c(classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find dynamic class " + str);
        }
    }

    /* renamed from: b */
    private static void m188b(Context context) {
        try {
            f84y.mo1177b(C0048ab.m22a(getRemoteContext(context).getResources()));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    /* renamed from: b */
    public static boolean m189b() {
        return m190c() != null;
    }

    /* renamed from: c */
    private static Class<?> m190c() {
        try {
            return Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /* renamed from: c */
    public static void m191c(Context context) throws GooglePlayServicesNotAvailableException {
        if (!m189b()) {
            int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
            if (isGooglePlayServicesAvailable != 0) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
        }
    }

    /* renamed from: d */
    private static void m192d(Context context) {
        Class c = m190c();
        if (c != null) {
            Log.i(C0128n.class.getSimpleName(), "Making Creator statically");
            f84y = (C0050ad) m186a(c);
            m188b(context);
        }
    }

    private static Context getRemoteContext(Context context) {
        if (f83x == null) {
            if (m190c() != null) {
                f83x = context;
            } else {
                f83x = GooglePlayServicesUtil.getRemoteContext(context);
            }
        }
        return f83x;
    }
}
