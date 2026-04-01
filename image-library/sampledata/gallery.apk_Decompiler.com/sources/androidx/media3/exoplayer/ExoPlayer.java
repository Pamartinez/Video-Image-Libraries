package androidx.media3.exoplayer;

import Ad.j;
import E2.h;
import E2.r;
import E2.t;
import I.c;
import I.d;
import android.content.Context;
import android.os.Looper;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DefaultLivePlaybackSpeedControl;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.ExtractorsFactory;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ExoPlayer extends Player {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AudioOffloadListener {
        void onSleepingForOffloadChanged(boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PreloadConfiguration {
        public static final PreloadConfiguration DEFAULT = new PreloadConfiguration(-9223372036854775807L);
        public final long targetPreloadDurationUs;

        public PreloadConfiguration(long j2) {
            this.targetPreloadDurationUs = j2;
        }
    }

    void release();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        h analyticsCollectorFunction;
        AudioAttributes audioAttributes;
        r bandwidthMeterSupplier;
        boolean buildCalled;
        Clock clock;
        final Context context;
        long detachSurfaceTimeoutMs;
        boolean deviceVolumeControlEnabled;
        boolean dynamicSchedulingEnabled;
        long foregroundModeTimeoutMs;
        boolean handleAudioBecomingNoisy;
        boolean handleAudioFocus;
        LivePlaybackSpeedControl livePlaybackSpeedControl;
        r loadControlSupplier;
        Looper looper;
        long maxSeekToPreviousPositionMs;
        r mediaSourceFactorySupplier;
        boolean pauseAtEndOfMediaItems;
        PlaybackLooperProvider playbackLooperProvider;
        String playerName;
        int priority;
        long releaseTimeoutMs;
        r renderersFactorySupplier;
        ScrubbingModeParameters scrubbingModeParameters;
        long seekBackIncrementMs;
        long seekForwardIncrementMs;
        SeekParameters seekParameters;
        boolean skipSilenceEnabled;
        SuitableOutputChecker suitableOutputChecker;
        boolean suppressPlaybackOnUnsuitableOutput;
        r trackSelectorSupplier;
        boolean useLazyPreparation;
        boolean usePlatformDiagnostics;
        int videoChangeFrameRateStrategy;
        int videoScalingMode;
        int wakeMode;

        public Builder(Context context2, RenderersFactory renderersFactory) {
            this(context2, new c(3, renderersFactory), new d(context2, 0));
            Assertions.checkNotNull(renderersFactory);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ TrackSelector lambda$new$14(Context context2) {
            return new DefaultTrackSelector(context2);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$3(Context context2) {
            return new DefaultMediaSourceFactory(context2, (ExtractorsFactory) new DefaultExtractorsFactory());
        }

        public ExoPlayer build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new ExoPlayerImpl(this, (Player) null);
        }

        public Builder experimentalSetDynamicSchedulingEnabled(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.dynamicSchedulingEnabled = z;
            return this;
        }

        public Builder setClock(Clock clock2) {
            Assertions.checkState(!this.buildCalled);
            this.clock = clock2;
            return this;
        }

        public Builder setLoadControl(LoadControl loadControl) {
            Assertions.checkState(!this.buildCalled);
            Assertions.checkNotNull(loadControl);
            this.loadControlSupplier = new c(0, loadControl);
            return this;
        }

        public Builder setLooper(Looper looper2) {
            Assertions.checkState(!this.buildCalled);
            Assertions.checkNotNull(looper2);
            this.looper = looper2;
            return this;
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            Assertions.checkState(!this.buildCalled);
            Assertions.checkNotNull(factory);
            this.mediaSourceFactorySupplier = new c(2, factory);
            return this;
        }

        public Builder setTrackSelector(TrackSelector trackSelector) {
            Assertions.checkState(!this.buildCalled);
            Assertions.checkNotNull(trackSelector);
            this.trackSelectorSupplier = new c(1, trackSelector);
            return this;
        }

        public Builder setUsePlatformDiagnostics(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.usePlatformDiagnostics = z;
            return this;
        }

        private Builder(Context context2, r rVar, r rVar2) {
            this(context2, rVar, rVar2, new d(context2, 1), new t(2), new d(context2, 2), new j(23));
        }

        private Builder(Context context2, r rVar, r rVar2, r rVar3, r rVar4, r rVar5, h hVar) {
            this.context = (Context) Assertions.checkNotNull(context2);
            this.renderersFactorySupplier = rVar;
            this.mediaSourceFactorySupplier = rVar2;
            this.trackSelectorSupplier = rVar3;
            this.loadControlSupplier = rVar4;
            this.bandwidthMeterSupplier = rVar5;
            this.analyticsCollectorFunction = hVar;
            this.looper = Util.getCurrentOrMainLooper();
            this.audioAttributes = AudioAttributes.DEFAULT;
            this.wakeMode = 0;
            this.videoScalingMode = 1;
            this.videoChangeFrameRateStrategy = 0;
            this.useLazyPreparation = true;
            this.seekParameters = SeekParameters.DEFAULT;
            this.seekBackIncrementMs = Def.SURFACE_CHANNEL_TIMEOUT_MILLIS;
            this.seekForwardIncrementMs = 15000;
            this.maxSeekToPreviousPositionMs = Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS;
            this.scrubbingModeParameters = ScrubbingModeParameters.DEFAULT;
            this.livePlaybackSpeedControl = new DefaultLivePlaybackSpeedControl.Builder().build();
            this.clock = Clock.DEFAULT;
            this.releaseTimeoutMs = 500;
            this.detachSurfaceTimeoutMs = 2000;
            this.usePlatformDiagnostics = true;
            this.playerName = "";
            this.priority = StatusCodes.UNDEFINED;
            this.suitableOutputChecker = new DefaultSuitableOutputChecker();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$2(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ LoadControl lambda$setLoadControl$19(LoadControl loadControl) {
            return loadControl;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$setMediaSourceFactory$17(MediaSource.Factory factory) {
            return factory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ TrackSelector lambda$setTrackSelector$18(TrackSelector trackSelector) {
            return trackSelector;
        }
    }
}
