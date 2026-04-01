package com.samsung.android.sum.core.channel;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface BufferChannel extends Channel<MediaBuffer> {
    public static final int DEFAULT = 0;
    @Deprecated
    public static final int SUPPLY = 1;
    public static final int SURFACE_DETACH_READ = 8;
    public static final int SURFACE_READ = 5;
    @Deprecated
    public static final int SURFACE_RECEIVE = 2;
    public static final int SURFACE_RW = 7;
    @Deprecated
    public static final int SURFACE_SEND = 3;
    @Deprecated
    public static final int SURFACE_TRANSIT = 4;
    public static final int SURFACE_WR = 9;
    public static final int SURFACE_WRITE = 6;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface Type {
    }

    static BufferChannel of(int i2) {
        return of(i2, new BlockingBufferChannel(Integer.MAX_VALUE));
    }

    int getCapacity() {
        throw new UnsupportedOperationException();
    }

    void setCapacity(int i2) {
        throw new UnsupportedOperationException();
    }

    static BufferChannel of(int i2, BufferChannel bufferChannel) {
        switch (i2) {
            case 2:
            case 5:
                return new StapleSurfaceReadChannel(bufferChannel);
            case 3:
            case 6:
                return new StapleSurfaceWriteChannel(bufferChannel);
            case 4:
            case 7:
                return new StapleSurfaceRWChannel(bufferChannel);
            case 8:
                return new DetachableSurfaceReadChannel(bufferChannel);
            case 9:
                return new StapleSurfaceWRChannel(bufferChannel);
            default:
                return bufferChannel;
        }
    }
}
