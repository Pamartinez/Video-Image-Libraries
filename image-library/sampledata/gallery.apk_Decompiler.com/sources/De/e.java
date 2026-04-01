package de;

import com.samsung.android.gallery.support.config.SdkConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e {
    public static final boolean b = SdkConfig.atLeast(SdkConfig.SEM.B_MR5);

    /* renamed from: a  reason: collision with root package name */
    public final f f4230a;

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0041 A[Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d A[Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public e(java.io.FileDescriptor r8) {
        /*
            r7 = this;
            java.lang.String r0 = "Unknown magic code ["
            r7.<init>()
            r1 = 12
            byte[] r2 = new byte[r1]     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            int r3 = android.system.OsConstants.SEEK_SET     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r4 = 0
            android.system.Os.lseek(r8, r4, r3)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r3 = 0
            android.system.Os.read(r8, r2, r3, r1)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            byte r4 = r2[r3]     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r5 = 255(0xff, float:3.57E-43)
            r4 = r4 & r5
            if (r4 != r5) goto L_0x0024
            r4 = 1
            byte r6 = r2[r4]     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r5 = r5 & r6
            r6 = 216(0xd8, float:3.03E-43)
            if (r5 != r6) goto L_0x0024
            goto L_0x0025
        L_0x0024:
            r4 = r3
        L_0x0025:
            r5 = 4
            byte r5 = r2[r5]     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r6 = 102(0x66, float:1.43E-43)
            if (r5 != r6) goto L_0x004d
            r5 = 5
            byte r5 = r2[r5]     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r6 = 116(0x74, float:1.63E-43)
            if (r5 != r6) goto L_0x004d
            r5 = 6
            byte r5 = r2[r5]     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r6 = 121(0x79, float:1.7E-43)
            if (r5 != r6) goto L_0x004d
            r5 = 7
            byte r5 = r2[r5]     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r6 = 112(0x70, float:1.57E-43)
            if (r5 != r6) goto L_0x004d
            boolean r0 = b     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            if (r0 == 0) goto L_0x004c
            de.b r0 = new de.b     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r0.<init>(r8)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r7.f4230a = r0     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
        L_0x004c:
            return
        L_0x004d:
            if (r4 == 0) goto L_0x0057
            de.c r0 = new de.c     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r0.<init>(r8)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r7.f4230a = r0     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            return
        L_0x0057:
            java.lang.String r7 = "MetaBoxFinder"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r8.<init>(r0)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            java.util.stream.IntStream r0 = java.util.stream.IntStream.range(r3, r1)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            A4.v r1 = new A4.v     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r3 = 3
            r1.<init>(r3, r2)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            java.util.stream.Stream r0 = r0.mapToObj(r1)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            java.lang.String r1 = ":"
            java.util.stream.Collector r1 = java.util.stream.Collectors.joining(r1)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            java.lang.Object r0 = r0.collect(r1)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            r8.append(r0)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            java.lang.String r0 = "]"
            r8.append(r0)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            java.lang.String r8 = r8.toString()     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            android.util.Log.w(r7, r8)     // Catch:{ ErrnoException -> 0x00a1, OutOfMemoryError -> 0x0088 }
            return
        L_0x0088:
            r7 = move-exception
            java.io.IOException r8 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Too many boxes in file. This might imply a corrupted file. e="
            r0.<init>(r1)
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.<init>(r7)
            throw r8
        L_0x00a1:
            r7 = move-exception
            java.io.IOException r7 = r7.rethrowAsIOException()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: de.e.<init>(java.io.FileDescriptor):void");
    }
}
