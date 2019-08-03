package org.yaledining.app;

import android.content.res.Resources;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;

public class SearchData implements Constant {
    public static String TRAITS_BOTTOM_TEXT = "Produced in a facility that contains nuts, peanuts, and gluten";

    public ArrayList getData(String key, String activity, Resources res, String pck, boolean flag) throws Exception {
        if (activity.equals("MainActivity")) {
            return searchforMainData(key, res, pck);
        }
        if (activity.equals("RestaurantActivity")) {
            return searchforRestaurantData(key, res, pck, flag);
        }
        if (activity.equals("MenuItemActivity")) {
            return searchforMenuItemData(key, res, pck, flag);
        }
        return null;
    }

    private ArrayList searchforMenuItemData(String key, Resources res, String pck, boolean flag) throws Exception {
        ArrayList<SearchMenuItemData> data = new ArrayList<>();
        if (key.equals("Nutrition")) {
            if (DatabaseNutrition.Data.length() > 0) {
                for (int i = 2; i < DatabaseNutrition.Column.length(); i++) {
                    SearchMenuItemData sr1 = new SearchMenuItemData();
                    sr1.setName(capitalizeString(DatabaseNutrition.Column.getString(i)) + ": " + DatabaseNutrition.Data.getJSONArray(0).getString(i));
                    data.add(sr1);
                }
            }
        } else if (!key.equals("Traits")) {
            for (int i2 = 0; i2 < DatabaseIngredients.Data.length(); i2++) {
                SearchMenuItemData sr12 = new SearchMenuItemData();
                sr12.setName(DatabaseIngredients.Data.getJSONArray(i2).getString(1));
                data.add(sr12);
            }
        } else if (DatabaseTraits.Data.length() > 0) {
            for (int i3 = 2; i3 < DatabaseTraits.Column.length() - 1; i3++) {
                SearchMenuItemData sr13 = new SearchMenuItemData();
                if (DatabaseTraits.Data.getJSONArray(0).getInt(i3) == 1) {
                    sr13.setName(capitalizeString(DatabaseTraits.Column.getString(i3)));
                    data.add(sr13);
                }
            }
            for (int i4 = 16; i4 < DatabaseTraits.Column.length(); i4++) {
                SearchMenuItemData sr14 = new SearchMenuItemData();
                TRAITS_BOTTOM_TEXT = DatabaseTraits.Data.getJSONArray(0).getString(i4);
                sr14.setName(TRAITS_BOTTOM_TEXT);
                data.add(sr14);
            }
        }
        return data;
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<SearchResults> searchforMainData(String key, Resources res, String pck) throws JSONException {
        ArrayList<SearchResults> data = new ArrayList<>();
        for (int i = 0; i < Database.Data.length(); i++) {
            if (Database.Data.getJSONArray(i).getString(3).equals(key)) {
                SearchResults sr1 = new SearchResults();
                sr1.setName_restaurant(Database.Data.getJSONArray(i).getString(2));
                sr1.setLocation_id(Database.Data.getJSONArray(i).getInt(0));
                String name = Database.Data.getJSONArray(i).getString(2).toUpperCase();
                boolean flag = false;
                String imagename = Database.Data.getJSONArray(i).getString(2).toLowerCase().replace(" ", "");
                if (imagename.equals("kbtcafé")) {
                    imagename = "kbtcafe";
                } else if (imagename.equals("thainfamilycafé")) {
                    imagename = "thainfamilycafe";
                } else if (imagename.equals("bectoncafé")) {
                    imagename = "bectoncafe";
                } else if (imagename.equals("hccafé")) {
                    imagename = "hccafe";
                }
                sr1.setImage(res.getIdentifier(imagename, ImageDownloader.SCHEME_DRAWABLE, pck));
                if (key.equals("Retail")) {
                    sr1.setCapacity(11);
                    flag = true;
                    if (name.length() > 21) {
                        name = name.substring(0, 18);
                        sr1.setName(new StringBuilder(String.valueOf(name)).append("...").toString());
                    } else {
                        sr1.setName(name);
                    }
                } else if (Database.Data.getJSONArray(i).getInt(6) == 1) {
                    sr1.setCapacity(-1);
                } else if (key.equals("Retail")) {
                    sr1.setCapacity(11);
                } else {
                    sr1.setCapacity(res.getIdentifier("capacity" + Database.Data.getJSONArray(i).getInt(4), ImageDownloader.SCHEME_DRAWABLE, pck));
                }
                if (name.length() > 10 && !flag) {
                    sr1.setName(name.substring(0, 9) + "...");
                } else if (!flag) {
                    sr1.setName(name);
                }
                data.add(sr1);
            }
        }
        return data;
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<?> searchforRestaurantData(String key, Resources res, String pck, boolean flag) throws JSONException {
        ArrayList<ArrayList> data = new ArrayList<>();
        new ArrayList();
        ArrayList<SearchMenuData> breakfast = new ArrayList<>();
        ArrayList<SearchMenuData> lunch = new ArrayList<>();
        ArrayList<SearchMenuData> brunch = new ArrayList<>();
        ArrayList<SearchMenuData> dinner = new ArrayList<>();
        int count = DatabaseMenuList.Data.length();
        for (int i = 0; i < count; i++) {
            if (DatabaseMenuList.Data.getJSONArray(i).getString(3).equals("Continental Breakfast") && flag == Extract_date(DatabaseMenuList.Data.getJSONArray(i).getString(5))) {
                SearchMenuData sr1 = new SearchMenuData();
                sr1.setName(DatabaseMenuList.Data.getJSONArray(i).getString(10));
                sr1.setMenuid(DatabaseMenuList.Data.getJSONArray(i).getInt(9));
                sr1.setType(new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(12)).append(" - ").append(DatabaseMenuList.Data.getJSONArray(i).getString(13)).toString());
                Logger.vLog("ISMENU FLAG", new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(10)).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(15)).toString());
                boolean isMenu = false;
                String strIsMenu = DatabaseMenuList.Data.getJSONArray(i).getString(15);
                try {
                    switch (Integer.parseInt(strIsMenu)) {
                        case 0:
                            isMenu = false;
                            break;
                        case 1:
                            isMenu = true;
                            break;
                    }
                } catch (Exception e) {
                    isMenu = Boolean.parseBoolean(strIsMenu);
                }
                sr1.setMenuAvailable(isMenu);
                Logger.vLog("ISMENU", isMenu);
                if (isMenu) {
                    sr1.setImage(res.getIdentifier("arrow", ImageDownloader.SCHEME_DRAWABLE, pck));
                } else {
                    sr1.setImage(0);
                }
                breakfast.add(sr1);
            } else if (DatabaseMenuList.Data.getJSONArray(i).getString(3).equals("Brunch") && flag == Extract_date(DatabaseMenuList.Data.getJSONArray(i).getString(5))) {
                SearchMenuData sr12 = new SearchMenuData();
                sr12.setName(DatabaseMenuList.Data.getJSONArray(i).getString(10));
                sr12.setMenuid(DatabaseMenuList.Data.getJSONArray(i).getInt(9));
                sr12.setType(new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(12)).append(" - ").append(DatabaseMenuList.Data.getJSONArray(i).getString(13)).toString());
                Logger.vLog("ISMENU FLAG", new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(10)).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(15)).toString());
                boolean isMenu2 = false;
                String strIsMenu2 = DatabaseMenuList.Data.getJSONArray(i).getString(15);
                try {
                    switch (Integer.parseInt(strIsMenu2)) {
                        case 0:
                            isMenu2 = false;
                            break;
                        case 1:
                            isMenu2 = true;
                            break;
                    }
                } catch (Exception e2) {
                    isMenu2 = Boolean.parseBoolean(strIsMenu2);
                }
                sr12.setMenuAvailable(isMenu2);
                Logger.vLog("ISMENU", isMenu2);
                if (isMenu2) {
                    sr12.setImage(res.getIdentifier("arrow", ImageDownloader.SCHEME_DRAWABLE, pck));
                } else {
                    sr12.setImage(0);
                }
                brunch.add(sr12);
            } else if (DatabaseMenuList.Data.getJSONArray(i).getString(3).equals("Lunch") && flag == Extract_date(DatabaseMenuList.Data.getJSONArray(i).getString(5))) {
                SearchMenuData sr13 = new SearchMenuData();
                sr13.setName(DatabaseMenuList.Data.getJSONArray(i).getString(10));
                sr13.setMenuid(DatabaseMenuList.Data.getJSONArray(i).getInt(9));
                sr13.setType(new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(12)).append(" - ").append(DatabaseMenuList.Data.getJSONArray(i).getString(13)).toString());
                Logger.vLog("ISMENU FLAG", new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(10)).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(15)).toString());
                boolean isMenu3 = false;
                String strIsMenu3 = DatabaseMenuList.Data.getJSONArray(i).getString(15);
                try {
                    switch (Integer.parseInt(strIsMenu3)) {
                        case 0:
                            isMenu3 = false;
                            break;
                        case 1:
                            isMenu3 = true;
                            break;
                    }
                } catch (Exception e3) {
                    isMenu3 = Boolean.parseBoolean(strIsMenu3);
                }
                sr13.setMenuAvailable(isMenu3);
                Logger.vLog("ISMENU", isMenu3);
                if (isMenu3) {
                    sr13.setImage(res.getIdentifier("arrow", ImageDownloader.SCHEME_DRAWABLE, pck));
                } else {
                    sr13.setImage(0);
                }
                lunch.add(sr13);
            } else if (!DatabaseMenuList.Data.getJSONArray(i).getString(3).equals("Dinner") || flag != Extract_date(DatabaseMenuList.Data.getJSONArray(i).getString(5))) {
                if (flag == Extract_date(DatabaseMenuList.Data.getJSONArray(i).getString(5))) {
                    SearchMenuData sr14 = new SearchMenuData();
                    sr14.setName(DatabaseMenuList.Data.getJSONArray(i).getString(10));
                    sr14.setType(new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(12)).append(" - ").append(DatabaseMenuList.Data.getJSONArray(i).getString(13)).toString());
                    sr14.setMenuid(0);
                    Logger.vLog("ISMENU FLAG", new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(10)).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(15)).toString());
                    boolean isMenu4 = false;
                    String strIsMenu4 = DatabaseMenuList.Data.getJSONArray(i).getString(15);
                    try {
                        switch (Integer.parseInt(strIsMenu4)) {
                            case 0:
                                isMenu4 = false;
                                break;
                            case 1:
                                isMenu4 = true;
                                break;
                        }
                    } catch (Exception e4) {
                        isMenu4 = Boolean.parseBoolean(strIsMenu4);
                    }
                    sr14.setMenuAvailable(isMenu4);
                    Logger.vLog("ISMENU", isMenu4);
                    if (isMenu4) {
                        sr14.setImage(res.getIdentifier("arrow", ImageDownloader.SCHEME_DRAWABLE, pck));
                    } else {
                        sr14.setImage(0);
                    }
                    breakfast.add(sr14);
                }
            } else {
                SearchMenuData sr15 = new SearchMenuData();
                sr15.setName(DatabaseMenuList.Data.getJSONArray(i).getString(10));
                sr15.setMenuid(DatabaseMenuList.Data.getJSONArray(i).getInt(9));
                sr15.setType(new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(12)).append(" - ").append(DatabaseMenuList.Data.getJSONArray(i).getString(13)).toString());
                Logger.vLog("ISMENU FLAG", new StringBuilder(String.valueOf(DatabaseMenuList.Data.getJSONArray(i).getString(3))).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(10)).append(" ").append(DatabaseMenuList.Data.getJSONArray(i).getString(15)).toString());
                boolean isMenu5 = false;
                String strIsMenu5 = DatabaseMenuList.Data.getJSONArray(i).getString(15);
                try {
                    switch (Integer.parseInt(strIsMenu5)) {
                        case 0:
                            isMenu5 = false;
                            break;
                        case 1:
                            isMenu5 = true;
                            break;
                    }
                } catch (Exception e5) {
                    isMenu5 = Boolean.parseBoolean(strIsMenu5);
                }
                sr15.setMenuAvailable(isMenu5);
                Logger.vLog("ISMENU", isMenu5);
                if (isMenu5) {
                    sr15.setImage(res.getIdentifier("arrow", ImageDownloader.SCHEME_DRAWABLE, pck));
                } else {
                    sr15.setImage(0);
                }
                dinner.add(sr15);
            }
        }
        data.add(breakfast);
        data.add(brunch);
        data.add(lunch);
        data.add(dinner);
        return data;
    }

    private boolean Extract_date(String str) {
        if (Calendar.getInstance().get(5) == new Date(str).getDate()) {
            return true;
        }
        return false;
    }

    public String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
                found = false;
            }
        }
        return String.valueOf(chars);
    }
}
