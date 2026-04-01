package com.samsung.android.gallery.app.ui.abstraction;

import A.a;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.material.appbar.AppBarLayout;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.ScreenMode;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.o3dp.lib3dphotography.i;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MvpBaseSystemUi {
    private WeakReference<Activity> mActivityRef;
    private Blackboard mBlackboard;
    private View mContentView;
    private BooleanSupplier mCustomHasNoStatusBar;
    private int mDensityDpi;
    private Boolean mFromLockScreen;
    private int mHardKeyboardHidden;
    private boolean mIsAospLargeScreenDpi;
    private boolean mIsTabletDpi;
    private int mOrientation;
    private int mResolution;
    private Boolean mScreenLocked;

    private int calculateResolution(Configuration configuration) {
        return (configuration.screenWidthDp * 10000) + configuration.screenHeightDp;
    }

    private int convertOrientationToActivityOrientation(int i2) {
        if (i2 == 1) {
            return 6;
        }
        return 1;
    }

    private Activity getActivity() {
        Activity activity;
        WeakReference<Activity> weakReference = this.mActivityRef;
        if (weakReference != null) {
            activity = weakReference.get();
        } else {
            activity = null;
        }
        if (activity != null) {
            return activity;
        }
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        this.mActivityRef = new WeakReference<>(readActivity);
        return readActivity;
    }

    private int getSystemOrientation(Activity activity) {
        return activity.getResources().getConfiguration().orientation;
    }

    private boolean hasNoStatusBar() {
        BooleanSupplier booleanSupplier = this.mCustomHasNoStatusBar;
        if (booleanSupplier != null && booleanSupplier.getAsBoolean()) {
            return true;
        }
        if (isPortrait() || !SystemUi.hasNoStatusBarInLandscape(getActivity())) {
            return false;
        }
        return true;
    }

    private boolean isThemeChanged(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (window == null) {
            return true;
        }
        boolean isStatusBarThemeLight = SystemUi.isStatusBarThemeLight(window);
        boolean isNaviBarThemeLight = SystemUi.isNaviBarThemeLight(window);
        if (isStatusBarThemeLight == z && isNaviBarThemeLight == z) {
            return false;
        }
        return true;
    }

    private void updateEmptyViewChildPosition(ViewGroup viewGroup, float f) {
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            viewGroup.getChildAt(i2).setTranslationY(f);
        }
    }

    private void updateViewerSystemBarColor(Activity activity, boolean z) {
        if (z) {
            SystemUi.setDarkSystemBar(activity);
        } else {
            SystemUi.setViewerSystemBar(activity);
        }
    }

    public boolean cancelForceRotate(Activity activity) {
        Blackboard blackboard;
        int i2;
        if (SystemUi.isRequestedOrientationIgnored(activity) || (blackboard = this.mBlackboard) == null || blackboard.read("viewer_force_rotated") == null) {
            return false;
        }
        if (activity != null) {
            Log.d("MvpBaseSystemUi", "cancelForceRotate " + this.mBlackboard.read("viewer_force_rotated"));
            if (!SdkConfig.atLeast(SdkConfig.GED.T) || !"com.samsung.android.gallery.app.activity.external.GalleryExternalActivity".equals(activity.getClass().getName())) {
                i2 = -1;
            } else {
                i2 = 3;
            }
            setRequestedOrientation(activity, i2);
        }
        this.mBlackboard.erase("viewer_force_rotated");
        return true;
    }

    public void clearScreenLocked() {
        this.mScreenLocked = Boolean.FALSE;
    }

    public void enterFullListScreen(boolean z) {
        String str;
        String str2;
        String str3;
        Activity activity = getActivity();
        if (activity != null) {
            boolean hasNoStatusBar = hasNoStatusBar();
            ScreenMode listFull = ScreenMode.getListFull(hasNoStatusBar);
            ScreenMode screenMode = SystemUi.getScreenMode(this.mBlackboard);
            boolean isThemeChanged = isThemeChanged(activity, !z);
            if (screenMode != listFull || isThemeChanged) {
                StringBuilder sb2 = new StringBuilder("enterScreen fullList {new)");
                if (hasNoStatusBar) {
                    str = "nostat";
                } else {
                    str = "stat";
                }
                sb2.append(str);
                sb2.append(NumericEnum.SEP);
                sb2.append(listFull);
                if (z) {
                    str2 = ":dark";
                } else {
                    str2 = ":light";
                }
                sb2.append(str2);
                sb2.append(" - prev)");
                sb2.append(screenMode);
                if (isThemeChanged) {
                    str3 = ":themeChanged";
                } else {
                    str3 = ":sameTheme";
                }
                sb2.append(str3);
                sb2.append("}");
                Log.d("MvpBaseSystemUi", sb2.toString());
                SystemUi.setSystemUiVisibility(activity, SystemUi.getSystemUiVisibilityFull(hasNoStatusBar));
                SystemUi.setScreenMode(this.mBlackboard, listFull);
                if (z) {
                    SystemUi.setDarkSystemBar(activity);
                } else {
                    SystemUi.setNormalSystemBar(activity);
                }
            } else {
                SystemUi.requestApplyInset(activity);
            }
        }
    }

    public void enterFullScreen(boolean z) {
        String str;
        String str2;
        String str3;
        Activity activity = getActivity();
        if (activity != null) {
            boolean hasNoStatusBar = hasNoStatusBar();
            ScreenMode full = ScreenMode.getFull(hasNoStatusBar);
            ScreenMode screenMode = SystemUi.getScreenMode(this.mBlackboard);
            boolean isThemeChanged = isThemeChanged(activity, !z);
            if (screenMode != full || isThemeChanged) {
                StringBuilder sb2 = new StringBuilder("enterScreen full {new)");
                if (hasNoStatusBar) {
                    str = "nostat";
                } else {
                    str = "stat";
                }
                sb2.append(str);
                sb2.append(NumericEnum.SEP);
                sb2.append(full);
                if (z) {
                    str2 = ":dark";
                } else {
                    str2 = ":light";
                }
                sb2.append(str2);
                sb2.append(" - prev)");
                sb2.append(screenMode);
                if (isThemeChanged) {
                    str3 = ":themeChanged";
                } else {
                    str3 = ":sameTheme";
                }
                sb2.append(str3);
                sb2.append("}");
                Log.d("MvpBaseSystemUi", sb2.toString());
                SystemUi.setSystemUiVisibility(activity, SystemUi.getSystemUiVisibilityFull(hasNoStatusBar));
                SystemUi.setScreenMode(this.mBlackboard, full);
                updateViewerSystemBarColor(activity, z);
                return;
            }
            SystemUi.requestApplyInset(activity);
            updateViewerSystemBarColor(activity, z);
        }
    }

    public View getContentView(Activity activity) {
        if (this.mContentView == null) {
            if (activity != null) {
                this.mContentView = activity.getWindow().getDecorView();
            } else {
                Log.e("MvpBaseSystemUi", "fail get content view");
            }
        }
        return this.mContentView;
    }

    public int getResolution() {
        return this.mResolution;
    }

    public int getStatusBarHeight(Activity activity) {
        boolean z;
        if (!hasNoStatusBar() || !SystemUi.getScreenMode(this.mBlackboard).isNoStatusBar()) {
            z = false;
        } else {
            z = true;
        }
        if (activity == null || SystemUi.isInMultiWindowMode(activity) || z) {
            return 0;
        }
        return SystemUi.getStatusBarHeight(activity);
    }

    public void hideNavigationBar() {
        Activity activity = getActivity();
        if (activity != null) {
            hideNavigationBar(!activity.getResources().getBoolean(R.bool.isNightTheme));
        }
    }

    public boolean isAospLargeScreenDpi() {
        return this.mIsAospLargeScreenDpi;
    }

    public boolean isFromLockScreen() {
        LaunchIntent launchIntent;
        boolean z;
        if (this.mFromLockScreen == null) {
            Blackboard blackboard = this.mBlackboard;
            if (blackboard == null) {
                launchIntent = null;
            } else {
                launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
            }
            if (launchIntent == null || !launchIntent.isFromLockScreen()) {
                z = false;
            } else {
                z = true;
            }
            this.mFromLockScreen = Boolean.valueOf(z);
        }
        return this.mFromLockScreen.booleanValue();
    }

    public boolean isPortrait() {
        if (this.mOrientation == 1) {
            return true;
        }
        return false;
    }

    public boolean isScreenLocked() {
        boolean z;
        Boolean bool = this.mScreenLocked;
        if (bool == null || bool.booleanValue()) {
            if (!isFromLockScreen() || !SeApiCompat.isScreenLocked(AppResources.getAppContext())) {
                z = false;
            } else {
                z = true;
            }
            this.mScreenLocked = Boolean.valueOf(z);
        }
        return this.mScreenLocked.booleanValue();
    }

    public boolean isTabletDpi() {
        return this.mIsTabletDpi;
    }

    public void onViewAttach(Activity activity, Blackboard blackboard) {
        this.mActivityRef = new WeakReference<>(activity);
        this.mBlackboard = blackboard;
        Resources resources = activity.getResources();
        if (resources == null) {
            Log.e("MvpBaseSystemUi", "onAttach null resources");
            return;
        }
        Configuration configuration = resources.getConfiguration();
        if (configuration == null) {
            Log.e("MvpBaseSystemUi", "onAttach null configuration");
            return;
        }
        updateDpi(resources);
        this.mOrientation = configuration.orientation;
        this.mDensityDpi = configuration.densityDpi;
        this.mResolution = calculateResolution(configuration);
    }

    public void onViewDetach() {
        this.mActivityRef = null;
        this.mContentView = null;
    }

    public void requestForceRotate(Activity activity) {
        if (this.mBlackboard != null && activity != null && !cancelForceRotate(activity)) {
            int systemOrientation = getSystemOrientation(activity);
            this.mBlackboard.publish("viewer_force_rotated", Integer.valueOf(systemOrientation));
            int convertOrientationToActivityOrientation = convertOrientationToActivityOrientation(systemOrientation);
            Log.d("MvpBaseSystemUi", "requestForceRotate " + systemOrientation + ArcCommonLog.TAG_COMMA + convertOrientationToActivityOrientation);
            setRequestedOrientation(activity, convertOrientationToActivityOrientation);
        }
    }

    public void setCustomHasNoStatusBar(BooleanSupplier booleanSupplier) {
        this.mCustomHasNoStatusBar = booleanSupplier;
    }

    public boolean setDensityDpi(int i2) {
        if (this.mDensityDpi == i2) {
            return false;
        }
        this.mDensityDpi = i2;
        return true;
    }

    public boolean setHardKeyboardHidden(Configuration configuration) {
        return setHardKeyboardHidden(configuration.hardKeyboardHidden);
    }

    public boolean setOrientation(int i2) {
        if (this.mOrientation == i2) {
            return false;
        }
        this.mOrientation = i2;
        return true;
    }

    public void setRequestedOrientation(Activity activity, int i2) {
        a.k(i2, "setRequestOrientation ", "MvpBaseSystemUi");
        if (activity != null) {
            activity.setRequestedOrientation(i2);
        }
    }

    public boolean setResolution(Configuration configuration) {
        return setResolution(calculateResolution(configuration));
    }

    public void showNavigationBar() {
        Activity activity = getActivity();
        if (activity != null) {
            showNavigationBar(!activity.getResources().getBoolean(R.bool.isNightTheme));
        }
    }

    public void updateDpi(Resources resources) {
        this.mIsTabletDpi = resources.getBoolean(R.bool.isTabletDpi);
        this.mIsAospLargeScreenDpi = resources.getBoolean(R.bool.aospLargeScreenDpi);
    }

    public void updateEmptyViewLayout(AppBarLayout appBarLayout, View view, int i2, int i7, int i8) {
        int i10;
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.no_item_view_container);
        if (viewGroup != null && view.getVisibility() != 8) {
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            if (i2 > viewGroup.getChildAt(0).getMeasuredHeight()) {
                i10 = (i2 - appBarLayout.getTotalScrollRange()) + Math.abs(i7) + i8;
                updateEmptyViewChildPosition(viewGroup, 0.0f);
            } else {
                i10 = -1;
            }
            if (layoutParams.height != i10) {
                layoutParams.height = i10;
                viewGroup.post(new i(5, viewGroup, layoutParams));
            }
        }
    }

    private boolean setHardKeyboardHidden(int i2) {
        if (this.mHardKeyboardHidden == i2) {
            return false;
        }
        this.mHardKeyboardHidden = i2;
        return true;
    }

    private boolean setResolution(int i2) {
        if (this.mResolution == i2) {
            return false;
        }
        this.mResolution = i2;
        return true;
    }

    public void hideNavigationBar(boolean z) {
        if (getActivity() != null) {
            SystemUi.setSystemUiVisibility(getActivity(), 3846, false);
            SystemUi.setSystemBarBehaviorInGestureBar(getActivity(), hasNoStatusBar(), false);
            SystemUi.setStatusBarTheme(getActivity(), z);
            SystemUi.setNavigationBarTheme(getActivity().getWindow(), z);
        }
    }

    public void showNavigationBar(boolean z) {
        Activity activity = getActivity();
        if (activity != null) {
            SystemUi.setSystemUiVisibility(activity, SystemUi.getSystemUiVisibilityFull(hasNoStatusBar()), false);
            SystemUi.setStatusBarTheme(activity, z);
            SystemUi.setSystemBarBehaviorInGestureBar(activity, hasNoStatusBar(), true);
            SystemUi.setNavigationBarTheme(activity.getWindow(), z);
            SystemUi.addSystemUiVisibilityForDex(activity);
        }
    }
}
