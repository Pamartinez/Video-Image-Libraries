package androidx.core.util;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import androidx.core.oneui.OneUI;
import androidx.reflect.content.res.SeslConfigurationReflector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslDisplayUtils {
    public static int getEdgeArea(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "active_edge_area", 1);
    }

    public static int getPinnedEdgeWidth(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "pinned_edge_width");
        } catch (Settings.SettingNotFoundException e) {
            Log.w("SeslDisplayUtils", "Failed get EdgeWidth " + e.toString());
            return 0;
        }
    }

    public static boolean isDesktopWindowing(Context context) {
        for (Display flags : ((DisplayManager) context.getSystemService("display")).getDisplays()) {
            if ((flags.getFlags() & 131072) != 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDexEnabled(Context context) {
        if (OneUI.isGreaterOrEqual(OneUI.Version.ONEUI_8_0)) {
            return isDesktopWindowing(context);
        }
        return SeslConfigurationReflector.isDexEnabled(context.getResources().getConfiguration());
    }

    public static boolean isPinEdgeEnabled(Context context) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), "panel_mode", 0) == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.w("SeslDisplayUtils", "Failed get panel mode " + e.toString());
            return false;
        }
    }
}
