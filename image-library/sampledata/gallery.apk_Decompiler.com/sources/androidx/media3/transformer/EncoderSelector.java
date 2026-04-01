package androidx.media3.transformer;

import F2.C0020h0;
import F2.C0022i0;
import F2.N;
import F2.U;
import F2.y0;
import W.a;
import c0.C0087b;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface EncoderSelector {
    public static final EncoderSelector DEFAULT = new a(6);

    /* JADX WARNING: type inference failed for: r2v2, types: [F2.N, F2.Q] */
    /* access modifiers changed from: private */
    static U lambda$static$1(String str) {
        U u;
        U supportedEncoders = EncoderUtil.getSupportedEncoders(str);
        C0087b bVar = new C0087b(str, 0);
        supportedEncoders.getClass();
        C0020h0 h0Var = new C0020h0(supportedEncoders, bVar);
        if (h0Var instanceof Collection) {
            u = U.y((Collection) h0Var);
        } else {
            C0022i0 i0Var = (C0022i0) h0Var.iterator();
            if (!i0Var.hasNext()) {
                u = y0.f278h;
            } else {
                Object next = i0Var.next();
                if (!i0Var.hasNext()) {
                    u = U.B(next);
                } else {
                    ? n = new N(4);
                    n.a(next);
                    while (i0Var.hasNext()) {
                        n.a(i0Var.next());
                    }
                    u = n.f();
                }
            }
        }
        if (u.isEmpty()) {
            return supportedEncoders;
        }
        return u;
    }
}
