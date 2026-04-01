package Tf;

import c0.C0086a;
import java.util.Arrays;
import kotlin.jvm.internal.j;
import me.s;
import ne.C1187e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f3817a = 0;

    static {
        int[] iArr = new int[256];
        int i2 = 0;
        for (int i7 = 0; i7 < 256; i7++) {
            iArr[i7] = "0123456789abcdef".charAt(i7 & 15) | ("0123456789abcdef".charAt(i7 >> 4) << 8);
        }
        int[] iArr2 = new int[256];
        for (int i8 = 0; i8 < 256; i8++) {
            iArr2[i8] = "0123456789ABCDEF".charAt(i8 & 15) | ("0123456789ABCDEF".charAt(i8 >> 4) << 8);
        }
        int[] iArr3 = new int[256];
        for (int i10 = 0; i10 < 256; i10++) {
            iArr3[i10] = -1;
        }
        int i11 = 0;
        int i12 = 0;
        while (i11 < "0123456789abcdef".length()) {
            iArr3["0123456789abcdef".charAt(i11)] = i12;
            i11++;
            i12++;
        }
        int i13 = 0;
        int i14 = 0;
        while (i13 < "0123456789ABCDEF".length()) {
            iArr3["0123456789ABCDEF".charAt(i13)] = i14;
            i13++;
            i14++;
        }
        long[] jArr = new long[256];
        for (int i15 = 0; i15 < 256; i15++) {
            jArr[i15] = -1;
        }
        int i16 = 0;
        int i17 = 0;
        while (i16 < "0123456789abcdef".length()) {
            jArr["0123456789abcdef".charAt(i16)] = (long) i17;
            i16++;
            i17++;
        }
        int i18 = 0;
        while (i2 < "0123456789ABCDEF".length()) {
            jArr["0123456789ABCDEF".charAt(i2)] = (long) i18;
            i2++;
            i18++;
        }
    }

    public static String a(long j2) {
        String str;
        long j3 = j2;
        f fVar = f.d;
        j.e(fVar, "format");
        if (fVar.f3820a) {
            str = "0123456789ABCDEF";
        } else {
            str = "0123456789abcdef";
        }
        e eVar = fVar.f3821c;
        if (!eVar.f3819a) {
            return b(j3, eVar, str, 64);
        }
        char charAt = str.charAt((int) ((j3 >> 60) & 15));
        char charAt2 = str.charAt((int) ((j3 >> 56) & 15));
        char charAt3 = str.charAt((int) ((j3 >> 52) & 15));
        char charAt4 = str.charAt((int) ((j3 >> 48) & 15));
        char charAt5 = str.charAt((int) ((j3 >> 44) & 15));
        char charAt6 = str.charAt((int) ((j3 >> 40) & 15));
        char c5 = charAt4;
        char c6 = charAt5;
        char c8 = charAt6;
        return new String(new char[]{charAt, charAt2, charAt3, c5, c6, c8, str.charAt((int) ((j3 >> 36) & 15)), str.charAt((int) ((j3 >> 32) & 15)), str.charAt((int) ((j3 >> 28) & 15)), str.charAt((int) ((j3 >> 24) & 15)), str.charAt((int) ((j3 >> 20) & 15)), str.charAt((int) ((j3 >> 16) & 15)), str.charAt((int) ((j3 >> 12) & 15)), str.charAt((int) ((j3 >> 8) & 15)), str.charAt((int) ((j3 >> 4) & 15)), str.charAt((int) (j3 & 15))});
    }

    public static final String b(long j2, e eVar, String str, int i2) {
        int i7 = i2 >> 2;
        eVar.getClass();
        int i8 = 1 - i7;
        if (i8 < 0) {
            i8 = 0;
        }
        long j3 = (long) 0;
        long j8 = ((long) i8) + j3 + ((long) i7) + j3;
        if (0 > j8 || j8 > 2147483647L) {
            throw new IllegalArgumentException("The resulting string length is too big: " + s.a(j8));
        }
        int i10 = (int) j8;
        char[] cArr = new char[i10];
        if (i8 > 0) {
            Arrays.fill(cArr, 0, i8, str.charAt(0));
        } else {
            i8 = 0;
        }
        int i11 = 0;
        while (i11 < i7) {
            i2 -= 4;
            cArr[i8] = str.charAt((int) ((j2 >> i2) & 15));
            i11++;
            i8++;
        }
        if (i8 == i10) {
            return new String(cArr);
        }
        C1187e.Companion.getClass();
        if (i8 > i10) {
            throw new IndexOutOfBoundsException(N2.j.b(i8, i10, "startIndex: 0, endIndex: ", ", size: "));
        } else if (i8 >= 0) {
            return new String(cArr, 0, i8);
        } else {
            throw new IllegalArgumentException(C0086a.i(i8, "startIndex: 0 > endIndex: "));
        }
    }
}
