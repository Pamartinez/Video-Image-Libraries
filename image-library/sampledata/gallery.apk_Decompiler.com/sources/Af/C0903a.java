package af;

import Df.C0736b;
import Df.p;
import Qe.C0813c;
import Qe.C0814d;
import java.util.Collection;
import java.util.LinkedHashSet;
import tf.C1311o;
import tf.C1312p;

/* renamed from: af.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0903a extends C1312p {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ p f3989c;
    public final /* synthetic */ LinkedHashSet d;
    public final /* synthetic */ boolean e;

    public C0903a(p pVar, LinkedHashSet linkedHashSet, boolean z) {
        this.f3989c = pVar;
        this.d = linkedHashSet;
        this.e = z;
    }

    public static /* synthetic */ void a(int i2) {
        Object[] objArr = new Object[3];
        if (i2 == 1) {
            objArr[0] = "fromSuper";
        } else if (i2 == 2) {
            objArr[0] = "fromCurrent";
        } else if (i2 == 3) {
            objArr[0] = "member";
        } else if (i2 != 4) {
            objArr[0] = "fakeOverride";
        } else {
            objArr[0] = "overridden";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1";
        if (i2 == 1 || i2 == 2) {
            objArr[2] = "conflict";
        } else if (i2 == 3 || i2 == 4) {
            objArr[2] = "setOverriddenDescriptors";
        } else {
            objArr[2] = "addFakeOverride";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public final void b(C0814d dVar) {
        if (dVar != null) {
            C1311o.r(dVar, new C0736b(14, this));
            this.d.add(dVar);
            return;
        }
        a(0);
        throw null;
    }

    public final void d(C0814d dVar, C0814d dVar2) {
        if (dVar2 == null) {
            a(2);
            throw null;
        }
    }

    public final void p(C0814d dVar, Collection collection) {
        if (dVar == null) {
            a(3);
            throw null;
        } else if (!this.e || dVar.b() == C0813c.FAKE_OVERRIDE) {
            dVar.m0(collection);
        }
    }
}
