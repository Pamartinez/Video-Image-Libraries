package og;

import N2.j;
import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;
import java.io.Closeable;
import java.io.EOFException;
import java.io.Flushable;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements b, Closeable, Flushable, WritableByteChannel, Cloneable, ByteChannel {
    public j d;
    public long e;

    public final byte a(long j2) {
        int i2;
        long j3 = j2;
        n.a(this.e, j3, 1);
        long j8 = this.e;
        if (j8 - j3 > j3) {
            j jVar = this.d;
            long j10 = j3;
            while (true) {
                int i7 = jVar.f5017c;
                int i8 = jVar.b;
                long j11 = (long) (i7 - i8);
                if (j10 < j11) {
                    return jVar.f5016a[i8 + ((int) j10)];
                }
                j10 -= j11;
                jVar = jVar.f;
            }
        } else {
            long j12 = j3 - j8;
            j jVar2 = this.d;
            do {
                jVar2 = jVar2.g;
                int i10 = jVar2.f5017c;
                i2 = jVar2.b;
                j12 += (long) (i10 - i2);
            } while (j12 < 0);
            return jVar2.f5016a[i2 + ((int) j12)];
        }
    }

    public final byte c() {
        long j2 = this.e;
        if (j2 != 0) {
            j jVar = this.d;
            int i2 = jVar.b;
            int i7 = jVar.f5017c;
            int i8 = i2 + 1;
            byte b = jVar.f5016a[i2];
            this.e = j2 - 1;
            if (i8 == i7) {
                this.d = jVar.a();
                k.H(jVar);
                return b;
            }
            jVar.b = i8;
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [og.a, java.lang.Object] */
    public final Object clone() {
        ? obj = new Object();
        if (this.e == 0) {
            return obj;
        }
        j c5 = this.d.c();
        obj.d = c5;
        c5.g = c5;
        c5.f = c5;
        j jVar = this.d;
        while (true) {
            jVar = jVar.f;
            if (jVar != this.d) {
                obj.d.g.b(jVar.c());
            } else {
                obj.e = this.e;
                return obj;
            }
        }
    }

    public final long e(a aVar, long j2) {
        if (aVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j2 >= 0) {
            long j3 = this.e;
            if (j3 == 0) {
                return -1;
            }
            if (j2 > j3) {
                j2 = j3;
            }
            aVar.l(this, j2);
            return j2;
        } else {
            throw new IllegalArgumentException(A.a.f("byteCount < 0: ", j2));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        long j2 = this.e;
        if (j2 != aVar.e) {
            return false;
        }
        long j3 = 0;
        if (j2 == 0) {
            return true;
        }
        j jVar = this.d;
        j jVar2 = aVar.d;
        int i2 = jVar.b;
        int i7 = jVar2.b;
        while (j3 < this.e) {
            long min = (long) Math.min(jVar.f5017c - i2, jVar2.f5017c - i7);
            int i8 = 0;
            while (((long) i8) < min) {
                int i10 = i2 + 1;
                int i11 = i7 + 1;
                if (jVar.f5016a[i2] != jVar2.f5016a[i7]) {
                    return false;
                }
                i8++;
                i2 = i10;
                i7 = i11;
            }
            if (i2 == jVar.f5017c) {
                jVar = jVar.f;
                i2 = jVar.b;
            }
            if (i7 == jVar2.f5017c) {
                jVar2 = jVar2.f;
                i7 = jVar2.b;
            }
            j3 += min;
        }
        return true;
    }

    public final byte[] f(long j2) {
        long j3 = j2;
        n.a(this.e, 0, j3);
        if (j3 <= 2147483647L) {
            int i2 = (int) j3;
            byte[] bArr = new byte[i2];
            int i7 = 0;
            while (i7 < i2) {
                int read = read(bArr, i7, i2 - i7);
                if (read != -1) {
                    i7 += read;
                } else {
                    throw new EOFException();
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException(A.a.f("byteCount > Integer.MAX_VALUE: ", j3));
    }

    public final String g(long j2, Charset charset) {
        long j3 = j2;
        n.a(this.e, 0, j3);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j3 > 2147483647L) {
            throw new IllegalArgumentException(A.a.f("byteCount > Integer.MAX_VALUE: ", j3));
        } else if (j3 == 0) {
            return "";
        } else {
            j jVar = this.d;
            int i2 = jVar.b;
            if (((long) i2) + j3 > ((long) jVar.f5017c)) {
                return new String(f(j3), charset);
            }
            String str = new String(jVar.f5016a, i2, (int) j3, charset);
            int i7 = (int) (((long) jVar.b) + j3);
            jVar.b = i7;
            this.e -= j3;
            if (i7 == jVar.f5017c) {
                this.d = jVar.a();
                k.H(jVar);
            }
            return str;
        }
    }

    public final void h(long j2) {
        while (j2 > 0) {
            j jVar = this.d;
            if (jVar != null) {
                int min = (int) Math.min(j2, (long) (jVar.f5017c - jVar.b));
                long j3 = (long) min;
                this.e -= j3;
                j2 -= j3;
                j jVar2 = this.d;
                int i2 = jVar2.b + min;
                jVar2.b = i2;
                if (i2 == jVar2.f5017c) {
                    this.d = jVar2.a();
                    k.H(jVar2);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    public final int hashCode() {
        j jVar = this.d;
        if (jVar == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i7 = jVar.f5017c;
            for (int i8 = jVar.b; i8 < i7; i8++) {
                i2 = (i2 * 31) + jVar.f5016a[i8];
            }
            jVar = jVar.f;
        } while (jVar != this.d);
        return i2;
    }

    public final boolean isOpen() {
        return true;
    }

    public final j j(int i2) {
        if (i2 < 1 || i2 > 8192) {
            throw new IllegalArgumentException();
        }
        j jVar = this.d;
        if (jVar == null) {
            j R = k.R();
            this.d = R;
            R.g = R;
            R.f = R;
            return R;
        }
        j jVar2 = jVar.g;
        if (jVar2.f5017c + i2 <= 8192 && jVar2.e) {
            return jVar2;
        }
        j R3 = k.R();
        jVar2.b(R3);
        return R3;
    }

    public final boolean k(long j2) {
        if (this.e >= j2) {
            return true;
        }
        return false;
    }

    public final void l(a aVar, long j2) {
        j jVar;
        j jVar2;
        int i2;
        if (aVar != this) {
            n.a(aVar.e, 0, j2);
            while (j2 > 0) {
                j jVar3 = aVar.d;
                int i7 = jVar3.f5017c - jVar3.b;
                int i8 = 0;
                if (j2 < ((long) i7)) {
                    j jVar4 = this.d;
                    if (jVar4 != null) {
                        jVar = jVar4.g;
                    } else {
                        jVar = null;
                    }
                    if (jVar != null && jVar.e) {
                        long j3 = ((long) jVar.f5017c) + j2;
                        if (jVar.d) {
                            i2 = 0;
                        } else {
                            i2 = jVar.b;
                        }
                        if (j3 - ((long) i2) <= 8192) {
                            jVar3.d(jVar, (int) j2);
                            aVar.e -= j2;
                            this.e += j2;
                            return;
                        }
                    }
                    int i10 = (int) j2;
                    if (i10 <= 0 || i10 > i7) {
                        throw new IllegalArgumentException();
                    }
                    if (i10 >= 1024) {
                        jVar2 = jVar3.c();
                    } else {
                        jVar2 = k.R();
                        System.arraycopy(jVar3.f5016a, jVar3.b, jVar2.f5016a, 0, i10);
                    }
                    jVar2.f5017c = jVar2.b + i10;
                    jVar3.b += i10;
                    jVar3.g.b(jVar2);
                    aVar.d = jVar2;
                }
                j jVar5 = aVar.d;
                long j8 = (long) (jVar5.f5017c - jVar5.b);
                aVar.d = jVar5.a();
                j jVar6 = this.d;
                if (jVar6 == null) {
                    this.d = jVar5;
                    jVar5.g = jVar5;
                    jVar5.f = jVar5;
                } else {
                    jVar6.g.b(jVar5);
                    j jVar7 = jVar5.g;
                    if (jVar7 == jVar5) {
                        throw new IllegalStateException();
                    } else if (jVar7.e) {
                        int i11 = jVar5.f5017c - jVar5.b;
                        int i12 = 8192 - jVar7.f5017c;
                        if (!jVar7.d) {
                            i8 = jVar7.b;
                        }
                        if (i11 <= i12 + i8) {
                            jVar5.d(jVar7, i11);
                            jVar5.a();
                            k.H(jVar5);
                        }
                    }
                }
                aVar.e -= j8;
                this.e += j8;
                j2 -= j8;
            }
            return;
        }
        throw new IllegalArgumentException("source == this");
    }

    public final void m(int i2) {
        j j2 = j(1);
        byte[] bArr = j2.f5016a;
        int i7 = j2.f5017c;
        j2.f5017c = i7 + 1;
        bArr[i7] = (byte) i2;
        this.e++;
    }

    public final void n(int i2) {
        j j2 = j(4);
        byte[] bArr = j2.f5016a;
        int i7 = j2.f5017c;
        bArr[i7] = (byte) ((i2 >>> 24) & ScoverState.TYPE_NFC_SMART_COVER);
        bArr[i7 + 1] = (byte) ((i2 >>> 16) & ScoverState.TYPE_NFC_SMART_COVER);
        bArr[i7 + 2] = (byte) ((i2 >>> 8) & ScoverState.TYPE_NFC_SMART_COVER);
        bArr[i7 + 3] = (byte) (i2 & ScoverState.TYPE_NFC_SMART_COVER);
        j2.f5017c = i7 + 4;
        this.e += 4;
    }

    public final void p(int i2, int i7, String str) {
        char c5;
        if (i2 < 0) {
            throw new IllegalArgumentException(C0086a.i(i2, "beginIndex < 0: "));
        } else if (i7 < i2) {
            throw new IllegalArgumentException(j.b(i7, i2, "endIndex < beginIndex: ", " < "));
        } else if (i7 <= str.length()) {
            while (i2 < i7) {
                char charAt = str.charAt(i2);
                if (charAt < 128) {
                    j j2 = j(1);
                    byte[] bArr = j2.f5016a;
                    int i8 = j2.f5017c - i2;
                    int min = Math.min(i7, 8192 - i8);
                    int i10 = i2 + 1;
                    bArr[i2 + i8] = (byte) charAt;
                    while (i10 < min) {
                        char charAt2 = str.charAt(i10);
                        if (charAt2 >= 128) {
                            break;
                        }
                        bArr[i10 + i8] = (byte) charAt2;
                        i10++;
                    }
                    int i11 = j2.f5017c;
                    int i12 = (i8 + i10) - i11;
                    j2.f5017c = i11 + i12;
                    this.e += (long) i12;
                    i2 = i10;
                } else {
                    if (charAt < 2048) {
                        m((charAt >> 6) | 192);
                        m((charAt & '?') | 128);
                    } else if (charAt < 55296 || charAt > 57343) {
                        m((charAt >> 12) | 224);
                        m(((charAt >> 6) & 63) | 128);
                        m((charAt & '?') | 128);
                    } else {
                        int i13 = i2 + 1;
                        if (i13 < i7) {
                            c5 = str.charAt(i13);
                        } else {
                            c5 = 0;
                        }
                        if (charAt > 56319 || c5 < 56320 || c5 > 57343) {
                            m(63);
                            i2 = i13;
                        } else {
                            int i14 = (((charAt & 10239) << 10) | (9215 & c5)) + 0;
                            m((i14 >> 18) | 240);
                            m(((i14 >> 12) & 63) | 128);
                            m(((i14 >> 6) & 63) | 128);
                            m((i14 & 63) | 128);
                            i2 += 2;
                        }
                    }
                    i2++;
                }
            }
        } else {
            StringBuilder o2 = C0086a.o(i7, "endIndex > string.length: ", " > ");
            o2.append(str.length());
            throw new IllegalArgumentException(o2.toString());
        }
    }

    public final int read(byte[] bArr, int i2, int i7) {
        n.a((long) bArr.length, (long) i2, (long) i7);
        j jVar = this.d;
        if (jVar == null) {
            return -1;
        }
        int min = Math.min(i7, jVar.f5017c - jVar.b);
        System.arraycopy(jVar.f5016a, jVar.b, bArr, i2, min);
        int i8 = jVar.b + min;
        jVar.b = i8;
        this.e -= (long) min;
        if (i8 == jVar.f5017c) {
            this.d = jVar.a();
            k.H(jVar);
        }
        return min;
    }

    public final String toString() {
        c cVar;
        long j2 = this.e;
        if (j2 <= 2147483647L) {
            int i2 = (int) j2;
            if (i2 == 0) {
                cVar = c.f5013h;
            } else {
                cVar = new l(this, i2);
            }
            return cVar.toString();
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.e);
    }

    public final int write(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            int remaining = byteBuffer.remaining();
            int i2 = remaining;
            while (i2 > 0) {
                j j2 = j(1);
                int min = Math.min(i2, 8192 - j2.f5017c);
                byteBuffer.get(j2.f5016a, j2.f5017c, min);
                i2 -= min;
                j2.f5017c += min;
            }
            this.e += (long) remaining;
            return remaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    public final int read(ByteBuffer byteBuffer) {
        j jVar = this.d;
        if (jVar == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), jVar.f5017c - jVar.b);
        byteBuffer.put(jVar.f5016a, jVar.b, min);
        int i2 = jVar.b + min;
        jVar.b = i2;
        this.e -= (long) min;
        if (i2 == jVar.f5017c) {
            this.d = jVar.a();
            k.H(jVar);
        }
        return min;
    }

    public final void close() {
    }

    public final a d() {
        return this;
    }

    public final void flush() {
    }
}
