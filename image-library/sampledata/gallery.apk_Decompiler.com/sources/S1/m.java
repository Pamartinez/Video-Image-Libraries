package S1;

import A.a;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.sec.android.gallery3d.R;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class m {
    public static int a(int i2) {
        return (int) (((float) i2) * Resources.getSystem().getDisplayMetrics().density);
    }

    public static float b(Context context) {
        j.e(context, "context");
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT < 35) {
            return ResourcesCompat.getFloat(resources, R.dimen.sesl_appbar_height_proportion);
        }
        Object systemService = context.getSystemService("window");
        j.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        WindowMetrics currentWindowMetrics = ((WindowManager) systemService).getCurrentWindowMetrics();
        j.d(currentWindowMetrics, "wm.currentWindowMetrics");
        int height = currentWindowMetrics.getBounds().height();
        float a7 = TypedValue.deriveDimension(1, (float) height, displayMetrics);
        Log.d("SeslAppBarHelper", "fullWindowHeight(dp)=" + a7 + ", fullWindowHeight(px)=" + height + ", screenHeightDp=" + context.getResources().getConfiguration().screenHeightDp);
        StringBuilder sb2 = new StringBuilder("orientation=");
        sb2.append(configuration.orientation);
        sb2.append(", fullWindowHeightDp=");
        sb2.append(a7);
        Log.d("SeslAppBarHelper", sb2.toString());
        if (configuration.orientation == 2) {
            if (a7 < 580.0f) {
                return 0.0f;
            }
            if (a7 < 640.0f) {
                return 0.51f;
            }
            if (a7 < 670.0f) {
                return 0.475f;
            }
            if (a7 < 710.0f) {
                return 0.45f;
            }
            if (a7 < 750.0f) {
                return 0.425f;
            }
            if (a7 < 800.0f) {
                return 0.4f;
            }
            if (a7 < 1080.0f) {
                return 0.37f;
            }
            return 0.27f;
        } else if (a7 < 639.0f) {
            return 0.0f;
        } else {
            if (a7 < 696.0f) {
                return 0.48f;
            }
            if (a7 < 780.0f) {
                return 0.43f;
            }
            if (a7 < 960.0f) {
                return 0.38f;
            }
            return 0.305f;
        }
    }

    public static int c(ViewGroup viewGroup) {
        Insets insets;
        Context context = viewGroup.getContext();
        if (Build.VERSION.SDK_INT < 35) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        Object systemService = context.getSystemService("window");
        j.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        WindowMetrics currentWindowMetrics = ((WindowManager) systemService).getCurrentWindowMetrics();
        j.d(currentWindowMetrics, "wm.currentWindowMetrics");
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(viewGroup);
        if (rootWindowInsets == null || (insets = rootWindowInsets.getInsets(WindowInsetsCompat.Type.systemBars())) == null) {
            insets = Insets.NONE;
        }
        j.d(insets, "rootInsets?.getInsets(Wi…temBars()) ?: Insets.NONE");
        int i2 = insets.top;
        int i7 = insets.bottom;
        int height = (currentWindowMetrics.getBounds().height() - i2) - i7;
        StringBuilder h5 = a.h(height, i2, "screenHeight(px)=", ", status=", ", navi=");
        h5.append(i7);
        Log.d("SeslAppBarHelper", h5.toString());
        return height;
    }
}
