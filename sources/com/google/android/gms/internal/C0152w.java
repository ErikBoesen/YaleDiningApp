package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusClient.C0204b;
import com.google.android.gms.plus.PlusOneButton.OnPlusOneClickListener;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

/* renamed from: com.google.android.gms.internal.w */
public class C0152w extends LinearLayout implements ConnectionCallbacks, OnConnectionFailedListener {

    /* renamed from: bQ */
    private static final int f108bQ = Color.parseColor("#666666");

    /* renamed from: bR */
    protected boolean f109bR;

    /* renamed from: bS */
    protected int f110bS;

    /* renamed from: bT */
    protected final LinearLayout f111bT;

    /* renamed from: bU */
    protected final FrameLayout f112bU;

    /* renamed from: bV */
    protected final CompoundButton f113bV;

    /* renamed from: bW */
    private final ProgressBar f114bW;

    /* renamed from: bX */
    protected final C0121k f115bX;

    /* renamed from: bY */
    private final C0066aj[] f116bY;

    /* renamed from: bZ */
    protected int f117bZ;

    /* renamed from: bk */
    protected PlusClient f118bk;

    /* renamed from: ca */
    private int f119ca;

    /* renamed from: cb */
    private int f120cb;

    /* renamed from: cc */
    private Uri[] f121cc;

    /* renamed from: cd */
    private String[] f122cd;

    /* renamed from: ce */
    private String[] f123ce;

    /* renamed from: cf */
    protected String f124cf;

    /* renamed from: cg */
    protected C0092au f125cg;

    /* renamed from: ch */
    protected final Resources f126ch;

    /* renamed from: ci */
    protected final LayoutInflater f127ci;

    /* renamed from: cj */
    private C0153a f128cj;

    /* renamed from: com.google.android.gms.internal.w$a */
    protected class C0153a implements C0204b {
        protected C0153a() {
        }

        /* renamed from: a */
        public void mo1426a(ConnectionResult connectionResult, C0092au auVar) {
            if (C0152w.this.f109bR) {
                C0152w.this.f109bR = false;
                C0152w.this.f113bV.refreshDrawableState();
            }
            if (!connectionResult.isSuccess() || auVar == null) {
                C0152w.this.mo1413Q();
                return;
            }
            C0152w.this.f125cg = auVar;
            C0152w.this.mo1410D();
            C0152w.this.mo1411N();
        }
    }

    /* renamed from: com.google.android.gms.internal.w$b */
    private class C0154b implements OnClickListener, OnPlusOneClickListener {

        /* renamed from: co */
        private final OnPlusOneClickListener f131co;

        public C0154b(OnPlusOneClickListener onPlusOneClickListener) {
            this.f131co = onPlusOneClickListener;
        }

        public void onClick(View view) {
            if (view == C0152w.this.f113bV || view == C0152w.this.f115bX) {
                Intent intent = C0152w.this.f125cg == null ? null : C0152w.this.f125cg.getIntent();
                if (this.f131co != null) {
                    this.f131co.onPlusOneClick(intent);
                } else {
                    onPlusOneClick(intent);
                }
            }
        }

