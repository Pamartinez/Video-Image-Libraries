package Hf;

import D0.e;
import Ge.a;
import Nf.c;
import Nf.j;
import Nf.p;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.v;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class I extends c {
    public static final e e = new e(6);
    public static final I f = new I(C1202t.d);

    /* JADX WARNING: type inference failed for: r4v0, types: [Ge.a, Nf.b, java.lang.Object] */
    public I(List list) {
        this.d = j.d;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C0759h hVar = (C0759h) it.next();
            hVar.getClass();
            String n = v.f4727a.b(C0759h.class).n();
            kotlin.jvm.internal.j.b(n);
            int J4 = e.J(n);
            int i2 = this.d.i();
            if (i2 != 0) {
                if (i2 == 1) {
                    a aVar = this.d;
                    kotlin.jvm.internal.j.c(aVar, "null cannot be cast to non-null type org.jetbrains.kotlin.util.OneElementArrayMap<T of org.jetbrains.kotlin.util.AttributeArrayOwner>");
                    p pVar = (p) aVar;
                    int i7 = pVar.e;
                    if (i7 == J4) {
                        this.d = new p(J4, hVar);
                    } else {
                        ? obj = new Object();
                        obj.d = new Object[20];
                        obj.e = 0;
                        this.d = obj;
                        obj.p(i7, pVar.d);
                    }
                }
                this.d.p(J4, hVar);
            } else {
                this.d = new p(J4, hVar);
            }
        }
    }
}
