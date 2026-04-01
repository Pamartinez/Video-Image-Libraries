package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseRenderer implements Renderer, RendererCapabilities {
    private Clock clock;
    private RendererConfiguration configuration;
    private final FormatHolder formatHolder;
    private int index;
    private long lastResetPositionUs;
    private final Object lock = new Object();
    private MediaSource.MediaPeriodId mediaPeriodId;
    private PlayerId playerId;
    private long readingPositionUs;
    private RendererCapabilities.Listener rendererCapabilitiesListener;
    private int state;
    private SampleStream stream;
    private Format[] streamFormats;
    private boolean streamIsFinal;
    private long streamOffsetUs;
    private Timeline timeline;
    private final int trackType;

    public BaseRenderer(int i2) {
        this.trackType = i2;
        this.formatHolder = new FormatHolder();
        this.readingPositionUs = Long.MIN_VALUE;
        this.timeline = Timeline.EMPTY;
    }

    public final void clearListener() {
        synchronized (this.lock) {
            this.rendererCapabilitiesListener = null;
        }
    }

    public final void disable() {
        boolean z = true;
        if (this.state != 1) {
            z = false;
        }
        Assertions.checkState(z);
        this.formatHolder.clear();
        this.state = 0;
        this.stream = null;
        this.streamFormats = null;
        this.streamIsFinal = false;
        onDisabled();
        this.mediaPeriodId = null;
    }

    public final void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j2, boolean z, boolean z3, long j3, long j8, MediaSource.MediaPeriodId mediaPeriodId2) {
        boolean z7;
        if (this.state == 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        Assertions.checkState(z7);
        this.configuration = rendererConfiguration;
        MediaSource.MediaPeriodId mediaPeriodId3 = mediaPeriodId2;
        this.mediaPeriodId = mediaPeriodId3;
        this.state = 1;
        onEnabled(z, z3);
        long j10 = j3;
        replaceStream(formatArr, sampleStream, j10, j8, mediaPeriodId3);
        resetPosition(j10, z);
    }

    public final FormatHolder getFormatHolder() {
        this.formatHolder.clear();
        return this.formatHolder;
    }

    public final long getLastResetPositionUs() {
        return this.lastResetPositionUs;
    }

    public final long getReadingPositionUs() {
        return this.readingPositionUs;
    }

    public final int getState() {
        return this.state;
    }

    public final SampleStream getStream() {
        return this.stream;
    }

    public final long getStreamOffsetUs() {
        return this.streamOffsetUs;
    }

    public final int getTrackType() {
        return this.trackType;
    }

    public final boolean hasReadStreamToEnd() {
        if (this.readingPositionUs == Long.MIN_VALUE) {
            return true;
        }
        return false;
    }

    public final void init(int i2, PlayerId playerId2, Clock clock2) {
        this.index = i2;
        this.playerId = playerId2;
        this.clock = clock2;
        onInit();
    }

    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    public final void maybeThrowStreamError() {
        ((SampleStream) Assertions.checkNotNull(this.stream)).maybeThrowError();
    }

    public abstract void onEnabled(boolean z, boolean z3);

    public abstract void onReset();

    public abstract void onStarted();

    public abstract void onStopped();

    public abstract void onStreamChanged(Format[] formatArr, long j2, long j3, MediaSource.MediaPeriodId mediaPeriodId2);

    public final int readSource(FormatHolder formatHolder2, DecoderInputBuffer decoderInputBuffer, int i2) {
        int readData = ((SampleStream) Assertions.checkNotNull(this.stream)).readData(formatHolder2, decoderInputBuffer, i2);
        if (readData != -4) {
            if (readData == -5) {
                Format format = (Format) Assertions.checkNotNull(formatHolder2.format);
                if (format.subsampleOffsetUs != Long.MAX_VALUE) {
                    formatHolder2.format = format.buildUpon().setSubsampleOffsetUs(format.subsampleOffsetUs + this.streamOffsetUs).build();
                }
            }
            return readData;
        } else if (decoderInputBuffer.isEndOfStream()) {
            this.readingPositionUs = Long.MIN_VALUE;
            if (this.streamIsFinal) {
                return -4;
            }
            return -3;
        } else {
            long j2 = decoderInputBuffer.timeUs + this.streamOffsetUs;
            decoderInputBuffer.timeUs = j2;
            this.readingPositionUs = Math.max(this.readingPositionUs, j2);
            return readData;
        }
    }

    public final void release() {
        boolean z;
        if (this.state == 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        onRelease();
    }

    public final void replaceStream(Format[] formatArr, SampleStream sampleStream, long j2, long j3, MediaSource.MediaPeriodId mediaPeriodId2) {
        Assertions.checkState(!this.streamIsFinal);
        this.stream = sampleStream;
        this.mediaPeriodId = mediaPeriodId2;
        if (this.readingPositionUs == Long.MIN_VALUE) {
            this.readingPositionUs = j2;
        }
        this.streamFormats = formatArr;
        this.streamOffsetUs = j3;
        onStreamChanged(formatArr, j2, j3, mediaPeriodId2);
    }

    public final void reset() {
        boolean z;
        if (this.state == 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.formatHolder.clear();
        onReset();
    }

    public final void resetPosition(long j2) {
        resetPosition(j2, false);
    }

    public final void setCurrentStreamFinal() {
        this.streamIsFinal = true;
    }

    public final void setListener(RendererCapabilities.Listener listener) {
        synchronized (this.lock) {
            this.rendererCapabilitiesListener = listener;
        }
    }

    public final void setTimeline(Timeline timeline2) {
        if (!Objects.equals(this.timeline, timeline2)) {
            this.timeline = timeline2;
            onTimelineChanged(timeline2);
        }
    }

    public final void start() {
        boolean z = true;
        if (this.state != 1) {
            z = false;
        }
        Assertions.checkState(z);
        this.state = 2;
        onStarted();
    }

    public final void stop() {
        boolean z;
        if (this.state == 2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.state = 1;
        onStopped();
    }

    public int supportsMixedMimeTypeAdaptation() {
        return 0;
    }

    private void resetPosition(long j2, boolean z) {
        this.streamIsFinal = false;
        this.lastResetPositionUs = j2;
        this.readingPositionUs = j2;
        onPositionReset(j2, z);
    }

    public final RendererCapabilities getCapabilities() {
        return this;
    }

    public void onDisabled() {
    }

    public void onInit() {
    }

    public void onRelease() {
    }

    public void onTimelineChanged(Timeline timeline2) {
    }

    public void handleMessage(int i2, Object obj) {
    }

    public void onPositionReset(long j2, boolean z) {
    }
}
