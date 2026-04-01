package com.samsung.android.gallery.support.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import com.samsung.android.gallery.support.R$bool;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ResourceCompat {
    static final ConcurrentHashMap<String, Boolean> sSmallScreenSizeMap = new ConcurrentHashMap<>();

    public static int dpToPixel(float f) {
        return Math.round(f * getDisplayMetrics(AppResources.getAppContext()).density);
    }

    public static int getCoarseWindowWidth(Resources resources) {
        return resources.getDisplayMetrics().widthPixels;
    }

    public static int getDimensionPixelOffset(Context context, int i2, int i7) {
        if (context != null) {
            return context.getResources().getDimensionPixelOffset(i2);
        }
        return i7;
    }

    public static int getDimensionPixelSize(View view, int i2, int i7) {
        return view != null ? view.getResources().getDimensionPixelSize(i2) : i7;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static float getFloatFromDimension(Context context, int i2, float f) {
        return context != null ? getFloatFromDimension(context.getResources(), i2) : f;
    }

    public static int getInteger(Context context, int i2, int i7) {
        if (context != null) {
            return context.getResources().getInteger(i2);
        }
        return i7;
    }

    public static int getWindowHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getWindowWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static boolean isLandscape(View view) {
        return view != null && view.getResources().getConfiguration().orientation == 2;
    }

    public static boolean isNightTheme(Context context) {
        if (context == null || !context.getResources().getBoolean(R$bool.isNightTheme)) {
            return false;
        }
        return true;
    }

    public static boolean isPortrait(View view) {
        return !isLandscape(view);
    }

    public static boolean isSmallScreenSize(Resources resources) {
        if (resources.getConfiguration().screenWidthDp < 250) {
            return true;
        }
        return false;
    }

    public static boolean isSmallScreenSizeChanged(Context context) {
        if (context == null) {
            return false;
        }
        String obj = context.toString();
        ConcurrentHashMap<String, Boolean> concurrentHashMap = sSmallScreenSizeMap;
        boolean isSmallScreenSize = isSmallScreenSize(context.getResources());
        concurrentHashMap.put(obj, Boolean.valueOf(isSmallScreenSize));
        return !Objects.equals(concurrentHashMap.getOrDefault(obj, Boolean.FALSE), Boolean.valueOf(isSmallScreenSize));
    }

    public static boolean isTablet(Context context) {
        if (context == null || !context.getResources().getBoolean(R$bool.isTabletDpi)) {
            return false;
        }
        return true;
    }

    public static int dpToPixel(Resources resources, float f) {
        try {
            return Math.round(resources.getDisplayMetrics().density * f);
        } catch (Exception unused) {
            return dpToPixel(f);
        }
    }

    public static int getDimensionPixelSize(Context context, int i2, int i7) {
        return context != null ? context.getResources().getDimensionPixelSize(i2) : i7;
    }

    public static float getFloatFromDimension(Context context, int i2) {
        return getFloatFromDimension(context, i2, 0.0f);
    }

    public static boolean isLandscape(Context context) {
        return context != null && context.getResources().getConfiguration().orientation == 2;
    }

    public static boolean isPortrait(Context context) {
        return !isLandscape(context);
    }

    public static float getFloatFromDimension(Resources resources, int i2) {
        return getFloatFromDimension(resources, i2, false);
    }

    public static int dpToPixel(Context context, float f) {
        return dpToPixel(context.getResources(), f);
    }

    public static float getFloatFromDimension(Resources resources, int i2, boolean z) {
        TypedValue typedValue = new TypedValue();
        if (resources != null) {
            resources.getValue(i2, typedValue, z);
        }
        return typedValue.getFloat();
    }
}
