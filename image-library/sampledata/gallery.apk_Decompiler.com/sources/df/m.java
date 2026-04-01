package Df;

import Hf.B;
import Hf.C0774x;
import Qe.A;
import Qe.C0814d;
import Qe.C0816f;
import java.util.ArrayList;
import kotlin.jvm.internal.j;
import lf.C1147A;
import lf.Q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m implements o, q, p {

    /* renamed from: c  reason: collision with root package name */
    public static final m f3356c = new m(0);
    public static final m d = new m(1);
    public static final m e = new m(2);
    public static final m f = new m(3);
    public static final m g = new m(4);
    public final /* synthetic */ int b;

    public /* synthetic */ m(int i2) {
        this.b = i2;
    }

    public static A e(C1147A a7) {
        int i2;
        if (a7 == null) {
            i2 = -1;
        } else {
            i2 = A.f3334a[a7.ordinal()];
        }
        if (i2 == 1) {
            return A.FINAL;
        }
        if (i2 == 2) {
            return A.OPEN;
        }
        if (i2 == 3) {
            return A.ABSTRACT;
        }
        if (i2 != 4) {
            return A.FINAL;
        }
        return A.SEALED;
    }

    public C0774x b(Q q, String str, B b5, B b8) {
        j.e(q, "proto");
        j.e(str, "flexibleId");
        j.e(b5, "lowerBound");
        j.e(b8, "upperBound");
        throw new IllegalArgumentException("This method should not be used.");
    }

    public void c(C0814d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[]{"descriptor", "kotlin/reflect/jvm/internal/impl/serialization/deserialization/ErrorReporter$1", "reportCannotInferVisibility"}));
        }
    }

    public Boolean d() {
        switch (this.b) {
            case 1:
                return null;
            default:
                return Boolean.TRUE;
        }
    }

    public void a(C0816f fVar, ArrayList arrayList) {
    }
}
