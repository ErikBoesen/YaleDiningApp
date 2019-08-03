package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;

/* renamed from: com.google.android.gms.internal.ak */
public class C0068ak implements OnClickListener {

    /* renamed from: bo */
    private final Intent f29bo;

    /* renamed from: bp */
    private final int f30bp;

    /* renamed from: g */
    private final Activity f31g;

    public C0068ak(Activity activity, Intent intent, int i) {
        this.f31g = activity;
        this.f29bo = intent;
        this.f30bp = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.f31g.startActivityForResult(this.f29bo, this.f30bp);
            dialogInterface.dismiss();
        } catch (ActivityNotFoundException e) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
