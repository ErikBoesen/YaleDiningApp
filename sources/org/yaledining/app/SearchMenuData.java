package org.yaledining.app;

public class SearchMenuData {
    private int image = 0;
    private boolean isMenuAvailable = false;
    private int menuid = 0;
    private String name = "";
    private String type = "";

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

    public int getMenuid() {
        return this.menuid;
    }

    public void setMenuid(int menuid2) {
        this.menuid = menuid2;
    }

    public void setType(String type2) {
        this.type = type2;
    }

    public String getType() {
        return this.type;
    }

    public boolean getIsMenuAvailable() {
        return this.isMenuAvailable;
    }

    public void setMenuAvailable(boolean isMenuAvailable2) {
        this.isMenuAvailable = isMenuAvailable2;
    }
}
