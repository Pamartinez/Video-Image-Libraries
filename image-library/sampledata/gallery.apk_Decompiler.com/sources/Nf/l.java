package Nf;

import bf.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends m {
    public static final l d = new l("must be a member function", 0);
    public static final l e = new l("must be a member or an extension function", 1);

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3591c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(String str, int i2) {
        super(str, 0);
        this.f3591c = i2;
    }

    public final boolean a(g gVar) {
        switch (this.f3591c) {
            case 0:
                if (gVar.n != null) {
                    return true;
                }
                return false;
            default:
                if (gVar.n == null && gVar.m == null) {
                    return false;
                }
                return true;
        }
    }
}
