package ee;

import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ee.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0979l {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f4300a = Logger.getLogger(C0979l.class.getName());
    public static final C0979l b = new Object();

    public static C0979l b() {
        ((f0) C0977j.f4299a).getClass();
        C0979l lVar = (C0979l) f0.b.get();
        C0979l lVar2 = b;
        if (lVar == null) {
            lVar = lVar2;
        }
        if (lVar == null) {
            return lVar2;
        }
        return lVar;
    }

    public final C0979l a() {
        ((f0) C0977j.f4299a).getClass();
        ThreadLocal threadLocal = f0.b;
        C0979l lVar = (C0979l) threadLocal.get();
        C0979l lVar2 = b;
        if (lVar == null) {
            lVar = lVar2;
        }
        threadLocal.set(this);
        if (lVar == null) {
            return lVar2;
        }
        return lVar;
    }

    public final void c(C0979l lVar) {
        if (lVar != null) {
            ThreadLocal threadLocal = f0.b;
            ((f0) C0977j.f4299a).getClass();
            C0979l lVar2 = (C0979l) threadLocal.get();
            C0979l lVar3 = b;
            if (lVar2 == null) {
                lVar2 = lVar3;
            }
            if (lVar2 != this) {
                f0.f4296a.log(Level.SEVERE, "Context was not attached when detaching", new Throwable().fillInStackTrace());
            }
            if (lVar != lVar3) {
                threadLocal.set(lVar);
            } else {
                threadLocal.set((Object) null);
            }
        } else {
            throw new NullPointerException("toAttach");
        }
    }
}
