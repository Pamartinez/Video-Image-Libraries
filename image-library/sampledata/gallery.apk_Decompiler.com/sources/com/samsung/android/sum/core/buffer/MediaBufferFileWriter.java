package com.samsung.android.sum.core.buffer;

import android.media.ExifInterface;
import c0.C0086a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.UniExifInterface;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaBufferFileWriter {
    private static final String TAG = Def.tagOf((Class<?>) MediaBufferFileWriter.class);
    private BiFunction<MediaBuffer, String, Boolean> compressImageWriter;
    private Supplier<ExifInterface> exifSupplier;
    private String ext;
    private final String path;
    private final String prefix;
    private Supplier<UniExifInterface> uniExifSupplier;

    /* renamed from: com.samsung.android.sum.core.buffer.MediaBufferFileWriter$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$ColorFormat;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.sum.core.types.ColorFormat[] r0 = com.samsung.android.sum.core.types.ColorFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$ColorFormat = r0
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.YUV420     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.NONE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.MediaBufferFileWriter.AnonymousClass1.<clinit>():void");
        }
    }

    public MediaBufferFileWriter(String str, String str2) {
        this.path = str;
        int lastIndexOf = str2.lastIndexOf(".");
        if (lastIndexOf > 0) {
            this.prefix = str2.substring(0, lastIndexOf);
            this.ext = str2.substring(lastIndexOf + 1);
            return;
        }
        this.prefix = str2;
        this.ext = null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$writeRawImageSingle$3(DataType dataType, DataType dataType2) {
        if (dataType2 == dataType.depth()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ BiFunction lambda$writeSingle$1() {
        return new C0929g(8);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$writeSingle$2(ColorFormat colorFormat) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$ColorFormat[colorFormat.ordinal()];
        if (i2 == 1) {
            return "i420";
        }
        if (i2 != 2) {
            return colorFormat.name().toLowerCase(Locale.ROOT);
        }
        return "gray";
    }

    private boolean writeGroup(MediaBuffer mediaBuffer) {
        try {
            return writeSingle(((MediaBufferGroup) mediaBuffer).getPrimaryBuffer(), "");
        } catch (UnsupportedOperationException unused) {
            List<MediaBuffer> asList = mediaBuffer.asList();
            for (int i2 = 0; i2 < asList.size(); i2++) {
                if (!writeSingle(asList.get(i2), C0086a.i(i2, "_"))) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x008d A[SYNTHETIC, Splitter:B:25:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099 A[SYNTHETIC, Splitter:B:30:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean writeRawImageSingle(com.samsung.android.sum.core.buffer.MediaBuffer r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r6 = "fail to save "
            java.lang.String r0 = "success to save "
            com.samsung.android.sum.core.format.MediaFormat r1 = r7.getFormat()
            com.samsung.android.sum.core.types.DataType r1 = r1.getDataType()
            com.samsung.android.sum.core.types.DataType r2 = com.samsung.android.sum.core.types.DataType.U8
            com.samsung.android.sum.core.types.DataType r3 = com.samsung.android.sum.core.types.DataType.S8
            com.samsung.android.sum.core.types.DataType[] r3 = new com.samsung.android.sum.core.types.DataType[]{r2, r3}
            java.util.stream.Stream r3 = java.util.Arrays.stream(r3)
            com.samsung.android.sum.core.buffer.t r4 = new com.samsung.android.sum.core.buffer.t
            r5 = 4
            r4.<init>(r5, r1)
            boolean r1 = r3.noneMatch(r4)
            if (r1 == 0) goto L_0x003e
            com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.IMAGE
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r1 = com.samsung.android.sum.core.buffer.MediaBuffer.newAlloc(r1)
            int r3 = r7.getChannels()
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r1 = r1.setDataType(r2, r3)
            com.samsung.android.sum.core.buffer.MutableMediaBuffer r1 = r1.allocateMutable()
            com.samsung.android.sum.core.functional.Operator r2 = com.samsung.android.sum.solution.filter.UniImgp.ofCvtData()
            r2.run((com.samsung.android.sum.core.buffer.MediaBuffer) r7, (com.samsung.android.sum.core.buffer.MutableMediaBuffer) r1)
            r7 = r1
        L_0x003e:
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0076 }
            r2.<init>(r8)     // Catch:{ IOException -> 0x0076 }
            java.lang.Class<java.nio.ByteBuffer> r1 = java.nio.ByteBuffer.class
            java.lang.Object r7 = r7.getTypedData(r1)     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            java.nio.channels.FileChannel r1 = r2.getChannel()     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            r1.write(r7)     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            java.lang.String r7 = TAG     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            r1.<init>(r0)     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            r1.append(r8)     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            java.lang.String r0 = r1.toString()     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r7, (java.lang.String) r0)     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            r6 = 1
            r2.close()     // Catch:{ IOException -> 0x0069 }
            return r6
        L_0x0069:
            r7 = move-exception
            r7.printStackTrace()
            return r6
        L_0x006e:
            r6 = move-exception
            r1 = r2
            goto L_0x0097
        L_0x0071:
            r7 = move-exception
            r1 = r2
            goto L_0x0077
        L_0x0074:
            r6 = move-exception
            goto L_0x0097
        L_0x0076:
            r7 = move-exception
        L_0x0077:
            r7.printStackTrace()     // Catch:{ all -> 0x0074 }
            java.lang.String r7 = TAG     // Catch:{ all -> 0x0074 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r0.<init>(r6)     // Catch:{ all -> 0x0074 }
            r0.append(r8)     // Catch:{ all -> 0x0074 }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0074 }
            com.samsung.android.sum.core.SLog.e((java.lang.String) r7, (java.lang.String) r6)     // Catch:{ all -> 0x0074 }
            if (r1 == 0) goto L_0x0095
            r1.close()     // Catch:{ IOException -> 0x0091 }
            goto L_0x0095
        L_0x0091:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0095:
            r6 = 0
            return r6
        L_0x0097:
            if (r1 == 0) goto L_0x00a1
            r1.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x00a1
        L_0x009d:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00a1:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.MediaBufferFileWriter.writeRawImageSingle(com.samsung.android.sum.core.buffer.MediaBuffer, java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x011a A[SYNTHETIC, Splitter:B:42:0x011a] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0120 A[SYNTHETIC, Splitter:B:45:0x0120] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean writeSingle(com.samsung.android.sum.core.buffer.MediaBuffer r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "writeSingle: "
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r0, (java.lang.String) r1)
            boolean r0 = r9 instanceof com.samsung.android.sum.core.buffer.MutableMediaBuffer
            if (r0 == 0) goto L_0x001d
            com.samsung.android.sum.core.buffer.MutableMediaBuffer r9 = (com.samsung.android.sum.core.buffer.MutableMediaBuffer) r9
            com.samsung.android.sum.core.buffer.MediaBuffer r9 = r9.get()
        L_0x001d:
            java.lang.String r0 = r8.ext
            r1 = 0
            if (r0 == 0) goto L_0x006e
            java.lang.String r2 = r8.path
            java.lang.String r3 = r8.prefix
            java.lang.Object[] r10 = new java.lang.Object[]{r2, r3, r10, r0}
            java.lang.String r0 = "%s/%s%s.%s"
            java.lang.String r10 = com.samsung.android.sum.core.Def.fmtstr(r0, r10)
            java.util.function.BiFunction<com.samsung.android.sum.core.buffer.MediaBuffer, java.lang.String, java.lang.Boolean> r0 = r8.compressImageWriter
            java.util.Optional r0 = java.util.Optional.ofNullable(r0)
            com.samsung.android.sum.core.buffer.u r2 = new com.samsung.android.sum.core.buffer.u
            r3 = 3
            r2.<init>(r3)
            java.lang.Object r0 = r0.orElseGet(r2)
            java.util.function.BiFunction r0 = (java.util.function.BiFunction) r0
            java.util.function.Supplier<com.samsung.android.sum.core.UniExifInterface> r2 = r8.uniExifSupplier
            if (r2 == 0) goto L_0x0063
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r2 = com.samsung.android.sum.core.buffer.MediaBuffer.newGroupAlloc()
            java.util.function.Supplier<com.samsung.android.sum.core.UniExifInterface> r3 = r8.uniExifSupplier
            java.lang.Object r3 = r3.get()
            com.samsung.android.sum.core.UniExifInterface r3 = (com.samsung.android.sum.core.UniExifInterface) r3
            r4 = 1
            com.samsung.android.sum.core.buffer.MediaBuffer r3 = com.samsung.android.sum.core.buffer.MediaBuffer.metadataBufferOf((int) r4, (com.samsung.android.sum.core.UniExifInterface) r3)
            com.samsung.android.sum.core.buffer.MediaBuffer[] r4 = new com.samsung.android.sum.core.buffer.MediaBuffer[r4]
            r4[r1] = r3
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r9 = r2.setBuffers((com.samsung.android.sum.core.buffer.MediaBuffer) r9, (com.samsung.android.sum.core.buffer.MediaBuffer[]) r4)
            com.samsung.android.sum.core.buffer.MediaBuffer r9 = r9.allocate()
        L_0x0063:
            java.lang.Object r9 = r0.apply(r9, r10)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            goto L_0x00b9
        L_0x006e:
            com.samsung.android.sum.core.format.MediaFormat r0 = r9.getFormat()
            com.samsung.android.sum.core.types.ColorFormat r0 = r0.getColorFormat()
            java.util.Optional r0 = java.util.Optional.ofNullable(r0)
            com.samsung.android.sum.core.buffer.a r2 = new com.samsung.android.sum.core.buffer.a
            r3 = 23
            r2.<init>(r3)
            java.util.Optional r0 = r0.map(r2)
            java.lang.String r2 = "raw"
            java.lang.Object r0 = r0.orElse(r2)
            java.lang.String r0 = (java.lang.String) r0
            r8.ext = r0
            java.lang.String r2 = r8.path
            java.lang.String r3 = r8.prefix
            int r0 = r9.getStride()
            int r4 = r9.getChannels()
            int r0 = r0 / r4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            int r0 = r9.getScanline()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            java.lang.String r7 = r8.ext
            r6 = r10
            java.lang.Object[] r10 = new java.lang.Object[]{r2, r3, r4, r5, r6, r7}
            java.lang.String r0 = "%s/%s_%dx%d%s.%s"
            java.lang.String r10 = com.samsung.android.sum.core.Def.fmtstr(r0, r10)
            boolean r9 = r8.writeRawImageSingle(r9, r10)
        L_0x00b9:
            if (r9 == 0) goto L_0x012a
            java.util.function.Supplier<android.media.ExifInterface> r8 = r8.exifSupplier
            if (r8 == 0) goto L_0x012a
            java.lang.Object r8 = r8.get()
            android.media.ExifInterface r8 = (android.media.ExifInterface) r8
            if (r8 == 0) goto L_0x012a
            r2 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0113 }
            java.lang.String r0 = "rw"
            r3.<init>(r10, r0)     // Catch:{ IOException -> 0x0113 }
            java.nio.channels.FileChannel r10 = r3.getChannel()     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            r4 = 0
            r10.position(r4)     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            android.media.ExifInterface r10 = new android.media.ExifInterface     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            java.io.FileDescriptor r0 = r3.getFD()     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            r10.<init>(r0)     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            java.lang.String[] r0 = com.samsung.android.sum.core.MetaDataUtil.getExifTags()     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            int r2 = r0.length     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
        L_0x00e6:
            if (r1 >= r2) goto L_0x0103
            r4 = r0[r1]     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            boolean r5 = r8.hasAttribute(r4)     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            if (r5 == 0) goto L_0x0100
            java.lang.String r5 = r8.getAttribute(r4)     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            r10.setAttribute(r4, r5)     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            goto L_0x0100
        L_0x00f8:
            r0 = move-exception
            r8 = r0
            r2 = r3
            goto L_0x011e
        L_0x00fc:
            r0 = move-exception
            r8 = r0
            r2 = r3
            goto L_0x0115
        L_0x0100:
            int r1 = r1 + 1
            goto L_0x00e6
        L_0x0103:
            r10.saveAttributes()     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            r3.close()     // Catch:{ IOException -> 0x010a }
            return r9
        L_0x010a:
            r0 = move-exception
            r8 = r0
            r8.printStackTrace()
            goto L_0x012a
        L_0x0110:
            r0 = move-exception
            r8 = r0
            goto L_0x011e
        L_0x0113:
            r0 = move-exception
            r8 = r0
        L_0x0115:
            r8.printStackTrace()     // Catch:{ all -> 0x0110 }
            if (r2 == 0) goto L_0x012a
            r2.close()     // Catch:{ IOException -> 0x010a }
            goto L_0x012a
        L_0x011e:
            if (r2 == 0) goto L_0x0129
            r2.close()     // Catch:{ IOException -> 0x0124 }
            goto L_0x0129
        L_0x0124:
            r0 = move-exception
            r9 = r0
            r9.printStackTrace()
        L_0x0129:
            throw r8
        L_0x012a:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.MediaBufferFileWriter.writeSingle(com.samsung.android.sum.core.buffer.MediaBuffer, java.lang.String):boolean");
    }

    public MediaBufferFileWriter setCompressImageWriter(BiFunction<MediaBuffer, String, Boolean> biFunction) {
        this.compressImageWriter = biFunction;
        return this;
    }

    public MediaBufferFileWriter setExifSupplier(Supplier<ExifInterface> supplier) {
        this.exifSupplier = supplier;
        return this;
    }

    public MediaBufferFileWriter setUniExifSupplier(Supplier<UniExifInterface> supplier) {
        this.uniExifSupplier = supplier;
        return this;
    }

    public boolean write(MediaBuffer mediaBuffer) {
        if (mediaBuffer instanceof MediaBufferGroup) {
            return writeGroup(mediaBuffer);
        }
        return writeSingle(mediaBuffer, "");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0057 A[SYNTHETIC, Splitter:B:21:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A[SYNTHETIC, Splitter:B:26:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean writeMetaByteBuffer(java.nio.ByteBuffer r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r4 = "[VDIS]//fail to save "
            java.lang.String r0 = "[VDIS]//success to save "
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0040 }
            r2.<init>(r6)     // Catch:{ IOException -> 0x0040 }
            java.nio.channels.FileChannel r1 = r2.getChannel()     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            r1.write(r5)     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            java.lang.String r1 = TAG     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            r3.append(r6)     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            java.lang.String r0 = " , buffer size="
            r3.append(r0)     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            int r5 = r5.capacity()     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            r3.append(r5)     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            java.lang.String r5 = r3.toString()     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r1, (java.lang.String) r5)     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            r4 = 1
            r2.close()     // Catch:{ IOException -> 0x0033 }
            return r4
        L_0x0033:
            r5 = move-exception
            r5.printStackTrace()
            return r4
        L_0x0038:
            r4 = move-exception
            r1 = r2
            goto L_0x0061
        L_0x003b:
            r5 = move-exception
            r1 = r2
            goto L_0x0041
        L_0x003e:
            r4 = move-exception
            goto L_0x0061
        L_0x0040:
            r5 = move-exception
        L_0x0041:
            r5.printStackTrace()     // Catch:{ all -> 0x003e }
            java.lang.String r5 = TAG     // Catch:{ all -> 0x003e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x003e }
            r0.<init>(r4)     // Catch:{ all -> 0x003e }
            r0.append(r6)     // Catch:{ all -> 0x003e }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x003e }
            com.samsung.android.sum.core.SLog.e((java.lang.String) r5, (java.lang.String) r4)     // Catch:{ all -> 0x003e }
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x005f:
            r4 = 0
            return r4
        L_0x0061:
            if (r1 == 0) goto L_0x006b
            r1.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r5 = move-exception
            r5.printStackTrace()
        L_0x006b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.MediaBufferFileWriter.writeMetaByteBuffer(java.nio.ByteBuffer, java.lang.String):boolean");
    }
}
