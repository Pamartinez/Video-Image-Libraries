package com.samsung.android.gallery.settings.helper;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.settings.R$bool;
import com.samsung.android.gallery.settings.R$dimen;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SettingLayoutUtils {
    public static void adjustSettingToolbarLayout(View view, AppBarLayout appBarLayout, WindowInsets windowInsets) {
        int systemInsetsTop = WindowUtils.getSystemInsetsTop(windowInsets);
        if (view != null) {
            ViewMarginUtils.setTopPadding((View) view.getParent(), systemInsetsTop);
            ViewParent parent = view.getParent();
            if (parent instanceof FloatingToolbarLayout) {
                ((FloatingToolbarLayout) parent).setWindowBottomInset(WindowUtils.getSystemInsetsBottom(windowInsets));
            }
        }
        if (appBarLayout != null) {
            appBarLayout.seslSetCollapsedHeight(appBarLayout.seslGetDefaultCollapsedHeight() + ((float) systemInsetsTop));
        }
    }

    private static float getMainLayoutWidthRatio(Context context) {
        float floatFromDimension;
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (!ViewUtils.needUpdateSideSpacing(displayMetrics)) {
            return 100.0f;
        }
        if (resources.getBoolean(R$bool.isDexDrawerSize)) {
            floatFromDimension = resources.getDimension(R$dimen.content_layout_width_for_large_screen) / ((float) displayMetrics.widthPixels);
        } else {
            floatFromDimension = ResourceCompat.getFloatFromDimension(resources, R$dimen.list_view_width_ratio, true);
        }
        return floatFromDimension * 100.0f;
    }

    public static int getPaddingFromAttr(Context context, int i2) {
        boolean isTerminated;
        boolean isTerminated2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i2});
        boolean z = false;
        try {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
            if (obtainStyledAttributes instanceof AutoCloseable) {
                ((AutoCloseable) obtainStyledAttributes).close();
                return dimensionPixelSize;
            } else if (obtainStyledAttributes instanceof ExecutorService) {
                ExecutorService executorService = (ExecutorService) obtainStyledAttributes;
                if (executorService != ForkJoinPool.commonPool() && !(isTerminated2 = executorService.isTerminated())) {
                    executorService.shutdown();
                    while (!isTerminated2) {
                        try {
                            isTerminated2 = executorService.awaitTermination(1, TimeUnit.DAYS);
                        } catch (InterruptedException unused) {
                            if (!z) {
                                executorService.shutdownNow();
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                }
                return dimensionPixelSize;
            } else {
                obtainStyledAttributes.recycle();
                return dimensionPixelSize;
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static int getPreferencePadding(Context context) {
        float mainLayoutWidthRatio = getMainLayoutWidthRatio(context);
        if (mainLayoutWidthRatio == 100.0f) {
            return context.getResources().getDimensionPixelOffset(R$dimen.sesl_preference_padding_horizontal);
        }
        int i2 = context.getResources().getDisplayMetrics().widthPixels;
        return (int) ((((float) i2) - ((mainLayoutWidthRatio / 100.0f) * ((float) i2))) / 2.0f);
    }

    public static boolean hasSideSpacing(Context context) {
        if (getMainLayoutWidthRatio(context) != 100.0f) {
            return true;
        }
        return false;
    }

    private static void setLayoutWeight(View view, float f) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.weight = f;
        view.setLayoutParams(layoutParams);
    }

    public static void updateLayoutWeight(Context context, View view, View view2, View view3) {
        if (context != null && view != null && view2 != null && view3 != null) {
            float mainLayoutWidthRatio = getMainLayoutWidthRatio(context);
            float f = (100.0f - mainLayoutWidthRatio) / 2.0f;
            setLayoutWeight(view, f);
            setLayoutWeight(view2, mainLayoutWidthRatio);
            setLayoutWeight(view3, f);
        }
    }
}
