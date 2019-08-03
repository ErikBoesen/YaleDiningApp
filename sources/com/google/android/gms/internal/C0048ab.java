package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.internal.C0110f.C0111a;
import java.lang.reflect.Field;

/* renamed from: com.google.android.gms.internal.ab */
public final class C0048ab<T> extends C0111a {

    /* renamed from: aE */
    private final T f16aE;

    private C0048ab(T t) {
        this.f16aE = t;
    }

    /* renamed from: a */
    public static <T> C0110f m22a(T t) {
        return new C0048ab(t);
    }

    /* renamed from: a */
    public static <T> T m23a(C0110f fVar) {
        if (fVar instanceof C0048ab) {
            return ((C0048ab) fVar).f16aE;
        }
        IBinder asBinder = fVar.asBinder();
        Field[] declaredFields = asBinder.getClass().getDeclaredFields();
        if (declaredFields.length == 1) {
            Field field = declaredFields[0];
            if (!field.isAccessible()) {
                field.setAccessible(true);
                try {
                    return field.get(asBinder);
                } catch (NullPointerException e) {
                    throw new IllegalArgumentException("Binder object is null.", e);
                } catch (IllegalArgumentException e2) {
                    throw new IllegalArgumentException("remoteBinder is the wrong class.", e2);
                } catch (IllegalAccessException e3) {
                    throw new IllegalArgumentException("Could not access the field in remoteBinder.", e3);
                }
            } else {
                throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly one declared *private* field for the wrapped object. Preferably, this is an instance of the ObjectWrapper<T> class.");
            }
        } else {
            throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly *one* declared private field for the wrapped object.  Preferably, this is an instance of the ObjectWrapper<T> class.");
        }
    }
}
