package com.samsung.android.sum.core.buffer;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.types.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollectBufferGroup extends MediaBufferGroup {
    public static final Parcelable.Creator<CollectBufferGroup> CREATOR = new Parcelable.Creator<CollectBufferGroup>() {
        public CollectBufferGroup createFromParcel(Parcel parcel) {
            return new CollectBufferGroup(parcel);
        }

        public CollectBufferGroup[] newArray(int i2) {
            return new CollectBufferGroup[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) CollectBufferGroup.class);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CollectBufferGroup(int i2, List<MediaBuffer> list) {
        super(list.isEmpty() ? MediaFormat.newBuilder(MediaType.NONE).build() : list.get(i2).getFormat(), list);
        this.primaryId = i2;
        this.extra.putAll(getPrimaryBuffer().getExtra());
    }

    public <T> MediaBuffer convertTo(Class<T> cls) {
        MediaBuffer copyTo = copyTo(cls);
        release();
        return copyTo;
    }

    public <T> MediaBuffer copyTo(Class<T> cls) {
        return MediaBuffer.newGroupAlloc().setMediaFormat(this.format).setBuffers(this.primaryId, (List<MediaBuffer>) (List) this.buffers.stream().map(new p(cls, 0)).collect(Collectors.toList())).allocate();
    }

    public <T> T getData() {
        return this.buffers.get(this.primaryId).getData();
    }

    public Class<?> getDataClass() {
        if (this.buffers.isEmpty()) {
            return null;
        }
        return this.buffers.get(this.primaryId).getDataClass();
    }

    public MediaBuffer getPrimaryBuffer() {
        return this.buffers.get(this.primaryId);
    }

    public <T> T getTypedData(Class<T> cls) {
        return this.buffers.get(this.primaryId).getTypedData(cls);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.primaryId);
    }

    public CollectBufferGroup(Parcel parcel) {
        super(parcel);
        this.primaryId = parcel.readInt();
    }
}
