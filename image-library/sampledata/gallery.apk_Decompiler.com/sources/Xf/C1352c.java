package xf;

import Ae.b;
import He.C0750f;
import Te.Q;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;

/* renamed from: xf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C1352c extends g implements b {
    public static final C1352c d = new g(1);

    public final String getName() {
        return "declaresDefaultValue";
    }

    public final C0750f getOwner() {
        return v.f4727a.b(Q.class);
    }

    public final String getSignature() {
        return "declaresDefaultValue()Z";
    }

    public final Object invoke(Object obj) {
        Q q = (Q) obj;
        j.e(q, "p0");
        return Boolean.valueOf(q.F0());
    }
}
