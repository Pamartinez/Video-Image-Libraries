package Yf;

import qe.C1227c;
import se.C1271c;

public final class m extends C1271c {
    public /* synthetic */ Object d;
    public int e;
    public final /* synthetic */ n f;
    public n g;

    /* renamed from: h  reason: collision with root package name */
    public h f3922h;

    /* renamed from: i  reason: collision with root package name */
    public Throwable f3923i;

    /* renamed from: j  reason: collision with root package name */
    public long f3924j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m(n nVar, C1227c cVar) {
        super(cVar);
        this.f = nVar;
    }

    public final Object invokeSuspend(Object obj) {
        this.d = obj;
        this.e |= Integer.MIN_VALUE;
        return this.f.collect((h) null, this);
    }
}
