package com.google.protobuf;

import I0.a;
import java.util.logging.Level;
import java.util.logging.Logger;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CodedOutputStream extends k {

    /* renamed from: h  reason: collision with root package name */
    public static final Logger f1576h = Logger.getLogger(CodedOutputStream.class.getName());

    /* renamed from: i  reason: collision with root package name */
    public static final boolean f1577i = p0.e;
    public P g;

    public static int Y(int i2) {
        if (i2 >= 0) {
            return b0(i2);
        }
        return 10;
    }

    public static int Z(String str) {
        int i2;
        try {
            i2 = s0.b(str);
        } catch (r0 unused) {
            i2 = str.getBytes(D.f1578a).length;
        }
        return b0(i2) + i2;
    }

    public static int a0(int i2) {
        return b0(i2 << 3);
    }

    public static int b0(int i2) {
        if ((i2 & -128) == 0) {
            return 1;
        }
        if ((i2 & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i2) == 0) {
            return 3;
        }
        if ((i2 & -268435456) == 0) {
            return 4;
        }
        return 5;
    }

    public static int c0(long j2) {
        int i2;
        if ((-128 & j2) == 0) {
            return 1;
        }
        if (j2 < 0) {
            return 10;
        }
        if ((-34359738368L & j2) != 0) {
            j2 >>>= 28;
            i2 = 6;
        } else {
            i2 = 2;
        }
        if ((-2097152 & j2) != 0) {
            i2 += 2;
            j2 >>>= 14;
        }
        if ((j2 & -16384) != 0) {
            return i2 + 1;
        }
        return i2;
    }

    public final void d0(String str, r0 r0Var) {
        f1576h.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", r0Var);
        byte[] bytes = str.getBytes(D.f1578a);
        try {
            r0(bytes.length);
            X(0, bytes.length, bytes);
        } catch (IndexOutOfBoundsException e) {
            throw new a(e);
        }
    }

    public abstract void e0(byte b);

    public abstract void f0(int i2, boolean z);

    public abstract void g0(int i2, ByteString byteString);

    public abstract void h0(int i2, int i7);

    public abstract void i0(int i2);

    public abstract void j0(int i2, long j2);

    public abstract void k0(long j2);

    public abstract void l0(int i2, int i7);

    public abstract void m0(int i2);

    public abstract void n0(int i2, MessageLite messageLite, Schema schema);

    public abstract void o0(int i2, String str);

    public abstract void p0(int i2, int i7);

    public abstract void q0(int i2, int i7);

    public abstract void r0(int i2);

    public abstract void s0(int i2, long j2);

    public abstract void t0(long j2);
}
