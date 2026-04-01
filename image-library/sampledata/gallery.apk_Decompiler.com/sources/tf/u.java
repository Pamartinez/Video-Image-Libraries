package Tf;

import c0.C0086a;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class u extends t {
    public static final void m0(String str) {
        throw new NumberFormatException(C0086a.h('\'', "Invalid number format: '", str));
    }

    public static Integer n0(String str) {
        int i2;
        boolean z;
        int i7;
        j.e(str, "<this>");
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i8 = 0;
        char charAt = str.charAt(0);
        int i10 = -2147483647;
        if (j.f(charAt, 48) < 0) {
            i2 = 1;
            if (length == 1) {
                return null;
            }
            if (charAt == '+') {
                z = false;
            } else if (charAt != '-') {
                return null;
            } else {
                i10 = Integer.MIN_VALUE;
                z = true;
            }
        } else {
            z = false;
            i2 = 0;
        }
        int i11 = -59652323;
        while (i2 < length) {
            int digit = Character.digit(str.charAt(i2), 10);
            if (digit < 0) {
                return null;
            }
            if ((i8 < i11 && (i11 != -59652323 || i8 < (i11 = i10 / 10))) || (i7 = i8 * 10) < i10 + digit) {
                return null;
            }
            i8 = i7 - digit;
            i2++;
        }
        if (z) {
            return Integer.valueOf(i8);
        }
        return Integer.valueOf(-i8);
    }
}
