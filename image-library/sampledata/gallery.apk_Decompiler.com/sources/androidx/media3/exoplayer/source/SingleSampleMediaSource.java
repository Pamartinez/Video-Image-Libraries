package androidx.media3.exoplayer.source;

import E2.r;
import F2.U;
import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.util.ReleasableExecutor;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleSampleMediaSource extends BaseMediaSource {
    private final DataSource.Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final r downloadExecutorSupplier;
    private final long durationUs;
    private final Format format;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final MediaItem mediaItem;
    private final Timeline timeline;
    private TransferListener transferListener;
    private final boolean treatLoadErrorsAsEndOfStream;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory {
        private final DataSource.Factory dataSourceFactory;
        private r downloadExecutorSupplier;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
        private Object tag;
        private String trackId;
        private boolean treatLoadErrorsAsEndOfStream = true;

        public Factory(DataSource.Factory factory) {
            this.dataSourceFactory = (DataSource.Factory) Assertions.checkNotNull(factory);
        }

        public SingleSampleMediaSource createMediaSource(MediaItem.SubtitleConfiguration subtitleConfiguration, long j2) {
            return new SingleSampleMediaSource(this.trackId, subtitleConfiguration, this.dataSourceFactory, j2, this.loadErrorHandlingPolicy, this.treatLoadErrorsAsEndOfStream, this.tag, this.downloadExecutorSupplier);
        }

        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
            if (loadErrorHandlingPolicy2 == null) {
                loadErrorHandlingPolicy2 = new DefaultLoadErrorHandlingPolicy();
            }
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
            return this;
        }
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        ReleasableExecutor releasableExecutor;
        DataSpec dataSpec2 = this.dataSpec;
        DataSource.Factory factory = this.dataSourceFactory;
        TransferListener transferListener2 = this.transferListener;
        Format format2 = this.format;
        long j3 = this.durationUs;
        LoadErrorHandlingPolicy loadErrorHandlingPolicy2 = this.loadErrorHandlingPolicy;
        MediaSourceEventListener.EventDispatcher createEventDispatcher = createEventDispatcher(mediaPeriodId);
        boolean z = this.treatLoadErrorsAsEndOfStream;
        r rVar = this.downloadExecutorSupplier;
        if (rVar != null) {
            releasableExecutor = (ReleasableExecutor) rVar.get();
        } else {
            releasableExecutor = null;
        }
        return new SingleSampleMediaPeriod(dataSpec2, factory, transferListener2, format2, j3, loadErrorHandlingPolicy2, createEventDispatcher, z, releasableExecutor);
    }

    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public void prepareSourceInternal(TransferListener transferListener2) {
        this.transferListener = transferListener2;
        refreshSourceInfo(this.timeline);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((SingleSampleMediaPeriod) mediaPeriod).release();
    }

    private SingleSampleMediaSource(String str, MediaItem.SubtitleConfiguration subtitleConfiguration, DataSource.Factory factory, long j2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, boolean z, Object obj, r rVar) {
        this.dataSourceFactory = factory;
        this.durationUs = j2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.treatLoadErrorsAsEndOfStream = z;
        MediaItem build = new MediaItem.Builder().setUri(Uri.EMPTY).setMediaId(subtitleConfiguration.uri.toString()).setSubtitleConfigurations(U.B(subtitleConfiguration)).setTag(obj).build();
        this.mediaItem = build;
        Format.Builder builder = new Format.Builder();
        String str2 = subtitleConfiguration.mimeType;
        Format.Builder label = builder.setSampleMimeType(str2 == null ? "text/x-unknown" : str2).setLanguage(subtitleConfiguration.language).setSelectionFlags(subtitleConfiguration.selectionFlags).setRoleFlags(subtitleConfiguration.roleFlags).setLabel(subtitleConfiguration.label);
        String str3 = subtitleConfiguration.id;
        this.format = label.setId(str3 != null ? str3 : str).build();
        this.dataSpec = new DataSpec.Builder().setUri(subtitleConfiguration.uri).setFlags(1).build();
        this.timeline = new SinglePeriodTimeline(j2, true, false, false, (Object) null, build);
        this.downloadExecutorSupplier = rVar;
    }

    public void maybeThrowSourceInfoRefreshError() {
    }

    public void releaseSourceInternal() {
    }
}
