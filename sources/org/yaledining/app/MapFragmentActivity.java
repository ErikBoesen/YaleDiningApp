package org.yaledining.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.StringTokenizer;
import org.json.JSONException;

public class MapFragmentActivity extends FragmentActivity implements OnMarkerClickListener, OnInfoWindowClickListener {
    /* access modifiers changed from: private */
    public GoogleMap googleMap;
    /* access modifiers changed from: private */
    public Marker marker;
    MarkerOptions markerOptions;

    private class CustomInfoWindowAdapter implements InfoWindowAdapter {
        private View view;

        public CustomInfoWindowAdapter() {
            this.view = MapFragmentActivity.this.getLayoutInflater().inflate(C0233R.layout.custom_balloon_overlay, null);
        }

        public View getInfoContents(Marker marker) {
            if (MapFragmentActivity.this.marker != null && MapFragmentActivity.this.marker.isInfoWindowShown()) {
                MapFragmentActivity.this.marker.hideInfoWindow();
                MapFragmentActivity.this.marker.showInfoWindow();
            }
            return null;
        }

        public View getInfoWindow(Marker marker) {
            MapFragmentActivity.this.marker = marker;
            ImageView image = (ImageView) this.view.findViewById(C0233R.C0234id.balloon_item_image);
            int location_id = 0;
            String type_of_restaurant = null;
            String image_name = null;
            String marker_snippet = marker.getSnippet();
            Logger.vLog("CustomInfoWindowAdapter", "Restaurent Data : " + marker_snippet);
            StringTokenizer stringTokenizer = new StringTokenizer(marker_snippet, ",");
            while (stringTokenizer.hasMoreTokens()) {
                location_id = Integer.parseInt(stringTokenizer.nextToken().toString());
                type_of_restaurant = stringTokenizer.nextToken().toString();
                image_name = stringTokenizer.nextToken().toString();
            }
            Logger.vLog("CustomInfoWindowAdapter", "Location id : " + location_id + " Type of Restaurant : " + type_of_restaurant + " Image name : " + image_name);
            image.setImageResource(MapFragmentActivity.this.getResources().getIdentifier(image_name, ImageDownloader.SCHEME_DRAWABLE, MapFragmentActivity.this.getPackageName()));
            String title = marker.getTitle();
            TextView titleUi = (TextView) this.view.findViewById(C0233R.C0234id.balloon_item_title);
            if (title != null) {
                titleUi.setText(title);
            } else {
                titleUi.setText("");
            }
            return this.view;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Bottomtabs.gps = false;
        Bottomtabs.type = "";
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        Bottomtabs.gps = true;
        setContentView(C0233R.layout.fragment_map);
        this.googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(C0233R.C0234id.map)).getMap();
        ((TextView) findViewById(C0233R.C0234id.title)).setText(getIntent().getExtras().getString("name"));
        ((Button) findViewById(C0233R.C0234id.star)).setVisibility(8);
        ((Button) findViewById(C0233R.C0234id.refresh)).setVisibility(8);
        Display display = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        RelativeLayout layout = (RelativeLayout) findViewById(C0233R.C0234id.mainlayout);
        if (NewYalediningActivity.dHeight < 1850) {
            if (NewYalediningActivity.dHeight < 1000 || NewYalediningActivity.dHeight > 1300) {
                if (NewYalediningActivity.dHeight < 440 || NewYalediningActivity.dHeight > 500) {
                    if (NewYalediningActivity.dHeight < 300 || NewYalediningActivity.dHeight > 400) {
                    }
                }
            }
        }
        layout.setLayoutParams(new LayoutParams(-1, display.getHeight() - NewYalediningActivity.iSetMiddleLayoutHeight_Other));
        ((LinearLayout) findViewById(C0233R.C0234id.layout)).addView(new Bottomtabs(this));
        if (this.googleMap != null) {
            GoogleMap googleMap2 = this.googleMap;
            CustomInfoWindowAdapter customInfoWindowAdapter = new CustomInfoWindowAdapter();
            googleMap2.setInfoWindowAdapter(customInfoWindowAdapter);
            int i = 0;
            while (i < Database.Data.length()) {
                try {
                    Logger.vLog("GPS Map Activity ", Database.Data.getJSONArray(i));
                    StringTokenizer stringTokenizer = new StringTokenizer(Database.Data.getJSONArray(i).getString(5), ",");
                    String lat = stringTokenizer.nextToken();
                    String lon = stringTokenizer.nextToken();
                    Double getKoor_lat = Double.valueOf(Double.parseDouble(lat));
                    Double getKoor_long = Double.valueOf(Double.parseDouble(lon));
                    Logger.vLog("MapFragmentActivity", "Lat : " + getKoor_lat + " Long : " + getKoor_long);
                    String image = Database.Data.getJSONArray(i).getString(2).toLowerCase().replace(" ", "");
                    if (image.equals("kbtcafé")) {
                        image = "kbtcafe";
                    } else if (image.equals("thainfamilycafé")) {
                        image = "thainfamilycafe";
                    } else if (image.equals("bectoncafé")) {
                        image = "bectoncafe";
                    } else if (image.equals("hccafé")) {
                        image = "hccafe";
                    }
                    this.markerOptions = new MarkerOptions();
                    this.markerOptions.position(new LatLng(getKoor_lat.doubleValue(), getKoor_long.doubleValue()));
                    this.markerOptions.title(Database.Data.getJSONArray(i).getString(2));
                    this.markerOptions.icon(BitmapDescriptorFactory.fromResource(C0233R.drawable.pin));
                    int location_id = Database.Data.getJSONArray(i).getInt(0);
                    String type_of_restaurant = Database.Data.getJSONArray(i).getString(3);
                    StringBuilder builder = new StringBuilder();
                    builder.append(new StringBuilder(String.valueOf(location_id)).append(",").append(type_of_restaurant.toString()).append(",").append(image.toString()).toString());
                    String restaurent_data = builder.toString();
                    Logger.vLog("MapFragmentActivity", "Restaurent Data : " + restaurent_data);
                    this.markerOptions.snippet(restaurent_data);
                    this.googleMap.addMarker(this.markerOptions);
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
            GoogleMap googleMap3 = this.googleMap;
            C02271 r0 = new OnCameraChangeListener() {
                public void onCameraChange(CameraPosition cameraPosition) {
                    MapFragmentActivity.this.googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(new LatLng(41.304497d, -72.934177d), new LatLng(41.323588d, -72.92134d)), 20));
                    MapFragmentActivity.this.googleMap.setOnCameraChangeListener(null);
                }
            };
            googleMap3.setOnCameraChangeListener(r0);
            this.googleMap.setOnMarkerClickListener(this);
            this.googleMap.setOnInfoWindowClickListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Bottomtabs.type = "map";
        Bottomtabs.gps = true;
    }

