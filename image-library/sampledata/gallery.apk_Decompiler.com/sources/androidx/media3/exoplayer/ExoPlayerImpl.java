package androidx.media3.exoplayer;

import F2.G;
import F2.U;
import F2.y0;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BasePlayer;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.AudioBecomingNoisyManager;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.ExoPlayerImplInternal;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.StreamVolumeManager;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.MediaMetricsListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.MaskingMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.spherical.CameraMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ExoPlayerImpl extends BasePlayer implements ExoPlayer {
    private final AnalyticsCollector analyticsCollector;
    private final Context applicationContext;
    private final Looper applicationLooper;
    private AudioAttributes audioAttributes;
    private final AudioBecomingNoisyManager audioBecomingNoisyManager;
    private final CopyOnWriteArraySet<ExoPlayer.AudioOffloadListener> audioOffloadListeners;
    private final BackgroundThreadStateHandler<Integer> audioSessionIdState;
    private Player.Commands availableCommands;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private final ComponentListener componentListener;
    private final ConditionVariable constructorFinished = new ConditionVariable();
    private CueGroup currentCueGroup;
    private final long detachSurfaceTimeoutMs;
    /* access modifiers changed from: private */
    public DeviceInfo deviceInfo;
    final TrackSelectorResult emptyTrackSelectorResult;
    private final FrameMetadataListener frameMetadataListener;
    private boolean hasNotifiedFullWrongThreadWarning;
    private final ExoPlayerImplInternal internalPlayer;
    private boolean isPriorityTaskManagerRegistered;
    /* access modifiers changed from: private */
    public final ListenerSet<Player.Listener> listeners;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private final long maxSeekToPreviousPositionMs;
    private MediaMetadata mediaMetadata;
    private final MediaSource.Factory mediaSourceFactory;
    private final List<MediaSourceHolderSnapshot> mediaSourceHolderSnapshots;
    private Surface ownedSurface;
    private boolean pauseAtEndOfMediaItems;
    private boolean pendingDiscontinuity;
    private int pendingDiscontinuityReason;
    private int pendingOperationAcks;
    private final Timeline.Period period;
    final Player.Commands permanentAvailableCommands;
    private PlaybackInfo playbackInfo;
    private final HandlerWrapper playbackInfoUpdateHandler;
    private final ExoPlayerImplInternal.PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private boolean playerReleased;
    private MediaMetadata playlistMetadata;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private int priority;
    private final Renderer[] renderers;
    private int repeatMode;
    private boolean scrubbingModeEnabled;
    private ScrubbingModeParameters scrubbingModeParameters;
    private final Renderer[] secondaryRenderers;
    private final long seekBackIncrementMs;
    private final long seekForwardIncrementMs;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private ShuffleOrder shuffleOrder;
    private boolean skipSilenceEnabled;
    private MediaMetadata staticAndDynamicMediaMetadata;
    /* access modifiers changed from: private */
    public final StreamVolumeManager streamVolumeManager;
    private final SuitableOutputChecker suitableOutputChecker;
    private SurfaceHolder surfaceHolder;
    /* access modifiers changed from: private */
    public boolean surfaceHolderSurfaceIsVideoOutput;
    private Size surfaceSize;
    private TextureView textureView;
    private boolean throwsWhenUsingWrongThread;
    private final TrackSelector trackSelector;
    private final boolean useLazyPreparation;
    private int videoChangeFrameRateStrategy;
    private Object videoOutput;
    private int videoScalingMode;
    private VideoSize videoSize;
    private float volume;
    private final WakeLockManager wakeLockManager;
    private final WifiLockManager wifiLockManager;
    private final Player wrappingPlayer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api31 {
        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$registerMediaMetricsListener$0(Context context, boolean z, ExoPlayerImpl exoPlayerImpl, PlayerId playerId) {
            MediaMetricsListener create = MediaMetricsListener.create(context);
            if (create == null) {
                Log.w("ExoPlayerImpl", "MediaMetricsService unavailable.");
                return;
            }
            if (z) {
                exoPlayerImpl.addAnalyticsListener(create);
            }
            playerId.setLogSessionId(create.getLogSessionId());
        }

        public static void registerMediaMetricsListener(Context context, ExoPlayerImpl exoPlayerImpl, boolean z, PlayerId playerId) {
            exoPlayerImpl.getClock().createHandler(exoPlayerImpl.getPlaybackLooper(), (Handler.Callback) null).post(new o(context, z, exoPlayerImpl, playerId));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FrameMetadataListener implements VideoFrameMetadataListener, CameraMotionListener, PlayerMessage.Target {
        private CameraMotionListener cameraMotionListener;
        private CameraMotionListener internalCameraMotionListener;
        private VideoFrameMetadataListener internalVideoFrameMetadataListener;
        private VideoFrameMetadataListener videoFrameMetadataListener;

        private FrameMetadataListener() {
        }

        public void handleMessage(int i2, Object obj) {
            if (i2 == 7) {
                this.videoFrameMetadataListener = (VideoFrameMetadataListener) obj;
            } else if (i2 == 8) {
                this.cameraMotionListener = (CameraMotionListener) obj;
            } else if (i2 == 10000) {
                if (obj == null) {
                    this.internalVideoFrameMetadataListener = null;
                    this.internalCameraMotionListener = null;
                    return;
                }
                throw new ClassCastException();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaSourceHolderSnapshot implements MediaSourceInfoHolder {
        private final MediaSource mediaSource;
        private Timeline timeline;
        private final Object uid;

        public MediaSourceHolderSnapshot(Object obj, MaskingMediaSource maskingMediaSource) {
            this.uid = obj;
            this.mediaSource = maskingMediaSource;
            this.timeline = maskingMediaSource.getTimeline();
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        public Object getUid() {
            return this.uid;
        }

        public void updateTimeline(Timeline timeline2) {
            this.timeline = timeline2;
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.exoplayer");
    }

    /* JADX WARNING: type inference failed for: r43v0, types: [androidx.media3.common.Player] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExoPlayerImpl(androidx.media3.exoplayer.ExoPlayer.Builder r42, androidx.media3.common.Player r43) {
        /*
            r41 = this;
            r1 = r41
            r0 = r42
            java.lang.String r2 = " [AndroidXMedia3/1.8.0] ["
            java.lang.String r3 = "Init "
            r1.<init>()
            androidx.media3.common.util.ConditionVariable r4 = new androidx.media3.common.util.ConditionVariable
            r4.<init>()
            r1.constructorFinished = r4
            java.lang.String r4 = "ExoPlayerImpl"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b6 }
            r5.<init>(r3)     // Catch:{ all -> 0x00b6 }
            int r3 = java.lang.System.identityHashCode(r1)     // Catch:{ all -> 0x00b6 }
            java.lang.String r3 = java.lang.Integer.toHexString(r3)     // Catch:{ all -> 0x00b6 }
            r5.append(r3)     // Catch:{ all -> 0x00b6 }
            r5.append(r2)     // Catch:{ all -> 0x00b6 }
            java.lang.String r2 = androidx.media3.common.util.Util.DEVICE_DEBUG_INFO     // Catch:{ all -> 0x00b6 }
            r5.append(r2)     // Catch:{ all -> 0x00b6 }
            java.lang.String r2 = "]"
            r5.append(r2)     // Catch:{ all -> 0x00b6 }
            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.util.Log.i(r4, r2)     // Catch:{ all -> 0x00b6 }
            android.content.Context r2 = r0.context     // Catch:{ all -> 0x00b6 }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x00b6 }
            r1.applicationContext = r2     // Catch:{ all -> 0x00b6 }
            E2.h r2 = r0.analyticsCollectorFunction     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.util.Clock r3 = r0.clock     // Catch:{ all -> 0x00b6 }
            java.lang.Object r2 = r2.apply(r3)     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.analytics.AnalyticsCollector r2 = (androidx.media3.exoplayer.analytics.AnalyticsCollector) r2     // Catch:{ all -> 0x00b6 }
            r1.analyticsCollector = r2     // Catch:{ all -> 0x00b6 }
            int r2 = r0.priority     // Catch:{ all -> 0x00b6 }
            r1.priority = r2     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.AudioAttributes r2 = r0.audioAttributes     // Catch:{ all -> 0x00b6 }
            r1.audioAttributes = r2     // Catch:{ all -> 0x00b6 }
            int r2 = r0.videoScalingMode     // Catch:{ all -> 0x00b6 }
            r1.videoScalingMode = r2     // Catch:{ all -> 0x00b6 }
            int r2 = r0.videoChangeFrameRateStrategy     // Catch:{ all -> 0x00b6 }
            r1.videoChangeFrameRateStrategy = r2     // Catch:{ all -> 0x00b6 }
            boolean r2 = r0.skipSilenceEnabled     // Catch:{ all -> 0x00b6 }
            r1.skipSilenceEnabled = r2     // Catch:{ all -> 0x00b6 }
            long r2 = r0.detachSurfaceTimeoutMs     // Catch:{ all -> 0x00b6 }
            r1.detachSurfaceTimeoutMs = r2     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayerImpl$ComponentListener r6 = new androidx.media3.exoplayer.ExoPlayerImpl$ComponentListener     // Catch:{ all -> 0x00b6 }
            r2 = 0
            r6.<init>()     // Catch:{ all -> 0x00b6 }
            r1.componentListener = r6     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayerImpl$FrameMetadataListener r3 = new androidx.media3.exoplayer.ExoPlayerImpl$FrameMetadataListener     // Catch:{ all -> 0x00b6 }
            r3.<init>()     // Catch:{ all -> 0x00b6 }
            r1.frameMetadataListener = r3     // Catch:{ all -> 0x00b6 }
            android.os.Handler r5 = new android.os.Handler     // Catch:{ all -> 0x00b6 }
            android.os.Looper r3 = r0.looper     // Catch:{ all -> 0x00b6 }
            r5.<init>(r3)     // Catch:{ all -> 0x00b6 }
            E2.r r3 = r0.renderersFactorySupplier     // Catch:{ all -> 0x00b6 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x00b6 }
            r4 = r3
            androidx.media3.exoplayer.RenderersFactory r4 = (androidx.media3.exoplayer.RenderersFactory) r4     // Catch:{ all -> 0x00b6 }
            r7 = r6
            r8 = r6
            r9 = r6
            androidx.media3.exoplayer.Renderer[] r3 = r4.createRenderers(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00b6 }
            r1.renderers = r3     // Catch:{ all -> 0x00b6 }
            int r6 = r3.length     // Catch:{ all -> 0x00b6 }
            r14 = 0
            if (r6 <= 0) goto L_0x0092
            r6 = 1
            goto L_0x0093
        L_0x0092:
            r6 = r14
        L_0x0093:
            androidx.media3.common.util.Assertions.checkState(r6)     // Catch:{ all -> 0x00b6 }
            int r3 = r3.length     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.Renderer[] r3 = new androidx.media3.exoplayer.Renderer[r3]     // Catch:{ all -> 0x00b6 }
            r1.secondaryRenderers = r3     // Catch:{ all -> 0x00b6 }
            r3 = r14
        L_0x009c:
            androidx.media3.exoplayer.Renderer[] r6 = r1.secondaryRenderers     // Catch:{ all -> 0x00b6 }
            int r7 = r6.length     // Catch:{ all -> 0x00b6 }
            if (r3 >= r7) goto L_0x00b9
            androidx.media3.exoplayer.Renderer[] r7 = r1.renderers     // Catch:{ all -> 0x00b6 }
            r8 = r7[r3]     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayerImpl$ComponentListener r10 = r1.componentListener     // Catch:{ all -> 0x00b6 }
            r11 = r10
            r12 = r10
            r13 = r10
            r7 = r4
            r9 = r5
            androidx.media3.exoplayer.Renderer r4 = r7.createSecondaryRenderer(r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x00b6 }
            r6[r3] = r4     // Catch:{ all -> 0x00b6 }
            int r3 = r3 + 1
            r4 = r7
            goto L_0x009c
        L_0x00b6:
            r0 = move-exception
            goto L_0x0368
        L_0x00b9:
            E2.r r3 = r0.trackSelectorSupplier     // Catch:{ all -> 0x00b6 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.trackselection.TrackSelector r3 = (androidx.media3.exoplayer.trackselection.TrackSelector) r3     // Catch:{ all -> 0x00b6 }
            r1.trackSelector = r3     // Catch:{ all -> 0x00b6 }
            E2.r r4 = r0.mediaSourceFactorySupplier     // Catch:{ all -> 0x00b6 }
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.source.MediaSource$Factory r4 = (androidx.media3.exoplayer.source.MediaSource.Factory) r4     // Catch:{ all -> 0x00b6 }
            r1.mediaSourceFactory = r4     // Catch:{ all -> 0x00b6 }
            E2.r r4 = r0.bandwidthMeterSupplier     // Catch:{ all -> 0x00b6 }
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.upstream.BandwidthMeter r4 = (androidx.media3.exoplayer.upstream.BandwidthMeter) r4     // Catch:{ all -> 0x00b6 }
            r1.bandwidthMeter = r4     // Catch:{ all -> 0x00b6 }
            boolean r5 = r0.useLazyPreparation     // Catch:{ all -> 0x00b6 }
            r1.useLazyPreparation = r5     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.SeekParameters r5 = r0.seekParameters     // Catch:{ all -> 0x00b6 }
            r1.seekParameters = r5     // Catch:{ all -> 0x00b6 }
            long r5 = r0.seekBackIncrementMs     // Catch:{ all -> 0x00b6 }
            r1.seekBackIncrementMs = r5     // Catch:{ all -> 0x00b6 }
            long r5 = r0.seekForwardIncrementMs     // Catch:{ all -> 0x00b6 }
            r1.seekForwardIncrementMs = r5     // Catch:{ all -> 0x00b6 }
            long r5 = r0.maxSeekToPreviousPositionMs     // Catch:{ all -> 0x00b6 }
            r1.maxSeekToPreviousPositionMs = r5     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ScrubbingModeParameters r5 = r0.scrubbingModeParameters     // Catch:{ all -> 0x00b6 }
            r1.scrubbingModeParameters = r5     // Catch:{ all -> 0x00b6 }
            boolean r5 = r0.pauseAtEndOfMediaItems     // Catch:{ all -> 0x00b6 }
            r1.pauseAtEndOfMediaItems = r5     // Catch:{ all -> 0x00b6 }
            android.os.Looper r9 = r0.looper     // Catch:{ all -> 0x00b6 }
            r1.applicationLooper = r9     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.util.Clock r10 = r0.clock     // Catch:{ all -> 0x00b6 }
            r1.clock = r10     // Catch:{ all -> 0x00b6 }
            if (r43 != 0) goto L_0x00ff
            r5 = r1
            goto L_0x0101
        L_0x00ff:
            r5 = r43
        L_0x0101:
            r1.wrappingPlayer = r5     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.util.ListenerSet r6 = new androidx.media3.common.util.ListenerSet     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.k r7 = new androidx.media3.exoplayer.k     // Catch:{ all -> 0x00b6 }
            r7.<init>(r1)     // Catch:{ all -> 0x00b6 }
            r6.<init>(r9, r10, r7)     // Catch:{ all -> 0x00b6 }
            r1.listeners = r6     // Catch:{ all -> 0x00b6 }
            java.util.concurrent.CopyOnWriteArraySet r6 = new java.util.concurrent.CopyOnWriteArraySet     // Catch:{ all -> 0x00b6 }
            r6.<init>()     // Catch:{ all -> 0x00b6 }
            r1.audioOffloadListeners = r6     // Catch:{ all -> 0x00b6 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x00b6 }
            r6.<init>()     // Catch:{ all -> 0x00b6 }
            r1.mediaSourceHolderSnapshots = r6     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.source.ShuffleOrder$DefaultShuffleOrder r6 = new androidx.media3.exoplayer.source.ShuffleOrder$DefaultShuffleOrder     // Catch:{ all -> 0x00b6 }
            r6.<init>(r14)     // Catch:{ all -> 0x00b6 }
            r1.shuffleOrder = r6     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayer$PreloadConfiguration r6 = androidx.media3.exoplayer.ExoPlayer.PreloadConfiguration.DEFAULT     // Catch:{ all -> 0x00b6 }
            r1.preloadConfiguration = r6     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r6 = new androidx.media3.exoplayer.trackselection.TrackSelectorResult     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.Renderer[] r7 = r1.renderers     // Catch:{ all -> 0x00b6 }
            int r8 = r7.length     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.RendererConfiguration[] r8 = new androidx.media3.exoplayer.RendererConfiguration[r8]     // Catch:{ all -> 0x00b6 }
            int r7 = r7.length     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.trackselection.ExoTrackSelection[] r7 = new androidx.media3.exoplayer.trackselection.ExoTrackSelection[r7]     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.Tracks r11 = androidx.media3.common.Tracks.EMPTY     // Catch:{ all -> 0x00b6 }
            r6.<init>(r8, r7, r11, r2)     // Catch:{ all -> 0x00b6 }
            r1.emptyTrackSelectorResult = r6     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.Timeline$Period r7 = new androidx.media3.common.Timeline$Period     // Catch:{ all -> 0x00b6 }
            r7.<init>()     // Catch:{ all -> 0x00b6 }
            r1.period = r7     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.Player$Commands$Builder r7 = new androidx.media3.common.Player$Commands$Builder     // Catch:{ all -> 0x00b6 }
            r7.<init>()     // Catch:{ all -> 0x00b6 }
            r8 = 20
            int[] r8 = new int[r8]     // Catch:{ all -> 0x00b6 }
            r8 = {1, 2, 3, 13, 14, 15, 16, 17, 18, 19, 31, 20, 30, 21, 35, 22, 24, 27, 28, 32} // fill-array     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.Player$Commands$Builder r7 = r7.addAll((int[]) r8)     // Catch:{ all -> 0x00b6 }
            boolean r8 = r3.isSetParametersSupported()     // Catch:{ all -> 0x00b6 }
            r11 = 29
            androidx.media3.common.Player$Commands$Builder r7 = r7.addIf(r11, r8)     // Catch:{ all -> 0x00b6 }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x00b6 }
            r11 = 23
            androidx.media3.common.Player$Commands$Builder r7 = r7.addIf(r11, r8)     // Catch:{ all -> 0x00b6 }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x00b6 }
            r11 = 25
            androidx.media3.common.Player$Commands$Builder r7 = r7.addIf(r11, r8)     // Catch:{ all -> 0x00b6 }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x00b6 }
            r11 = 33
            androidx.media3.common.Player$Commands$Builder r7 = r7.addIf(r11, r8)     // Catch:{ all -> 0x00b6 }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x00b6 }
            r11 = 26
            androidx.media3.common.Player$Commands$Builder r7 = r7.addIf(r11, r8)     // Catch:{ all -> 0x00b6 }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x00b6 }
            r11 = 34
            androidx.media3.common.Player$Commands$Builder r7 = r7.addIf(r11, r8)     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.Player$Commands r7 = r7.build()     // Catch:{ all -> 0x00b6 }
            r1.permanentAvailableCommands = r7     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.Player$Commands$Builder r8 = new androidx.media3.common.Player$Commands$Builder     // Catch:{ all -> 0x00b6 }
            r8.<init>()     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.Player$Commands$Builder r7 = r8.addAll((androidx.media3.common.Player.Commands) r7)     // Catch:{ all -> 0x00b6 }
            r13 = 4
            androidx.media3.common.Player$Commands$Builder r7 = r7.add(r13)     // Catch:{ all -> 0x00b6 }
            r8 = 10
            androidx.media3.common.Player$Commands$Builder r7 = r7.add(r8)     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.Player$Commands r7 = r7.build()     // Catch:{ all -> 0x00b6 }
            r1.availableCommands = r7     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.util.HandlerWrapper r7 = r10.createHandler(r9, r2)     // Catch:{ all -> 0x00b6 }
            r1.playbackInfoUpdateHandler = r7     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.k r7 = new androidx.media3.exoplayer.k     // Catch:{ all -> 0x00b6 }
            r7.<init>(r1)     // Catch:{ all -> 0x00b6 }
            r1.playbackInfoUpdateListener = r7     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.PlaybackInfo r8 = androidx.media3.exoplayer.PlaybackInfo.createDummy(r6)     // Catch:{ all -> 0x00b6 }
            r1.playbackInfo = r8     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.analytics.AnalyticsCollector r8 = r1.analyticsCollector     // Catch:{ all -> 0x00b6 }
            r8.setPlayer(r5, r9)     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.analytics.PlayerId r5 = new androidx.media3.exoplayer.analytics.PlayerId     // Catch:{ all -> 0x00b6 }
            java.lang.String r8 = r0.playerName     // Catch:{ all -> 0x00b6 }
            r5.<init>(r8)     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayerImplInternal r16 = new androidx.media3.exoplayer.ExoPlayerImplInternal     // Catch:{ all -> 0x00b6 }
            android.content.Context r8 = r1.applicationContext     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.Renderer[] r11 = r1.renderers     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.Renderer[] r12 = r1.secondaryRenderers     // Catch:{ all -> 0x00b6 }
            E2.r r13 = r0.loadControlSupplier     // Catch:{ all -> 0x00b6 }
            java.lang.Object r13 = r13.get()     // Catch:{ all -> 0x00b6 }
            r22 = r13
            androidx.media3.exoplayer.LoadControl r22 = (androidx.media3.exoplayer.LoadControl) r22     // Catch:{ all -> 0x00b6 }
            int r13 = r1.repeatMode     // Catch:{ all -> 0x00b6 }
            boolean r2 = r1.shuffleModeEnabled     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.analytics.AnalyticsCollector r15 = r1.analyticsCollector     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.SeekParameters r14 = r1.seekParameters     // Catch:{ all -> 0x00b6 }
            r25 = r2
            androidx.media3.exoplayer.LivePlaybackSpeedControl r2 = r0.livePlaybackSpeedControl     // Catch:{ all -> 0x00b6 }
            r28 = r2
            r20 = r3
            long r2 = r0.releaseTimeoutMs     // Catch:{ all -> 0x00b6 }
            r29 = r2
            boolean r2 = r1.pauseAtEndOfMediaItems     // Catch:{ all -> 0x00b6 }
            boolean r3 = r0.dynamicSchedulingEnabled     // Catch:{ all -> 0x00b6 }
            r31 = r2
            androidx.media3.exoplayer.PlaybackLooperProvider r2 = r0.playbackLooperProvider     // Catch:{ all -> 0x00b6 }
            r37 = r2
            androidx.media3.exoplayer.ExoPlayer$PreloadConfiguration r2 = r1.preloadConfiguration     // Catch:{ all -> 0x00b6 }
            r38 = r2
            androidx.media3.exoplayer.ExoPlayerImpl$FrameMetadataListener r2 = r1.frameMetadataListener     // Catch:{ all -> 0x00b6 }
            r39 = r2
            r32 = r3
            r23 = r4
            r36 = r5
            r21 = r6
            r35 = r7
            r17 = r8
            r33 = r9
            r34 = r10
            r18 = r11
            r19 = r12
            r24 = r13
            r27 = r14
            r26 = r15
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r31, r32, r33, r34, r35, r36, r37, r38, r39)     // Catch:{ all -> 0x00b6 }
            r3 = r16
            r4 = r23
            r9 = r33
            r2 = r36
            r1.internalPlayer = r3     // Catch:{ all -> 0x00b6 }
            android.os.Looper r10 = r3.getPlaybackLooper()     // Catch:{ all -> 0x00b6 }
            r5 = 1065353216(0x3f800000, float:1.0)
            r1.volume = r5     // Catch:{ all -> 0x00b6 }
            r5 = 0
            r1.repeatMode = r5     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.MediaMetadata r5 = androidx.media3.common.MediaMetadata.EMPTY     // Catch:{ all -> 0x00b6 }
            r1.mediaMetadata = r5     // Catch:{ all -> 0x00b6 }
            r1.playlistMetadata = r5     // Catch:{ all -> 0x00b6 }
            r1.staticAndDynamicMediaMetadata = r5     // Catch:{ all -> 0x00b6 }
            r5 = -1
            r1.maskingWindowIndex = r5     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.text.CueGroup r5 = androidx.media3.common.text.CueGroup.EMPTY_TIME_ZERO     // Catch:{ all -> 0x00b6 }
            r1.currentCueGroup = r5     // Catch:{ all -> 0x00b6 }
            r5 = 1
            r1.throwsWhenUsingWrongThread = r5     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.analytics.AnalyticsCollector r5 = r1.analyticsCollector     // Catch:{ all -> 0x00b6 }
            r1.addListener(r5)     // Catch:{ all -> 0x00b6 }
            android.os.Handler r5 = new android.os.Handler     // Catch:{ all -> 0x00b6 }
            r5.<init>(r9)     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.analytics.AnalyticsCollector r6 = r1.analyticsCollector     // Catch:{ all -> 0x00b6 }
            r4.addEventListener(r5, r6)     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayerImpl$ComponentListener r4 = r1.componentListener     // Catch:{ all -> 0x00b6 }
            r1.addAudioOffloadListener(r4)     // Catch:{ all -> 0x00b6 }
            long r4 = r0.foregroundModeTimeoutMs     // Catch:{ all -> 0x00b6 }
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x025c
            r3.experimentalSetForegroundModeTimeoutMs(r4)     // Catch:{ all -> 0x00b6 }
        L_0x025c:
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00b6 }
            r5 = 31
            if (r4 < r5) goto L_0x0269
            android.content.Context r4 = r1.applicationContext     // Catch:{ all -> 0x00b6 }
            boolean r5 = r0.usePlatformDiagnostics     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayerImpl.Api31.registerMediaMetricsListener(r4, r1, r5, r2)     // Catch:{ all -> 0x00b6 }
        L_0x0269:
            androidx.media3.common.util.BackgroundThreadStateHandler r6 = new androidx.media3.common.util.BackgroundThreadStateHandler     // Catch:{ all -> 0x00b6 }
            r40 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r40)     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.k r11 = new androidx.media3.exoplayer.k     // Catch:{ all -> 0x00b6 }
            r11.<init>(r1)     // Catch:{ all -> 0x00b6 }
            r8 = r10
            r10 = r34
            r6.<init>(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x00b6 }
            r34 = r10
            r10 = r8
            r1.audioSessionIdState = r6     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.b r2 = new androidx.media3.exoplayer.b     // Catch:{ all -> 0x00b6 }
            r4 = 3
            r2.<init>(r4, r1)     // Catch:{ all -> 0x00b6 }
            r6.runInBackground(r2)     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.AudioBecomingNoisyManager r16 = new androidx.media3.exoplayer.AudioBecomingNoisyManager     // Catch:{ all -> 0x00b6 }
            android.content.Context r2 = r0.context     // Catch:{ all -> 0x00b6 }
            android.os.Looper r5 = r0.looper     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayerImpl$ComponentListener r6 = r1.componentListener     // Catch:{ all -> 0x00b6 }
            r17 = r2
            r19 = r5
            r20 = r6
            r18 = r10
            r21 = r34
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ all -> 0x00b6 }
            r2 = r16
            r10 = r18
            r34 = r21
            r1.audioBecomingNoisyManager = r2     // Catch:{ all -> 0x00b6 }
            boolean r5 = r0.handleAudioBecomingNoisy     // Catch:{ all -> 0x00b6 }
            r2.setEnabled(r5)     // Catch:{ all -> 0x00b6 }
            boolean r2 = r0.suppressPlaybackOnUnsuitableOutput     // Catch:{ all -> 0x00b6 }
            if (r2 == 0) goto L_0x02c3
            androidx.media3.exoplayer.SuitableOutputChecker r6 = r0.suitableOutputChecker     // Catch:{ all -> 0x00b6 }
            r1.suitableOutputChecker = r6     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.k r7 = new androidx.media3.exoplayer.k     // Catch:{ all -> 0x00b6 }
            r7.<init>(r1)     // Catch:{ all -> 0x00b6 }
            android.content.Context r8 = r1.applicationContext     // Catch:{ all -> 0x00b6 }
            r11 = r34
            r6.enable(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x00b6 }
            r34 = r11
            goto L_0x02c6
        L_0x02c3:
            r2 = 0
            r1.suitableOutputChecker = r2     // Catch:{ all -> 0x00b6 }
        L_0x02c6:
            boolean r2 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x00b6 }
            if (r2 == 0) goto L_0x02e2
            androidx.media3.exoplayer.StreamVolumeManager r6 = new androidx.media3.exoplayer.StreamVolumeManager     // Catch:{ all -> 0x00b6 }
            android.content.Context r7 = r0.context     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayerImpl$ComponentListener r8 = r1.componentListener     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.AudioAttributes r2 = r1.audioAttributes     // Catch:{ all -> 0x00b6 }
            int r2 = r2.getStreamType()     // Catch:{ all -> 0x00b6 }
            r11 = r9
            r12 = r34
            r9 = r2
            r6.<init>(r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x00b6 }
            r8 = r10
            r10 = r12
            r1.streamVolumeManager = r6     // Catch:{ all -> 0x00b6 }
            goto L_0x02e8
        L_0x02e2:
            r8 = r10
            r10 = r34
            r2 = 0
            r1.streamVolumeManager = r2     // Catch:{ all -> 0x00b6 }
        L_0x02e8:
            androidx.media3.exoplayer.WakeLockManager r2 = new androidx.media3.exoplayer.WakeLockManager     // Catch:{ all -> 0x00b6 }
            android.content.Context r5 = r0.context     // Catch:{ all -> 0x00b6 }
            r2.<init>(r5, r8, r10)     // Catch:{ all -> 0x00b6 }
            r1.wakeLockManager = r2     // Catch:{ all -> 0x00b6 }
            int r5 = r0.wakeMode     // Catch:{ all -> 0x00b6 }
            if (r5 == 0) goto L_0x02f7
            r5 = 1
            goto L_0x02f9
        L_0x02f7:
            r5 = r40
        L_0x02f9:
            r2.setEnabled(r5)     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.WifiLockManager r2 = new androidx.media3.exoplayer.WifiLockManager     // Catch:{ all -> 0x00b6 }
            android.content.Context r5 = r0.context     // Catch:{ all -> 0x00b6 }
            r2.<init>(r5, r8, r10)     // Catch:{ all -> 0x00b6 }
            r1.wifiLockManager = r2     // Catch:{ all -> 0x00b6 }
            int r5 = r0.wakeMode     // Catch:{ all -> 0x00b6 }
            r6 = 2
            if (r5 != r6) goto L_0x030c
            r14 = 1
            goto L_0x030e
        L_0x030c:
            r14 = r40
        L_0x030e:
            r2.setEnabled(r14)     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.DeviceInfo r2 = androidx.media3.common.DeviceInfo.UNKNOWN     // Catch:{ all -> 0x00b6 }
            r1.deviceInfo = r2     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.VideoSize r2 = androidx.media3.common.VideoSize.UNKNOWN     // Catch:{ all -> 0x00b6 }
            r1.videoSize = r2     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.util.Size r2 = androidx.media3.common.util.Size.UNKNOWN     // Catch:{ all -> 0x00b6 }
            r1.surfaceSize = r2     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ScrubbingModeParameters r2 = r1.scrubbingModeParameters     // Catch:{ all -> 0x00b6 }
            r3.setScrubbingModeParameters(r2)     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.AudioAttributes r2 = r1.audioAttributes     // Catch:{ all -> 0x00b6 }
            boolean r0 = r0.handleAudioFocus     // Catch:{ all -> 0x00b6 }
            r3.setAudioAttributes(r2, r0)     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.AudioAttributes r0 = r1.audioAttributes     // Catch:{ all -> 0x00b6 }
            r5 = 1
            r1.sendRendererMessage(r5, r4, r0)     // Catch:{ all -> 0x00b6 }
            int r0 = r1.videoScalingMode     // Catch:{ all -> 0x00b6 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00b6 }
            r2 = 4
            r1.sendRendererMessage(r6, r2, r0)     // Catch:{ all -> 0x00b6 }
            int r0 = r1.videoChangeFrameRateStrategy     // Catch:{ all -> 0x00b6 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00b6 }
            r2 = 5
            r1.sendRendererMessage(r6, r2, r0)     // Catch:{ all -> 0x00b6 }
            boolean r0 = r1.skipSilenceEnabled     // Catch:{ all -> 0x00b6 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x00b6 }
            r2 = 9
            r5 = 1
            r1.sendRendererMessage(r5, r2, r0)     // Catch:{ all -> 0x00b6 }
            androidx.media3.exoplayer.ExoPlayerImpl$FrameMetadataListener r0 = r1.frameMetadataListener     // Catch:{ all -> 0x00b6 }
            r2 = 6
            r3 = 8
            r1.sendRendererMessage(r2, r3, r0)     // Catch:{ all -> 0x00b6 }
            int r0 = r1.priority     // Catch:{ all -> 0x00b6 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00b6 }
            r2 = 16
            r1.sendRendererMessage(r2, r0)     // Catch:{ all -> 0x00b6 }
            androidx.media3.common.util.ConditionVariable r0 = r1.constructorFinished
            r0.open()
            return
        L_0x0368:
            androidx.media3.common.util.ConditionVariable r1 = r1.constructorFinished
            r1.open()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImpl.<init>(androidx.media3.exoplayer.ExoPlayer$Builder, androidx.media3.common.Player):void");
    }

    private List<MediaSourceList.MediaSourceHolder> addMediaSourceHolders(int i2, List<MediaSource> list) {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < list.size(); i7++) {
            MediaSourceList.MediaSourceHolder mediaSourceHolder = new MediaSourceList.MediaSourceHolder(list.get(i7), this.useLazyPreparation);
            arrayList.add(mediaSourceHolder);
            this.mediaSourceHolderSnapshots.add(i7 + i2, new MediaSourceHolderSnapshot(mediaSourceHolder.uid, mediaSourceHolder.mediaSource));
        }
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(i2, arrayList.size());
        return arrayList;
    }

    private MediaMetadata buildUpdatedMediaMetadata() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return this.staticAndDynamicMediaMetadata;
        }
        return this.staticAndDynamicMediaMetadata.buildUpon().populate(currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem.mediaMetadata).build();
    }

    private int computePlaybackSuppressionReason(boolean z) {
        if (this.scrubbingModeEnabled) {
            return 4;
        }
        SuitableOutputChecker suitableOutputChecker2 = this.suitableOutputChecker;
        if (suitableOutputChecker2 != null && !suitableOutputChecker2.isSelectedOutputSuitableForPlayback()) {
            return 3;
        }
        if (this.playbackInfo.playbackSuppressionReason != 1 || z) {
            return 0;
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public static DeviceInfo createDeviceInfo(StreamVolumeManager streamVolumeManager2) {
        int i2;
        int i7 = 0;
        DeviceInfo.Builder builder = new DeviceInfo.Builder(0);
        if (streamVolumeManager2 != null) {
            i2 = streamVolumeManager2.getMinVolume();
        } else {
            i2 = 0;
        }
        DeviceInfo.Builder minVolume = builder.setMinVolume(i2);
        if (streamVolumeManager2 != null) {
            i7 = streamVolumeManager2.getMaxVolume();
        }
        return minVolume.setMaxVolume(i7).build();
    }

    private Timeline createMaskingTimeline() {
        return new PlaylistTimeline(this.mediaSourceHolderSnapshots, this.shuffleOrder);
    }

    private List<MediaSource> createMediaSources(List<MediaItem> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(this.mediaSourceFactory.createMediaSource(list.get(i2)));
        }
        return arrayList;
    }

    private PlayerMessage createMessageInternal(PlayerMessage.Target target) {
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(this.playbackInfo);
        ExoPlayerImplInternal exoPlayerImplInternal = this.internalPlayer;
        Timeline timeline = this.playbackInfo.timeline;
        if (currentWindowIndexInternal == -1) {
            currentWindowIndexInternal = 0;
        }
        return new PlayerMessage(exoPlayerImplInternal, target, timeline, currentWindowIndexInternal, this.clock, exoPlayerImplInternal.getPlaybackLooper());
    }

    private Pair<Boolean, Integer> evaluateMediaItemTransitionReason(PlaybackInfo playbackInfo2, PlaybackInfo playbackInfo3, boolean z, int i2, boolean z3, boolean z7) {
        Timeline timeline = playbackInfo3.timeline;
        Timeline timeline2 = playbackInfo2.timeline;
        if (timeline2.isEmpty() && timeline.isEmpty()) {
            return new Pair<>(Boolean.FALSE, -1);
        }
        int i7 = 3;
        if (timeline2.isEmpty() != timeline.isEmpty()) {
            return new Pair<>(Boolean.TRUE, 3);
        }
        if (!timeline.getWindow(timeline.getPeriodByUid(playbackInfo3.periodId.periodUid, this.period).windowIndex, this.window).uid.equals(timeline2.getWindow(timeline2.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period).windowIndex, this.window).uid)) {
            if (z && i2 == 0) {
                i7 = 1;
            } else if (z && i2 == 1) {
                i7 = 2;
            } else if (!z3) {
                throw new IllegalStateException();
            }
            return new Pair<>(Boolean.TRUE, Integer.valueOf(i7));
        } else if (z && i2 == 0 && playbackInfo3.periodId.windowSequenceNumber < playbackInfo2.periodId.windowSequenceNumber) {
            return new Pair<>(Boolean.TRUE, 0);
        } else {
            if (!z || i2 != 1 || !z7) {
                return new Pair<>(Boolean.FALSE, -1);
            }
            return new Pair<>(Boolean.TRUE, 2);
        }
    }

    private long getContentPositionInternal(PlaybackInfo playbackInfo2) {
        if (!playbackInfo2.periodId.isAd()) {
            return Util.usToMs(getCurrentPositionUsInternal(playbackInfo2));
        }
        playbackInfo2.timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period);
        if (playbackInfo2.requestedContentPositionUs == -9223372036854775807L) {
            return playbackInfo2.timeline.getWindow(getCurrentWindowIndexInternal(playbackInfo2), this.window).getDefaultPositionMs();
        }
        return Util.usToMs(playbackInfo2.requestedContentPositionUs) + this.period.getPositionInWindowMs();
    }

    private long getCurrentPositionUsInternal(PlaybackInfo playbackInfo2) {
        long j2;
        if (playbackInfo2.timeline.isEmpty()) {
            return Util.msToUs(this.maskingWindowPositionMs);
        }
        if (playbackInfo2.sleepingForOffload) {
            j2 = playbackInfo2.getEstimatedPositionUs();
        } else {
            j2 = playbackInfo2.positionUs;
        }
        if (playbackInfo2.periodId.isAd()) {
            return j2;
        }
        return periodPositionUsToWindowPositionUs(playbackInfo2.timeline, playbackInfo2.periodId, j2);
    }

    private int getCurrentWindowIndexInternal(PlaybackInfo playbackInfo2) {
        if (playbackInfo2.timeline.isEmpty()) {
            return this.maskingWindowIndex;
        }
        return playbackInfo2.timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period).windowIndex;
    }

    private Player.PositionInfo getPositionInfo(long j2) {
        Object obj;
        int i2;
        MediaItem mediaItem;
        Object obj2;
        long j3;
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        if (!this.playbackInfo.timeline.isEmpty()) {
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            Object obj3 = playbackInfo2.periodId.periodUid;
            playbackInfo2.timeline.getPeriodByUid(obj3, this.period);
            i2 = this.playbackInfo.timeline.getIndexOfPeriod(obj3);
            obj2 = obj3;
            obj = this.playbackInfo.timeline.getWindow(currentMediaItemIndex, this.window).uid;
            mediaItem = this.window.mediaItem;
        } else {
            obj = null;
            i2 = -1;
            mediaItem = null;
            obj2 = null;
        }
        int i7 = i2;
        long usToMs = Util.usToMs(j2);
        if (this.playbackInfo.periodId.isAd()) {
            j3 = Util.usToMs(getRequestedContentPositionUs(this.playbackInfo));
        } else {
            j3 = usToMs;
        }
        MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
        return new Player.PositionInfo(obj, currentMediaItemIndex, mediaItem, obj2, i7, usToMs, j3, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
    }

    private Player.PositionInfo getPreviousPositionInfo(int i2, PlaybackInfo playbackInfo2, int i7) {
        int i8;
        Object obj;
        MediaItem mediaItem;
        int i10;
        Object obj2;
        long j2;
        long j3;
        PlaybackInfo playbackInfo3 = playbackInfo2;
        Timeline.Period period2 = new Timeline.Period();
        if (!playbackInfo3.timeline.isEmpty()) {
            Object obj3 = playbackInfo3.periodId.periodUid;
            playbackInfo3.timeline.getPeriodByUid(obj3, period2);
            int i11 = period2.windowIndex;
            int indexOfPeriod = playbackInfo3.timeline.getIndexOfPeriod(obj3);
            Object obj4 = playbackInfo3.timeline.getWindow(i11, this.window).uid;
            mediaItem = this.window.mediaItem;
            obj = obj3;
            i8 = indexOfPeriod;
            obj2 = obj4;
            i10 = i11;
        } else {
            i10 = i7;
            obj2 = null;
            mediaItem = null;
            obj = null;
            i8 = -1;
        }
        if (i2 == 0) {
            if (playbackInfo3.periodId.isAd()) {
                MediaSource.MediaPeriodId mediaPeriodId = playbackInfo3.periodId;
                j3 = period2.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
                j2 = getRequestedContentPositionUs(playbackInfo3);
                long usToMs = Util.usToMs(j3);
                long usToMs2 = Util.usToMs(j2);
                MediaSource.MediaPeriodId mediaPeriodId2 = playbackInfo3.periodId;
                return new Player.PositionInfo(obj2, i10, mediaItem, obj, i8, usToMs, usToMs2, mediaPeriodId2.adGroupIndex, mediaPeriodId2.adIndexInAdGroup);
            } else if (playbackInfo3.periodId.nextAdGroupIndex != -1) {
                j3 = getRequestedContentPositionUs(this.playbackInfo);
            } else {
                j3 = period2.positionInWindowUs + period2.durationUs;
            }
        } else if (playbackInfo3.periodId.isAd()) {
            j3 = playbackInfo3.positionUs;
            j2 = getRequestedContentPositionUs(playbackInfo3);
            long usToMs3 = Util.usToMs(j3);
            long usToMs22 = Util.usToMs(j2);
            MediaSource.MediaPeriodId mediaPeriodId22 = playbackInfo3.periodId;
            return new Player.PositionInfo(obj2, i10, mediaItem, obj, i8, usToMs3, usToMs22, mediaPeriodId22.adGroupIndex, mediaPeriodId22.adIndexInAdGroup);
        } else {
            j3 = period2.positionInWindowUs + playbackInfo3.positionUs;
        }
        j2 = j3;
        long usToMs32 = Util.usToMs(j3);
        long usToMs222 = Util.usToMs(j2);
        MediaSource.MediaPeriodId mediaPeriodId222 = playbackInfo3.periodId;
        return new Player.PositionInfo(obj2, i10, mediaItem, obj, i8, usToMs32, usToMs222, mediaPeriodId222.adGroupIndex, mediaPeriodId222.adIndexInAdGroup);
    }

    private static long getRequestedContentPositionUs(PlaybackInfo playbackInfo2) {
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period2 = new Timeline.Period();
        playbackInfo2.timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, period2);
        if (playbackInfo2.requestedContentPositionUs == -9223372036854775807L) {
            return playbackInfo2.timeline.getWindow(period2.windowIndex, window).getDefaultPositionUs();
        }
        return period2.getPositionInWindowUs() + playbackInfo2.requestedContentPositionUs;
    }

    /* access modifiers changed from: private */
    /* renamed from: handlePlaybackInfo */
    public void lambda$new$1(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        boolean z;
        long j2;
        boolean z3;
        int i2 = this.pendingOperationAcks - playbackInfoUpdate.operationAcks;
        this.pendingOperationAcks = i2;
        boolean z7 = true;
        if (playbackInfoUpdate.positionDiscontinuity) {
            this.pendingDiscontinuityReason = playbackInfoUpdate.discontinuityReason;
            this.pendingDiscontinuity = true;
        }
        if (i2 == 0) {
            Timeline timeline = playbackInfoUpdate.playbackInfo.timeline;
            if (!this.playbackInfo.timeline.isEmpty() && timeline.isEmpty()) {
                this.maskingWindowIndex = -1;
                this.maskingWindowPositionMs = 0;
                this.maskingPeriodIndex = 0;
            }
            if (!timeline.isEmpty()) {
                List<Timeline> childTimelines = ((PlaylistTimeline) timeline).getChildTimelines();
                if (childTimelines.size() == this.mediaSourceHolderSnapshots.size()) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                Assertions.checkState(z3);
                for (int i7 = 0; i7 < childTimelines.size(); i7++) {
                    this.mediaSourceHolderSnapshots.get(i7).updateTimeline(childTimelines.get(i7));
                }
            }
            long j3 = -9223372036854775807L;
            if (this.pendingDiscontinuity) {
                if (playbackInfoUpdate.playbackInfo.periodId.equals(this.playbackInfo.periodId) && playbackInfoUpdate.playbackInfo.discontinuityStartPositionUs == this.playbackInfo.positionUs) {
                    z7 = false;
                }
                if (z7) {
                    if (timeline.isEmpty() || playbackInfoUpdate.playbackInfo.periodId.isAd()) {
                        j2 = playbackInfoUpdate.playbackInfo.discontinuityStartPositionUs;
                    } else {
                        PlaybackInfo playbackInfo2 = playbackInfoUpdate.playbackInfo;
                        j2 = periodPositionUsToWindowPositionUs(timeline, playbackInfo2.periodId, playbackInfo2.discontinuityStartPositionUs);
                    }
                    j3 = j2;
                }
                z = z7;
            } else {
                z = false;
            }
            this.pendingDiscontinuity = false;
            updatePlaybackInfo(playbackInfoUpdate.playbackInfo, 1, z, this.pendingDiscontinuityReason, j3, -1, false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(this.wrappingPlayer, new Player.Events(flagSet));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        this.playbackInfoUpdateHandler.post(new f(2, this, playbackInfoUpdate));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3() {
        this.audioSessionIdState.setStateInBackground(Integer.valueOf(Util.generateAudioSessionIdV21(this.applicationContext)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAvailableCommands$28(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.availableCommands);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$15(int i2, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(i2);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$21(PlaybackInfo playbackInfo2, Player.Listener listener) {
        listener.onLoadingChanged(playbackInfo2.isLoading);
        listener.onIsLoadingChanged(playbackInfo2.isLoading);
    }

    private static PlaybackInfo maskPlaybackState(PlaybackInfo playbackInfo2, int i2) {
        PlaybackInfo copyWithPlaybackState = playbackInfo2.copyWithPlaybackState(i2);
        if (i2 == 1 || i2 == 4) {
            return copyWithPlaybackState.copyWithIsLoading(false);
        }
        return copyWithPlaybackState;
    }

    private PlaybackInfo maskTimelineAndPosition(PlaybackInfo playbackInfo2, Timeline timeline, Pair<Object, Long> pair) {
        boolean z;
        MediaSource.MediaPeriodId mediaPeriodId;
        TrackGroupArray trackGroupArray;
        TrackSelectorResult trackSelectorResult;
        List list;
        int i2;
        long j2;
        Timeline timeline2 = timeline;
        Pair<Object, Long> pair2 = pair;
        if (timeline2.isEmpty() || pair2 != null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        Timeline timeline3 = playbackInfo2.timeline;
        long contentPositionInternal = getContentPositionInternal(playbackInfo2);
        PlaybackInfo copyWithTimeline = playbackInfo2.copyWithTimeline(timeline);
        if (timeline2.isEmpty()) {
            MediaSource.MediaPeriodId dummyPeriodForEmptyTimeline = PlaybackInfo.getDummyPeriodForEmptyTimeline();
            long msToUs = Util.msToUs(this.maskingWindowPositionMs);
            TrackGroupArray trackGroupArray2 = TrackGroupArray.EMPTY;
            TrackSelectorResult trackSelectorResult2 = this.emptyTrackSelectorResult;
            G g = U.e;
            PlaybackInfo copyWithLoadingMediaPeriodId = copyWithTimeline.copyWithNewPosition(dummyPeriodForEmptyTimeline, msToUs, msToUs, msToUs, 0, trackGroupArray2, trackSelectorResult2, y0.f278h).copyWithLoadingMediaPeriodId(dummyPeriodForEmptyTimeline);
            copyWithLoadingMediaPeriodId.bufferedPositionUs = copyWithLoadingMediaPeriodId.positionUs;
            return copyWithLoadingMediaPeriodId;
        }
        Object obj = copyWithTimeline.periodId.periodUid;
        boolean equals = obj.equals(((Pair) Util.castNonNull(pair2)).first);
        if (!equals) {
            mediaPeriodId = new MediaSource.MediaPeriodId(pair2.first);
        } else {
            mediaPeriodId = copyWithTimeline.periodId;
        }
        long longValue = ((Long) pair2.second).longValue();
        long msToUs2 = Util.msToUs(contentPositionInternal);
        if (!timeline3.isEmpty()) {
            msToUs2 -= timeline3.getPeriodByUid(obj, this.period).getPositionInWindowUs();
        }
        if (!equals || longValue < msToUs2) {
            boolean z3 = equals;
            MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
            long j3 = longValue;
            Assertions.checkState(!mediaPeriodId2.isAd());
            if (!z3) {
                trackGroupArray = TrackGroupArray.EMPTY;
            } else {
                trackGroupArray = copyWithTimeline.trackGroups;
            }
            TrackGroupArray trackGroupArray3 = trackGroupArray;
            if (!z3) {
                trackSelectorResult = this.emptyTrackSelectorResult;
            } else {
                trackSelectorResult = copyWithTimeline.trackSelectorResult;
            }
            TrackSelectorResult trackSelectorResult3 = trackSelectorResult;
            if (!z3) {
                G g3 = U.e;
                list = y0.f278h;
            } else {
                list = copyWithTimeline.staticMetadata;
            }
            PlaybackInfo copyWithLoadingMediaPeriodId2 = copyWithTimeline.copyWithNewPosition(mediaPeriodId2, j3, j3, j3, 0, trackGroupArray3, trackSelectorResult3, list).copyWithLoadingMediaPeriodId(mediaPeriodId2);
            copyWithLoadingMediaPeriodId2.bufferedPositionUs = j3;
            return copyWithLoadingMediaPeriodId2;
        } else if (i2 == 0) {
            int indexOfPeriod = timeline2.getIndexOfPeriod(copyWithTimeline.loadingMediaPeriodId.periodUid);
            if (indexOfPeriod != -1 && timeline2.getPeriod(indexOfPeriod, this.period).windowIndex == timeline2.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex) {
                return copyWithTimeline;
            }
            timeline2.getPeriodByUid(mediaPeriodId.periodUid, this.period);
            if (mediaPeriodId.isAd()) {
                j2 = this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
            } else {
                j2 = this.period.durationUs;
            }
            MediaSource.MediaPeriodId mediaPeriodId3 = mediaPeriodId;
            PlaybackInfo copyWithLoadingMediaPeriodId3 = copyWithTimeline.copyWithNewPosition(mediaPeriodId3, copyWithTimeline.positionUs, copyWithTimeline.positionUs, copyWithTimeline.discontinuityStartPositionUs, j2 - copyWithTimeline.positionUs, copyWithTimeline.trackGroups, copyWithTimeline.trackSelectorResult, copyWithTimeline.staticMetadata).copyWithLoadingMediaPeriodId(mediaPeriodId3);
            copyWithLoadingMediaPeriodId3.bufferedPositionUs = j2;
            return copyWithLoadingMediaPeriodId3;
        } else {
            MediaSource.MediaPeriodId mediaPeriodId4 = mediaPeriodId;
            Assertions.checkState(!mediaPeriodId4.isAd());
            long max = Math.max(0, copyWithTimeline.totalBufferedDurationUs - (longValue - msToUs2));
            long j8 = copyWithTimeline.bufferedPositionUs;
            if (copyWithTimeline.loadingMediaPeriodId.equals(copyWithTimeline.periodId)) {
                j8 = longValue + max;
            }
            long j10 = longValue;
            PlaybackInfo copyWithNewPosition = copyWithTimeline.copyWithNewPosition(mediaPeriodId4, j10, j10, j10, max, copyWithTimeline.trackGroups, copyWithTimeline.trackSelectorResult, copyWithTimeline.staticMetadata);
            copyWithNewPosition.bufferedPositionUs = j8;
            return copyWithNewPosition;
        }
    }

    private Pair<Object, Long> maskWindowPositionMsOrGetPeriodPositionUs(Timeline timeline, int i2, long j2) {
        if (timeline.isEmpty()) {
            this.maskingWindowIndex = i2;
            if (j2 == -9223372036854775807L) {
                j2 = 0;
            }
            this.maskingWindowPositionMs = j2;
            this.maskingPeriodIndex = 0;
            return null;
        }
        if (i2 == -1 || i2 >= timeline.getWindowCount()) {
            i2 = timeline.getFirstWindowIndex(this.shuffleModeEnabled);
            j2 = timeline.getWindow(i2, this.window).getDefaultPositionMs();
        }
        return timeline.getPeriodPositionUs(this.window, this.period, i2, Util.msToUs(j2));
    }

    /* access modifiers changed from: private */
    public void maybeNotifySurfaceSizeChanged(int i2, int i7) {
        if (i2 != this.surfaceSize.getWidth() || i7 != this.surfaceSize.getHeight()) {
            this.surfaceSize = new Size(i2, i7);
            this.listeners.sendEvent(24, new l(i2, i7));
            sendRendererMessage(2, 14, new Size(i2, i7));
        }
    }

    private void maybeUpdatePlaybackSuppressionReason() {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        updatePlayWhenReady(playbackInfo2.playWhenReady, playbackInfo2.playWhenReadyChangeReason);
    }

    /* access modifiers changed from: private */
    public void onAudioSessionIdChanged(int i2, int i7) {
        verifyApplicationThread();
        sendRendererMessage(1, 10, Integer.valueOf(i7));
        sendRendererMessage(2, 10, Integer.valueOf(i7));
        this.listeners.sendEvent(21, new m(i7));
    }

    /* access modifiers changed from: private */
    public void onSelectedOutputSuitabilityChanged(boolean z) {
        if (!this.playerReleased) {
            if (!z) {
                maybeUpdatePlaybackSuppressionReason();
            } else if (this.playbackInfo.playbackSuppressionReason == 3) {
                maybeUpdatePlaybackSuppressionReason();
            }
        }
    }

    private long periodPositionUsToWindowPositionUs(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j2) {
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return this.period.getPositionInWindowUs() + j2;
    }

    private void removeMediaSourceHolders(int i2, int i7) {
        for (int i8 = i7 - 1; i8 >= i2; i8--) {
            this.mediaSourceHolderSnapshots.remove(i8);
        }
        this.shuffleOrder = this.shuffleOrder.cloneAndRemove(i2, i7);
    }

    private void removeSurfaceCallbacks() {
        TextureView textureView2 = this.textureView;
        if (textureView2 != null) {
            if (textureView2.getSurfaceTextureListener() != this.componentListener) {
                Log.w("ExoPlayerImpl", "SurfaceTextureListener already unset or replaced.");
            } else {
                this.textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
            }
            this.textureView = null;
        }
        SurfaceHolder surfaceHolder2 = this.surfaceHolder;
        if (surfaceHolder2 != null) {
            surfaceHolder2.removeCallback(this.componentListener);
            this.surfaceHolder = null;
        }
    }

    private void sendRendererMessage(int i2, Object obj) {
        sendRendererMessage(-1, i2, obj);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setMediaSourcesInternal(java.util.List<androidx.media3.exoplayer.source.MediaSource> r15, int r16, long r17, boolean r19) {
        /*
            r14 = this;
            r1 = r16
            androidx.media3.exoplayer.PlaybackInfo r2 = r14.playbackInfo
            int r2 = r14.getCurrentWindowIndexInternal(r2)
            long r3 = r14.getCurrentPosition()
            int r5 = r14.pendingOperationAcks
            r6 = 1
            int r5 = r5 + r6
            r14.pendingOperationAcks = r5
            java.util.List<androidx.media3.exoplayer.ExoPlayerImpl$MediaSourceHolderSnapshot> r5 = r14.mediaSourceHolderSnapshots
            boolean r5 = r5.isEmpty()
            r7 = 0
            if (r5 != 0) goto L_0x0024
            java.util.List<androidx.media3.exoplayer.ExoPlayerImpl$MediaSourceHolderSnapshot> r5 = r14.mediaSourceHolderSnapshots
            int r5 = r5.size()
            r14.removeMediaSourceHolders(r7, r5)
        L_0x0024:
            java.util.List r9 = r14.addMediaSourceHolders(r7, r15)
            androidx.media3.common.Timeline r5 = r14.createMaskingTimeline()
            boolean r8 = r5.isEmpty()
            if (r8 != 0) goto L_0x0038
            int r8 = r5.getWindowCount()
            if (r1 >= r8) goto L_0x003b
        L_0x0038:
            r10 = r17
            goto L_0x0043
        L_0x003b:
            androidx.media3.common.IllegalSeekPositionException r0 = new androidx.media3.common.IllegalSeekPositionException
            r10 = r17
            r0.<init>(r5, r1, r10)
            throw r0
        L_0x0043:
            r8 = -1
            if (r19 == 0) goto L_0x0053
            boolean r1 = r14.shuffleModeEnabled
            int r1 = r5.getFirstWindowIndex(r1)
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0051:
            r10 = r1
            goto L_0x005a
        L_0x0053:
            if (r1 != r8) goto L_0x0058
            r10 = r2
            r2 = r3
            goto L_0x005a
        L_0x0058:
            r2 = r10
            goto L_0x0051
        L_0x005a:
            androidx.media3.exoplayer.PlaybackInfo r1 = r14.playbackInfo
            android.util.Pair r4 = r14.maskWindowPositionMsOrGetPeriodPositionUs(r5, r10, r2)
            androidx.media3.exoplayer.PlaybackInfo r1 = r14.maskTimelineAndPosition(r1, r5, r4)
            int r4 = r1.playbackState
            if (r10 == r8) goto L_0x007a
            if (r4 == r6) goto L_0x007a
            boolean r4 = r5.isEmpty()
            if (r4 != 0) goto L_0x0079
            int r4 = r5.getWindowCount()
            if (r10 < r4) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r4 = 2
            goto L_0x007a
        L_0x0079:
            r4 = 4
        L_0x007a:
            androidx.media3.exoplayer.PlaybackInfo r1 = maskPlaybackState(r1, r4)
            androidx.media3.exoplayer.ExoPlayerImplInternal r8 = r14.internalPlayer
            long r11 = androidx.media3.common.util.Util.msToUs(r2)
            androidx.media3.exoplayer.source.ShuffleOrder r13 = r14.shuffleOrder
            r8.setMediaSources(r9, r10, r11, r13)
            androidx.media3.exoplayer.PlaybackInfo r2 = r14.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = r2.periodId
            java.lang.Object r2 = r2.periodUid
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r1.periodId
            java.lang.Object r3 = r3.periodUid
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00a5
            androidx.media3.exoplayer.PlaybackInfo r2 = r14.playbackInfo
            androidx.media3.common.Timeline r2 = r2.timeline
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x00a5
            r3 = r6
            goto L_0x00a6
        L_0x00a5:
            r3 = r7
        L_0x00a6:
            long r5 = r14.getCurrentPositionUsInternal(r1)
            r7 = -1
            r8 = 0
            r2 = 0
            r4 = 4
            r0 = r14
            r0.updatePlaybackInfo(r1, r2, r3, r4, r5, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImpl.setMediaSourcesInternal(java.util.List, int, long, boolean):void");
    }

    /* access modifiers changed from: private */
    public void setSurfaceTextureInternal(SurfaceTexture surfaceTexture) {
        Surface surface = new Surface(surfaceTexture);
        setVideoOutputInternal(surface);
        this.ownedSurface = surface;
    }

    /* access modifiers changed from: private */
    public void setVideoOutputInternal(Object obj) {
        boolean z;
        long j2;
        Surface surface;
        Object obj2 = this.videoOutput;
        if (obj2 == null || obj2 == obj) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            j2 = this.detachSurfaceTimeoutMs;
        } else {
            j2 = -9223372036854775807L;
        }
        boolean videoOutput2 = this.internalPlayer.setVideoOutput(obj, j2);
        if (z && this.videoOutput == (surface = this.ownedSurface)) {
            surface.release();
            this.ownedSurface = null;
        }
        this.videoOutput = obj;
        if (!videoOutput2) {
            stopInternal(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(3), 1003));
        }
    }

    private void stopInternal(ExoPlaybackException exoPlaybackException) {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        PlaybackInfo copyWithLoadingMediaPeriodId = playbackInfo2.copyWithLoadingMediaPeriodId(playbackInfo2.periodId);
        copyWithLoadingMediaPeriodId.bufferedPositionUs = copyWithLoadingMediaPeriodId.positionUs;
        copyWithLoadingMediaPeriodId.totalBufferedDurationUs = 0;
        PlaybackInfo maskPlaybackState = maskPlaybackState(copyWithLoadingMediaPeriodId, 1);
        if (exoPlaybackException != null) {
            maskPlaybackState = maskPlaybackState.copyWithPlaybackError(exoPlaybackException);
        }
        this.pendingOperationAcks++;
        this.internalPlayer.stop();
        updatePlaybackInfo(maskPlaybackState, 0, false, 5, -9223372036854775807L, -1, false);
    }

    private void updateAvailableCommands() {
        Player.Commands commands = this.availableCommands;
        Player.Commands availableCommands2 = Util.getAvailableCommands(this.wrappingPlayer, this.permanentAvailableCommands);
        this.availableCommands = availableCommands2;
        if (!availableCommands2.equals(commands)) {
            this.listeners.queueEvent(13, new k(this));
        }
    }

    /* access modifiers changed from: private */
    public void updatePlayWhenReady(boolean z, int i2) {
        int computePlaybackSuppressionReason = computePlaybackSuppressionReason(z);
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.playWhenReady != z || playbackInfo2.playbackSuppressionReason != computePlaybackSuppressionReason || playbackInfo2.playWhenReadyChangeReason != i2) {
            this.pendingOperationAcks++;
            if (playbackInfo2.sleepingForOffload) {
                playbackInfo2 = playbackInfo2.copyWithEstimatedPosition();
            }
            PlaybackInfo copyWithPlayWhenReady = playbackInfo2.copyWithPlayWhenReady(z, i2, computePlaybackSuppressionReason);
            this.internalPlayer.setPlayWhenReady(z, i2, computePlaybackSuppressionReason);
            updatePlaybackInfo(copyWithPlayWhenReady, 0, false, 5, -9223372036854775807L, -1, false);
        }
    }

    private void updatePlaybackInfo(PlaybackInfo playbackInfo2, int i2, boolean z, int i7, long j2, int i8, boolean z3) {
        boolean z7;
        boolean z9;
        boolean z10;
        PlaybackInfo playbackInfo3 = playbackInfo2;
        PlaybackInfo playbackInfo4 = this.playbackInfo;
        this.playbackInfo = playbackInfo3;
        boolean equals = playbackInfo4.timeline.equals(playbackInfo3.timeline);
        int i10 = i7;
        Pair<Boolean, Integer> evaluateMediaItemTransitionReason = evaluateMediaItemTransitionReason(playbackInfo3, playbackInfo4, z, i10, !equals, z3);
        boolean booleanValue = ((Boolean) evaluateMediaItemTransitionReason.first).booleanValue();
        int intValue = ((Integer) evaluateMediaItemTransitionReason.second).intValue();
        MediaItem mediaItem = null;
        if (booleanValue) {
            if (!playbackInfo3.timeline.isEmpty()) {
                mediaItem = playbackInfo3.timeline.getWindow(playbackInfo3.timeline.getPeriodByUid(playbackInfo3.periodId.periodUid, this.period).windowIndex, this.window).mediaItem;
            }
            this.staticAndDynamicMediaMetadata = MediaMetadata.EMPTY;
        }
        if (booleanValue || !playbackInfo4.staticMetadata.equals(playbackInfo3.staticMetadata)) {
            this.staticAndDynamicMediaMetadata = this.staticAndDynamicMediaMetadata.buildUpon().populateFromMetadata(playbackInfo3.staticMetadata).build();
        }
        MediaMetadata buildUpdatedMediaMetadata = buildUpdatedMediaMetadata();
        boolean equals2 = buildUpdatedMediaMetadata.equals(this.mediaMetadata);
        this.mediaMetadata = buildUpdatedMediaMetadata;
        if (playbackInfo4.playWhenReady != playbackInfo3.playWhenReady) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (playbackInfo4.playbackState != playbackInfo3.playbackState) {
            z9 = true;
        } else {
            z9 = false;
        }
        if (z9 || z7) {
            updateWakeAndWifiLock();
        }
        boolean z11 = playbackInfo4.isLoading;
        boolean z12 = playbackInfo3.isLoading;
        if (z11 != z12) {
            z10 = true;
        } else {
            z10 = false;
        }
        if (z10) {
            updatePriorityTaskManagerForIsLoadingChange(z12);
        }
        if (!equals) {
            this.listeners.queueEvent(0, new g(playbackInfo3, i2, 0));
        }
        if (z) {
            this.listeners.queueEvent(11, new n(getPreviousPositionInfo(i10, playbackInfo4, i8), getPositionInfo(j2), i10));
        }
        if (booleanValue) {
            this.listeners.queueEvent(1, new g(mediaItem, intValue, 1));
        }
        if (playbackInfo4.playbackError != playbackInfo3.playbackError) {
            this.listeners.queueEvent(10, new i(playbackInfo3, 7));
            if (playbackInfo3.playbackError != null) {
                this.listeners.queueEvent(10, new i(playbackInfo3, 8));
            }
        }
        TrackSelectorResult trackSelectorResult = playbackInfo4.trackSelectorResult;
        TrackSelectorResult trackSelectorResult2 = playbackInfo3.trackSelectorResult;
        if (trackSelectorResult != trackSelectorResult2) {
            this.trackSelector.onSelectionActivated(trackSelectorResult2.info);
            this.listeners.queueEvent(2, new i(playbackInfo3, 9));
        }
        if (!equals2) {
            this.listeners.queueEvent(14, new h(0, this.mediaMetadata));
        }
        if (z10) {
            this.listeners.queueEvent(3, new i(playbackInfo3, 0));
        }
        if (z9 || z7) {
            this.listeners.queueEvent(-1, new i(playbackInfo3, 1));
        }
        if (z9) {
            this.listeners.queueEvent(4, new i(playbackInfo3, 2));
        }
        if (z7 || playbackInfo4.playWhenReadyChangeReason != playbackInfo3.playWhenReadyChangeReason) {
            this.listeners.queueEvent(5, new i(playbackInfo3, 3));
        }
        if (playbackInfo4.playbackSuppressionReason != playbackInfo3.playbackSuppressionReason) {
            this.listeners.queueEvent(6, new i(playbackInfo3, 4));
        }
        if (playbackInfo4.isPlaying() != playbackInfo3.isPlaying()) {
            this.listeners.queueEvent(7, new i(playbackInfo3, 5));
        }
        if (!playbackInfo4.playbackParameters.equals(playbackInfo3.playbackParameters)) {
            this.listeners.queueEvent(12, new i(playbackInfo3, 6));
        }
        updateAvailableCommands();
        this.listeners.flushEvents();
        if (playbackInfo4.sleepingForOffload != playbackInfo3.sleepingForOffload) {
            Iterator<ExoPlayer.AudioOffloadListener> it = this.audioOffloadListeners.iterator();
            while (it.hasNext()) {
                it.next().onSleepingForOffloadChanged(playbackInfo3.sleepingForOffload);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateWakeAndWifiLock() {
        int playbackState = getPlaybackState();
        boolean z = false;
        if (playbackState != 1) {
            if (playbackState == 2 || playbackState == 3) {
                boolean isSleepingForOffload = isSleepingForOffload();
                WakeLockManager wakeLockManager2 = this.wakeLockManager;
                if (getPlayWhenReady() && !isSleepingForOffload) {
                    z = true;
                }
                wakeLockManager2.setStayAwake(z);
                this.wifiLockManager.setStayAwake(getPlayWhenReady());
                return;
            } else if (playbackState != 4) {
                throw new IllegalStateException();
            }
        }
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
    }

    private void verifyApplicationThread() {
        IllegalStateException illegalStateException;
        this.constructorFinished.blockUninterruptible();
        if (Thread.currentThread() != getApplicationLooper().getThread()) {
            String formatInvariant = Util.formatInvariant("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\nSee https://developer.android.com/guide/topics/media/issues/player-accessed-on-wrong-thread", Thread.currentThread().getName(), getApplicationLooper().getThread().getName());
            if (!this.throwsWhenUsingWrongThread) {
                if (this.hasNotifiedFullWrongThreadWarning) {
                    illegalStateException = null;
                } else {
                    illegalStateException = new IllegalStateException();
                }
                Log.w("ExoPlayerImpl", formatInvariant, illegalStateException);
                this.hasNotifiedFullWrongThreadWarning = true;
                return;
            }
            throw new IllegalStateException(formatInvariant);
        }
    }

    public void addAnalyticsListener(AnalyticsListener analyticsListener) {
        this.analyticsCollector.addListener((AnalyticsListener) Assertions.checkNotNull(analyticsListener));
    }

    public void addAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
        this.audioOffloadListeners.add(audioOffloadListener);
    }

    public void addListener(Player.Listener listener) {
        this.listeners.add((Player.Listener) Assertions.checkNotNull(listener));
    }

    public Looper getApplicationLooper() {
        return this.applicationLooper;
    }

    public Clock getClock() {
        return this.clock;
    }

    public long getContentPosition() {
        verifyApplicationThread();
        return getContentPositionInternal(this.playbackInfo);
    }

    public int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adGroupIndex;
        }
        return -1;
    }

    public int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adIndexInAdGroup;
        }
        return -1;
    }

    public int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(this.playbackInfo);
        if (currentWindowIndexInternal == -1) {
            return 0;
        }
        return currentWindowIndexInternal;
    }

    public int getCurrentPeriodIndex() {
        verifyApplicationThread();
        if (this.playbackInfo.timeline.isEmpty()) {
            return this.maskingPeriodIndex;
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        return playbackInfo2.timeline.getIndexOfPeriod(playbackInfo2.periodId.periodUid);
    }

    public long getCurrentPosition() {
        verifyApplicationThread();
        return Util.usToMs(getCurrentPositionUsInternal(this.playbackInfo));
    }

    public Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return this.playbackInfo.timeline;
    }

    public Tracks getCurrentTracks() {
        verifyApplicationThread();
        return this.playbackInfo.trackSelectorResult.tracks;
    }

    public long getDuration() {
        verifyApplicationThread();
        if (!isPlayingAd()) {
            return getContentDuration();
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo2.periodId;
        playbackInfo2.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return Util.usToMs(this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup));
    }

    public boolean getPlayWhenReady() {
        verifyApplicationThread();
        return this.playbackInfo.playWhenReady;
    }

    public Looper getPlaybackLooper() {
        return this.internalPlayer.getPlaybackLooper();
    }

    public int getPlaybackState() {
        verifyApplicationThread();
        return this.playbackInfo.playbackState;
    }

    public int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        return this.playbackInfo.playbackSuppressionReason;
    }

    public int getRepeatMode() {
        verifyApplicationThread();
        return this.repeatMode;
    }

    public boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return this.shuffleModeEnabled;
    }

    public long getTotalBufferedDuration() {
        verifyApplicationThread();
        return Util.usToMs(this.playbackInfo.totalBufferedDurationUs);
    }

    public boolean isPlayingAd() {
        verifyApplicationThread();
        return this.playbackInfo.periodId.isAd();
    }

    public boolean isSleepingForOffload() {
        verifyApplicationThread();
        return this.playbackInfo.sleepingForOffload;
    }

    public void prepare() {
        int i2;
        verifyApplicationThread();
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.playbackState == 1) {
            PlaybackInfo copyWithPlaybackError = playbackInfo2.copyWithPlaybackError((ExoPlaybackException) null);
            if (copyWithPlaybackError.timeline.isEmpty()) {
                i2 = 4;
            } else {
                i2 = 2;
            }
            PlaybackInfo maskPlaybackState = maskPlaybackState(copyWithPlaybackError, i2);
            this.pendingOperationAcks++;
            this.internalPlayer.prepare();
            updatePlaybackInfo(maskPlaybackState, 1, false, 5, -9223372036854775807L, -1, false);
        }
    }

    /* JADX WARNING: type inference failed for: r1v11, types: [androidx.media3.common.util.ListenerSet$Event, java.lang.Object] */
    public void release() {
        Log.i("ExoPlayerImpl", "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.8.0] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
        verifyApplicationThread();
        this.audioBecomingNoisyManager.setEnabled(false);
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.release();
        }
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
        SuitableOutputChecker suitableOutputChecker2 = this.suitableOutputChecker;
        if (suitableOutputChecker2 != null) {
            suitableOutputChecker2.disable();
        }
        if (!this.internalPlayer.release()) {
            this.listeners.sendEvent(10, new Object());
        }
        this.listeners.release();
        this.playbackInfoUpdateHandler.removeCallbacksAndMessages((Object) null);
        this.bandwidthMeter.removeEventListener(this.analyticsCollector);
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.sleepingForOffload) {
            this.playbackInfo = playbackInfo2.copyWithEstimatedPosition();
        }
        PlaybackInfo maskPlaybackState = maskPlaybackState(this.playbackInfo, 1);
        this.playbackInfo = maskPlaybackState;
        PlaybackInfo copyWithLoadingMediaPeriodId = maskPlaybackState.copyWithLoadingMediaPeriodId(maskPlaybackState.periodId);
        this.playbackInfo = copyWithLoadingMediaPeriodId;
        copyWithLoadingMediaPeriodId.bufferedPositionUs = copyWithLoadingMediaPeriodId.positionUs;
        this.playbackInfo.totalBufferedDurationUs = 0;
        this.analyticsCollector.release();
        removeSurfaceCallbacks();
        Surface surface = this.ownedSurface;
        if (surface != null) {
            surface.release();
            this.ownedSurface = null;
        }
        if (!this.isPriorityTaskManagerRegistered) {
            this.currentCueGroup = CueGroup.EMPTY_TIME_ZERO;
            this.playerReleased = true;
            return;
        }
        Assertions.checkNotNull(null).getClass();
        throw new ClassCastException();
    }

    public void setMediaItems(List<MediaItem> list, boolean z) {
        verifyApplicationThread();
        setMediaSources(createMediaSources(list), z);
    }

    public void setMediaSources(List<MediaSource> list, boolean z) {
        verifyApplicationThread();
        setMediaSourcesInternal(list, -1, -9223372036854775807L, z);
    }

    public void setPlayWhenReady(boolean z) {
        verifyApplicationThread();
        updatePlayWhenReady(z, 1);
    }

    private void sendRendererMessage(int i2, int i7, Object obj) {
        for (Renderer renderer : this.renderers) {
            if (i2 == -1 || renderer.getTrackType() == i2) {
                createMessageInternal(renderer).setType(i7).setPayload(obj).send();
            }
        }
        for (Renderer renderer2 : this.secondaryRenderers) {
            if (renderer2 != null && (i2 == -1 || renderer2.getTrackType() == i2)) {
                createMessageInternal(renderer2).setType(i7).setPayload(obj).send();
            }
        }
    }

    public ExoPlaybackException getPlayerError() {
        verifyApplicationThread();
        return this.playbackInfo.playbackError;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ComponentListener implements VideoRendererEventListener, AudioRendererEventListener, TextOutput, MetadataOutput, SurfaceHolder.Callback, TextureView.SurfaceTextureListener, AudioBecomingNoisyManager.EventListener, StreamVolumeManager.Listener, ExoPlayer.AudioOffloadListener {
        private ComponentListener() {
        }

        public void onAudioBecomingNoisy() {
            ExoPlayerImpl.this.updatePlayWhenReady(false, 3);
        }

        public void onSleepingForOffloadChanged(boolean z) {
            ExoPlayerImpl.this.updateWakeAndWifiLock();
        }

        public void onStreamTypeChanged(int i2) {
            DeviceInfo access$2300 = ExoPlayerImpl.createDeviceInfo(ExoPlayerImpl.this.streamVolumeManager);
            if (!access$2300.equals(ExoPlayerImpl.this.deviceInfo)) {
                DeviceInfo unused = ExoPlayerImpl.this.deviceInfo = access$2300;
                ExoPlayerImpl.this.listeners.sendEvent(29, new h(1, access$2300));
            }
        }

        public void onStreamVolumeChanged(int i2, boolean z) {
            ExoPlayerImpl.this.listeners.sendEvent(30, new p(i2, z));
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i7) {
            ExoPlayerImpl.this.setSurfaceTextureInternal(surfaceTexture);
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i2, i7);
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            ExoPlayerImpl.this.setVideoOutputInternal((Object) null);
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(0, 0);
            return true;
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i7) {
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i2, i7);
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i7, int i8) {
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i7, i8);
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (ExoPlayerImpl.this.surfaceHolderSurfaceIsVideoOutput) {
                ExoPlayerImpl.this.setVideoOutputInternal(surfaceHolder.getSurface());
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (ExoPlayerImpl.this.surfaceHolderSurfaceIsVideoOutput) {
                ExoPlayerImpl.this.setVideoOutputInternal((Object) null);
            }
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(0, 0);
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    private void updatePriorityTaskManagerForIsLoadingChange(boolean z) {
    }
}
