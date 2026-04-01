package Qe;

import Gf.p;
import Hf.B;
import Hf.M;
import Hf.d0;
import Re.h;
import java.util.List;
import kotlin.jvm.internal.j;
import qf.C1240g;

/* renamed from: Qe.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0815e implements V {
    public final V d;
    public final C0820j e;
    public final int f;

    public C0815e(V v, C0820j jVar, int i2) {
        this.d = v;
        this.e = jVar;
        this.f = i2;
    }

    public final p F() {
        p F4 = this.d.F();
        j.d(F4, "getStorageManager(...)");
        return F4;
    }

    public final boolean J() {
        return true;
    }

    /* renamed from: a  reason: collision with other method in class */
    public final C0819i m50a() {
        return this.d.a();
    }

    public final C0822l g() {
        return this.e;
    }

    public final h getAnnotations() {
        return this.d.getAnnotations();
    }

    public final int getIndex() {
        return this.d.getIndex() + this.f;
    }

    public final C1240g getName() {
        C1240g name = this.d.getName();
        j.d(name, "getName(...)");
        return name;
    }

    public final Q getSource() {
        Q source = this.d.getSource();
        j.d(source, "getSource(...)");
        return source;
    }

    public final List getUpperBounds() {
        List upperBounds = this.d.getUpperBounds();
        j.d(upperBounds, "getUpperBounds(...)");
        return upperBounds;
    }

    public final B i() {
        B i2 = this.d.i();
        j.d(i2, "getDefaultType(...)");
        return i2;
    }

    public final M q() {
        M q = this.d.q();
        j.d(q, "getTypeConstructor(...)");
        return q;
    }

    public final boolean r() {
        return this.d.r();
    }

    public final d0 t() {
        d0 t = this.d.t();
        j.d(t, "getVariance(...)");
        return t;
    }

    public final String toString() {
        return this.d + "[inner-copy]";
    }

    public final Object v(C0824n nVar, Object obj) {
        return this.d.v(nVar, obj);
    }

    /* renamed from: a  reason: collision with other method in class */
    public final C0822l m51a() {
        return this.d.a();
    }

    public final V a() {
        return this.d.a();
    }
}
