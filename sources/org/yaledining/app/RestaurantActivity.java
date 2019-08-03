package org.yaledining.app;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.ArrayList;

public class RestaurantActivity extends BaseActivity implements Constant, OnClickListener, DialogiOnClick {
    ArrayList<SearchMenuData> breakfast = new ArrayList<>();
    ArrayList<SearchMenuData> brunch = new ArrayList<>();
    boolean check = false;
    String clicked = "today";
    ArrayList<SearchMenuData> dinner = new ArrayList<>();
    Display display;
    boolean flag = false;
    String location_id;
    ArrayList<SearchMenuData> lunch = new ArrayList<>();
    /* access modifiers changed from: private */
    public Handler mHandler;
    ProgressBar progress;
    ArrayList<ArrayList> search = null;
    Thread thread;
    String ttl;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(C0233R.layout.main);
        this.progress = (ProgressBar) findViewById(C0233R.C0234id.mainprogressbar);
        int mPBSize = NewYalediningActivity.iProgressbarHeightAndWidth;
        LayoutParams layoutParams = new LayoutParams(mPBSize, mPBSize);
        layoutParams.addRule(13);
        this.progress.setLayoutParams(layoutParams);
        this.progress.setVisibility(0);
        Bundle b = getIntent().getExtras();
        this.display = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        RelativeLayout middle = (RelativeLayout) findViewById(C0233R.C0234id.middle);
        middle.setLayoutParams(new LinearLayout.LayoutParams(-1, NewYalediningActivity.dHeight - NewYalediningActivity.iSetMiddleLayoutHeight));
        TextView title = (TextView) findViewById(C0233R.C0234id.title);
        this.ttl = b.getString("type");
        this.location_id = b.getString("Location_id");
        if (this.ttl.length() > 15) {
            title.setText(new StringBuilder(String.valueOf(this.ttl.substring(0, 13))).append("..").toString());
        } else {
            title.setText(this.ttl);
        }
        int ResId = getResources().getIdentifier(b.getString("image"), ImageDownloader.SCHEME_DRAWABLE, getPackageName());
        ImageView image = new ImageView(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        image.setLayoutParams(layoutParams2);
        image.setImageResource(ResId);
        image.setScaleType(ScaleType.CENTER_INSIDE);
        image.setAlpha(300);
        middle.addView(image);
        Button button = (Button) findViewById(C0233R.C0234id.star);
        button.setBackgroundResource(C0233R.drawable.info_button);
        button.setOnClickListener(this);
        ((Button) findViewById(C0233R.C0234id.refresh)).setVisibility(8);
        TextView tw = (TextView) findViewById(C0233R.C0234id.backbutton);
        tw.setVisibility(0);
        tw.setMaxWidth(45);
        tw.setMinWidth(45);
        if (b.getBoolean("gps")) {
            tw.setText("Map");
            Bottomtabs.type = "";
            Bottomtabs.gps = true;
        } else {
            tw.setText(" List");
            Bottomtabs.type = "";
            Bottomtabs.gps = false;
        }
        tw.setWidth(45);
        tw.setOnClickListener(this);
        Button button2 = (Button) findViewById(C0233R.C0234id.button1);
        button2.setText("Today");
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(C0233R.C0234id.button2);
        button3.setText("Tomorrow");
        button3.setOnClickListener(this);
        ((LinearLayout) findViewById(C0233R.C0234id.root)).addView(new Bottomtabs(this));
        try {
            if (CheckInternetUtil.isNetAvailable(this)) {
                getdata();
            } else {
                new Dailog(getApplicationContext(), "Alert", "Unable to Connect", 1, "Ok", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        if (v.getId() == C0233R.C0234id.button1 && this.clicked != "today") {
            ((Button) findViewById(C0233R.C0234id.button2)).setBackgroundResource(C0233R.drawable.unselected1);
            v.setBackgroundResource(C0233R.drawable.selected);
            this.clicked = "today";
            ((LinearLayout) findViewById(C0233R.C0234id.list)).removeAllViews();
            try {
                this.search = new SearchData().getData(this.clicked, "RestaurantActivity", getResources(), getPackageName(), true);
                this.breakfast = (ArrayList) this.search.get(0);
                this.brunch = (ArrayList) this.search.get(1);
                this.lunch = (ArrayList) this.search.get(2);
                this.dinner = (ArrayList) this.search.get(3);
                create_view();
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v.getId() == C0233R.C0234id.button2 && this.clicked != "tomorrow" && this.flag) {
            ((Button) findViewById(C0233R.C0234id.button1)).setBackgroundResource(C0233R.drawable.unselected);
            v.setBackgroundResource(C0233R.drawable.selected1);
            this.clicked = "tomorrow";
            ((LinearLayout) findViewById(C0233R.C0234id.list)).removeAllViews();
            try {
                this.search = new SearchData().getData(this.clicked, "RestaurantActivity", getResources(), getPackageName(), false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.breakfast = (ArrayList) this.search.get(0);
            this.brunch = (ArrayList) this.search.get(1);
            this.lunch = (ArrayList) this.search.get(2);
            this.dinner = (ArrayList) this.search.get(3);
            create_view();
            System.gc();
        } else if (v.getId() == C0233R.C0234id.backbutton) {
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            if (getIntent().getExtras().getString("parent") == null) {
                intent.addFlags(67108864);
                startActivity(intent);
            }
        } else if (v.getId() == C0233R.C0234id.star) {
            Bundle b = getIntent().getExtras();
            Intent intent2 = new Intent().setClass(this, searchActivity.class);
            intent2.putExtra("type_restaurant", b.getString("type_restaurant"));
            intent2.putExtra("key", this.ttl);
            intent2.putExtra("location_id", this.location_id);
            intent2.putExtra("image", b.getString("image"));
            intent2.putExtra("gps", Bottomtabs.gps);
            startActivity(intent2);
        } else if (v.getId() == C0233R.C0234id.list) {
            Bundle b2 = getIntent().getExtras();
            Intent intent3 = new Intent().setClass(this, MenuItemActivity.class);
            intent3.putExtra("menuitem_id", v.findViewById(C0233R.C0234id.main).getTag().toString());
            intent3.putExtra("type", v.getTag().toString());
            intent3.putExtra("image", b2.getString("image"));
            intent3.putExtra("type_restaurant", b2.getString("type_restaurant"));
            intent3.putExtra("gps", Bottomtabs.gps);
            if (intent3 != null) {
                startActivity(intent3);
            }
        }
    }

    private void getdata() throws Exception {
        this.mHandler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                Bundle extras = RestaurantActivity.this.getIntent().getExtras();
                try {
                    boolean request = new HTTPManager(null).request(RestaurantActivity.this.getBaseContext(), "MenuList", Integer.parseInt(RestaurantActivity.this.location_id));
                    RestaurantActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            Bundle b = RestaurantActivity.this.getIntent().getExtras();
                            try {
                                RestaurantActivity.this.search = new SearchData().getData(b.getString("type"), "RestaurantActivity", RestaurantActivity.this.getResources(), RestaurantActivity.this.getPackageName(), true);
                                Logger.vLog("RestaurantActivity", "Serach Data : " + RestaurantActivity.this.search.size());
                                RestaurantActivity.this.breakfast = (ArrayList) RestaurantActivity.this.search.get(0);
                                RestaurantActivity.this.brunch = (ArrayList) RestaurantActivity.this.search.get(1);
                                RestaurantActivity.this.lunch = (ArrayList) RestaurantActivity.this.search.get(2);
                                RestaurantActivity.this.dinner = (ArrayList) RestaurantActivity.this.search.get(3);
                                RestaurantActivity.this.create_view();
                                RestaurantActivity.this.flag = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            RestaurantActivity.this.progress.setVisibility(8);
                            System.gc();
                        }
                    });
                } catch (Exception e) {
                    RestaurantActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            try {
                                RestaurantActivity.this.diplayDialogbox();
                            } catch (Exception e) {
                            }
                        }
                    });
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void create_view() {
        try {
            ScrollView scrollView = (ScrollView) findViewById(C0233R.C0234id.scroll);
            scrollView.smoothScrollBy(0, 100);
            scrollView.setSmoothScrollingEnabled(true);
            LinearLayout linearLayout = (LinearLayout) findViewById(C0233R.C0234id.list);
            scrollView.setVisibility(0);
            linearLayout.setVisibility(0);
            ((RelativeLayout) findViewById(C0233R.C0234id.middle)).setGravity(3);
            if (this.breakfast.size() > 0) {
                linearLayout.addView(addTitle(((SearchMenuData) this.breakfast.get(0)).getType()));
                linearLayout.addView(addLine());
                for (int i = 0; i < this.breakfast.size(); i++) {
                    linearLayout.addView(addLabel(((SearchMenuData) this.breakfast.get(i)).getName(), ((SearchMenuData) this.breakfast.get(i)).getMenuid(), ((SearchMenuData) this.breakfast.get(i)).getIsMenuAvailable()));
                    linearLayout.addView(addLine());
                }
            }
            if (this.brunch.size() > 0) {
                linearLayout.addView(addTitle(((SearchMenuData) this.brunch.get(0)).getType()));
                linearLayout.addView(addLine());
                for (int i2 = 0; i2 < this.brunch.size(); i2++) {
                    linearLayout.addView(addLabel(((SearchMenuData) this.brunch.get(i2)).getName(), ((SearchMenuData) this.brunch.get(i2)).getMenuid(), ((SearchMenuData) this.brunch.get(i2)).getIsMenuAvailable()));
                    linearLayout.addView(addLine());
                }
            }
            if (this.lunch.size() > 0) {
                linearLayout.addView(addTitle(((SearchMenuData) this.lunch.get(0)).getType()));
                linearLayout.addView(addLine());
                for (int i3 = 0; i3 < this.lunch.size(); i3++) {
                    linearLayout.addView(addLabel(((SearchMenuData) this.lunch.get(i3)).getName(), ((SearchMenuData) this.lunch.get(i3)).getMenuid(), ((SearchMenuData) this.lunch.get(i3)).getIsMenuAvailable()));
                    linearLayout.addView(addLine());
                }
            }
            if (this.dinner.size() > 0) {
                linearLayout.addView(addTitle(((SearchMenuData) this.dinner.get(0)).getType()));
                linearLayout.addView(addLine());
                for (int i4 = 0; i4 < this.dinner.size(); i4++) {
                    linearLayout.addView(addLabel(((SearchMenuData) this.dinner.get(i4)).getName(), ((SearchMenuData) this.dinner.get(i4)).getMenuid(), ((SearchMenuData) this.dinner.get(i4)).getIsMenuAvailable()));
                    linearLayout.addView(addLine());
                }
            }
        } catch (Exception e) {
        }
    }

    private View addLabel(String string, int id, boolean isMenuAvailable) {
        View view = getLayoutInflater().inflate(C0233R.layout.label2, null);
        TextView label = (TextView) view.findViewById(C0233R.C0234id.main);
        ImageView imgArrow = (ImageView) view.findViewById(C0233R.C0234id.arrow);
        label.setGravity(3);
        label.setTag(id);
        label.setPadding(0, 12, 0, 12);
        label.setText("  " + string);
        view.setTag(string);
        Bundle extras = getIntent().getExtras();
        if (!isMenuAvailable) {
            imgArrow.setImageDrawable(null);
        } else {
            view.setOnClickListener(this);
        }
        return view;
    }

    private TextView addTitle(String string) {
        TextView label = (TextView) getLayoutInflater().inflate(C0233R.layout.label1, null);
        label.setPadding(5, 5, 5, 5);
        label.setText(string);
        return label;
    }

    private ImageView addLine() {
        ImageView label = new ImageView(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(-1, 1);
        label.setMaxHeight(1);
        label.setMaxWidth(-1);
        label.setBackgroundColor(0);
        label.setPadding(0, 0, 0, 0);
        label.setLayoutParams(params1);
        label.setBackgroundResource(C0233R.drawable.h_line);
        return label;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onClick(DialogInterface dialog, int which) {
        finish();
    }

    /* access modifiers changed from: private */
    public void diplayDialogbox() {
        Builder builder = new Builder(getApplicationContext());
        builder.setMessage("Unable to Connect");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                RestaurantActivity.this.finish();
            }
        });
        builder.create().show();
    }
}
