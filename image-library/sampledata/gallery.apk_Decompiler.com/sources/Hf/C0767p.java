package Hf;

import Re.h;
import com.samsung.android.sum.core.message.Message;
import kotlin.jvm.internal.j;

/* renamed from: Hf.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0767p extends T {
    public final T b;

    /* renamed from: c  reason: collision with root package name */
    public final T f3449c;

    public C0767p(T t, T t3) {
        this.b = t;
        this.f3449c = t3;
    }

    public final boolean a() {
        if (this.b.a() || this.f3449c.a()) {
            return true;
        }
        return false;
    }

    public final boolean b() {
        if (this.b.b() || this.f3449c.b()) {
            return true;
        }
        return false;
    }

    public final h c(h hVar) {
        j.e(hVar, "annotations");
        return this.f3449c.c(this.b.c(hVar));
    }

    public final P d(C0774x xVar) {
        P d = this.b.d(xVar);
        if (d == null) {
            return this.f3449c.d(xVar);
        }
        return d;
    }

    public final C0774x f(C0774x xVar, d0 d0Var) {
        j.e(xVar, "topLevelType");
        j.e(d0Var, Message.KEY_POSITION);
        return this.f3449c.f(this.b.f(xVar, d0Var), d0Var);
    }
}
