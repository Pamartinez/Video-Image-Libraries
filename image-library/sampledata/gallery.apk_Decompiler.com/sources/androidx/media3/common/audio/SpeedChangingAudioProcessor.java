package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.SpeedProviderUtil;
import androidx.media3.common.util.Util;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SpeedChangingAudioProcessor implements AudioProcessor {
    private float currentSpeed;
    private boolean endOfStreamQueuedToSonic;
    private long framesRead;
    private AudioProcessor.AudioFormat inputAudioFormat;
    private boolean inputEnded;
    private final Object lock;
    private final LongArrayQueue pendingCallbackInputTimesUs = new LongArrayQueue();
    private final Queue<Object> pendingCallbacks = new ArrayDeque();
    private AudioProcessor.AudioFormat pendingInputAudioFormat;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;
    private final SynchronizedSonicAudioProcessor sonicAudioProcessor;
    private final SpeedProvider speedProvider;

    public SpeedChangingAudioProcessor(SpeedProvider speedProvider2) {
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.speedProvider = speedProvider2;
        Object obj = new Object();
        this.lock = obj;
        this.sonicAudioProcessor = new SynchronizedSonicAudioProcessor(obj, true);
        resetInternalState(true);
    }

    private static long getDurationUsAfterProcessorApplied(SpeedProvider speedProvider2, int i2, long j2) {
        return Util.sampleCountToDurationUs(getSampleCountAfterProcessorApplied(speedProvider2, i2, Util.scaleLargeValue(j2, (long) i2, 1000000, RoundingMode.HALF_EVEN)), i2);
    }

    public static long getSampleCountAfterProcessorApplied(SpeedProvider speedProvider2, int i2, long j2) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        if (speedProvider2 != null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (i2 > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3);
        long j3 = 0;
        if (j2 >= 0) {
            z7 = true;
        }
        Assertions.checkArgument(z7);
        long j8 = 0;
        while (j3 < j2) {
            long nextSpeedChangeSamplePosition = SpeedProviderUtil.getNextSpeedChangeSamplePosition(speedProvider2, j3, i2);
            if (nextSpeedChangeSamplePosition == -1 || nextSpeedChangeSamplePosition > j2) {
                nextSpeedChangeSamplePosition = j2;
            }
            float sampleAlignedSpeed = SpeedProviderUtil.getSampleAlignedSpeed(speedProvider2, j3, i2);
            j8 += Sonic.getExpectedFrameCountAfterProcessorApplied(i2, i2, sampleAlignedSpeed, sampleAlignedSpeed, nextSpeedChangeSamplePosition - j3);
            j3 = nextSpeedChangeSamplePosition;
        }
        return j8;
    }

    private void processPendingCallbacks() {
        synchronized (this.lock) {
            try {
                if (this.inputAudioFormat.sampleRate != -1) {
                    if (!this.pendingCallbacks.isEmpty()) {
                        long remove = this.pendingCallbackInputTimesUs.remove();
                        if (this.pendingCallbacks.remove() == null) {
                            getDurationUsAfterProcessorApplied(this.speedProvider, this.inputAudioFormat.sampleRate, remove);
                            throw null;
                        }
                        throw new ClassCastException();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void resetInternalState(boolean z) {
        if (z) {
            this.currentSpeed = 1.0f;
        }
        this.framesRead = 0;
        this.endOfStreamQueuedToSonic = false;
    }

    private void updateSpeed(float f) {
        if (f != this.currentSpeed) {
            this.currentSpeed = f;
            this.sonicAudioProcessor.setSpeed(f);
            this.sonicAudioProcessor.setPitch(f);
            this.sonicAudioProcessor.flush();
            this.endOfStreamQueuedToSonic = false;
        }
    }

    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) {
        this.pendingInputAudioFormat = audioFormat;
        AudioProcessor.AudioFormat configure = this.sonicAudioProcessor.configure(audioFormat);
        this.pendingOutputAudioFormat = configure;
        return configure;
    }

    public void flush() {
        this.inputEnded = false;
        resetInternalState(false);
        synchronized (this.lock) {
            this.inputAudioFormat = this.pendingInputAudioFormat;
            this.sonicAudioProcessor.flush();
            processPendingCallbacks();
        }
    }

    public long getDurationAfterProcessorApplied(long j2) {
        return SpeedProviderUtil.getDurationAfterSpeedProviderApplied(this.speedProvider, j2);
    }

    public ByteBuffer getOutput() {
        return this.sonicAudioProcessor.getOutput();
    }

    public boolean isActive() {
        return !this.pendingOutputAudioFormat.equals(AudioProcessor.AudioFormat.NOT_SET);
    }

    public boolean isEnded() {
        if (!this.inputEnded || !this.sonicAudioProcessor.isEnded()) {
            return false;
        }
        return true;
    }

    public void queueEndOfStream() {
        this.inputEnded = true;
        if (!this.endOfStreamQueuedToSonic) {
            this.sonicAudioProcessor.queueEndOfStream();
            this.endOfStreamQueuedToSonic = true;
        }
    }

    public void queueInput(ByteBuffer byteBuffer) {
        AudioProcessor.AudioFormat audioFormat;
        int i2;
        synchronized (this.lock) {
            audioFormat = this.inputAudioFormat;
        }
        float sampleAlignedSpeed = SpeedProviderUtil.getSampleAlignedSpeed(this.speedProvider, this.framesRead, audioFormat.sampleRate);
        long nextSpeedChangeSamplePosition = SpeedProviderUtil.getNextSpeedChangeSamplePosition(this.speedProvider, this.framesRead, audioFormat.sampleRate);
        updateSpeed(sampleAlignedSpeed);
        int limit = byteBuffer.limit();
        if (nextSpeedChangeSamplePosition != -1) {
            i2 = (int) ((nextSpeedChangeSamplePosition - this.framesRead) * ((long) audioFormat.bytesPerFrame));
            byteBuffer.limit(Math.min(limit, byteBuffer.position() + i2));
        } else {
            i2 = -1;
        }
        long position = (long) byteBuffer.position();
        this.sonicAudioProcessor.queueInput(byteBuffer);
        boolean z = true;
        if (i2 != -1 && ((long) byteBuffer.position()) - position == ((long) i2)) {
            this.sonicAudioProcessor.queueEndOfStream();
            this.endOfStreamQueuedToSonic = true;
        }
        long position2 = ((long) byteBuffer.position()) - position;
        if (position2 % ((long) audioFormat.bytesPerFrame) != 0) {
            z = false;
        }
        Assertions.checkState(z, "A frame was not queued completely.");
        this.framesRead = (position2 / ((long) audioFormat.bytesPerFrame)) + this.framesRead;
        byteBuffer.limit(limit);
    }

    public void reset() {
        flush();
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        synchronized (this.lock) {
            this.inputAudioFormat = audioFormat;
            this.pendingCallbackInputTimesUs.clear();
            this.pendingCallbacks.clear();
        }
        resetInternalState(true);
        this.sonicAudioProcessor.reset();
    }
}
