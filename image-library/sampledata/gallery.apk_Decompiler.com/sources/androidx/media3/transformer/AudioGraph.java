package androidx.media3.transformer;

import F2.U;
import androidx.media3.common.Format;
import androidx.media3.common.audio.AudioProcessingPipeline;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.transformer.AudioMixer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AudioGraph {
    private final AudioProcessingPipeline audioProcessingPipeline;
    private int finishedInputs;
    private final List<InputInfo> inputInfos = new ArrayList();
    private boolean isMixerConfigured;
    private boolean isMixerReady;
    private final AudioMixer mixer;
    private AudioProcessor.AudioFormat mixerAudioFormat;
    private ByteBuffer mixerOutput;
    private long pendingStartTimeUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class InputInfo {
        public final AudioGraphInput audioGraphInput;
        public int mixerSourceId = -1;

        public InputInfo(AudioGraphInput audioGraphInput2) {
            this.audioGraphInput = audioGraphInput2;
        }
    }

    public AudioGraph(AudioMixer.Factory factory, U u) {
        this.mixer = factory.create();
        this.mixerAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.mixerOutput = AudioProcessor.EMPTY_BUFFER;
        this.audioProcessingPipeline = new AudioProcessingPipeline(u);
    }

    private boolean ensureMixerReady() {
        if (this.isMixerReady) {
            return true;
        }
        if (!this.isMixerConfigured) {
            try {
                this.mixer.configure(this.mixerAudioFormat, -1, this.pendingStartTimeUs);
                this.isMixerConfigured = true;
            } catch (AudioProcessor.UnhandledAudioFormatException e) {
                throw ExportException.createForAudioProcessing(e, "Error while configuring mixer");
            }
        }
        this.isMixerReady = true;
        for (int i2 = 0; i2 < this.inputInfos.size(); i2++) {
            InputInfo inputInfo = this.inputInfos.get(i2);
            if (inputInfo.mixerSourceId == -1) {
                AudioGraphInput audioGraphInput = inputInfo.audioGraphInput;
                try {
                    audioGraphInput.getOutput();
                    long startTimeUs = audioGraphInput.getStartTimeUs();
                    if (startTimeUs == -9223372036854775807L) {
                        this.isMixerReady = false;
                    } else if (startTimeUs != Long.MIN_VALUE) {
                        inputInfo.mixerSourceId = this.mixer.addSource(audioGraphInput.getOutputAudioFormat(), startTimeUs);
                    }
                } catch (AudioProcessor.UnhandledAudioFormatException e7) {
                    throw ExportException.createForAudioProcessing(e7, "Unhandled format while adding source " + inputInfo.mixerSourceId);
                }
            }
        }
        return this.isMixerReady;
    }

    private void feedMixer() {
        for (int i2 = 0; i2 < this.inputInfos.size(); i2++) {
            feedMixerFromInput(this.inputInfos.get(i2));
        }
    }

    private void feedMixerFromInput(InputInfo inputInfo) {
        int i2 = inputInfo.mixerSourceId;
        if (this.mixer.hasSource(i2)) {
            AudioGraphInput audioGraphInput = inputInfo.audioGraphInput;
            if (audioGraphInput.isEnded()) {
                this.mixer.removeSource(i2);
                inputInfo.mixerSourceId = -1;
                this.finishedInputs++;
                return;
            }
            try {
                this.mixer.queueInput(i2, audioGraphInput.getOutput());
            } catch (AudioProcessor.UnhandledAudioFormatException e) {
                throw ExportException.createForAudioProcessing(e, "AudioGraphInput (sourceId=" + i2 + ") reconfiguration");
            }
        }
    }

    private void feedProcessingPipelineFromMixer() {
        if (isMixerEnded()) {
            this.audioProcessingPipeline.queueEndOfStream();
        } else {
            this.audioProcessingPipeline.queueInput(this.mixerOutput);
        }
    }

    public static boolean isInputAudioFormatValid(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.encoding == -1 || audioFormat.sampleRate == -1 || audioFormat.channelCount == -1) {
            return false;
        }
        return true;
    }

    private boolean isMixerEnded() {
        if (this.mixerOutput.hasRemaining() || this.finishedInputs < this.inputInfos.size() || !this.mixer.isEnded()) {
            return false;
        }
        return true;
    }

    public ByteBuffer getOutput() {
        if (!ensureMixerReady()) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        if (!this.mixer.isEnded()) {
            feedMixer();
        }
        if (!this.mixerOutput.hasRemaining()) {
            this.mixerOutput = this.mixer.getOutput();
        }
        if (!this.audioProcessingPipeline.isOperational()) {
            return this.mixerOutput;
        }
        feedProcessingPipelineFromMixer();
        return this.audioProcessingPipeline.getOutput();
    }

    public AudioProcessor.AudioFormat getOutputAudioFormat() {
        return this.audioProcessingPipeline.getOutputAudioFormat();
    }

    public boolean isEnded() {
        if (this.audioProcessingPipeline.isOperational()) {
            return this.audioProcessingPipeline.isEnded();
        }
        return isMixerEnded();
    }

    public AudioGraphInput registerInput(EditedMediaItem editedMediaItem, Format format) {
        boolean z;
        if (format.pcmEncoding != -1) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        try {
            AudioGraphInput audioGraphInput = new AudioGraphInput(this.mixerAudioFormat, editedMediaItem, format);
            if (Objects.equals(this.mixerAudioFormat, AudioProcessor.AudioFormat.NOT_SET)) {
                AudioProcessor.AudioFormat outputAudioFormat = audioGraphInput.getOutputAudioFormat();
                this.mixerAudioFormat = outputAudioFormat;
                this.audioProcessingPipeline.configure(outputAudioFormat);
                this.audioProcessingPipeline.flush();
            }
            this.inputInfos.add(new InputInfo(audioGraphInput));
            DebugTraceUtil.logEvent("AudioGraph", "RegisterNewInputStream", -9223372036854775807L, "%s", format);
            return audioGraphInput;
        } catch (AudioProcessor.UnhandledAudioFormatException e) {
            AudioProcessor.UnhandledAudioFormatException unhandledAudioFormatException = e;
            throw ExportException.createForAudioProcessing(unhandledAudioFormatException, "Error while registering input " + this.inputInfos.size());
        }
    }

    public void reset() {
        for (int i2 = 0; i2 < this.inputInfos.size(); i2++) {
            this.inputInfos.get(i2).audioGraphInput.release();
        }
        this.inputInfos.clear();
        this.mixer.reset();
        this.audioProcessingPipeline.reset();
        this.finishedInputs = 0;
        this.mixerOutput = AudioProcessor.EMPTY_BUFFER;
        this.mixerAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
    }
}
