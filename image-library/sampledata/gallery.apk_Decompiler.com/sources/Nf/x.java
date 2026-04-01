package Nf;

import bf.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x extends m {
    public static final x d = new x("must have no value parameters", 0);
    public static final x e = new x("must have a single value parameter", 1);

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3607c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x(String str, int i2) {
        super(str, 1);
        this.f3607c = i2;
    }

    public final boolean a(g gVar) {
        switch (this.f3607c) {
            case 0:
                return gVar.B().isEmpty();
            default:
                if (gVar.B().size() == 1) {
                    return true;
                }
                return false;
        }
    }
}
