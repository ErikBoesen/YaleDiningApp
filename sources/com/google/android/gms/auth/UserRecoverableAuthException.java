package com.google.android.gms.auth;

import android.content.Intent;

public class UserRecoverableAuthException extends GoogleAuthException {

    /* renamed from: bo */
    private final Intent f3bo;

    public UserRecoverableAuthException(String str, Intent intent) {
        super(str);
        this.f3bo = intent;
    }

    public Intent getIntent() {
        return new Intent(this.f3bo);
    }
}
