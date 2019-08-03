package org.yaledining.app;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.io.Serializable;

public class Bottomtabs extends LinearLayout implements Constant, OnKeyListener, Serializable {
    static boolean gps = false;
    static String type = "home";
    Context context;
    Context ctx;
    Intent intent;
    private OnClickListener screenshotOnClickListener = new OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == C0233R.C0234id.home) {
                Bottomtabs.gps = false;
                if (Bottomtabs.type != "home") {
                    Bottomtabs.type = "home";
                    Bottomtabs.gps = false;
                    Bottomtabs.this.intent = new Intent(Bottomtabs.this.context, MainActivity.class);
                    Bottomtabs.this.intent.addFlags(67108864);
                    Bottomtabs.this.context.startActivity(Bottomtabs.this.intent);
                }
            } else if (v.getId() == C0233R.C0234id.map) {
                Bottomtabs.gps = true;
                if (Bottomtabs.type != "map") {
                    Bottomtabs.type = "map";
                    Bottomtabs.this.intent = new Intent(Bottomtabs.this.context, MapFragmentActivity.class);
                    Bottomtabs.this.intent.putExtra("name", "Dining Locations");
                    Bottomtabs.this.intent.addFlags(67108864);
                    Bottomtabs.this.context.startActivity(Bottomtabs.this.intent);
                }
            } else if (v.getId() == C0233R.C0234id.site1) {
                displayAlertBox(Constant.TAB_URL1);
            } else if (v.getId() == C0233R.C0234id.site2) {
                displayAlertBox(Constant.TAB_URL2);
            }
        }

        private void displayAlertBox(final String url) {
            Builder builder = new Builder(Bottomtabs.this.context);
            builder.setTitle("Alert");
            builder.setMessage("This will close the App and open an external webpage.\nDo you wish to continue ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    Bottomtabs.this.intent = new Intent(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    Bottomtabs.this.intent.addFlags(67108864);
                    Bottomtabs.this.context.startActivity(Bottomtabs.this.intent);
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            builder.create().show();
        }
    };

    public Bottomtabs(Context context2) {
        super(context2);
        this.context = context2;
        ((LayoutInflater) context2.getSystemService("layout_inflater")).inflate(C0233R.layout.bottomtabs, this, true);
        ((RelativeLayout) findViewById(C0233R.C0234id.home)).setOnClickListener(this.screenshotOnClickListener);
        ((RelativeLayout) findViewById(C0233R.C0234id.map)).setOnClickListener(this.screenshotOnClickListener);
        ((RelativeLayout) findViewById(C0233R.C0234id.site1)).setOnClickListener(this.screenshotOnClickListener);
        ((RelativeLayout) findViewById(C0233R.C0234id.site2)).setOnClickListener(this.screenshotOnClickListener);
        if (gps) {
            ((ImageView) findViewById(C0233R.C0234id.site2_image)).setAlpha(70);
            ((ImageView) findViewById(C0233R.C0234id.home_image)).setImageResource(C0233R.drawable.home1);
            ((ImageView) findViewById(C0233R.C0234id.map_image)).setImageResource(C0233R.drawable.map_image_01);
            ImageView image = (ImageView) findViewById(C0233R.C0234id.map_glass);
            image.setVisibility(0);
            image.setAlpha(100);
            ((ImageView) findViewById(C0233R.C0234id.home_glass)).setVisibility(4);
            return;
        }
        ((ImageView) findViewById(C0233R.C0234id.site2_image)).setAlpha(70);
        ((ImageView) findViewById(C0233R.C0234id.home_image)).setImageResource(C0233R.drawable.home_01);
        ((ImageView) findViewById(C0233R.C0234id.map_image)).setImageResource(C0233R.drawable.map_image);
        ((ImageView) findViewById(C0233R.C0234id.map_glass)).setVisibility(4);
        ImageView image2 = (ImageView) findViewById(C0233R.C0234id.home_glass);
        image2.setVisibility(0);
        image2.setAlpha(100);
    }

    public boolean onKey(DialogInterface arg0, int arg1, KeyEvent arg2) {
        if (arg1 == 4) {
            ((Activity) getContext()).finish();
        }
        return false;
    }
}
