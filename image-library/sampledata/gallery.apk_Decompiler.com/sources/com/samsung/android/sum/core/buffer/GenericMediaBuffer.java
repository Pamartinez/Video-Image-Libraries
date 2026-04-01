package com.samsung.android.sum.core.buffer;

import android.hardware.HardwareBuffer;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.samsung.android.sum.core.DebugUtils;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.format.Duplicable;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.types.MediaType;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GenericMediaBuffer<T> extends MediaBufferBase {
    public static final Parcelable.Creator<GenericMediaBuffer<?>> CREATOR = new Parcelable.Creator<GenericMediaBuffer<?>>() {
        public GenericMediaBuffer<?> createFromParcel(Parcel parcel) {
            return new GenericMediaBuffer<>(parcel);
        }

        public GenericMediaBuffer<?>[] newArray(int i2) {
            return new GenericMediaBuffer[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) GenericMediaBuffer.class);
    private T data;
    private final Class<T> dataClass;
    private volatile List<MediaFormat> planes;

    public GenericMediaBuffer(MediaFormat mediaFormat, T t) {
        super(mediaFormat);
        this.data = t;
        this.dataClass = t.getClass();
        adjustShape();
    }

    private String dataToString(Object obj) {
        return (String) Optional.ofNullable(obj).map(new C0923a(13)).orElse("n/a");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$dataToString$2(Object obj) {
        try {
            return BufferExtension.stringfy(obj);
        } catch (UnsupportedOperationException unused) {
            return obj.toString();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$0() {
        if (this.data != null && isRequiredToReleaseExplicitly() && !containFlags(new int[]{16})) {
            try {
                BufferExtension.dealloc(this.data);
            } catch (UnsupportedOperationException unused) {
            }
        }
        this.data = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$toString$1() {
        if (!DebugUtils.TRACE_BUFFER) {
            return "[trace off]";
        }
        return Def.contentToString("data=" + dataToString(this.data));
    }

    public /* bridge */ /* synthetic */ void addExtra(Map map) {
        super.addExtra(map);
    }

    public /* bridge */ /* synthetic */ void addOnReleaseListener(Runnable[] runnableArr) {
        super.addOnReleaseListener(runnableArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void adjustShape() {
        /*
            r6 = this;
            T r0 = r6.data
            if (r0 == 0) goto L_0x001a
            boolean r1 = r0 instanceof java.nio.ByteBuffer
            if (r1 == 0) goto L_0x000f
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0
            int r0 = r0.limit()
            goto L_0x001b
        L_0x000f:
            boolean r1 = r0 instanceof android.graphics.Bitmap
            if (r1 == 0) goto L_0x001a
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            int r0 = r0.getByteCount()
            goto L_0x001b
        L_0x001a:
            r0 = 0
        L_0x001b:
            com.samsung.android.sum.core.format.MediaFormat r1 = r6.format
            com.samsung.android.sum.core.types.ColorFormat r1 = r1.getColorFormat()
            com.samsung.android.sum.core.types.ColorFormat r2 = com.samsung.android.sum.core.types.ColorFormat.NONE
            if (r1 == r2) goto L_0x00da
            com.samsung.android.sum.core.types.ColorFormat r2 = com.samsung.android.sum.core.types.ColorFormat.OPAQUE
            if (r1 == r2) goto L_0x00da
            com.samsung.android.sum.core.types.ColorFormat r2 = com.samsung.android.sum.core.types.ColorFormat.P010
            if (r1 == r2) goto L_0x00da
            com.samsung.android.sum.core.types.ColorFormat r2 = com.samsung.android.sum.core.types.ColorFormat.P010_ZIPPED
            if (r1 != r2) goto L_0x0033
            goto L_0x00da
        L_0x0033:
            com.samsung.android.sum.core.format.MediaFormat r2 = r6.format
            com.samsung.android.sum.core.format.MutableMediaFormat r2 = r2.toMutableFormat()
            if (r0 == 0) goto L_0x00da
            com.samsung.android.sum.core.format.MediaFormat r3 = r6.format
            com.samsung.android.sum.core.types.MediaType r3 = r3.getMediaType()
            boolean r3 = r3.isCompressed()
            if (r3 != 0) goto L_0x00da
            com.samsung.android.sum.core.format.MediaFormat r3 = r6.format
            com.samsung.android.sum.core.format.Shape r3 = r3.getShape()
            if (r3 == 0) goto L_0x00da
            com.samsung.android.sum.core.format.MediaFormat r3 = r6.format
            com.samsung.android.sum.core.types.DataType r3 = r3.getDataType()
            if (r3 == 0) goto L_0x0061
            com.samsung.android.sum.core.format.MediaFormat r3 = r6.format
            com.samsung.android.sum.core.types.DataType r3 = r3.getDataType()
            com.samsung.android.sum.core.types.DataType r4 = com.samsung.android.sum.core.types.DataType.NONE
            if (r3 != r4) goto L_0x00da
        L_0x0061:
            int r3 = r6.getChannels()
            com.samsung.android.sum.core.buffer.Align r4 = r6.align
            int r4 = r4.getDimension()
            float r5 = (float) r0
            float r4 = (float) r4
            float r1 = r1.bytePerPixel()
            float r1 = r1 * r4
            float r5 = r5 / r1
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0083
            com.samsung.android.sum.core.types.DataType r0 = com.samsung.android.sum.core.types.DataType.U8
            com.samsung.android.sum.core.types.DataType r0 = com.samsung.android.sum.core.types.DataType.of(r0, r3)
            r2.setDataType(r0)
            goto L_0x00b2
        L_0x0083:
            r1 = 1073741824(0x40000000, float:2.0)
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0093
            com.samsung.android.sum.core.types.DataType r0 = com.samsung.android.sum.core.types.DataType.U16
            com.samsung.android.sum.core.types.DataType r0 = com.samsung.android.sum.core.types.DataType.of(r0, r3)
            r2.setDataType(r0)
            goto L_0x00b2
        L_0x0093:
            r1 = 1077936128(0x40400000, float:3.0)
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x00a3
            com.samsung.android.sum.core.types.DataType r0 = com.samsung.android.sum.core.types.DataType.F32
            com.samsung.android.sum.core.types.DataType r0 = com.samsung.android.sum.core.types.DataType.of(r0, r3)
            r2.setDataType(r0)
            goto L_0x00b2
        L_0x00a3:
            r1 = 1082130432(0x40800000, float:4.0)
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x00b9
            com.samsung.android.sum.core.types.DataType r0 = com.samsung.android.sum.core.types.DataType.F64
            com.samsung.android.sum.core.types.DataType r0 = com.samsung.android.sum.core.types.DataType.of(r0, r3)
            r2.setDataType(r0)
        L_0x00b2:
            com.samsung.android.sum.core.format.MediaFormat r0 = r2.toMediaFormat()
            r6.format = r0
            return
        L_0x00b9:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "data-size and align(shape) doesn't match"
            java.lang.String r3 = " vs "
            java.lang.StringBuilder r0 = c0.C0086a.o(r0, r2, r3)
            com.samsung.android.sum.core.format.MediaFormat r2 = r6.format
            r0.append(r2)
            java.lang.String r2 = " & "
            r0.append(r2)
            com.samsung.android.sum.core.buffer.Align r6 = r6.align
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r1.<init>(r6)
            throw r1
        L_0x00da:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.GenericMediaBuffer.adjustShape():void");
    }

    public /* bridge */ /* synthetic */ MediaBuffer clearFlags(int[] iArr) {
        return super.clearFlags(iArr);
    }

    public /* bridge */ /* synthetic */ boolean containFlags(int[] iArr) {
        return super.containFlags(iArr);
    }

    public /* bridge */ /* synthetic */ boolean containsAllExtra(String[] strArr) {
        return super.containsAllExtra(strArr);
    }

    public /* bridge */ /* synthetic */ boolean containsAnyExtra(String[] strArr) {
        return super.containsAnyExtra(strArr);
    }

    public /* bridge */ /* synthetic */ boolean containsExtra(String str) {
        return super.containsExtra(str);
    }

    public <V> MediaBuffer convertTo(Class<V> cls) {
        MediaBuffer copyTo = copyTo(cls);
        release();
        return copyTo;
    }

    public <V> MediaBuffer copyTo(Class<V> cls) {
        Object typedData = getTypedData(cls);
        if (cls.isAssignableFrom(this.dataClass)) {
            if (cls == HardwareBuffer.class) {
                return dup();
            }
            if (typedData instanceof Duplicable) {
                typedData = ((Duplicable) typedData).dupAll();
            } else if (typedData instanceof ByteBuffer) {
                ByteBuffer byteBuffer = (ByteBuffer) typedData;
                int limit = byteBuffer.limit();
                if (limit == 0) {
                    limit = byteBuffer.capacity();
                }
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(limit);
                byteBuffer.rewind();
                allocateDirect.put(byteBuffer);
                byteBuffer.rewind();
                allocateDirect.flip();
                typedData = allocateDirect;
            } else {
                throw new UnsupportedOperationException("Not supported for given type=" + this.dataClass);
            }
        }
        return MediaBuffer.newAlloc().setMediaFormat(this.format).setExtra(this.extra).setFlags(this.flags).wrap(typedData);
    }

    public /* bridge */ /* synthetic */ int describeContents() {
        return super.describeContents();
    }

    public /* bridge */ /* synthetic */ MediaBuffer dup() {
        return super.dup();
    }

    public /* bridge */ /* synthetic */ MediaBuffer dupAll() {
        return super.dupAll();
    }

    public /* bridge */ /* synthetic */ Align getAlign() {
        return super.getAlign();
    }

    public /* bridge */ /* synthetic */ int getChannels() {
        return super.getChannels();
    }

    public /* bridge */ /* synthetic */ int getCols() {
        return super.getCols();
    }

    public <V> V getData() {
        return this.dataClass.cast(this.data);
    }

    public Class<?> getDataClass() {
        return this.dataClass;
    }

    public MediaBuffer getExifBuffer() {
        if (this.format.getMediaType() != MediaType.META || !this.format.contains("exif")) {
            return null;
        }
        return this;
    }

    public /* bridge */ /* synthetic */ Object getExtra(String str) {
        return super.getExtra(str);
    }

    public /* bridge */ /* synthetic */ int getFlags() {
        return super.getFlags();
    }

    public /* bridge */ /* synthetic */ MediaFormat getFormat() {
        return super.getFormat();
    }

    public MediaBuffer getIccBuffer() {
        if (this.format.getMediaType() != MediaType.META || !this.format.contains("icc")) {
            return null;
        }
        return this;
    }

    public List<MediaBuffer> getMetaDataBuffers() {
        if (this.format.getMediaType() == MediaType.META) {
            return asList();
        }
        return null;
    }

    public /* bridge */ /* synthetic */ int getRows() {
        return super.getRows();
    }

    public /* bridge */ /* synthetic */ int getScanline() {
        return super.getScanline();
    }

    public /* bridge */ /* synthetic */ int getStride() {
        return super.getStride();
    }

    public <V> V getTypedData(Class<V> cls) {
        synchronized (this.dataLock) {
            try {
                if (this.data != null) {
                    if (!cls.isAssignableFrom(this.dataClass)) {
                        if (cls.isPrimitive()) {
                            if (!Number.class.isAssignableFrom(this.dataClass)) {
                            }
                        }
                        if (!this.dataClass.isPrimitive() || !Number.class.isAssignableFrom(cls)) {
                            V transformDataTo = transformDataTo(this.data, cls);
                            return transformDataTo;
                        }
                    }
                    Object obj = this.data;
                    return obj;
                }
                throw new NullPointerException();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public /* bridge */ /* synthetic */ Object getTypedDataOr(Class cls, Object obj) {
        return super.getTypedDataOr(cls, obj);
    }

    public void release() {
        String str = TAG;
        SLog.v(str, "release: " + hashCode());
        release(new q(this, 1));
    }

    public /* bridge */ /* synthetic */ List removeAllOnReleaseListeners() {
        return super.removeAllOnReleaseListeners();
    }

    public /* bridge */ /* synthetic */ Object removeExtra(String str) {
        return super.removeExtra(str);
    }

    public /* bridge */ /* synthetic */ void setExtra(String str, Object obj) {
        super.setExtra(str, obj);
    }

    public /* bridge */ /* synthetic */ MediaBuffer setFlags(int[] iArr) {
        return super.setFlags(iArr);
    }

    public /* bridge */ /* synthetic */ MediaBuffer setScanline(int i2) {
        return super.setScanline(i2);
    }

    public /* bridge */ /* synthetic */ MediaBuffer setStride(int i2) {
        return super.setStride(i2);
    }

    public long size() {
        return (long) (getFormat().bytePerPixel() * ((float) getAlign().getDimension()));
    }

    public Stream<MediaBuffer> stream() {
        return Stream.of(this);
    }

    public String toString() {
        return contentToString(this, new C0925c(2, this));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.hardware.HardwareBuffer} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeToParcel(android.os.Parcel r4, int r5) {
        /*
            r3 = this;
            java.lang.Class<android.hardware.HardwareBuffer> r0 = android.hardware.HardwareBuffer.class
            super.writeToParcel(r4, r5)
            T r1 = r3.data
            boolean r2 = r1 instanceof android.os.ParcelFileDescriptor
            if (r2 == 0) goto L_0x0017
            r0 = 3
            r4.writeInt(r0)
            T r0 = r3.data
            android.os.Parcelable r0 = (android.os.Parcelable) r0
            r4.writeParcelable(r0, r5)
            goto L_0x0048
        L_0x0017:
            boolean r1 = r1 instanceof java.lang.Number
            if (r1 == 0) goto L_0x0027
            r5 = 5
            r4.writeInt(r5)
            T r5 = r3.data
            java.lang.Number r5 = (java.lang.Number) r5
            r4.writeSerializable(r5)
            goto L_0x0048
        L_0x0027:
            com.samsung.android.sum.core.format.MediaFormat r1 = r3.getFormat()     // Catch:{ UnsupportedOperationException -> 0x0034 }
            T r2 = r3.data     // Catch:{ UnsupportedOperationException -> 0x0034 }
            java.lang.Object r1 = com.samsung.android.sum.core.buffer.BufferExtension.transform(r1, r2, r0)     // Catch:{ UnsupportedOperationException -> 0x0034 }
            android.hardware.HardwareBuffer r1 = (android.hardware.HardwareBuffer) r1     // Catch:{ UnsupportedOperationException -> 0x0034 }
            goto L_0x0041
        L_0x0034:
            com.samsung.android.sum.core.format.MediaFormat r1 = r3.getFormat()
            T r2 = r3.data
            java.lang.Object r0 = com.samsung.android.sum.core.buffer.BufferExtension.transform(r1, r2, r0)
            r1 = r0
            android.hardware.HardwareBuffer r1 = (android.hardware.HardwareBuffer) r1
        L_0x0041:
            r0 = 2
            r4.writeInt(r0)
            r4.writeParcelable(r1, r5)
        L_0x0048:
            java.util.List<com.samsung.android.sum.core.format.MediaFormat> r3 = r3.planes
            java.io.Serializable r3 = (java.io.Serializable) r3
            r4.writeSerializable(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.buffer.GenericMediaBuffer.writeToParcel(android.os.Parcel, int):void");
    }

    public /* bridge */ /* synthetic */ Object getExtra(String str, Object obj) {
        return super.getExtra(str, obj);
    }

    public /* bridge */ /* synthetic */ void setExtra(Map map) {
        super.setExtra(map);
    }

    public /* bridge */ /* synthetic */ Map getExtra() {
        return super.getExtra();
    }

    public GenericMediaBuffer(MediaFormat mediaFormat, Align align, T t) {
        super(mediaFormat, align);
        this.data = t;
        this.dataClass = t.getClass();
        adjustShape();
    }

    public GenericMediaBuffer(Parcel parcel) {
        super(parcel);
        int readInt = parcel.readInt();
        if (readInt == 2) {
            Class<HardwareBuffer> cls = HardwareBuffer.class;
            this.dataClass = cls;
            this.data = parcel.readParcelable(cls.getClassLoader(), HardwareBuffer.class);
        } else if (readInt == 3) {
            Class<ParcelFileDescriptor> cls2 = ParcelFileDescriptor.class;
            this.dataClass = cls2;
            this.data = parcel.readParcelable(cls2.getClassLoader(), ParcelFileDescriptor.class);
        } else if (readInt == 5) {
            T readSerializable = parcel.readSerializable();
            this.data = readSerializable;
            this.dataClass = readSerializable.getClass();
        } else {
            throw new IllegalArgumentException("unknown type");
        }
        this.planes = (List) parcel.readSerializable();
    }
}
