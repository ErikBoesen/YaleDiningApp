package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.p000v4.app.ShareCompat;
import android.support.p000v4.view.accessibility.AccessibilityEventCompat;
import android.text.TextUtils;
import com.google.android.gms.internal.C0091at;
import java.util.ArrayList;

public final class PlusShare {
    public static final String EXTRA_CONTENT_DEEP_LINK_ID = "com.google.android.apps.plus.CONTENT_DEEP_LINK_ID";
    public static final String EXTRA_CONTENT_DEEP_LINK_METADATA = "com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA";
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION = "description";
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL = "thumbnailUrl";
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_TITLE = "title";
    public static final String PARAM_CONTENT_DEEP_LINK_ID = "deep_link_id";

    public static class Builder {

        /* renamed from: bo */
        private final Intent f295bo = new Intent().setAction("android.intent.action.SEND");

        /* renamed from: cm */
        private Activity f296cm;

        /* renamed from: cn */
        private ArrayList<Uri> f297cn;

        public Builder() {
        }

        private Builder(Activity activity) {
            this.f296cm = activity;
            this.f295bo.putExtra(ShareCompat.EXTRA_CALLING_PACKAGE, activity.getPackageName());
            this.f295bo.putExtra(ShareCompat.EXTRA_CALLING_ACTIVITY, activity.getComponentName());
            this.f295bo.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        }

        public static Builder from(Activity activity) {
            return new Builder(activity);
        }

        public Builder addStream(Uri uri) {
            Uri uri2 = (Uri) this.f295bo.getParcelableExtra("android.intent.extra.STREAM");
            if (uri2 == null) {
                return setStream(uri);
            }
            if (this.f297cn == null) {
                this.f297cn = new ArrayList<>();
            }
            this.f297cn.add(uri2);
            this.f297cn.add(uri);
            return this;
        }

        public Intent getIntent() {
            boolean z = this.f297cn != null && this.f297cn.size() > 1;
            boolean equals = this.f295bo.getAction().equals("android.intent.action.SEND_MULTIPLE");
            if (!z && equals) {
                this.f295bo.setAction("android.intent.action.SEND");
                if (this.f297cn == null || this.f297cn.isEmpty()) {
                    this.f295bo.removeExtra("android.intent.extra.STREAM");
                } else {
                    this.f295bo.putExtra("android.intent.extra.STREAM", (Parcelable) this.f297cn.get(0));
                }
                this.f297cn = null;
            }
            if (z && !equals) {
                this.f295bo.setAction("android.intent.action.SEND_MULTIPLE");
                if (this.f297cn == null || this.f297cn.isEmpty()) {
                    this.f295bo.removeExtra("android.intent.extra.STREAM");
                } else {
                    this.f295bo.putParcelableArrayListExtra("android.intent.extra.STREAM", this.f297cn);
                }
            }
            this.f295bo.setPackage(GooglePlusUtil.GOOGLE_PLUS_PACKAGE);
            return this.f295bo;
        }

        public Builder setContent(Uri uri) {
            boolean z = true;
            C0091at.m106b(this.f296cm != null, "Must include the launching activity with PlusShare.Builder.from() before setting deep links");
            if (uri == null) {
                z = false;
            }
            C0091at.m106b(z, "The deepLinkUri parameter is required.");
            this.f295bo.putExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID, uri.toString());
            return this;
        }

        public Builder setContent(String str, String str2, String str3, Uri uri) {
            boolean z = true;
            C0091at.m106b(this.f296cm != null, "Must include the launching activity with PlusShare.Builder.from() before setting deep links");
            C0091at.m106b(!TextUtils.isEmpty(str), "The deepLinkId parameter is required.");
            C0091at.m106b(!TextUtils.isEmpty(str2), "The title parameter is required.");
            if (TextUtils.isEmpty(str3)) {
                z = false;
            }
            C0091at.m106b(z, "The description parameter is required.");
            Bundle bundle = new Bundle();
            bundle.putString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, str2);
            bundle.putString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str3);
            if (uri != null) {
                bundle.putString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL, uri.toString());
            }
            this.f295bo.putExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID, str);
            this.f295bo.putExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_METADATA, bundle);
            return this;
        }

        public Builder setStream(Uri uri) {
            this.f297cn = null;
            this.f295bo.putExtra("android.intent.extra.STREAM", uri);
            return this;
        }

        public Builder setText(CharSequence charSequence) {
            this.f295bo.putExtra("android.intent.extra.TEXT", charSequence);
            return this;
        }

        public Builder setType(String str) {
            this.f295bo.setType(str);
            return this;
        }
    }

    private PlusShare() {
        throw new AssertionError();
    }

    public static String getDeepLinkId(Intent intent) {
        if (intent == null || intent.getData() == null) {
            return null;
        }
        return intent.getData().getQueryParameter(PARAM_CONTENT_DEEP_LINK_ID);
    }
}
