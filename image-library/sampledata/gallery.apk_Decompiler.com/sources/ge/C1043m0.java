package ge;

import L2.a;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import t1.C0280e;

/* renamed from: ge.m0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1043m0 {

    /* renamed from: a  reason: collision with root package name */
    public static final long f4533a = TimeUnit.SECONDS.toNanos(1);

    public static void a(List list) {
        int i2 = 0;
        while (i2 < list.size()) {
            if (list.get(i2) instanceof Map) {
                i2++;
            } else {
                throw new ClassCastException(String.format(Locale.US, "value %s for idx %d in %s is not object", new Object[]{list.get(i2), Integer.valueOf(i2), list}));
            }
        }
    }

    public static Boolean b(String str, Map map) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not Boolean", new Object[]{obj, str, map}));
    }

    public static List c(String str, Map map) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof List) {
            return (List) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not List", new Object[]{obj, str, map}));
    }

    public static List d(String str, Map map) {
        List c5 = c(str, map);
        if (c5 == null) {
            return null;
        }
        int i2 = 0;
        while (i2 < c5.size()) {
            if (c5.get(i2) instanceof String) {
                i2++;
            } else {
                throw new ClassCastException(String.format(Locale.US, "value '%s' for idx %d in '%s' is not string", new Object[]{c5.get(i2), Integer.valueOf(i2), c5}));
            }
        }
        return c5;
    }

    public static Double e(String str, Map map) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf(Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not a double", new Object[]{obj, str}));
            }
        } else {
            throw new IllegalArgumentException(String.format("value '%s' for key '%s' in '%s' is not a number", new Object[]{obj, str, map}));
        }
    }

    public static Integer f(String str, Map map) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Double) {
            Double d = (Double) obj;
            int intValue = d.intValue();
            if (((double) intValue) == d.doubleValue()) {
                return Integer.valueOf(intValue);
            }
            throw new ClassCastException("Number expected to be integer: " + d);
        } else if (obj instanceof String) {
            try {
                return Integer.valueOf(Integer.parseInt((String) obj));
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not an integer", new Object[]{obj, str}));
            }
        } else {
            throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not an integer", new Object[]{obj, str}));
        }
    }

    public static Map g(String str, Map map) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Map) {
            return (Map) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not object", new Object[]{obj, str, map}));
    }

    public static String h(String str, Map map) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not String", new Object[]{obj, str, map}));
    }

    public static Long i(String str, Map map) {
        String h5 = h(str, map);
        if (h5 == null) {
            return null;
        }
        try {
            return Long.valueOf(k(h5));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static long j(int i2, long j2) {
        int i7;
        boolean z;
        long j3 = (long) i2;
        long j8 = f4533a;
        if (j3 <= (-j8) || j3 >= j8) {
            j2 = a.h(j2, j3 / j8);
            i2 = (int) (j3 % j8);
        }
        if (j2 > 0 && i2 < 0) {
            i2 = (int) (((long) i2) + j8);
            j2--;
        }
        if (j2 < 0 && i2 > 0) {
            i2 = (int) (((long) i2) - j8);
            j2++;
        }
        if (j2 >= -315576000000L && j2 <= 315576000000L) {
            long j10 = (long) i2;
            if (j10 >= -999999999 && j10 < j8 && ((j2 >= 0 && i2 >= 0) || (i7 <= 0 && i2 <= 0))) {
                long nanos = TimeUnit.SECONDS.toNanos(j2);
                long j11 = (long) i2;
                long j12 = nanos + j11;
                int i8 = ((j11 ^ nanos) > 0 ? 1 : ((j11 ^ nanos) == 0 ? 0 : -1));
                boolean z3 = false;
                if (i8 < 0) {
                    z = true;
                } else {
                    z = false;
                }
                if ((nanos ^ j12) >= 0) {
                    z3 = true;
                }
                if (z || z3) {
                    return j12;
                }
                return ((j12 >>> 63) ^ 1) + Long.MAX_VALUE;
            }
        }
        throw new IllegalArgumentException("Duration is not valid. See proto definition for valid values. Seconds (" + j2 + ") must be in range [-315,576,000,000, +315,576,000,000]. Nanos (" + i2 + ") must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds");
    }

    public static long k(String str) {
        boolean z;
        String str2;
        int i2;
        if (str.isEmpty() || str.charAt(str.length() - 1) != 's') {
            throw new ParseException("Invalid duration string: ".concat(str), 0);
        }
        if (str.charAt(0) == '-') {
            str = str.substring(1);
            z = true;
        } else {
            z = false;
        }
        String d = C0280e.d(1, 0, str);
        int indexOf = d.indexOf(46);
        if (indexOf != -1) {
            str2 = d.substring(indexOf + 1);
            d = d.substring(0, indexOf);
        } else {
            str2 = "";
        }
        long parseLong = Long.parseLong(d);
        if (str2.isEmpty()) {
            i2 = 0;
        } else {
            i2 = 0;
            for (int i7 = 0; i7 < 9; i7++) {
                i2 *= 10;
                if (i7 < str2.length()) {
                    if (str2.charAt(i7) < '0' || str2.charAt(i7) > '9') {
                        throw new ParseException("Invalid nanoseconds.", 0);
                    }
                    i2 = (str2.charAt(i7) - '0') + i2;
                }
            }
        }
        if (parseLong >= 0) {
            if (z) {
                parseLong = -parseLong;
                i2 = -i2;
            }
            try {
                return j(i2, parseLong);
            } catch (IllegalArgumentException unused) {
                throw new ParseException("Duration value is out of range.", 0);
            }
        } else {
            throw new ParseException("Invalid duration string: ".concat(str), 0);
        }
    }
}
