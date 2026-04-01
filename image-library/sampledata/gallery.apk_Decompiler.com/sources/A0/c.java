package A0;

import K0.a;
import c0.C0086a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements b {
    public final List d;
    public a e;
    public a f = null;
    public float g = -1.0f;

    public c(List list) {
        this.d = list;
        this.e = f(0.0f);
    }

    public final boolean a(float f5) {
        a aVar = this.f;
        a aVar2 = this.e;
        if (aVar == aVar2 && this.g == f5) {
            return true;
        }
        this.f = aVar2;
        this.g = f5;
        return false;
    }

    public final a b() {
        return this.e;
    }

    public final boolean c(float f5) {
        a aVar = this.e;
        if (f5 >= aVar.b() && f5 < aVar.a()) {
            return !this.e.c();
        }
        this.e = f(f5);
        return true;
    }

    public final float d() {
        return ((a) this.d.get(0)).b();
    }

    public final float e() {
        return ((a) C0086a.f(1, this.d)).a();
    }

    public final a f(float f5) {
        List list = this.d;
        a aVar = (a) C0086a.f(1, list);
        if (f5 >= aVar.b()) {
            return aVar;
        }
        for (int size = list.size() - 2; size >= 1; size--) {
            a aVar2 = (a) list.get(size);
            if (this.e != aVar2 && f5 >= aVar2.b() && f5 < aVar2.a()) {
                return aVar2;
            }
        }
        return (a) list.get(0);
    }

    public final boolean isEmpty() {
        return false;
    }
}
