package com.samsung.android.gallery.widget.utils;

import B7.h;
import E7.b;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.PathInterpolator;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PickerViewUtil {
    private static void adjustActivityToolBarMargin(View view, Rect rect) {
        Optional.ofNullable(view).ifPresent(new h(rect, 2));
    }

    private static void adjustClipboardMargin(View view, Rect rect) {
        Optional.ofNullable(view).ifPresent(new h(rect, 1));
    }

    public static void adjustContentAreaMargin(View view, int i2) {
        ViewMarginUtils.setTopMargin(view, i2);
    }

    public static void adjustEmptyViewMargin(View view, int i2) {
        ViewMarginUtils.setBottomMargin(view, i2);
    }

    public static void adjustTopAreaMargin(WindowInsets windowInsets, View view, View view2) {
        int i2;
        int systemInsetsStart = WindowUtils.getSystemInsetsStart(windowInsets);
        int systemInsetsTop = WindowUtils.getSystemInsetsTop(windowInsets);
        int systemInsetsEnd = WindowUtils.getSystemInsetsEnd(windowInsets);
        if (view != null) {
            i2 = SystemUi.getToolBarHeightWithPadding(view.getContext());
        } else {
            i2 = 0;
        }
        adjustActivityToolBarMargin(view, new Rect(systemInsetsStart, systemInsetsTop, systemInsetsEnd, 0));
        adjustClipboardMargin(view2, new Rect(systemInsetsStart, systemInsetsTop + i2, systemInsetsEnd, 0));
    }

    public static void adjustViewAreaMargin(WindowInsets windowInsets, View view, View view2, View view3) {
        int i2;
        int systemInsetsStart = WindowUtils.getSystemInsetsStart(windowInsets);
        int systemInsetsTop = WindowUtils.getSystemInsetsTop(windowInsets);
        int systemInsetsEnd = WindowUtils.getSystemInsetsEnd(windowInsets);
        if (view != null) {
            i2 = SystemUi.getToolBarHeightWithPadding(view.getContext());
        } else {
            i2 = 0;
        }
        adjustActivityToolBarMargin(view, new Rect(systemInsetsStart, systemInsetsTop, systemInsetsEnd, 0));
        int i7 = systemInsetsTop + i2;
        adjustClipboardMargin(view2, new Rect(systemInsetsStart, i7, systemInsetsEnd, 0));
        adjustContentAreaMargin(view3, i7);
    }

    public static void setContentViewTopPadding(View view, int i2) {
        ViewMarginUtils.setTopPadding(view, i2);
    }

    public static void setContentViewTopPaddingWithAnimation(View view, int i2, int i7) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i2, i7});
        ofInt.setInterpolator(new PathInterpolator(0.22f, 0.25f, 0.0f, 1.1f));
        ofInt.addUpdateListener(new b(view, 2));
        ofInt.setDuration(500);
        ofInt.setStartDelay(i2 > i7 ? 100 : 0);
        ofInt.start();
    }

    public static void setContentViewTopPaddingWithAnimation(View view, int i2) {
        setContentViewTopPaddingWithAnimation(view, view.getPaddingTop(), i2);
    }
}
