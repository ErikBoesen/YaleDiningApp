package org.yaledining.app;

public class SearchResults {
    private int capacity;
    private int image;
    private int location_id = 0;
    private String name = "";
    private String name_restaurant = "";

    public void setName(String name2) {
        if (name2 == null) {
            String name3 = "";
        } else {
            this.name = name2;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setImage(int image2) {
        this.image = image2;
    }

    public int getImage() {
        return this.image;
    }

    public void setName_restaurant(String name_restaurant2) {
        if (name_restaurant2 == null) {
            String name_restaurant3 = "";
        } else {
            this.name_restaurant = name_restaurant2;
        }
    }

    public String getName_restaurant() {
        return this.name_restaurant;
    }

    public void setCapacity(int capacity2) {
        this.capacity = capacity2;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setLocation_id(int location_id2) {
        this.location_id = location_id2;
    }

    public int getLocation_id() {
        return this.location_id;
    }
}
