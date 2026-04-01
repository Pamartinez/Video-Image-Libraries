package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import Vf.C0867e0;
import Vf.n0;
import Vf.r;
import i.C0212a;
import java.util.List;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public final c f4154a;
    public final List b;

    /* renamed from: c  reason: collision with root package name */
    public final List f4155c;
    public final List d;
    public final List e;
    public final r f;

    /* JADX WARNING: type inference failed for: r2v1, types: [Vf.n0, Vf.r] */
    public h(c cVar, List list, List list2, List list3, List list4) {
        j.e(list, "inputBufferStreams");
        j.e(list2, "inputSurfaceStreams");
        j.e(list3, "outputBufferStreams");
        j.e(list4, "outputSurfaceStreams");
        this.f4154a = cVar;
        this.b = list;
        this.f4155c = list2;
        this.d = list3;
        this.e = list4;
        ? n0Var = new n0(true);
        n0Var.C((C0867e0) null);
        this.f = n0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (j.a(this.f4154a, hVar.f4154a) && j.a(this.b, hVar.b) && j.a(this.f4155c, hVar.f4155c) && j.a(this.d, hVar.d) && j.a(this.e, hVar.e)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        c cVar = this.f4154a;
        if (cVar == null) {
            i2 = 0;
        } else {
            i2 = cVar.hashCode();
        }
        return this.e.hashCode() + C0212a.f(this.d, C0212a.f(this.f4155c, C0212a.f(this.b, i2 * 31, 31), 31), 31);
    }

    public final String toString() {
        return "Config(configMetadata=" + this.f4154a + ", inputBufferStreams=" + this.b + ", inputSurfaceStreams=" + this.f4155c + ", outputBufferStreams=" + this.d + ", outputSurfaceStreams=" + this.e + ")";
    }
}
