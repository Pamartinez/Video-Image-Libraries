package com.google.protobuf;

import c0.C0086a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UnknownFieldSetLite {
    public static final UnknownFieldSetLite f = new UnknownFieldSetLite(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f1588a;
    public int[] b;

    /* renamed from: c  reason: collision with root package name */
    public Object[] f1589c;
    public int d;
    public boolean e;

    public UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }

    public static UnknownFieldSetLite e(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
        int i2 = unknownFieldSetLite.f1588a + unknownFieldSetLite2.f1588a;
        int[] copyOf = Arrays.copyOf(unknownFieldSetLite.b, i2);
        System.arraycopy(unknownFieldSetLite2.b, 0, copyOf, unknownFieldSetLite.f1588a, unknownFieldSetLite2.f1588a);
        Object[] copyOf2 = Arrays.copyOf(unknownFieldSetLite.f1589c, i2);
        System.arraycopy(unknownFieldSetLite2.f1589c, 0, copyOf2, unknownFieldSetLite.f1588a, unknownFieldSetLite2.f1588a);
        return new UnknownFieldSetLite(i2, copyOf, copyOf2, true);
    }

    public final void a() {
        if (!this.e) {
            throw new UnsupportedOperationException();
        }
    }

    public final void b(int i2) {
        int[] iArr = this.b;
        if (i2 > iArr.length) {
            int i7 = this.f1588a;
            int i8 = (i7 / 2) + i7;
            if (i8 >= i2) {
                i2 = i8;
            }
            if (i2 < 8) {
                i2 = 8;
            }
            this.b = Arrays.copyOf(iArr, i2);
            this.f1589c = Arrays.copyOf(this.f1589c, i2);
        }
    }

    public final int c() {
        int a0;
        int c02;
        int a02;
        int i2 = this.d;
        if (i2 != -1) {
            return i2;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < this.f1588a; i8++) {
            int i10 = this.b[i8];
            int i11 = i10 >>> 3;
            int i12 = i10 & 7;
            if (i12 != 0) {
                if (i12 == 1) {
                    ((Long) this.f1589c[i8]).getClass();
                    a02 = CodedOutputStream.a0(i11) + 8;
                } else if (i12 == 2) {
                    int a03 = CodedOutputStream.a0(i11);
                    int size = ((ByteString) this.f1589c[i8]).size();
                    i7 = C0086a.B(size, size, a03, i7);
                } else if (i12 == 3) {
                    a0 = CodedOutputStream.a0(i11) * 2;
                    c02 = ((UnknownFieldSetLite) this.f1589c[i8]).c();
                } else if (i12 == 5) {
                    ((Integer) this.f1589c[i8]).getClass();
                    a02 = CodedOutputStream.a0(i11) + 4;
                } else {
                    throw new IllegalStateException(F.c());
                }
                i7 = a02 + i7;
            } else {
                long longValue = ((Long) this.f1589c[i8]).longValue();
                a0 = CodedOutputStream.a0(i11);
                c02 = CodedOutputStream.c0(longValue);
            }
            i7 = c02 + a0 + i7;
        }
        this.d = i7;
        return i7;
    }

    public final boolean d(int i2, CodedInputStream codedInputStream) {
        int z;
        a();
        int i7 = i2 >>> 3;
        int i8 = i2 & 7;
        if (i8 == 0) {
            f(i2, Long.valueOf(codedInputStream.r()));
            return true;
        } else if (i8 == 1) {
            f(i2, Long.valueOf(codedInputStream.o()));
            return true;
        } else if (i8 == 2) {
            f(i2, codedInputStream.k());
            return true;
        } else if (i8 == 3) {
            UnknownFieldSetLite unknownFieldSetLite = new UnknownFieldSetLite();
            do {
                z = codedInputStream.z();
                if (z == 0 || !unknownFieldSetLite.d(z, codedInputStream)) {
                    codedInputStream.a((i7 << 3) | 4);
                    f(i2, unknownFieldSetLite);
                }
                z = codedInputStream.z();
                break;
            } while (!unknownFieldSetLite.d(z, codedInputStream));
            codedInputStream.a((i7 << 3) | 4);
            f(i2, unknownFieldSetLite);
            return true;
        } else if (i8 == 4) {
            return false;
        } else {
            if (i8 == 5) {
                f(i2, Integer.valueOf(codedInputStream.n()));
                return true;
            }
            throw F.c();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnknownFieldSetLite)) {
            return false;
        }
        UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj;
        int i2 = this.f1588a;
        if (i2 == unknownFieldSetLite.f1588a) {
            int[] iArr = this.b;
            int[] iArr2 = unknownFieldSetLite.b;
            int i7 = 0;
            while (true) {
                if (i7 >= i2) {
                    Object[] objArr = this.f1589c;
                    Object[] objArr2 = unknownFieldSetLite.f1589c;
                    int i8 = this.f1588a;
                    int i10 = 0;
                    while (i10 < i8) {
                        if (objArr[i10].equals(objArr2[i10])) {
                            i10++;
                        }
                    }
                    return true;
                } else if (iArr[i7] != iArr2[i7]) {
                    break;
                } else {
                    i7++;
                }
            }
        }
        return false;
    }

    public final void f(int i2, Object obj) {
        a();
        b(this.f1588a + 1);
        int[] iArr = this.b;
        int i7 = this.f1588a;
        iArr[i7] = i2;
        this.f1589c[i7] = obj;
        this.f1588a = i7 + 1;
    }

    public final void g(P p6) {
        if (this.f1588a != 0) {
            p6.getClass();
            for (int i2 = 0; i2 < this.f1588a; i2++) {
                int i7 = this.b[i2];
                Object obj = this.f1589c[i2];
                int i8 = i7 >>> 3;
                int i10 = i7 & 7;
                if (i10 == 0) {
                    p6.f(i8, ((Long) obj).longValue());
                } else if (i10 == 1) {
                    p6.c(i8, ((Long) obj).longValue());
                } else if (i10 == 2) {
                    p6.a(i8, (ByteString) obj);
                } else if (i10 == 3) {
                    CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
                    codedOutputStream.p0(i8, 3);
                    ((UnknownFieldSetLite) obj).g(p6);
                    codedOutputStream.p0(i8, 4);
                } else if (i10 == 5) {
                    p6.b(i8, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(F.c());
                }
            }
        }
    }

    public final int hashCode() {
        int i2 = this.f1588a;
        int i7 = (527 + i2) * 31;
        int[] iArr = this.b;
        int i8 = 17;
        int i10 = 17;
        for (int i11 = 0; i11 < i2; i11++) {
            i10 = (i10 * 31) + iArr[i11];
        }
        int i12 = (i7 + i10) * 31;
        Object[] objArr = this.f1589c;
        int i13 = this.f1588a;
        for (int i14 = 0; i14 < i13; i14++) {
            i8 = (i8 * 31) + objArr[i14].hashCode();
        }
        return i12 + i8;
    }

    public UnknownFieldSetLite(int i2, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.f1588a = i2;
        this.b = iArr;
        this.f1589c = objArr;
        this.e = z;
    }
}
