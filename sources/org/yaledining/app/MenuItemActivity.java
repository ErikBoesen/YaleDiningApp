package org.yaledining.app;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import org.json.JSONException;

public class MenuItemActivity extends BaseActivity implements Constant, OnClickListener, DialogiOnClick {
    TextView bottom_text;
    String clicked = "Nutrition";
    ListView ingradient;
    boolean ingredient_flag = false;
    Handler mHandler;
    int menu_id;
    ListView nutrition;
    String prev_clicked = "Nutrition";
    ArrayList<SearchMenuItemData> search = null;
    boolean trait_flag = false;
    ListView traits;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(C0233R.layout.main);
        ((ProgressBar) findViewById(C0233R.C0234id.mainprogressbar)).setVisibility(0);
        ProgressBar progress = (ProgressBar) findViewById(C0233R.C0234id.mainprogressbar);
        int mPBSize = NewYalediningActivity.iProgressbarHeightAndWidth;
        LayoutParams layoutParams = new LayoutParams(mPBSize, mPBSize);
        layoutParams.addRule(13);
        progress.setLayoutParams(layoutParams);
        Bundle b = getIntent().getExtras();
        this.menu_id = Integer.parseInt(b.getString("menuitem_id"));
        try {
            if (CheckInternetUtil.isNetAvailable(this)) {
                getdata();
            } else {
                new Dailog(getApplicationContext(), "Alert", "Unable to Connect", 1, "Ok", null);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        LinearLayout main = (LinearLayout) findViewById(C0233R.C0234id.root);
        main.removeView(findViewById(C0233R.C0234id.toptab));
        RelativeLayout middle = (RelativeLayout) findViewById(C0233R.C0234id.middle);
        main.removeView(findViewById(C0233R.C0234id.middle));
        main.setClickable(false);
        main.addView((LinearLayout) getLayoutInflater().inflate(C0233R.layout.menuinfotabs, null));
        Button button = (Button) findViewById(C0233R.C0234id.button1);
        button.setText("Nutrition");
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(C0233R.C0234id.button2);
        button2.setText("Traits");
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(C0233R.C0234id.button3);
        button3.setText("Ingredients");
        button3.setOnClickListener(this);
        main.addView(middle);
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        ((RelativeLayout) findViewById(C0233R.C0234id.middle)).setLayoutParams(new LinearLayout.LayoutParams(-1, NewYalediningActivity.dHeight - NewYalediningActivity.iSetMiddleLayoutHeight));
        TextView title = (TextView) findViewById(C0233R.C0234id.title);
        title.setTextSize(18.0f);
        title.setText("Menu Item Information");
        new ImageView(this);
        this.menu_id = Integer.parseInt(b.getString("menuitem_id"));
        ((Button) findViewById(C0233R.C0234id.star)).setVisibility(8);
        ((Button) findViewById(C0233R.C0234id.refresh)).setVisibility(8);
        TextView tw = (TextView) findViewById(C0233R.C0234id.backbutton);
        tw.setVisibility(0);
        tw.setText("Menu");
        tw.setOnClickListener(this);
        Bottomtabs.gps = b.getBoolean("gps");
        main.addView(new Bottomtabs(this));
        ((ImageView) findViewById(C0233R.C0234id.site2_image)).setAlpha(90);
        try {
            this.nutrition = CreateList();
            this.traits = CreateList();
            this.ingradient = CreateList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finAct();
    }

    public void finAct() {
        System.gc();
        finish();
    }

    /* access modifiers changed from: private */
    public void addLabel(String string) {
        TextView label = (TextView) getLayoutInflater().inflate(C0233R.layout.label, null);
        label.setText(string);
        label.setTextSize(20.0f);
        ((LinearLayout) findViewById(C0233R.C0234id.middle1)).addView(label);
    }

    private ListView CreateList() {
        new ListView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -1);
        ListView list = new ListView(this);
        list.setLayoutParams(params);
        list.setDividerHeight(1);
        list.setCacheColorHint(0);
        list.setHeaderDividersEnabled(true);
        list.setBackgroundColor(0);
        list.setScrollingCacheEnabled(false);
        list.setSmoothScrollbarEnabled(true);
        list.setFocusable(false);
        list.setFocusableInTouchMode(false);
        list.setSelector(17170445);
        return list;
    }

    private void getdata() throws JSONException {
        this.mHandler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                try {
                    boolean request = new HTTPManager(null).request(MenuItemActivity.this.getBaseContext(), "MenuInfo Nutrition", MenuItemActivity.this.menu_id);
                    boolean check = new HTTPManager(null).request(MenuItemActivity.this.getBaseContext(), "MenuInfo Traits", MenuItemActivity.this.menu_id);
                    boolean check2 = new HTTPManager(null).request(MenuItemActivity.this.getBaseContext(), "MenuInfo Ingredients", MenuItemActivity.this.menu_id);
                    MenuItemActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            Bundle b = MenuItemActivity.this.getIntent().getExtras();
                            try {
                                MenuItemActivity.this.search = new SearchData().getData(MenuItemActivity.this.clicked, "MenuItemActivity", MenuItemActivity.this.getResources(), MenuItemActivity.this.getPackageName(), true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            LinearLayout linear = (LinearLayout) MenuItemActivity.this.findViewById(C0233R.C0234id.middle1);
                            linear.setVisibility(0);
                            linear.setBackgroundColor(0);
                            ArrayList<String> listItems = new ArrayList<>();
                            listItems.add("No info Available");
                            if (MenuItemActivity.this.search.size() > 0) {
                                MenuItemActivity.this.nutrition.setAdapter(new MenuItemAdapter(MenuItemActivity.this.getBaseContext(), MenuItemActivity.this.search));
                            } else {
                                MenuItemActivity.this.nutrition.setAdapter(new ArrayAdapter(MenuItemActivity.this.getBaseContext(), C0233R.layout.list, listItems));
                            }
                            LinearLayout middle = (LinearLayout) MenuItemActivity.this.findViewById(C0233R.C0234id.middle1);
                            MenuItemActivity.this.addLabel(b.getString("type"));
                            middle.setBackgroundColor(0);
                            middle.addView(MenuItemActivity.this.nutrition);
                            ((ProgressBar) MenuItemActivity.this.findViewById(C0233R.C0234id.mainprogressbar)).setVisibility(8);
                            MenuItemActivity.this.trait_flag = true;
                            MenuItemActivity.this.ingredient_flag = true;
                        }
                    });
                } catch (Exception e) {
                    MenuItemActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            try {
                                ((ProgressBar) MenuItemActivity.this.findViewById(C0233R.C0234id.mainprogressbar)).setVisibility(8);
                                MenuItemActivity.this.diplayDialogbox();
                            } catch (Exception e) {
                            }
                        }
                    });
                }
            }
        }).start();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onClick(View v) {
        if (v.getId() == C0233R.C0234id.button1 && this.clicked != "Nutrition") {
            this.prev_clicked = this.clicked;
            ((Button) findViewById(C0233R.C0234id.button2)).setBackgroundResource(C0233R.drawable.unselected2);
            ((Button) findViewById(C0233R.C0234id.button3)).setBackgroundResource(C0233R.drawable.unselected1);
            v.setBackgroundResource(C0233R.drawable.selected);
            this.clicked = "Nutrition";
            try {
                this.search = new SearchData().getData(this.clicked, "MenuItemActivity", getResources(), getPackageName(), true);
                ArrayList<String> listItems = new ArrayList<>();
                listItems.add("No info Available");
                if (this.search.size() > 0) {
                    this.nutrition.setAdapter(new MenuItemAdapter(getBaseContext(), this.search));
                } else {
                    this.nutrition.setAdapter(new ArrayAdapter(getBaseContext(), C0233R.layout.list, listItems));
                }
                if (this.prev_clicked.equals("Traits")) {
                    ((LinearLayout) findViewById(C0233R.C0234id.middle1)).removeView(this.traits);
                } else {
                    ((LinearLayout) findViewById(C0233R.C0234id.middle1)).removeView(this.ingradient);
                }
                ((LinearLayout) findViewById(C0233R.C0234id.middle1)).addView(this.nutrition);
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v.getId() == C0233R.C0234id.button2 && this.clicked != "Traits" && this.trait_flag) {
            this.prev_clicked = this.clicked;
            ((Button) findViewById(C0233R.C0234id.button1)).setBackgroundResource(C0233R.drawable.unselected);
            ((Button) findViewById(C0233R.C0234id.button3)).setBackgroundResource(C0233R.drawable.unselected1);
            v.setBackgroundResource(C0233R.drawable.selected2);
            this.clicked = "Traits";
            try {
                this.search = new SearchData().getData(this.clicked, "MenuItemActivity", getResources(), getPackageName(), true);
                ArrayList<String> listItems2 = new ArrayList<>();
                listItems2.add("No allergens");
                if (this.search.size() > 0) {
                    this.traits.setAdapter(new MenuItemAdapter(getBaseContext(), this.search));
                } else {
                    this.traits.setAdapter(new ArrayAdapter(getBaseContext(), C0233R.layout.list, listItems2));
                }
                if (this.prev_clicked.equals("Nutrition")) {
                    ((LinearLayout) findViewById(C0233R.C0234id.middle1)).removeView(this.nutrition);
                } else {
                    ((LinearLayout) findViewById(C0233R.C0234id.middle1)).removeView(this.ingradient);
                }
                ((LinearLayout) findViewById(C0233R.C0234id.middle1)).addView(this.traits);
                System.gc();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (v.getId() == C0233R.C0234id.button3 && this.clicked != "Ingredients" && this.ingredient_flag) {
            this.prev_clicked = this.clicked;
            ((Button) findViewById(C0233R.C0234id.button1)).setBackgroundResource(C0233R.drawable.unselected);
            ((Button) findViewById(C0233R.C0234id.button2)).setBackgroundResource(C0233R.drawable.unselected2);
            v.setBackgroundResource(C0233R.drawable.selected1);
            this.clicked = "Ingredients";
            try {
                this.search = new SearchData().getData(this.clicked, "MenuItemActivity", getResources(), getPackageName(), true);
                ArrayList<String> listItems3 = new ArrayList<>();
                listItems3.add("No info Available");
                if (this.search.size() > 0) {
                    this.ingradient.setAdapter(new MenuItemAdapter(getBaseContext(), this.search));
                } else {
                    this.ingradient.setAdapter(new ArrayAdapter(getBaseContext(), C0233R.layout.list, listItems3));
                }
                if (this.prev_clicked.equals("Nutrition")) {
                    ((LinearLayout) findViewById(C0233R.C0234id.middle1)).removeView(this.nutrition);
                } else {
                    ((LinearLayout) findViewById(C0233R.C0234id.middle1)).removeView(this.traits);
                }
                ((LinearLayout) findViewById(C0233R.C0234id.middle1)).addView(this.ingradient);
                System.gc();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } else if (v.getId() == C0233R.C0234id.backbutton) {
            finAct();
        }
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
                MenuItemActivity.this.finish();
            }
        });
        builder.create().show();
    }
}
