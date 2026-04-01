package com.samsung.android.gallery.support.renderscript;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenderScriptCompatImplR implements RenderScriptCompatible {
    protected final int SIZE = 64;
    protected final String TAG = getClass().getSimpleName();

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: android.renderscript.Allocation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: android.renderscript.Allocation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: android.renderscript.Allocation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: android.renderscript.Allocation} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00a5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap getBlurAppliedBitmap(android.content.Context r6, android.graphics.Bitmap r7, float r8, boolean r9) {
        /*
            r5 = this;
            java.lang.String r9 = "blur failed. e="
            r0 = 0
            if (r6 == 0) goto L_0x00a9
            if (r7 != 0) goto L_0x0009
            goto L_0x00a9
        L_0x0009:
            android.renderscript.RenderScript r6 = android.renderscript.RenderScript.create(r6)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x0066, all -> 0x0062 }
            android.renderscript.Allocation$MipmapControl r1 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x005e, all -> 0x005a }
            r2 = 1
            android.renderscript.Allocation r1 = android.renderscript.Allocation.createFromBitmap(r6, r7, r1, r2)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x005e, all -> 0x005a }
            android.renderscript.Type r3 = r1.getType()     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x0056, all -> 0x0052 }
            android.renderscript.Allocation r3 = android.renderscript.Allocation.createTyped(r6, r3)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x0056, all -> 0x0052 }
            android.renderscript.Element r4 = android.renderscript.Element.U8_4(r6)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x004f, all -> 0x004c }
            android.renderscript.ScriptIntrinsicBlur r4 = android.renderscript.ScriptIntrinsicBlur.create(r6, r4)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x004f, all -> 0x004c }
            r4.setRadius(r8)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x004a }
            r4.setInput(r1)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x004a }
            r4.forEach(r3)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x004a }
            android.graphics.Bitmap$Config r8 = r7.getConfig()     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x004a }
            android.graphics.Bitmap r7 = r7.copy(r8, r2)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x004a }
            r3.copyTo(r7)     // Catch:{ RSRuntimeException | IllegalStateException | NullPointerException -> 0x004a }
            r1.destroy()
            r3.destroy()
            r4.destroy()
            if (r6 == 0) goto L_0x0046
            r6.destroy()
        L_0x0046:
            return r7
        L_0x0047:
            r5 = move-exception
        L_0x0048:
            r0 = r1
            goto L_0x0094
        L_0x004a:
            r7 = move-exception
            goto L_0x006a
        L_0x004c:
            r5 = move-exception
            r4 = r0
            goto L_0x0048
        L_0x004f:
            r7 = move-exception
            r4 = r0
            goto L_0x006a
        L_0x0052:
            r5 = move-exception
            r3 = r0
            r4 = r3
            goto L_0x0048
        L_0x0056:
            r7 = move-exception
            r3 = r0
        L_0x0058:
            r4 = r3
            goto L_0x006a
        L_0x005a:
            r5 = move-exception
            r3 = r0
        L_0x005c:
            r4 = r3
            goto L_0x0094
        L_0x005e:
            r7 = move-exception
            r1 = r0
        L_0x0060:
            r3 = r1
            goto L_0x0058
        L_0x0062:
            r5 = move-exception
            r6 = r0
            r3 = r6
            goto L_0x005c
        L_0x0066:
            r7 = move-exception
            r6 = r0
            r1 = r6
            goto L_0x0060
        L_0x006a:
            java.lang.String r5 = r5.TAG     // Catch:{ all -> 0x0047 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0047 }
            r8.<init>(r9)     // Catch:{ all -> 0x0047 }
            java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x0047 }
            r8.append(r7)     // Catch:{ all -> 0x0047 }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x0047 }
            com.samsung.android.gallery.support.utils.Log.e(r5, r7)     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x0084
            r1.destroy()
        L_0x0084:
            if (r3 == 0) goto L_0x0089
            r3.destroy()
        L_0x0089:
            if (r4 == 0) goto L_0x008e
            r4.destroy()
        L_0x008e:
            if (r6 == 0) goto L_0x0093
            r6.destroy()
        L_0x0093:
            return r0
        L_0x0094:
            if (r0 == 0) goto L_0x0099
            r0.destroy()
        L_0x0099:
            if (r3 == 0) goto L_0x009e
            r3.destroy()
        L_0x009e:
            if (r4 == 0) goto L_0x00a3
            r4.destroy()
        L_0x00a3:
            if (r6 == 0) goto L_0x00a8
            r6.destroy()
        L_0x00a8:
            throw r5
        L_0x00a9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.renderscript.RenderScriptCompatImplR.getBlurAppliedBitmap(android.content.Context, android.graphics.Bitmap, float, boolean):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap getLUTAppliedBitmap(android.graphics.Bitmap r8, com.samsung.android.gallery.support.renderscript.RenderScriptCompat.LutInfo r9) {
        /*
            r7 = this;
            java.lang.String r0 = "applyWithLut failed"
            r1 = 0
            android.content.Context r2 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()     // Catch:{ Error | Exception -> 0x008b, all -> 0x0087 }
            android.renderscript.RenderScript r2 = android.renderscript.RenderScript.create(r2)     // Catch:{ Error | Exception -> 0x008b, all -> 0x0087 }
            int r3 = r8.getWidth()     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            int r4 = r8.getHeight()     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            android.graphics.Bitmap$Config r5 = r8.getConfig()     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r3, r4, r5)     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            android.renderscript.Type$Builder r4 = new android.renderscript.Type$Builder     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            android.renderscript.Element r5 = android.renderscript.Element.U8_4(r2)     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            r4.<init>(r2, r5)     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            r5 = 64
            r4.setX(r5)     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            r4.setY(r5)     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            r4.setZ(r5)     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            android.renderscript.Allocation r5 = android.renderscript.Allocation.createFromBitmap(r2, r8)     // Catch:{ Error | Exception -> 0x0082, all -> 0x007d }
            android.renderscript.Allocation r6 = android.renderscript.Allocation.createFromBitmap(r2, r3)     // Catch:{ Error | Exception -> 0x0079, all -> 0x0075 }
            android.renderscript.Type r4 = r4.create()     // Catch:{ Error | Exception -> 0x0072, all -> 0x006e }
            android.renderscript.Allocation r4 = android.renderscript.Allocation.createTyped(r2, r4)     // Catch:{ Error | Exception -> 0x0072, all -> 0x006e }
            int[] r9 = r9.mData     // Catch:{ Error | Exception -> 0x006c }
            r4.copyFromUnchecked(r9)     // Catch:{ Error | Exception -> 0x006c }
            android.renderscript.Element r9 = android.renderscript.Element.U8_4(r2)     // Catch:{ Error | Exception -> 0x006c }
            android.renderscript.ScriptIntrinsic3DLUT r1 = android.renderscript.ScriptIntrinsic3DLUT.create(r2, r9)     // Catch:{ Error | Exception -> 0x006c }
            r1.setLUT(r4)     // Catch:{ Error | Exception -> 0x006c }
            r1.forEach(r5, r6)     // Catch:{ Error | Exception -> 0x006c }
            r6.copyTo(r3)     // Catch:{ Error | Exception -> 0x006c }
            r1.destroy()
            if (r2 == 0) goto L_0x005d
            r2.destroy()
        L_0x005d:
            r4.destroy()
            if (r5 == 0) goto L_0x0065
            r5.destroy()
        L_0x0065:
            r6.destroy()
            return r3
        L_0x0069:
            r7 = move-exception
            goto L_0x00be
        L_0x006c:
            r9 = move-exception
            goto L_0x008f
        L_0x006e:
            r7 = move-exception
            r4 = r1
            goto L_0x00be
        L_0x0072:
            r9 = move-exception
            r4 = r1
            goto L_0x008f
        L_0x0075:
            r7 = move-exception
            r4 = r1
            r6 = r4
            goto L_0x00be
        L_0x0079:
            r9 = move-exception
            r4 = r1
            r6 = r4
            goto L_0x008f
        L_0x007d:
            r7 = move-exception
            r4 = r1
        L_0x007f:
            r5 = r4
            r6 = r5
            goto L_0x00be
        L_0x0082:
            r9 = move-exception
            r4 = r1
        L_0x0084:
            r5 = r4
            r6 = r5
            goto L_0x008f
        L_0x0087:
            r7 = move-exception
            r2 = r1
            r4 = r2
            goto L_0x007f
        L_0x008b:
            r9 = move-exception
            r2 = r1
            r4 = r2
            goto L_0x0084
        L_0x008f:
            java.lang.String r7 = r7.TAG     // Catch:{ all -> 0x0069 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0069 }
            r3.<init>(r0)     // Catch:{ all -> 0x0069 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0069 }
            r3.append(r9)     // Catch:{ all -> 0x0069 }
            java.lang.String r9 = r3.toString()     // Catch:{ all -> 0x0069 }
            com.samsung.android.gallery.support.utils.Log.e(r7, r9)     // Catch:{ all -> 0x0069 }
            if (r1 == 0) goto L_0x00a9
            r1.destroy()
        L_0x00a9:
            if (r2 == 0) goto L_0x00ae
            r2.destroy()
        L_0x00ae:
            if (r4 == 0) goto L_0x00b3
            r4.destroy()
        L_0x00b3:
            if (r5 == 0) goto L_0x00b8
            r5.destroy()
        L_0x00b8:
            if (r6 == 0) goto L_0x00bd
            r6.destroy()
        L_0x00bd:
            return r8
        L_0x00be:
            if (r1 == 0) goto L_0x00c3
            r1.destroy()
        L_0x00c3:
            if (r2 == 0) goto L_0x00c8
            r2.destroy()
        L_0x00c8:
            if (r4 == 0) goto L_0x00cd
            r4.destroy()
        L_0x00cd:
            if (r5 == 0) goto L_0x00d2
            r5.destroy()
        L_0x00d2:
            if (r6 == 0) goto L_0x00d7
            r6.destroy()
        L_0x00d7:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.renderscript.RenderScriptCompatImplR.getLUTAppliedBitmap(android.graphics.Bitmap, com.samsung.android.gallery.support.renderscript.RenderScriptCompat$LutInfo):android.graphics.Bitmap");
    }
}
