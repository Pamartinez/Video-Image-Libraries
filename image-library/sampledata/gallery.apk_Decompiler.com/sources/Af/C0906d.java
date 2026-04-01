package af;

import Ae.b;
import Hf.C0774x;
import Jf.k;
import Jf.l;
import Ne.p;
import Qe.C;
import Te.Q;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;

/* renamed from: af.d  reason: case insensitive filesystem */
public final class C0906d implements b {
    public static final C0906d d = new Object();

    public final Object invoke(Object obj) {
        C0774x type;
        C c5 = (C) obj;
        Object obj2 = C0907e.f3994a;
        j.e(c5, "module");
        Q x9 = c.x(C0905c.b, c5.f().i(p.t));
        if (x9 == null || (type = x9.getType()) == null) {
            return l.c(k.UNMAPPED_ANNOTATION_TARGET_TYPE, new String[0]);
        }
        return type;
    }
}
