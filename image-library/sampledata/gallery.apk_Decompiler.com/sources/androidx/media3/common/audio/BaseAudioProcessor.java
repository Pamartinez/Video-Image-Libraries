package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer;
    protected AudioProcessor.AudioFormat inputAudioFormat;
    private boolean inputEnded;
    protected AudioProcessor.AudioFormat outputAudioFormat;
    private ByteBuffer outputBuffer;
    private AudioProcessor.AudioFormat pendingInputAudioFormat;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;

    public BaseAudioProcessor() {
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.outputBuffer = byteBuffer;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.outputAudioFormat = audioFormat;
    }

    public final AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) {
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = onConfigure(audioFormat);
        if (isActive()) {
            return this.pendingOutputAudioFormat;
        }
        return AudioProcessor.AudioFormat.NOT_SET;
    }

    public final void flush() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
        this.inputAudioFormat = this.pendingInputAudioFormat;
        this.outputAudioFormat = this.pendingOutputAudioFormat;
        onFlush();
    }

    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        return byteBuffer;
    }

    public boolean isActive() {
        if (this.pendingOutputAudioFormat != AudioProcessor.AudioFormat.NOT_SET) {
            return true;
        }
        return false;
    }

    public boolean isEnded() {
        if (!this.inputEnded || this.outputBuffer != AudioProcessor.EMPTY_BUFFER) {
            return false;
        }
        return true;
    }

    public abstract AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat);

    public final void queueEndOfStream() {
        this.inputEnded = true;
        onQueueEndOfStream();
    }

    public final ByteBuffer replaceOutputBuffer(int i2) {
        if (this.buffer.capacity() < i2) {
            this.buffer = ByteBuffer.allocateDirect(i2).order(ByteOrder.nativeOrder());
        } else {
            this.buffer.clear();
        }
        ByteBuffer byteBuffer = this.buffer;
        this.outputBuffer = byteBuffer;
        return byteBuffer;
    }

    public final void reset() {
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.outputBuffer = byteBuffer;
        this.inputEnded = false;
        this.buffer = byteBuffer;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.outputAudioFormat = audioFormat;
        onReset();
    }

    public void onFlush() {
    }

    public void onQueueEndOfStream() {
    }

    public void onReset() {
    }
}
