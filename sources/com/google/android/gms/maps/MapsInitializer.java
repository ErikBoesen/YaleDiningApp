package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.internal.C0050ad;
import com.google.android.gms.internal.C0091at;
import com.google.android.gms.internal.C0128n;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class MapsInitializer {
    private MapsInitializer() {
    }

    public static void initialize(Context context) throws GooglePlayServicesNotAvailableException {
        C0091at.m107c(context);
        C0050ad a = C0128n.m185a(context);
        try {
            CameraUpdateFactory.m323a(a.mo1179r());
            BitmapDescriptorFactory.m363a(a.mo1180s());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
