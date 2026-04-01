package com.samsung.android.gallery.widget.utils;

import A.a;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$attr;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.abstraction.ScreenMode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SystemUi {
    private static void addSystemUiVisibility(Window window, int i2) {
        try {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | i2);
        } catch (Exception e) {
            a.s(e, C0086a.o(i2, "addSystemUiVisibility [", "] failed e="), "SystemUi");
        }
    }

    public static void addSystemUiVisibilityForDex(Activity activity) {
        if (activity != null && DeviceInfo.isDexMode(Blackboard.getContext())) {
            addSystemUiVisibility(activity.getWindow(), 4096);
        }
    }

    public static void changeColorMode(Activity activity, int i2) {
        Window window;
        int colorMode;
        if (SuperHdrConfig.SUPPORT && SuperHdrConfig.isEnabled()) {
            if (activity == null) {
                window = null;
            } else {
                window = activity.getWindow();
            }
            if (window != null && (colorMode = window.getColorMode()) != i2) {
                Log.i("SystemUi", "changeColorMode", Logger.toColorMode(colorMode), Logger.toColorMode(i2));
                window.setColorMode(i2);
                window.getDecorView().getRootView().postInvalidate();
            }
        }
    }

    public static void clearShowWhenLocked(Activity activity) {
        try {
            Log.d("SystemUi", "clearLockScreenView");
            clearShowWhenLockedCompatO(activity);
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            int i2 = attributes.flags;
            if ((524288 & i2) != 0) {
                attributes.flags = i2 & -524289;
                activity.getWindow().setAttributes(attributes);
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("clearLockScreenView failed e="), "SystemUi");
        }
    }

    private static void clearShowWhenLockedCompatO(Activity activity) {
        activity.setShowWhenLocked(false);
    }

    private static void clearSystemUiVisibility(Window window, int i2) {
        try {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (~i2));
        } catch (Exception e) {
            a.s(e, C0086a.o(i2, "clearSystemUiVisibility [", "] failed e="), "SystemUi");
        }
    }

    public static int getNavigationBarHeight(WindowInsets windowInsets) {
        if (windowInsets == null) {
            return DeviceInfo.getNavigationBarHeight();
        }
        Insets insets = windowInsets.getInsets(WindowInsets.Type.navigationBars());
        return insets.bottom - insets.top;
    }

    public static Rect getPinEdgeRect(WindowInsets windowInsets, Context context) {
        Rect rect = new Rect();
        boolean isPinEdgeEnabled = SeApiCompat.isPinEdgeEnabled(context);
        if (windowInsets != null && context != null && Build.VERSION.SDK_INT == 30 && isPinEdgeEnabled) {
            Insets insets = windowInsets.getInsets(WindowInsets.Type.navigationBars());
            int pinnedEdgeWidth = SeApiCompat.getPinnedEdgeWidth(context);
            if (SeApiCompat.isLeftPinnedEdge(context)) {
                if (insets.left != pinnedEdgeWidth) {
                    pinnedEdgeWidth = 0;
                }
                rect.left = pinnedEdgeWidth;
                return rect;
            }
            if (insets.right != pinnedEdgeWidth) {
                pinnedEdgeWidth = 0;
            }
            rect.right = pinnedEdgeWidth;
        }
        return rect;
    }

    public static ScreenMode getScreenMode(Blackboard blackboard) {
        if (blackboard != null) {
            return (ScreenMode) blackboard.read("data://screenMode", ScreenMode.Normal);
        }
        return ScreenMode.Normal;
    }

    public static int getStatusBarColor(Activity activity) {
        if (activity != null) {
            return getStatusBarColor(activity.getWindow());
        }
        return -1;
    }

    public static int getStatusBarHeight(Activity activity) {
        try {
            return activity.getWindow().getDecorView().getRootWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()).top;
        } catch (Exception e) {
            Log.e("SystemUi", "getStatusBarHeight failed. e=" + e.getMessage());
            return DeviceInfo.getStatusBarHeight();
        }
    }

    public static int getStatusBarHeightIfExists(Activity activity) {
        try {
            return activity.getWindow().getDecorView().getRootWindowInsets().getInsets(WindowInsets.Type.systemBars()).top;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getStatusBarHeightIfExists failed. e="), "SystemUi");
            if (activity == null || isInMultiWindowMode(activity) || isInNoStatusBarMode(activity)) {
                return 0;
            }
            return getStatusBarHeight(activity);
        }
    }

    public static int getSystemUiVisibilityFull(boolean z) {
        if (z) {
            return 1796;
        }
        return 1792;
    }

    public static int getToolBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R$attr.actionBarSize});
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public static int getToolBarHeightWithPadding(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_top_padding) + getToolBarHeight(context);
    }

    public static boolean hasNoStatusBarInLandscape(Activity activity) {
        if (AppResources.getBoolean(R$bool.isTabletDpi) || isInMultiWindowMode(activity)) {
            return false;
        }
        return true;
    }

    public static boolean isFullScreen(Blackboard blackboard) {
        ScreenMode screenMode = getScreenMode(blackboard);
        if (ScreenMode.Full == screenMode || ScreenMode.FullNos == screenMode) {
            return true;
        }
        return false;
    }

    public static boolean isInMultiWindowMode(Activity activity) {
        return activity != null && activity.isInMultiWindowMode();
    }

    public static boolean isInNoStatusBarMode(Activity activity) {
        if (!ResourceCompat.isLandscape((Context) activity) || !hasNoStatusBarInLandscape(activity) || !getScreenMode(Blackboard.getInstance(activity.toString())).isNoStatusBar()) {
            return false;
        }
        return true;
    }

    public static boolean isNaviBarThemeLight(Window window) {
        if ((window.getDecorView().getSystemUiVisibility() & 16) != 0) {
            return true;
        }
        return false;
    }

    public static boolean isRequestedOrientationIgnored(Activity activity) {
        if (!SdkConfig.atLeast(SdkConfig.GED.B) || activity == null || !activity.getResources().getBoolean(R$bool.aospLargeScreenDpi)) {
            return false;
        }
        return true;
    }

    public static boolean isStatusBarThemeLight(Window window) {
        if ((window.getDecorView().getSystemUiVisibility() & SerializeOptions.SORT) != 0) {
            return true;
        }
        return false;
    }

    public static boolean isSystemBarBehaviorDefault(Activity activity) {
        WindowInsetsController windowInsetsController;
        if (Build.VERSION.SDK_INT < 31 || activity == null || (windowInsetsController = activity.getWindow().getDecorView().getWindowInsetsController()) == null || windowInsetsController.getSystemBarsBehavior() != 1) {
            return false;
        }
        return true;
    }

    public static void keepScreenOn(Activity activity, boolean z) {
        if (activity != null) {
            if (z) {
                try {
                    activity.getWindow().addFlags(128);
                } catch (Exception e) {
                    StringBuilder sb2 = new StringBuilder("keepScreenOn {");
                    sb2.append(z);
                    sb2.append("} failed. e=");
                    a.s(e, sb2, "SystemUi");
                    return;
                }
            } else {
                activity.getWindow().clearFlags(128);
            }
            Log.i("SystemUi", "keepScreenOn {" + z + "}");
            return;
        }
        Log.e("SystemUi", "keepScreenOn {" + z + "} failed. null activity");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setSystemBarBehaviorInGestureBar$0(boolean z, WindowInsetsController windowInsetsController, boolean z3) {
        if (z) {
            windowInsetsController.hide(WindowInsets.Type.statusBars());
            windowInsetsController.setSystemBarsBehavior(2);
            return;
        }
        if (!z3) {
            windowInsetsController.hide(WindowInsets.Type.systemBars());
        } else {
            windowInsetsController.show(WindowInsets.Type.systemBars());
        }
        windowInsetsController.setSystemBarsBehavior(1);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setSystemBarBehaviorInGestureBar$1(Activity activity, boolean z, boolean z3) {
        WindowInsetsController windowInsetsController;
        if (Build.VERSION.SDK_INT >= 31 && activity != null && (windowInsetsController = activity.getWindow().getDecorView().getWindowInsetsController()) != null && DeviceInfo.isGestureNaviBar(activity)) {
            ThreadUtil.runOnUiThread(new androidx.core.widget.a(z, windowInsetsController, z3));
        }
    }

    public static void requestApplyInset(Activity activity) {
        try {
            activity.getWindow().getDecorView().requestApplyInsets();
        } catch (Exception e) {
            a.s(e, new StringBuilder("requestApplyInset failed e="), "SystemUi");
        }
    }

    public static void setDarkNavigationBar(Activity activity) {
        setNavigationBarTheme(activity, false);
    }

    public static void setDarkStatusBar(Activity activity) {
        setStatusBarTheme(activity, false);
    }

    public static void setDarkSystemBar(Activity activity) {
        if (activity != null) {
            setDarkStatusBar(activity);
            setDarkNavigationBar(activity);
        }
    }

    public static void setDecorFitsSystemWindows(Window window, boolean z) {
        try {
            window.setDecorFitsSystemWindows(z);
        } catch (Exception e) {
            a.s(e, new StringBuilder("setDecorFitsSystemWindows failed. e="), "SystemUi");
        }
    }

    public static void setLayoutInDisPlayCutoutMode(Window window, int i2) {
        try {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = i2;
            window.setAttributes(attributes);
        } catch (Exception e) {
            a.s(e, new StringBuilder("setLayoutInDisPlayCutoutMode failed. e="), "SystemUi");
        }
    }

    public static void setNavigationBarTheme(Activity activity, boolean z) {
        if (activity != null) {
            setNavigationBarTheme(activity.getWindow(), z);
        }
    }

    public static void setNormalNavigationBar(Activity activity) {
        setNavigationBarTheme(activity, activity.getResources().getBoolean(R$bool.gallery_window_light_navigation_bar));
    }

    public static void setNormalStatusBar(Activity activity) {
        setStatusBarTheme(activity, activity.getResources().getBoolean(R$bool.is_light_status_bar));
    }

    public static void setNormalSystemBar(Activity activity) {
        if (activity != null) {
            setNormalStatusBar(activity);
            setNormalNavigationBar(activity);
        }
    }

    public static void setScreenMode(Blackboard blackboard, ScreenMode screenMode) {
        if (blackboard != null) {
            blackboard.replace("data://screenMode", screenMode);
        }
    }

    public static void setStatusBarTheme(Activity activity, boolean z) {
        if (activity != null) {
            setStatusBarTheme(activity.getWindow(), z);
        }
    }

    public static void setSystemBarBehaviorInGestureBar(Activity activity, boolean z, boolean z3) {
        SimpleThreadPool.getInstance().execute(new androidx.core.widget.a(activity, z, z3, 2));
    }

    private static void setSystemUiVisibility(Window window, int i2, boolean z) {
        try {
            View decorView = window.getDecorView();
            if ((i2 & 4) != 0) {
                window.setFlags(1024, 1024);
            } else {
                window.clearFlags(1024);
            }
            decorView.setSystemUiVisibility(i2);
            if (z) {
                decorView.requestApplyInsets();
            }
        } catch (Exception e) {
            a.s(e, C0086a.o(i2, "setSystemUiVisibility [", "] failed e="), "SystemUi");
        }
    }

    private static void setViewerNavigationBar(Activity activity) {
        setNavigationBarTheme(activity, !activity.getResources().getBoolean(R$bool.isNightTheme));
    }

    private static void setViewerStatusBar(Activity activity) {
        setStatusBarTheme(activity, !activity.getResources().getBoolean(R$bool.isNightTheme));
    }

    public static void setViewerSystemBar(Activity activity) {
        if (activity != null) {
            setViewerNavigationBar(activity);
            setViewerStatusBar(activity);
        }
    }

    public static boolean supportPopoverUi(Context context, boolean z) {
        if (!Features.isEnabled(Features.SUPPORT_POP_OVER_UI) || !context.getResources().getBoolean(R$bool.supportPopover)) {
            return false;
        }
        if (!z || !SdkConfig.atLeast(SdkConfig.GED.S)) {
            return true;
        }
        return false;
    }

    public static void updateSystemUiVisibilityForFullScreen(Activity activity) {
        if (activity == null) {
            return;
        }
        if (!isInMultiWindowMode(activity)) {
            addSystemUiVisibility(activity.getWindow(), 4096);
        } else {
            clearSystemUiVisibility(activity.getWindow(), 4096);
        }
    }

    public static int getStatusBarColor(Window window) {
        if (window != null) {
            return window.getStatusBarColor();
        }
        return -1;
    }

    public static boolean isInMultiWindowMode(Context context) {
        return (context instanceof Activity) && isInMultiWindowMode((Activity) context);
    }

    public static void setNavigationBarTheme(Window window, boolean z) {
        if (PocFeatures.isEnabled(PocFeatures.GradientProtection)) {
            clearSystemUiVisibility(window, 16);
        } else if (z) {
            addSystemUiVisibility(window, 16);
        } else {
            clearSystemUiVisibility(window, 16);
        }
    }

    public static void setStatusBarTheme(Window window, boolean z) {
        if (PocFeatures.isEnabled(PocFeatures.GradientProtection)) {
            clearSystemUiVisibility(window, SerializeOptions.SORT);
        } else if (z) {
            addSystemUiVisibility(window, SerializeOptions.SORT);
        } else {
            clearSystemUiVisibility(window, SerializeOptions.SORT);
        }
    }

    public static void setSystemUiVisibility(Activity activity, int i2) {
        if (activity != null) {
            setSystemUiVisibility(activity.getWindow(), i2, true);
        }
    }

    public static void setSystemUiVisibility(Activity activity, int i2, boolean z) {
        if (activity != null) {
            setSystemUiVisibility(activity.getWindow(), i2, z);
        }
    }
}
