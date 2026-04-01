package com.google.gson.internal;

import c0.C0086a;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreJava9DateFormatProvider {
    private PreJava9DateFormatProvider() {
    }

    private static String getDatePartOfDateTimePattern(int i2) {
        if (i2 == 0) {
            return "EEEE, MMMM d, yyyy";
        }
        if (i2 == 1) {
            return "MMMM d, yyyy";
        }
        if (i2 == 2) {
            return "MMM d, yyyy";
        }
        if (i2 == 3) {
            return "M/d/yy";
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Unknown DateFormat style: "));
    }

    private static String getTimePartOfDateTimePattern(int i2) {
        if (i2 == 0 || i2 == 1) {
            return "h:mm:ss a z";
        }
        if (i2 == 2) {
            return "h:mm:ss a";
        }
        if (i2 == 3) {
            return "h:mm a";
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Unknown DateFormat style: "));
    }

    public static DateFormat getUsDateTimeFormat(int i2, int i7) {
        return new SimpleDateFormat(getDatePartOfDateTimePattern(i2) + " " + getTimePartOfDateTimePattern(i7), Locale.US);
    }
}
