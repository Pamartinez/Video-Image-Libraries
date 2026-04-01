package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.MediaClock;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.transformer.AssetLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ExoAssetLoaderBaseRenderer extends BaseRenderer {
    private final AssetLoader.Listener assetLoaderListener;
    protected Codec decoder;
    private final DecoderInputBuffer decoderInputBuffer = new DecoderInputBuffer(0);
    private boolean hasPendingConsumerInput;
    private Format inputFormat;
    protected boolean isEnded;
    private boolean isRunning;
    private final TransformerMediaClock mediaClock;
    private Format outputFormat;
    protected SampleConsumer sampleConsumer;
    private boolean shouldInitDecoder;
    protected long streamStartPositionUs;

    public ExoAssetLoaderBaseRenderer(int i2, TransformerMediaClock transformerMediaClock, AssetLoader.Listener listener) {
        super(i2);
        this.mediaClock = transformerMediaClock;
        this.assetLoaderListener = listener;
    }

    private boolean ensureSampleConsumerInitialized() {
        if (this.sampleConsumer != null) {
            return true;
        }
        if (this.outputFormat == null) {
            if (this.decoder == null || TransformerUtil.getProcessedTrackType(this.inputFormat.sampleMimeType) != 1) {
                this.outputFormat = overrideOutputFormat(this.inputFormat);
            } else {
                Format outputFormat2 = this.decoder.getOutputFormat();
                if (outputFormat2 == null) {
                    return false;
                }
                this.outputFormat = overrideOutputFormat(outputFormat2);
            }
        }
        SampleConsumer onOutputFormat = this.assetLoaderListener.onOutputFormat(this.outputFormat);
        if (onOutputFormat == null) {
            return false;
        }
        this.sampleConsumer = onOutputFormat;
        return true;
    }

    private boolean feedConsumerFromInput() {
        DecoderInputBuffer inputBuffer = this.sampleConsumer.getInputBuffer();
        if (inputBuffer == null) {
            return false;
        }
        if (!this.hasPendingConsumerInput) {
            if (!readInput(inputBuffer)) {
                return false;
            }
            if (shouldDropInputBuffer(inputBuffer)) {
                return true;
            }
            this.hasPendingConsumerInput = true;
        }
        boolean isEndOfStream = inputBuffer.isEndOfStream();
        if (!this.sampleConsumer.queueInputBuffer()) {
            return false;
        }
        this.hasPendingConsumerInput = false;
        this.isEnded = isEndOfStream;
        return !isEndOfStream;
    }

    private boolean feedDecoderFromInput() {
        if (!this.decoder.maybeDequeueInputBuffer(this.decoderInputBuffer) || !readInput(this.decoderInputBuffer)) {
            return false;
        }
        if (shouldDropInputBuffer(this.decoderInputBuffer)) {
            return true;
        }
        onDecoderInputReady(this.decoderInputBuffer);
        this.decoder.queueInputBuffer(this.decoderInputBuffer);
        return true;
    }

    private boolean readInput(DecoderInputBuffer decoderInputBuffer2) {
        int readSource = readSource(getFormatHolder(), decoderInputBuffer2, 0);
        if (readSource == -5) {
            throw new IllegalStateException("Format changes are not supported.");
        } else if (readSource != -4) {
            return false;
        } else {
            decoderInputBuffer2.flip();
            if (decoderInputBuffer2.isEndOfStream()) {
                return true;
            }
            this.mediaClock.updateTimeForTrackType(getTrackType(), decoderInputBuffer2.timeUs);
            return true;
        }
    }

    private boolean readInputFormatAndInitDecoderIfNeeded() {
        Format format = this.inputFormat;
        if (format != null && !this.shouldInitDecoder) {
            return true;
        }
        if (format == null) {
            FormatHolder formatHolder = getFormatHolder();
            if (readSource(formatHolder, this.decoderInputBuffer, 2) != -5) {
                return false;
            }
            Format overrideInputFormat = overrideInputFormat((Format) Assertions.checkNotNull(formatHolder.format));
            this.inputFormat = overrideInputFormat;
            onInputFormatRead(overrideInputFormat);
            this.shouldInitDecoder = this.assetLoaderListener.onTrackAdded(this.inputFormat, 3);
        }
        if (this.shouldInitDecoder) {
            if (TransformerUtil.getProcessedTrackType(this.inputFormat.sampleMimeType) == 2 && !ensureSampleConsumerInitialized()) {
                return false;
            }
            initDecoder(this.inputFormat);
            this.shouldInitDecoder = false;
        }
        return true;
    }

    public abstract boolean feedConsumerFromDecoder();

    public MediaClock getMediaClock() {
        return this.mediaClock;
    }

    public abstract void initDecoder(Format format);

    public boolean isEnded() {
        return this.isEnded;
    }

    public boolean isReady() {
        return true;
    }

    public void onEnabled(boolean z, boolean z3) {
        this.mediaClock.updateTimeForTrackType(getTrackType(), 0);
    }

    public void onReset() {
        Codec codec = this.decoder;
        if (codec != null) {
            codec.release();
        }
    }

    public void onStarted() {
        this.isRunning = true;
    }

    public void onStopped() {
        this.isRunning = false;
    }

    public void onStreamChanged(Format[] formatArr, long j2, long j3, MediaSource.MediaPeriodId mediaPeriodId) {
        this.streamStartPositionUs = j2;
    }

    public void render(long j2, long j3) {
        boolean z;
        try {
            if (this.isRunning && !isEnded()) {
                if (readInputFormatAndInitDecoderIfNeeded()) {
                    if (this.decoder != null) {
                        do {
                            if (ensureSampleConsumerInitialized()) {
                                z = feedConsumerFromDecoder();
                            } else {
                                z = false;
                            }
                        } while (z | feedDecoderFromInput());
                    } else if (ensureSampleConsumerInitialized()) {
                        do {
                        } while (feedConsumerFromInput());
                    }
                }
            }
        } catch (ExportException e) {
            this.isRunning = false;
            this.assetLoaderListener.onError(e);
        }
    }

    public abstract boolean shouldDropInputBuffer(DecoderInputBuffer decoderInputBuffer2);

    public int supportsFormat(Format format) {
        int i2;
        if (MimeTypes.getTrackType(format.sampleMimeType) == getTrackType()) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        return RendererCapabilities.create(i2);
    }

    public void onDecoderInputReady(DecoderInputBuffer decoderInputBuffer2) {
    }

    public void onInputFormatRead(Format format) {
    }

    public Format overrideInputFormat(Format format) {
        return format;
    }

    public Format overrideOutputFormat(Format format) {
        return format;
    }
}
