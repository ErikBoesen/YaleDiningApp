package org.yaledining.app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.maps.GeoPoint;

public class MainActivity extends BaseActivity implements Constant, OnClickListener, OnItemClickListener, DialogiOnClick {
    static GeoPoint point;
    MyCustomBaseAdapter adapter = null;
    Button btn;
    ProgressBar button1;
    boolean check;
    String clicked = "Residential";
    RelativeLayout middle;
    ListView resedential;
    ListView retail;
    View view;

    private void getKaizenValue() {
        Display display = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        NewYalediningActivity.dHeight = display.getHeight();
        Logger.vLog("NewYalediningActivity : ", "Width : " + display.getWidth() + " Height : " + display.getHeight());
        View vBottomTabs = new Bottomtabs(this);
        vBottomTabs.measure(0, 0);
        Logger.vLog("MainActivity BottomBar : ", "Height : " + vBottomTabs.getMeasuredHeight());
        View vTopBar = findViewById(C0233R.C0234id.topbar);
        vTopBar.measure(0, 0);
        Logger.vLog("MainActivity Top Bar : ", "Height : " + vTopBar.getMeasuredHeight());
        View vTopTab = findViewById(C0233R.C0234id.toptab);
        vTopTab.measure(0, 0);
        Logger.vLog("MainActivity Top Tab : ", "Height : " + vTopTab.getMeasuredHeight());
        if (display.getHeight() >= 1750) {
            NewYalediningActivity.keygen = 470;
        } else if (display.getHeight() >= 1000 && display.getHeight() <= 1300) {
            NewYalediningActivity.keygen = 320;
        } else if (display.getHeight() >= 440 && display.getHeight() <= 500) {
            NewYalediningActivity.keygen = 170;
        } else if (display.getHeight() < 300 || display.getHeight() > 400) {
            NewYalediningActivity.keygen = 240;
        } else {
            NewYalediningActivity.keygen = 120;
        }
        Logger.vLog("NewYalediningActivity : ", "Keygen : " + NewYalediningActivity.keygen);
    }

