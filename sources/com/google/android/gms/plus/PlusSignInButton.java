package com.google.android.gms.plus;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.gms.internal.C0091at;
import com.google.android.gms.internal.C0109e;

public final class PlusSignInButton extends ImageView {
    public static final int BUTTON_SIZE_STANDARD = 0;
    public static final int BUTTON_SIZE_WIDE = 1;

    /* renamed from: bN */
    static final Uri f298bN = C0109e.m151a("client_sign_in");

    /* renamed from: bO */
    static final Uri f299bO = C0109e.m151a("client_sign_in_w");

    /* renamed from: bP */
    private int f300bP;

    public PlusSignInButton(Context context) {
        this(context, null);
    }

    public PlusSignInButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842866);
        this.f300bP = 0;
        setPadding(0, 0, 0, 0);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setAlpha(0);
        setBackgroundDrawable(colorDrawable);
        m409L();
    }

    /* renamed from: K */
    private Uri m408K() {
        switch (this.f300bP) {
            case 0:
                return f298bN;
            case 1:
                return f299bO;
            default:
                throw new IllegalStateException();
        }
    }

    /* renamed from: L */
    private void m409L() {
        setImageURI(m408K());
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        setColorFilter(isPressed() ? C0109e.f72h : 0);
    }

    public void setSize(int i) {
        C0091at.m104a(i >= 0 && i < 2, (Object) "Invalid button type.");
        this.f300bP = i;
        m409L();
    }
}
