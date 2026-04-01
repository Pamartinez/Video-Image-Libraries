package X2;

import com.samsung.scsp.media.api.constant.MediaApiContract;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* renamed from: X2.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0067g extends r {
    public static final Pattern k = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");
    public static final long[] l = {604800000, MediaApiContract.DAY_IN_MILLI, 3600000, 60000, 1000};
    public static final Pattern m = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final long f921c;
    public final boolean d;
    public final long e;
    public final boolean f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final String f922h;

    /* renamed from: i  reason: collision with root package name */
    public final String[] f923i;

    /* renamed from: j  reason: collision with root package name */
    public final String f924j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0067g(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String[] r23, java.lang.String r24) {
        /*
            r16 = this;
            r0 = r16
            r1 = r20
            X2.s r2 = X2.s.CALENDAR
            r0.<init>(r2)
            r2 = r17
            r0.b = r2
            long r2 = d(r18)     // Catch:{ ParseException -> 0x0093 }
            r0.f921c = r2     // Catch:{ ParseException -> 0x0093 }
            if (r19 != 0) goto L_0x0056
            r4 = -1
            if (r1 != 0) goto L_0x001d
        L_0x0019:
            r7 = r4
        L_0x001a:
            r14 = 0
            goto L_0x004a
        L_0x001d:
            java.util.regex.Pattern r6 = k
            java.util.regex.Matcher r1 = r6.matcher(r1)
            boolean r6 = r1.matches()
            if (r6 != 0) goto L_0x002a
            goto L_0x0019
        L_0x002a:
            r6 = 0
            r7 = 0
        L_0x002d:
            long[] r9 = l
            int r10 = r9.length
            if (r6 >= r10) goto L_0x001a
            int r10 = r6 + 1
            java.lang.String r11 = r1.group(r10)
            if (r11 == 0) goto L_0x0046
            r12 = r9[r6]
            int r6 = java.lang.Integer.parseInt(r11)
            r14 = 0
            long r2 = (long) r6
            long r12 = r12 * r2
            long r7 = r7 + r12
            goto L_0x0048
        L_0x0046:
            r14 = 0
        L_0x0048:
            r6 = r10
            goto L_0x002d
        L_0x004a:
            int r1 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r1 >= 0) goto L_0x004f
            goto L_0x0053
        L_0x004f:
            long r1 = r0.f921c
            long r4 = r1 + r7
        L_0x0053:
            r0.e = r4
            goto L_0x005c
        L_0x0056:
            long r1 = d(r19)     // Catch:{ ParseException -> 0x0088 }
            r0.e = r1     // Catch:{ ParseException -> 0x0088 }
        L_0x005c:
            int r1 = r18.length()
            r2 = 8
            r3 = 1
            if (r1 != r2) goto L_0x0067
            r1 = r3
            goto L_0x0068
        L_0x0067:
            r1 = 0
        L_0x0068:
            r0.d = r1
            if (r19 == 0) goto L_0x0074
            int r1 = r19.length()
            if (r1 != r2) goto L_0x0074
            r2 = r3
            goto L_0x0075
        L_0x0074:
            r2 = 0
        L_0x0075:
            r0.f = r2
            r1 = r21
            r0.g = r1
            r1 = r22
            r0.f922h = r1
            r1 = r23
            r0.f923i = r1
            r1 = r24
            r0.f924j = r1
            return
        L_0x0088:
            r0 = move-exception
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0093:
            r0 = move-exception
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: X2.C0067g.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String):void");
    }

    public static long d(String str) {
        if (!m.matcher(str).matches()) {
            throw new ParseException(str, 0);
        } else if (str.length() == 8) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat.parse(str).getTime();
        } else if (str.length() != 16 || str.charAt(15) != 'Z') {
            return new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH).parse(str).getTime();
        } else {
            long time = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH).parse(str.substring(0, 15)).getTime();
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            long j2 = time + ((long) gregorianCalendar.get(15));
            gregorianCalendar.setTime(new Date(j2));
            return j2 + ((long) gregorianCalendar.get(16));
        }
    }

    public final String a() {
        String str;
        DateFormat dateFormat;
        DateFormat dateFormat2;
        StringBuilder sb2 = new StringBuilder(100);
        r.b(sb2, this.b);
        long j2 = this.f921c;
        String str2 = null;
        if (j2 < 0) {
            str = null;
        } else {
            if (this.d) {
                dateFormat2 = DateFormat.getDateInstance(2);
            } else {
                dateFormat2 = DateFormat.getDateTimeInstance(2, 2);
            }
            str = dateFormat2.format(Long.valueOf(j2));
        }
        r.b(sb2, str);
        long j3 = this.e;
        if (j3 >= 0) {
            if (this.f) {
                dateFormat = DateFormat.getDateInstance(2);
            } else {
                dateFormat = DateFormat.getDateTimeInstance(2, 2);
            }
            str2 = dateFormat.format(Long.valueOf(j3));
        }
        r.b(sb2, str2);
        r.b(sb2, this.g);
        r.b(sb2, this.f922h);
        r.c(sb2, this.f923i);
        r.b(sb2, this.f924j);
        return sb2.toString();
    }
}
