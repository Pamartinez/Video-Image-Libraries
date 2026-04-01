package com.samsung.android.sum.core.channel;

import android.view.Surface;
import ba.C0582a;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StapleSurfaceWRChannel implements SurfaceReadChannel, SurfaceWriteChannel {
    private final SurfaceReadChannel readChannel;
    private final SurfaceWriteChannel writeChannel;

    public StapleSurfaceWRChannel(BufferChannel bufferChannel) {
        this.readChannel = (SurfaceReadChannel) BufferChannel.of(5, bufferChannel);
        this.writeChannel = (SurfaceWriteChannel) BufferChannel.of(6, bufferChannel);
    }

    public void cancel() {
        this.writeChannel.cancel();
        this.readChannel.cancel();
    }

    public void close() {
        this.writeChannel.close();
        this.readChannel.close();
    }

    public void configure(SurfaceChannelConfig surfaceChannelConfig) {
        this.readChannel.configure(surfaceChannelConfig);
        SurfaceWriteChannel surfaceWriteChannel = this.writeChannel;
        Surface surface = this.readChannel.getSurface();
        SurfaceReadChannel surfaceReadChannel = this.readChannel;
        Objects.requireNonNull(surfaceReadChannel);
        surfaceWriteChannel.configure(surface, new C0582a(28, surfaceReadChannel));
    }

    public MediaBuffer getBuffer() {
        return this.writeChannel.getBuffer();
    }

    public int getFormat() {
        return ((Integer) Optional.ofNullable(this.readChannel).map(new b(19)).orElse(0)).intValue();
    }

    public int getHeight() {
        return ((Integer) Optional.ofNullable(this.readChannel).map(new b(18)).orElse(-1)).intValue();
    }

    public Surface getSurface() {
        return this.readChannel.getSurface();
    }

    public long getUsage() {
        return ((Long) Optional.ofNullable(this.readChannel).map(new b(16)).orElse(0L)).longValue();
    }

    public int getWidth() {
        return ((Integer) Optional.ofNullable(this.readChannel).map(new b(17)).orElse(-1)).intValue();
    }

    public boolean isClosedForReceive() {
        return this.readChannel.isClosedForReceive();
    }

    public boolean isClosedForSend() {
        return this.writeChannel.isClosedForSend();
    }

    public boolean isRequireToConfigure(int i2, int i7, int i8, long j2) {
        return this.readChannel.isRequireToConfigure() || this.writeChannel.isRequireToConfigure(i2, i7, i8, j2);
    }

    public void reset() {
        this.writeChannel.reset();
        this.readChannel.reset();
    }

    public boolean isRequireToConfigure() {
        return this.readChannel.isRequireToConfigure();
    }

    public MediaBuffer receive() {
        return (MediaBuffer) this.readChannel.receive();
    }

    public void send(MediaBuffer mediaBuffer) {
        this.writeChannel.send(mediaBuffer);
    }
}
