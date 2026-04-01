package Yf;

import qe.C1227c;
import se.C1271c;

public final class o extends C1271c {
    public /* synthetic */ Object d;
    public int e;
    public final /* synthetic */ e f;
    public Object g;

    /* renamed from: h  reason: collision with root package name */
    public h f3927h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public o(e eVar, C1227c cVar) {
        super(cVar);
        this.f = eVar;
    }

    public final Object invokeSuspend(Object obj) {
        this.d = obj;
        this.e |= Integer.MIN_VALUE;
        return this.f.emit((Object) null, this);
    }
}
