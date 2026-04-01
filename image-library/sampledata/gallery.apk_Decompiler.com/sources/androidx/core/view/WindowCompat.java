package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WindowCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api30Impl {
        public static void setDecorFitsSystemWindows(Window window, boolean z) {
            int i2;
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            if (z) {
                i2 = systemUiVisibility & -257;
            } else {
                i2 = systemUiVisibility | 256;
            }
            decorView.setSystemUiVisibility(i2);
            window.setDecorFitsSystemWindows(z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api35Impl {
        public static void setDecorFitsSystemWindows(Window window, boolean z) {
            window.setDecorFitsSystemWindows(z);
        }
    }

    public static WindowInsetsControllerCompat getInsetsController(Window window, View view) {
        return new WindowInsetsControllerCompat(window, view);
    }

    public static void setDecorFitsSystemWindows(Window window, boolean z) {
        if (Build.VERSION.SDK_INT >= 35) {
            Api35Impl.setDecorFitsSystemWindows(window, z);
        } else {
            Api30Impl.setDecorFitsSystemWindows(window, z);
        }
    }
}
