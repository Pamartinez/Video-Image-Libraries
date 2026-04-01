package Yf;

import qe.C1227c;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends C1271c {
    public /* synthetic */ Object d;
    public final /* synthetic */ e e;
    public int f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(e eVar, C1227c cVar) {
        super(cVar);
        this.e = eVar;
    }

    public final Object invokeSuspend(Object obj) {
        this.d = obj;
        this.f |= Integer.MIN_VALUE;
        return this.e.emit((Object) null, this);
    }
}
