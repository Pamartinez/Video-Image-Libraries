package com.samsung.android.sum.core.buffer;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.DebugUtils;
import com.samsung.android.sum.core.Def;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeriveBufferGroup extends MediaBufferGroup {
    public static final Parcelable.Creator<DeriveBufferGroup> CREATOR = new Parcelable.Creator<DeriveBufferGroup>() {
        public DeriveBufferGroup createFromParcel(Parcel parcel) {
            return new DeriveBufferGroup(parcel);
        }

        public DeriveBufferGroup[] newArray(int i2) {
            return new DeriveBufferGroup[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) DeriveBufferGroup.class);
    private MediaBuffer primaryBuffer;

    public DeriveBufferGroup(MediaBuffer mediaBuffer, List<MediaBuffer> list) {
        super(mediaBuffer.getFormat(), list);
        this.primaryBuffer = mediaBuffer;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$1() {
        this.primaryBuffer = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$toString$2() {
        if (!DebugUtils.TRACE_BUFFER) {
            return "[trace off]";
        }
        return Def.contentToStringln("    ", "primary-buffer=" + this.primaryBuffer.toString());
    }

    public <T> MediaBuffer convertTo(Class<T> cls) {
        MediaBuffer copyTo = copyTo(cls);
        release();
        return copyTo;
    }

    public <T> MediaBuffer copyTo(Class<T> cls) {
        return MediaBuffer.newGroupAlloc().setMediaFormat(this.format).setBuffers(this.primaryBuffer.copyTo(cls), (List<MediaBuffer>) (List) this.buffers.stream().map(new p(cls, 1)).collect(Collectors.toList())).allocate();
    }

    public <T> T getData() {
        return this.primaryBuffer.getData();
    }

    public Class<?> getDataClass() {
        return this.primaryBuffer.getDataClass();
    }

    public MediaBuffer getPrimaryBuffer() {
        return this.primaryBuffer;
    }

    public <T> T getTypedData(Class<T> cls) {
        return this.primaryBuffer.getTypedData(cls);
    }

    public void release() {
        release(new q(this, 0));
    }

    public String toString() {
        return contentToString(this, new C0925c(1, this));
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.primaryBuffer, i2);
    }

    public MediaBuffer dup() {
        DeriveBufferGroup deriveBufferGroup = (DeriveBufferGroup) super.dup();
        deriveBufferGroup.primaryBuffer = (MediaBuffer) this.primaryBuffer.dup();
        return deriveBufferGroup;
    }

    public DeriveBufferGroup(Parcel parcel) {
        super(parcel);
        this.primaryBuffer = (MediaBuffer) parcel.readParcelable(GenericMediaBuffer.class.getClassLoader());
    }
}
