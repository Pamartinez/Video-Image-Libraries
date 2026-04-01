package com.samsung.android.sum.core.buffer;

import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.os.ParcelFileDescriptor;
import c0.C0086a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MediaFormatBuilder;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.format.Shape;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.FlipType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.SplitType;
import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaBufferAlloc {
    private static final int COLLECTIVE_GROUP = 512;
    private static final int DERIVATIVE_GROUP = 256;
    private static final int GROUP = 2;
    private static final int SHARABLE = 65536;
    private static final int SINGLE = 1;
    private static final String TAG = Def.tagOf((Class<?>) MediaBufferAlloc.class);
    private Align align;
    private int bufferType = 1;
    private List<MediaBuffer> buffers;
    private Object data;
    private final HashMap<String, Object> extra = new HashMap<>();
    private int flags;
    private MediaFormat mediaFormat;
    private final MediaFormatBuilder mediaFormatBuilder = MediaFormat.newBuilder();
    private Runnable onReleaseListener;
    private MediaBuffer primaryBuffer;
    private int repBufferId;

    /* renamed from: com.samsung.android.sum.core.buffer.MediaBufferAlloc$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$ColorFormat;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.sum.core.types.ColorFormat[] r0 = com.samsung.android.sum.core.types.ColorFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$ColorFormat = r0
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.RGB     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.RGBA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.NV12     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.NV21     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.P010     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.VENDOR_IMPLEMENTED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.MediaBufferAlloc.AnonymousClass1.<clinit>():void");
        }
    }

    private ByteBuffer allocateByteBuffer() {
        return ByteBuffer.allocateDirect((int) (this.mediaFormat.bytePerSample() * ((float) ((Integer) Optional.ofNullable(this.align).map(new C0923a(14)).orElse(Integer.valueOf(this.mediaFormat.dimension()))).intValue())));
    }

    private HardwareBuffer allocateHwBuffer() {
        long usage = this.mediaFormat.getUsage();
        if (usage == 0) {
            usage = 51;
        } else {
            String str = TAG;
            SLog.d(str, "allocateHwBuffer w/ given usage 0x" + Long.toHexString(usage));
        }
        long j2 = usage;
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$ColorFormat[this.mediaFormat.getColorFormat().ordinal()]) {
            case 1:
                return HardwareBuffer.create(this.mediaFormat.getCols(), this.mediaFormat.getRows(), 3, 1, j2);
            case 2:
                return HardwareBuffer.create(this.mediaFormat.getCols(), this.mediaFormat.getRows(), 1, 1, j2);
            case 3:
                return HardwareBuffer.create(this.mediaFormat.getCols(), this.mediaFormat.getRows(), 35, 1, j2);
            case 4:
                return HardwareBuffer.create(this.mediaFormat.getCols(), this.mediaFormat.getRows(), 35, 1, j2);
            case 5:
                return HardwareBuffer.create(this.mediaFormat.getCols(), this.mediaFormat.getRows(), 54, 1, j2);
            case 6:
                return HardwareBuffer.create(this.mediaFormat.getCols(), this.mediaFormat.getRows(), 35, 1, j2);
            default:
                String str2 = TAG;
                SLog.i(str2, "unknown format(" + this.mediaFormat.getColorFormat() + "), so alloc as blob");
                return HardwareBuffer.create((int) this.mediaFormat.size(), 1, 33, 1, j2);
        }
    }

    private boolean isCollective() {
        if ((this.bufferType & 512) != 0) {
            return true;
        }
        return false;
    }

    private boolean isDerivative() {
        if ((this.bufferType & 256) != 0) {
            return true;
        }
        return false;
    }

    private boolean isSharable() {
        if ((this.bufferType & SHARABLE) != 0) {
            return true;
        }
        return false;
    }

    private boolean isSingle() {
        if ((this.bufferType & 1) != 0) {
            return true;
        }
        return false;
    }

    private MutableMediaFormat mergeFormat(MediaFormat mediaFormat2, MediaFormat mediaFormat3) {
        return mergeFormat(mediaFormat2.toMutableFormat(), mediaFormat3);
    }

    public MediaBuffer allocate() {
        MediaBuffer mediaBuffer;
        Class cls;
        MediaFormat mediaFormat2 = this.mediaFormat;
        if (mediaFormat2 == null) {
            this.mediaFormat = this.mediaFormatBuilder.build();
        } else {
            this.mediaFormat = mergeFormat(mediaFormat2, this.mediaFormatBuilder.build());
        }
        if (isSingle()) {
            if (this.data == null && this.mediaFormat.size() == 0) {
                mediaBuffer = new MutableMediaBuffer(this.mediaFormat);
            } else {
                if (this.data == null) {
                    if (isSharable()) {
                        this.data = allocateHwBuffer();
                    } else {
                        this.data = allocateByteBuffer();
                    }
                } else if (!this.mediaFormat.isValid()) {
                    Object obj = this.data;
                    if (!(obj instanceof FileDescriptor) && !(obj instanceof ParcelFileDescriptor)) {
                        try {
                            this.mediaFormat = mergeFormat(this.mediaFormat, (MediaFormat) BufferExtension.describe(obj));
                        } catch (UnsupportedOperationException e) {
                            String str = TAG;
                            SLog.w(str, "format is not valid:" + e);
                        }
                    }
                }
                Align align2 = this.align;
                if (align2 != null) {
                    if (align2.getDimension() == 0) {
                        this.align.adjustAlignByFormat(this.mediaFormat);
                    } else {
                        this.align.adjustAlign();
                    }
                    mediaBuffer = new GenericMediaBuffer(this.mediaFormat, this.align, this.data);
                } else {
                    mediaBuffer = new GenericMediaBuffer(this.mediaFormat, this.data);
                }
            }
        } else if (isCollective()) {
            mediaBuffer = new CollectBufferGroup(this.repBufferId, this.buffers);
        } else if (isDerivative()) {
            mediaBuffer = new DeriveBufferGroup(this.primaryBuffer, this.buffers);
        } else {
            throw new UnsupportedOperationException("unsupported type");
        }
        if (!this.extra.isEmpty()) {
            mediaBuffer.getExtra().putAll(this.extra);
        }
        int i2 = this.flags;
        if (i2 != 0) {
            mediaBuffer.setFlags(i2);
        }
        if (isSharable() && this.data.getClass() != (cls = HardwareBuffer.class)) {
            mediaBuffer = mediaBuffer.convertTo(cls);
        }
        Runnable runnable = this.onReleaseListener;
        if (runnable != null) {
            mediaBuffer.addOnReleaseListener(runnable);
        }
        return mediaBuffer;
    }

    public MutableMediaBuffer allocateMutable() {
        MutableMediaBuffer mutableMediaBuffer;
        MediaBuffer allocate = allocate();
        if (allocate instanceof MutableMediaBuffer) {
            mutableMediaBuffer = (MutableMediaBuffer) allocate;
        } else {
            mutableMediaBuffer = new MutableMediaBuffer(allocate);
        }
        MediaFormat mediaFormat2 = this.mediaFormat;
        if (mediaFormat2 != null) {
            mutableMediaBuffer.setFixedFormat(mediaFormat2);
            mutableMediaBuffer.get();
        }
        return mutableMediaBuffer;
    }

    public MediaBufferAlloc asCompressed() {
        this.mediaFormatBuilder.asCompressed();
        return this;
    }

    public MediaBufferAlloc asGroup() {
        return setGroup(true);
    }

    public MediaBufferAlloc asSharable() {
        return setSharable(true);
    }

    public MediaBufferAlloc set(Object... objArr) {
        for (MediaFormat mediaFormat2 : objArr) {
            if (mediaFormat2 instanceof MediaFormat) {
                SLog.v(TAG, "set media-format: " + mediaFormat2);
                setMediaFormat(mediaFormat2);
            } else if (mediaFormat2 instanceof Map) {
                SLog.v(TAG, "set extra: " + mediaFormat2);
                setExtra((Map) mediaFormat2);
            } else if (mediaFormat2 instanceof Align) {
                SLog.v(TAG, "set align: " + mediaFormat2);
                setAlign((Align) mediaFormat2);
            } else if (mediaFormat2 instanceof ColorFormat) {
                SLog.v(TAG, "set color-format: " + mediaFormat2);
                setColorFormat((ColorFormat) mediaFormat2);
            } else if (mediaFormat2 instanceof MediaType) {
                SLog.v(TAG, "set media-type: " + mediaFormat2);
                setMediaType((MediaType) mediaFormat2);
            } else if (mediaFormat2 instanceof DataType) {
                SLog.v(TAG, "set data-type: " + mediaFormat2);
                setDataType((DataType) mediaFormat2);
            } else if (mediaFormat2 instanceof Shape) {
                SLog.v(TAG, "set shape: " + mediaFormat2);
                setShape((Shape) mediaFormat2);
            } else if (mediaFormat2 instanceof ColorSpace) {
                SLog.v(TAG, "set color-space: " + mediaFormat2);
                setColorSpace((ColorSpace) mediaFormat2);
            } else {
                SLog.v(TAG, "set data: " + mediaFormat2);
                setData(mediaFormat2);
            }
        }
        return this;
    }

    public MediaBufferAlloc setAlign(Align align2) {
        this.align = align2;
        return this;
    }

    public MediaBufferAlloc setAlignOf(int i2, int i7) {
        this.align = Align.of(i2, i7);
        return this;
    }

    public MediaBufferAlloc setAttribute(String str, Object obj) {
        this.mediaFormatBuilder.setAttribute(str, obj);
        return this;
    }

    public MediaBufferAlloc setBuffers(List<MediaBuffer> list) {
        return setBuffers(0, list);
    }

    public MediaBufferAlloc setCodecType(CodecType codecType) {
        this.mediaFormatBuilder.setCodecType(codecType);
        return this;
    }

    public MediaBufferAlloc setColorFormat(ColorFormat colorFormat) {
        this.mediaFormatBuilder.setColorFormat(colorFormat);
        return this;
    }

    public MediaBufferAlloc setColorSpace(ColorSpace colorSpace) {
        this.mediaFormatBuilder.setColorSpace(colorSpace);
        return this;
    }

    public MediaBufferAlloc setCropRect(Rect rect) {
        this.mediaFormatBuilder.setCropRect(rect);
        return this;
    }

    public MediaBufferAlloc setData(Object obj) {
        boolean z;
        if (obj instanceof Number) {
            setMediaType(MediaType.SCALA);
        }
        if (this.data == null) {
            z = true;
        } else {
            z = false;
        }
        Def.check(z, "data should not be null, but " + this.data, new Object[0]);
        this.data = obj;
        return this;
    }

    public MediaBufferAlloc setDataType(DataType dataType) {
        this.mediaFormatBuilder.setDataType(dataType);
        return this;
    }

    public MediaBufferAlloc setExtra(String str, Object obj) {
        this.extra.put(str, obj);
        return this;
    }

    public MediaBufferAlloc setFlags(int i2) {
        this.flags = i2 | this.flags;
        return this;
    }

    public MediaBufferAlloc setFlipType(FlipType flipType) {
        this.mediaFormatBuilder.setFlipType(flipType);
        return this;
    }

    public MediaBufferAlloc setGroup(boolean z) {
        if (z) {
            this.bufferType = 2;
            return this;
        }
        this.bufferType = 1;
        return this;
    }

    public MediaBufferAlloc setMediaFormat(MediaFormat mediaFormat2) {
        this.mediaFormat = mediaFormat2;
        return this;
    }

    public MediaBufferAlloc setMediaType(MediaType mediaType) {
        this.mediaFormatBuilder.setMediaType(mediaType);
        return this;
    }

    public MediaBufferAlloc setMetadataKey(int i2) {
        if (i2 == 1) {
            return setExtra("exif", Boolean.TRUE);
        }
        if (i2 == 2) {
            return setExtra("icc", Boolean.TRUE);
        }
        if (i2 == 3) {
            return setExtra("gain-map", Boolean.TRUE);
        }
        throw new UnsupportedOperationException(C0086a.i(i2, "Unsupported metadata-key given! key="));
    }

    public MediaBufferAlloc setOnReleaseListener(Runnable runnable) {
        this.onReleaseListener = runnable;
        return this;
    }

    public MediaBufferAlloc setScanline(int i2) {
        Align align2 = this.align;
        if (align2 == null) {
            this.align = Align.scanlineOf(i2);
            return this;
        }
        align2.setScanline(i2);
        return this;
    }

    public MediaBufferAlloc setShape(int i2, int i7) {
        this.mediaFormatBuilder.setShape(i2, i7);
        return this;
    }

    public MediaBufferAlloc setSharable(boolean z) {
        this.bufferType |= SHARABLE;
        return this;
    }

    public MediaBufferAlloc setStride(int i2) {
        Align align2 = this.align;
        if (align2 == null) {
            this.align = Align.strideOf(i2);
            return this;
        }
        align2.setStride(i2);
        return this;
    }

    public MediaBufferAlloc setTimestampUs(long j2) {
        this.extra.put(Message.KEY_TIMESTAMP_US, Long.valueOf(j2));
        return this;
    }

    public MediaBufferAlloc setUsage(long j2) {
        this.mediaFormatBuilder.setUsage(j2);
        return this;
    }

    public MediaBuffer wrap(Object obj) {
        return setData(obj).allocate();
    }

    private MutableMediaFormat mergeFormat(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat2) {
        MediaType mediaType = mutableMediaFormat.getMediaType();
        MediaType mediaType2 = MediaType.NONE;
        if (mediaType == mediaType2 && mediaFormat2.getMediaType() != mediaType2) {
            mutableMediaFormat.setMediaType(mediaFormat2.getMediaType());
        }
        if ((mutableMediaFormat.getShape() == null || mutableMediaFormat.getShape().isEmpty()) && mediaFormat2.getShape() != null) {
            mutableMediaFormat.setShape(mediaFormat2.getShape());
        }
        DataType dataType = mutableMediaFormat.getDataType();
        DataType dataType2 = DataType.NONE;
        if (dataType == dataType2 && mediaFormat2.getDataType() != dataType2) {
            mutableMediaFormat.setDataType(mediaFormat2.getDataType());
        }
        ColorFormat colorFormat = mutableMediaFormat.getColorFormat();
        ColorFormat colorFormat2 = ColorFormat.NONE;
        if (colorFormat == colorFormat2 && mediaFormat2.getColorFormat() != colorFormat2) {
            mutableMediaFormat.setColorFormat(mediaFormat2.getColorFormat());
        }
        ColorSpace colorSpace = mutableMediaFormat.getColorSpace();
        ColorSpace colorSpace2 = ColorSpace.NONE;
        if (colorSpace == colorSpace2 && mediaFormat2.getColorSpace() != colorSpace2) {
            mutableMediaFormat.setColorSpace(mediaFormat2.getColorSpace());
        }
        FlipType flipType = mutableMediaFormat.getFlipType();
        FlipType flipType2 = FlipType.NONE;
        if (flipType == flipType2 && mediaFormat2.getFlipType() != flipType2) {
            mutableMediaFormat.setFlipType(mediaFormat2.getFlipType());
        }
        CodecType codecType = mutableMediaFormat.getCodecType();
        CodecType codecType2 = CodecType.NONE;
        if (codecType == codecType2 && mediaFormat2.getCodecType() != codecType2) {
            mutableMediaFormat.setCodecType(mediaFormat2.getCodecType());
        }
        if (mutableMediaFormat.getCropRect() == null && mediaFormat2.getCropRect() != null && !mediaFormat2.getCropRect().isEmpty()) {
            mutableMediaFormat.setCropRect(mediaFormat2.getCropRect());
        }
        SplitType splitType = mutableMediaFormat.getSplitType();
        SplitType splitType2 = SplitType.NONE;
        if (splitType == splitType2 && mediaFormat2.getSplitType() != splitType2) {
            mutableMediaFormat.setSplitType(mediaFormat2.getSplitType());
        }
        return mutableMediaFormat;
    }

    public MediaBufferAlloc setAlign(int i2, int i7) {
        this.align = Align.shapeOf(i2, i7);
        return this;
    }

    public MediaBufferAlloc setAlignOf(int i2) {
        this.align = Align.of(i2);
        return this;
    }

    public MediaBufferAlloc setBuffers(int i2, List<MediaBuffer> list) {
        this.bufferType = 514;
        this.repBufferId = i2;
        this.buffers = list;
        return this;
    }

    public MediaBufferAlloc setDataType(DataType dataType, int i2) {
        this.mediaFormatBuilder.setDataType(dataType, i2);
        return this;
    }

    public MediaBufferAlloc setExtra(Map<String, Object> map) {
        this.extra.putAll(map);
        return this;
    }

    public MediaBufferAlloc setMediaFormat(MutableMediaFormat mutableMediaFormat) {
        return setMediaFormat(mutableMediaFormat.toMediaFormat());
    }

    public MediaBufferAlloc setShape(Rect rect) {
        this.mediaFormatBuilder.setShape(rect);
        return this;
    }

    public MediaBufferAlloc setShape(int i2) {
        this.mediaFormatBuilder.setShape(i2);
        return this;
    }

    public MediaBufferAlloc setShape(Shape shape) {
        this.mediaFormatBuilder.setShape(shape);
        return this;
    }

    public MediaBufferAlloc setBuffers(int i2, MediaBuffer... mediaBufferArr) {
        return setBuffers(i2, (List<MediaBuffer>) new ArrayList(Arrays.asList(mediaBufferArr)));
    }

    public MediaBufferAlloc setShape(int i2, int i7, int i8, int i10) {
        this.mediaFormatBuilder.setShape(i2, i7, i8, i10);
        return this;
    }

    public MediaBufferAlloc setBuffers(MediaBuffer mediaBuffer, List<MediaBuffer> list) {
        this.bufferType = 258;
        this.primaryBuffer = mediaBuffer;
        this.buffers = list;
        return this;
    }

    public MutableMediaBuffer allocateMutable(MediaBuffer mediaBuffer) {
        if (mediaBuffer instanceof MutableMediaBuffer) {
            return (MutableMediaBuffer) mediaBuffer;
        }
        return new MutableMediaBuffer(mediaBuffer);
    }

    public MediaBufferAlloc setBuffers(MediaBuffer mediaBuffer, MediaBuffer... mediaBufferArr) {
        return setBuffers(mediaBuffer, (List<MediaBuffer>) new ArrayList(Arrays.asList(mediaBufferArr)));
    }
}
