package com.samsung.android.gallery.module.graphics;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ImageDecoderSem150Impl extends ImageDecoderImpl {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SemLibHolder {
        static volatile boolean SUPPORT = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0020, code lost:
        if ((r0 instanceof java.lang.NoClassDefFoundError) != false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        com.samsung.android.gallery.module.graphics.ImageDecoderSem150Impl.SemLibHolder.SUPPORT = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0025, code lost:
        r1 = r4.TAG;
        com.samsung.android.gallery.support.utils.Log.e(r1, "decodeByteArray failed. e=" + r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap decodeByteArray(byte[] r5, int r6, int r7, com.samsung.android.gallery.module.graphics.BitmapOptions r8) {
        /*
            r4 = this;
            boolean r0 = com.samsung.android.gallery.module.graphics.ImageDecoderSem150Impl.SemLibHolder.SUPPORT
            if (r0 == 0) goto L_0x003c
            boolean r0 = com.samsung.android.gallery.module.graphics.CodecCompat.PATCH_JPEG_PROGRESSIVE     // Catch:{ Error | Exception -> 0x0013, all -> 0x001c }
            if (r0 == 0) goto L_0x0015
            boolean r0 = com.samsung.android.gallery.module.graphics.CodecCompat.ensureJpegSyntaxCompatible(r5, r6, r7, r8)     // Catch:{ Error | Exception -> 0x0013, all -> 0x001c }
            if (r0 != 0) goto L_0x0015
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeByteArray(r5, r6, r7, r8)     // Catch:{ Error | Exception -> 0x0013, all -> 0x001c }
            return r4
        L_0x0013:
            r0 = move-exception
            goto L_0x001e
        L_0x0015:
            android.graphics.Bitmap r0 = com.samsung.android.media.SemBitmapFactory.decodeByteArray(r5, r6, r7, r8)     // Catch:{ Error | Exception -> 0x0013, all -> 0x001c }
            if (r0 == 0) goto L_0x003c
            return r0
        L_0x001c:
            r4 = move-exception
            throw r4
        L_0x001e:
            boolean r1 = r0 instanceof java.lang.NoClassDefFoundError
            if (r1 == 0) goto L_0x0025
            r1 = 0
            com.samsung.android.gallery.module.graphics.ImageDecoderSem150Impl.SemLibHolder.SUPPORT = r1
        L_0x0025:
            java.lang.String r1 = r4.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "decodeByteArray failed. e="
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
        L_0x003c:
            android.graphics.Bitmap r4 = super.decodeByteArray(r5, r6, r7, r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.graphics.ImageDecoderSem150Impl.decodeByteArray(byte[], int, int, com.samsung.android.gallery.module.graphics.BitmapOptions):android.graphics.Bitmap");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        if ((r0 instanceof java.lang.NoClassDefFoundError) != false) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0058, code lost:
        com.samsung.android.gallery.module.graphics.ImageDecoderSem150Impl.SemLibHolder.SUPPORT = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005a, code lost:
        r1 = r5.TAG;
        com.samsung.android.gallery.support.utils.Log.e(r1, "decodeFile failed. e=" + r0.getMessage());
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap decodeFile(java.lang.String r6, com.samsung.android.gallery.module.graphics.BitmapOptions r7) {
        /*
            r5 = this;
            java.lang.String r0 = "decodeFile(heap) failed "
            boolean r1 = com.samsung.android.gallery.module.graphics.ImageDecoderSem150Impl.SemLibHolder.SUPPORT
            if (r1 == 0) goto L_0x0071
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            r2.<init>(r6)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            long r3 = r2.length()     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            int r3 = (int) r3     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            boolean r4 = r7.inPreferredJavaHeap     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            if (r4 != 0) goto L_0x0019
            r4 = 5242880(0x500000, float:7.34684E-39)
            if (r3 >= r4) goto L_0x004b
        L_0x0019:
            byte[] r2 = r5.readByteArray(r2)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            if (r2 == 0) goto L_0x004b
            int r4 = r2.length     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            if (r4 <= 0) goto L_0x004b
            android.graphics.Bitmap r3 = r5.decodeByteArray(r2, r1, r3, r7)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            if (r3 == 0) goto L_0x0029
            return r3
        L_0x0029:
            java.lang.String r3 = r5.TAG     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            r4.<init>(r0)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            r4.append(r7)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            java.lang.String r0 = ", "
            r4.append(r0)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            r0 = 16
            java.lang.String r0 = com.samsung.android.gallery.support.utils.StringCompat.valueOf((byte[]) r2, (int) r0)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            r4.append(r0)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            java.lang.String r0 = r4.toString()     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            com.samsung.android.gallery.support.utils.Log.e(r3, r0)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            goto L_0x004b
        L_0x0049:
            r0 = move-exception
            goto L_0x0054
        L_0x004b:
            android.graphics.Bitmap r0 = com.samsung.android.media.SemBitmapFactory.decodeFile(r6, r7)     // Catch:{ Error | Exception -> 0x0049, all -> 0x0052 }
            if (r0 == 0) goto L_0x0071
            return r0
        L_0x0052:
            r5 = move-exception
            throw r5
        L_0x0054:
            boolean r2 = r0 instanceof java.lang.NoClassDefFoundError
            if (r2 == 0) goto L_0x005a
            com.samsung.android.gallery.module.graphics.ImageDecoderSem150Impl.SemLibHolder.SUPPORT = r1
        L_0x005a:
            java.lang.String r1 = r5.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "decodeFile failed. e="
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
        L_0x0071:
            android.graphics.Bitmap r5 = super.decodeFile(r6, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.graphics.ImageDecoderSem150Impl.decodeFile(java.lang.String, com.samsung.android.gallery.module.graphics.BitmapOptions):android.graphics.Bitmap");
    }
}
