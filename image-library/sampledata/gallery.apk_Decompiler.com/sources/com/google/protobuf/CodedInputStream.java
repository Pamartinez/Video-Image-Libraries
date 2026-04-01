package com.google.protobuf;

import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CodedInputStream {

    /* renamed from: a  reason: collision with root package name */
    public int f1574a;
    public int b = 100;

    /* renamed from: c  reason: collision with root package name */
    public C0141m f1575c;

    public static int b(int i2) {
        return (-(i2 & 1)) ^ (i2 >>> 1);
    }

    public static long c(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    public static C0138j f(byte[] bArr, int i2, int i7, boolean z) {
        C0138j jVar = new C0138j(bArr, i2, i7, z);
        try {
            jVar.i(i7);
            return jVar;
        } catch (F e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static CodedInputStream g(InputStream inputStream) {
        if (inputStream != null) {
            return new C0139k(inputStream);
        }
        byte[] bArr = D.b;
        return f(bArr, 0, bArr.length, false);
    }

    public static int s(InputStream inputStream, int i2) {
        if ((i2 & 128) == 0) {
            return i2;
        }
        int i7 = i2 & 127;
        int i8 = 7;
        while (i8 < 32) {
            int read = inputStream.read();
            if (read != -1) {
                i7 |= (read & 127) << i8;
                if ((read & 128) == 0) {
                    return i7;
                }
                i8 += 7;
            } else {
                throw F.g();
            }
        }
        while (i8 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw F.g();
            } else if ((read2 & 128) == 0) {
                return i7;
            } else {
                i8 += 7;
            }
        }
        throw F.d();
    }

    public abstract int A();

    public abstract long B();

    public abstract void a(int i2);

    public abstract int d();

    public abstract boolean e();

    public abstract void h(int i2);

    public abstract int i(int i2);

    public abstract boolean j();

    public abstract C0137i k();

    public abstract double l();

    public abstract int m();

    public abstract int n();

    public abstract long o();

    public abstract float p();

    public abstract int q();

    public abstract long r();

    public abstract int t();

    public abstract long u();

    public abstract int v();

    public abstract long w();

    public abstract String x();

    public abstract String y();

    public abstract int z();
}
