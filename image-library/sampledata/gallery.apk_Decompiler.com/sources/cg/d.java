package cg;

import Sf.a;
import Sf.n;
import Sf.o;
import Wf.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    public static final Collection f4019a;

    static {
        try {
            Iterator it = Arrays.asList(new b[]{new b()}).iterator();
            j.e(it, "<this>");
            f4019a = n.v0(new a(new o(1, it)));
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }
}
