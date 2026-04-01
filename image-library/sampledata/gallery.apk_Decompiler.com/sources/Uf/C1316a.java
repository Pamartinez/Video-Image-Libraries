package uf;

import Af.p;
import Hf.B;
import Hf.C0774x;
import Hf.I;
import Hf.M;
import Hf.P;
import Hf.c0;
import If.f;
import Jf.h;
import Jf.l;
import Kf.c;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* renamed from: uf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1316a extends B implements c {
    public final P e;
    public final C1318c f;
    public final boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final I f5146h;

    public C1316a(P p6, C1318c cVar, boolean z, I i2) {
        j.e(p6, "typeProjection");
        j.e(i2, "attributes");
        this.e = p6;
        this.f = cVar;
        this.g = z;
        this.f5146h = i2;
    }

    public final p A() {
        return l.a(h.CAPTURED_TYPE_SCOPE, true, new String[0]);
    }

    public final B B0(boolean z) {
        if (z == this.g) {
            return this;
        }
        return new C1316a(this.e, this.f, z, this.f5146h);
    }

    public final B C0(I i2) {
        j.e(i2, "newAttributes");
        return new C1316a(this.e, this.f, this.g, i2);
    }

    public final List e0() {
        return C1202t.d;
    }

    public final I p0() {
        return this.f5146h;
    }

    public final M s0() {
        return this.f;
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("Captured(");
        sb2.append(this.e);
        sb2.append(')');
        if (this.g) {
            str = "?";
        } else {
            str = "";
        }
        sb2.append(str);
        return sb2.toString();
    }

    public final boolean u0() {
        return this.g;
    }

    public final C0774x w0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        return new C1316a(this.e.d(fVar), this.f, this.g, this.f5146h);
    }

    public final c0 y0(boolean z) {
        if (z == this.g) {
            return this;
        }
        return new C1316a(this.e, this.f, z, this.f5146h);
    }

    public final c0 z0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        return new C1316a(this.e.d(fVar), this.f, this.g, this.f5146h);
    }
}
