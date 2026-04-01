package androidx.reflect.view;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.PointerIcon;
import android.view.View;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslViewReflector {
    private static final Class<?> mClass = View.class;

    public static void clearAccessibilityFocus(View view) {
        Method method = SeslBaseReflector.getMethod(mClass, "clearAccessibilityFocus", (Class<?>[]) new Class[0]);
        if (method != null) {
            SeslBaseReflector.invoke(view, method, new Object[0]);
        }
    }

    public static int getField_mPaddingLeft(View view) {
        Field declaredField = SeslBaseReflector.getDeclaredField(mClass, "mPaddingLeft");
        if (declaredField == null) {
            return 0;
        }
        Object obj = SeslBaseReflector.get(view, declaredField);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public static int getField_mPaddingRight(View view) {
        Field declaredField = SeslBaseReflector.getDeclaredField(mClass, "mPaddingRight");
        if (declaredField == null) {
            return 0;
        }
        Object obj = SeslBaseReflector.get(view, declaredField);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public static void getWindowDisplayFrame(View view, Rect rect) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "getWindowDisplayFrame", (Class<?>[]) new Class[]{Rect.class});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(view, declaredMethod, rect);
        }
    }

    public static boolean isHighContrastTextEnabled(View view) {
        Method method = SeslBaseReflector.getMethod(mClass, "semIsHighContrastTextEnabled", (Class<?>[]) new Class[0]);
        if (method != null) {
            Object invoke = SeslBaseReflector.invoke(view, method, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        }
        return false;
    }

    public static boolean isHoveringUIEnabled(View view) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "isHoveringUIEnabled", (Class<?>[]) new Class[0]);
        if (declaredMethod != null) {
            Object invoke = SeslBaseReflector.invoke(view, declaredMethod, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        }
        return false;
    }

    public static boolean isInScrollingContainer(View view) {
        Method method;
        if (Build.VERSION.SDK_INT <= 30) {
            method = SeslBaseReflector.getMethod(mClass, "isInScrollingContainer", (Class<?>[]) new Class[0]);
        } else {
            method = SeslBaseReflector.getMethod(mClass, "hidden_isInScrollingContainer", (Class<?>[]) new Class[0]);
        }
        if (method != null) {
            Object invoke = SeslBaseReflector.invoke(view, method, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        }
        return false;
    }

    public static boolean isVisibleToUser(View view) {
        return isVisibleToUser(view, (Rect) null);
    }

    public static void notifyViewAccessibilityStateChangedIfNeeded(View view, int i2) {
        Method method;
        int i7 = Build.VERSION.SDK_INT;
        Class cls = Integer.TYPE;
        if (i7 <= 30) {
            method = SeslBaseReflector.getMethod(mClass, "notifyViewAccessibilityStateChangedIfNeeded", (Class<?>[]) new Class[]{cls});
        } else {
            method = SeslBaseReflector.getMethod(mClass, "hidden_notifyViewAccessibilityStateChangedIfNeeded", (Class<?>[]) new Class[]{cls});
        }
        if (method != null) {
            SeslBaseReflector.invoke(view, method, Integer.valueOf(i2));
        }
    }

    public static boolean requestAccessibilityFocus(View view) {
        Method method = SeslBaseReflector.getMethod(mClass, "requestAccessibilityFocus", (Class<?>[]) new Class[0]);
        if (method != null) {
            Object invoke = SeslBaseReflector.invoke(view, method, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        }
        return false;
    }

    public static void resetPaddingToInitialValues(View view) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "resetPaddingToInitialValues", (Class<?>[]) new Class[0]);
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(view, declaredMethod, new Object[0]);
        }
    }

    public static void resolvePadding(View view) {
        Method method;
        if (Build.VERSION.SDK_INT <= 30) {
            method = SeslBaseReflector.getDeclaredMethod(mClass, "resolvePadding", (Class<?>[]) new Class[0]);
        } else {
            method = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_resolvePadding", (Class<?>[]) new Class[0]);
        }
        if (method != null) {
            SeslBaseReflector.invoke(view, method, new Object[0]);
        }
    }

    public static Object semGetHoverPopup(View view, boolean z) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semGetHoverPopup", (Class<?>[]) new Class[]{Boolean.TYPE});
        if (declaredMethod != null) {
            return SeslBaseReflector.invoke(view, declaredMethod, Boolean.valueOf(z));
        }
        return null;
    }

    public static int semGetHoverPopupType(View view) {
        Method method = SeslBaseReflector.getMethod(mClass, "semGetHoverPopupType", (Class<?>[]) new Class[0]);
        if (method != null) {
            Object invoke = SeslBaseReflector.invoke(view, method, new Object[0]);
            if (invoke instanceof Integer) {
                return ((Integer) invoke).intValue();
            }
        }
        return 0;
    }

    public static void semSetBlurInfo(View view, Object obj) {
        if (Build.VERSION.SDK_INT >= 31) {
            try {
                Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semSetBlurInfo", (Class<?>[]) new Class[]{Class.forName("android.view.SemBlurInfo")});
                if (declaredMethod != null) {
                    SeslBaseReflector.invoke(view, declaredMethod, obj);
                }
            } catch (ClassNotFoundException e) {
                Log.e("SeslViewReflector", "semSetBlurInfo ClassNotFoundException", e);
            }
        }
    }

    public static void semSetDirectPenInputEnabled(View view, boolean z) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semSetDirectPenInputEnabled", (Class<?>[]) new Class[]{Boolean.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(view, declaredMethod, Boolean.valueOf(z));
        }
    }

    public static void semSetHoverPopupType(View view, int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semSetHoverPopupType", (Class<?>[]) new Class[]{Integer.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(view, declaredMethod, Integer.valueOf(i2));
        }
    }

    public static void semSetPointerIcon(View view, int i2, PointerIcon pointerIcon) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semSetPointerIcon", (Class<?>[]) new Class[]{Integer.TYPE, PointerIcon.class});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(view, declaredMethod, Integer.valueOf(i2), pointerIcon);
        }
    }

    public static void semSetScrollBarBottomPadding(View view, int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "semSetScrollBarBottomPadding", (Class<?>[]) new Class[]{Integer.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(view, declaredMethod, Integer.valueOf(i2));
        }
    }

    public static void semSetScrollBarTopPadding(View view, int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "semSetScrollBarTopPadding", (Class<?>[]) new Class[]{Integer.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(view, declaredMethod, Integer.valueOf(i2));
        }
    }

    public static void setField_mPaddingLeft(View view, int i2) {
        Field declaredField = SeslBaseReflector.getDeclaredField(mClass, "mPaddingLeft");
        if (declaredField != null) {
            SeslBaseReflector.set(view, declaredField, Integer.valueOf(i2));
        }
    }

    public static void setField_mPaddingRight(View view, int i2) {
        Field declaredField = SeslBaseReflector.getDeclaredField(mClass, "mPaddingRight");
        if (declaredField != null) {
            SeslBaseReflector.set(view, declaredField, Integer.valueOf(i2));
        }
    }

    public static void setFrameContentVelocity(View view, float f) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "setFrameContentVelocity", (Class<?>[]) new Class[]{Float.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(view, declaredMethod, Float.valueOf(f));
        }
    }

    public static boolean isVisibleToUser(View view, Rect rect) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "isVisibleToUser", (Class<?>[]) new Class[]{Rect.class});
        if (declaredMethod == null) {
            return false;
        }
        Object invoke = SeslBaseReflector.invoke(view, declaredMethod, rect);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }
}
