package com.google.protobuf;

import java.nio.charset.Charset;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class P {
    public static final C0152y b = new C0152y(1);

    /* renamed from: a  reason: collision with root package name */
    public final Object f1586a;

    public P(CodedOutputStream codedOutputStream) {
        D.a(codedOutputStream, "output");
        this.f1586a = codedOutputStream;
        codedOutputStream.g = this;
    }

    public void a(int i2, ByteString byteString) {
        ((CodedOutputStream) this.f1586a).g0(i2, byteString);
    }

    public void b(int i2, int i7) {
        ((CodedOutputStream) this.f1586a).h0(i2, i7);
    }

    public void c(int i2, long j2) {
        ((CodedOutputStream) this.f1586a).j0(i2, j2);
    }

    public void d(int i2, Object obj, Schema schema) {
        CodedOutputStream codedOutputStream = (CodedOutputStream) this.f1586a;
        codedOutputStream.p0(i2, 3);
        schema.a((MessageLite) obj, codedOutputStream.g);
        codedOutputStream.p0(i2, 4);
    }

    public void e(int i2, int i7) {
        ((CodedOutputStream) this.f1586a).l0(i2, i7);
    }

    public void f(int i2, long j2) {
        ((CodedOutputStream) this.f1586a).s0(i2, j2);
    }

    public void g(int i2, Object obj, Schema schema) {
        ((CodedOutputStream) this.f1586a).n0(i2, (MessageLite) obj, schema);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.protobuf.O, java.lang.Object] */
    public P() {
        U u;
        try {
            u = (U) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            u = b;
        }
        U[] uArr = {C0152y.b, u};
        ? obj = new Object();
        obj.f1585a = uArr;
        Charset charset = D.f1578a;
        this.f1586a = obj;
    }
}
