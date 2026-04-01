package I2;

import He.F;
import a.C0068a;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.math.RoundingMode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f347a = 0;

    static {
        Math.log(2.0d);
    }

    public static boolean a(double d) {
        if (!F.L(d)) {
            return false;
        }
        if (d == MapUtil.INVALID_LOCATION || 52 - Long.numberOfTrailingZeros(F.J(d)) <= Math.getExponent(d)) {
            return true;
        }
        return false;
    }

    public static boolean b(double d) {
        if (d > MapUtil.INVALID_LOCATION && F.L(d)) {
            long J4 = F.J(d);
            if ((J4 & (J4 - 1)) == 0) {
                return true;
            }
        }
        return false;
    }

    public static int c(double d) {
        boolean z;
        boolean b;
        RoundingMode roundingMode = RoundingMode.CEILING;
        boolean z3 = false;
        if (d <= MapUtil.INVALID_LOCATION || !F.L(d)) {
            z = false;
        } else {
            z = true;
        }
        F.i("x must be positive and finite", z);
        int exponent = Math.getExponent(d);
        if (Math.getExponent(d) < -1022) {
            return c(d * 4.503599627370496E15d) - 52;
        }
        switch (a.f346a[roundingMode.ordinal()]) {
            case 1:
                C0068a.j(b(d));
                break;
            case 2:
                break;
            case 3:
                z3 = !b(d);
                break;
            case 4:
                if (exponent < 0) {
                    z3 = true;
                }
                b = b(d);
                break;
            case 5:
                if (exponent >= 0) {
                    z3 = true;
                }
                b = b(d);
                break;
            case 6:
            case 7:
            case 8:
                double longBitsToDouble = Double.longBitsToDouble((Double.doubleToRawLongBits(d) & 4503599627370495L) | 4607182418800017408L);
                if (longBitsToDouble * longBitsToDouble > 2.0d) {
                    z3 = true;
                    break;
                }
                break;
            default:
                throw new AssertionError();
        }
        z3 &= !b;
        if (z3) {
            return exponent + 1;
        }
        return exponent;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        r2 = (double) r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0088, code lost:
        if ((-9.223372036854776E18d - r2) >= 1.0d) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008a, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008c, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0091, code lost:
        if (r2 >= 9.223372036854776E18d) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0094, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0096, code lost:
        if ((r0 & r1) == false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0099, code lost:
        return (long) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b5, code lost:
        throw new java.lang.ArithmeticException("rounded value is out of range for input " + r8 + " and rounding mode " + r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002a, code lost:
        if (java.lang.Math.abs(r8 - r2) == 0.5d) goto L_0x0076;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long d(double r8, java.math.RoundingMode r10) {
        /*
            boolean r0 = He.F.L(r8)
            if (r0 == 0) goto L_0x00b6
            int[] r0 = I2.a.f346a
            int r1 = r10.ordinal()
            r0 = r0[r1]
            r1 = 1
            r2 = 1
            r4 = 0
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            switch(r0) {
                case 1: goto L_0x0078;
                case 2: goto L_0x0068;
                case 3: goto L_0x0059;
                case 4: goto L_0x0076;
                case 5: goto L_0x0046;
                case 6: goto L_0x0041;
                case 7: goto L_0x002d;
                case 8: goto L_0x001e;
                default: goto L_0x0018;
            }
        L_0x0018:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            r8.<init>()
            throw r8
        L_0x001e:
            double r2 = java.lang.Math.rint(r8)
            double r4 = r8 - r2
            double r4 = java.lang.Math.abs(r4)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x0080
            goto L_0x0076
        L_0x002d:
            double r2 = java.lang.Math.rint(r8)
            double r4 = r8 - r2
            double r4 = java.lang.Math.abs(r4)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x0080
            double r2 = java.lang.Math.copySign(r6, r8)
            double r2 = r2 + r8
            goto L_0x0080
        L_0x0041:
            double r2 = java.lang.Math.rint(r8)
            goto L_0x0080
        L_0x0046:
            boolean r0 = a(r8)
            if (r0 == 0) goto L_0x004d
            goto L_0x0076
        L_0x004d:
            long r2 = (long) r8
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0054
            r0 = r1
            goto L_0x0055
        L_0x0054:
            r0 = -1
        L_0x0055:
            long r4 = (long) r0
            long r2 = r2 + r4
            double r2 = (double) r2
            goto L_0x0080
        L_0x0059:
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0076
            boolean r0 = a(r8)
            if (r0 == 0) goto L_0x0064
            goto L_0x0076
        L_0x0064:
            long r4 = (long) r8
            long r4 = r4 + r2
        L_0x0066:
            double r2 = (double) r4
            goto L_0x0080
        L_0x0068:
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x0076
            boolean r0 = a(r8)
            if (r0 == 0) goto L_0x0073
            goto L_0x0076
        L_0x0073:
            long r4 = (long) r8
            long r4 = r4 - r2
            goto L_0x0066
        L_0x0076:
            r2 = r8
            goto L_0x0080
        L_0x0078:
            boolean r0 = a(r8)
            a.C0068a.j(r0)
            goto L_0x0076
        L_0x0080:
            r4 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            double r4 = r4 - r2
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            r4 = 0
            if (r0 >= 0) goto L_0x008c
            r0 = r1
            goto L_0x008d
        L_0x008c:
            r0 = r4
        L_0x008d:
            r5 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r5 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x0094
            goto L_0x0095
        L_0x0094:
            r1 = r4
        L_0x0095:
            r0 = r0 & r1
            if (r0 == 0) goto L_0x009a
            long r8 = (long) r2
            return r8
        L_0x009a:
            java.lang.ArithmeticException r0 = new java.lang.ArithmeticException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "rounded value is out of range for input "
            r1.<init>(r2)
            r1.append(r8)
            java.lang.String r8 = " and rounding mode "
            r1.append(r8)
            r1.append(r10)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            throw r0
        L_0x00b6:
            java.lang.ArithmeticException r8 = new java.lang.ArithmeticException
            java.lang.String r9 = "input is infinite or NaN"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: I2.b.d(double, java.math.RoundingMode):long");
    }
}
