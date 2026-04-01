package jd;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.C;
import Vf.C0886x;
import Vf.C0888z;
import Vf.D;
import java.util.ArrayList;
import me.x;
import ne.C1196n;
import qe.C1227c;
import re.C1245a;
import se.i;

/* renamed from: jd.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1099b extends i implements c {
    public final /* synthetic */ int d;
    public /* synthetic */ Object e;
    public final /* synthetic */ Iterable f;
    public final /* synthetic */ c g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1099b(Iterable iterable, c cVar, C1227c cVar2, int i2) {
        super(2, cVar2);
        this.d = i2;
        this.f = iterable;
        this.g = cVar;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        switch (this.d) {
            case 0:
                C1099b bVar = new C1099b(this.f, this.g, cVar, 0);
                bVar.e = obj;
                return bVar;
            default:
                C1099b bVar2 = new C1099b(this.f, this.g, cVar, 1);
                bVar2.e = obj;
                return bVar2;
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        A a7 = (A) obj;
        C1227c cVar = (C1227c) obj2;
        switch (this.d) {
            case 0:
                return ((C1099b) create(a7, cVar)).invokeSuspend(x.f4917a);
            default:
                return ((C1099b) create(a7, cVar)).invokeSuspend(x.f4917a);
        }
    }

    public final Object invokeSuspend(Object obj) {
        int i2 = this.d;
        c cVar = this.g;
        Iterable<Object> iterable = this.f;
        switch (i2) {
            case 0:
                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                A a7 = (A) this.e;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (Object aVar2 : iterable) {
                    arrayList.add(D.b(a7, (C0888z) null, (C) null, new C1098a(cVar, aVar2, (C1227c) null, 0), 3));
                }
                return arrayList;
            default:
                C1245a aVar3 = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                A a10 = (A) this.e;
                ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
                for (Object aVar4 : iterable) {
                    arrayList2.add(D.n(a10, (C0886x) null, (C) null, new C1098a(cVar, aVar4, (C1227c) null, 1), 3));
                }
                return arrayList2;
        }
    }
}
