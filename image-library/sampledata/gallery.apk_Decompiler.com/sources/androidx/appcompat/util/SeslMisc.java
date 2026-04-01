package androidx.appcompat.util;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.TypedValue;
import androidx.appcompat.R$attr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslMisc {
    public static boolean isDefaultTheme(Context context) {
        return TextUtils.isEmpty(Settings.System.getString(context.getContentResolver(), "current_sec_active_themepackage"));
    }

    public static boolean isLightTheme(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.isLightTheme, typedValue, true);
        if (typedValue.data != 0) {
            return true;
        }
        return false;
    }
}
