package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

/* renamed from: com.google.android.gms.internal.k */
public class C0121k extends FrameLayout {

    /* renamed from: u */
    private String[] f78u = null;

    /* renamed from: v */
    private final ImageView f79v;

    /* renamed from: w */
    private final TextView f80w;

    public C0121k(Context context) {
        super(context);
        this.f79v = new ImageView(context);
        addView(this.f79v, new LayoutParams(-2, -2, 17));
        this.f80w = new TextView(context);
        addView(this.f80w, new LayoutParams(-2, -2, 17));
        bringChildToFront(this.f80w);
    }

    /* renamed from: a */
    public void mo1338a(Uri uri) {
        this.f79v.setImageURI(uri);
    }

    /* renamed from: a */
    public void mo1339a(String[] strArr) {
        this.f78u = strArr;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        Paint paint = new Paint();
        paint.setTextSize(this.f80w.getTextSize());
        paint.setTypeface(this.f80w.getTypeface());
        int i3 = this.f78u != null ? this.f78u.length : 0;
        int i4 = 0;
        CharSequence charSequence = null;
        for (int i5 = 0; i5 < i3; i5++) {
            if (this.f78u[i5] != null) {
                int measureText = (int) paint.measureText(this.f78u[i5]);
                if (measureText <= size && measureText >= i4) {
                    charSequence = this.f78u[i5];
                    i4 = measureText;
                }
            }
        }
        if (charSequence == null || !charSequence.equals(this.f80w.getText())) {
            this.f80w.setText(charSequence);
        }
        super.onMeasure(i, i2);
    }

    public void setGravity(int i) {
        this.f80w.setGravity(i);
    }

    public void setSingleLine() {
        this.f80w.setSingleLine();
    }

    public void setTextColor(int i) {
        this.f80w.setTextColor(i);
    }

    public void setTextSize(int i, float f) {
        this.f80w.setTextSize(i, f);
    }
}
