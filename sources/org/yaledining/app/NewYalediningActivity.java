package org.yaledining.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class NewYalediningActivity extends BaseActivity implements DialogiOnClick {
    public static int dHeight;
    public static int iHeightForFeedbackButton;
    public static int iMiddleLayoutLeftRightPadding;
    public static int iMiddleLayoutTopBottomPadding;
    public static int iProgressbarHeightAndWidth;
    public static int iSetHeightToFeedBackButton;
    public static int iSetMiddleLayoutHeight;
    public static int iSetMiddleLayoutHeight_Other;
    public static int iTopMaginToDatePicker;
    public static int iWidthForFeedbackButton;
    public static int keygen;
    boolean check = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(C0233R.layout.splash);
        ConstantUtils.getValues(this);
        try {
            this.check = CheckInternetUtil.isNetAvailable(this);
            if (this.check) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        try {
                            NewYalediningActivity.this.check = new HTTPManager(NewYalediningActivity.this).request(NewYalediningActivity.this, "Names of Restaurants", 0);
                            ((TextView) NewYalediningActivity.this.findViewById(C0233R.C0234id.text)).setText("Completed");
                            if (NewYalediningActivity.this.check) {
                                SharedPreferences preferences = NewYalediningActivity.this.getSharedPreferences("yaledining", 0);
                                String activity = preferences.getString("activity", null);
                                if (activity == null) {
                                    NewYalediningActivity.this.startActivity(new Intent(NewYalediningActivity.this, MainActivity.class));
                                } else if (activity.equals("MainActivity")) {
                                    Intent intent = new Intent(NewYalediningActivity.this, MainActivity.class);
                                    intent.putExtra("clicked", preferences.getString("clicked", null));
                                    NewYalediningActivity.this.startActivity(intent);
                                } else if (activity.equals("RestaurantActivity")) {
                                    Intent intent2 = new Intent(NewYalediningActivity.this, RestaurantActivity.class);
                                    intent2.putExtra("type", preferences.getString("key", null));
                                    intent2.putExtra("image", preferences.getString("image", null));
                                    intent2.putExtra("Location_id", preferences.getString("Location_id", null));
                                    intent2.putExtra("type_restaurant", preferences.getString("type_restaurant", null));
                                    NewYalediningActivity.this.startActivity(intent2);
                                } else {
                                    NewYalediningActivity.this.finish();
                                }
                                NewYalediningActivity.this.finish();
                                NewYalediningActivity.this.overridePendingTransition(C0233R.anim.mainfadein, C0233R.anim.splashfadeout);
                            }
                        } catch (Exception e) {
                            if (NewYalediningActivity.this.check) {
                                SharedPreferences preferences2 = NewYalediningActivity.this.getSharedPreferences("yaledining", 0);
                                String activity2 = preferences2.getString("activity", null);
                                if (activity2 == null) {
                                    NewYalediningActivity.this.startActivity(new Intent(NewYalediningActivity.this, MainActivity.class));
                                } else if (activity2.equals("MainActivity")) {
                                    Intent intent3 = new Intent(NewYalediningActivity.this, MainActivity.class);
                                    intent3.putExtra("clicked", preferences2.getString("clicked", null));
                                    NewYalediningActivity.this.startActivity(intent3);
                                } else if (activity2.equals("RestaurantActivity")) {
                                    Intent intent4 = new Intent(NewYalediningActivity.this, RestaurantActivity.class);
                                    intent4.putExtra("type", preferences2.getString("key", null));
                                    intent4.putExtra("image", preferences2.getString("image", null));
                                    intent4.putExtra("Location_id", preferences2.getString("Location_id", null));
                                    intent4.putExtra("type_restaurant", preferences2.getString("type_restaurant", null));
                                    NewYalediningActivity.this.startActivity(intent4);
                                } else {
                                    NewYalediningActivity.this.finish();
                                }
                                NewYalediningActivity.this.finish();
                                NewYalediningActivity.this.overridePendingTransition(C0233R.anim.mainfadein, C0233R.anim.splashfadeout);
                            }
                        } catch (Throwable th) {
                            if (NewYalediningActivity.this.check) {
                                SharedPreferences preferences3 = NewYalediningActivity.this.getSharedPreferences("yaledining", 0);
                                String activity3 = preferences3.getString("activity", null);
                                if (activity3 == null) {
                                    NewYalediningActivity.this.startActivity(new Intent(NewYalediningActivity.this, MainActivity.class));
                                } else if (activity3.equals("MainActivity")) {
                                    Intent intent5 = new Intent(NewYalediningActivity.this, MainActivity.class);
                                    intent5.putExtra("clicked", preferences3.getString("clicked", null));
                                    NewYalediningActivity.this.startActivity(intent5);
                                } else if (activity3.equals("RestaurantActivity")) {
                                    Intent intent6 = new Intent(NewYalediningActivity.this, RestaurantActivity.class);
                                    intent6.putExtra("type", preferences3.getString("key", null));
                                    intent6.putExtra("image", preferences3.getString("image", null));
                                    intent6.putExtra("Location_id", preferences3.getString("Location_id", null));
                                    intent6.putExtra("type_restaurant", preferences3.getString("type_restaurant", null));
                                    NewYalediningActivity.this.startActivity(intent6);
                                } else {
                                    NewYalediningActivity.this.finish();
                                }
                                NewYalediningActivity.this.finish();
                                NewYalediningActivity.this.overridePendingTransition(C0233R.anim.mainfadein, C0233R.anim.splashfadeout);
                            }
                            throw th;
                        }
                    }
                }, (long) 2000);
            } else {
                new Dailog(this, "Alert", "Unable to Connect.", 2, "Close", "Retry");
            }
        } catch (Exception e) {
            new Dailog(this, "Alert", "Unable to Connect.", 2, "Close", "Retry");
        }
        ((TextView) findViewById(C0233R.C0234id.text)).setText("Getting latest data..");
        getWindow().setWindowAnimations(C0233R.anim.splashfadeout);
        getWindow().setWindowAnimations(C0233R.anim.mainfadein);
    }

    public void onClick(DialogInterface dialog, int which) {
        if (-2 == which) {
            startActivity(new Intent(this, NewYalediningActivity.class));
            finish();
        } else if (-1 == which) {
            finish();
        }
    }
}
