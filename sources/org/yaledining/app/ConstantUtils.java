package org.yaledining.app;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class ConstantUtils {
    public static void getValues(Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        NewYalediningActivity.dHeight = display.getHeight();
        if (display.getHeight() >= 1750) {
            NewYalediningActivity.iSetMiddleLayoutHeight = 470;
            NewYalediningActivity.iSetMiddleLayoutHeight_Other = 360;
            NewYalediningActivity.iWidthForFeedbackButton = 230;
            NewYalediningActivity.iHeightForFeedbackButton = 80;
            NewYalediningActivity.iTopMaginToDatePicker = 100;
            NewYalediningActivity.iMiddleLayoutLeftRightPadding = 20;
            NewYalediningActivity.iMiddleLayoutTopBottomPadding = 20;
            NewYalediningActivity.iProgressbarHeightAndWidth = 100;
            NewYalediningActivity.iSetHeightToFeedBackButton = 110;
        } else if (display.getHeight() >= 1000 && display.getHeight() <= 1300) {
            NewYalediningActivity.iSetMiddleLayoutHeight = 320;
            NewYalediningActivity.iSetMiddleLayoutHeight_Other = 240;
            NewYalediningActivity.iWidthForFeedbackButton = 170;
            NewYalediningActivity.iHeightForFeedbackButton = 65;
            NewYalediningActivity.iTopMaginToDatePicker = 100;
            NewYalediningActivity.iMiddleLayoutLeftRightPadding = 20;
            NewYalediningActivity.iMiddleLayoutTopBottomPadding = 20;
            NewYalediningActivity.iProgressbarHeightAndWidth = 50;
            NewYalediningActivity.iSetHeightToFeedBackButton = 80;
        } else if (display.getHeight() >= 440 && display.getHeight() <= 500) {
            NewYalediningActivity.iSetMiddleLayoutHeight = 170;
            NewYalediningActivity.iSetMiddleLayoutHeight_Other = 130;
            NewYalediningActivity.iWidthForFeedbackButton = 90;
            NewYalediningActivity.iHeightForFeedbackButton = 35;
            NewYalediningActivity.iTopMaginToDatePicker = 80;
            NewYalediningActivity.iMiddleLayoutLeftRightPadding = 20;
            NewYalediningActivity.iMiddleLayoutTopBottomPadding = 20;
            NewYalediningActivity.iProgressbarHeightAndWidth = 30;
            NewYalediningActivity.iSetHeightToFeedBackButton = 30;
        } else if (display.getHeight() < 300 || display.getHeight() > 400) {
            NewYalediningActivity.iSetMiddleLayoutHeight = 240;
            NewYalediningActivity.iSetMiddleLayoutHeight_Other = 180;
            NewYalediningActivity.iWidthForFeedbackButton = 130;
            NewYalediningActivity.iHeightForFeedbackButton = 55;
            NewYalediningActivity.iTopMaginToDatePicker = 100;
            NewYalediningActivity.iMiddleLayoutLeftRightPadding = 20;
            NewYalediningActivity.iMiddleLayoutTopBottomPadding = 20;
            NewYalediningActivity.iProgressbarHeightAndWidth = 40;
            NewYalediningActivity.iSetHeightToFeedBackButton = 55;
        } else {
            NewYalediningActivity.iSetMiddleLayoutHeight = 120;
            NewYalediningActivity.iSetMiddleLayoutHeight_Other = 95;
            NewYalediningActivity.iWidthForFeedbackButton = 60;
            NewYalediningActivity.iHeightForFeedbackButton = 30;
            NewYalediningActivity.iTopMaginToDatePicker = 50;
            NewYalediningActivity.iMiddleLayoutLeftRightPadding = 20;
            NewYalediningActivity.iMiddleLayoutTopBottomPadding = 5;
            NewYalediningActivity.iProgressbarHeightAndWidth = 20;
            NewYalediningActivity.iSetHeightToFeedBackButton = 24;
        }
        Logger.vLog("ConstantUtils => getValues : ", "iSetMiddleLayoutHeight : " + NewYalediningActivity.iSetMiddleLayoutHeight);
        Logger.vLog("ConstantUtils => getValues : ", "iSetMiddleLayoutHeight_Other : " + NewYalediningActivity.iSetMiddleLayoutHeight_Other);
        Logger.vLog("ConstantUtils => getWidthAndHeightForFeedbackButton : ", "Width : " + NewYalediningActivity.iWidthForFeedbackButton + " Height : " + NewYalediningActivity.iHeightForFeedbackButton);
        Logger.vLog("ConstantUtils => getTopMaginToDatePicker : ", "Margin : " + NewYalediningActivity.iTopMaginToDatePicker);
        Logger.vLog("ConstantUtils => Linear Padding : ", "LeftRight : " + NewYalediningActivity.iMiddleLayoutLeftRightPadding + " TopBottom :" + NewYalediningActivity.iMiddleLayoutTopBottomPadding);
    }

    public static int getKaizenValueInMainActivity(Context context) {
        int mValue;
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (display.getHeight() >= 1750) {
            mValue = 470;
        } else if (display.getHeight() >= 1000 && display.getHeight() <= 1300) {
            mValue = 320;
        } else if (display.getHeight() >= 440 && display.getHeight() <= 500) {
            mValue = 170;
        } else if (display.getHeight() < 300 || display.getHeight() > 400) {
            mValue = 240;
        } else {
            mValue = 120;
        }
        Logger.vLog("ConstantUtils => getKaizenValueInMainActivity : ", "Kaizen : " + mValue);
        return mValue;
    }

    public static int getKaizenValueInOtherActivity(Context context) {
        int mValue;
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (display.getHeight() >= 1750) {
            mValue = 360;
        } else if (display.getHeight() >= 1000 && display.getHeight() <= 1300) {
            mValue = 240;
        } else if (display.getHeight() >= 440 && display.getHeight() <= 500) {
            mValue = 130;
        } else if (display.getHeight() < 300 || display.getHeight() > 400) {
            mValue = 180;
        } else {
            mValue = 95;
        }
        Logger.vLog("ConstantUtils => getKaizenValueInOtherActivity : ", "Kaizen : " + mValue);
        return mValue;
    }
}
