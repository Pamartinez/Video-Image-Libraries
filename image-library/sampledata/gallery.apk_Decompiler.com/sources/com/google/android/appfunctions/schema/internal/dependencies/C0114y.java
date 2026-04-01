package com.google.android.appfunctions.schema.internal.dependencies;

import java.nio.charset.Charset;

/* renamed from: com.google.android.appfunctions.schema.internal.dependencies.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0114y {
    public static final C0098h b = new C0098h(1);

    /* renamed from: a  reason: collision with root package name */
    public final Object f1229a;

    public C0114y() {
        C c5;
        Class cls = b0.f1220a;
        try {
            c5 = (C) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            c5 = b;
        }
        C0113x xVar = new C0113x(C0098h.b, c5);
        Charset charset = C0106p.f1226a;
        this.f1229a = xVar;
    }

    public void a(int i2, Object obj, M m) {
        h0 h0Var = (h0) this.f1229a;
        A a7 = (A) obj;
        h0Var.Z(i2, 2);
        h0Var.h0(a7.a(m));
        m.a(a7, this);
    }

    public C0114y(h0 h0Var) {
        Charset charset = C0106p.f1226a;
        if (h0Var != null) {
            this.f1229a = h0Var;
            h0Var.d = this;
            return;
        }
        throw new NullPointerException("output");
    }
}
