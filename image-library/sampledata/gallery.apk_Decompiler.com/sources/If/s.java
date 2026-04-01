package If;

import Hf.C0754c;
import Hf.C0764m;
import Hf.K;
import Hf.c0;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum s {
    ;

    static {
        s[] sVarArr;
        $ENTRIES = c.t(sVarArr);
    }

    public static s b(c0 c0Var) {
        j.e(c0Var, "<this>");
        if (c0Var.u0()) {
            return ACCEPT_NULL;
        }
        if (c0Var instanceof C0764m) {
            C0764m mVar = (C0764m) c0Var;
        }
        if (C0754c.h(g.l(false, (e) null, 24), C0754c.m(c0Var), K.b)) {
            return NOT_NULL;
        }
        return UNKNOWN;
    }

    public abstract s a(c0 c0Var);
}