    public boolean onMarkerClick(Marker marker2) {
        return false;
    }

    public void onInfoWindowClick(Marker marker2) {
        if (marker2.isInfoWindowShown()) {
            marker2.hideInfoWindow();
        }
        Intent intent = new Intent(this, RestaurantActivity.class);
        Logger.vLog("onInfoWindowClick", "Restaurent Data : " + marker2.getSnippet());
        String location_id = null;
        String type_of_restaurant = null;
        String image_name = null;
        String marker_snippet = marker2.getSnippet();
        Logger.vLog("CustomInfoWindowAdapter", "Restaurent Data : " + marker_snippet);
        StringTokenizer stringTokenizer = new StringTokenizer(marker_snippet, ",");
        while (stringTokenizer.hasMoreTokens()) {
            location_id = stringTokenizer.nextToken().toString();
            type_of_restaurant = stringTokenizer.nextToken().toString();
            image_name = stringTokenizer.nextToken().toString();
        }
        Logger.vLog("CustomInfoWindowAdapter", "Location id : " + location_id + " Type of Restaurant : " + type_of_restaurant + " Image name : " + image_name);
        String background_image = new StringBuilder(String.valueOf(image_name)).append("_background").toString();
        Logger.vLog("onInfoWindowClick", "background_image : " + background_image);
        intent.putExtra("type", marker2.getTitle());
        intent.putExtra("type_restaurant", type_of_restaurant);
        intent.putExtra("Location_id", location_id);
        intent.putExtra("image", background_image);
        intent.putExtra("parent", "2");
        intent.putExtra("gps", true);
        startActivity(intent);
    }
}
