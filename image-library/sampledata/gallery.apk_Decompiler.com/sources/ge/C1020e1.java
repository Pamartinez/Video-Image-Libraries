package ge;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/* renamed from: ge.e1  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1020e1 implements C1048o0 {
    public static final Logger e = Logger.getLogger(C1020e1.class.getName());
    public static final Constructor f;
    public static final Method g;

    /* renamed from: h  reason: collision with root package name */
    public static final RuntimeException f4507h;

    /* renamed from: i  reason: collision with root package name */
    public static final Object[] f4508i = {1L};
    public final Object d;

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c A[ADDED_TO_REGION] */
    static {
        /*
            java.lang.Class<ge.e1> r0 = ge.C1020e1.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            e = r0
            r0 = 0
            java.lang.String r1 = "java.util.concurrent.atomic.LongAdder"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ all -> 0x003e }
            java.lang.String r2 = "add"
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x003e }
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch:{ all -> 0x003e }
            java.lang.reflect.Method r2 = r1.getMethod(r2, r3)     // Catch:{ all -> 0x003e }
            java.lang.String r3 = "sum"
            r1.getMethod(r3, r0)     // Catch:{ all -> 0x0039 }
            java.lang.reflect.Constructor[] r1 = r1.getConstructors()     // Catch:{ all -> 0x0039 }
            int r3 = r1.length     // Catch:{ all -> 0x0039 }
            r4 = 0
        L_0x002a:
            if (r4 >= r3) goto L_0x003b
            r5 = r1[r4]     // Catch:{ all -> 0x0039 }
            java.lang.Class[] r6 = r5.getParameterTypes()     // Catch:{ all -> 0x0039 }
            int r6 = r6.length     // Catch:{ all -> 0x0039 }
            if (r6 != 0) goto L_0x0036
            goto L_0x003c
        L_0x0036:
            int r4 = r4 + 1
            goto L_0x002a
        L_0x0039:
            r1 = move-exception
            goto L_0x0040
        L_0x003b:
            r5 = r0
        L_0x003c:
            r1 = r0
            goto L_0x004a
        L_0x003e:
            r1 = move-exception
            r2 = r0
        L_0x0040:
            java.util.logging.Logger r3 = e
            java.util.logging.Level r4 = java.util.logging.Level.FINE
            java.lang.String r5 = "LongAdder can not be found via reflection, this is normal for JDK7 and below"
            r3.log(r4, r5, r1)
            r5 = r0
        L_0x004a:
            if (r1 != 0) goto L_0x0055
            if (r5 == 0) goto L_0x0055
            f = r5
            g = r2
            f4507h = r0
            goto L_0x0060
        L_0x0055:
            f = r0
            g = r0
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r1)
            f4507h = r0
        L_0x0060:
            r0 = 1
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            f4508i = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1020e1.<clinit>():void");
    }

    public C1020e1() {
        RuntimeException runtimeException = f4507h;
        if (runtimeException == null) {
            try {
                this.d = f.newInstance((Object[]) null);
            } catch (InstantiationException e7) {
                throw new RuntimeException(e7);
            } catch (IllegalAccessException e8) {
                throw new RuntimeException(e8);
            } catch (InvocationTargetException e9) {
                throw new RuntimeException(e9);
            }
        } else {
            throw runtimeException;
        }
    }

    public final void y() {
        try {
            g.invoke(this.d, f4508i);
        } catch (IllegalAccessException e7) {
            throw new RuntimeException(e7);
        } catch (InvocationTargetException e8) {
            throw new RuntimeException(e8);
        }
    }
}
