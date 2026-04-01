package cg;

import Sf.o;
import Wf.a;
import Wf.e;
import Wf.g;
import android.os.Looper;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    public static final e f4030a;

    static {
        String str;
        int i2 = u.f4034a;
        Object obj = null;
        try {
            str = System.getProperty("kotlinx.coroutines.fast.service.loader");
        } catch (SecurityException unused) {
            str = null;
        }
        if (str != null) {
            Boolean.parseBoolean(str);
        }
        try {
            Iterator it = Arrays.asList(new a[]{new a()}).iterator();
            j.e(it, "<this>");
            Iterator it2 = Sf.n.v0(new Sf.a(new o(1, it))).iterator();
            if (it2.hasNext()) {
                obj = it2.next();
                if (it2.hasNext()) {
                    ((a) obj).getClass();
                    do {
                        ((a) it2.next()).getClass();
                    } while (it2.hasNext());
                }
            }
            if (((a) obj) != null) {
                Looper mainLooper = Looper.getMainLooper();
                if (mainLooper != null) {
                    f4030a = new e(false, g.a(mainLooper));
                    return;
                }
                throw new IllegalStateException("The main looper is not available");
            }
            throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }
}
