package com.samsung.android.gallery.image360.systemui;

import N2.j;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import com.samsung.android.gallery.image360.R$bool;
import com.samsung.android.gallery.widget.utils.SystemUi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseSystemUi {
    protected final String TAG = getClass().getSimpleName();
    private int mOrientation;

    private boolean hasNoStatusBar(Activity activity) {
        if (isPortrait() || !SystemUi.hasNoStatusBarInLandscape(activity)) {
            return false;
        }
        return true;
    }

    private boolean isPortrait() {
        if (this.mOrientation == 1) {
            return true;
        }
        return false;
    }

    public void hideNavigationBar(Activity activity) {
        SystemUi.setSystemUiVisibility(activity, 3846, false);
        SystemUi.setSystemBarBehaviorInGestureBar(activity, hasNoStatusBar(activity), false);
    }

    public void onViewAttach(Resources resources) {
        if (resources == null) {
            Log.e(this.TAG, "onAttach null resources");
            return;
        }
        Configuration configuration = resources.getConfiguration();
        if (configuration == null) {
            Log.e(this.TAG, "onAttach null configuration");
        } else {
            this.mOrientation = configuration.orientation;
        }
    }

    public void removeFullScreenFlags(Activity activity) {
        if (activity != null && !SystemUi.isInMultiWindowMode(activity)) {
            try {
                activity.getWindow().getDecorView().setSystemUiVisibility(8208);
            } catch (Exception e) {
                j.D(e, new StringBuilder("removeFullScreenFlags e="), this.TAG);
            }
        }
    }

    public void setOrientation(int i2) {
        if (this.mOrientation != i2) {
            this.mOrientation = i2;
        }
    }

    public void showNavigationBar(Activity activity) {
        if (activity != null) {
            boolean z = !activity.getResources().getBoolean(R$bool.isNightTheme);
            SystemUi.setSystemUiVisibility(activity, SystemUi.getSystemUiVisibilityFull(hasNoStatusBar(activity)), false);
            SystemUi.setStatusBarTheme(activity, z);
            SystemUi.setSystemBarBehaviorInGestureBar(activity, hasNoStatusBar(activity), true);
            SystemUi.setNavigationBarTheme(activity.getWindow(), z);
            SystemUi.addSystemUiVisibilityForDex(activity);
        }
    }

    public abstract void updateBarColors(Activity activity);

    public void setRootView(View view) {
    }

    public void onApplyInsets(Rect rect, Rect rect2) {
    }
}