    @SuppressLint({"ResourceAsColor"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(C0233R.layout.main);
        Bottomtabs.gps = false;
        Button button = (Button) findViewById(C0233R.C0234id.refresh);
        button.setBackgroundResource(C0233R.drawable.refresh_button);
        button.setMaxWidth(45);
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(C0233R.C0234id.star);
        button2.setBackgroundResource(C0233R.drawable.star_button);
        button2.setOnClickListener(this);
        ((TextView) findViewById(C0233R.C0234id.title)).setVisibility(8);
        ((ImageView) findViewById(C0233R.C0234id.title1)).setVisibility(0);
        Button button3 = (Button) findViewById(C0233R.C0234id.button1);
        button3.setText("Residential");
        button3.setOnClickListener(this);
        Button button4 = (Button) findViewById(C0233R.C0234id.button2);
        button4.setText("Retail");
        button4.setOnClickListener(this);
        int top = getWindow().findViewById(16908290).getTop();
        this.middle = (RelativeLayout) findViewById(C0233R.C0234id.middle);
        this.middle.setLayoutParams(new LayoutParams(-1, NewYalediningActivity.dHeight - NewYalediningActivity.iSetMiddleLayoutHeight));
        LinearLayout layout1 = (LinearLayout) findViewById(C0233R.C0234id.root);
        this.view = new Bottomtabs(this);
        layout1.addView(this.view);
        Bottomtabs.type = "home";
        try {
            LayoutParams params = new LayoutParams(-2, -2);
            this.resedential = new ListView(this);
            this.resedential.setLayoutParams(params);
            this.resedential.setBackgroundColor(17170445);
            this.resedential.setScrollingCacheEnabled(false);
            this.resedential.setSmoothScrollbarEnabled(true);
            this.resedential.setOnItemClickListener(this);
            this.retail = new ListView(this);
            this.retail.setLayoutParams(params);
            this.retail.setScrollingCacheEnabled(false);
            this.retail.setBackgroundResource(17170445);
            this.retail.setSmoothScrollbarEnabled(true);
            this.retail.setOnItemClickListener(this);
            String str = getSharedPreferences("yaledining", 0).getString("clicked", null);
            if (str != null) {
                this.clicked = str;
            }
            if (this.clicked.equals("Retail")) {
                this.retail.setAdapter(new MyCustomBaseAdapter(this, new SearchData().getData("Retail", "MainActivity", getResources(), getPackageName(), false)));
                this.middle.addView(this.retail);
                ((Button) findViewById(C0233R.C0234id.button2)).setBackgroundResource(C0233R.drawable.selected1);
                ((Button) findViewById(C0233R.C0234id.button1)).setBackgroundResource(C0233R.drawable.unselected);
            } else {
                this.clicked = "Residential";
                this.resedential.setAdapter(new MyCustomBaseAdapter(this, new SearchData().getData("Residential", "MainActivity", getResources(), getPackageName(), false)));
                this.middle.addView(this.resedential);
                ((Button) findViewById(C0233R.C0234id.button1)).setBackgroundResource(C0233R.drawable.selected);
                ((Button) findViewById(C0233R.C0234id.button2)).setBackgroundResource(C0233R.drawable.unselected1);
            }
        } catch (Exception e) {
        }
        System.gc();
    }

    public void onClick(View v) {
        String str;
        String str2;
        if (v.getId() == C0233R.C0234id.button1 && this.clicked != "Resedential") {
            ((Button) findViewById(C0233R.C0234id.button2)).setBackgroundResource(C0233R.drawable.unselected1);
            v.setBackgroundResource(C0233R.drawable.selected);
            this.clicked = "Residential";
            try {
                this.resedential.setAdapter(new MyCustomBaseAdapter(this, new SearchData().getData("Residential", "MainActivity", getResources(), getPackageName(), false)));
                this.middle.removeView(this.retail);
                this.middle.addView(this.resedential);
            } catch (Exception e) {
            }
        } else if (v.getId() == C0233R.C0234id.button2 && !this.clicked.equals("Retail")) {
            ((Button) findViewById(C0233R.C0234id.button1)).setBackgroundResource(C0233R.drawable.unselected);
            v.setBackgroundResource(C0233R.drawable.selected1);
            this.clicked = "Retail";
            try {
                this.retail.setAdapter(new MyCustomBaseAdapter(this, new SearchData().getData("Retail", "MainActivity", getResources(), getPackageName(), false)));
                this.middle.removeView(this.resedential);
                this.middle.addView(this.retail);
            } catch (Exception e2) {
            }
        } else if (v.getId() == C0233R.C0234id.refresh) {
            if (CheckInternetUtil.isNetAvailable(this)) {
                this.button1 = (ProgressBar) findViewById(C0233R.C0234id.progressbar);
                this.button1.setVisibility(0);
                this.btn = (Button) findViewById(C0233R.C0234id.refresh);
                this.btn.setVisibility(8);
                refresh();
                return;
            }
            try {
                new Dailog(this, "Alert", "Unable to Connect", 1, "Ok", null);
            } catch (Exception e3) {
            }
        } else if (v.getId() == C0233R.C0234id.star) {
            try {
                str = getSharedPreferences("yaledining", 0).getString("Flag", null);
            } catch (Exception e4) {
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

    public void onItemClick(AdapterView<?> adapterView, View view2, int position, long id) {
        if (view2 != null) {
            TextView name_restaurant = (TextView) view2.findViewById(C0233R.C0234id.name_restaurant);
            Intent intent = new Intent().setClass(this, RestaurantActivity.class);
            intent.putExtra("type", name_restaurant.getText());
            intent.putExtra("type_restaurant", this.clicked);
            intent.putExtra("Location_id", name_restaurant.getTag().toString());
            intent.putExtra("parent", "1");
            String imagename = ((String) name_restaurant.getText()).toLowerCase().replace(" ", "");
            if (imagename.equals("kbtcafé")) {
                imagename = "kbtcafe";
            } else if (imagename.equals("thainfamilycafé")) {
                imagename = "thainfamilycafe";
            } else if (imagename.equals("bectoncafé")) {
                imagename = "bectoncafe";
            } else if (imagename.equals("hccafé")) {
                imagename = "hccafe";
            }
            intent.putExtra("image", new StringBuilder(String.valueOf(imagename)).append("_background").toString());
            if (intent != null) {
                startActivity(intent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Bottomtabs.type = "home";
        Bottomtabs.gps = false;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
    }

    public void onClick(DialogInterface dialog, int which) {
        if (-1 == which) {
            Editor editor = getSharedPreferences("yaledining", 0).edit();
            editor.putString("activity", "MainActivity");
            editor.commit();
            editor.putString("clicked", this.clicked);
            editor.commit();
            editor.putString("Flag", "1");
            editor.commit();
        }
    }

    public void refresh() {
        ((Button) findViewById(C0233R.C0234id.star)).setClickable(false);
        ((Button) findViewById(C0233R.C0234id.button1)).setClickable(false);
        ((Button) findViewById(C0233R.C0234id.button2)).setClickable(false);
        this.middle.setClickable(false);
        final Handler mHandler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                MainActivity.this.check = new HTTPManager(null).request(MainActivity.this, "Names of Restaurants", 0);
                try {
                    MainActivity.this.adapter = new MyCustomBaseAdapter(MainActivity.this, new SearchData().getData("Residential", "MainActivity", MainActivity.this.getResources(), MainActivity.this.getPackageName(), false));
                } catch (Exception e1) {
                    mHandler.post(new Runnable() {
                        public void run() {
                            MainActivity.this.button1.setVisibility(8);
                            MainActivity.this.btn.setVisibility(0);
                        }
                    });
                    e1.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    public void run() {
                        try {
                            System.out.println("Handler");
                            MainActivity.this.resedential.setAdapter(MainActivity.this.adapter);
                            MainActivity.this.middle.removeAllViews();
                            MainActivity.this.middle.addView(MainActivity.this.resedential);
                            System.gc();
                            MainActivity.this.middle.setClickable(false);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.star)).setClickable(true);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button1)).setClickable(true);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button2)).setClickable(true);
                            MainActivity.this.clicked = "Residential";
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button1)).setBackgroundResource(C0233R.drawable.selected);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button2)).setBackgroundResource(C0233R.drawable.unselected1);
                            MainActivity.this.button1.setVisibility(8);
                            MainActivity.this.btn.setVisibility(0);
                            System.gc();
                        } catch (Exception e) {
                            MainActivity.this.middle.setClickable(false);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.star)).setClickable(true);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button1)).setClickable(true);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button2)).setClickable(true);
                            MainActivity.this.clicked = "Residential";
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button1)).setBackgroundResource(C0233R.drawable.selected);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button2)).setBackgroundResource(C0233R.drawable.unselected1);
                            MainActivity.this.button1.setVisibility(8);
                            MainActivity.this.btn.setVisibility(0);
                            System.gc();
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            MainActivity.this.middle.setClickable(false);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.star)).setClickable(true);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button1)).setClickable(true);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button2)).setClickable(true);
                            MainActivity.this.clicked = "Residential";
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button1)).setBackgroundResource(C0233R.drawable.selected);
                            ((Button) MainActivity.this.findViewById(C0233R.C0234id.button2)).setBackgroundResource(C0233R.drawable.unselected1);
                            MainActivity.this.button1.setVisibility(8);
                            MainActivity.this.btn.setVisibility(0);
                            System.gc();
                            throw th2;
                        }
                    }
                });
            }
        }).start();
    }
}
