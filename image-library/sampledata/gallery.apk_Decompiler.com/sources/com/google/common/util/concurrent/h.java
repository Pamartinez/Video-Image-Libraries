package com.google.common.util.concurrent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends C0122a {
    public final boolean a(q qVar, C0126e eVar, C0126e eVar2) {
        synchronized (qVar) {
            try {
                if (qVar.listeners != eVar) {
                    return false;
                }
                C0126e unused = qVar.listeners = eVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean b(q qVar, Object obj, Object obj2) {
        synchronized (qVar) {
            try {
                if (qVar.value != obj) {
                    return false;
                }
                Object unused = qVar.value = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean c(q qVar, p pVar, p pVar2) {
        synchronized (qVar) {
            try {
                if (qVar.waiters != pVar) {
                    return false;
                }
                p unused = qVar.waiters = pVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final C0126e d(q qVar) {
        C0126e access$700;
        C0126e eVar = C0126e.d;
        synchronized (qVar) {
            try {
                access$700 = qVar.listeners;
                if (access$700 != eVar) {
                    C0126e unused = qVar.listeners = eVar;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return access$700;
    }

    public final p e(q qVar) {
        p access$800;
        p pVar = p.f1570c;
        synchronized (qVar) {
            try {
                access$800 = qVar.waiters;
                if (access$800 != pVar) {
                    p unused = qVar.waiters = pVar;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return access$800;
    }

    public final void f(p pVar, p pVar2) {
        pVar.b = pVar2;
    }

    public final void g(p pVar, Thread thread) {
        pVar.f1571a = thread;
    }
}
