package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.InputDevice;
import android.view.ViewConfiguration;
import androidx.core.util.Supplier;
import java.util.Objects;
import x.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewConfigurationCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        public static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledHorizontalScrollFactor();
        }

        public static float getScaledVerticalScrollFactor(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledVerticalScrollFactor();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static int getScaledHoverSlop(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledHoverSlop();
        }

        public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api34Impl {
        public static int getScaledMaximumFlingVelocity(ViewConfiguration viewConfiguration, int i2, int i7, int i8) {
            return viewConfiguration.getScaledMaximumFlingVelocity(i2, i7, i8);
        }

        public static int getScaledMinimumFlingVelocity(ViewConfiguration viewConfiguration, int i2, int i7, int i8) {
            return viewConfiguration.getScaledMinimumFlingVelocity(i2, i7, i8);
        }
    }

    private static int getCompatFlingVelocityThreshold(Resources resources, int i2, Supplier<Integer> supplier, int i7) {
        int dimensionPixelSize;
        if (i2 == -1) {
            return supplier.get().intValue();
        }
        if (i2 == 0 || (dimensionPixelSize = resources.getDimensionPixelSize(i2)) < 0) {
            return i7;
        }
        return dimensionPixelSize;
    }

    private static int getPlatformResId(Resources resources, String str, String str2) {
        return resources.getIdentifier(str, str2, "android");
    }

    private static int getPreApi34MaximumFlingVelocityResId(Resources resources, int i2, int i7) {
        if (i2 == 4194304 && i7 == 26) {
            return getPlatformResId(resources, "config_viewMaxRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    private static int getPreApi34MinimumFlingVelocityResId(Resources resources, int i2, int i7) {
        if (i2 == 4194304 && i7 == 26) {
            return getPlatformResId(resources, "config_viewMinRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    public static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration, Context context) {
        return Api26Impl.getScaledHorizontalScrollFactor(viewConfiguration);
    }

    public static int getScaledHoverSlop(ViewConfiguration viewConfiguration) {
        return Api28Impl.getScaledHoverSlop(viewConfiguration);
    }

    public static int getScaledMaximumFlingVelocity(Context context, ViewConfiguration viewConfiguration, int i2, int i7, int i8) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getScaledMaximumFlingVelocity(viewConfiguration, i2, i7, i8);
        }
        if (!isInputDeviceInfoValid(i2, i7, i8)) {
            return Integer.MIN_VALUE;
        }
        Resources resources = context.getResources();
        int preApi34MaximumFlingVelocityResId = getPreApi34MaximumFlingVelocityResId(resources, i8, i7);
        Objects.requireNonNull(viewConfiguration);
        return getCompatFlingVelocityThreshold(resources, preApi34MaximumFlingVelocityResId, new e(viewConfiguration, 0), Integer.MIN_VALUE);
    }

    public static int getScaledMinimumFlingVelocity(Context context, ViewConfiguration viewConfiguration, int i2, int i7, int i8) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getScaledMinimumFlingVelocity(viewConfiguration, i2, i7, i8);
        }
        if (!isInputDeviceInfoValid(i2, i7, i8)) {
            return Integer.MAX_VALUE;
        }
        Resources resources = context.getResources();
        int preApi34MinimumFlingVelocityResId = getPreApi34MinimumFlingVelocityResId(resources, i8, i7);
        Objects.requireNonNull(viewConfiguration);
        return getCompatFlingVelocityThreshold(resources, preApi34MinimumFlingVelocityResId, new e(viewConfiguration, 1), Integer.MAX_VALUE);
    }

    public static float getScaledVerticalScrollFactor(ViewConfiguration viewConfiguration, Context context) {
        return Api26Impl.getScaledVerticalScrollFactor(viewConfiguration);
    }

    private static boolean isInputDeviceInfoValid(int i2, int i7, int i8) {
        InputDevice device = InputDevice.getDevice(i2);
        if (device == null || device.getMotionRange(i7, i8) == null) {
            return false;
        }
        return true;
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration, Context context) {
        return Api28Impl.shouldShowMenuShortcutsWhenKeyboardPresent(viewConfiguration);
    }
}
