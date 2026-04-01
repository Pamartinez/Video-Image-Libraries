package androidx.picker.util;

import android.content.Context;
import android.graphics.Typeface;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.util.SeslMisc;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslPickerBasicUtils {
    public static Typeface getOpenThemeTypeface(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), "theme_font_clock");
        if (string != null) {
            try {
                if (!TextUtils.isEmpty(string) && SeslMisc.isLightTheme(context)) {
                    return Typeface.createFromFile(string);
                }
            } catch (Exception unused) {
                Log.e("SeslPickerBasicUtils", "Open Theme Font not found");
            }
        }
        return null;
    }
}
