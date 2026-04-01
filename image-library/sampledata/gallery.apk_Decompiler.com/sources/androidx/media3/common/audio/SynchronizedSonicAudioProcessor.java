package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SynchronizedSonicAudioProcessor implements AudioProcessor {
    private final Object lock;
    private final SonicAudioProcessor sonicAudioProcessor;

    public SynchronizedSonicAudioProcessor(Object obj, boolean z) {
        this.lock = obj;
        this.sonicAudioProcessor = new SonicAudioProcessor(z);
    }

    public final AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) {
        AudioProcessor.AudioFormat configure;
        synchronized (this.lock) {
            configure = this.sonicAudioProcessor.configure(audioFormat);
        }
        return configure;
    }

    public final void flush() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.flush();
        }
    }

    public long getDurationAfterProcessorApplied(long j2) {
        return getPlayoutDuration(j2);
    }

    public final ByteBuffer getOutput() {
        ByteBuffer output;
        synchronized (this.lock) {
            output = this.sonicAudioProcessor.getOutput();
        }
        return output;
    }

    public final long getPlayoutDuration(long j2) {
        long playoutDuration;
        synchronized (this.lock) {
            playoutDuration = this.sonicAudioProcessor.getPlayoutDuration(j2);
        }
        return playoutDuration;
    }

    public final boolean isActive() {
        boolean isActive;
        synchronized (this.lock) {
            isActive = this.sonicAudioProcessor.isActive();
        }
        return isActive;
    }

    public final boolean isEnded() {
        boolean isEnded;
        synchronized (this.lock) {
            isEnded = this.sonicAudioProcessor.isEnded();
        }
        return isEnded;
    }

    public final void queueEndOfStream() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.queueEndOfStream();
        }
    }

    public final void queueInput(ByteBuffer byteBuffer) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.queueInput(byteBuffer);
        }
    }

    public final void reset() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.reset();
        }
    }

    public final void setPitch(float f) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setPitch(f);
        }
    }

    public final void setSpeed(float f) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setSpeed(f);
        }
    }
}
