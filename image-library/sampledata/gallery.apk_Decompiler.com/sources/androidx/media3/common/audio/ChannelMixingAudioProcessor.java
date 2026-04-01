package androidx.media3.common.audio;

import android.util.SparseArray;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ChannelMixingAudioProcessor extends BaseAudioProcessor {
    private final SparseArray<ChannelMixingMatrix> matrixByInputChannelCount = new SparseArray<>();

    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.encoding == 2) {
            ChannelMixingMatrix channelMixingMatrix = this.matrixByInputChannelCount.get(audioFormat.channelCount);
            if (channelMixingMatrix == null) {
                throw new AudioProcessor.UnhandledAudioFormatException("No mixing matrix for input channel count", audioFormat);
            } else if (channelMixingMatrix.isIdentity()) {
                return AudioProcessor.AudioFormat.NOT_SET;
            } else {
                return new AudioProcessor.AudioFormat(audioFormat.sampleRate, channelMixingMatrix.getOutputChannelCount(), 2);
            }
        } else {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        }
    }

    public void putChannelMixingMatrix(ChannelMixingMatrix channelMixingMatrix) {
        this.matrixByInputChannelCount.put(channelMixingMatrix.getInputChannelCount(), channelMixingMatrix);
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining() / this.inputAudioFormat.bytesPerFrame;
        ByteBuffer replaceOutputBuffer = replaceOutputBuffer(this.outputAudioFormat.bytesPerFrame * remaining);
        ByteBuffer byteBuffer2 = byteBuffer;
        AudioMixingUtil.mix(byteBuffer2, this.inputAudioFormat, replaceOutputBuffer, this.outputAudioFormat, (ChannelMixingMatrix) Assertions.checkStateNotNull(this.matrixByInputChannelCount.get(this.inputAudioFormat.channelCount)), remaining, false, true);
        replaceOutputBuffer.flip();
    }
}
