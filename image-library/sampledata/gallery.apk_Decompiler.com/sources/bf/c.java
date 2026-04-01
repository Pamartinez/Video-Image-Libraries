package Bf;

import Hf.C0774x;
import Qe.C0812b;
import Te.C0853n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends a {
    public final C0853n f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(C0812b bVar, C0774x xVar) {
        super(xVar);
        if (xVar != null) {
            this.f = (C0853n) bVar;
            return;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[]{"receiverType", "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/ExtensionReceiver", "<init>"}));
    }

    public final String toString() {
        return getType() + ": Ext {" + this.f + "}";
    }
}
