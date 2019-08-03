package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;

public final class ConnectionResult {
    public static final int DEVELOPER_ERROR = 10;
    public static final int INTERNAL_ERROR = 8;
    public static final int INVALID_ACCOUNT = 5;
    public static final int NETWORK_ERROR = 7;
    public static final int RESOLUTION_REQUIRED = 6;
    public static final int SERVICE_DISABLED = 3;
    public static final int SERVICE_INVALID = 9;
    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;

    /* renamed from: aB */
    public static final ConnectionResult f4aB = new ConnectionResult(0, null);

    /* renamed from: aC */
    private final PendingIntent f5aC;

    /* renamed from: aD */
    private final int f6aD;

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this.f6aD = i;
        this.f5aC = pendingIntent;
    }

    public int getErrorCode() {
        return this.f6aD;
    }

    public PendingIntent getResolution() {
        return this.f5aC;
    }

    public boolean hasResolution() {
        return (this.f6aD == 0 || this.f5aC == null) ? false : true;
    }

    public boolean isSuccess() {
        return this.f6aD == 0;
    }

    public void startResolutionForResult(Activity activity, int i) throws SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.f5aC.getIntentSender(), i, null, 0, 0, 0);
        }
    }
}
