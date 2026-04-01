package androidx.media3.transformer;

import F2.X;
import F2.Y;
import N.a;
import android.content.Context;
import android.media.metrics.LogSessionId;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.ExoTimeoutException;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.Codec;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExoPlayerAssetLoader implements AssetLoader {
    /* access modifiers changed from: private */
    public final Context context;
    private final CapturingDecoderFactory decoderFactory;
    /* access modifiers changed from: private */
    public final EditedMediaItem editedMediaItem;
    /* access modifiers changed from: private */
    public final ExoPlayer player;
    /* access modifiers changed from: private */
    public int progressState;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements AssetLoader.Factory {
        private final Clock clock;
        private final Context context;
        private final Codec.DecoderFactory decoderFactory;
        private final LogSessionId logSessionId;
        private final MediaSource.Factory mediaSourceFactory;
        private final TrackSelector.Factory trackSelectorFactory;

        public Factory(Context context2, Codec.DecoderFactory decoderFactory2, Clock clock2, MediaSource.Factory factory, TrackSelector.Factory factory2, LogSessionId logSessionId2) {
            this.context = context2;
            this.decoderFactory = decoderFactory2;
            this.clock = clock2;
            this.mediaSourceFactory = factory;
            this.trackSelectorFactory = factory2;
            this.logSessionId = logSessionId2;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ TrackSelector lambda$createAssetLoader$0(DefaultTrackSelector.Parameters parameters, Context context2) {
            DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(context2);
            defaultTrackSelector.setParameters(parameters);
            return defaultTrackSelector;
        }

        public AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, AssetLoader.Listener listener, AssetLoader.CompositionSettings compositionSettings) {
            DefaultMediaSourceFactory defaultMediaSourceFactory;
            a aVar;
            MediaSource.Factory factory = this.mediaSourceFactory;
            if (factory == null) {
                DefaultExtractorsFactory defaultExtractorsFactory = new DefaultExtractorsFactory();
                if (editedMediaItem.flattenForSlowMotion) {
                    defaultExtractorsFactory.setMp4ExtractorFlags(4);
                }
                defaultMediaSourceFactory = new DefaultMediaSourceFactory(this.context, (ExtractorsFactory) defaultExtractorsFactory);
            } else {
                defaultMediaSourceFactory = factory;
            }
            TrackSelector.Factory factory2 = this.trackSelectorFactory;
            if (factory2 == null) {
                aVar = new a(new DefaultTrackSelector.Parameters.Builder(this.context).setForceHighestSupportedBitrate(true).setConstrainAudioChannelCountToDeviceCapabilities(false).build());
            } else {
                aVar = factory2;
            }
            return new ExoPlayerAssetLoader(this.context, editedMediaItem, defaultMediaSourceFactory, this.decoderFactory, compositionSettings.hdrMode, looper, listener, this.clock, aVar, this.logSessionId);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class PlayerListener implements Player.Listener {
        private final AssetLoader.Listener assetLoaderListener;

        public PlayerListener(AssetLoader.Listener listener) {
            this.assetLoaderListener = listener;
        }

        public void onPlayerError(PlaybackException playbackException) {
            Throwable cause = playbackException.getCause();
            if (!(cause instanceof ExoTimeoutException) || ((ExoTimeoutException) cause).timeoutOperation != 1) {
                int i2 = 1000;
                Object obj = ExportException.NAME_TO_ERROR_CODE.get(playbackException.getErrorCodeName());
                if (obj != null) {
                    i2 = obj;
                }
                this.assetLoaderListener.onError(ExportException.createForAssetLoader(playbackException, ((Integer) Assertions.checkNotNull((Integer) i2)).intValue()));
                return;
            }
            Log.e("ExoPlayerAssetLoader", "Releasing the player timed out.", playbackException);
        }

        public void onTimelineChanged(Timeline timeline, int i2) {
            int i7;
            try {
                if (ExoPlayerAssetLoader.this.progressState == 1) {
                    Timeline.Window window = new Timeline.Window();
                    timeline.getWindow(0, window);
                    if (!window.isPlaceholder) {
                        long j2 = window.durationUs;
                        ExoPlayerAssetLoader exoPlayerAssetLoader = ExoPlayerAssetLoader.this;
                        if (j2 > 0) {
                            if (j2 != -9223372036854775807L) {
                                i7 = 2;
                                int unused = exoPlayerAssetLoader.progressState = i7;
                                this.assetLoaderListener.onDurationUs(window.durationUs);
                            }
                        }
                        i7 = 3;
                        int unused2 = exoPlayerAssetLoader.progressState = i7;
                        this.assetLoaderListener.onDurationUs(window.durationUs);
                    }
                }
            } catch (RuntimeException e) {
                this.assetLoaderListener.onError(ExportException.createForAssetLoader(e, 1000));
            }
        }

        public void onTracksChanged(Tracks tracks) {
            try {
                int isTypeSelected = tracks.isTypeSelected(1);
                if (tracks.isTypeSelected(2)) {
                    isTypeSelected++;
                }
                ExoPlayerAssetLoader.maybeWarnUnsupportedTrackTypes(tracks);
                if (isTypeSelected > 0) {
                    this.assetLoaderListener.onTrackCount(isTypeSelected);
                    ExoPlayerAssetLoader.this.player.play();
                    return;
                }
                String str = "The asset loader has no audio or video track to output.";
                if (TransformerUtil.isImage(ExoPlayerAssetLoader.this.context, ExoPlayerAssetLoader.this.editedMediaItem.mediaItem)) {
                    str = "The asset loader has no audio or video track to output. Try setting an image duration on input image MediaItems.";
                }
                this.assetLoaderListener.onError(ExportException.createForAssetLoader(new IllegalStateException(str), 1001));
            } catch (RuntimeException e) {
                this.assetLoaderListener.onError(ExportException.createForAssetLoader(e, 1000));
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RenderersFactoryImpl implements RenderersFactory {
        private final AssetLoader.Listener assetLoaderListener;
        private final Codec.DecoderFactory decoderFactory;
        private final boolean flattenForSlowMotion;
        private final int hdrMode;
        private final LogSessionId logSessionId;
        private final TransformerMediaClock mediaClock = new TransformerMediaClock();
        private final boolean removeAudio;
        private final boolean removeVideo;

        public RenderersFactoryImpl(boolean z, boolean z3, boolean z7, Codec.DecoderFactory decoderFactory2, int i2, AssetLoader.Listener listener, LogSessionId logSessionId2) {
            this.removeAudio = z;
            this.removeVideo = z3;
            this.flattenForSlowMotion = z7;
            this.decoderFactory = decoderFactory2;
            this.hdrMode = i2;
            this.assetLoaderListener = listener;
            this.logSessionId = logSessionId2;
        }

        public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
            ArrayList arrayList = new ArrayList();
            if (!this.removeAudio) {
                arrayList.add(new ExoAssetLoaderAudioRenderer(this.decoderFactory, this.mediaClock, this.assetLoaderListener, this.logSessionId));
            }
            if (!this.removeVideo) {
                arrayList.add(new ExoAssetLoaderVideoRenderer(this.flattenForSlowMotion, this.decoderFactory, this.hdrMode, this.mediaClock, this.assetLoaderListener, this.logSessionId));
            }
            return (Renderer[]) arrayList.toArray(new Renderer[0]);
        }
    }

    /* access modifiers changed from: private */
    public static void maybeWarnUnsupportedTrackTypes(Tracks tracks) {
        for (int i2 = 0; i2 < tracks.getGroups().size(); i2++) {
            int type = ((Tracks.Group) tracks.getGroups().get(i2)).getType();
            if (!(type == 1 || type == 2)) {
                A.a.D(type, "Unsupported track type: ", "ExoPlayerAssetLoader");
            }
        }
    }

    public Y getDecoderNames() {
        X x9 = new X(4);
        String audioDecoderName = this.decoderFactory.getAudioDecoderName();
        if (audioDecoderName != null) {
            x9.b(1, audioDecoderName);
        }
        String videoDecoderName = this.decoderFactory.getVideoDecoderName();
        if (videoDecoderName != null) {
            x9.b(2, videoDecoderName);
        }
        return x9.a();
    }

    public int getProgress(ProgressHolder progressHolder) {
        if (this.progressState == 2) {
            long duration = this.player.getDuration();
            progressHolder.progress = Util.percentInt(Math.min(this.player.getCurrentPosition(), duration), duration);
        }
        return this.progressState;
    }

    public void release() {
        this.player.release();
        this.progressState = 0;
    }

    public void start() {
        this.player.setMediaItem(this.editedMediaItem.mediaItem);
        this.player.prepare();
        this.progressState = 1;
    }

    private ExoPlayerAssetLoader(Context context2, EditedMediaItem editedMediaItem2, MediaSource.Factory factory, Codec.DecoderFactory decoderFactory2, int i2, Looper looper, AssetLoader.Listener listener, Clock clock, TrackSelector.Factory factory2, LogSessionId logSessionId) {
        Context context3 = context2;
        EditedMediaItem editedMediaItem3 = editedMediaItem2;
        Codec.DecoderFactory decoderFactory3 = decoderFactory2;
        Clock clock2 = clock;
        this.context = context3;
        this.editedMediaItem = editedMediaItem3;
        CapturingDecoderFactory capturingDecoderFactory = new CapturingDecoderFactory(decoderFactory3);
        this.decoderFactory = capturingDecoderFactory;
        TrackSelector a7 = Factory.lambda$createAssetLoader$0(((a) factory2).d, context3);
        ExoPlayer.Builder usePlatformDiagnostics = new ExoPlayer.Builder(context3, new RenderersFactoryImpl(editedMediaItem3.removeAudio, editedMediaItem3.removeVideo, editedMediaItem3.flattenForSlowMotion, capturingDecoderFactory, i2, listener, logSessionId)).setMediaSourceFactory(factory).setTrackSelector(a7).setLoadControl(new DefaultLoadControl.Builder().setBufferDurationsMs(50000, 50000, 100, 200).build()).setLooper(looper).setUsePlatformDiagnostics(false);
        if (decoderFactory3 instanceof DefaultDecoderFactory) {
            usePlatformDiagnostics.experimentalSetDynamicSchedulingEnabled(((DefaultDecoderFactory) decoderFactory3).isDynamicSchedulingEnabled());
        }
        if (clock2 != Clock.DEFAULT) {
            usePlatformDiagnostics.setClock(clock2);
        }
        ExoPlayer build = usePlatformDiagnostics.build();
        this.player = build;
        build.addListener(new PlayerListener(listener));
        this.progressState = 0;
    }
}
