package i1;

import N2.j;
import c0.C0086a;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.scsp.framework.core.network.Network;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends DateFormat {

    /* renamed from: j  reason: collision with root package name */
    public static final Pattern f1783j = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d");
    public static final Pattern k = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[:]\\d\\d(?:[:]\\d\\d)?(\\.\\d+)?(Z|[+-]\\d\\d(?:[:]?\\d\\d)?)?");
    public static final String[] l = {"yyyy-MM-dd'T'HH:mm:ss.SSSX", "yyyy-MM-dd'T'HH:mm:ss.SSS", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"};
    public static final TimeZone m;
    public static final Locale n;

    /* renamed from: o  reason: collision with root package name */
    public static final SimpleDateFormat f1784o;

    /* renamed from: p  reason: collision with root package name */
    public static final b f1785p = new b();
    public static final GregorianCalendar q;
    public transient TimeZone d;
    public final Locale e;
    public Boolean f;
    public transient Calendar g;

    /* renamed from: h  reason: collision with root package name */
    public transient DateFormat f1786h;

    /* renamed from: i  reason: collision with root package name */
    public final boolean f1787i;

    static {
        try {
            TimeZone timeZone = TimeZone.getTimeZone("UTC");
            m = timeZone;
            Locale locale = Locale.US;
            n = locale;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", locale);
            f1784o = simpleDateFormat;
            simpleDateFormat.setTimeZone(timeZone);
            q = new GregorianCalendar(timeZone, locale);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    public b() {
        this.f1787i = true;
        this.e = n;
    }

    public static int b(int i2, String str) {
        return (str.charAt(i2 + 1) - '0') + ((str.charAt(i2) - '0') * 10);
    }

    public static int c(String str) {
        int charAt = ((str.charAt(1) - '0') * 100) + ((str.charAt(0) - '0') * 1000);
        return (str.charAt(3) - '0') + ((str.charAt(2) - '0') * 10) + charAt;
    }

    public static void f(StringBuffer stringBuffer, int i2) {
        int i7 = i2 / 10;
        if (i7 == 0) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i7 + 48));
            i2 -= i7 * 10;
        }
        stringBuffer.append((char) (i2 + 48));
    }

    public static void g(StringBuffer stringBuffer, int i2) {
        int i7 = i2 / 100;
        if (i7 == 0) {
            stringBuffer.append('0');
            stringBuffer.append('0');
        } else {
            if (i7 > 99) {
                stringBuffer.append(i7);
            } else {
                f(stringBuffer, i7);
            }
            i2 -= i7 * 100;
        }
        f(stringBuffer, i2);
    }

    public final Calendar a(TimeZone timeZone) {
        Calendar calendar = this.g;
        if (calendar == null) {
            calendar = (Calendar) q.clone();
            this.g = calendar;
        }
        if (!calendar.getTimeZone().equals(timeZone)) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setLenient(isLenient());
        return calendar;
    }

    public final Object clone() {
        return new b(this.d, this.e, this.f, this.f1787i);
    }

    public final Date d(String str) {
        TimeZone timeZone;
        String str2;
        int i2;
        int i7;
        int i8;
        int i10;
        String str3 = str;
        int length = str3.length();
        if (this.d == null || 'Z' == str3.charAt(length - 1)) {
            timeZone = m;
        } else {
            timeZone = this.d;
        }
        Calendar a7 = a(timeZone);
        a7.clear();
        int i11 = 0;
        if (length > 10) {
            Matcher matcher = k.matcher(str3);
            if (matcher.matches()) {
                int start = matcher.start(2);
                int end = matcher.end(2);
                int i12 = end - start;
                if (i12 > 1) {
                    int b = b(start + 1, str3) * 3600;
                    if (i12 >= 5) {
                        b += b(end - 2, str3) * 60;
                    }
                    if (str3.charAt(start) == '-') {
                        i10 = b * StatusCodes.UNDEFINED;
                    } else {
                        i10 = b * 1000;
                    }
                    a7.set(15, i10);
                    a7.set(16, 0);
                }
                int c5 = c(str3);
                int b5 = b(5, str3) - 1;
                int b8 = b(8, str3);
                int b10 = b(11, str3);
                int b11 = b(14, str3);
                if (length <= 16 || str3.charAt(16) != ':') {
                    int i13 = c5;
                    i7 = b8;
                    i8 = i13;
                    i2 = 0;
                } else {
                    int i14 = c5;
                    i7 = b8;
                    i8 = i14;
                    i2 = b(17, str3);
                }
                a7.set(i8, b5, i7, b10, b11, i2);
                int start2 = matcher.start(1);
                int i15 = start2 + 1;
                int end2 = matcher.end(1);
                if (i15 >= end2) {
                    a7.set(14, 0);
                } else {
                    int i16 = end2 - i15;
                    if (i16 != 0) {
                        if (i16 != 1) {
                            if (i16 != 2) {
                                if (i16 == 3 || i16 <= 9) {
                                    i11 = str3.charAt(start2 + 3) - '0';
                                } else {
                                    throw new ParseException(j.d("Cannot parse date \"", str3, "\": invalid fractional seconds '", matcher.group(1).substring(1), "'; can use at most 9 digits"), i15);
                                }
                            }
                            i11 += (str3.charAt(start2 + 2) - '0') * 10;
                        }
                        i11 += (str3.charAt(i15) - '0') * 100;
                    }
                    a7.set(14, i11);
                }
                return a7.getTime();
            }
            str2 = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
        } else if (f1783j.matcher(str3).matches()) {
            a7.set(c(str3), b(5, str3) - 1, b(8, str3), 0, 0, 0);
            a7.set(14, 0);
            return a7.getTime();
        } else {
            str2 = "yyyy-MM-dd";
        }
        Boolean bool = this.f;
        StringBuilder q10 = C0086a.q("Cannot parse date \"", str3, "\": while it seems to fit format '", str2, "', parsing fails (leniency? ");
        q10.append(bool);
        q10.append(")");
        throw new ParseException(q10.toString(), 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006d, code lost:
        r0 = R0.c.f639a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008a, code lost:
        if (r2 < 0) goto L_0x0090;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Date e(java.lang.String r6, java.text.ParsePosition r7) {
        /*
            r5 = this;
            int r0 = r6.length()
            r1 = 7
            r2 = 45
            r3 = 0
            if (r0 < r1) goto L_0x004d
            char r0 = r6.charAt(r3)
            boolean r0 = java.lang.Character.isDigit(r0)
            if (r0 == 0) goto L_0x004d
            r0 = 3
            char r0 = r6.charAt(r0)
            boolean r0 = java.lang.Character.isDigit(r0)
            if (r0 == 0) goto L_0x004d
            r0 = 4
            char r0 = r6.charAt(r0)
            if (r0 != r2) goto L_0x004d
            r0 = 5
            char r0 = r6.charAt(r0)
            boolean r0 = java.lang.Character.isDigit(r0)
            if (r0 == 0) goto L_0x004d
            java.util.Date r5 = r5.d(r6)     // Catch:{ IllegalArgumentException -> 0x0036 }
            return r5
        L_0x0036:
            r5 = move-exception
            java.text.ParseException r0 = new java.text.ParseException
            java.lang.String r5 = r5.getMessage()
            java.lang.String r1 = "Cannot parse date \""
            java.lang.String r2 = "\", problem: "
            java.lang.String r5 = i.C0212a.n(r1, r6, r2, r5)
            int r6 = r7.getErrorIndex()
            r0.<init>(r5, r6)
            throw r0
        L_0x004d:
            int r0 = r6.length()
        L_0x0051:
            int r0 = r0 + -1
            if (r0 < 0) goto L_0x0065
            char r1 = r6.charAt(r0)
            r4 = 48
            if (r1 < r4) goto L_0x0061
            r4 = 57
            if (r1 <= r4) goto L_0x0051
        L_0x0061:
            if (r0 > 0) goto L_0x0065
            if (r1 == r2) goto L_0x0051
        L_0x0065:
            if (r0 >= 0) goto L_0x00ac
            char r0 = r6.charAt(r3)
            if (r0 == r2) goto L_0x0090
            java.lang.String r0 = R0.c.f639a
            int r1 = r0.length()
            int r2 = r6.length()
            if (r2 >= r1) goto L_0x007a
            goto L_0x0090
        L_0x007a:
            if (r2 <= r1) goto L_0x007d
            goto L_0x00ac
        L_0x007d:
            if (r3 >= r1) goto L_0x0090
            char r2 = r6.charAt(r3)
            char r4 = r0.charAt(r3)
            int r2 = r2 - r4
            if (r2 == 0) goto L_0x008d
            if (r2 >= 0) goto L_0x00ac
            goto L_0x0090
        L_0x008d:
            int r3 = r3 + 1
            goto L_0x007d
        L_0x0090:
            long r5 = R0.c.a(r6)     // Catch:{ NumberFormatException -> 0x009a }
            java.util.Date r7 = new java.util.Date
            r7.<init>(r5)
            return r7
        L_0x009a:
            java.text.ParseException r5 = new java.text.ParseException
            java.lang.String r0 = "Timestamp value "
            java.lang.String r1 = " out of 64-bit value range"
            java.lang.String r6 = i.C0212a.m(r0, r6, r1)
            int r7 = r7.getErrorIndex()
            r5.<init>(r6, r7)
            throw r5
        L_0x00ac:
            java.text.DateFormat r0 = r5.f1786h
            if (r0 != 0) goto L_0x00e5
            java.util.TimeZone r0 = r5.d
            java.lang.Boolean r1 = r5.f
            java.util.Locale r2 = n
            java.util.Locale r3 = r5.e
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x00cd
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r4 = "EEE, dd MMM yyyy HH:mm:ss zzz"
            r2.<init>(r4, r3)
            if (r0 != 0) goto L_0x00c9
            java.util.TimeZone r0 = m
        L_0x00c9:
            r2.setTimeZone(r0)
            goto L_0x00da
        L_0x00cd:
            java.text.SimpleDateFormat r2 = f1784o
            java.lang.Object r2 = r2.clone()
            java.text.DateFormat r2 = (java.text.DateFormat) r2
            if (r0 == 0) goto L_0x00da
            r2.setTimeZone(r0)
        L_0x00da:
            if (r1 == 0) goto L_0x00e3
            boolean r0 = r1.booleanValue()
            r2.setLenient(r0)
        L_0x00e3:
            r5.f1786h = r2
        L_0x00e5:
            java.text.DateFormat r5 = r5.f1786h
            java.util.Date r5 = r5.parse(r6, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.b.e(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return false;
    }

    public final StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        TimeZone timeZone = this.d;
        if (timeZone == null) {
            timeZone = m;
        }
        Calendar a7 = a(timeZone);
        a7.setTime(date);
        int i2 = a7.get(1);
        char c5 = '+';
        if (a7.get(0) != 0) {
            if (i2 > 9999) {
                stringBuffer.append('+');
            }
            g(stringBuffer, i2);
        } else if (i2 == 1) {
            stringBuffer.append("+0000");
        } else {
            stringBuffer.append('-');
            g(stringBuffer, i2 - 1);
        }
        stringBuffer.append('-');
        f(stringBuffer, a7.get(2) + 1);
        stringBuffer.append('-');
        f(stringBuffer, a7.get(5));
        stringBuffer.append('T');
        f(stringBuffer, a7.get(11));
        stringBuffer.append(':');
        f(stringBuffer, a7.get(12));
        stringBuffer.append(':');
        f(stringBuffer, a7.get(13));
        stringBuffer.append('.');
        int i7 = a7.get(14);
        int i8 = i7 / 100;
        if (i8 == 0) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i8 + 48));
            i7 -= i8 * 100;
        }
        f(stringBuffer, i7);
        int offset = timeZone.getOffset(a7.getTimeInMillis());
        boolean z = this.f1787i;
        if (offset != 0) {
            int i10 = offset / Network.DEFAULT_TIMEOUT;
            int abs = Math.abs(i10 / 60);
            int abs2 = Math.abs(i10 % 60);
            if (offset < 0) {
                c5 = '-';
            }
            stringBuffer.append(c5);
            f(stringBuffer, abs);
            if (z) {
                stringBuffer.append(':');
            }
            f(stringBuffer, abs2);
            return stringBuffer;
        } else if (z) {
            stringBuffer.append("+00:00");
            return stringBuffer;
        } else {
            stringBuffer.append("+0000");
            return stringBuffer;
        }
    }

    public final TimeZone getTimeZone() {
        return this.d;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final boolean isLenient() {
        Boolean bool = this.f;
        if (bool == null || bool.booleanValue()) {
            return true;
        }
        return false;
    }

    public final Date parse(String str) {
        String trim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        Date e7 = e(trim, parsePosition);
        if (e7 != null) {
            return e7;
        }
        StringBuilder sb2 = new StringBuilder();
        for (String str2 : l) {
            if (sb2.length() > 0) {
                sb2.append("\", \"");
            } else {
                sb2.append('\"');
            }
            sb2.append(str2);
        }
        sb2.append('\"');
        throw new ParseException(j.d("Cannot parse date \"", trim, "\": not compatible with any of standard forms (", sb2.toString(), ")"), parsePosition.getErrorIndex());
    }

    public final void setLenient(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        Boolean bool = this.f;
        if (valueOf != bool && !valueOf.equals(bool)) {
            this.f = valueOf;
            this.f1786h = null;
        }
    }

    public final void setTimeZone(TimeZone timeZone) {
        if (!timeZone.equals(this.d)) {
            this.f1786h = null;
            this.d = timeZone;
        }
    }

    public final String toString() {
        return String.format("DateFormat %s: (timezone: %s, locale: %s, lenient: %s)", new Object[]{b.class.getName(), this.d, this.e, this.f});
    }

    public b(TimeZone timeZone, Locale locale, Boolean bool, boolean z) {
        this.d = timeZone;
        this.e = locale;
        this.f = bool;
        this.f1787i = z;
    }

    public final Date parse(String str, ParsePosition parsePosition) {
        try {
            return e(str, parsePosition);
        } catch (ParseException unused) {
            return null;
        }
    }
}
