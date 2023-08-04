package com.operator.app.kalyanitechnoforge.uihelper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utilities {
    /**
     * Utilities instance
     */
    private static Utilities utilities = null;

    private Utilities() {
        // No instance
    }

    public static Utilities getInstance() {
        if (utilities == null) {
            utilities = new Utilities();
        }
        return utilities;
    }

    public void setLightStatusBar(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(color); // optional
        }
    }

    /**
     * Hide Soft keyboard
     *
     * @param mContext current activity context
     * @param view     any view
     */
    public void hideSoftKeyboard(Context mContext, View view) {
        if (((Activity) mContext).getCurrentFocus() != null && ((Activity) mContext).getCurrentFocus() instanceof View) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
