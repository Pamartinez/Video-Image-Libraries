package androidx.reflect.lunarcalendar;

import android.content.Context;
import androidx.reflect.SeslBaseReflector;
import androidx.reflect.SeslPathClassReflector;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Method;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslLunarDateUtilsReflector {
    public static String buildLunarDateString(PathClassLoader pathClassLoader, Calendar calendar, Context context) {
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.android.calendar.event.widget.datetimepicker.LunarDateUtils", "buildLunarDateString", Calendar.class, Context.class);
        if (method != null) {
            Object invoke = SeslBaseReflector.invoke((Object) null, method, calendar, context);
            if (invoke instanceof String) {
                return (String) invoke;
            }
        }
        return null;
    }
}
