package androidx.core.util;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Preconditions {
    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static int checkArgumentInRange(int i2, int i7, int i8, String str) {
        if (i2 < i7) {
            Locale locale = Locale.US;
            throw new IllegalArgumentException(str + " is out of range of [" + i7 + ArcCommonLog.TAG_COMMA + i8 + "] (too low)");
        } else if (i2 <= i8) {
            return i2;
        } else {
            Locale locale2 = Locale.US;
            throw new IllegalArgumentException(str + " is out of range of [" + i7 + ArcCommonLog.TAG_COMMA + i8 + "] (too high)");
        }
    }

    public static int checkArgumentNonnegative(int i2, String str) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException(str);
    }

    public static int checkFlagsArgument(int i2, int i7) {
        if ((i2 & i7) == i2) {
            return i2;
        }
        throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i2) + ", but only 0x" + Integer.toHexString(i7) + " are allowed");
    }

    public static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static int checkArgumentNonnegative(int i2) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException();
    }

    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }
}
