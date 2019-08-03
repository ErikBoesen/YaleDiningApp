package org.yaledining.app;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import java.util.List;

public abstract class BalloonItemizedOverlay<Item extends OverlayItem> extends ItemizedOverlay<Item> {
    private BalloonOverlayView<Item> balloonView;
    private View clickRegion;
    /* access modifiers changed from: private */
    public int currentFocussedIndex;
    /* access modifiers changed from: private */
    public Item currentFocussedItem;
    private MapView mapView;

    /* renamed from: mc */
    final MapController f301mc;

    public BalloonItemizedOverlay(Drawable defaultMarker, MapView mapView2) {
        super(defaultMarker);
        this.mapView = mapView2;
        this.f301mc = mapView2.getController();
    }

    /* access modifiers changed from: protected */
    public boolean onBalloonTap(int index, Item item) {
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean onTap(int index) {
        this.currentFocussedIndex = index;
        this.currentFocussedItem = createItem(index);
        createAndDisplayBalloonOverlay();
        this.f301mc.animateTo(this.currentFocussedItem.getPoint());
        return true;
    }

    /* access modifiers changed from: protected */
    public BalloonOverlayView<Item> createBalloonOverlayView() {
        return new BalloonOverlayView<>(getMapView().getContext(), 0);
    }

    /* access modifiers changed from: protected */
    public MapView getMapView() {
        return this.mapView;
    }

    /* access modifiers changed from: protected */
    public void hideBalloon() {
        if (this.balloonView != null) {
            this.balloonView.setVisibility(8);
        }
    }

    private void hideOtherBalloons(List<Overlay> overlays) {
        for (Overlay overlay : overlays) {
            if ((overlay instanceof BalloonItemizedOverlay) && overlay != this) {
                ((BalloonItemizedOverlay) overlay).hideBalloon();
            }
        }
    }

    private OnTouchListener createBalloonTouchListener() {
        return new OnTouchListener() {
            float startX;
            float startY;

            public boolean onTouch(View v, MotionEvent event) {
                Drawable d = ((View) v.getParent()).findViewById(C0233R.C0234id.balloon_main_layout).getBackground();
                if (event.getAction() == 0) {
                    if (d.setState(new int[]{16842919})) {
                        d.invalidateSelf();
                    }
                    this.startX = event.getX();
                    this.startY = event.getY();
                    return true;
                } else if (event.getAction() != 1) {
                    return false;
                } else {
                    if (d.setState(new int[0])) {
                        d.invalidateSelf();
                    }
                    if (Math.abs(this.startX - event.getX()) < 40.0f && Math.abs(this.startY - event.getY()) < 40.0f) {
                        BalloonItemizedOverlay.this.onBalloonTap(BalloonItemizedOverlay.this.currentFocussedIndex, BalloonItemizedOverlay.this.currentFocussedItem);
                    }
                    return true;
                }
            }
        };
    }

    public Item getFocus() {
        return this.currentFocussedItem;
    }

    public void setFocus(Item item) {
        this.currentFocussedItem = item;
        if (this.currentFocussedItem == null) {
            hideBalloon();
        } else {
            createAndDisplayBalloonOverlay();
        }
    }

    private boolean createAndDisplayBalloonOverlay() {
        boolean isRecycled;
        if (this.balloonView == null) {
            this.balloonView = createBalloonOverlayView();
            this.clickRegion = this.balloonView.findViewById(C0233R.C0234id.balloon_inner_layout);
            this.clickRegion.setOnTouchListener(createBalloonTouchListener());
            isRecycled = false;
        } else {
            isRecycled = true;
        }
        this.balloonView.setVisibility(8);
        List<Overlay> mapOverlays = this.mapView.getOverlays();
        if (mapOverlays.size() > 1) {
            hideOtherBalloons(mapOverlays);
        }
        if (this.currentFocussedItem != null) {
            this.balloonView.setData(this.currentFocussedItem);
        }
        LayoutParams params = new LayoutParams(-2, -2, this.currentFocussedItem.getPoint(), 81);
        this.balloonView.setVisibility(0);
        if (isRecycled) {
            this.balloonView.setLayoutParams(params);
        } else {
            this.mapView.addView(this.balloonView, params);
        }
        return isRecycled;
    }
}
