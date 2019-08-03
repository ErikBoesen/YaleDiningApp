package org.yaledining.app;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class HTTPManager extends Logger implements Constant {
    Context cxt;

    static class Database {
        static JSONArray Data;
        static boolean check;

        Database() {
        }
    }

    static class DatabaseIngredients {
        static JSONArray Data;

        DatabaseIngredients() {
        }
    }

    static class DatabaseMenuList {
        static JSONArray Data;

        DatabaseMenuList() {
        }
    }

    static class DatabaseNutrition {
        static JSONArray Column;
        static JSONArray Data;

        DatabaseNutrition() {
        }
    }

    static class DatabaseTraits {
        static JSONArray Column;
        static JSONArray Data;

        DatabaseTraits() {
        }
    }

    HTTPManager(Context ctx) {
        this.cxt = ctx;
    }

    public boolean request(Context cxt2, String Url, int key) {
        this.cxt = cxt2;
        String str = "";
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
        HttpClient client = new DefaultHttpClient(httpParams);
        HttpGet request = null;
        if (Url.equals("Names of Restaurants")) {
            request = new HttpGet(Constant.URL);
        } else {
            if (Url.startsWith("MenuList")) {
                request = new HttpGet("http://www.yaledining.org/fasttrack/menus.cfm?location=" + key + "&version=3");
            } else {
                if (Url.startsWith("MenuInfo")) {
                    String url = "http://www.yaledining.org/fasttrack/";
                    if (Url.endsWith("Nutrition")) {
                        url = new StringBuilder(String.valueOf(url)).append("menuitem-nutrition.cfm?MENUITEMID=").append(key).toString();
                    } else {
                        if (Url.endsWith("Traits")) {
                            url = new StringBuilder(String.valueOf(url)).append("menuitem-codes.cfm?MENUITEMID=").append(key).toString();
                        } else {
                            if (Url.endsWith("Ingredients")) {
                                url = new StringBuilder(String.valueOf(url)).append("menuitem-ingredients.cfm?MENUITEMID=").append(key).toString();
                            }
                        }
                    }
                    request = new HttpGet(new StringBuilder(String.valueOf(url)).append("&version=3").toString());
                }
            }
        }
        try {
            InputStream in = client.execute(request).getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str2 = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    in.close();
                    return parse(str2.toString(), Url);
                }
                str2.append(new StringBuilder(String.valueOf(line)).append("\n").toString());
            }
        } catch (HttpException e) {
            diplayDialogbox();
            return false;
        } catch (Exception e2) {
            diplayDialogbox();
            return false;
        }
    }

    public boolean parse(String res, String Url) throws Exception {
        boolean flag = false;
        JSONObject jObject = new JSONObject(res);
        if (Url.equals("Names of Restaurants")) {
            Database.Data = jObject.getJSONArray("DATA");
            flag = true;
        } else if (Url.startsWith("MenuList")) {
            DatabaseMenuList.Data = null;
            JSONArray temp = jObject.getJSONArray("DATA");
            DatabaseMenuList.Data = new JSONArray();
            if (temp != null) {
                for (int i = 0; i < temp.length(); i++) {
                    DatabaseMenuList.Data.put(temp.getJSONArray(i));
                }
            }
            return true;
        } else if (Url.endsWith("Nutrition")) {
            DatabaseNutrition.Data = null;
            DatabaseNutrition.Column = null;
            JSONObject jObject2 = new JSONObject(res);
            DatabaseNutrition.Data = jObject2.getJSONArray("DATA");
            DatabaseNutrition.Column = jObject2.getJSONArray("COLUMNS");
            flag = true;
        } else if (Url.endsWith("Traits")) {
            DatabaseTraits.Data = null;
            DatabaseTraits.Column = null;
            JSONObject jObject3 = new JSONObject(res);
            DatabaseTraits.Data = jObject3.getJSONArray("DATA");
            DatabaseTraits.Column = jObject3.getJSONArray("COLUMNS");
            flag = true;
        } else if (Url.endsWith("Ingredients")) {
            DatabaseIngredients.Data = null;
            DatabaseIngredients.Data = new JSONObject(res).getJSONArray("DATA");
            flag = true;
        }
        return flag;
    }

    private void diplayDialogbox() {
        Builder builder = new Builder(this.cxt);
        builder.setMessage("Unable to Connect");
        builder.setCancelable(false);
        builder.setPositiveButton("Retry", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setPositiveButton("Close", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ((Activity) HTTPManager.this.cxt).finish();
            }
        });
        builder.create().show();
    }
}
