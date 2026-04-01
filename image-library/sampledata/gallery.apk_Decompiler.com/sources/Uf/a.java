package Uf;

import Tf.n;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Comparable {
    public static final long e = c.q(4611686018427387903L);
    public static final long f = c.q(-4611686018427387903L);
    public static final /* synthetic */ int g = 0;
    public final long d;

    static {
        int i2 = b.f3828a;
    }

    public /* synthetic */ a(long j2) {
        this.d = j2;
    }

    public static final long a(long j2, long j3) {
        long j8 = (long) 1000000;
        long j10 = j3 / j8;
        long j11 = j2 + j10;
        if (-4611686018426L > j11 || j11 >= 4611686018427L) {
            return c.q(B1.a.m(j11, -4611686018427387903L, 4611686018427387903L));
        }
        return c.s((j11 * j8) + (j3 - (j10 * j8)));
    }

    public static final void b(StringBuilder sb2, int i2, int i7, int i8, String str, boolean z) {
        sb2.append(i2);
        if (i7 != 0) {
            sb2.append('.');
            String F02 = n.F0(String.valueOf(i7), i8, '0');
            int i10 = -1;
            int length = F02.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i11 = length - 1;
                    if (F02.charAt(length) != '0') {
                        i10 = length;
                        break;
                    } else if (i11 < 0) {
                        break;
                    } else {
                        length = i11;
                    }
                }
            }
            int i12 = i10 + 1;
            if (z || i12 >= 3) {
                sb2.append(F02, 0, ((i10 + 3) / 3) * 3);
            } else {
                sb2.append(F02, 0, i12);
            }
        }
        sb2.append(str);
    }

    public static final boolean c(long j2) {
        if (j2 == e || j2 == f) {
            return true;
        }
        return false;
    }

    public static final long d(long j2, long j3) {
        if (c(j2)) {
            if (!c(j3) || (j3 ^ j2) >= 0) {
                return j2;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        } else if (c(j3)) {
            return j3;
        } else {
            int i2 = ((int) j2) & 1;
            if (i2 == (((int) j3) & 1)) {
                long j8 = (j2 >> 1) + (j3 >> 1);
                if (i2 != 0) {
                    return c.r(j8);
                }
                if (-4611686018426999999L > j8 || j8 >= 4611686018427000000L) {
                    return c.q(j8 / ((long) 1000000));
                }
                return c.s(j8);
            } else if (i2 == 1) {
                return a(j2 >> 1, j3 >> 1);
            } else {
                return a(j3 >> 1, j2 >> 1);
            }
        }
    }

    public static final long e(long j2, c cVar) {
        c cVar2;
        j.e(cVar, "unit");
        if (j2 == e) {
            return Long.MAX_VALUE;
        }
        if (j2 == f) {
            return Long.MIN_VALUE;
        }
        long j3 = j2 >> 1;
        if ((((int) j2) & 1) == 0) {
            cVar2 = c.NANOSECONDS;
        } else {
            cVar2 = c.MILLISECONDS;
        }
        j.e(cVar2, "sourceUnit");
        return cVar.a().convert(j3, cVar2.a());
    }

    public final int compareTo(Object obj) {
        long j2 = ((a) obj).d;
        long j3 = this.d;
        long j8 = j3 ^ j2;
        if (j8 < 0 || (((int) j8) & 1) == 0) {
            int i2 = (j3 > j2 ? 1 : (j3 == j2 ? 0 : -1));
            if (i2 < 0) {
                return -1;
            }
            if (i2 == 0) {
                return 0;
            }
            return 1;
        }
        int i7 = (((int) j3) & 1) - (((int) j2) & 1);
        if (j3 < 0) {
            return -i7;
        }
        return i7;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        if (this.d != ((a) obj).d) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.d);
    }

    public final String toString() {
        boolean z;
        int i2;
        long j2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        long j3;
        long j8 = this.d;
        int i17 = (j8 > 0 ? 1 : (j8 == 0 ? 0 : -1));
        if (i17 == 0) {
            return "0s";
        }
        if (j8 == e) {
            return "Infinity";
        }
        if (j8 == f) {
            return "-Infinity";
        }
        if (i17 < 0) {
            z = true;
        } else {
            z = false;
        }
        StringBuilder sb2 = new StringBuilder();
        if (z) {
            sb2.append('-');
        }
        if (i17 < 0) {
            j8 = ((long) (((int) j8) & 1)) + ((-(j8 >> 1)) << 1);
            int i18 = b.f3828a;
        }
        long e7 = e(j8, c.DAYS);
        if (c(j8)) {
            i2 = 0;
        } else {
            i2 = (int) (e(j8, c.HOURS) % ((long) 24));
        }
        if (c(j8)) {
            j2 = 0;
            i7 = 0;
        } else {
            j2 = 0;
            i7 = (int) (e(j8, c.MINUTES) % ((long) 60));
        }
        if (c(j8)) {
            i8 = 0;
        } else {
            i8 = (int) (e(j8, c.SECONDS) % ((long) 60));
        }
        if (c(j8)) {
            i10 = 1;
            i11 = 0;
        } else {
            if ((((int) j8) & 1) == 1) {
                i10 = 1;
                j3 = ((j8 >> 1) % ((long) 1000)) * ((long) 1000000);
            } else {
                i10 = 1;
                j3 = (j8 >> 1) % ((long) CommonConstants.SupportedApiMinVersion.VERSION_10_0);
            }
            i11 = (int) j3;
        }
        if (e7 != j2) {
            i12 = i10;
        } else {
            i12 = 0;
        }
        if (i2 != 0) {
            i13 = i10;
        } else {
            i13 = 0;
        }
        if (i7 != 0) {
            i14 = i10;
        } else {
            i14 = 0;
        }
        if (i8 == 0 && i11 == 0) {
            i15 = 0;
        } else {
            i15 = i10;
        }
        if (i12 != 0) {
            sb2.append(e7);
            sb2.append('d');
            i16 = i10;
        } else {
            i16 = 0;
        }
        if (!(i13 == 0 && (i12 == 0 || (i14 == 0 && i15 == 0)))) {
            int i19 = i16 + 1;
            if (i16 > 0) {
                sb2.append(' ');
            }
            sb2.append(i2);
            sb2.append('h');
            i16 = i19;
        }
        if (!(i14 == 0 && (i15 == 0 || (i13 == 0 && i12 == 0)))) {
            int i20 = i16 + 1;
            if (i16 > 0) {
                sb2.append(' ');
            }
            sb2.append(i7);
            sb2.append('m');
            i16 = i20;
        }
        if (i15 != 0) {
            int i21 = i16 + 1;
            if (i16 > 0) {
                sb2.append(' ');
            }
            if (i8 != 0 || i12 != 0 || i13 != 0 || i14 != 0) {
                b(sb2, i8, i11, 9, "s", false);
            } else if (i11 >= 1000000) {
                b(sb2, i11 / 1000000, i11 % 1000000, 6, "ms", false);
            } else if (i11 >= 1000) {
                b(sb2, i11 / 1000, i11 % 1000, 3, "us", false);
            } else {
                sb2.append(i11);
                sb2.append("ns");
            }
            i16 = i21;
        }
        if (z && i16 > i10) {
            sb2.insert(i10, '(').append(')');
        }
        return sb2.toString();
    }
}
