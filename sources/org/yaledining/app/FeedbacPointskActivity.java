package org.yaledining.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class FeedbacPointskActivity extends BaseActivity implements Constant, OnClickListener, OnItemClickListener {
    DatePicker date;
    Display display;
    TextView label;
    ListView list;
    String str = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(C0233R.layout.main);
        this.date = new DatePicker(this);
        this.display = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        ((RelativeLayout) findViewById(C0233R.C0234id.middle)).setLayoutParams(new LayoutParams(-1, NewYalediningActivity.dHeight - NewYalediningActivity.iSetMiddleLayoutHeight_Other));
        LinearLayout linear = (LinearLayout) findViewById(C0233R.C0234id.middle1);
        linear.setVisibility(0);
        int mLeft = NewYalediningActivity.iMiddleLayoutLeftRightPadding;
        int mRight = mLeft;
        int mTop = NewYalediningActivity.iMiddleLayoutTopBottomPadding;
        linear.setPadding(mLeft, mTop, mRight, mTop);
        linear.setBackgroundColor(17170443);
        Bundle b = getIntent().getExtras();
        ((TextView) findViewById(C0233R.C0234id.title)).setText(b.getString("tag"));
        ((Button) findViewById(C0233R.C0234id.star)).setVisibility(4);
        ((Button) findViewById(C0233R.C0234id.refresh)).setVisibility(8);
        TextView tw = (TextView) findViewById(C0233R.C0234id.backbutton);
        tw.setVisibility(0);
        tw.setLayoutParams(new LayoutParams(NewYalediningActivity.iWidthForFeedbackButton, NewYalediningActivity.iHeightForFeedbackButton));
        tw.setBackgroundResource(C0233R.drawable.back_button);
        tw.setText(" Feedback");
        tw.setOnClickListener(this);
        Bottomtabs.gps = b.getBoolean("gps");
        LinearLayout linear2 = (LinearLayout) findViewById(C0233R.C0234id.root);
        linear2.addView(new Bottomtabs(this));
        linear2.removeView(findViewById(C0233R.C0234id.toptab));
        create_view();
    }

    public void onClick(View v) {
        if (C0233R.C0234id.backbutton == v.getId()) {
            if (this.str.equals("")) {
                finish();
                return;
            }
            this.str = "";
            if (this.date.getMonth() < 10) {
                this.str += "0" + (this.date.getMonth() + 1) + "/";
            } else {
                this.str += (this.date.getMonth() + 1) + "/";
            }
            if (this.date.getDayOfMonth() < 10) {
                this.str += "0" + this.date.getDayOfMonth() + "/";
            } else {
                this.str += this.date.getDayOfMonth() + "/";
            }
            this.str += this.date.getYear();
            Bundle b = getIntent().getExtras();
            Intent intent = new Intent();
            intent.putExtra("clicked", this.str);
            intent.putExtra("tag", b.getString("tag"));
            setResult(-1, intent);
            finish();
        } else if (v.getTag().toString().equals("date")) {
            String[] months = new DateFormatSymbols().getMonths();
            String text = null;
            if (this.date.getMonth() >= 0 && this.date.getMonth() <= 11) {
                text = months[this.date.getMonth()];
            }
            this.label.setText(new StringBuilder(String.valueOf(text)).append(" ").append(this.date.getDayOfMonth()).append(", ").append(this.date.getYear()).toString());
        }
    }

    private void create_view() {
        Bundle b = getIntent().getExtras();
        if (b.getString("tag").equals("Cleanliness") || b.getString("tag").equals("Food") || b.getString("tag").equals("Service")) {
            this.list = new ListView(this);
            this.list.setLayoutParams(new LayoutParams(-1, -1));
            this.list.setScrollingCacheEnabled(false);
            this.list.setBackgroundColor(17170445);
            this.list.setCacheColorHint(17170445);
            TextView text = new TextView(this);
            text.setTextColor(0);
            text.setText("5 - Excellent");
            ArrayList<String> listItems = new ArrayList<>();
            listItems.add(" 5 - Excellent");
            listItems.add(" 4 - Good");
            listItems.add(" 3 - Adequate");
            listItems.add(" 2 - Unacceptable");
            listItems.add(" 1 - No Answer");
            this.list.setAdapter(new FeedbackAdapter(this, listItems, getIntent().getExtras().getString("checked")));
            ((LinearLayout) findViewById(C0233R.C0234id.middle1)).addView(this.list);
            this.list.setOnItemClickListener(this);
        } else if (b.getString("tag").equals("Date")) {
            LayoutParams params = new LayoutParams(-1, -1);
            params.setMargins(0, NewYalediningActivity.iTopMaginToDatePicker, 0, 0);
            params.gravity = 80;
            this.date.setLayoutParams(params);
            this.date.setOnClickListener(this);
            this.date.setTag("date");
            this.date.setTag("date");
            LayoutInflater inflater = getLayoutInflater();
            String[] months = new DateFormatSymbols().getMonths();
            String text2 = null;
            if (this.date.getMonth() >= 0 && this.date.getMonth() <= 11) {
                text2 = months[this.date.getMonth()];
            }
            String text3 = new StringBuilder(String.valueOf(text2)).append(" ").append(this.date.getDayOfMonth()).append(", ").append(this.date.getYear()).toString();
            this.str = "";
            if (this.date.getMonth() < 10) {
                this.str += "0" + (this.date.getMonth() + 1) + "/";
            } else {
                this.str += (this.date.getMonth() + 1) + "/";
            }
            if (this.date.getMonth() < 10) {
                this.str += "0" + this.date.getDayOfMonth() + "/";
            } else {
                this.str += this.date.getDayOfMonth() + "/";
            }
            this.str += this.date.getYear();
            this.label = (TextView) inflater.inflate(C0233R.layout.list, null);
            this.label.setTextSize(20.0f);
            this.label.setText(text3);
            this.label.setGravity(1);
            LinearLayout linear = (LinearLayout) findViewById(C0233R.C0234id.middle1);
            linear.addView(this.label);
            linear.addView(this.date);
        } else if (b.getString("tag").equals("MealPeriod")) {
            this.list = new ListView(this);
            this.list.setLayoutParams(new LayoutParams(-1, -1));
            this.list.setScrollingCacheEnabled(false);
            this.list.setBackgroundColor(17170445);
            this.list.setCacheColorHint(17170445);
            TextView text4 = new TextView(this);
            text4.setTextColor(0);
            text4.setText("5 - Excellent");
            ArrayList<String> listItems2 = new ArrayList<>();
            listItems2.add(" Breakfast");
            listItems2.add(" Brunch");
            listItems2.add(" Lunch");
            listItems2.add(" Dinner");
            this.list.setAdapter(new FeedbackAdapter(this, listItems2, getIntent().getExtras().getString("checked")));
            ((LinearLayout) findViewById(C0233R.C0234id.middle1)).addView(this.list);
            this.list.setOnItemClickListener(this);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String selectedFromList = (String) this.list.getItemAtPosition(position);
        Bundle b = getIntent().getExtras();
        Intent intent = new Intent();
        intent.putExtra("clicked", selectedFromList);
        intent.putExtra("tag", b.getString("tag"));
        setResult(-1, intent);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
