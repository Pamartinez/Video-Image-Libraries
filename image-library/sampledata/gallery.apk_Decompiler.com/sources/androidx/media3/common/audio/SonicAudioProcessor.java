package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SonicAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer;
    private AudioProcessor.AudioFormat inputAudioFormat;
    private long inputBytes;
    private boolean inputEnded;
    private AudioProcessor.AudioFormat outputAudioFormat;
    private ByteBuffer outputBuffer;
    private long outputBytes;
    private AudioProcessor.AudioFormat pendingInputAudioFormat;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;
    private int pendingOutputSampleRate;
    private boolean pendingSonicRecreation;
    private float pitch;
    private ShortBuffer shortBuffer;
    private final boolean shouldBeActiveWithDefaultParameters;
    private Sonic sonic;
    private float speed;

    public SonicAudioProcessor() {
        this(false);
    }

    private boolean areParametersSetToDefaultValues() {
        if (Math.abs(this.speed - 1.0f) >= 1.0E-4f || Math.abs(this.pitch - 1.0f) >= 1.0E-4f || this.pendingOutputAudioFormat.sampleRate != this.pendingInputAudioFormat.sampleRate) {
            return false;
        }
        return true;
    }

    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.encoding == 2) {
            int i2 = this.pendingOutputSampleRate;
            if (i2 == -1) {
                i2 = audioFormat.sampleRate;
            }
            this.pendingInputAudioFormat = audioFormat;
            AudioProcessor.AudioFormat audioFormat2 = new AudioProcessor.AudioFormat(i2, audioFormat.channelCount, 2);
            this.pendingOutputAudioFormat = audioFormat2;
            this.pendingSonicRecreation = true;
            return audioFormat2;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    public void flush() {
        if (isActive()) {
            AudioProcessor.AudioFormat audioFormat = this.pendingInputAudioFormat;
            this.inputAudioFormat = audioFormat;
            AudioProcessor.AudioFormat audioFormat2 = this.pendingOutputAudioFormat;
            this.outputAudioFormat = audioFormat2;
            if (this.pendingSonicRecreation) {
                this.sonic = new Sonic(audioFormat.sampleRate, audioFormat.channelCount, this.speed, this.pitch, audioFormat2.sampleRate);
            } else {
                Sonic sonic2 = this.sonic;
                if (sonic2 != null) {
                    sonic2.flush();
                }
            }
        }
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }

    public long getDurationAfterProcessorApplied(long j2) {
        return getPlayoutDuration(j2);
    }

    public ByteBuffer getOutput() {
        int outputSize;
        Sonic sonic2 = this.sonic;
        if (sonic2 != null && (outputSize = sonic2.getOutputSize()) > 0) {
            if (this.buffer.capacity() < outputSize) {
                ByteBuffer order = ByteBuffer.allocateDirect(outputSize).order(ByteOrder.nativeOrder());
                this.buffer = order;
                this.shortBuffer = order.asShortBuffer();
            } else {
                this.buffer.clear();
                this.shortBuffer.clear();
            }
            sonic2.getOutput(this.shortBuffer);
            this.outputBytes += (long) outputSize;
            this.buffer.limit(outputSize);
            this.outputBuffer = this.buffer;
        }
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        return byteBuffer;
    }

    public long getPlayoutDuration(long j2) {
        if (this.outputBytes < ErrorCodeConvertor.ERROR_NOT_ALLOWED_CALLER) {
            return (long) (((double) j2) / ((double) this.speed));
        }
        long pendingInputBytes = this.inputBytes - ((long) ((Sonic) Assertions.checkNotNull(this.sonic)).getPendingInputBytes());
        int i2 = this.outputAudioFormat.sampleRate;
        int i7 = this.inputAudioFormat.sampleRate;
        if (i2 == i7) {
            return Util.scaleLargeTimestamp(j2, this.outputBytes, pendingInputBytes);
        }
        return Util.scaleLargeTimestamp(j2, this.outputBytes * ((long) i7), pendingInputBytes * ((long) i2));
    }

    public boolean isActive() {
        if (this.pendingOutputAudioFormat.sampleRate == -1) {
            return false;
        }
        if (this.shouldBeActiveWithDefaultParameters || !areParametersSetToDefaultValues()) {
            return true;
        }
        return false;
    }

    public boolean isEnded() {
        if (!this.inputEnded) {
            return false;
        }
        Sonic sonic2 = this.sonic;
        if (sonic2 == null || sonic2.getOutputSize() == 0) {
            return true;
        }
        return false;
    }

    public void queueEndOfStream() {
        Sonic sonic2 = this.sonic;
        if (sonic2 != null) {
            sonic2.queueEndOfStream();
        }
        this.inputEnded = true;
    }

    public void queueInput(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.inputBytes += (long) remaining;
            ((Sonic) Assertions.checkNotNull(this.sonic)).queueInput(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
    }

    public void reset() {
        this.speed = 1.0f;
        this.pitch = 1.0f;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.outputAudioFormat = audioFormat;
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.shortBuffer = byteBuffer.asShortBuffer();
        this.outputBuffer = byteBuffer;
        this.pendingOutputSampleRate = -1;
        this.pendingSonicRecreation = false;
        this.sonic = null;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }

    public void setOutputSampleRateHz(int i2) {
        boolean z;
        if (i2 == -1 || i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        this.pendingOutputSampleRate = i2;
    }

    public void setPitch(float f) {
        boolean z;
        if (f > 0.0f) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (this.pitch != f) {
            this.pitch = f;
            this.pendingSonicRecreation = true;
        }
    }

    public void setSpeed(float f) {
        boolean z;
        if (f > 0.0f) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (this.speed != f) {
            this.speed = f;
            this.pendingSonicRecreation = true;
        }
    }

    public SonicAudioProcessor(boolean z) {
        this.speed = 1.0f;
        this.pitch = 1.0f;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.outputAudioFormat = audioFormat;
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.shortBuffer = byteBuffer.asShortBuffer();
        this.outputBuffer = byteBuffer;
        this.pendingOutputSampleRate = -1;
        this.shouldBeActiveWithDefaultParameters = z;
    }
}
