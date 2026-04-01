package se;

import A0.l;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.jvm.internal.j;
import qe.C1227c;

/* renamed from: se.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1269a implements C1227c, C1272d, Serializable {
    private final C1227c completion;

    public C1269a(C1227c cVar) {
        this.completion = cVar;
    }

    public C1227c create(C1227c cVar) {
        j.e(cVar, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    public C1272d getCallerFrame() {
        C1227c cVar = this.completion;
        if (cVar instanceof C1272d) {
            return (C1272d) cVar;
        }
        return null;
    }

    public final C1227c getCompletion() {
        return this.completion;
    }

    public StackTraceElement getStackTraceElement() {
        int i2;
        String str;
        Method method;
        Object invoke;
        Method method2;
        Object invoke2;
        Object obj;
        Integer num;
        int i7;
        C1273e eVar = (C1273e) getClass().getAnnotation(C1273e.class);
        String str2 = null;
        if (eVar == null) {
            return null;
        }
        int v = eVar.v();
        if (v <= 1) {
            int i8 = -1;
            try {
                Field declaredField = getClass().getDeclaredField("label");
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(this);
                if (obj2 instanceof Integer) {
                    num = (Integer) obj2;
                } else {
                    num = null;
                }
                if (num != null) {
                    i7 = num.intValue();
                } else {
                    i7 = 0;
                }
                i2 = i7 - 1;
            } catch (Exception unused) {
                i2 = -1;
            }
            if (i2 >= 0) {
                i8 = eVar.l()[i2];
            }
            l lVar = f.b;
            l lVar2 = f.f5071a;
            if (lVar == null) {
                try {
                    l lVar3 = new l(Class.class.getDeclaredMethod("getModule", (Class[]) null), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", (Class[]) null), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", (Class[]) null), false, 19);
                    f.b = lVar3;
                    lVar = lVar3;
                } catch (Exception unused2) {
                    f.b = lVar2;
                    lVar = lVar2;
                }
            }
            if (!(lVar == lVar2 || (method = (Method) lVar.e) == null || (invoke = method.invoke(getClass(), (Object[]) null)) == null || (method2 = (Method) lVar.f) == null || (invoke2 = method2.invoke(invoke, (Object[]) null)) == null)) {
                Method method3 = (Method) lVar.g;
                if (method3 != null) {
                    obj = method3.invoke(invoke2, (Object[]) null);
                } else {
                    obj = null;
                }
                if (obj instanceof String) {
                    str2 = (String) obj;
                }
            }
            if (str2 == null) {
                str = eVar.c();
            } else {
                str = str2 + '/' + eVar.c();
            }
            return new StackTraceElement(str, eVar.m(), eVar.f(), i8);
        }
        throw new IllegalStateException(("Debug metadata version mismatch. Expected: 1, got " + v + ". Please update the Kotlin standard library.").toString());
    }

    public abstract Object invokeSuspend(Object obj);

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: se.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: qe.c} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: se.a} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void resumeWith(java.lang.Object r3) {
        /*
            r2 = this;
        L_0x0000:
            se.a r2 = (se.C1269a) r2
            qe.c r0 = r2.completion
            kotlin.jvm.internal.j.b(r0)
            java.lang.Object r3 = r2.invokeSuspend(r3)     // Catch:{ all -> 0x0010 }
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED     // Catch:{ all -> 0x0010 }
            if (r3 != r1) goto L_0x0015
            return
        L_0x0010:
            r3 = move-exception
            me.j r3 = L2.a.l(r3)
        L_0x0015:
            r2.releaseIntercepted()
            boolean r2 = r0 instanceof se.C1269a
            if (r2 == 0) goto L_0x001e
            r2 = r0
            goto L_0x0000
        L_0x001e:
            r0.resumeWith(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: se.C1269a.resumeWith(java.lang.Object):void");
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb2.append(stackTraceElement);
        return sb2.toString();
    }

    public C1227c create(Object obj, C1227c cVar) {
        j.e(cVar, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    public void releaseIntercepted() {
    }
}
