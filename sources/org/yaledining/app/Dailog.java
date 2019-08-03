package org.yaledining.app;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;

public class Dailog extends AlertDialog {
    public Dailog(Context context, String title, String message, int number_of_buttons, String positive_button, String negative_button) {
        super(context);
        Builder builder = new Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        if (number_of_buttons == 1) {
            builder.setPositiveButton(positive_button, (DialogiOnClick) context);
            builder.create().show();
        } else if (number_of_buttons == 2) {
            builder.setPositiveButton(positive_button, (DialogiOnClick) context);
            builder.setNegativeButton(negative_button, (DialogiOnClick) context);
            builder.create().show();
        }
    }
}
