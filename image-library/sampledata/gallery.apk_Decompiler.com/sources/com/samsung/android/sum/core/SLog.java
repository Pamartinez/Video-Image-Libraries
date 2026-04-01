package com.samsung.android.sum.core;

import android.util.Log;
import com.samsung.android.sum.core.DebugUtils;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SLog {
    private static int DEBUG_LEVEL = 0;
    private static final int MAX_TAG_LEN = 32;
    private static final String TAG = "Sum";

    static {
        init();
    }

    public static void d(String str, String str2) {
        if (isLoggable(3)) {
            Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }

    public static void i(String str, String str2) {
        Log.i(str, str2);
    }

    public static void init() {
        String str;
        String str2;
        String str3;
        int levelCode = DebugUtils.SumDebugLevel.of(4).getLevelCode();
        DEBUG_LEVEL = levelCode;
        String nameOf = DebugUtils.SumDebugLevel.nameOf(levelCode);
        Integer valueOf = Integer.valueOf(DEBUG_LEVEL);
        String nameOf2 = DebugUtils.SystemDebugLevel.nameOf(DebugUtils.SYSTEM_DEBUG_LEVEL);
        String str4 = "off";
        String str5 = "on";
        if (DebugUtils.TRACE_BUFFER) {
            str = str4;
            str4 = str5;
        } else {
            str = str4;
        }
        if (DebugUtils.TRACE_MESSAGE) {
            str2 = str5;
        } else {
            str2 = str5;
            str5 = str;
        }
        if (DebugUtils.TRACE_BUFFER_CHANNEL) {
            str3 = str2;
        } else {
            str3 = str2;
            str2 = str;
        }
        if (!DebugUtils.TRACE_MESSAGE_CHANNEL) {
            str3 = str;
        }
        i(TAG, Def.fmtstr("DEBUG_LEVEL is set as %s(%d)[system: %s], trace[buffer:%s, message:%s, buffer-channel:%s, message-channel: %s]", nameOf, valueOf, nameOf2, str4, str5, str2, str3));
    }

    private static boolean isLoggable(int i2) {
        if (i2 >= DEBUG_LEVEL) {
            return true;
        }
        return false;
    }

    public static void setDebugLevel(DebugUtils.SumDebugLevel sumDebugLevel) {
        DEBUG_LEVEL = sumDebugLevel.getLevelCode();
        i(TAG, Def.fmtstr("DEBUG_LEVEL is updated as %s(%d)[system: %s]", sumDebugLevel.getLevelName(), Integer.valueOf(DEBUG_LEVEL), DebugUtils.SystemDebugLevel.nameOf(DebugUtils.SYSTEM_DEBUG_LEVEL)));
    }

    public static String tagOf(Class<?> cls) {
        String concat = "Sum@".concat(cls.getSimpleName());
        if (concat.length() <= 32) {
            return concat;
        }
        w("Tag(%s)'s length(%d) is longer than limit(%d).", concat, Integer.valueOf(concat.length()), 32);
        return concat.substring(0, 32);
    }

    public static void v(String str, String str2) {
        if (isLoggable(2)) {
            Log.v(str, str2);
        }
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
    }

    public static void e(Tag tag, String str) {
        String primary = tag.getPrimary();
        e(primary, tag.getSecondary() + str);
    }

    public static void i(Tag tag, String str) {
        String primary = tag.getPrimary();
        i(primary, tag.getSecondary() + str);
    }

    public static void w(Tag tag, String str) {
        String primary = tag.getPrimary();
        w(primary, tag.getSecondary() + str);
    }

    public static void d(Tag tag, String str) {
        String primary = tag.getPrimary();
        d(primary, tag.getSecondary() + str);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    public static void i(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    public static void v(Tag tag, String str) {
        String primary = tag.getPrimary();
        v(primary, tag.getSecondary() + str);
    }

    public static void w(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    public static void d(String str, String str2, Consumer<Exception> consumer) {
        try {
            d(str, str2);
        } catch (Exception e) {
            consumer.accept(e);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        Log.e(str, String.format(Locale.ROOT, str2, objArr));
    }

    public static void v(String str, String str2, Consumer<Exception> consumer) {
        try {
            v(str, str2);
        } catch (Exception e) {
            consumer.accept(e);
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        Log.w(str, String.format(Locale.ROOT, str2, objArr));
    }

    public static void d(String str, Supplier<String> supplier) {
        if (isLoggable(3)) {
            Log.d(str, supplier.get());
        }
    }

    public static void v(String str, Supplier<String> supplier) {
        if (isLoggable(2)) {
            Log.v(str, supplier.get());
        }
    }

    public static void d(String str, Supplier<String> supplier, Consumer<Exception> consumer) {
        try {
            d(str, supplier);
        } catch (Exception e) {
            consumer.accept(e);
        }
    }

    public static void v(String str, Supplier<String> supplier, Consumer<Exception> consumer) {
        try {
            v(str, supplier);
        } catch (Exception e) {
            consumer.accept(e);
        }
    }

    public static void d(Tag tag, Supplier<String> supplier, Consumer<Exception> consumer) {
        String primary = tag.getPrimary();
        d(primary, tag.getSecondary() + supplier);
    }

    public static void v(String str, String str2, Throwable th) {
        if (isLoggable(2)) {
            Log.v(str, str2, th);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (isLoggable(3)) {
            Log.d(str, str2, th);
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        if (isLoggable(2)) {
            Log.v(str, String.format(Locale.ROOT, str2, objArr));
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (isLoggable(3)) {
            Log.d(str, String.format(Locale.ROOT, str2, objArr));
        }
    }
}
