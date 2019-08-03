package org.yaledining.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import java.util.ArrayList;

public class CustomItemizedOverlay<Item extends OverlayItem> extends BalloonItemizedOverlay<CustomOverlayItem> {

    /* renamed from: c */
    private Context f303c;
    private ArrayList<CustomOverlayItem> m_overlays = new ArrayList<>();

    public CustomItemizedOverlay(Drawable defaultMarker, MapView mapView) {
        super(boundCenterBottom(defaultMarker), mapView);
        this.f303c = mapView.getContext();
    }

    public void addOverlay(CustomOverlayItem overlay) {
        this.m_overlays.add(overlay);
        populate();
    }

    /* access modifiers changed from: protected */
    public CustomOverlayItem createItem(int i) {
        return (CustomOverlayItem) this.m_overlays.get(i);
    }

    public int size() {
        return this.m_overlays.size();
    }

    /* access modifiers changed from: protected */
    public boolean onBalloonTap(int index, CustomOverlayItem item) {
        return true;
    }

    /* access modifiers changed from: protected */
    public BalloonOverlayView<CustomOverlayItem> createBalloonOverlayView() {
        return new CustomBalloonOverlayView(getMapView().getContext(), 0);
    }
}
