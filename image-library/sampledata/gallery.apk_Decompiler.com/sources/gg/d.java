package gg;

import Ae.a;
import android.content.Context;
import androidx.appcompat.view.SupportMenuInflater;
import ig.c;
import ig.f;
import ig.g;
import ig.h;
import ig.l;
import kg.P;
import kg.Q;
import kotlin.jvm.internal.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends k implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(int i2, Object obj) {
        super(0);
        this.d = i2;
        this.e = obj;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                c cVar = new c((e) this.e, 1);
                return L2.a.e("com.samsung.android.sdk.moneta.travel.internal.model.TransportationBaseInternal", c.d, new f[0], cVar);
            case 1:
                h hVar = (h) this.e;
                return Integer.valueOf(Q.e(hVar, hVar.k));
            case 2:
                return L2.a.e("kotlin.Unit", l.g, new f[0], new g(1, (P) this.e));
            default:
                return new SupportMenuInflater((Context) this.e);
        }
    }
}
