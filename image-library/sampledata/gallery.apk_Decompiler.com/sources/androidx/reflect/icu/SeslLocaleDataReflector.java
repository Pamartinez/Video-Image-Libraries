package androidx.reflect.icu;

import android.util.Log;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;
import java.text.DateFormatSymbols;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslLocaleDataReflector {
    private static String mClassName = "libcore.icu.LocaleData";
    private static String mDateFormatSymbolsClass = "android.icu.text.DateFormatSymbols";
    private static String mSemClassName = "com.samsung.sesl.icu.SemLocaleData";
    private static String mSemDateFormatSymbolsClass = "com.samsung.sesl.icu.SemDateFormatSymbols";

    public static Object get(Locale locale) {
        Object invoke;
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mSemClassName, "get", (Class<?>[]) new Class[]{Locale.class});
        if (declaredMethod == null || (invoke = SeslBaseReflector.invoke((Object) null, declaredMethod, locale)) == null || !invoke.getClass().getName().equals(mClassName)) {
            return null;
        }
        return invoke;
    }

    public static String[] getAmpmNarrowStrings(Object obj) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mSemDateFormatSymbolsClass, "getAmpmNarrowStrings", (Class<?>[]) new Class[]{SeslBaseReflector.getClass(mDateFormatSymbolsClass)});
        Object obj2 = null;
        if (declaredMethod != null) {
            obj2 = SeslBaseReflector.invoke((Object) null, declaredMethod, obj);
        }
        if (obj2 instanceof String[]) {
            return (String[]) obj2;
        }
        Log.e("SeslLocaleDataReflector", "amPm narrow strings failed. Use getAmPmStrings for ampm");
        return new DateFormatSymbols().getAmPmStrings();
    }

    public static String[] getField_amPm(Object obj) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mSemClassName, "getAmPm", (Class<?>[]) new Class[]{SeslBaseReflector.getClass(mClassName)});
        Object obj2 = null;
        if (declaredMethod != null) {
            obj2 = SeslBaseReflector.invoke((Object) null, declaredMethod, obj);
        }
        if (obj2 instanceof String[]) {
            return (String[]) obj2;
        }
        Log.e("SeslLocaleDataReflector", "amPm failed. Use DateFormatSymbols for ampm");
        return new DateFormatSymbols().getAmPmStrings();
    }

    public static String getField_narrowAm(Object obj) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mSemClassName, "getNarrowAm", (Class<?>[]) new Class[]{SeslBaseReflector.getClass(mClassName)});
        Object obj2 = null;
        if (declaredMethod != null) {
            obj2 = SeslBaseReflector.invoke((Object) null, declaredMethod, obj);
        }
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        return "Am";
    }

    public static String getField_narrowPm(Object obj) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mSemClassName, "getNarrowPm", (Class<?>[]) new Class[]{SeslBaseReflector.getClass(mClassName)});
        Object obj2 = null;
        if (declaredMethod != null) {
            obj2 = SeslBaseReflector.invoke((Object) null, declaredMethod, obj);
        }
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        return "Pm";
    }
}
