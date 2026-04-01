package androidx.media3.transformer;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SilentAudioGenerator {
    public final AudioProcessor.AudioFormat audioFormat;
    private final ByteBuffer internalBuffer;
    private final AtomicLong remainingBytesToOutput = new AtomicLong();

    public SilentAudioGenerator(AudioProcessor.AudioFormat audioFormat2) {
        this.audioFormat = audioFormat2;
        ByteBuffer order = ByteBuffer.allocateDirect(audioFormat2.bytesPerFrame * 1024).order(ByteOrder.nativeOrder());
        this.internalBuffer = order;
        order.flip();
    }

    public void addSilence(long j2) {
        this.remainingBytesToOutput.addAndGet(((long) this.audioFormat.bytesPerFrame) * Util.durationUsToSampleCount(j2, this.audioFormat.sampleRate));
    }

    public ByteBuffer getBuffer() {
        long j2 = this.remainingBytesToOutput.get();
        if (!this.internalBuffer.hasRemaining()) {
            this.internalBuffer.clear();
            if (j2 < ((long) this.internalBuffer.capacity())) {
                this.internalBuffer.limit((int) j2);
            }
            this.remainingBytesToOutput.addAndGet((long) (-this.internalBuffer.remaining()));
        }
        return this.internalBuffer;
    }

    public boolean hasRemaining() {
        if (this.internalBuffer.hasRemaining() || this.remainingBytesToOutput.get() > 0) {
            return true;
        }
        return false;
    }
}
