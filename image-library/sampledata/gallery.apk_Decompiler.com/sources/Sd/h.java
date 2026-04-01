package Sd;

import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends i {
    /* JADX WARNING: Can't wrap try/catch for region: R(6:17|18|19|20|21|38) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0088 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.os.Bundle r9, java.lang.String r10, java.util.function.Consumer r11) {
        /*
            r8 = this;
            java.lang.String r0 = r8.b
            android.content.Context r1 = r8.f3704a
            r2 = 0
            r3 = 0
            android.content.ContentResolver r4 = r1.getContentResolver()     // Catch:{ all -> 0x001e }
            android.net.Uri r5 = Sd.l.f3708a     // Catch:{ all -> 0x001e }
            android.content.ContentProviderClient r4 = r4.acquireUnstableContentProviderClient(r5)     // Catch:{ all -> 0x001e }
            if (r4 != 0) goto L_0x0021
            android.os.Bundle r9 = Sd.i.d     // Catch:{ all -> 0x0017 }
            r11.accept(r9)     // Catch:{ all -> 0x0017 }
        L_0x0017:
            if (r4 == 0) goto L_0x00b5
            r4.close()     // Catch:{ all -> 0x001e }
            goto L_0x00b5
        L_0x001e:
            r9 = move-exception
            goto L_0x00a1
        L_0x0021:
            Sd.g r5 = new Sd.g     // Catch:{ all -> 0x0097 }
            r5.<init>(r8, r11)     // Catch:{ all -> 0x0097 }
            r3 = 64
            byte[] r3 = new byte[r3]     // Catch:{ all -> 0x008f }
            java.security.SecureRandom r6 = new java.security.SecureRandom     // Catch:{ all -> 0x008f }
            r6.<init>()     // Catch:{ all -> 0x008f }
            r6.nextBytes(r3)     // Catch:{ all -> 0x008f }
            java.lang.String r6 = new java.lang.String     // Catch:{ all -> 0x008f }
            java.util.Base64$Encoder r7 = java.util.Base64.getEncoder()     // Catch:{ all -> 0x008f }
            byte[] r3 = r7.encode(r3)     // Catch:{ all -> 0x008f }
            r6.<init>(r3)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = "com.samsung.scloud.gallery."
            java.lang.String r3 = r3.concat(r6)     // Catch:{ all -> 0x008f }
            java.lang.String r6 = "actionName"
            r9.putString(r6, r3)     // Catch:{ all -> 0x008f }
            android.content.IntentFilter r6 = new android.content.IntentFilter     // Catch:{ all -> 0x008f }
            r6.<init>()     // Catch:{ all -> 0x008f }
            r6.addAction(r3)     // Catch:{ all -> 0x008f }
            r3 = 2
            r1.registerReceiver(r5, r6, r3)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = "packageName"
            Sd.f r6 = Sd.f.C(r1)     // Catch:{ all -> 0x008f }
            java.lang.String r6 = r6.f3700a     // Catch:{ all -> 0x008f }
            r9.putString(r3, r6)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = "blockingRequest"
            r9.putBoolean(r3, r2)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = "call : method = "
            java.lang.String r3 = r3.concat(r10)     // Catch:{ all -> 0x008f }
            android.util.Log.i(r0, r3)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = "com.samsung.android.scloud.gallery"
            java.lang.String r1 = r1.getPackageName()     // Catch:{ all -> 0x008f }
            android.os.Bundle r9 = r4.call(r3, r10, r1, r9)     // Catch:{ all -> 0x008f }
            if (r9 != 0) goto L_0x0092
            java.lang.String r9 = "call failed. result is null"
            android.util.Log.e(r0, r9)     // Catch:{ all -> 0x008f }
            r8.c(r5)     // Catch:{ all -> 0x008f }
            android.os.Bundle r9 = Sd.i.f3703c     // Catch:{ all -> 0x0088 }
            r11.accept(r9)     // Catch:{ all -> 0x0088 }
        L_0x0088:
            r4.close()     // Catch:{ all -> 0x008c }
            goto L_0x00b5
        L_0x008c:
            r9 = move-exception
            r3 = r5
            goto L_0x00a1
        L_0x008f:
            r9 = move-exception
            r3 = r5
            goto L_0x0098
        L_0x0092:
            r9 = 1
            r4.close()     // Catch:{ all -> 0x008c }
            return r9
        L_0x0097:
            r9 = move-exception
        L_0x0098:
            r4.close()     // Catch:{ all -> 0x009c }
            goto L_0x00a0
        L_0x009c:
            r10 = move-exception
            r9.addSuppressed(r10)     // Catch:{ all -> 0x001e }
        L_0x00a0:
            throw r9     // Catch:{ all -> 0x001e }
        L_0x00a1:
            r8.c(r3)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r10 = "Unknown exception. "
            r8.<init>(r10)
            i.C0212a.y(r9, r8, r0)
            android.os.Bundle r8 = Sd.i.b(r9)     // Catch:{ all -> 0x00b5 }
            r11.accept(r8)     // Catch:{ all -> 0x00b5 }
        L_0x00b5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Sd.h.a(android.os.Bundle, java.lang.String, java.util.function.Consumer):boolean");
    }

    public final void c(g gVar) {
        if (gVar != null) {
            try {
                this.f3704a.unregisterReceiver(gVar);
            } catch (Throwable th) {
                C0212a.y(th, new StringBuilder("unregisterReceiver failed. "), this.b);
            }
        }
    }
}
