package androidx.reflect.text;

import android.text.TextPaint;
import android.text.TextUtils;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslTextUtilsReflector {
    private static final Class<?> mClass = TextUtils.class;

    public static char[] semGetPrefixCharForSpan(TextPaint textPaint, CharSequence charSequence, char[] cArr) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semGetPrefixCharForSpan", (Class<?>[]) new Class[]{TextPaint.class, CharSequence.class, char[].class});
        if (declaredMethod == null) {
            return new char[0];
        }
        Object invoke = SeslBaseReflector.invoke((Object) null, declaredMethod, textPaint, charSequence, cArr);
        if (invoke instanceof char[]) {
            return (char[]) invoke;
        }
        return null;
    }
}
