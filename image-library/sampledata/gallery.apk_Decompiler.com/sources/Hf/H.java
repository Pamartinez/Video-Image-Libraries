package Hf;

import Qe.C0819i;
import Qe.V;
import java.util.ArrayList;
import java.util.Map;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H extends N {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3428c;
    public final /* synthetic */ Object d;

    public /* synthetic */ H(int i2, Object obj) {
        this.f3428c = i2;
        this.d = obj;
    }

    public boolean a() {
        switch (this.f3428c) {
            case 1:
                return false;
            default:
                return super.a();
        }
    }

    public boolean e() {
        switch (this.f3428c) {
            case 1:
                return ((Map) this.d).isEmpty();
            default:
                return super.e();
        }
    }

    public final P g(M m) {
        switch (this.f3428c) {
            case 0:
                j.e(m, "key");
                if (!((ArrayList) this.d).contains(m)) {
                    return null;
                }
                C0819i g = m.g();
                j.c(g, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
                return a0.j((V) g);
            default:
                j.e(m, "key");
                return (P) ((Map) this.d).get(m);
        }
    }
}
