package org.yaledining.app;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class CustomOverlayItem extends OverlayItem {
    protected String image;
    protected int loc_id;
    protected int mImageURL;
    protected String type_of_restaurant;

    public String getImage() {
        return this.image;
    }

    public void setImage(String image2) {
        this.image = image2;
    }

    public CustomOverlayItem(GeoPoint point, String title, String snippet, int imageURL) {
        super(point, title, snippet);
        this.mImageURL = imageURL;
    }

    public String getType_of_restaurant() {
        return this.type_of_restaurant;
    }

    public void setType_of_restaurant(String type_of_restaurant2) {
        this.type_of_restaurant = type_of_restaurant2;
    }

    public int getLoc_id() {
        return this.loc_id;
    }

    public void setLoc_id(int loc_id2) {
        this.loc_id = loc_id2;
    }

    public int getImageURL() {
        return this.mImageURL;
    }

    public void setImageURL(int imageURL) {
        this.mImageURL = imageURL;
    }
}
