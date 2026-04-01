package com.samsung.android.sdk.scs.ai.visual.c2pa;

import com.samsung.android.visual.ai.sdkcommon.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C2paManifestsCallback extends e {
    public C2paManifestsCallback() {
        attachInterface(this, "com.samsung.android.visual.ai.sdkcommon.IC2paManifestsCallback");
    }

    public abstract /* synthetic */ void onError(String str);

    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x006b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPfdCreation(android.os.ParcelFileDescriptor r8, boolean r9) {
        /*
            r7 = this;
            java.lang.String r0 = "Error while closing pfd"
            java.lang.String r1 = "C2paClient"
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x006b }
            java.io.FileDescriptor r3 = r8.getFileDescriptor()     // Catch:{ Exception -> 0x006b }
            r2.<init>(r3)     // Catch:{ Exception -> 0x006b }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x004b }
            r3.<init>(r2)     // Catch:{ all -> 0x004b }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ all -> 0x004d }
            r4.<init>(r3)     // Catch:{ all -> 0x004d }
            java.util.stream.Stream r5 = r4.lines()     // Catch:{ all -> 0x004f }
            java.lang.String r6 = "\n"
            java.util.stream.Collector r6 = java.util.stream.Collectors.joining(r6)     // Catch:{ all -> 0x004f }
            java.lang.Object r5 = r5.collect(r6)     // Catch:{ all -> 0x004f }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x004f }
            r6 = 1
            r7.onResult(r5, r9, r6)     // Catch:{ all -> 0x004f }
            r4.close()     // Catch:{ all -> 0x004d }
            r3.close()     // Catch:{ all -> 0x004b }
            r2.close()     // Catch:{ Exception -> 0x006b }
            r8.close()     // Catch:{ IOException | SecurityException -> 0x0038 }
            return
        L_0x0038:
            r8 = move-exception
            android.util.Log.e(r1, r0)
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError r9 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError.PFD_READ_ERROR
            java.lang.String r9 = r9.getErrString()
            r7.onError(r9)
            r8.printStackTrace()
            goto L_0x007c
        L_0x0049:
            r9 = move-exception
            goto L_0x007d
        L_0x004b:
            r9 = move-exception
            goto L_0x0062
        L_0x004d:
            r9 = move-exception
            goto L_0x0059
        L_0x004f:
            r9 = move-exception
            r4.close()     // Catch:{ all -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r4 = move-exception
            r9.addSuppressed(r4)     // Catch:{ all -> 0x004d }
        L_0x0058:
            throw r9     // Catch:{ all -> 0x004d }
        L_0x0059:
            r3.close()     // Catch:{ all -> 0x005d }
            goto L_0x0061
        L_0x005d:
            r3 = move-exception
            r9.addSuppressed(r3)     // Catch:{ all -> 0x004b }
        L_0x0061:
            throw r9     // Catch:{ all -> 0x004b }
        L_0x0062:
            r2.close()     // Catch:{ all -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r2 = move-exception
            r9.addSuppressed(r2)     // Catch:{ Exception -> 0x006b }
        L_0x006a:
            throw r9     // Catch:{ Exception -> 0x006b }
        L_0x006b:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError r9 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError.PFD_READ_ERROR     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = r9.getErrString()     // Catch:{ all -> 0x0049 }
            r7.onError(r9)     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = "Error while reading c2pa manifest from pfd"
            android.util.Log.e(r1, r9)     // Catch:{ all -> 0x0049 }
            r8.close()     // Catch:{ IOException | SecurityException -> 0x0038 }
        L_0x007c:
            return
        L_0x007d:
            r8.close()     // Catch:{ IOException | SecurityException -> 0x0081 }
            goto L_0x0091
        L_0x0081:
            r8 = move-exception
            android.util.Log.e(r1, r0)
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError r0 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError.PFD_READ_ERROR
            java.lang.String r0 = r0.getErrString()
            r7.onError(r0)
            r8.printStackTrace()
        L_0x0091:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestsCallback.onPfdCreation(android.os.ParcelFileDescriptor, boolean):void");
    }

    public abstract /* synthetic */ void onResult(String str, boolean z, boolean z3);
}
