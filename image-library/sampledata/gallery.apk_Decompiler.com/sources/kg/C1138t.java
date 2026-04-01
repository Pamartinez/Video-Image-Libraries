package kg;

import Ae.a;
import ig.f;
import ig.l;
import kotlin.jvm.internal.k;

/* renamed from: kg.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1138t extends k implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ C1139u f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1138t(int i2, String str, C1139u uVar) {
        super(0);
        this.d = i2;
        this.e = str;
        this.f = uVar;
    }

    public final Object invoke() {
        int i2 = this.d;
        f[] fVarArr = new f[i2];
        for (int i7 = 0; i7 < i2; i7++) {
            fVarArr[i7] = L2.a.f(this.e + '.' + this.f.e[i7], l.g, new f[0]);
        }
        return fVarArr;
    }
}
