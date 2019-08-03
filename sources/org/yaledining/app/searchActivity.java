package org.yaledining.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import org.json.JSONArray;
import org.json.JSONException;

public class searchActivity extends BaseActivity implements Constant, OnClickListener, DialogiOnClick {
    Display display;

    /* renamed from: id */
    int f306id;
    int keygen;
    JSONArray row = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(C0233R.layout.main);
        this.display = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        RelativeLayout middle = (RelativeLayout) findViewById(C0233R.C0234id.middle);
        middle.setLayoutParams(new LayoutParams(-1, NewYalediningActivity.dHeight - NewYalediningActivity.iSetMiddleLayoutHeight_Other));
        LinearLayout linear = (LinearLayout) findViewById(C0233R.C0234id.middle1);
        linear.setVisibility(0);
        linear.setBackgroundColor(0);
        Bundle b = getIntent().getExtras();
        TextView title = (TextView) findViewById(C0233R.C0234id.title);
        this.f306id = Integer.parseInt(b.getString("location_id"));
        if (b.getString("key").length() > 15) {
            title.setText(new StringBuilder(String.valueOf(b.getString("key").substring(0, 13))).append("..").toString());
        } else {
            title.setText(b.getString("key"));
        }
        int ResId = getResources().getIdentifier(b.getString("image"), ImageDownloader.SCHEME_DRAWABLE, getPackageName());
        ImageView image = new ImageView(this);
        image.setImageResource(ResId);
        image.setLayoutParams(new LayoutParams(-1, -1));
        image.setAlpha(300);
        middle.addView(image);
        Button button = (Button) findViewById(C0233R.C0234id.star);
        button.setBackgroundResource(C0233R.drawable.star_button);
        button.setTag("star");
        button.setOnClickListener(this);
        ((Button) findViewById(C0233R.C0234id.refresh)).setVisibility(8);
        TextView tw = (TextView) findViewById(C0233R.C0234id.backbutton);
        tw.setVisibility(0);
        tw.setText(" Menu");
        tw.setOnClickListener(this);
        Bottomtabs.gps = b.getBoolean("gps");
        LinearLayout linear2 = (LinearLayout) findViewById(C0233R.C0234id.root);
        linear2.addView(new Bottomtabs(this));
        linear2.removeView(findViewById(C0233R.C0234id.toptab));
        addLabel("Contact Information");
        try {
            LinearLayout linear3 = (LinearLayout) findViewById(C0233R.C0234id.middle1);
            for (int i = 0; i < Database.Data.length(); i++) {
                if (Database.Data.getJSONArray(i).getString(2).equals(b.get("key"))) {
                    this.row = Database.Data.getJSONArray(i);
                    displayView(this.row, linear3);
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    private void displayView(JSONArray row2, LinearLayout linear) throws JSONException {
        LayoutInflater inflater = getLayoutInflater();
        RelativeLayout layout = (RelativeLayout) inflater.inflate(C0233R.layout.row, null);
        ((ImageView) layout.findViewById(C0233R.C0234id.image)).setImageResource(C0233R.drawable.map_image);
        ((TextView) layout.findViewById(C0233R.C0234id.name)).setText(row2.getString(7));
        layout.setOnClickListener(this);
        layout.setTag("map");
        linear.addView(layout);
        RelativeLayout layout2 = (RelativeLayout) inflater.inflate(C0233R.layout.row, null);
        ImageView image = (ImageView) layout2.findViewById(C0233R.C0234id.image);
        image.setLayoutParams(new RelativeLayout.LayoutParams(35, -2));
        image.setImageResource(C0233R.drawable.phone);
        ((TextView) layout2.findViewById(C0233R.C0234id.name)).setText(row2.getString(8));
        layout2.setOnClickListener(this);
        layout2.setTag("call");
        linear.addView(layout2);
        addLabel("Managers");
        for (int i = 9; i < 16; i += 2) {
            if (!row2.getString(i).equals("null")) {
                RelativeLayout layout3 = (RelativeLayout) inflater.inflate(C0233R.layout.row, null);
                ((ImageView) layout3.findViewById(C0233R.C0234id.image)).setImageResource(C0233R.drawable.messageicon);
                TextView text = (TextView) layout3.findViewById(C0233R.C0234id.name);
                layout3.setTag("email" + i);
                text.setText(row2.getString(i));
                layout3.setOnClickListener(this);
                linear.addView(layout3);
            }
        }
        addLabel("Give us your feedback.");
    }

    private void addLabel(String string) {
        LayoutInflater inflater = getLayoutInflater();
        TextView label = (TextView) inflater.inflate(C0233R.layout.label, null);
        label.setText(string);
        if (string.equals("Give us your feedback.")) {
            label.setBackgroundResource(C0233R.drawable.label);
            TextView label2 = (TextView) inflater.inflate(C0233R.layout.label3, null);
            label2.setText(string);
            label2.setBackgroundResource(C0233R.drawable.button);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, NewYalediningActivity.iSetHeightToFeedBackButton);
            params.addRule(12);
            params.addRule(13);
            if (NewYalediningActivity.dHeight >= 1750) {
                params.setMargins(25, 10, 25, 25);
                label2.setLayoutParams(params);
                label2.setTag("feedback");
                label2.setTextSize(16.0f);
            } else if (NewYalediningActivity.dHeight >= 1000 && NewYalediningActivity.dHeight <= 1300) {
                params.setMargins(25, 10, 25, 25);
                label2.setLayoutParams(params);
                label2.setTag("feedback");
                label2.setTextSize(16.0f);
            } else if (NewYalediningActivity.dHeight >= 440 && NewYalediningActivity.dHeight <= 500) {
                params.setMargins(25, 10, 25, 10);
                label2.setLayoutParams(params);
                label2.setTag("feedback");
                label2.setTextSize(16.0f);
            } else if (NewYalediningActivity.dHeight < 300 || NewYalediningActivity.dHeight > 400) {
                params.setMargins(25, 10, 25, 25);
                label2.setLayoutParams(params);
                label2.setTag("feedback");
                label2.setTextSize(16.0f);
            } else {
                params.setMargins(10, 5, 10, 5);
                label2.setLayoutParams(params);
                label2.setTag("feedback");
                label2.setTextSize(15.0f);
            }
            label2.setOnClickListener(this);
            ((RelativeLayout) findViewById(C0233R.C0234id.middle)).addView(label2);
            return;
        }
        label.setTextSize(22.0f);
        ((LinearLayout) findViewById(C0233R.C0234id.middle1)).addView(label);
    }

    private void SendMail(String email) {
        Intent emailIntent = new Intent("android.intent.action.SEND");
        emailIntent.setType("plain/text");
        emailIntent.putExtra("android.intent.extra.EMAIL", new String[]{email});
        emailIntent.putExtra("android.intent.extra.SUBJECT", "Email Subject");
        emailIntent.putExtra("android.intent.extra.TEXT", "Email Body");
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onClick(View v) {
        String str;
        String str2;
        if (v.getId() == C0233R.C0234id.backbutton) {
            finish();
        } else if (v.getTag().equals("map")) {
            try {
                if (CheckInternetUtil.isNetAvailable(this)) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("geo:" + this.row.getString(5) + "?q=" + this.row.getString(7))));
                    return;
                }
                new Dailog(this, "Alert", "Unable to Connect", 1, "Ok", null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v.getTag() == "call") {
            if (((TelephonyManager) getSystemService("phone")).getSimState() == 1) {
                new Dailog(this, "Alert", " No Network ", 1, "Ok", null);
                return;
            }
            Intent callIntent = new Intent("android.intent.action.CALL");
            try {
                callIntent.setData(Uri.parse("tel:" + this.row.getString(8)));
                callIntent.setFlags(268435456);
                startActivity(callIntent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (v.getTag().equals("email9")) {
            try {
                SendMail(this.row.getString(10));
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        } else if (v.getTag().equals("email11")) {
            try {
                ConnectivityManager conMgr = (ConnectivityManager) getSystemService("connectivity");
                WifiManager wifi = (WifiManager) getSystemService("wifi");
                if (conMgr.getNetworkInfo(0).getState() == State.CONNECTED || conMgr.getNetworkInfo(1).getState() == State.CONNECTING || wifi.isWifiEnabled()) {
                    SendMail(this.row.getString(12));
                    return;
                }
                new Dailog(this, "Alert", "Unable to Connect", 1, "Ok", null);
            } catch (JSONException e4) {
                e4.printStackTrace();
            }
        } else if (v.getTag().equals("emai13")) {
            try {
                SendMail(this.row.getString(14));
            } catch (JSONException e5) {
                e5.printStackTrace();
            }
        } else if (v.getTag().equals("email15")) {
            try {
                SendMail(this.row.getString(16));
            } catch (JSONException e6) {
                e6.printStackTrace();
            }
        } else if (v.getTag() == "feedback") {
            Intent intent = new Intent().setClass(this, FeedbackActivity.class);
            Bundle b = getIntent().getExtras();
            intent.putExtra("name of restaurant", (String) b.get("key"));
            intent.putExtra("image", b.getString("image"));
            intent.putExtra("gps", Bottomtabs.gps);
            if (intent != null) {
                startActivity(intent);
            }
        } else if (v.getTag() == "star") {
            try {
                str = getSharedPreferences("yaledining", 0).getString("Flag", null);
            } catch (Exception e7) {
                str = null;
            }
            if (str == null) {
                str2 = "Do you Wish to make this as your favorite ?";
            } else {
                str2 = "Do you Wish to replace your current Favourite with this dining location ?";
            }
            new Dailog(this, "Alert", str2, 2, "Yes", "No");
        }
    }

    public void onClick(DialogInterface dialog, int which) {
        if (-1 == which) {
            Editor editor = getSharedPreferences("yaledining", 0).edit();
            editor.putString("activity", "RestaurantActivity");
            editor.commit();
            Bundle b = getIntent().getExtras();
            editor.putString("key", b.getString("key"));
            editor.commit();
            editor.putString("image", b.getString("image"));
            editor.commit();
            editor.putString("Location_id", this.f306id);
            editor.commit();
            editor.putString("type_restaurant", b.getString("type_restaurant"));
            editor.commit();
            editor.putString("Flag", "1");
            editor.commit();
        }
    }
}
