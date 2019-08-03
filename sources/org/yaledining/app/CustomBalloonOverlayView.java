package org.yaledining.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.maps.OverlayItem;

@SuppressLint({"ViewConstructor"})
public class CustomBalloonOverlayView<Item extends OverlayItem> extends BalloonOverlayView<CustomOverlayItem> {
    /* access modifiers changed from: private */
    public ImageView image;
    /* access modifiers changed from: private */
    public TextView title;

    /* renamed from: v */
    View f302v;

    public CustomBalloonOverlayView(Context context, int balloonBottomOffset) {
        super(context, balloonBottomOffset);
    }

    /* access modifiers changed from: protected */
    public void setupView(final Context context, final ViewGroup parent) {
        this.f302v = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0233R.layout.custom_balloon_overlay, parent);
        this.title = (TextView) this.f302v.findViewById(C0233R.C0234id.balloon_item_title);
        this.image = (ImageView) this.f302v.findViewById(C0233R.C0234id.balloon_item_image);
        ((ImageView) this.f302v.findViewById(C0233R.C0234id.balloon_close)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                parent.setVisibility(8);
                Intent intent = new Intent(context, RestaurantActivity.class);
                ImageView close = (ImageView) v.findViewById(C0233R.C0234id.balloon_close);
                intent.putExtra("type", CustomBalloonOverlayView.this.title.getText());
                intent.putExtra("type_restaurant", close.getTag().toString());
                intent.putExtra("Location_id", CustomBalloonOverlayView.this.title.getTag().toString());
                intent.putExtra("image", CustomBalloonOverlayView.this.image.getTag().toString());
                intent.putExtra("parent", "2");
                intent.putExtra("gps", true);
                context.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void setBalloonData(CustomOverlayItem item, ViewGroup parent) {
        this.title.setText(item.getTitle());
        this.title.setTag(new StringBuilder(String.valueOf(item.getLoc_id())).toString());
        this.image.setTag(item.getImage());
        ImageView close = (ImageView) this.f302v.findViewById(C0233R.C0234id.balloon_close);
        this.image.setImageResource(item.getImageURL());
        close.setTag(item.getType_of_restaurant());
    }
}
