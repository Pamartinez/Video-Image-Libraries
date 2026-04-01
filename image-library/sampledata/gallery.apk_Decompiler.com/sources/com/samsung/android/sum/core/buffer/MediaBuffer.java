package com.samsung.android.sum.core.buffer;

import android.graphics.Bitmap;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.UniExifInterface;
import com.samsung.android.sum.core.format.Duplicable;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MediaFormatBuilder;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.functional.PlaceHolder;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaBuffer extends Parcelable, Duplicable<MediaBuffer>, AutoCloseable {
    public static final int BUFFER_FLAG_BEG_OF_STREAM = 4;
    public static final int BUFFER_FLAG_DATA_NOT_OWNED = 16;
    public static final int BUFFER_FLAG_END_OF_STREAM = 8;
    public static final int BUFFER_FLAG_PACKED_EVALUATION_BUFFER = 2;
    public static final int BUFFER_FLAG_PACKED_IO_BUFFERS = 1;
    public static final int BUFFER_FLAG_SHUTTER_SOUND_REMOVE = 32;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface BufferFlag {
    }

    static MediaBuffer allocate(MediaFormat mediaFormat) {
        MediaBuffer allocate = newAlloc().setMediaFormat(mediaFormat).allocate();
        Def.check(!(allocate instanceof MutableMediaBuffer), "can't allocate due to 0 size from media-format", new Object[0]);
        return allocate;
    }

    static MediaBuffer allocateHardware(MediaFormat mediaFormat) {
        MediaBuffer allocate = newImageAlloc().setMediaFormat(mediaFormat).asSharable().allocate();
        Def.check(!(allocate instanceof MutableMediaBuffer), "can't allocate due to 0 size from media-format", new Object[0]);
        return allocate;
    }

    /* access modifiers changed from: private */
    static /* synthetic */ boolean lambda$mutableOf$0(Object obj) {
        return obj instanceof MediaBuffer;
    }

    static MediaBuffer metadataBufferOf(int i2, ByteBuffer byteBuffer) {
        MediaBufferAlloc shape = newMetaAlloc().setShape(1, byteBuffer.limit());
        if (i2 == 1) {
            shape.setAttribute("exif", Boolean.TRUE);
        } else if (i2 == 2) {
            shape.setAttribute("icc", Boolean.TRUE);
        } else if (i2 == 3) {
            shape.setAttribute("gain-map", Boolean.TRUE);
        } else {
            throw new UnsupportedOperationException(C0086a.i(i2, "not support for "));
        }
        return shape.wrap(byteBuffer);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.samsung.android.sum.core.buffer.MutableMediaBuffer mutableOf(java.lang.Object... r6) {
        /*
            com.samsung.android.sum.core.buffer.MediaBufferAlloc r0 = newAlloc()
            if (r6 == 0) goto L_0x009a
            java.util.stream.Stream r1 = java.util.Arrays.stream(r6)
            com.samsung.android.sum.core.buffer.e r2 = new com.samsung.android.sum.core.buffer.e
            r3 = 1
            r2.<init>(r3)
            java.util.stream.Collector r2 = java.util.stream.Collectors.partitioningBy(r2)
            java.lang.Object r1 = r1.collect(r2)
            java.util.Map r1 = (java.util.Map) r1
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            java.lang.Object r3 = r1.get(r2)
            if (r3 == 0) goto L_0x0097
            java.lang.Object r3 = r1.get(r2)
            java.util.List r3 = (java.util.List) r3
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0097
            java.lang.Object r6 = r1.get(r2)
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            r3 = 1
            r4 = 0
            if (r6 != r3) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r3 = r4
        L_0x003e:
            java.lang.String r5 = "not support hold multiple buffers: "
            java.lang.String r6 = c0.C0086a.i(r6, r5)
            java.lang.Object[] r5 = new java.lang.Object[r4]
            com.samsung.android.sum.core.Def.check(r3, r6, r5)
            java.lang.Object r6 = r1.get(r2)
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = r6.get(r4)
            boolean r6 = r6 instanceof com.samsung.android.sum.core.buffer.MutableMediaBuffer
            if (r6 == 0) goto L_0x0064
            java.lang.Object r6 = r1.get(r2)
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = r6.get(r4)
            com.samsung.android.sum.core.buffer.MutableMediaBuffer r6 = (com.samsung.android.sum.core.buffer.MutableMediaBuffer) r6
            goto L_0x0075
        L_0x0064:
            com.samsung.android.sum.core.buffer.MutableMediaBuffer r6 = new com.samsung.android.sum.core.buffer.MutableMediaBuffer
            java.lang.Object r2 = r1.get(r2)
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r2.get(r4)
            com.samsung.android.sum.core.buffer.MediaBuffer r2 = (com.samsung.android.sum.core.buffer.MediaBuffer) r2
            r6.<init>((com.samsung.android.sum.core.buffer.MediaBuffer) r2)
        L_0x0075:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            java.lang.Object r3 = r1.get(r2)
            if (r3 == 0) goto L_0x009b
            java.lang.Object r3 = r1.get(r2)
            java.util.List r3 = (java.util.List) r3
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x009b
            java.lang.Object r1 = r1.get(r2)
            java.util.List r1 = (java.util.List) r1
            java.lang.Object[] r1 = r1.toArray()
            r0.set(r1)
            goto L_0x009b
        L_0x0097:
            r0.set(r6)
        L_0x009a:
            r6 = 0
        L_0x009b:
            if (r6 != 0) goto L_0x00a2
            com.samsung.android.sum.core.buffer.MutableMediaBuffer r6 = r0.allocateMutable()
            return r6
        L_0x00a2:
            com.samsung.android.sum.core.buffer.MutableMediaBuffer r0 = r0.allocateMutable()
            com.samsung.android.sum.core.format.MediaFormat r0 = r0.getFixedFormat()
            r6.setFixedFormat(r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.MediaBuffer.mutableOf(java.lang.Object[]):com.samsung.android.sum.core.buffer.MutableMediaBuffer");
    }

    static MediaBufferAlloc newAlloc() {
        return new MediaBufferAlloc();
    }

    static MediaBufferAlloc newAudioAlloc() {
        return newAlloc(MediaType.AUDIO);
    }

    static MediaBufferAlloc newCompressedImageAlloc() {
        return newAlloc(MediaType.COMPRESSED_IMAGE);
    }

    static MediaBufferAlloc newGroupAlloc() {
        return newAlloc().asGroup();
    }

    static MediaBufferAlloc newImageAlloc() {
        return newAlloc(MediaType.IMAGE);
    }

    static MediaBufferAlloc newMetaAlloc() {
        return newAlloc(MediaType.META);
    }

    static MediaBufferAlloc newVideoAlloc() {
        return newAlloc(MediaType.VIDEO);
    }

    static MediaBuffer of(Object... objArr) {
        return newAlloc().set(objArr).allocate();
    }

    static MediaBuffer scalaOf(Number number) {
        MediaBufferAlloc mediaType = newAlloc().setMediaType(MediaType.SCALA);
        if (number instanceof Integer) {
            mediaType.setDataType(DataType.U32C1).setShape(4);
        } else if (number instanceof Long) {
            mediaType.setDataType(DataType.U64C1).setShape(8);
        } else if (number instanceof Byte) {
            mediaType.setDataType(DataType.U8C1).setShape(1);
        } else if (number instanceof Float) {
            mediaType.setDataType(DataType.F32C1).setShape(4);
        } else if (number instanceof Double) {
            mediaType.setDataType(DataType.F64C1).setShape(8);
        } else {
            throw new UnsupportedOperationException("not supported number format: " + number);
        }
        return mediaType.wrap(number);
    }

    void addExtra(Map<String, Object> map);

    void addOnReleaseListener(Runnable... runnableArr);

    List<MediaBuffer> asList() {
        return (List) stream().collect(Collectors.toList());
    }

    MediaBuffer clearFlags(int... iArr);

    void close() {
        release();
    }

    boolean containFlags(int... iArr);

    boolean containsAllExtra(String... strArr);

    boolean containsAnyExtra(String... strArr);

    boolean containsExtra(String str);

    <T> MediaBuffer convertTo(Class<T> cls);

    <T> MediaBuffer copyTo(Class<T> cls);

    Align getAlign();

    int getChannels();

    int getCols();

    <T> T getData();

    Class<?> getDataClass();

    MediaBuffer getExifBuffer();

    <T> T getExtra(String str);

    <V> V getExtra(String str, V v);

    Map<String, Object> getExtra();

    int getFlags();

    MediaFormat getFormat();

    MediaBuffer getIccBuffer();

    List<MediaBuffer> getMetaDataBuffers();

    int getRows();

    int getScanline();

    int getStride();

    <T> T getTypedData(Class<T> cls);

    <T> T getTypedDataOr(Class<T> cls, T t);

    boolean isEmpty() {
        return false;
    }

    boolean isMutable() {
        return this instanceof PlaceHolder;
    }

    boolean isNotEmpty() {
        return true;
    }

    void release();

    List<Runnable> removeAllOnReleaseListeners();

    <T> T removeExtra(String str);

    void setExtra(String str, Object obj);

    void setExtra(Map<String, Object> map);

    MediaBuffer setFlags(int... iArr);

    MediaBuffer setScanline(int i2);

    MediaBuffer setStride(int i2);

    long size();

    Stream<MediaBuffer> stream();

    MutableMediaBuffer toMutable() {
        if (this instanceof MutableMediaBuffer) {
            return (MutableMediaBuffer) this;
        }
        return new MutableMediaBuffer(this);
    }

    static MediaBufferAlloc newAlloc(MediaType mediaType) {
        return newAlloc().setMediaType(mediaType);
    }

    static MediaBuffer of(Bitmap bitmap) {
        return newImageAlloc().wrap(bitmap);
    }

    static MediaBuffer allocate(MutableMediaFormat mutableMediaFormat) {
        MediaBuffer allocate = newAlloc().setMediaFormat(mutableMediaFormat).allocate();
        Def.check(!(allocate instanceof MutableMediaBuffer), "can't allocate due to 0 size from media-format", new Object[0]);
        return allocate;
    }

    static MediaBuffer of(HardwareBuffer hardwareBuffer) {
        return newImageAlloc().wrap(hardwareBuffer);
    }

    static MediaBuffer of(Image image) {
        return newImageAlloc().wrap(image);
    }

    static MediaBuffer metadataBufferOf(int i2, Bitmap bitmap) {
        MutableMediaFormat describe = BufferExtension.describe(bitmap);
        describe.setMediaType(MediaType.META);
        if (i2 == 1) {
            describe.set("exif", Boolean.TRUE);
        } else if (i2 == 2) {
            describe.set("icc", Boolean.TRUE);
        } else if (i2 != 3) {
            SLog.w("MediaBuffer", "not supported metadata-key " + i2);
        } else {
            describe.set("gain-map", Boolean.TRUE);
        }
        return newMetaAlloc().setMediaFormat(describe).wrap(bitmap);
    }

    static MediaBuffer metadataBufferOf(int i2, UniExifInterface uniExifInterface) {
        return metadataBufferOf(i2, uniExifInterface.toExifByteBuffer());
    }

    static <T> MediaBuffer metadataBufferOf(String str, T t) {
        MutableMediaFormat mutableMediaFormat;
        MediaFormatBuilder newMetaBuilder = MediaFormat.newMetaBuilder();
        if (t instanceof ByteBuffer) {
            mutableMediaFormat = newMetaBuilder.setShape(1, ((ByteBuffer) t).limit()).buildMutable();
        } else if (t instanceof UniExifInterface) {
            mutableMediaFormat = newMetaBuilder.setShape(1, ((UniExifInterface) t).toExifByteBuffer().limit()).buildMutable();
        } else if (t instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) t;
            mutableMediaFormat = newMetaBuilder.setDataType(DataType.U8C3).setColorFormat(ColorFormat.RGB).setColorSpace(ColorSpace.of(bitmap)).setShape(bitmap.getWidth(), bitmap.getHeight()).buildMutable();
            float size = ((float) mutableMediaFormat.size()) / ((float) bitmap.getByteCount());
            if (((float) Math.round(size * 100.0f)) / 100.0f == 0.75f) {
                mutableMediaFormat.setDataType(DataType.U8C4);
                mutableMediaFormat.setColorFormat(ColorFormat.RGBA);
            } else if (((float) Math.round(size * 10.0f)) / 10.0f == 0.5f) {
                mutableMediaFormat.setDataType(DataType.U16C3);
            } else if (((float) Math.round(size * 1000.0f)) / 1000.0f == 0.375f) {
                mutableMediaFormat.setDataType(DataType.U16C4);
                mutableMediaFormat.setColorFormat(ColorFormat.RGBA);
            } else if (Math.round(size) == 3) {
                mutableMediaFormat.setDataType(DataType.U8C1);
                mutableMediaFormat.setColorFormat(ColorFormat.GRAY);
            } else {
                throw new IllegalArgumentException("byte count +" + bitmap.getByteCount() + "is differ from calculated buffer size" + mutableMediaFormat.size());
            }
        } else {
            mutableMediaFormat = newMetaBuilder.buildMutable();
        }
        mutableMediaFormat.set(str, Boolean.TRUE);
        return newMetaAlloc().setMediaFormat(mutableMediaFormat).wrap(t);
    }
}
