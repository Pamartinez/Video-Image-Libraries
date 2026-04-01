package com.google.gson.internal.bind.util;

import com.samsung.scsp.framework.core.network.Network;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);
    private static final String UTC_ID = "UTC";

    private ISO8601Utils() {
    }

    private static boolean checkOffset(String str, int i2, char c5) {
        if (i2 >= str.length() || str.charAt(i2) != c5) {
            return false;
        }
        return true;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    private static int indexOfNonDigit(String str, int i2) {
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (charAt < '0' || charAt > '9') {
                return i2;
            }
            i2++;
        }
        return str.length();
    }

    private static void padInt(StringBuilder sb2, int i2, int i7) {
        String num = Integer.toString(i2);
        for (int length = i7 - num.length(); length > 0; length--) {
            sb2.append('0');
        }
        sb2.append(num);
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e5 A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01ae A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date parse(java.lang.String r19, java.text.ParsePosition r20) {
        /*
            r1 = r19
            r2 = r20
            java.lang.String r0 = "Mismatching time zone indicator: "
            java.lang.String r3 = "GMT"
            java.lang.String r4 = "00"
            java.lang.String r5 = "Invalid time zone indicator '"
            int r6 = r2.getIndex()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            int r7 = r6 + 4
            int r8 = parseInt(r1, r6, r7)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r9 = 45
            boolean r10 = checkOffset(r1, r7, r9)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r11 = 5
            if (r10 == 0) goto L_0x0021
            int r7 = r6 + 5
        L_0x0021:
            int r6 = r7 + 2
            int r10 = parseInt(r1, r7, r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            boolean r12 = checkOffset(r1, r6, r9)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r12 == 0) goto L_0x002f
            int r6 = r7 + 3
        L_0x002f:
            int r7 = r6 + 2
            int r12 = parseInt(r1, r6, r7)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r13 = 84
            boolean r13 = checkOffset(r1, r7, r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r14 = 1
            r15 = 0
            if (r13 != 0) goto L_0x0059
            int r11 = r1.length()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r11 > r7) goto L_0x0059
            java.util.GregorianCalendar r0 = new java.util.GregorianCalendar     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            int r10 = r10 - r14
            r0.<init>(r8, r10, r12)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r0.setLenient(r15)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r2.setIndex(r7)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.util.Date r0 = r0.getTime()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            return r0
        L_0x0056:
            r0 = move-exception
            goto L_0x01b6
        L_0x0059:
            r11 = 43
            r15 = 90
            if (r13 == 0) goto L_0x00dc
            int r7 = r6 + 3
            int r13 = r6 + 5
            int r7 = parseInt(r1, r7, r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r14 = 58
            boolean r17 = checkOffset(r1, r13, r14)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r17 == 0) goto L_0x0071
            int r13 = r6 + 6
        L_0x0071:
            int r6 = r13 + 2
            int r17 = parseInt(r1, r13, r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            boolean r14 = checkOffset(r1, r6, r14)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r14 == 0) goto L_0x0080
            int r13 = r13 + 3
            r6 = r13
        L_0x0080:
            int r13 = r1.length()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r13 <= r6) goto L_0x00d4
            char r13 = r1.charAt(r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r13 == r15) goto L_0x00d4
            if (r13 == r11) goto L_0x00d4
            if (r13 == r9) goto L_0x00d4
            int r13 = r6 + 2
            int r14 = parseInt(r1, r6, r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r9 = 59
            if (r14 <= r9) goto L_0x00a0
            r9 = 63
            if (r14 >= r9) goto L_0x00a0
            r14 = 59
        L_0x00a0:
            r9 = 46
            boolean r9 = checkOffset(r1, r13, r9)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r9 == 0) goto L_0x00ce
            int r9 = r6 + 3
            int r13 = r6 + 4
            int r13 = indexOfNonDigit(r1, r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            int r6 = r6 + 6
            int r6 = java.lang.Math.min(r13, r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            int r18 = parseInt(r1, r9, r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            int r6 = r6 - r9
            r9 = 1
            if (r6 == r9) goto L_0x00c5
            r9 = 2
            if (r6 == r9) goto L_0x00c2
            goto L_0x00c7
        L_0x00c2:
            int r18 = r18 * 10
            goto L_0x00c7
        L_0x00c5:
            int r18 = r18 * 100
        L_0x00c7:
            r6 = r7
            r7 = r13
            r9 = r17
            r13 = r18
            goto L_0x00df
        L_0x00ce:
            r6 = r7
            r7 = r13
            r9 = r17
            r13 = 0
            goto L_0x00df
        L_0x00d4:
            r9 = r7
            r7 = r6
            r6 = r9
            r9 = r17
        L_0x00d9:
            r13 = 0
            r14 = 0
            goto L_0x00df
        L_0x00dc:
            r6 = 0
            r9 = 0
            goto L_0x00d9
        L_0x00df:
            int r11 = r1.length()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r11 <= r7) goto L_0x01ae
            char r11 = r1.charAt(r7)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r11 != r15) goto L_0x00f3
            java.util.TimeZone r0 = TIMEZONE_UTC     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r16 = 1
            int r7 = r7 + 1
            goto L_0x017c
        L_0x00f3:
            r15 = 43
            if (r11 == r15) goto L_0x0113
            r15 = 45
            if (r11 != r15) goto L_0x00fc
            goto L_0x0113
        L_0x00fc:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r3.<init>(r5)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r3.append(r11)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.lang.String r4 = "'"
            r3.append(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.lang.String r3 = r3.toString()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r0.<init>(r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            throw r0     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
        L_0x0113:
            java.lang.String r5 = r1.substring(r7)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            int r11 = r5.length()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r15 = 5
            if (r11 < r15) goto L_0x011f
            goto L_0x0123
        L_0x011f:
            java.lang.String r5 = r5.concat(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
        L_0x0123:
            int r4 = r5.length()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            int r7 = r7 + r4
            java.lang.String r4 = "+0000"
            boolean r4 = r5.equals(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r4 != 0) goto L_0x017a
            java.lang.String r4 = "+00:00"
            boolean r4 = r5.equals(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r4 == 0) goto L_0x0139
            goto L_0x017a
        L_0x0139:
            java.lang.String r3 = r3.concat(r5)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.util.TimeZone r4 = java.util.TimeZone.getTimeZone(r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.lang.String r5 = r4.getID()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            boolean r11 = r5.equals(r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r11 != 0) goto L_0x0178
            java.lang.String r11 = ":"
            java.lang.String r15 = ""
            java.lang.String r5 = r5.replace(r11, r15)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            boolean r5 = r5.equals(r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            if (r5 == 0) goto L_0x015a
            goto L_0x0178
        L_0x015a:
            java.lang.IndexOutOfBoundsException r5 = new java.lang.IndexOutOfBoundsException     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r6.<init>(r0)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r6.append(r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.lang.String r0 = " given, resolves to "
            r6.append(r0)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.lang.String r0 = r4.getID()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r6.append(r0)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.lang.String r0 = r6.toString()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r5.<init>(r0)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            throw r5     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
        L_0x0178:
            r0 = r4
            goto L_0x017c
        L_0x017a:
            java.util.TimeZone r0 = TIMEZONE_UTC     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
        L_0x017c:
            java.util.GregorianCalendar r3 = new java.util.GregorianCalendar     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r3.<init>(r0)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r0 = 0
            r3.setLenient(r0)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r0 = 1
            r3.set(r0, r8)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            int r10 = r10 - r0
            r0 = 2
            r3.set(r0, r10)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r15 = 5
            r3.set(r15, r12)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r0 = 11
            r3.set(r0, r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r0 = 12
            r3.set(r0, r9)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r0 = 13
            r3.set(r0, r14)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r0 = 14
            r3.set(r0, r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            r2.setIndex(r7)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.util.Date r0 = r3.getTime()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            return r0
        L_0x01ae:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            java.lang.String r3 = "No time zone indicator"
            r0.<init>(r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
            throw r0     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0056 }
        L_0x01b6:
            if (r1 != 0) goto L_0x01ba
            r1 = 0
            goto L_0x01c2
        L_0x01ba:
            java.lang.String r3 = "\""
            r4 = 34
            java.lang.String r1 = c0.C0086a.h(r4, r3, r1)
        L_0x01c2:
            java.lang.String r3 = r0.getMessage()
            if (r3 == 0) goto L_0x01ce
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x01e9
        L_0x01ce:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "("
            r3.<init>(r4)
            java.lang.Class r4 = r0.getClass()
            java.lang.String r4 = r4.getName()
            r3.append(r4)
            java.lang.String r4 = ")"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
        L_0x01e9:
            java.text.ParseException r4 = new java.text.ParseException
            java.lang.String r5 = "Failed to parse date ["
            java.lang.String r6 = "]: "
            java.lang.String r1 = i.C0212a.n(r5, r1, r6, r3)
            int r2 = r2.getIndex()
            r4.<init>(r1, r2)
            r4.initCause(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static int parseInt(String str, int i2, int i7) {
        int i8;
        int i10;
        if (i2 < 0 || i7 > str.length() || i2 > i7) {
            throw new NumberFormatException(str);
        }
        if (i2 < i7) {
            i10 = i2 + 1;
            int digit = Character.digit(str.charAt(i2), 10);
            if (digit >= 0) {
                i8 = -digit;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i7));
            }
        } else {
            i8 = 0;
            i10 = i2;
        }
        while (i10 < i7) {
            int i11 = i10 + 1;
            int digit2 = Character.digit(str.charAt(i10), 10);
            if (digit2 >= 0) {
                i8 = (i8 * 10) - digit2;
                i10 = i11;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i7));
            }
        }
        return -i8;
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb2 = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb2, gregorianCalendar.get(1), 4);
        char c5 = '-';
        sb2.append('-');
        padInt(sb2, gregorianCalendar.get(2) + 1, 2);
        sb2.append('-');
        padInt(sb2, gregorianCalendar.get(5), 2);
        sb2.append('T');
        padInt(sb2, gregorianCalendar.get(11), 2);
        sb2.append(':');
        padInt(sb2, gregorianCalendar.get(12), 2);
        sb2.append(':');
        padInt(sb2, gregorianCalendar.get(13), 2);
        if (z) {
            sb2.append('.');
            padInt(sb2, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i2 = offset / Network.DEFAULT_TIMEOUT;
            int abs = Math.abs(i2 / 60);
            int abs2 = Math.abs(i2 % 60);
            if (offset >= 0) {
                c5 = '+';
            }
            sb2.append(c5);
            padInt(sb2, abs, 2);
            sb2.append(':');
            padInt(sb2, abs2, 2);
        } else {
            sb2.append('Z');
        }
        return sb2.toString();
    }
}
