package vf;

import Hf.C0774x;
import Qe.C;
import kotlin.jvm.internal.j;

/* renamed from: vf.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1327g {

    /* renamed from: a  reason: collision with root package name */
    public final Object f5158a;

    public C1327g(Object obj) {
        this.f5158a = obj;
    }

    public abstract C0774x a(C c5);

    public Object b() {
        return this.f5158a;
    }

    public final boolean equals(Object obj) {
        C1327g gVar;
        if (this == obj) {
            return true;
        }
        Object b = b();
        Object obj2 = null;
        if (obj instanceof C1327g) {
            gVar = (C1327g) obj;
        } else {
            gVar = null;
        }
        if (gVar != null) {
            obj2 = gVar.b();
        }
        if (j.a(b, obj2)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        Object b = b();
        if (b != null) {
            return b.hashCode();
        }
        return 0;
    }

    public String toString() {
        return String.valueOf(b());
    }
}
