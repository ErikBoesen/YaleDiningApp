package com.google.android.gms.auth;

import android.content.Intent;

public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException {

    /* renamed from: k */
    private final int f2k;

    GooglePlayServicesAvailabilityException(int i, String str, Intent intent) {
        super(str, intent);
        this.f2k = i;
    }

    public int getConnectionStatusCode() {
        return this.f2k;
    }
}
