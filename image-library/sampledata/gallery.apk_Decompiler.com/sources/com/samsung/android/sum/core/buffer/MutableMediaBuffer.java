package com.samsung.android.sum.core.buffer;

import Bd.C0726b;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.UniExifInterface;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.functional.PlaceHolder;
import com.samsung.android.sum.core.types.MediaType;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MutableMediaBuffer extends MediaBufferBase implements PlaceHolder<MediaBuffer> {
    public static final Parcelable.Creator<MutableMediaBuffer> CREATOR = new Parcelable.Creator<MutableMediaBuffer>() {
        public MutableMediaBuffer createFromParcel(Parcel parcel) {
            return new MutableMediaBuffer(parcel);
        }

        public MutableMediaBuffer[] newArray(int i2) {
            return new MutableMediaBuffer[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) MutableMediaBuffer.class);
    private MediaBuffer buffer;

    public MutableMediaBuffer() {
        super(MediaFormat.newBuilder(MediaType.NONE).build());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$containFlags$2(int[] iArr) {
        return Boolean.valueOf(super.containFlags(iArr));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getExifBuffer$9(MediaBuffer mediaBuffer) {
        if (mediaBuffer.getFormat().getMediaType() != MediaType.META || !mediaBuffer.getFormat().contains("exif")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getIccBuffer$10(MediaBuffer mediaBuffer) {
        if (mediaBuffer.getFormat().getMediaType() != MediaType.META || !mediaBuffer.getFormat().contains("icc")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMetaDataBuffers$8(MediaBuffer mediaBuffer) {
        if (mediaBuffer.getFormat().getMediaType() == MediaType.META) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$0() {
        this.buffer = null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setExifBuffer$11(MediaBuffer mediaBuffer) {
        return !mediaBuffer.getFormat().contains("exif");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setIccBuffer$12(MediaBuffer mediaBuffer) {
        return !mediaBuffer.getFormat().contains("icc");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Stream lambda$stream$3() {
        return Stream.of(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$toString$13() {
        StringBuilder sb2 = new StringBuilder("buffer=");
        Object obj = this.buffer;
        if (obj == null) {
            obj = "n/a";
        }
        sb2.append(obj);
        return Def.contentToStringln("", sb2.toString());
    }

    public void addExtra(Map<String, Object> map) {
        MediaBuffer mediaBuffer = this.buffer;
        if (mediaBuffer != null) {
            mediaBuffer.addExtra(map);
        } else {
            super.addExtra(map);
        }
    }

    public /* bridge */ /* synthetic */ void addOnReleaseListener(Runnable[] runnableArr) {
        super.addOnReleaseListener(runnableArr);
    }

    public /* bridge */ /* synthetic */ MediaBuffer clearFlags(int[] iArr) {
        return super.clearFlags(iArr);
    }

    public boolean containFlags(int... iArr) {
        return ((Boolean) Optional.ofNullable(this.buffer).map(new m(3, iArr)).orElseGet(new C0726b(6, this, iArr))).booleanValue();
    }

    public boolean containsAllExtra(String... strArr) {
        MediaBuffer mediaBuffer = this.buffer;
        if (mediaBuffer != null) {
            return mediaBuffer.containsAllExtra(strArr);
        }
        return super.containsAllExtra(strArr);
    }

    public boolean containsAnyExtra(String... strArr) {
        MediaBuffer mediaBuffer = this.buffer;
        if (mediaBuffer != null) {
            return mediaBuffer.containsAnyExtra(strArr);
        }
        return super.containsAnyExtra(strArr);
    }

    public boolean containsExtra(String str) {
        MediaBuffer mediaBuffer = this.buffer;
        if (mediaBuffer != null) {
            return mediaBuffer.containsExtra(str);
        }
        return super.containsExtra(str);
    }

    public <V> MediaBuffer convertTo(Class<V> cls) {
        return (MediaBuffer) Optional.ofNullable(this.buffer).map(new p(cls, 3)).orElse((Object) null);
    }

    public <T> MediaBuffer copyTo(Class<T> cls) {
        return (MediaBuffer) Optional.ofNullable(this.buffer).map(new p(cls, 4)).orElse((Object) null);
    }

    public int describeContents() {
        return 0;
    }

    public /* bridge */ /* synthetic */ MediaBuffer dup() {
        return super.dup();
    }

    public /* bridge */ /* synthetic */ MediaBuffer dupAll() {
        return super.dupAll();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MutableMediaBuffer) {
            if (get() == ((MutableMediaBuffer) obj).get()) {
                return true;
            }
            return false;
        } else if (get() == obj) {
            return true;
        } else {
            return false;
        }
    }

    public MediaBuffer get() {
        return this.buffer;
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
        return Optional.ofNullable(this.buffer).map(new C0923a(25)).orElse((Object) null);
    }

    public Class<?> getDataClass() {
        return (Class) Optional.ofNullable(this.buffer).map(new C0923a(28)).orElse((Object) null);
    }

    public MediaBuffer getExifBuffer() {
        return this.buffer.asList().stream().filter(new C0927e(8)).findAny().orElse((Object) null);
    }

    public Map<String, Object> getExtra() {
        MediaBuffer mediaBuffer = this.buffer;
        return mediaBuffer != null ? mediaBuffer.getExtra() : super.getExtra();
    }

    public MediaFormat getFixedFormat() {
        return this.format;
    }

    public /* bridge */ /* synthetic */ int getFlags() {
        return super.getFlags();
    }

    public MediaFormat getFormat() {
        return (MediaFormat) Optional.ofNullable(this.buffer).map(new C0923a(27)).orElseGet(new C(this, 2));
    }

    public MediaBuffer getIccBuffer() {
        return this.buffer.asList().stream().filter(new C0927e(7)).findAny().orElse((Object) null);
    }

    public List<MediaBuffer> getMetaDataBuffers() {
        return (List) this.buffer.asList().stream().filter(new C0927e(9)).collect(Collectors.toList());
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
        return Optional.ofNullable(this.buffer).map(new p(cls, 2)).orElseThrow(new u(4));
    }

    public /* bridge */ /* synthetic */ Object getTypedDataOr(Class cls, Object obj) {
        return super.getTypedDataOr(cls, obj);
    }

    public boolean isEmpty() {
        if (this.buffer == null) {
            return true;
        }
        return false;
    }

    public boolean isNotEmpty() {
        if (this.buffer != null) {
            return true;
        }
        return false;
    }

    public MediaBuffer moveTo(MutableMediaBuffer mutableMediaBuffer) {
        MediaBuffer mediaBuffer = mutableMediaBuffer.buffer;
        mutableMediaBuffer.put(reset());
        return mediaBuffer;
    }

    public void release() {
        release(new q(this, 3));
    }

    public /* bridge */ /* synthetic */ List removeAllOnReleaseListeners() {
        return super.removeAllOnReleaseListeners();
    }

    public /* bridge */ /* synthetic */ Object removeExtra(String str) {
        return super.removeExtra(str);
    }

    public MediaBuffer setExifBuffer(MediaBuffer mediaBuffer) {
        List list = (List) this.buffer.asList().stream().filter(new C0927e(6)).collect(Collectors.toList());
        list.add(mediaBuffer);
        return setMetaDataBuffer(list);
    }

    public /* bridge */ /* synthetic */ void setExtra(String str, Object obj) {
        super.setExtra(str, obj);
    }

    public void setFixedFormat(MediaFormat mediaFormat) {
        this.format = mediaFormat;
    }

    public /* bridge */ /* synthetic */ MediaBuffer setFlags(int[] iArr) {
        return super.setFlags(iArr);
    }

    public MediaBuffer setIccBuffer(MediaBuffer mediaBuffer) {
        List list = (List) this.buffer.asList().stream().filter(new C0927e(10)).collect(Collectors.toList());
        list.add(mediaBuffer);
        return setMetaDataBuffer(list);
    }

    public MediaBuffer setMetaDataBuffer(List<MediaBuffer> list) {
        List<MediaBuffer> asList = this.buffer.asList();
        asList.addAll(list);
        put(MediaBuffer.newGroupAlloc().setBuffers(this.buffer, asList).allocate());
        return this.buffer;
    }

    public /* bridge */ /* synthetic */ MediaBuffer setScanline(int i2) {
        return super.setScanline(i2);
    }

    public /* bridge */ /* synthetic */ MediaBuffer setStride(int i2) {
        return super.setStride(i2);
    }

    public long size() {
        MediaBuffer mediaBuffer = this.buffer;
        if (mediaBuffer != null) {
            return mediaBuffer.size();
        }
        return 0;
    }

    public Stream<MediaBuffer> stream() {
        return (Stream) Optional.ofNullable(this.buffer).map(new C0923a(26)).orElseGet(new C(this, 1));
    }

    public String toString() {
        return contentToString(this, new C(this, 0));
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        if (this.buffer != null) {
            parcel.writeInt(1);
            parcel.writeParcelable(this.buffer, i2);
            return;
        }
        parcel.writeInt(0);
    }

    public MutableMediaBuffer(MediaFormat mediaFormat) {
        super(mediaFormat);
    }

    public <V> V getExtra(String str) {
        MediaBuffer mediaBuffer = this.buffer;
        return mediaBuffer != null ? mediaBuffer.getExtra(str) : super.getExtra(str);
    }

    public void put(MediaBuffer mediaBuffer) {
        if (mediaBuffer instanceof MutableMediaBuffer) {
            if (mediaBuffer.isEmpty()) {
                setFixedFormat(((MutableMediaBuffer) mediaBuffer).getFixedFormat());
                setExtra(mediaBuffer.getExtra());
                setFlags(new int[]{mediaBuffer.getFlags()});
                return;
            }
            mediaBuffer = ((MutableMediaBuffer) mediaBuffer).reset();
        }
        MediaBuffer mediaBuffer2 = this.buffer;
        if (mediaBuffer != mediaBuffer2) {
            if (mediaBuffer2 != null) {
                mediaBuffer.addExtra(mediaBuffer2.getExtra());
            }
            HashMap<String, Object> hashMap = this.extra;
            if (hashMap != null) {
                mediaBuffer.addExtra(hashMap);
            }
            this.buffer = mediaBuffer;
            this.align = mediaBuffer.getAlign();
        }
    }

    public MediaBuffer reset() {
        MediaBuffer mediaBuffer = this.buffer;
        if (mediaBuffer != null) {
            super.addExtra(mediaBuffer.getExtra());
            this.buffer = null;
        }
        return mediaBuffer;
    }

    public /* bridge */ /* synthetic */ void setExtra(Map map) {
        super.setExtra(map);
    }

    public MutableMediaBuffer(MediaFormat mediaFormat, Align align) {
        super(mediaFormat, align);
    }

    public <V> V getExtra(String str, V v) {
        MediaBuffer mediaBuffer = this.buffer;
        return mediaBuffer != null ? mediaBuffer.getExtra(str, v) : super.getExtra(str, v);
    }

    public MutableMediaBuffer(MediaBuffer mediaBuffer) {
        super(MediaFormat.newBuilder(MediaType.NONE).build());
        put(mediaBuffer);
    }

    public MutableMediaBuffer(Parcel parcel) {
        super(parcel);
        if (parcel.readInt() != 0) {
            this.buffer = (MediaBuffer) parcel.readParcelable(MediaBuffer.class.getClassLoader());
        }
    }

    public MediaBuffer setExifBuffer(ByteBuffer byteBuffer) {
        return setExifBuffer(MediaBuffer.newMetaAlloc().setShape(1, byteBuffer.limit()).setAttribute("exif", Boolean.TRUE).wrap(byteBuffer));
    }

    public MediaBuffer setIccBuffer(ByteBuffer byteBuffer) {
        return setIccBuffer(MediaBuffer.newMetaAlloc().setShape(1, byteBuffer.limit()).setAttribute("icc", Boolean.TRUE).wrap(byteBuffer));
    }

    public MediaBuffer setExifBuffer(UniExifInterface uniExifInterface) {
        return setExifBuffer(uniExifInterface.toExifByteBuffer());
    }
}
