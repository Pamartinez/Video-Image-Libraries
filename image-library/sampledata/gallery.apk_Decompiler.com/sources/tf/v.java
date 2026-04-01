package Tf;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class v extends u {
    public static boolean o0(String str, String str2) {
        j.e(str, "<this>");
        j.e(str2, "suffix");
        return str.endsWith(str2);
    }

    public static boolean p0(String str, String str2, boolean z) {
        if (str == null) {
            if (str2 == null) {
                return true;
            }
            return false;
        } else if (!z) {
            return str.equals(str2);
        } else {
            return str.equalsIgnoreCase(str2);
        }
    }

    public static boolean q0(String str, int i2, boolean z, String str2, int i7, int i8) {
        j.e(str, "<this>");
        j.e(str2, "other");
        if (!z) {
            return str.regionMatches(i2, str2, i7, i8);
        }
        return str.regionMatches(z, i2, str2, i7, i8);
    }

    public static String r0(String str, char c5, char c6) {
        j.e(str, "<this>");
        String replace = str.replace(c5, c6);
        j.d(replace, "replace(...)");
        return replace;
    }

    public static String s0(String str, String str2, String str3) {
        j.e(str, "<this>");
        int y0 = n.y0(str, str2, 0, false);
        if (y0 < 0) {
            return str;
        }
        int length = str2.length();
        int i2 = 1;
        if (length >= 1) {
            i2 = length;
        }
        int length2 = str3.length() + (str.length() - length);
        if (length2 >= 0) {
            StringBuilder sb2 = new StringBuilder(length2);
            int i7 = 0;
            do {
                sb2.append(str, i7, y0);
                sb2.append(str3);
                i7 = y0 + length;
                if (y0 >= str.length() || (y0 = n.y0(str, str2, y0 + i2, false)) <= 0) {
                    sb2.append(str, i7, str.length());
                    String sb3 = sb2.toString();
                    j.d(sb3, "toString(...)");
                }
                sb2.append(str, i7, y0);
                sb2.append(str3);
                i7 = y0 + length;
                break;
            } while ((y0 = n.y0(str, str2, y0 + i2, false)) <= 0);
            sb2.append(str, i7, str.length());
            String sb32 = sb2.toString();
            j.d(sb32, "toString(...)");
            return sb32;
        }
        throw new OutOfMemoryError();
    }

    public static boolean t0(String str, String str2) {
        j.e(str, "<this>");
        j.e(str2, "prefix");
        return str.startsWith(str2);
    }
}
