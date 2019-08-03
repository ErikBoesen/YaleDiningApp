package org.yaledining.app;

import android.graphics.drawable.Drawable;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import java.util.List;

public class Gps_mapActivity extends MapActivity {
    Drawable drawable;
    Drawable drawable2;
    CustomItemizedOverlay<CustomOverlayItem> itemizedOverlay;
    CustomItemizedOverlay<CustomOverlayItem> itemizedOverlay2;
    private MapController mapController;
    List<Overlay> mapOverlays;
    private MapView mapView;

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Gps_mapActivity.super.onDestroy();
        Bottomtabs.gps = false;
        Bottomtabs.type = "";
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Gps_mapActivity.super.onPause();
    }

    /* JADX WARNING: type inference failed for: r0v18, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r29) {
        /*
            r28 = this;
            org.yaledining.app.Gps_mapActivity.super.onCreate(r29)
            r24 = 1
            r0 = r28
            r1 = r24
            r0.requestWindowFeature(r1)
            r24 = 1
            org.yaledining.app.Bottomtabs.gps = r24
            r24 = 2130903054(0x7f03000e, float:1.7412915E38)
            r0 = r28
            r1 = r24
            r0.setContentView(r1)
            r24 = 2131230830(0x7f08006e, float:1.8077724E38)
            r0 = r28
            r1 = r24
            android.view.View r23 = r0.findViewById(r1)     // Catch:{ Exception -> 0x0323 }
            android.widget.TextView r23 = (android.widget.TextView) r23     // Catch:{ Exception -> 0x0323 }
            android.content.Intent r24 = r28.getIntent()     // Catch:{ Exception -> 0x0323 }
            android.os.Bundle r24 = r24.getExtras()     // Catch:{ Exception -> 0x0323 }
            java.lang.String r25 = "name"
            java.lang.String r24 = r24.getString(r25)     // Catch:{ Exception -> 0x0323 }
            r23.setText(r24)     // Catch:{ Exception -> 0x0323 }
            r24 = 2131230831(0x7f08006f, float:1.8077726E38)
            r0 = r28
            r1 = r24
            android.view.View r6 = r0.findViewById(r1)     // Catch:{ Exception -> 0x0323 }
            android.widget.Button r6 = (android.widget.Button) r6     // Catch:{ Exception -> 0x0323 }
            r24 = 8
            r0 = r24
            r6.setVisibility(r0)     // Catch:{ Exception -> 0x0323 }
            r24 = 2131230827(0x7f08006b, float:1.8077718E38)
            r0 = r28
            r1 = r24
            android.view.View r6 = r0.findViewById(r1)     // Catch:{ Exception -> 0x0323 }
            android.widget.Button r6 = (android.widget.Button) r6     // Catch:{ Exception -> 0x0323 }
            r24 = 8
            r0 = r24
            r6.setVisibility(r0)     // Catch:{ Exception -> 0x0323 }
            java.lang.String r24 = "window"
            r0 = r28
            r1 = r24
            java.lang.Object r24 = r0.getSystemService(r1)     // Catch:{ Exception -> 0x0323 }
            android.view.WindowManager r24 = (android.view.WindowManager) r24     // Catch:{ Exception -> 0x0323 }
            android.view.Display r7 = r24.getDefaultDisplay()     // Catch:{ Exception -> 0x0323 }
            r24 = 2131230775(0x7f080037, float:1.8077612E38)
            r0 = r28
            r1 = r24
            android.view.View r16 = r0.findViewById(r1)     // Catch:{ Exception -> 0x0323 }
            android.widget.RelativeLayout r16 = (android.widget.RelativeLayout) r16     // Catch:{ Exception -> 0x0323 }
            int r24 = org.yaledining.app.NewYalediningActivity.dHeight     // Catch:{ Exception -> 0x0323 }
            r25 = 1850(0x73a, float:2.592E-42)
            r0 = r24
            r1 = r25
            if (r0 < r1) goto L_0x01ab
            r13 = 360(0x168, float:5.04E-43)
        L_0x0089:
            android.widget.LinearLayout$LayoutParams r24 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0323 }
            r25 = -1
            int r26 = r7.getHeight()     // Catch:{ Exception -> 0x0323 }
            int r26 = r26 - r13
            r24.<init>(r25, r26)     // Catch:{ Exception -> 0x0323 }
            r0 = r16
            r1 = r24
            r0.setLayoutParams(r1)     // Catch:{ Exception -> 0x0323 }
            r24 = 2131230773(0x7f080035, float:1.8077608E38)
            r0 = r28
            r1 = r24
            android.view.View r17 = r0.findViewById(r1)     // Catch:{ Exception -> 0x0323 }
            android.widget.LinearLayout r17 = (android.widget.LinearLayout) r17     // Catch:{ Exception -> 0x0323 }
            org.yaledining.app.Bottomtabs r5 = new org.yaledining.app.Bottomtabs     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            r5.<init>(r0)     // Catch:{ Exception -> 0x0323 }
            r0 = r17
            r0.addView(r5)     // Catch:{ Exception -> 0x0323 }
            r24 = 2131230785(0x7f080041, float:1.8077633E38)
            r0 = r28
            r1 = r24
            android.view.View r24 = r0.findViewById(r1)     // Catch:{ Exception -> 0x0323 }
            com.google.android.maps.MapView r24 = (com.google.android.maps.MapView) r24     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            r1 = r28
            r1.mapView = r0     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            com.google.android.maps.MapView r0 = r0.mapView     // Catch:{ Exception -> 0x0323 }
            r24 = r0
            r25 = 1
            r24.setAlwaysDrawnWithCacheEnabled(r25)     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            com.google.android.maps.MapView r0 = r0.mapView     // Catch:{ Exception -> 0x0323 }
            r24 = r0
            r25 = 0
            r24.setStreetView(r25)     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            com.google.android.maps.MapView r0 = r0.mapView     // Catch:{ Exception -> 0x0323 }
            r24 = r0
            r25 = 1
            r24.setBuiltInZoomControls(r25)     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            com.google.android.maps.MapView r0 = r0.mapView     // Catch:{ Exception -> 0x0323 }
            r24 = r0
            com.google.android.maps.MapController r24 = r24.getController()     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            r1 = r28
            r1.mapController = r0     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            com.google.android.maps.MapView r0 = r0.mapView     // Catch:{ Exception -> 0x0323 }
            r24 = r0
            java.util.List r24 = r24.getOverlays()     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            r1 = r28
            r1.mapOverlays = r0     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            com.google.android.maps.MapController r0 = r0.mapController     // Catch:{ Exception -> 0x0323 }
            r24 = r0
            r25 = 18
            r24.setZoom(r25)     // Catch:{ Exception -> 0x0323 }
            android.content.res.Resources r24 = r28.getResources()     // Catch:{ Exception -> 0x0323 }
            r25 = 2130837623(0x7f020077, float:1.7280205E38)
            android.graphics.drawable.Drawable r8 = r24.getDrawable(r25)     // Catch:{ Exception -> 0x0323 }
            org.yaledining.app.CustomItemizedOverlay r24 = new org.yaledining.app.CustomItemizedOverlay     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            com.google.android.maps.MapView r0 = r0.mapView     // Catch:{ Exception -> 0x0323 }
            r25 = r0
            r0 = r24
            r1 = r25
            r0.<init>(r8, r1)     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            r1 = r28
            r1.itemizedOverlay = r0     // Catch:{ Exception -> 0x0323 }
            r11 = 0
        L_0x0136:
            org.json.JSONArray r24 = org.yaledining.app.HTTPManager.Database.Data     // Catch:{ Exception -> 0x0323 }
            int r24 = r24.length()     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            if (r11 < r0) goto L_0x01f7
            r0 = r28
            java.util.List<com.google.android.maps.Overlay> r0 = r0.mapOverlays     // Catch:{ Exception -> 0x0323 }
            r24 = r0
            r0 = r28
            org.yaledining.app.CustomItemizedOverlay<org.yaledining.app.CustomOverlayItem> r0 = r0.itemizedOverlay     // Catch:{ Exception -> 0x0323 }
            r25 = r0
            r24.add(r25)     // Catch:{ Exception -> 0x0323 }
            org.json.JSONArray r24 = org.yaledining.app.HTTPManager.Database.Data     // Catch:{ Exception -> 0x0323 }
            r25 = 0
            org.json.JSONArray r24 = r24.getJSONArray(r25)     // Catch:{ Exception -> 0x0323 }
            r25 = 5
            java.lang.String r21 = r24.getString(r25)     // Catch:{ Exception -> 0x0323 }
            java.util.StringTokenizer r22 = new java.util.StringTokenizer     // Catch:{ Exception -> 0x0323 }
            java.lang.String r24 = ","
            r0 = r22
            r1 = r21
            r2 = r24
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x0323 }
            java.lang.String r14 = r22.nextToken()     // Catch:{ Exception -> 0x0323 }
            java.lang.String r18 = r22.nextToken()     // Catch:{ Exception -> 0x0323 }
            double r24 = java.lang.Double.parseDouble(r14)     // Catch:{ Exception -> 0x0323 }
            r26 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r24 = r24 * r26
            java.lang.Double r10 = java.lang.Double.valueOf(r24)     // Catch:{ Exception -> 0x0323 }
            double r24 = java.lang.Double.parseDouble(r18)     // Catch:{ Exception -> 0x0323 }
            r26 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r24 = r24 * r26
            java.lang.Double r9 = java.lang.Double.valueOf(r24)     // Catch:{ Exception -> 0x0323 }
            int r15 = r9.intValue()     // Catch:{ Exception -> 0x0323 }
            int r19 = r10.intValue()     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            com.google.android.maps.MapController r0 = r0.mapController     // Catch:{ Exception -> 0x0323 }
            r24 = r0
            com.google.android.maps.GeoPoint r25 = new com.google.android.maps.GeoPoint     // Catch:{ Exception -> 0x0323 }
            r0 = r25
            r1 = r19
            r0.<init>(r1, r15)     // Catch:{ Exception -> 0x0323 }
            r24.setCenter(r25)     // Catch:{ Exception -> 0x0323 }
        L_0x01aa:
            return
        L_0x01ab:
            int r24 = org.yaledining.app.NewYalediningActivity.dHeight     // Catch:{ Exception -> 0x0323 }
            r25 = 1000(0x3e8, float:1.401E-42)
            r0 = r24
            r1 = r25
            if (r0 < r1) goto L_0x01c3
            int r24 = org.yaledining.app.NewYalediningActivity.dHeight     // Catch:{ Exception -> 0x0323 }
            r25 = 1300(0x514, float:1.822E-42)
            r0 = r24
            r1 = r25
            if (r0 > r1) goto L_0x01c3
            r13 = 240(0xf0, float:3.36E-43)
            goto L_0x0089
        L_0x01c3:
            int r24 = org.yaledining.app.NewYalediningActivity.dHeight     // Catch:{ Exception -> 0x0323 }
            r25 = 440(0x1b8, float:6.17E-43)
            r0 = r24
            r1 = r25
            if (r0 < r1) goto L_0x01db
            int r24 = org.yaledining.app.NewYalediningActivity.dHeight     // Catch:{ Exception -> 0x0323 }
            r25 = 500(0x1f4, float:7.0E-43)
            r0 = r24
            r1 = r25
            if (r0 > r1) goto L_0x01db
            r13 = 130(0x82, float:1.82E-43)
            goto L_0x0089
        L_0x01db:
            int r24 = org.yaledining.app.NewYalediningActivity.dHeight     // Catch:{ Exception -> 0x0323 }
            r25 = 300(0x12c, float:4.2E-43)
            r0 = r24
            r1 = r25
            if (r0 < r1) goto L_0x01f3
            int r24 = org.yaledining.app.NewYalediningActivity.dHeight     // Catch:{ Exception -> 0x0323 }
            r25 = 400(0x190, float:5.6E-43)
            r0 = r24
            r1 = r25
            if (r0 > r1) goto L_0x01f3
            r13 = 95
            goto L_0x0089
        L_0x01f3:
            r13 = 180(0xb4, float:2.52E-43)
            goto L_0x0089
        L_0x01f7:
            org.json.JSONArray r24 = org.yaledining.app.HTTPManager.Database.Data     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            org.json.JSONArray r24 = r0.getJSONArray(r11)     // Catch:{ Exception -> 0x0323 }
            r25 = 5
            java.lang.String r21 = r24.getString(r25)     // Catch:{ Exception -> 0x0323 }
            java.util.StringTokenizer r22 = new java.util.StringTokenizer     // Catch:{ Exception -> 0x0323 }
            java.lang.String r24 = ","
            r0 = r22
            r1 = r21
            r2 = r24
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x0323 }
            java.lang.String r14 = r22.nextToken()     // Catch:{ Exception -> 0x0323 }
            java.lang.String r18 = r22.nextToken()     // Catch:{ Exception -> 0x0323 }
            double r24 = java.lang.Double.parseDouble(r14)     // Catch:{ Exception -> 0x0323 }
            r26 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r24 = r24 * r26
            java.lang.Double r10 = java.lang.Double.valueOf(r24)     // Catch:{ Exception -> 0x0323 }
            double r24 = java.lang.Double.parseDouble(r18)     // Catch:{ Exception -> 0x0323 }
            r26 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r24 = r24 * r26
            java.lang.Double r9 = java.lang.Double.valueOf(r24)     // Catch:{ Exception -> 0x0323 }
            int r15 = r9.intValue()     // Catch:{ Exception -> 0x0323 }
            int r19 = r10.intValue()     // Catch:{ Exception -> 0x0323 }
            org.json.JSONArray r24 = org.yaledining.app.HTTPManager.Database.Data     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            org.json.JSONArray r24 = r0.getJSONArray(r11)     // Catch:{ Exception -> 0x0323 }
            r25 = 2
            java.lang.String r12 = r24.getString(r25)     // Catch:{ Exception -> 0x0323 }
            java.lang.String r12 = r12.toLowerCase()     // Catch:{ Exception -> 0x0323 }
            java.lang.String r24 = " "
            java.lang.String r25 = ""
            r0 = r24
            r1 = r25
            java.lang.String r12 = r12.replace(r0, r1)     // Catch:{ Exception -> 0x0323 }
            java.lang.String r24 = "kbtcafé"
            r0 = r24
            boolean r24 = r12.equals(r0)     // Catch:{ Exception -> 0x0323 }
            if (r24 == 0) goto L_0x02f9
            java.lang.String r12 = "kbtcafe"
        L_0x026a:
            android.content.res.Resources r24 = r28.getResources()     // Catch:{ Exception -> 0x0323 }
            java.lang.String r25 = "drawable"
            java.lang.String r26 = r28.getPackageName()     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            r1 = r25
            r2 = r26
            int r4 = r0.getIdentifier(r12, r1, r2)     // Catch:{ Exception -> 0x0323 }
            org.yaledining.app.CustomOverlayItem r20 = new org.yaledining.app.CustomOverlayItem     // Catch:{ Exception -> 0x0323 }
            com.google.android.maps.GeoPoint r24 = new com.google.android.maps.GeoPoint     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            r1 = r19
            r0.<init>(r1, r15)     // Catch:{ Exception -> 0x0323 }
            org.json.JSONArray r25 = org.yaledining.app.HTTPManager.Database.Data     // Catch:{ Exception -> 0x0323 }
            r0 = r25
            org.json.JSONArray r25 = r0.getJSONArray(r11)     // Catch:{ Exception -> 0x0323 }
            r26 = 2
            java.lang.String r25 = r25.getString(r26)     // Catch:{ Exception -> 0x0323 }
            java.lang.String r26 = ""
            r0 = r20
            r1 = r24
            r2 = r25
            r3 = r26
            r0.<init>(r1, r2, r3, r4)     // Catch:{ Exception -> 0x0323 }
            org.json.JSONArray r24 = org.yaledining.app.HTTPManager.Database.Data     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            org.json.JSONArray r24 = r0.getJSONArray(r11)     // Catch:{ Exception -> 0x0323 }
            r25 = 0
            int r24 = r24.getInt(r25)     // Catch:{ Exception -> 0x0323 }
            r0 = r20
            r1 = r24
            r0.setLoc_id(r1)     // Catch:{ Exception -> 0x0323 }
            org.json.JSONArray r24 = org.yaledining.app.HTTPManager.Database.Data     // Catch:{ Exception -> 0x0323 }
            r0 = r24
            org.json.JSONArray r24 = r0.getJSONArray(r11)     // Catch:{ Exception -> 0x0323 }
            r25 = 3
            java.lang.String r24 = r24.getString(r25)     // Catch:{ Exception -> 0x0323 }
            r0 = r20
            r1 = r24
            r0.setType_of_restaurant(r1)     // Catch:{ Exception -> 0x0323 }
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0323 }
            java.lang.String r25 = java.lang.String.valueOf(r12)     // Catch:{ Exception -> 0x0323 }
            r24.<init>(r25)     // Catch:{ Exception -> 0x0323 }
            java.lang.String r25 = "_background"
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ Exception -> 0x0323 }
            java.lang.String r24 = r24.toString()     // Catch:{ Exception -> 0x0323 }
            r0 = r20
            r1 = r24
            r0.setImage(r1)     // Catch:{ Exception -> 0x0323 }
            r0 = r28
            org.yaledining.app.CustomItemizedOverlay<org.yaledining.app.CustomOverlayItem> r0 = r0.itemizedOverlay     // Catch:{ Exception -> 0x0323 }
            r24 = r0
            r0 = r24
            r1 = r20
            r0.addOverlay(r1)     // Catch:{ Exception -> 0x0323 }
            int r11 = r11 + 1
            goto L_0x0136
        L_0x02f9:
            java.lang.String r24 = "thainfamilycafé"
            r0 = r24
            boolean r24 = r12.equals(r0)     // Catch:{ Exception -> 0x0323 }
            if (r24 == 0) goto L_0x0307
            java.lang.String r12 = "thainfamilycafe"
            goto L_0x026a
        L_0x0307:
            java.lang.String r24 = "bectoncafé"
            r0 = r24
            boolean r24 = r12.equals(r0)     // Catch:{ Exception -> 0x0323 }
            if (r24 == 0) goto L_0x0315
            java.lang.String r12 = "bectoncafe"
            goto L_0x026a
        L_0x0315:
            java.lang.String r24 = "hccafé"
            r0 = r24
            boolean r24 = r12.equals(r0)     // Catch:{ Exception -> 0x0323 }
            if (r24 == 0) goto L_0x026a
            java.lang.String r12 = "hccafe"
            goto L_0x026a
        L_0x0323:
            r24 = move-exception
            goto L_0x01aa
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yaledining.app.Gps_mapActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    public boolean isRouteDisplayed() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Gps_mapActivity.super.onResume();
        Bottomtabs.type = "map";
        Bottomtabs.gps = true;
    }
}
