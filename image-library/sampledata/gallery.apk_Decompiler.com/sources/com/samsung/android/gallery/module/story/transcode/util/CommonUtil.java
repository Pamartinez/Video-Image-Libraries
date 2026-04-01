package com.samsung.android.gallery.module.story.transcode.util;

import java.io.File;
import java.io.RandomAccessFile;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CommonUtil {
    public static File createFile(String str) {
        return new File(str);
    }

    public static RandomAccessFile createRandomAccessFile(File file, String str) {
        return new RandomAccessFile(file, str);
    }

    public static long unsignedIntToLong(byte[] bArr) {
        return (((((((long) (bArr[0] & 255)) << 8) | ((long) (bArr[1] & 255))) << 8) | ((long) (bArr[2] & 255))) << 8) | ((long) (bArr[3] & 255));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00aa, code lost:
        com.samsung.android.gallery.support.utils.Log.d("CommonUtil", "Found: mvhd");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r10.read(r5, 0, 4);
        r10.writeLong(java.lang.System.currentTimeMillis());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ba, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00bc, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00bd, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x011a, code lost:
        if (r10 != null) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0120, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0121, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0128, code lost:
        if (r10 != null) goto L_0x011c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean updateCreationTime(java.lang.String r21) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "updateCreationTime filepath : "
            r0.<init>(r1)
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r21)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "CommonUtil"
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
            java.io.File r0 = createFile(r21)
            r2 = 4
            byte[] r3 = new byte[r2]
            byte[] r4 = new byte[r2]
            byte[] r5 = new byte[r2]
            long r6 = r0.length()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "file size: "
            r8.<init>(r9)
            r8.append(r6)
            java.lang.String r8 = r8.toString()
            com.samsung.android.gallery.support.utils.Log.d(r1, r8)
            java.lang.String r8 = "stbl"
            java.lang.String r9 = "trak"
            java.lang.String r10 = "mdia"
            java.lang.String r11 = "minf"
            java.lang.String r12 = "moov"
            java.lang.String[] r8 = new java.lang.String[]{r10, r11, r12, r8, r9}
            r9 = 0
            r10 = 0
            java.lang.String r11 = "rw"
            java.io.RandomAccessFile r10 = createRandomAccessFile(r0, r11)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r13 = 0
        L_0x0053:
            int r0 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x011a
            r10.seek(r13)     // Catch:{ IOException -> 0x0062 }
            goto L_0x0066
        L_0x005b:
            r0 = move-exception
            r1 = r0
            goto L_0x012c
        L_0x005f:
            r0 = move-exception
            goto L_0x0125
        L_0x0062:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ IOException | NullPointerException -> 0x005f }
        L_0x0066:
            r10.read(r3, r9, r2)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r15 = 0
            long r11 = unsignedIntToLong(r3)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r10.read(r4, r9, r2)     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.lang.String r0 = new java.lang.String     // Catch:{ IOException | NullPointerException -> 0x005f }
            r17 = r15
            java.nio.charset.Charset r15 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ IOException | NullPointerException -> 0x005f }
            r0.<init>(r4, r15)     // Catch:{ IOException | NullPointerException -> 0x005f }
            int r15 = java.util.Arrays.binarySearch(r8, r0)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r19 = 8
            if (r15 < 0) goto L_0x00a2
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException | NullPointerException -> 0x005f }
            r11.<init>()     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.lang.String r12 = "Found parent: "
            r11.append(r12)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r11.append(r0)     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.lang.String r0 = " move to position: "
            r11.append(r0)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r11.append(r15)     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.lang.String r0 = r11.toString()     // Catch:{ IOException | NullPointerException -> 0x005f }
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)     // Catch:{ IOException | NullPointerException -> 0x005f }
            long r13 = r13 + r19
            goto L_0x0053
        L_0x00a2:
            java.lang.String r15 = "mvhd"
            boolean r0 = r0.equals(r15)     // Catch:{ IOException | NullPointerException -> 0x005f }
            if (r0 == 0) goto L_0x00bf
            java.lang.String r0 = "Found: mvhd"
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r1 = 1
            r10.read(r5, r9, r2)     // Catch:{ IOException | NullPointerException -> 0x00bc }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ IOException | NullPointerException -> 0x00bc }
            r10.writeLong(r2)     // Catch:{ IOException | NullPointerException -> 0x00bc }
            r9 = r1
            goto L_0x011a
        L_0x00bc:
            r0 = move-exception
            r9 = r1
            goto L_0x0125
        L_0x00bf:
            r15 = 1
            int r0 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r0 != 0) goto L_0x00f1
            long r11 = r13 + r19
            r10.seek(r11)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r0 = 8
            byte[] r11 = new byte[r0]     // Catch:{ IOException | NullPointerException -> 0x005f }
            r10.read(r11, r9, r0)     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.math.BigInteger r0 = new java.math.BigInteger     // Catch:{ IOException | NullPointerException -> 0x005f }
            r0.<init>(r11)     // Catch:{ IOException | NullPointerException -> 0x005f }
            long r11 = r0.longValue()     // Catch:{ IOException | NullPointerException -> 0x005f }
            long r13 = r13 + r11
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException | NullPointerException -> 0x005f }
            r0.<init>()     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.lang.String r15 = "64bit: "
            r0.append(r15)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r0.append(r11)     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException | NullPointerException -> 0x005f }
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)     // Catch:{ IOException | NullPointerException -> 0x005f }
            goto L_0x0053
        L_0x00f1:
            int r0 = (r11 > r17 ? 1 : (r11 == r17 ? 0 : -1))
            if (r0 != 0) goto L_0x00fb
            java.lang.String r0 = "filePointer does not go forward. Exit."
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)     // Catch:{ IOException | NullPointerException -> 0x005f }
            goto L_0x011a
        L_0x00fb:
            long r13 = r13 + r11
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException | NullPointerException -> 0x005f }
            r0.<init>()     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.lang.String r15 = "move: "
            r0.append(r15)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r0.append(r13)     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.lang.String r15 = " atomsize "
            r0.append(r15)     // Catch:{ IOException | NullPointerException -> 0x005f }
            r0.append(r11)     // Catch:{ IOException | NullPointerException -> 0x005f }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException | NullPointerException -> 0x005f }
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)     // Catch:{ IOException | NullPointerException -> 0x005f }
            goto L_0x0053
        L_0x011a:
            if (r10 == 0) goto L_0x012b
        L_0x011c:
            r10.close()     // Catch:{ IOException -> 0x0120 }
            goto L_0x012b
        L_0x0120:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x012b
        L_0x0125:
            r0.printStackTrace()     // Catch:{ all -> 0x005b }
            if (r10 == 0) goto L_0x012b
            goto L_0x011c
        L_0x012b:
            return r9
        L_0x012c:
            if (r10 == 0) goto L_0x0136
            r10.close()     // Catch:{ IOException -> 0x0132 }
            goto L_0x0136
        L_0x0132:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0136:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.story.transcode.util.CommonUtil.updateCreationTime(java.lang.String):boolean");
    }
}
