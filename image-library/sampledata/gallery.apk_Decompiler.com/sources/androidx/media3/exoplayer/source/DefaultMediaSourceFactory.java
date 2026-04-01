package androidx.media3.exoplayer.source;

import E2.r;
import F2.U;
import M.b;
import android.content.Context;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.ExternallyLoadedMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.source.SingleSampleMediaSource;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleExtractor;
import androidx.media3.extractor.text.SubtitleParser;
import c0.C0086a;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultMediaSourceFactory implements MediaSourceFactory {
    private DataSource.Factory dataSourceFactory;
    private final DelegateFactoryLoader delegateFactoryLoader;
    private long liveMaxOffsetMs;
    private float liveMaxSpeed;
    private long liveMinOffsetMs;
    private float liveMinSpeed;
    private long liveTargetOffsetMs;
    private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private boolean parseSubtitlesDuringExtraction;
    private MediaSource.Factory serverSideAdInsertionMediaSourceFactory;
    private SubtitleParser.Factory subtitleParserFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DelegateFactoryLoader {
        private int codecsToParseWithinGopSampleDependencies;
        private DataSource.Factory dataSourceFactory;
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private final ExtractorsFactory extractorsFactory;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private final Map<Integer, MediaSource.Factory> mediaSourceFactories = new HashMap();
        private final Map<Integer, r> mediaSourceFactorySuppliers = new HashMap();
        private boolean parseSubtitlesDuringExtraction = true;
        private SubtitleParser.Factory subtitleParserFactory;

        public DelegateFactoryLoader(ExtractorsFactory extractorsFactory2, SubtitleParser.Factory factory) {
            this.extractorsFactory = extractorsFactory2;
            this.subtitleParserFactory = factory;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ MediaSource.Factory lambda$loadSupplier$4(DataSource.Factory factory) {
            return new ProgressiveMediaSource.Factory(factory, this.extractorsFactory);
        }

        private r loadSupplier(int i2) {
            r rVar;
            a aVar;
            r rVar2 = this.mediaSourceFactorySuppliers.get(Integer.valueOf(i2));
            if (rVar2 != null) {
                return rVar2;
            }
            DataSource.Factory factory = (DataSource.Factory) Assertions.checkNotNull(this.dataSourceFactory);
            Class<MediaSource.Factory> cls = MediaSource.Factory.class;
            if (i2 != 0) {
                if (i2 == 1) {
                    aVar = new a(Class.forName("androidx.media3.exoplayer.smoothstreaming.SsMediaSource$Factory").asSubclass(cls), factory, 1);
                } else if (i2 == 2) {
                    aVar = new a(Class.forName("androidx.media3.exoplayer.hls.HlsMediaSource$Factory").asSubclass(cls), factory, 2);
                } else if (i2 == 3) {
                    rVar = new b(Class.forName("androidx.media3.exoplayer.rtsp.RtspMediaSource$Factory").asSubclass(cls));
                } else if (i2 == 4) {
                    rVar = new a(this, factory, 3);
                } else {
                    throw new IllegalArgumentException(C0086a.i(i2, "Unrecognized contentType: "));
                }
                rVar = aVar;
            } else {
                rVar = new a(Class.forName("androidx.media3.exoplayer.dash.DashMediaSource$Factory").asSubclass(cls), factory, 0);
            }
            this.mediaSourceFactorySuppliers.put(Integer.valueOf(i2), rVar);
            return rVar;
        }

        public MediaSource.Factory getMediaSourceFactory(int i2) {
            MediaSource.Factory factory = this.mediaSourceFactories.get(Integer.valueOf(i2));
            if (factory != null) {
                return factory;
            }
            MediaSource.Factory factory2 = (MediaSource.Factory) loadSupplier(i2).get();
            DrmSessionManagerProvider drmSessionManagerProvider2 = this.drmSessionManagerProvider;
            if (drmSessionManagerProvider2 != null) {
                factory2.setDrmSessionManagerProvider(drmSessionManagerProvider2);
            }
            LoadErrorHandlingPolicy loadErrorHandlingPolicy2 = this.loadErrorHandlingPolicy;
            if (loadErrorHandlingPolicy2 != null) {
                factory2.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
            }
            factory2.setSubtitleParserFactory(this.subtitleParserFactory);
            factory2.experimentalParseSubtitlesDuringExtraction(this.parseSubtitlesDuringExtraction);
            factory2.experimentalSetCodecsToParseWithinGopSampleDependencies(this.codecsToParseWithinGopSampleDependencies);
            this.mediaSourceFactories.put(Integer.valueOf(i2), factory2);
            return factory2;
        }

        public void setCodecsToParseWithinGopSampleDependencies(int i2) {
            this.codecsToParseWithinGopSampleDependencies = i2;
            this.extractorsFactory.experimentalSetCodecsToParseWithinGopSampleDependencies(i2);
        }

        public void setDataSourceFactory(DataSource.Factory factory) {
            if (factory != this.dataSourceFactory) {
                this.dataSourceFactory = factory;
                this.mediaSourceFactorySuppliers.clear();
                this.mediaSourceFactories.clear();
            }
        }

        public void setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider2) {
            this.drmSessionManagerProvider = drmSessionManagerProvider2;
            for (MediaSource.Factory drmSessionManagerProvider3 : this.mediaSourceFactories.values()) {
                drmSessionManagerProvider3.setDrmSessionManagerProvider(drmSessionManagerProvider2);
            }
        }

        public void setJpegExtractorFlags(int i2) {
            ExtractorsFactory extractorsFactory2 = this.extractorsFactory;
            if (extractorsFactory2 instanceof DefaultExtractorsFactory) {
                ((DefaultExtractorsFactory) extractorsFactory2).setJpegExtractorFlags(i2);
            }
        }

        public void setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
            for (MediaSource.Factory loadErrorHandlingPolicy3 : this.mediaSourceFactories.values()) {
                loadErrorHandlingPolicy3.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
            }
        }

        public void setParseSubtitlesDuringExtraction(boolean z) {
            this.parseSubtitlesDuringExtraction = z;
            this.extractorsFactory.experimentalSetTextTrackTranscodingEnabled(z);
            for (MediaSource.Factory experimentalParseSubtitlesDuringExtraction : this.mediaSourceFactories.values()) {
                experimentalParseSubtitlesDuringExtraction.experimentalParseSubtitlesDuringExtraction(z);
            }
        }

        public void setSubtitleParserFactory(SubtitleParser.Factory factory) {
            this.subtitleParserFactory = factory;
            this.extractorsFactory.setSubtitleParserFactory(factory);
            for (MediaSource.Factory subtitleParserFactory2 : this.mediaSourceFactories.values()) {
                subtitleParserFactory2.setSubtitleParserFactory(factory);
            }
        }
    }

    public DefaultMediaSourceFactory(Context context, ExtractorsFactory extractorsFactory) {
        this((DataSource.Factory) new DefaultDataSource.Factory(context), extractorsFactory);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Extractor[] lambda$createMediaSource$0(Format format) {
        Extractor extractor;
        if (this.subtitleParserFactory.supportsFormat(format)) {
            extractor = new SubtitleExtractor(this.subtitleParserFactory.create(format), (Format) null);
        } else {
            extractor = new UnknownSubtitlesExtractor(format);
        }
        return new Extractor[]{extractor};
    }

    private static MediaSource maybeClipMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        MediaItem.ClippingConfiguration clippingConfiguration = mediaItem.clippingConfiguration;
        if (clippingConfiguration.startPositionUs == 0 && clippingConfiguration.endPositionUs == Long.MIN_VALUE && !clippingConfiguration.relativeToDefaultPosition) {
            return mediaSource;
        }
        return new ClippingMediaSource.Builder(mediaSource).setStartPositionUs(mediaItem.clippingConfiguration.startPositionUs).setEndPositionUs(mediaItem.clippingConfiguration.endPositionUs).setEnableInitialDiscontinuity(!mediaItem.clippingConfiguration.startsAtKeyFrame).setAllowDynamicClippingUpdates(mediaItem.clippingConfiguration.relativeToLiveWindow).setRelativeToDefaultPosition(mediaItem.clippingConfiguration.relativeToDefaultPosition).setAllowUnseekableMedia(mediaItem.clippingConfiguration.allowUnseekableMedia).build();
    }

    private MediaSource maybeWrapWithAdsMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        Assertions.checkNotNull(mediaItem.localConfiguration);
        mediaItem.localConfiguration.getClass();
        return mediaSource;
    }

    /* access modifiers changed from: private */
    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> cls, DataSource.Factory factory) {
        try {
            return (MediaSource.Factory) cls.getConstructor(new Class[]{DataSource.Factory.class}).newInstance(new Object[]{factory});
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public MediaSource createMediaSource(MediaItem mediaItem) {
        Assertions.checkNotNull(mediaItem.localConfiguration);
        String scheme = mediaItem.localConfiguration.uri.getScheme();
        if (scheme != null && scheme.equals("ssai")) {
            return ((MediaSource.Factory) Assertions.checkNotNull(this.serverSideAdInsertionMediaSourceFactory)).createMediaSource(mediaItem);
        }
        if (Objects.equals(mediaItem.localConfiguration.mimeType, "application/x-image-uri")) {
            long msToUs = Util.msToUs(mediaItem.localConfiguration.imageDurationMs);
            if (Assertions.checkNotNull(null) == null) {
                return new ExternallyLoadedMediaSource.Factory(msToUs, (ExternalLoader) null).createMediaSource(mediaItem);
            }
            throw new ClassCastException();
        }
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        int inferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType);
        if (mediaItem.localConfiguration.imageDurationMs != -9223372036854775807L) {
            this.delegateFactoryLoader.setJpegExtractorFlags(1);
        }
        try {
            MediaSource.Factory mediaSourceFactory = this.delegateFactoryLoader.getMediaSourceFactory(inferContentTypeForUriAndMimeType);
            MediaItem.LiveConfiguration.Builder buildUpon = mediaItem.liveConfiguration.buildUpon();
            if (mediaItem.liveConfiguration.targetOffsetMs == -9223372036854775807L) {
                buildUpon.setTargetOffsetMs(this.liveTargetOffsetMs);
            }
            if (mediaItem.liveConfiguration.minPlaybackSpeed == -3.4028235E38f) {
                buildUpon.setMinPlaybackSpeed(this.liveMinSpeed);
            }
            if (mediaItem.liveConfiguration.maxPlaybackSpeed == -3.4028235E38f) {
                buildUpon.setMaxPlaybackSpeed(this.liveMaxSpeed);
            }
            if (mediaItem.liveConfiguration.minOffsetMs == -9223372036854775807L) {
                buildUpon.setMinOffsetMs(this.liveMinOffsetMs);
            }
            if (mediaItem.liveConfiguration.maxOffsetMs == -9223372036854775807L) {
                buildUpon.setMaxOffsetMs(this.liveMaxOffsetMs);
            }
            MediaItem.LiveConfiguration build = buildUpon.build();
            if (!build.equals(mediaItem.liveConfiguration)) {
                mediaItem = mediaItem.buildUpon().setLiveConfiguration(build).build();
            }
            MediaSource createMediaSource = mediaSourceFactory.createMediaSource(mediaItem);
            U u = ((MediaItem.LocalConfiguration) Util.castNonNull(mediaItem.localConfiguration)).subtitleConfigurations;
            if (!u.isEmpty()) {
                MediaSource[] mediaSourceArr = new MediaSource[(u.size() + 1)];
                mediaSourceArr[0] = createMediaSource;
                for (int i2 = 0; i2 < u.size(); i2++) {
                    if (this.parseSubtitlesDuringExtraction) {
                        Format build2 = new Format.Builder().setSampleMimeType(((MediaItem.SubtitleConfiguration) u.get(i2)).mimeType).setLanguage(((MediaItem.SubtitleConfiguration) u.get(i2)).language).setSelectionFlags(((MediaItem.SubtitleConfiguration) u.get(i2)).selectionFlags).setRoleFlags(((MediaItem.SubtitleConfiguration) u.get(i2)).roleFlags).setLabel(((MediaItem.SubtitleConfiguration) u.get(i2)).label).setId(((MediaItem.SubtitleConfiguration) u.get(i2)).id).build();
                        ProgressiveMediaSource.Factory factory = new ProgressiveMediaSource.Factory(this.dataSourceFactory, (ExtractorsFactory) new b(this, build2));
                        if (this.subtitleParserFactory.supportsFormat(build2)) {
                            build2 = build2.buildUpon().setSampleMimeType("application/x-media3-cues").setCodecs(build2.sampleMimeType).setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(build2)).build();
                        }
                        ProgressiveMediaSource.Factory enableLazyLoadingWithSingleTrack = factory.enableLazyLoadingWithSingleTrack(0, build2);
                        LoadErrorHandlingPolicy loadErrorHandlingPolicy2 = this.loadErrorHandlingPolicy;
                        if (loadErrorHandlingPolicy2 != null) {
                            enableLazyLoadingWithSingleTrack.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
                        }
                        mediaSourceArr[i2 + 1] = enableLazyLoadingWithSingleTrack.createMediaSource(MediaItem.fromUri(((MediaItem.SubtitleConfiguration) u.get(i2)).uri.toString()));
                    } else {
                        SingleSampleMediaSource.Factory factory2 = new SingleSampleMediaSource.Factory(this.dataSourceFactory);
                        LoadErrorHandlingPolicy loadErrorHandlingPolicy3 = this.loadErrorHandlingPolicy;
                        if (loadErrorHandlingPolicy3 != null) {
                            factory2.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy3);
                        }
                        mediaSourceArr[i2 + 1] = factory2.createMediaSource((MediaItem.SubtitleConfiguration) u.get(i2), -9223372036854775807L);
                    }
                }
                createMediaSource = new MergingMediaSource(mediaSourceArr);
            }
            return maybeWrapWithAdsMediaSource(mediaItem, maybeClipMediaSource(mediaItem, createMediaSource));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public DefaultMediaSourceFactory(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
        this.dataSourceFactory = factory;
        DefaultSubtitleParserFactory defaultSubtitleParserFactory = new DefaultSubtitleParserFactory();
        this.subtitleParserFactory = defaultSubtitleParserFactory;
        DelegateFactoryLoader delegateFactoryLoader2 = new DelegateFactoryLoader(extractorsFactory, defaultSubtitleParserFactory);
        this.delegateFactoryLoader = delegateFactoryLoader2;
        delegateFactoryLoader2.setDataSourceFactory(factory);
        this.liveTargetOffsetMs = -9223372036854775807L;
        this.liveMinOffsetMs = -9223372036854775807L;
        this.liveMaxOffsetMs = -9223372036854775807L;
        this.liveMinSpeed = -3.4028235E38f;
        this.liveMaxSpeed = -3.4028235E38f;
        this.parseSubtitlesDuringExtraction = true;
    }

    @Deprecated
    public DefaultMediaSourceFactory experimentalParseSubtitlesDuringExtraction(boolean z) {
        this.parseSubtitlesDuringExtraction = z;
        this.delegateFactoryLoader.setParseSubtitlesDuringExtraction(z);
        return this;
    }

    public DefaultMediaSourceFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i2) {
        this.delegateFactoryLoader.setCodecsToParseWithinGopSampleDependencies(i2);
        return this;
    }

    public DefaultMediaSourceFactory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
        this.delegateFactoryLoader.setDrmSessionManagerProvider((DrmSessionManagerProvider) Assertions.checkNotNull(drmSessionManagerProvider, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior."));
        return this;
    }

    public DefaultMediaSourceFactory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
        this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(loadErrorHandlingPolicy2, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
        this.delegateFactoryLoader.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
        return this;
    }

    public DefaultMediaSourceFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        this.subtitleParserFactory = (SubtitleParser.Factory) Assertions.checkNotNull(factory);
        this.delegateFactoryLoader.setSubtitleParserFactory(factory);
        return this;
    }

    /* access modifiers changed from: private */
    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> cls) {
        try {
            return (MediaSource.Factory) cls.getConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UnknownSubtitlesExtractor implements Extractor {
        private final Format format;

        public UnknownSubtitlesExtractor(Format format2) {
            this.format = format2;
        }

        public void init(ExtractorOutput extractorOutput) {
            TrackOutput track = extractorOutput.track(0, 3);
            extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
            extractorOutput.endTracks();
            track.format(this.format.buildUpon().setSampleMimeType("text/x-unknown").setCodecs(this.format.sampleMimeType).build());
        }

        public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
            if (extractorInput.skip(Integer.MAX_VALUE) == -1) {
                return -1;
            }
            return 0;
        }

        public boolean sniff(ExtractorInput extractorInput) {
            return true;
        }

        public void release() {
        }

        public void seek(long j2, long j3) {
        }
    }
}
