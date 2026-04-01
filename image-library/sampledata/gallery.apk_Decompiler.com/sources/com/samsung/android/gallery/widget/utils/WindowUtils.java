package com.samsung.android.gallery.widget.utils;

import android.app.Activity;
import android.graphics.Insets;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$id;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WindowUtils {
    private static final int SYSTEM_BARS_WITH_CUTOUT;
    private static final int SYSTEM_BARS_WITH_IME;

    static {
        int systemBars = WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout();
        SYSTEM_BARS_WITH_CUTOUT = systemBars;
        SYSTEM_BARS_WITH_IME = systemBars | WindowInsets.Type.ime();
    }

    public static Rect getCaptionBarInsets(WindowInsets windowInsets) {
        return getCaptionBarInsets(windowInsets, false);
    }

    public static int getDisplayCutoutEnd(WindowInsets windowInsets) {
        if (Features.isEnabled(Features.IS_RTL)) {
            return getDisplayCutoutLeft(windowInsets);
        }
        return getDisplayCutoutRight(windowInsets);
    }

    public static int getDisplayCutoutLeft(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return windowInsets.getInsets(WindowInsets.Type.displayCutout()).left;
        }
        return 0;
    }

    public static Rect getDisplayCutoutRect(WindowInsets windowInsets) {
        if (windowInsets == null) {
            return new Rect();
        }
        Insets insets = windowInsets.getInsets(WindowInsets.Type.displayCutout());
        return new Rect(insets.left, insets.top, insets.right, insets.bottom);
    }

    public static int getDisplayCutoutRight(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return windowInsets.getInsets(WindowInsets.Type.displayCutout()).right;
        }
        return 0;
    }

    public static int getIMEInsetsBottom(WindowInsets windowInsets) {
        int i2;
        if (windowInsets == null || (i2 = windowInsets.getInsets(WindowInsets.Type.ime()).bottom) <= 0) {
            return 0;
        }
        return Math.max(i2 - windowInsets.getInsets(WindowInsets.Type.systemBars()).bottom, 0);
    }

    public static int getMultiWindowToolbarHeight(Activity activity) {
        if (activity == null || !activity.isInMultiWindowMode()) {
            return 0;
        }
        try {
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            View findViewById = viewGroup.findViewById(R$id.content);
            if (findViewById == null) {
                findViewById = viewGroup.findViewById(R$id.content_container);
            }
            if (findViewById == null) {
                return 0;
            }
            int[] iArr = new int[2];
            findViewById.getLocationInWindow(iArr);
            return iArr[1];
        } catch (NullPointerException unused) {
            return 0;
        }
    }

    public static Rect getNavigationBarInsets(WindowInsets windowInsets) {
        return getNavigationBarInsets(windowInsets, false);
    }

    public static Rect getStatusBarInsets(WindowInsets windowInsets) {
        return getStatusBarInsets(windowInsets, false);
    }

    public static Rect getSystemInsets(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return getSystemInsetsR(windowInsets);
        }
        return new Rect();
    }

    public static int getSystemInsetsBottom(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return windowInsets.getInsets(SYSTEM_BARS_WITH_CUTOUT).bottom;
        }
        return 0;
    }

    public static int getSystemInsetsEnd(WindowInsets windowInsets) {
        if (Features.isEnabled(Features.IS_RTL)) {
            return getSystemInsetsLeft(windowInsets);
        }
        return getSystemInsetsRight(windowInsets);
    }

    public static Rect getSystemInsetsIgnoringVisibility(WindowInsets windowInsets) {
        if (windowInsets == null) {
            return new Rect();
        }
        Insets insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
        return new Rect(insetsIgnoringVisibility.left, insetsIgnoringVisibility.top, insetsIgnoringVisibility.right, insetsIgnoringVisibility.bottom);
    }

    public static int getSystemInsetsLeft(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return windowInsets.getInsets(SYSTEM_BARS_WITH_CUTOUT).left;
        }
        return 0;
    }

    private static Rect getSystemInsetsR(WindowInsets windowInsets) {
        return getSystemInsetsR(windowInsets, SYSTEM_BARS_WITH_CUTOUT);
    }

    public static int getSystemInsetsRight(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return windowInsets.getInsets(SYSTEM_BARS_WITH_CUTOUT).right;
        }
        return 0;
    }

    public static int getSystemInsetsStart(WindowInsets windowInsets) {
        if (Features.isEnabled(Features.IS_RTL)) {
            return getSystemInsetsRight(windowInsets);
        }
        return getSystemInsetsLeft(windowInsets);
    }

    public static int getSystemInsetsTop(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return windowInsets.getInsets(SYSTEM_BARS_WITH_CUTOUT).top;
        }
        return 0;
    }

    public static Rect getSystemInsetsWithIme(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return getSystemInsetsR(windowInsets, SYSTEM_BARS_WITH_IME);
        }
        return new Rect();
    }

    public static boolean isIMEVisible(WindowInsets windowInsets) {
        if (getIMEInsetsBottom(windowInsets) > 0) {
            return true;
        }
        return false;
    }

    public static Rect getCaptionBarInsets(WindowInsets windowInsets, boolean z) {
        if (windowInsets == null) {
            return new Rect(0, 0, 0, 0);
        }
        if (z) {
            Insets insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.captionBar());
            return new Rect(insetsIgnoringVisibility.left, insetsIgnoringVisibility.top, insetsIgnoringVisibility.right, insetsIgnoringVisibility.bottom);
        }
        Insets insets = windowInsets.getInsets(WindowInsets.Type.captionBar());
        return new Rect(insets.left, insets.top, insets.right, insets.bottom);
    }

    public static Rect getNavigationBarInsets(WindowInsets windowInsets, boolean z) {
        if (windowInsets == null) {
            return new Rect(0, 0, 0, 0);
        }
        if (z) {
            Insets insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());
            return new Rect(insetsIgnoringVisibility.left, insetsIgnoringVisibility.top, insetsIgnoringVisibility.right, insetsIgnoringVisibility.bottom);
        }
        Insets insets = windowInsets.getInsets(WindowInsets.Type.navigationBars());
        return new Rect(insets.left, insets.top, insets.right, insets.bottom);
    }

    public static Rect getStatusBarInsets(WindowInsets windowInsets, boolean z) {
        if (windowInsets == null) {
            return new Rect(0, 0, 0, 0);
        }
        if (z) {
            Insets insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.statusBars());
            return new Rect(insetsIgnoringVisibility.left, insetsIgnoringVisibility.top, insetsIgnoringVisibility.right, insetsIgnoringVisibility.bottom);
        }
        Insets insets = windowInsets.getInsets(WindowInsets.Type.statusBars());
        return new Rect(insets.left, insets.top, insets.right, insets.bottom);
    }

    private static Rect getSystemInsetsR(WindowInsets windowInsets, int i2) {
        Insets insets = windowInsets.getInsets(i2);
        return new Rect(insets.left, insets.top, insets.right, insets.bottom);
    }
}
