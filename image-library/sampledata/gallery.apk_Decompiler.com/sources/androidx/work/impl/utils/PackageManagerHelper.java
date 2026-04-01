package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.work.Logger;
import com.samsung.scsp.media.api.constant.MediaApiContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PackageManagerHelper {
    private static final String TAG = Logger.tagWithPrefix("PackageManagerHelper");

    private static int getComponentEnabledSetting(Context context, String str) {
        return context.getPackageManager().getComponentEnabledSetting(new ComponentName(context, str));
    }

    private static boolean isComponentEnabled(int i2, boolean z) {
        if (i2 == 0) {
            return z;
        }
        if (i2 == 1) {
            return true;
        }
        return false;
    }

    public static void setComponentEnabled(Context context, Class<?> cls, boolean z) {
        int i2;
        String str = "disabled";
        try {
            if (z == isComponentEnabled(getComponentEnabledSetting(context, cls.getName()), false)) {
                Logger.get().debug(TAG, "Skipping component enablement for ".concat(cls.getName()));
                return;
            }
            PackageManager packageManager = context.getPackageManager();
            ComponentName componentName = new ComponentName(context, cls.getName());
            if (z) {
                i2 = 1;
            } else {
                i2 = 2;
            }
            packageManager.setComponentEnabledSetting(componentName, i2, 1);
            Logger logger = Logger.get();
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(cls.getName());
            sb2.append(" ");
            sb2.append(z ? MediaApiContract.Parameter.ENABLED : str);
            logger.debug(str2, sb2.toString());
        } catch (Exception e) {
            Logger logger2 = Logger.get();
            String str3 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(cls.getName());
            sb3.append("could not be ");
            if (z) {
                str = MediaApiContract.Parameter.ENABLED;
            }
            sb3.append(str);
            logger2.debug(str3, sb3.toString(), e);
        }
    }
}
