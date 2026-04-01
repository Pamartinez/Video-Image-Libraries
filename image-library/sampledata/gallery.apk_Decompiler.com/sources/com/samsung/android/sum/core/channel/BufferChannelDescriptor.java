package com.samsung.android.sum.core.channel;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.SharedBufferManager;
import com.samsung.android.sum.core.format.MediaFormat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BufferChannelDescriptor implements Parcelable {
    public static final Parcelable.Creator<BufferChannelDescriptor> CREATOR = new Parcelable.Creator<BufferChannelDescriptor>() {
        public BufferChannelDescriptor createFromParcel(Parcel parcel) {
            return new BufferChannelDescriptor(parcel);
        }

        public BufferChannelDescriptor[] newArray(int i2) {
            return new BufferChannelDescriptor[i2];
        }
    };
    private int capacity;
    private Consumer<MediaBuffer> metaDataHandler;
    private Surface surface;
    private MediaFormat surfaceFormat;
    int type;
    private long usage;

    public BufferChannelDescriptor() {
        this(0, Integer.MAX_VALUE);
    }

    public int describeContents() {
        return 0;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public Consumer<MediaBuffer> getMetaDataHandler() {
        return this.metaDataHandler;
    }

    public Surface getSurface() {
        return this.surface;
    }

    public MediaFormat getSurfaceFormat() {
        return this.surfaceFormat;
    }

    public int getType() {
        return this.type;
    }

    public long getUsage() {
        return this.usage;
    }

    public BufferChannelDescriptor setCapacity(int i2) {
        this.capacity = i2;
        return this;
    }

    public BufferChannelDescriptor setMetaDataHandler(Consumer<MediaBuffer> consumer) {
        this.metaDataHandler = consumer;
        return this;
    }

    public BufferChannelDescriptor setSurface(Surface surface2) {
        this.surface = surface2;
        return this;
    }

    public BufferChannelDescriptor setUsage(long j2) {
        this.usage = j2;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.type);
        parcel.writeInt(this.capacity);
        parcel.writeLong(this.usage);
    }

    public BufferChannelDescriptor(int i2) {
        this(i2, Integer.MAX_VALUE);
    }

    public BufferChannelDescriptor setSurface(Surface surface2, int i2) {
        this.surface = surface2;
        this.surfaceFormat = MediaFormat.newBuilder().setColorFormat(SharedBufferManager.colorFormatFromHalFormat(i2)).build();
        return this;
    }

    public BufferChannelDescriptor(int i2, int i7) {
        this.type = i2;
        this.capacity = i7;
    }

    public BufferChannelDescriptor(Parcel parcel) {
        this.type = parcel.readInt();
        this.capacity = parcel.readInt();
        this.usage = parcel.readLong();
    }

    public BufferChannelDescriptor setSurface(Surface surface2, int i2, int i7) {
        this.surface = surface2;
        this.surfaceFormat = MediaFormat.newBuilder().setColorFormat(SharedBufferManager.colorFormatFromHalFormat(i2)).setAttribute("rotation", Integer.valueOf(i7)).build();
        return this;
    }

    public BufferChannelDescriptor setSurface(Surface surface2, MediaFormat mediaFormat) {
        this.surface = surface2;
        this.surfaceFormat = mediaFormat;
        return this;
    }
}
