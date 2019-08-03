package org.yaledining.app;

public class SearchMenuItemData {
    private String name = "";

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
}
