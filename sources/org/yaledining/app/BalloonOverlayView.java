package org.yaledining.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.maps.OverlayItem;

@SuppressLint({"ViewConstructor"})
public class BalloonOverlayView<Item extends OverlayItem> extends FrameLayout {
    private LinearLayout layout;
    private TextView title;

    public BalloonOverlayView(Context context, int balloonBottomOffset) {
        super(context);
        setPadding(10, 0, 10, balloonBottomOffset);
        this.layout = new LinearLayout(context);
        this.layout.setVisibility(0);
        setupView(context, this.layout);
        LayoutParams params = new LayoutParams(-2, -2);
        params.gravity = 0;
        addView(this.layout, params);
    }

    /* access modifiers changed from: protected */
    public void setupView(Context context, ViewGroup parent) {
        View v = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0233R.layout.balloon_overlay, parent);
        this.title = (TextView) v.findViewById(C0233R.C0234id.balloon_item_title);
        ((ImageView) v.findViewById(C0233R.C0234id.balloon_close)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            }
        });
    }

    public void setData(Item item) {
        System.out.println("DATa" + item);
        this.layout.setVisibility(0);
        setBalloonData(item, this.layout);
    }

    /* access modifiers changed from: protected */
    public void setBalloonData(Item item, ViewGroup parent) {
        if (item.getTitle() != null) {
            this.title.setVisibility(0);
            this.title.setText(item.getTitle());
            return;
        }
        this.title.setText("");
        this.title.setVisibility(4);
    }
}
