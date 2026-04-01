package Vf;

import Wf.e;
import cg.n;
import cg.u;
import eg.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class F {

    /* renamed from: a  reason: collision with root package name */
    public static final I f3841a;

    static {
        String str;
        boolean z;
        E e;
        int i2 = u.f4034a;
        try {
            str = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            str = null;
        }
        if (str != null) {
            z = Boolean.parseBoolean(str);
        } else {
            z = false;
        }
        if (!z) {
            e = E.k;
        } else {
            f fVar = M.f3843a;
            e eVar = n.f4030a;
            e eVar2 = eVar.f;
            e = eVar;
            if (eVar == null) {
                e = E.k;
            }
        }
        f3841a = e;
    }
}
