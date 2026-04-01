package tf;

import Ae.c;
import Qe.C0812b;
import Qe.C0822l;
import kotlin.jvm.internal.j;

/* renamed from: tf.b  reason: case insensitive filesystem */
public final class C1298b implements c {
    public final C0812b d;
    public final C0812b e;

    public C1298b(C0812b bVar, C0812b bVar2) {
        this.d = bVar;
        this.e = bVar2;
    }

    public final Object invoke(Object obj, Object obj2) {
        boolean z;
        C0822l lVar = (C0822l) obj2;
        if (!j.a((C0822l) obj, this.d) || !j.a(lVar, this.e)) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
