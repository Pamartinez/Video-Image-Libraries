package com.samsung.android.sum.core.buffer;

import android.os.Parcel;
import c0.C0086a;
import com.samsung.android.sum.core.DebugUtils;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.types.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaBufferGroup extends MediaBufferBase {
    private static final String TAG = Def.tagOf((Class<?>) MediaBufferGroup.class);
    protected List<MediaBuffer> buffers;
    protected int primaryId = -1;

    public MediaBufferGroup(MediaFormat mediaFormat, List<MediaBuffer> list) {
        super(mediaFormat);
        this.buffers = list;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getExifBuffer$1(MediaBuffer mediaBuffer) {
        if (mediaBuffer.getFormat().getMediaType() != MediaType.META || !mediaBuffer.getFormat().contains("exif")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getIccBuffer$2(MediaBuffer mediaBuffer) {
        if (mediaBuffer.getFormat().getMediaType() != MediaType.META || !mediaBuffer.getFormat().contains("icc")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMetaDataBuffers$0(MediaBuffer mediaBuffer) {
        if (mediaBuffer.getFormat().getMediaType() == MediaType.META) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$3() {
        List<MediaBuffer> list = this.buffers;
        if (list != null) {
            list.clear();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$toString$4(int i2) {
        StringBuilder o2 = C0086a.o(i2, "(", "-th)");
        o2.append(this.buffers.get(i2).toString());
        return o2.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$toString$5() {
        if (!DebugUtils.TRACE_BUFFER) {
            return "[trace off]";
        }
        return Def.contentToStringln("    ", "data[#" + this.buffers.size() + "]\n" + ((String) IntStream.range(0, this.buffers.size()).mapToObj(new A(this)).collect(Collectors.joining("\n"))));
    }

    public MediaBufferGroup addBuffer(MediaBuffer... mediaBufferArr) {
        return addBuffer((List<MediaBuffer>) Arrays.asList(mediaBufferArr));
    }

    public /* bridge */ /* synthetic */ void addExtra(Map map) {
        super.addExtra(map);
    }

    public /* bridge */ /* synthetic */ void addOnReleaseListener(Runnable[] runnableArr) {
        super.addOnReleaseListener(runnableArr);
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

    public /* bridge */ /* synthetic */ int describeContents() {
        return super.describeContents();
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

    public MediaBuffer getExifBuffer() {
        return this.buffers.stream().filter(new C0927e(3)).findAny().orElse((Object) null);
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
        return this.buffers.stream().filter(new C0927e(4)).findAny().orElse((Object) null);
    }

    public List<MediaBuffer> getMetaDataBuffers() {
        return (List) this.buffers.stream().filter(new C0927e(5)).collect(Collectors.toList());
    }

    public abstract MediaBuffer getPrimaryBuffer();

    public int getPrimaryId() {
        return this.primaryId;
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

    public /* bridge */ /* synthetic */ Object getTypedDataOr(Class cls, Object obj) {
        return super.getTypedDataOr(cls, obj);
    }

    public void release() {
        release(new q(this, 2));
    }

    public /* bridge */ /* synthetic */ List removeAllOnReleaseListeners() {
        return super.removeAllOnReleaseListeners();
    }

    public boolean removeBuffer(MediaBuffer mediaBuffer) {
        return this.buffers.remove(mediaBuffer);
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

    public MediaBuffer setMetaDataBuffer(List<MediaBuffer> list) {
        return addBuffer(list);
    }

    public /* bridge */ /* synthetic */ MediaBuffer setScanline(int i2) {
        return super.setScanline(i2);
    }

    public /* bridge */ /* synthetic */ MediaBuffer setStride(int i2) {
        return super.setStride(i2);
    }

    public long size() {
        return (long) ((Integer) Optional.ofNullable(this.buffers).map(new C0923a(24)).orElse(0)).intValue();
    }

    public Stream<MediaBuffer> stream() {
        return this.buffers.stream();
    }

    public String toString() {
        return contentToString(this, new C0925c(3, this));
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelableList(this.buffers, i2);
    }

    public MediaBufferGroup addBuffer(List<MediaBuffer> list) {
        this.buffers.addAll(list);
        return this;
    }

    public MediaBuffer dup() {
        MediaBufferGroup mediaBufferGroup = (MediaBufferGroup) super.dup();
        mediaBufferGroup.buffers = this.buffers;
        return mediaBufferGroup;
    }

    public MediaBuffer dupAll() {
        throw new UnsupportedOperationException("not implemented yet");
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

    public MediaBufferGroup(Parcel parcel) {
        super(parcel);
        ArrayList arrayList = new ArrayList();
        this.buffers = arrayList;
        parcel.readParcelableList(arrayList, GenericMediaBuffer.class.getClassLoader());
    }
}
