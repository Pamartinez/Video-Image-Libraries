package Hf;

import Ae.b;
import Ne.p;
import kotlin.jvm.internal.j;
import qf.C1236c;

/* renamed from: Hf.u  reason: case insensitive filesystem */
public final class C0771u implements b {
    public static final C0771u e = new C0771u(0);
    public final /* synthetic */ int d;

    public /* synthetic */ C0771u(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                C0774x xVar = (C0774x) obj;
                j.e(xVar, "it");
                return xVar.toString();
            default:
                C1236c cVar = (C1236c) obj;
                if (cVar != null) {
                    return Boolean.valueOf(!cVar.equals(p.y));
                }
                throw new IllegalArgumentException("Argument for @NotNull parameter 'name' of kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor$1.invoke must not be null");
        }
    }
}
