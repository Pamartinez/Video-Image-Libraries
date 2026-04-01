package Sd;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D extends i {
    public D(Context context) {
        super(context, "BLOCKING");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|18|19|20) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|(3:4|5|(1:34))(3:11|12|(2:14|15)(5:16|17|18|19|20))|8|9|35|(1:(1:27))) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0053 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0019 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0053=Splitter:B:18:0x0053, B:8:0x0019=Splitter:B:8:0x0019} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.os.Bundle r6, java.lang.String r7, java.util.function.Consumer r8) {
        /*
            r5 = this;
            android.content.Context r0 = r5.f3704a
            java.lang.String r1 = "call : method = "
            java.lang.String r5 = r5.b
            android.content.ContentResolver r2 = r0.getContentResolver()     // Catch:{ all -> 0x001d }
            android.net.Uri r3 = Sd.l.f3708a     // Catch:{ all -> 0x001d }
            android.content.ContentProviderClient r2 = r2.acquireUnstableContentProviderClient(r3)     // Catch:{ all -> 0x001d }
            if (r2 != 0) goto L_0x001f
            android.os.Bundle r6 = Sd.i.d     // Catch:{ all -> 0x0017 }
            r8.accept(r6)     // Catch:{ all -> 0x0017 }
        L_0x0017:
            if (r2 == 0) goto L_0x0072
        L_0x0019:
            r2.close()     // Catch:{ all -> 0x001d }
            goto L_0x0072
        L_0x001d:
            r6 = move-exception
            goto L_0x0061
        L_0x001f:
            java.lang.String r3 = r1.concat(r7)     // Catch:{ all -> 0x0057 }
            android.util.Log.i(r5, r3)     // Catch:{ all -> 0x0057 }
            java.lang.String r3 = "packageName"
            Sd.f r4 = Sd.f.C(r0)     // Catch:{ all -> 0x0057 }
            java.lang.String r4 = r4.f3700a     // Catch:{ all -> 0x0057 }
            r6.putString(r3, r4)     // Catch:{ all -> 0x0057 }
            java.lang.String r3 = "blockingRequest"
            r4 = 1
            r6.putBoolean(r3, r4)     // Catch:{ all -> 0x0057 }
            java.lang.String r1 = r1.concat(r7)     // Catch:{ all -> 0x0057 }
            android.util.Log.i(r5, r1)     // Catch:{ all -> 0x0057 }
            java.lang.String r1 = "com.samsung.android.scloud.gallery"
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0057 }
            android.os.Bundle r6 = r2.call(r1, r7, r0, r6)     // Catch:{ all -> 0x0057 }
            if (r6 != 0) goto L_0x0050
            android.os.Bundle r6 = Sd.i.f3703c     // Catch:{ all -> 0x0019 }
            r8.accept(r6)     // Catch:{ all -> 0x0019 }
            goto L_0x0019
        L_0x0050:
            r8.accept(r6)     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r2.close()     // Catch:{ all -> 0x001d }
            return r4
        L_0x0057:
            r6 = move-exception
            r2.close()     // Catch:{ all -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r7 = move-exception
            r6.addSuppressed(r7)     // Catch:{ all -> 0x001d }
        L_0x0060:
            throw r6     // Catch:{ all -> 0x001d }
        L_0x0061:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "Unknown exception. "
            r7.<init>(r0)
            i.C0212a.y(r6, r7, r5)
            android.os.Bundle r5 = Sd.i.b(r6)     // Catch:{ all -> 0x0072 }
            r8.accept(r5)     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: Sd.D.a(android.os.Bundle, java.lang.String, java.util.function.Consumer):boolean");
    }
}