        public void onPlusOneClick(Intent intent) {
            Context context = C0152w.this.getContext();
            if ((context instanceof Activity) && intent != null) {
                ((Activity) context).startActivityForResult(intent, C0152w.this.f110bS);
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.w$c */
    private class C0155c extends CompoundButton {
        public C0155c(Context context) {
            super(context);
        }

        public void toggle() {
            if (C0152w.this.f109bR) {
                super.toggle();
                return;
            }
            C0152w.this.f109bR = true;
            C0152w.this.mo1412P();
        }
    }

    public C0152w(Context context) {
        this(context, null);
    }

    public C0152w(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f110bS = 0;
        this.f116bY = new C0066aj[4];
        this.f117bZ = 1;
        this.f119ca = 2;
        this.f120cb = 3;
        this.f128cj = new C0153a();
        C0091at.m102a(context, (Object) "Context must not be null.");
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0) {
            this.f126ch = null;
            this.f127ci = null;
        } else {
            Context j = m275j(context);
            this.f126ch = j.getResources();
            this.f127ci = (LayoutInflater) j.getSystemService("layout_inflater");
        }
        this.f120cb = m265a(context, attributeSet);
        this.f119ca = m271b(context, attributeSet);
        Point point = new Point();
        m267a(point);
        if (isInEditMode()) {
            TextView textView = new TextView(context);
            textView.setGravity(17);
            textView.setText("[ +1 ]");
            addView(textView, new LayoutParams(point.x, point.y));
            this.f115bX = null;
            this.f114bW = null;
            this.f113bV = null;
            this.f112bU = null;
            this.f111bT = null;
            return;
        }
        setFocusable(true);
        this.f111bT = new LinearLayout(context);
        this.f111bT.setGravity(17);
        this.f111bT.setOrientation(0);
        addView(this.f111bT);
        this.f113bV = new C0155c(context);
        this.f113bV.setBackgroundDrawable(null);
        this.f115bX = m278m(context);
        this.f112bU = m276k(context);
        this.f112bU.addView(this.f113bV, new FrameLayout.LayoutParams(point.x, point.y, 17));
        m272b(point);
        this.f114bW = m277l(context);
        this.f114bW.setVisibility(4);
        this.f112bU.addView(this.f114bW, new FrameLayout.LayoutParams(point.x, point.y, 17));
        int length = this.f116bY.length;
        for (int i = 0; i < length; i++) {
            this.f116bY[i] = m279n(getContext());
        }
        mo1414R();
    }

    /* renamed from: M */
    private void m257M() {
        boolean z = true;
        int applyDimension = (int) TypedValue.applyDimension(1, 5.0f, getContext().getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 1.0f, getContext().getResources().getDisplayMetrics());
        int length = this.f116bY.length;
        for (int i = 0; i < length; i++) {
            if (this.f116bY[i].getVisibility() == 0) {
                LayoutParams layoutParams = new LayoutParams(this.f116bY[i].getLayoutParams());
                if (z) {
                    layoutParams.setMargins(applyDimension, 0, applyDimension2, 0);
                    z = false;
                } else {
                    layoutParams.setMargins(applyDimension2, 0, applyDimension2, 0);
                }
                this.f116bY[i].setLayoutParams(layoutParams);
            }
        }
    }

    /* renamed from: O */
    private LayoutParams m258O() {
        LayoutParams layoutParams;
        int i = 0;
        switch (this.f119ca) {
            case 1:
                layoutParams = new LayoutParams(-2, -2);
                break;
            case 2:
                layoutParams = new LayoutParams(-2, -1);
                break;
            default:
                layoutParams = new LayoutParams(-2, -2);
                break;
        }
        layoutParams.bottomMargin = this.f120cb == 2 ? 1 : 0;
        if (this.f120cb != 2) {
            i = 1;
        }
        layoutParams.leftMargin = i;
        return layoutParams;
    }

    /* renamed from: U */
    private void m259U() {
        switch (this.f119ca) {
            case 1:
                this.f115bX.mo1339a(this.f123ce);
                this.f115bX.setVisibility(0);
                return;
            case 2:
                this.f115bX.mo1339a(this.f122cd);
                this.f115bX.setVisibility(0);
                return;
            default:
                this.f115bX.mo1339a((String[]) null);
                this.f115bX.setVisibility(8);
                return;
        }
    }

    /* renamed from: V */
    private void m260V() {
        if (this.f121cc == null || this.f119ca != 2) {
            for (C0066aj visibility : this.f116bY) {
                visibility.setVisibility(8);
            }
        } else {
            Point point = new Point();
            m267a(point);
            point.x = point.y;
            int length = this.f116bY.length;
            int length2 = this.f121cc.length;
            int i = 0;
            while (i < length) {
                Uri uri = i < length2 ? this.f121cc[i] : null;
                if (uri == null) {
                    this.f116bY[i].setVisibility(8);
                } else {
                    this.f116bY[i].setLayoutParams(new LayoutParams(point.x, point.y));
                    this.f116bY[i].mo1229a(uri, point.y);
                    this.f116bY[i].setVisibility(0);
                }
                i++;
            }
        }
        m257M();
    }

    /* renamed from: W */
    private Drawable m261W() {
        if (this.f126ch == null) {
            return null;
        }
        return this.f126ch.getDrawable(this.f126ch.getIdentifier(m262X(), ImageDownloader.SCHEME_DRAWABLE, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE));
    }

    /* renamed from: X */
    private String m262X() {
        switch (this.f120cb) {
            case 0:
                return "ic_plusone_small";
            case 1:
                return "ic_plusone_medium";
            case 2:
                return "ic_plusone_tall";
            default:
                return "ic_plusone_standard";
        }
    }

    /* renamed from: Y */
    private Uri m263Y() {
        return C0109e.m151a(m264Z());
    }

    /* renamed from: Z */
    private String m264Z() {
        switch (this.f120cb) {
            case 0:
                return "global_count_bubble_small";
            case 1:
                return "global_count_bubble_medium";
            case 2:
                return "global_count_bubble_tall";
            default:
                return "global_count_bubble_standard";
        }
    }

    /* renamed from: a */
    private int m265a(Context context, AttributeSet attributeSet) {
        String a = C0087ar.m98a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", context, attributeSet, true, false, "PlusOneButton");
        if ("SMALL".equalsIgnoreCase(a)) {
            return 0;
        }
        if ("MEDIUM".equalsIgnoreCase(a)) {
            return 1;
        }
        if ("TALL".equalsIgnoreCase(a)) {
            return 2;
        }
        return "STANDARD".equalsIgnoreCase(a) ? 3 : 3;
    }

    /* renamed from: a */
    private void m266a(int i, int i2) {
        this.f117bZ = i2;
        this.f120cb = i;
        mo1411N();
    }

    /* renamed from: a */
    private void m267a(Point point) {
        int i = 24;
        int i2 = 20;
        switch (this.f120cb) {
            case 0:
                i2 = 14;
                break;
            case 1:
                i = 32;
                break;
            case 2:
                i = 50;
                break;
            default:
                i = 38;
                i2 = 24;
                break;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float applyDimension = TypedValue.applyDimension(1, (float) i, displayMetrics);
        float applyDimension2 = TypedValue.applyDimension(1, (float) i2, displayMetrics);
        point.x = (int) (((double) applyDimension) + 0.5d);
        point.y = (int) (((double) applyDimension2) + 0.5d);
    }

    /* renamed from: a */
    private void m268a(View view) {
        int applyDimension = (int) TypedValue.applyDimension(1, 3.0f, getContext().getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 5.0f, getContext().getResources().getDisplayMetrics());
        if (this.f119ca != 2) {
            applyDimension2 = 0;
        }
        if (!(this.f120cb == 2 && this.f119ca == 1)) {
            applyDimension = 0;
        }
        view.setPadding(applyDimension2, 0, 0, applyDimension);
    }

    /* renamed from: a */
    private void m269a(Uri[] uriArr) {
        this.f121cc = uriArr;
        m260V();
    }

    /* renamed from: b */
    private static int m270b(int i, int i2) {
        switch (i) {
            case 0:
                return 11;
            case 2:
                return i2 != 2 ? 15 : 13;
            default:
                return 13;
        }
    }

    /* renamed from: b */
    private int m271b(Context context, AttributeSet attributeSet) {
        String a = C0087ar.m98a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, attributeSet, true, false, "PlusOneButton");
        if ("INLINE".equalsIgnoreCase(a)) {
            return 2;
        }
        if ("NONE".equalsIgnoreCase(a)) {
            return 0;
        }
        if ("BUBBLE".equalsIgnoreCase(a)) {
        }
        return 1;
    }

    /* renamed from: b */
    private void m272b(Point point) {
        point.y = (int) (((float) point.y) - TypedValue.applyDimension(1, 6.0f, getResources().getDisplayMetrics()));
        point.x = point.y;
    }

    /* renamed from: c */
    private void m273c(String[] strArr) {
        this.f122cd = strArr;
        m259U();
    }

    /* renamed from: d */
    private void m274d(String[] strArr) {
        this.f123ce = strArr;
        m259U();
    }

    /* renamed from: j */
    private Context m275j(Context context) {
        try {
            return getContext().createPackageContext(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, 4);
        } catch (NameNotFoundException e) {
            if (Log.isLoggable("PlusOneButton", 5)) {
                Log.w("PlusOneButton", "Google Play services is not installed");
            }
            return null;
        }
    }

    /* renamed from: k */
    private FrameLayout m276k(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setFocusable(false);
        return frameLayout;
    }

    /* renamed from: l */
    private ProgressBar m277l(Context context) {
        ProgressBar progressBar = new ProgressBar(context, null, 16843400);
        progressBar.setFocusable(false);
        progressBar.setIndeterminate(true);
        return progressBar;
    }

    /* renamed from: m */
    private C0121k m278m(Context context) {
        C0121k kVar = new C0121k(context);
        kVar.setFocusable(false);
        kVar.setGravity(17);
        kVar.setSingleLine();
        kVar.setTextSize(0, TypedValue.applyDimension(2, (float) m270b(this.f120cb, this.f119ca), context.getResources().getDisplayMetrics()));
        kVar.setTextColor(f108bQ);
        kVar.setVisibility(0);
        return kVar;
    }

    /* renamed from: n */
    private C0066aj m279n(Context context) {
        C0066aj ajVar = new C0066aj(context);
        ajVar.setVisibility(8);
        return ajVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: D */
    public void mo1410D() {
        if (this.f125cg != null) {
            m273c(this.f125cg.mo1279af());
            m274d(new String[]{this.f125cg.mo1278ae()});
            m269a(this.f125cg.mo1280ag());
            if (this.f125cg.mo1277ad()) {
                mo1415S();
            } else {
                mo1414R();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: N */
    public void mo1411N() {
        if (!isInEditMode()) {
            this.f111bT.removeAllViews();
            Point point = new Point();
            m267a(point);
            this.f113bV.setLayoutParams(new FrameLayout.LayoutParams(point.x, point.y, 17));
            m272b(point);
            this.f114bW.setLayoutParams(new FrameLayout.LayoutParams(point.x, point.y, 17));
            if (this.f119ca == 1) {
                this.f115bX.mo1338a(m263Y());
            } else {
                this.f115bX.mo1338a((Uri) null);
            }
            m260V();
            this.f115bX.setLayoutParams(m258O());
            this.f115bX.setTextSize(0, TypedValue.applyDimension(2, (float) m270b(this.f120cb, this.f119ca), getContext().getResources().getDisplayMetrics()));
            m268a((View) this.f115bX);
            if (this.f120cb == 2 && this.f119ca == 1) {
                this.f111bT.setOrientation(1);
                this.f111bT.addView(this.f115bX);
                this.f111bT.addView(this.f112bU);
            } else {
                this.f111bT.setOrientation(0);
                this.f111bT.addView(this.f112bU);
                for (C0066aj addView : this.f116bY) {
                    this.f111bT.addView(addView);
                }
                this.f111bT.addView(this.f115bX);
            }
            requestLayout();
        }
    }

    /* renamed from: P */
    public void mo1412P() {
        setType(2);
        this.f114bW.setVisibility(0);
        mo1416T();
    }

    /* renamed from: Q */
    public void mo1413Q() {
        setType(3);
        this.f114bW.setVisibility(4);
        mo1416T();
    }

    /* access modifiers changed from: protected */
    /* renamed from: R */
    public void mo1414R() {
        setType(1);
        this.f114bW.setVisibility(4);
        mo1416T();
    }

    /* access modifiers changed from: protected */
    /* renamed from: S */
    public void mo1415S() {
        setType(0);
        this.f114bW.setVisibility(4);
        mo1416T();
    }

    /* access modifiers changed from: protected */
    /* renamed from: T */
    public void mo1416T() {
        this.f113bV.setButtonDrawable(m261W());
        switch (this.f117bZ) {
            case 0:
                this.f113bV.setEnabled(true);
                this.f113bV.setChecked(true);
                return;
            case 1:
                this.f113bV.setEnabled(true);
                this.f113bV.setChecked(false);
                return;
            case 2:
                this.f113bV.setEnabled(false);
                this.f113bV.setChecked(true);
                return;
            default:
                this.f113bV.setEnabled(false);
                this.f113bV.setChecked(false);
                return;
        }
    }

    public void initialize(PlusClient plusClient, String str, int i) {
        C0091at.m102a(plusClient, (Object) "Plus client must not be null.");
        C0091at.m102a(str, (Object) "URL must not be null.");
        C0091at.m104a(i >= 0 && i <= 65535, (Object) "activityRequestCode must be an unsigned 16 bit integer.");
        this.f110bS = i;
        this.f124cf = str;
        if (plusClient != this.f118bk) {
            if (this.f118bk != null) {
                this.f118bk.unregisterConnectionCallbacks(this);
                this.f118bk.unregisterConnectionFailedListener(this);
            }
            this.f118bk = plusClient;
            this.f118bk.registerConnectionCallbacks(this);
            this.f118bk.registerConnectionFailedListener(this);
            for (C0066aj a : this.f116bY) {
                a.mo1231a(plusClient);
            }
        } else if (this.f118bk.isConnected()) {
            onConnected();
        }
        mo1411N();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f118bk != null) {
            if (!this.f118bk.isConnectionCallbacksRegistered(this)) {
                this.f118bk.registerConnectionCallbacks(this);
            }
            if (!this.f118bk.isConnectionFailedListenerRegistered(this)) {
                this.f118bk.registerConnectionFailedListener(this);
            }
        }
    }

    public void onConnected() {
        if (this.f124cf != null) {
            this.f118bk.mo1932a(this.f128cj, this.f124cf);
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        mo1413Q();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f118bk != null) {
            if (this.f118bk.isConnectionCallbacksRegistered(this)) {
                this.f118bk.unregisterConnectionCallbacks(this);
            }
            if (this.f118bk.isConnectionFailedListenerRegistered(this)) {
                this.f118bk.unregisterConnectionFailedListener(this);
            }
        }
    }

    public void onDisconnected() {
    }

    public boolean performClick() {
        return this.f113bV.performClick();
    }

    public void setAnnotation(int i) {
        C0091at.m102a(Integer.valueOf(i), (Object) "Annotation must not be null.");
        this.f119ca = i;
        m259U();
        mo1411N();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f113bV.setOnClickListener(onClickListener);
        this.f115bX.setOnClickListener(onClickListener);
    }

    public void setOnPlusOneClickListener(OnPlusOneClickListener onPlusOneClickListener) {
        setOnClickListener(new C0154b(onPlusOneClickListener));
    }

    public void setSize(int i) {
        m266a(i, this.f117bZ);
    }

    public void setType(int i) {
        m266a(this.f120cb, i);
    }
}
