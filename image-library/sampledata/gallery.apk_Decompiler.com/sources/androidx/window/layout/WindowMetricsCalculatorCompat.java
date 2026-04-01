package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0018\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u001c\u0010\u001aJ\u0017\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u001e\u0010\u001aJ\u0017\u0010!\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b \u0010\u001aJ\u0017\u0010%\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u0010H\u0001¢\u0006\u0004\b#\u0010$R\u0014\u0010'\u001a\u00020&8\u0002X\u0004¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006)"}, d2 = {"Landroidx/window/layout/WindowMetricsCalculatorCompat;", "Landroidx/window/layout/WindowMetricsCalculator;", "<init>", "()V", "Landroid/app/Activity;", "activity", "Landroid/graphics/Rect;", "bounds", "Lme/x;", "getRectSizeFromDisplay", "(Landroid/app/Activity;Landroid/graphics/Rect;)V", "Landroid/content/Context;", "context", "", "getNavigationBarHeight", "(Landroid/content/Context;)I", "Landroid/view/Display;", "display", "Landroid/view/DisplayCutout;", "getCutoutForDisplay", "(Landroid/view/Display;)Landroid/view/DisplayCutout;", "Landroidx/window/layout/WindowMetrics;", "computeCurrentWindowMetrics", "(Landroid/app/Activity;)Landroidx/window/layout/WindowMetrics;", "computeMaximumWindowMetrics", "computeWindowBoundsQ$window_release", "(Landroid/app/Activity;)Landroid/graphics/Rect;", "computeWindowBoundsQ", "computeWindowBoundsP$window_release", "computeWindowBoundsP", "computeWindowBoundsN$window_release", "computeWindowBoundsN", "computeWindowBoundsIceCreamSandwich$window_release", "computeWindowBoundsIceCreamSandwich", "Landroid/graphics/Point;", "getRealSizeForDisplay$window_release", "(Landroid/view/Display;)Landroid/graphics/Point;", "getRealSizeForDisplay", "", "TAG", "Ljava/lang/String;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WindowMetricsCalculatorCompat implements WindowMetricsCalculator {
    public static final WindowMetricsCalculatorCompat INSTANCE = new WindowMetricsCalculatorCompat();
    private static final String TAG = "WindowMetricsCalculatorCompat";

    private WindowMetricsCalculatorCompat() {
    }

    private final DisplayCutout getCutoutForDisplay(Display display) {
        try {
            Constructor<?> constructor = Class.forName("android.view.DisplayInfo").getConstructor((Class[]) null);
            constructor.setAccessible(true);
            Object newInstance = constructor.newInstance((Object[]) null);
            Method declaredMethod = display.getClass().getDeclaredMethod("getDisplayInfo", new Class[]{newInstance.getClass()});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(display, new Object[]{newInstance});
            Field declaredField = newInstance.getClass().getDeclaredField("displayCutout");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(newInstance);
            if (obj instanceof DisplayCutout) {
                return (DisplayCutout) obj;
            }
        } catch (ClassNotFoundException e) {
            Log.w(TAG, e);
        } catch (NoSuchMethodException e7) {
            Log.w(TAG, e7);
        } catch (NoSuchFieldException e8) {
            Log.w(TAG, e8);
        } catch (IllegalAccessException e9) {
            Log.w(TAG, e9);
        } catch (InvocationTargetException e10) {
            Log.w(TAG, e10);
        } catch (InstantiationException e11) {
            Log.w(TAG, e11);
        }
        return null;
    }

    private final int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private final void getRectSizeFromDisplay(Activity activity, Rect rect) {
        activity.getWindowManager().getDefaultDisplay().getRectSize(rect);
    }

    public WindowMetrics computeCurrentWindowMetrics(Activity activity) {
        j.e(activity, "activity");
        return new WindowMetrics(ActivityCompatHelperApi30.INSTANCE.currentWindowBounds(activity));
    }

    public WindowMetrics computeMaximumWindowMetrics(Activity activity) {
        j.e(activity, "activity");
        return new WindowMetrics(ActivityCompatHelperApi30.INSTANCE.maximumWindowBounds(activity));
    }

    public final Rect computeWindowBoundsIceCreamSandwich$window_release(Activity activity) {
        int i2;
        j.e(activity, "activity");
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        j.d(defaultDisplay, "defaultDisplay");
        Point realSizeForDisplay$window_release = getRealSizeForDisplay$window_release(defaultDisplay);
        Rect rect = new Rect();
        int i7 = realSizeForDisplay$window_release.x;
        if (i7 == 0 || (i2 = realSizeForDisplay$window_release.y) == 0) {
            defaultDisplay.getRectSize(rect);
            return rect;
        }
        rect.right = i7;
        rect.bottom = i2;
        return rect;
    }

    public final Rect computeWindowBoundsN$window_release(Activity activity) {
        j.e(activity, "activity");
        Rect rect = new Rect();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getRectSize(rect);
        if (!ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
            Point realSizeForDisplay$window_release = getRealSizeForDisplay$window_release(defaultDisplay);
            int navigationBarHeight = getNavigationBarHeight(activity);
            int i2 = rect.bottom;
            if (i2 + navigationBarHeight == realSizeForDisplay$window_release.y) {
                rect.bottom = i2 + navigationBarHeight;
                return rect;
            }
            int i7 = rect.right;
            if (i7 + navigationBarHeight == realSizeForDisplay$window_release.x) {
                rect.right = i7 + navigationBarHeight;
            }
        }
        return rect;
    }

    public final Rect computeWindowBoundsP$window_release(Activity activity) {
        DisplayCutout cutoutForDisplay;
        j.e(activity, "activity");
        Rect rect = new Rect();
        Configuration configuration = activity.getResources().getConfiguration();
        try {
            Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(configuration);
            if (ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
                Object invoke = obj.getClass().getDeclaredMethod("getBounds", (Class[]) null).invoke(obj, (Object[]) null);
                if (invoke != null) {
                    rect.set((Rect) invoke);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
                }
            } else {
                Object invoke2 = obj.getClass().getDeclaredMethod("getAppBounds", (Class[]) null).invoke(obj, (Object[]) null);
                if (invoke2 != null) {
                    rect.set((Rect) invoke2);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
                }
            }
        } catch (NoSuchFieldException e) {
            Log.w(TAG, e);
            getRectSizeFromDisplay(activity, rect);
        } catch (NoSuchMethodException e7) {
            Log.w(TAG, e7);
            getRectSizeFromDisplay(activity, rect);
        } catch (IllegalAccessException e8) {
            Log.w(TAG, e8);
            getRectSizeFromDisplay(activity, rect);
        } catch (InvocationTargetException e9) {
            Log.w(TAG, e9);
            getRectSizeFromDisplay(activity, rect);
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        DisplayCompatHelperApi17 displayCompatHelperApi17 = DisplayCompatHelperApi17.INSTANCE;
        j.d(defaultDisplay, "currentDisplay");
        displayCompatHelperApi17.getRealSize(defaultDisplay, point);
        ActivityCompatHelperApi24 activityCompatHelperApi24 = ActivityCompatHelperApi24.INSTANCE;
        if (!activityCompatHelperApi24.isInMultiWindowMode(activity)) {
            int navigationBarHeight = getNavigationBarHeight(activity);
            int i2 = rect.bottom;
            if (i2 + navigationBarHeight == point.y) {
                rect.bottom = i2 + navigationBarHeight;
            } else {
                int i7 = rect.right;
                if (i7 + navigationBarHeight == point.x) {
                    rect.right = i7 + navigationBarHeight;
                } else if (rect.left == navigationBarHeight) {
                    rect.left = 0;
                }
            }
        }
        if ((rect.width() < point.x || rect.height() < point.y) && !activityCompatHelperApi24.isInMultiWindowMode(activity) && (cutoutForDisplay = getCutoutForDisplay(defaultDisplay)) != null) {
            int i8 = rect.left;
            DisplayCompatHelperApi28 displayCompatHelperApi28 = DisplayCompatHelperApi28.INSTANCE;
            if (i8 == displayCompatHelperApi28.safeInsetLeft(cutoutForDisplay)) {
                rect.left = 0;
            }
            if (point.x - rect.right == displayCompatHelperApi28.safeInsetRight(cutoutForDisplay)) {
                rect.right = displayCompatHelperApi28.safeInsetRight(cutoutForDisplay) + rect.right;
            }
            if (rect.top == displayCompatHelperApi28.safeInsetTop(cutoutForDisplay)) {
                rect.top = 0;
            }
            if (point.y - rect.bottom == displayCompatHelperApi28.safeInsetBottom(cutoutForDisplay)) {
                rect.bottom = displayCompatHelperApi28.safeInsetBottom(cutoutForDisplay) + rect.bottom;
            }
        }
        return rect;
    }

    public final Rect computeWindowBoundsQ$window_release(Activity activity) {
        j.e(activity, "activity");
        Configuration configuration = activity.getResources().getConfiguration();
        try {
            Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(configuration);
            Object invoke = obj.getClass().getDeclaredMethod("getBounds", (Class[]) null).invoke(obj, (Object[]) null);
            if (invoke != null) {
                return new Rect((Rect) invoke);
            }
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
        } catch (NoSuchFieldException e) {
            Log.w(TAG, e);
            return computeWindowBoundsP$window_release(activity);
        } catch (NoSuchMethodException e7) {
            Log.w(TAG, e7);
            return computeWindowBoundsP$window_release(activity);
        } catch (IllegalAccessException e8) {
            Log.w(TAG, e8);
            return computeWindowBoundsP$window_release(activity);
        } catch (InvocationTargetException e9) {
            Log.w(TAG, e9);
            return computeWindowBoundsP$window_release(activity);
        }
    }

    public final Point getRealSizeForDisplay$window_release(Display display) {
        j.e(display, "display");
        Point point = new Point();
        DisplayCompatHelperApi17.INSTANCE.getRealSize(display, point);
        return point;
    }
}
