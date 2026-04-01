package com.samsung.android.gallery.support.utils;

import E3.e;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RuntimeCompat {
    public static HashMap<String, Long> du(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        File file = new File(str);
        String str2 = "du -b -d 1 " + file.getName();
        HashMap<String, Long> hashMap = new HashMap<>();
        exec(str2, file.getParentFile(), new e((HashMap) hashMap, file, str2, currentTimeMillis));
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006a, code lost:
        if (r5 == null) goto L_0x006d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void exec(java.lang.String r9, java.io.File r10, java.util.function.Consumer<java.util.List<java.lang.String>> r11) {
        /*
            java.lang.String r0 = "exec ["
            java.lang.String r1 = "RuntimeCompat"
            long r2 = java.lang.System.currentTimeMillis()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r5 = 0
            java.lang.Runtime r6 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0041 }
            java.lang.String r7 = "/system/bin/sh"
            java.lang.String r8 = "-c"
            java.lang.String[] r7 = new java.lang.String[]{r7, r8, r9}     // Catch:{ Exception -> 0x0041 }
            java.lang.Process r5 = r6.exec(r7, r5, r10)     // Catch:{ Exception -> 0x0041 }
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0041 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0041 }
            java.io.InputStream r7 = r5.getInputStream()     // Catch:{ Exception -> 0x0041 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0041 }
            r10.<init>(r6)     // Catch:{ Exception -> 0x0041 }
        L_0x002c:
            java.lang.String r6 = r10.readLine()     // Catch:{ all -> 0x0036 }
            if (r6 == 0) goto L_0x0038
            r4.add(r6)     // Catch:{ all -> 0x0036 }
            goto L_0x002c
        L_0x0036:
            r6 = move-exception
            goto L_0x0043
        L_0x0038:
            r10.close()     // Catch:{ Exception -> 0x0041 }
        L_0x003b:
            r5.destroy()
            goto L_0x006d
        L_0x003f:
            r9 = move-exception
            goto L_0x007c
        L_0x0041:
            r10 = move-exception
            goto L_0x004c
        L_0x0043:
            r10.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r10 = move-exception
            r6.addSuppressed(r10)     // Catch:{ Exception -> 0x0041 }
        L_0x004b:
            throw r6     // Catch:{ Exception -> 0x0041 }
        L_0x004c:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x003f }
            r6.<init>()     // Catch:{ all -> 0x003f }
            r6.append(r0)     // Catch:{ all -> 0x003f }
            r6.append(r9)     // Catch:{ all -> 0x003f }
            java.lang.String r7 = "] failed. e="
            r6.append(r7)     // Catch:{ all -> 0x003f }
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x003f }
            r6.append(r10)     // Catch:{ all -> 0x003f }
            java.lang.String r10 = r6.toString()     // Catch:{ all -> 0x003f }
            com.samsung.android.gallery.support.utils.Log.e(r1, r10)     // Catch:{ all -> 0x003f }
            if (r5 == 0) goto L_0x006d
            goto L_0x003b
        L_0x006d:
            java.lang.String r10 = "] +"
            java.lang.StringBuilder r9 = N2.j.k(r0, r9, r10)
            A.a.x(r9, r2, r1)
            if (r11 == 0) goto L_0x007b
            r11.accept(r4)
        L_0x007b:
            return
        L_0x007c:
            if (r5 == 0) goto L_0x0081
            r5.destroy()
        L_0x0081:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.RuntimeCompat.exec(java.lang.String, java.io.File, java.util.function.Consumer):void");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$du$0(HashMap hashMap, File file, String str, long j2, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String[] split = ((String) it.next()).split("\\s", 2);
            hashMap.put(split[1], Long.valueOf(Math.max(UnsafeCast.toLong(split[0], 0) - 3452, 0)));
        }
    }
}
