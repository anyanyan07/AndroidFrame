package com.xwtec.androidframe.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by ayy on 2018/6/23.
 * Describe:xxx
 */

public class PopUtil {
    public static void popShowFromBottom(Activity activity, PopupWindow popupWindow) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = 0.5f;
        window.setAttributes(layoutParams);
        popupWindow.showAtLocation(window.getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    public static void popDismiss(Activity activity, PopupWindow popupWindow) {
        if (popupWindow != null && popupWindow.isShowing()) {
            Window window = activity.getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.alpha = 1.0f;
            window.setAttributes(layoutParams);
            popupWindow.dismiss();
        }
    }
}
