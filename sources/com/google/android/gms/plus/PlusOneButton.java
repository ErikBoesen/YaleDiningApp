package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.google.android.gms.internal.C0091at;
import com.google.android.gms.internal.C0152w;

public final class PlusOneButton extends ViewGroup {
    public static final int ANNOTATION_BUBBLE = 1;
    public static final int ANNOTATION_INLINE = 2;
    public static final int ANNOTATION_NONE = 0;
    public static final int SIZE_MEDIUM = 1;
    public static final int SIZE_SMALL = 0;
    public static final int SIZE_STANDARD = 3;
    public static final int SIZE_TALL = 2;

    /* renamed from: ct */
    private final C0152w f294ct;

    public interface OnPlusOneClickListener {
        void onPlusOneClick(Intent intent);
    }

    public PlusOneButton(Context context) {
        this(context, null);
    }

    public PlusOneButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f294ct = new C0152w(context, attributeSet);
        addView(this.f294ct);
        if (!isInEditMode()) {
            setOnPlusOneClickListener(null);
        }
    }

    public void initialize(PlusClient plusClient, String str, int i) {
        C0091at.m104a(getContext() instanceof Activity, (Object) "To use this method, the PlusOneButton must be placed in an Activity. Use initialize(PlusClient, String, OnPlusOneClickListener).");
        this.f294ct.initialize(plusClient, str, i);
    }

    public void initialize(PlusClient plusClient, String str, OnPlusOneClickListener onPlusOneClickListener) {
        this.f294ct.initialize(plusClient, str, 0);
        this.f294ct.setOnPlusOneClickListener(onPlusOneClickListener);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f294ct.layout(0, 0, i3 - i, i4 - i2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        C0152w wVar = this.f294ct;
        measureChild(wVar, i, i2);
        setMeasuredDimension(wVar.getMeasuredWidth(), wVar.getMeasuredHeight());
    }

    public void setAnnotation(int i) {
        this.f294ct.setAnnotation(i);
    }

    public void setOnPlusOneClickListener(OnPlusOneClickListener onPlusOneClickListener) {
        this.f294ct.setOnPlusOneClickListener(onPlusOneClickListener);
    }

    public void setSize(int i) {
        this.f294ct.setSize(i);
    }
}
