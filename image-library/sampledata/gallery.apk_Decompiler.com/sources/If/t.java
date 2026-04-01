package If;

import Ae.c;
import He.C0750f;
import Hf.C0774x;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t extends g implements c {
    public final /* synthetic */ int d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(int i2, Object obj, int i7) {
        super(i2, obj);
        this.d = i7;
    }

    public final String getName() {
        switch (this.d) {
            case 0:
                return "isStrictSupertype";
            default:
                return "equalTypes";
        }
    }

    public final C0750f getOwner() {
        switch (this.d) {
            case 0:
                return v.f4727a.b(u.class);
            default:
                return v.f4727a.b(l.class);
        }
    }

    public final String getSignature() {
        switch (this.d) {
            case 0:
                return "isStrictSupertype(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)Z";
            default:
                return "equalTypes(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)Z";
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        boolean z;
        switch (this.d) {
            case 0:
                C0774x xVar = (C0774x) obj;
                C0774x xVar2 = (C0774x) obj2;
                j.e(xVar, "p0");
                j.e(xVar2, "p1");
                ((u) this.receiver).getClass();
                k.b.getClass();
                l lVar = j.b;
                if (!lVar.b(xVar, xVar2) || lVar.b(xVar2, xVar)) {
                    z = false;
                } else {
                    z = true;
                }
                return Boolean.valueOf(z);
            default:
                C0774x xVar3 = (C0774x) obj;
                C0774x xVar4 = (C0774x) obj2;
                j.e(xVar3, "p0");
                j.e(xVar4, "p1");
                return Boolean.valueOf(((l) this.receiver).a(xVar3, xVar4));
        }
    }
}
