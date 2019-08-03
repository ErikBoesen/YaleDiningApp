package org.yaledining.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.regex.Pattern;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

public class FeedbackActivity extends BaseActivity implements Constant, OnTouchListener, OnClickListener, OnKeyListener, DialogiOnClick, TextWatcher {
    String Checked = "";
    boolean email = true;
    boolean feedback = false;
    boolean sending = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(C0233R.layout.main);
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        if (NewYalediningActivity.dHeight < 1850) {
            if (NewYalediningActivity.dHeight < 1000 || NewYalediningActivity.dHeight > 1300) {
                if (NewYalediningActivity.dHeight < 440 || NewYalediningActivity.dHeight > 500) {
                    if (NewYalediningActivity.dHeight >= 300 && NewYalediningActivity.dHeight <= 400) {
                        Logger.vLog("MainActivity ", "Keygen : " + 95);
                    }
                }
            }
        }
        ((RelativeLayout) findViewById(C0233R.C0234id.middle)).setLayoutParams(new LayoutParams(-1, NewYalediningActivity.dHeight - NewYalediningActivity.iSetMiddleLayoutHeight_Other));
        LinearLayout linear = (LinearLayout) findViewById(C0233R.C0234id.middle1);
        linear.setVisibility(0);
        int mLeft = NewYalediningActivity.iMiddleLayoutLeftRightPadding;
        int mRight = mLeft;
        int mTop = NewYalediningActivity.iMiddleLayoutTopBottomPadding;
        linear.setPadding(mLeft, mTop, mRight, mTop);
        Bundle b = getIntent().getExtras();
        TextView title = (TextView) findViewById(C0233R.C0234id.title);
        if (b.getString("name of restaurant").length() > 15) {
            title.setText(new StringBuilder(String.valueOf(b.getString("name of restaurant").substring(0, 13))).append("..").toString());
        } else {
            title.setText(b.getString("name of restaurant"));
        }
        linear.addView(((LayoutInflater) getSystemService("layout_inflater")).inflate(C0233R.layout.table, null));
        Button button = (Button) findViewById(C0233R.C0234id.star);
        button.setText("Send");
        button.setOnClickListener(this);
        ((Button) findViewById(C0233R.C0234id.refresh)).setVisibility(8);
        TextView tw = (TextView) findViewById(C0233R.C0234id.backbutton);
        tw.setVisibility(0);
        tw.setText(" Info");
        tw.setOnClickListener(this);
        Bottomtabs.gps = b.getBoolean("gps");
        LinearLayout linear2 = (LinearLayout) findViewById(C0233R.C0234id.root);
        Bottomtabs bottomtabs = new Bottomtabs(this);
        linear2.addView(bottomtabs);
        linear2.removeView(findViewById(C0233R.C0234id.toptab));
        ((TableRow) findViewById(C0233R.C0234id.cleanliness)).setOnClickListener(this);
        ((TableRow) findViewById(C0233R.C0234id.food)).setOnClickListener(this);
        ((TableRow) findViewById(C0233R.C0234id.service)).setOnClickListener(this);
        ((TableRow) findViewById(C0233R.C0234id.date)).setOnClickListener(this);
        ((TableRow) findViewById(C0233R.C0234id.mealperiod)).setOnClickListener(this);
        EditText text = (EditText) findViewById(C0233R.C0234id.email);
        text.setNextFocusDownId(C0233R.C0234id.comments);
        text.setOnKeyListener(this);
        text.setOnTouchListener(this);
        text.addTextChangedListener(this);
        EditText text2 = (EditText) findViewById(C0233R.C0234id.comments);
        text2.setOnKeyListener(this);
        text2.setOnTouchListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == C0233R.C0234id.backbutton) {
            finish();
        } else if (v.getId() == C0233R.C0234id.star) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(v.getWindowToken(), 0);
            if (!this.email) {
                new Dailog(this, "Alert", "Invalid Email .", 1, "Ok", "");
            } else if (checkfields() && this.email && !this.sending) {
                this.sending = true;
                new Handler().post(new Runnable() {
                    public void run() {
                        FeedbackActivity.this.sendata();
                    }
                });
            } else if (!this.sending) {
                new Dailog(this, "Alert", "Please complete all the required fields to continue", 1, "Ok", "");
            }
        } else if (v.getId() == C0233R.C0234id.cleanliness || v.getId() == C0233R.C0234id.food || v.getId() == C0233R.C0234id.service || v.getId() == C0233R.C0234id.date || v.getId() == C0233R.C0234id.mealperiod) {
            Intent intent = getIntent().setClass(this, FeedbacPointskActivity.class);
            intent.putExtra("tag", v.getTag().toString());
            if (v.getId() == C0233R.C0234id.cleanliness) {
                intent.putExtra("checked", ((TextView) findViewById(C0233R.C0234id.points_clean)).getText());
            } else if (v.getId() == C0233R.C0234id.food) {
                intent.putExtra("checked", ((TextView) findViewById(C0233R.C0234id.points_food)).getText());
            } else if (v.getId() == C0233R.C0234id.service) {
                intent.putExtra("checked", ((TextView) findViewById(C0233R.C0234id.points_service)).getText());
            } else if (v.getId() == C0233R.C0234id.mealperiod) {
                intent.putExtra("checked", ((TextView) findViewById(C0233R.C0234id.meal_time)).getText());
            }
            intent.putExtra("gps", Bottomtabs.gps);
            startActivityForResult(intent, 0);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode != -1 || requestCode != 0) {
            return;
        }
        if (intent.getStringExtra("tag").equals("Cleanliness")) {
            this.Checked = intent.getStringExtra("clicked");
            ((ImageView) findViewById(C0233R.C0234id.arrow1)).setVisibility(8);
            TextView text = (TextView) findViewById(C0233R.C0234id.points_clean);
            text.setVisibility(0);
            text.setText(intent.getStringExtra("clicked"));
        } else if (intent.getStringExtra("tag").equals("Food")) {
            this.Checked = intent.getStringExtra("clicked");
            ((ImageView) findViewById(C0233R.C0234id.arrow2)).setVisibility(8);
            TextView text2 = (TextView) findViewById(C0233R.C0234id.points_food);
            text2.setVisibility(0);
            text2.setText(intent.getStringExtra("clicked"));
        } else if (intent.getStringExtra("tag").equals("Service")) {
            this.Checked = intent.getStringExtra("clicked");
            ((ImageView) findViewById(C0233R.C0234id.arrow3)).setVisibility(8);
            TextView text3 = (TextView) findViewById(C0233R.C0234id.points_service);
            text3.setVisibility(0);
            text3.setText(intent.getStringExtra("clicked"));
        } else if (intent.getStringExtra("tag").equals("Date")) {
            ((ImageView) findViewById(C0233R.C0234id.arrow4)).setVisibility(8);
            TextView text4 = (TextView) findViewById(C0233R.C0234id.date1);
            text4.setVisibility(0);
            text4.setText(intent.getStringExtra("clicked"));
        } else if (intent.getStringExtra("tag").equals("MealPeriod")) {
            this.Checked = intent.getStringExtra("clicked");
            ((ImageView) findViewById(C0233R.C0234id.arrow5)).setVisibility(8);
            TextView text5 = (TextView) findViewById(C0233R.C0234id.meal_time);
            text5.setVisibility(0);
            text5.setText(intent.getStringExtra("clicked"));
        }
    }

    /* access modifiers changed from: private */
    public void sendata() {
        Bundle b = getIntent().getExtras();
        HttpClient httpclient = new DefaultHttpClient();
        String url = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("http://www.yaledining.org/fasttrack/location-feedback-process.cfm?" + "Id_Location=")).append(Search_ID(b.getString("name of restaurant"))).toString())).append("&Cleanliness=").toString())).append(((TextView) findViewById(C0233R.C0234id.points_clean)).getText().charAt(1)).toString())).append("&Food=").toString())).append(((TextView) findViewById(C0233R.C0234id.points_food)).getText().charAt(1)).toString())).append("&Service=").toString())).append(((TextView) findViewById(C0233R.C0234id.points_service)).getText().charAt(1)).toString())).append("&DateOfFeedback=").toString())).append(((TextView) findViewById(C0233R.C0234id.date1)).getText().toString().replace(" ", "")).toString())).append("&MealPeriod=").toString())).append(((TextView) findViewById(C0233R.C0234id.meal_time)).getText().toString()).toString();
        Editable edit = ((EditText) findViewById(C0233R.C0234id.email)).getText();
        new String();
        String temp = edit.toString();
        if (temp.length() == 0) {
            temp = null;
        }
        String url2 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(url)).append("&EmailFrom=").toString())).append(temp).toString();
        String temp2 = ((EditText) findViewById(C0233R.C0234id.comments)).getText().toString().replace("\n", " ");
        if (temp2.length() == 0) {
            temp2 = null;
        }
        String url3 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(url2)).append("&Comments=").toString())).append(temp2).toString().replace(" ", "");
        try {
            if (CheckInternetUtil.isNetAvailable(this)) {
                HttpResponse execute = httpclient.execute(new HttpGet(url3));
                new Dailog(this, "Alert", "Feedback was submitted successfully.", 1, "Ok", "");
                this.feedback = true;
            } else {
                new Dailog(this, "Alert", "Unable to Connect", 1, "Ok", null);
            }
            this.sending = false;
        } catch (Exception e) {
            e.printStackTrace();
            this.sending = false;
            try {
                new Dailog(this, "Alert", "Failed to submit feedback.", 1, "Ok", "");
            } catch (Exception e2) {
            }
        }
    }

    private int Search_ID(String key) {
        int i = 0;
        while (i < Database.Data.length()) {
            try {
                if (Database.Data.getJSONArray(i).getString(2).equals(key)) {
                    return Database.Data.getJSONArray(i).getInt(0);
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    private boolean checkfields() {
        if (((TextView) findViewById(C0233R.C0234id.points_clean)).getVisibility() == 8 || ((TextView) findViewById(C0233R.C0234id.points_food)).getVisibility() == 8 || ((TextView) findViewById(C0233R.C0234id.points_service)).getVisibility() == 8 || ((TextView) findViewById(C0233R.C0234id.meal_time)).getVisibility() == 8 || ((TextView) findViewById(C0233R.C0234id.date1)).getVisibility() == 8) {
            return false;
        }
        return true;
    }

    public void onClick(DialogInterface dialog, int which) {
        if (this.feedback) {
            finish();
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == C0233R.C0234id.email) {
            ((EditText) v).setHint("");
            v.setFocusable(true);
        } else if (v.getId() == C0233R.C0234id.comments) {
            ((EditText) v).setHint("");
            v.setFocusable(true);
        }
        return false;
    }

    public void afterTextChanged(Editable arg0) {
    }

    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    }

    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        String email1 = ((EditText) findViewById(C0233R.C0234id.email)).getText().toString();
        if (Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email1).matches() || email1.length() == 0) {
            this.email = true;
        } else {
            this.email = false;
        }
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        v.getId();
        if (v.getId() == C0233R.C0234id.email && keyCode == 66) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.clearFocus();
        } else if (v.getId() == C0233R.C0234id.comments && keyCode == 66) {
            v.clearFocus();
            ((EditText) findViewById(C0233R.C0234id.email)).setFocusable(true);
        }
        return false;
    }
}
