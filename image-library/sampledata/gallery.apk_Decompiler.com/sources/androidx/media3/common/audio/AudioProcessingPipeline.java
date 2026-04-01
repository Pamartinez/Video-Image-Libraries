package androidx.media3.common.audio;

import F2.U;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AudioProcessingPipeline {
    private final List<AudioProcessor> activeAudioProcessors = new ArrayList();
    private final U audioProcessors;
    private boolean inputEnded;
    private AudioProcessor.AudioFormat outputAudioFormat;
    private ByteBuffer[] outputBuffers = new ByteBuffer[0];
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;

    public AudioProcessingPipeline(U u) {
        this.audioProcessors = u;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.outputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputEnded = false;
    }

    private int getFinalOutputBufferIndex() {
        return this.outputBuffers.length - 1;
    }

    private void processData(ByteBuffer byteBuffer) {
        boolean z;
        ByteBuffer byteBuffer2;
        boolean z3;
        for (boolean z7 = true; z7; z7 = z) {
            z = false;
            for (int i2 = 0; i2 <= getFinalOutputBufferIndex(); i2++) {
                if (!this.outputBuffers[i2].hasRemaining()) {
                    AudioProcessor audioProcessor = this.activeAudioProcessors.get(i2);
                    if (!audioProcessor.isEnded()) {
                        if (i2 > 0) {
                            byteBuffer2 = this.outputBuffers[i2 - 1];
                        } else if (byteBuffer.hasRemaining()) {
                            byteBuffer2 = byteBuffer;
                        } else {
                            byteBuffer2 = AudioProcessor.EMPTY_BUFFER;
                        }
                        audioProcessor.queueInput(byteBuffer2);
                        this.outputBuffers[i2] = audioProcessor.getOutput();
                        if (((long) byteBuffer2.remaining()) - ((long) byteBuffer2.remaining()) > 0 || this.outputBuffers[i2].hasRemaining()) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z |= z3;
                    } else if (!this.outputBuffers[i2].hasRemaining() && i2 < getFinalOutputBufferIndex()) {
                        this.activeAudioProcessors.get(i2 + 1).queueEndOfStream();
                    }
                }
            }
        }
    }

    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) {
        if (!audioFormat.equals(AudioProcessor.AudioFormat.NOT_SET)) {
            for (int i2 = 0; i2 < this.audioProcessors.size(); i2++) {
                AudioProcessor audioProcessor = (AudioProcessor) this.audioProcessors.get(i2);
                AudioProcessor.AudioFormat configure = audioProcessor.configure(audioFormat);
                if (audioProcessor.isActive()) {
                    Assertions.checkState(!configure.equals(AudioProcessor.AudioFormat.NOT_SET));
                    audioFormat = configure;
                }
            }
            this.pendingOutputAudioFormat = audioFormat;
            return audioFormat;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioProcessingPipeline)) {
            return false;
        }
        AudioProcessingPipeline audioProcessingPipeline = (AudioProcessingPipeline) obj;
        if (this.audioProcessors.size() != audioProcessingPipeline.audioProcessors.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.audioProcessors.size(); i2++) {
            if (this.audioProcessors.get(i2) != audioProcessingPipeline.audioProcessors.get(i2)) {
                return false;
            }
        }
        return true;
    }

    public void flush() {
        this.activeAudioProcessors.clear();
        this.outputAudioFormat = this.pendingOutputAudioFormat;
        this.inputEnded = false;
        for (int i2 = 0; i2 < this.audioProcessors.size(); i2++) {
            AudioProcessor audioProcessor = (AudioProcessor) this.audioProcessors.get(i2);
            audioProcessor.flush();
            if (audioProcessor.isActive()) {
                this.activeAudioProcessors.add(audioProcessor);
            }
        }
        this.outputBuffers = new ByteBuffer[this.activeAudioProcessors.size()];
        for (int i7 = 0; i7 <= getFinalOutputBufferIndex(); i7++) {
            this.outputBuffers[i7] = this.activeAudioProcessors.get(i7).getOutput();
        }
    }

    public ByteBuffer getOutput() {
        if (!isOperational()) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        ByteBuffer byteBuffer = this.outputBuffers[getFinalOutputBufferIndex()];
        if (byteBuffer.hasRemaining()) {
            return byteBuffer;
        }
        processData(AudioProcessor.EMPTY_BUFFER);
        return this.outputBuffers[getFinalOutputBufferIndex()];
    }

    public AudioProcessor.AudioFormat getOutputAudioFormat() {
        return this.outputAudioFormat;
    }

    public int hashCode() {
        return this.audioProcessors.hashCode();
    }

    public boolean isEnded() {
        if (!this.inputEnded || !this.activeAudioProcessors.get(getFinalOutputBufferIndex()).isEnded() || this.outputBuffers[getFinalOutputBufferIndex()].hasRemaining()) {
            return false;
        }
        return true;
    }

    public boolean isOperational() {
        return !this.activeAudioProcessors.isEmpty();
    }

    public void queueEndOfStream() {
        if (isOperational() && !this.inputEnded) {
            this.inputEnded = true;
            this.activeAudioProcessors.get(0).queueEndOfStream();
        }
    }

    public void queueInput(ByteBuffer byteBuffer) {
        if (isOperational() && !this.inputEnded) {
            processData(byteBuffer);
        }
    }

    public void reset() {
        for (int i2 = 0; i2 < this.audioProcessors.size(); i2++) {
            AudioProcessor audioProcessor = (AudioProcessor) this.audioProcessors.get(i2);
            audioProcessor.flush();
            audioProcessor.reset();
        }
        this.outputBuffers = new ByteBuffer[0];
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.outputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputEnded = false;
    }
}
