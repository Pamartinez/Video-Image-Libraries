package com.samsung.android.gallery.plugins.motionphoto;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import com.samsung.android.gallery.plugins.R$bool;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.ScreenMode;
import com.samsung.android.gallery.widget.utils.SystemUi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SystemUiCompat {
    public static boolean hasStatusBar(Activity activity) {
        if (activity.getResources().getConfiguration().orientation == 2 || SystemUi.isInMultiWindowMode(activity)) {
            return false;
        }
        return true;
    }

    public static void hideNavigationBar(Activity activity) {
        SystemUi.setSystemUiVisibility(activity, 3846, false);
    }

    public static boolean isDarkTheme(Activity activity) {
        return activity.getResources().getBoolean(R$bool.isNightTheme);
    }

    public static void setActivityProperties(Activity activity) {
        Window window = activity.getWindow();
        SeApiCompat.disableViewRoundedCorner(window.getDecorView());
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
    }

    public static void setFullScreen(Activity activity) {
        String str;
        boolean isDarkTheme = isDarkTheme(activity);
        boolean z = !hasStatusBar(activity);
        ScreenMode full = ScreenMode.getFull(z);
        Boolean valueOf = Boolean.valueOf(z);
        if (isDarkTheme) {
            str = "dark";
        } else {
            str = "light";
        }
        Log.d("SystemUiCompat", "setFullScreen", full, valueOf, str);
        SystemUi.setSystemUiVisibility(activity, SystemUi.getSystemUiVisibilityFull(z));
        if (isDarkTheme) {
            SystemUi.setDarkSystemBar(activity);
        } else {
            SystemUi.setViewerSystemBar(activity);
        }
    }

    public static void setSystemBarEnabled(Activity activity, boolean z) {
        if (z) {
            showNavigationBar(activity);
        } else {
            hideNavigationBar(activity);
        }
    }

    public static void showNavigationBar(Activity activity) {
        boolean z = !isDarkTheme(activity);
        SystemUi.setSystemUiVisibility(activity, SystemUi.getSystemUiVisibilityFull(!hasStatusBar(activity)), false);
        SystemUi.setStatusBarTheme(activity, z);
        SystemUi.setNavigationBarTheme(activity.getWindow(), z);
        SystemUi.addSystemUiVisibilityForDex(activity);
    }
}
