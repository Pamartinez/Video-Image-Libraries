package Ff;

import Ae.b;
import Qe.C0814d;
import Qe.r;
import Te.t;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.j;
import tf.C1311o;
import tf.C1312p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends C1312p {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3381c;
    public final /* synthetic */ AbstractCollection d;

    public /* synthetic */ g(AbstractCollection abstractCollection, int i2) {
        this.f3381c = i2;
        this.d = abstractCollection;
    }

    public static /* synthetic */ void a(int i2) {
        Object[] objArr = new Object[3];
        if (i2 == 1) {
            objArr[0] = "fromSuper";
        } else if (i2 != 2) {
            objArr[0] = "fakeOverride";
        } else {
            objArr[0] = "fromCurrent";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor$EnumEntryScope$4";
        if (i2 == 1 || i2 == 2) {
            objArr[2] = "conflict";
        } else {
            objArr[2] = "addFakeOverride";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public final void b(C0814d dVar) {
        switch (this.f3381c) {
            case 0:
                j.e(dVar, "fakeOverride");
                C1311o.r(dVar, (b) null);
                ((ArrayList) this.d).add(dVar);
                return;
            default:
                if (dVar != null) {
                    C1311o.r(dVar, (b) null);
                    ((LinkedHashSet) this.d).add(dVar);
                    return;
                }
                a(0);
                throw null;
        }
    }

    public final void d(C0814d dVar, C0814d dVar2) {
        switch (this.f3381c) {
            case 0:
                j.e(dVar2, "fromCurrent");
                if (dVar2 instanceof t) {
                    ((t) dVar2).L0(r.f3681a, dVar);
                    return;
                }
                return;
            default:
                if (dVar2 == null) {
                    a(2);
                    throw null;
                }
                return;
        }
    }
}
