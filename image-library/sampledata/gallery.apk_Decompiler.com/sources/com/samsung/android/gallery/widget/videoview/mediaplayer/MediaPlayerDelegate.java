package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.PlaybackParams;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Surface;
import c0.C0086a;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ValueTrigger;
import com.samsung.android.gallery.widget.videoview.mediaplayer.plugin.IMediaPlayerPlugin;
import com.samsung.android.gallery.widget.videoview.mediaplayer.plugin.MediaPlayerPluginComposite;
import g6.g;
import i4.C0468a;
import ic.b;
import ic.c;
import ic.d;
import ic.e;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaPlayerDelegate implements IMediaPlayerPlugin {
    protected final Object LOCK;
    protected final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private boolean mAudioMute;
    private String mDataSource;
    private boolean mIsPreview;
    private boolean mLooping;
    private MediaItem mMediaItem;
    private MediaPlayerCompat mMediaPlayer;
    private final ConcurrentLinkedQueue<MediaPlayerListener> mMediaPlayerListener = new ConcurrentLinkedQueue<>();
    private final MpViewThread mMpViewThread;
    private final IMediaPlayerPlugin mPluginComposite;
    private final ValueTrigger<Boolean> mRenderStart;
    private int mRenderingPosition;
    private float mSpeed;
    private final ValueTrigger<PlayState> mState;
    protected long[] mSubDataSource;
    private boolean mSupportTimeTick;
    private Surface mSurface;
    private final ValueTrigger<Boolean> mSurfaceReady;
    private boolean mVisualSeekStart;
    private boolean mWfdTcpDisabled;

    public MediaPlayerDelegate() {
        MpViewThread mpViewThread = new MpViewThread();
        this.mMpViewThread = mpViewThread;
        Boolean bool = Boolean.FALSE;
        this.mSurfaceReady = new ValueTrigger<>(bool, "surfaceReady");
        this.mRenderStart = new ValueTrigger<>(bool, "mRenderStart");
        this.mState = new ValueTrigger<>(PlayState.NONE, mpViewThread.getHandler());
        this.LOCK = new Object();
        this.mVisualSeekStart = false;
        this.mAudioMute = true;
        this.mSupportTimeTick = false;
        this.mWfdTcpDisabled = false;
        this.mLooping = false;
        this.mPluginComposite = new MediaPlayerPluginComposite(this);
        this.mIsPreview = false;
        this.mSpeed = 1.0f;
    }

    private MediaPlayerCompat createMediaPlayer(MediaItem mediaItem) {
        MediaPlayerCompat createMediaPlayerCompat = MediaPlayerFactory.createMediaPlayerCompat(mediaItem, this.mIsPreview);
        createMediaPlayerCompat.setOnInfoListener(new e(this));
        createMediaPlayerCompat.setOnPreparedListener(new e(this));
        createMediaPlayerCompat.setOnErrorListener(new e(this));
        createMediaPlayerCompat.setOnCompletionListener(new e(this));
        createMediaPlayerCompat.setOnVideoSizeChangedListener(new e(this));
        createMediaPlayerCompat.setTimeTickEnabled(this.mSupportTimeTick);
        if (this.mWfdTcpDisabled) {
            createMediaPlayerCompat.setWfdTcpDisable();
        }
        return createMediaPlayerCompat;
    }

    private void destroyMediaPlayer() {
        this.mMpViewThread.cancel();
        synchronized (this.LOCK) {
            new LatchBuilder("ResetMediaPlayer", true).addWorker(new b(this, 1)).setPostExecutor((Consumer<Boolean>) new g(5, this)).setTimeout(1000).start();
            this.mMediaPlayer = null;
        }
    }

    private String getDataSource(MediaItem mediaItem) {
        if (mediaItem == null) {
            Log.e(this.TAG, "getDataSource mMediaItem null");
            return "";
        } else if (mediaItem.isSharing()) {
            return MediaItemMde.getDownloadVideoPath(mediaItem);
        } else {
            return VideoPropData.getLongExposurePath(mediaItem);
        }
    }

    private int getOrientation() {
        if (this.mMediaItem.getVideoMetadataOrientation() >= 0) {
            return this.mMediaItem.getVideoMetadataOrientation();
        }
        if (this.mMediaPlayer.getVideoRotation() >= 0) {
            return this.mMediaPlayer.getVideoRotation();
        }
        return this.mMediaItem.getOrientation();
    }

    private boolean isPrepared() {
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat == null || !mediaPlayerCompat.isPrepared()) {
            return false;
        }
        return true;
    }

    private boolean isSharedVideo(MediaItem mediaItem) {
        if (!mediaItem.isSharing() || !mediaItem.isVideo()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$destroyMediaPlayer$8() {
        resetPlayer(this.mMediaPlayer);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$destroyMediaPlayer$9(Boolean bool) {
        if (bool.booleanValue()) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "MediaPlayer reset time out " + this.mMediaPlayer);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoError$7(int i2, int i7) {
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onVideoError(i2, i7);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPaused$6(int i2) {
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onVideoPause(i2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPlay$5(int i2) {
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onVideoPlay(i2);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$pause$12(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.PLAY.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$play$0(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.SET_DATA.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$play$1() {
        this.mSurfaceReady.when(Boolean.TRUE).then(new b(this, 0), "PREPARE");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$play$2(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.PREPARED.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$seekBegin$13(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.PLAY.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seekBegin$14() {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.prepareSeekTo();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$seekFinish$15(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.PLAY.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seekFinish$16() {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.completeSeekTo(true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$seekTo$17(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.PREPARED.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seekTo$18(int i2) {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.seekTo(i2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$setPlaybackRange$23(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.PREPARED.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaybackRange$24(int i2, int i7) {
        synchronized (this.LOCK) {
            this.mMediaPlayer.setPlaybackRange(i2, i7);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$setRenderingPosition$3(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.PREPARED.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setRenderingPosition$4() {
        synchronized (this.LOCK) {
            try {
                if (this.mMediaPlayer.getCurrentPositionMs() != this.mRenderingPosition) {
                    if (this.mMediaItem.isMotionPhoto()) {
                        this.mMediaPlayer.seekToAdaptively(this.mRenderingPosition);
                    } else {
                        this.mMediaPlayer.seekTo(this.mRenderingPosition);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$setVolume$10(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.PREPARED.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setVolume$11(float f, float f5) {
        synchronized (this.LOCK) {
            MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
            if (mediaPlayerCompat != null) {
                try {
                    mediaPlayerCompat.setVolume(f, f5);
                } catch (IllegalStateException e) {
                    Log.e((CharSequence) this.TAG, "setVolume failed", e.getMessage());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$visualSeekTo$21(PlayState playState) {
        boolean z;
        if (playState.value() >= PlayState.PLAY.value()) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$visualSeekTo$22(int i2) {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.visualSeekTo(i2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private boolean needPrepare() {
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat == null || mediaPlayerCompat.isPreparing() || this.mMediaPlayer.isPrepared()) {
            return false;
        }
        return true;
    }

    private void onTimeTickUpdate(int i2, int i7) {
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onTimeTickUpdate(i2, i7);
        }
    }

    /* access modifiers changed from: private */
    public boolean onVideoError(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        StringCompat stringCompat = this.TAG;
        Log.e(stringCompat, "onVideoError#" + this.mState + " code=" + i2);
        this.mState.get();
        PlayState playState = PlayState.ERROR;
        this.mState.set(PlayState.ERROR);
        this.mMpViewThread.run(new c(this, i2, i7, 0));
        return true;
    }

    private void onVideoPaused(int i2) {
        log("onVideoPaused p=" + i2);
        this.mMpViewThread.run(new d(this, i2, 3));
    }

    /* access modifiers changed from: private */
    public void onVideoPlay() {
        int currentPositionMs;
        boolean isPlaying;
        String str;
        synchronized (this.LOCK) {
            currentPositionMs = this.mMediaPlayer.getCurrentPositionMs();
            isPlaying = this.mMediaPlayer.isPlaying();
        }
        StringBuilder o2 = C0086a.o(currentPositionMs, "onVideoPlay pos=", ", state=");
        if (isPlaying) {
            str = "T";
        } else {
            str = "F";
        }
        o2.append(str);
        log(o2.toString());
        if (isPlaying) {
            this.mState.set(PlayState.PLAY);
        }
        this.mMpViewThread.run(new d(this, currentPositionMs, 2));
    }

    private void onVideoReleased() {
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onVideoReleased();
        }
    }

    /* access modifiers changed from: private */
    public void pauseInternal() {
        try {
            synchronized (this.LOCK) {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.pause();
                    int renderingPosition = this.mMediaPlayer.getRenderingPosition();
                    this.mRenderingPosition = renderingPosition;
                    if (renderingPosition == 0) {
                        this.mRenderingPosition = this.mMediaPlayer.getCurrentPositionMs();
                    }
                } else {
                    Log.e(this.TAG, "pauseVideo but mp null");
                }
            }
            StringCompat stringCompat = this.TAG;
            Log.v(stringCompat, "pauseVideo at " + this.mRenderingPosition + " / " + this);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "pauseVideo failed", (Throwable) e);
        } catch (Throwable th) {
            while (true) {
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void prepare() {
        log("prepare#" + this.mState.get());
        try {
            synchronized (this.LOCK) {
                if (needPrepare()) {
                    this.mMediaPlayer.prepareAsync();
                }
            }
            this.mState.set(PlayState.PREPARING);
        } catch (IllegalStateException e) {
            onVideoError(this.mMediaPlayer, 0, 0);
            Log.e((CharSequence) this.TAG, "prepare failed", (Throwable) e);
        } catch (Throwable th) {
            while (true) {
            }
            throw th;
        }
    }

    private void resetPlayer(MediaPlayerCompat mediaPlayerCompat) {
        if (mediaPlayerCompat != null) {
            mediaPlayerCompat.reset();
            mediaPlayerCompat.release();
        }
    }

    private void setDataSource() {
        String str;
        String str2;
        Uri uri;
        if (this.mMediaItem == null || this.mMediaPlayer == null) {
            StringBuilder sb2 = new StringBuilder("illegal argument for setDataSource ");
            if (this.mMediaItem == null) {
                str = " null data";
            } else {
                str = "";
            }
            sb2.append(str);
            if (this.mMediaPlayer == null) {
                str2 = " null player";
            } else {
                str2 = "";
            }
            sb2.append(str2);
            String sb3 = sb2.toString();
            Log.e(this.TAG, sb3);
            throw new NullPointerException(sb3);
        }
        synchronized (this.LOCK) {
            try {
                long[] jArr = this.mSubDataSource;
                if (jArr != null) {
                    this.mMediaPlayer.setDataSource(this.mDataSource, jArr[0], jArr[1]);
                } else if (this.mMediaItem.isUriItem()) {
                    this.mMediaPlayer.setDataSource(AppResources.getAppContext(), Uri.parse(this.mDataSource));
                } else if (this.mMediaItem.isCloudOnly() && MediaItemUtil.supportCloudPreviewPlay(this.mMediaItem)) {
                    this.mMediaPlayer.setDataSource(this.mDataSource);
                } else if (SdkConfig.atLeast(SdkConfig.GED.S)) {
                    if (isSharedVideo(this.mMediaItem)) {
                        uri = MediaItemMde.getDownloadVideoUri(this.mMediaItem);
                    } else {
                        uri = ContentUri.getUri(this.mMediaItem);
                    }
                    if (uri != null) {
                        this.mMediaPlayer.setDataSource(AppResources.getAppContext(), uri);
                    } else {
                        this.mMediaPlayer.setDataSource(this.mDataSource);
                    }
                } else {
                    this.mMediaPlayer.setDataSource(this.mDataSource);
                }
                if (this.mMediaItem.isHlgVideo()) {
                    this.mMediaPlayer.setIsHdrVideo();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public synchronized void setSurfaceHolder() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.Object r0 = r4.LOCK     // Catch:{ Exception -> 0x0028 }
            monitor-enter(r0)     // Catch:{ Exception -> 0x0028 }
            com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat r1 = r4.mMediaPlayer     // Catch:{ all -> 0x0020 }
            android.view.Surface r2 = r4.mSurface     // Catch:{ all -> 0x0020 }
            r1.setSurface(r2)     // Catch:{ all -> 0x0020 }
            com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat r1 = r4.mMediaPlayer     // Catch:{ all -> 0x0020 }
            int r1 = r1.getFrameRate()     // Catch:{ all -> 0x0020 }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0020 }
            r3 = 31
            if (r2 < r3) goto L_0x0022
            if (r1 < 0) goto L_0x0022
            android.view.Surface r2 = r4.mSurface     // Catch:{ all -> 0x0020 }
            float r1 = (float) r1     // Catch:{ all -> 0x0020 }
            r2.setFrameRate(r1, 1, 0)     // Catch:{ all -> 0x0020 }
            goto L_0x0022
        L_0x0020:
            r1 = move-exception
            goto L_0x0024
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            goto L_0x0067
        L_0x0024:
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            throw r1     // Catch:{ Exception -> 0x0028 }
        L_0x0026:
            r0 = move-exception
            goto L_0x0069
        L_0x0028:
            r0 = move-exception
            com.samsung.android.gallery.support.utils.StringCompat r1 = r4.TAG     // Catch:{ all -> 0x0026 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0026 }
            r2.<init>()     // Catch:{ all -> 0x0026 }
            java.lang.String r3 = "setSurfaceHolder : MediaPlayer "
            r2.append(r3)     // Catch:{ all -> 0x0026 }
            com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat r3 = r4.mMediaPlayer     // Catch:{ all -> 0x0026 }
            r2.append(r3)     // Catch:{ all -> 0x0026 }
            java.lang.String r3 = ", Surface = "
            r2.append(r3)     // Catch:{ all -> 0x0026 }
            android.view.Surface r3 = r4.mSurface     // Catch:{ all -> 0x0026 }
            r2.append(r3)     // Catch:{ all -> 0x0026 }
            java.lang.String r3 = ", PlaybackState = "
            r2.append(r3)     // Catch:{ all -> 0x0026 }
            com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat r3 = r4.mMediaPlayer     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x0053
            int r3 = r3.getPlaybackState()     // Catch:{ all -> 0x0026 }
            goto L_0x0055
        L_0x0053:
            r3 = -99
        L_0x0055:
            r2.append(r3)     // Catch:{ all -> 0x0026 }
            java.lang.String r3 = ", Exception = "
            r2.append(r3)     // Catch:{ all -> 0x0026 }
            r2.append(r0)     // Catch:{ all -> 0x0026 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0026 }
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)     // Catch:{ all -> 0x0026 }
        L_0x0067:
            monitor-exit(r4)
            return
        L_0x0069:
            monitor-exit(r4)     // Catch:{ all -> 0x0026 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerDelegate.setSurfaceHolder():void");
    }

    /* access modifiers changed from: private */
    public void start() {
        try {
            synchronized (this.LOCK) {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.start();
                } else {
                    Log.e(this.TAG, "start failed. null player");
                }
            }
        } catch (Exception e) {
            onVideoError(this.mMediaPlayer, 0, 0);
            Log.e((CharSequence) this.TAG, "start failed", (Throwable) e);
        } catch (Throwable th) {
            throw th;
        }
    }

    private void updateMediaItem(MediaPlayerCompat mediaPlayerCompat) {
        if (this.mMediaItem != null) {
            int durationMs = mediaPlayerCompat.getDurationMs();
            if (this.mMediaItem.getFileDuration() != durationMs) {
                VideoPropData.of(this.mMediaItem).videoDurationInPlayer = durationMs;
            }
            VideoPropData.of(this.mMediaItem).videoFrameRate = mediaPlayerCompat.getFrameRate();
        }
    }

    public void addMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        if (!this.mMediaPlayerListener.contains(mediaPlayerListener)) {
            this.mMediaPlayerListener.add(mediaPlayerListener);
        }
    }

    public void beginInstantSlowMoPlay() {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.beginInstantSlowMoPlay();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void close() {
        close(false);
    }

    public void disableWfdTcp() {
        this.mWfdTcpDisabled = true;
    }

    public void endInstantSlowMoPlay() {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.endInstantSlowMoPlay();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getCurrentPosition() {
        int i2;
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    i2 = mediaPlayerCompat.getCurrentPositionMs();
                } else {
                    i2 = 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public int getDurationMs() {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat == null) {
                    return 0;
                }
                int durationMs = mediaPlayerCompat.getDurationMs();
                return durationMs;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Bitmap getFrameBitmap(int i2) {
        return getFrameBitmap(i2, false);
    }

    public int getFrameRate() {
        int i2;
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    i2 = mediaPlayerCompat.getFrameRate();
                } else {
                    i2 = 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public PlayState getPlayState() {
        return this.mState.get();
    }

    public int getRenderingPosition() {
        return this.mRenderingPosition;
    }

    public float getSpeed() {
        return this.mSpeed;
    }

    public int getVideoHeight() {
        int i2;
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    i2 = mediaPlayerCompat.getVideoHeight();
                } else {
                    i2 = 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public int getVideoWidth() {
        int i2;
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    i2 = mediaPlayerCompat.getVideoWidth();
                } else {
                    i2 = 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public boolean hasAudioTrack() {
        boolean z;
        synchronized (this.LOCK) {
            try {
                if (!isPrepared() || !this.mMediaPlayer.hasAudioTrack()) {
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public boolean isAudioMute() {
        return this.mAudioMute;
    }

    public boolean isSurfaceReady() {
        return this.mSurfaceReady.get().booleanValue();
    }

    public boolean isVisualSeeking() {
        return this.mVisualSeekStart;
    }

    public void log(String str) {
        Log.d(this.TAG, str);
    }

    public synchronized void onSurfaceCreated(Surface surface) {
        log("onSurfaceCreated");
        this.mSurface = surface;
        this.mSurfaceReady.set(Boolean.TRUE);
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onSurfaceCreated();
        }
    }

    public synchronized void onSurfaceDestroyed() {
        log("onSurfaceDestroyed");
        this.mSurface = null;
        this.mSurfaceReady.clear();
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onSurfaceDestroyed();
        }
    }

    public void onVideoCompleted(MediaPlayerCompat mediaPlayerCompat) {
        log("onVideoCompleted");
        this.mRenderingPosition = 0;
        this.mState.set(PlayState.COMPLETE);
        this.mPluginComposite.onVideoCompleted(mediaPlayerCompat);
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onVideoCompleted();
        }
    }

    public boolean onVideoInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        this.mPluginComposite.onVideoInfo(mediaPlayerCompat, i2, i7);
        switch (i2) {
            case 3:
                this.mRenderStart.set(Boolean.TRUE);
                return true;
            case 9000001:
                onTimeTickUpdate(mediaPlayerCompat.getDurationMs(), i7);
                return true;
            case 9000002:
                this.mRenderStart.when(Boolean.TRUE).then(new b(this, 4), "SET_STATE_TO_PLAY");
                return true;
            case 9000004:
                this.mState.set(PlayState.PAUSE);
                onVideoPaused(i7);
                return true;
            case 9000006:
                this.mState.set(PlayState.NONE);
                this.mRenderStart.set(Boolean.FALSE);
                onVideoReleased();
                return true;
            default:
                log("onVideoInfo w=" + i2);
                Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
                while (it.hasNext()) {
                    it.next().onVideoInfo(i2, i7);
                }
                return true;
        }
    }

    public void onVideoPrepared(MediaPlayerCompat mediaPlayerCompat) {
        int videoWidth = mediaPlayerCompat.getVideoWidth();
        int videoHeight = mediaPlayerCompat.getVideoHeight();
        log("onVideoPrepared" + Logger.v(Integer.valueOf(videoWidth), Integer.valueOf(videoHeight), Integer.valueOf(mediaPlayerCompat.getFrameRate())));
        updateMediaItem(mediaPlayerCompat);
        try {
            this.mSurfaceReady.when(Boolean.TRUE).then(new b(this, 5), "setSurfaceHolder");
            this.mState.set(PlayState.PREPARED);
            mediaPlayerCompat.setLooping(this.mLooping);
            this.mPluginComposite.onVideoPrepared(mediaPlayerCompat);
            Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
            while (it.hasNext()) {
                it.next().onVideoPrepared(videoWidth, videoHeight);
            }
        } catch (Exception e) {
            onVideoError(this.mMediaPlayer, 0, 0);
            Log.e((CharSequence) this.TAG, "onVideoPrepared failed", (Throwable) e);
        }
    }

    public void onVideoSizeChanged(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        this.mPluginComposite.onVideoSizeChanged(mediaPlayerCompat, i2, i7);
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onVideoSizeChanged(i2, i7);
        }
    }

    public void open(MediaItem mediaItem) {
        this.mMediaPlayer = createMediaPlayer(mediaItem);
        this.mDataSource = getDataSource(mediaItem);
        this.mMediaItem = mediaItem;
        this.mVisualSeekStart = false;
        try {
            setDataSource();
            this.mMediaPlayer.setAudioMute(this.mAudioMute);
            this.mState.set(PlayState.SET_DATA);
            this.mPluginComposite.open(mediaItem);
        } catch (Exception e) {
            onVideoError(this.mMediaPlayer, 9000200, 0);
            Log.e((CharSequence) this.TAG, "open failed", (Throwable) e);
        }
    }

    public void pause() {
        if (this.mState.get().value() >= PlayState.PLAY.value()) {
            pauseInternal();
        } else {
            this.mState.when(new C0468a(1)).then(new b(this, 3), "PAUSE");
        }
    }

    public void pauseOnReady(int i2) {
        log("pauseOnReady#" + this.mState.get());
        play();
        pause();
    }

    public void play() {
        log("play#" + this.mState.get());
        this.mState.when(new C0468a(8)).then(new b(this, 7), "WAIT PREPARE for SURFACE READY");
        this.mState.when(new C0468a(9)).then(new b(this, 8), "WAIT MP_START for PREPARED");
    }

    public void release3dEffect() {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.releaseSoundAlive();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void removeMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        this.mMediaPlayerListener.remove(mediaPlayerListener);
    }

    public void resume() {
        synchronized (this.LOCK) {
            MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
            if (mediaPlayerCompat != null) {
                try {
                    if (mediaPlayerCompat.getCurrentPositionMs() >= this.mMediaPlayer.getDurationMs() - 100) {
                        this.mMediaPlayer.seekTo(0);
                    }
                    StringCompat stringCompat = this.TAG;
                    Log.v(stringCompat, "resume " + this);
                    this.mMediaPlayer.start();
                } catch (Exception e) {
                    Log.e((CharSequence) this.TAG, "resume failed", (Throwable) e);
                }
            }
        }
    }

    public void seekBegin() {
        this.mVisualSeekStart = true;
        this.mState.when(new C0468a(10)).then(new b(this, 9), "SEEK BEGIN");
    }

    public void seekFinish() {
        this.mVisualSeekStart = false;
        this.mState.when(new C0468a(4)).then(new b(this, 2), "SEEK END");
    }

    public void seekTo(int i2) {
        this.mState.when(new C0468a(2)).then(new d(this, i2, 0), "SEEK");
    }

    public void set3dEffectPosition(double d) {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.set3dEffectPosition(d);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setAudioEraserOff(boolean z, String str) {
        synchronized (this.LOCK) {
            try {
                if (this.mState.get().value() >= PlayState.SET_DATA.value() && this.mMediaPlayer != null) {
                    if (!z) {
                        FileUtils.makeDirectoryIfAbsent(str);
                    }
                    this.mMediaPlayer.setAudioEraserOff(z, str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setAudioMute(boolean z) {
        MediaPlayerCompat mediaPlayerCompat;
        this.mAudioMute = z;
        synchronized (this.LOCK) {
            try {
                if (this.mState.get().value() >= PlayState.SET_DATA.value() && (mediaPlayerCompat = this.mMediaPlayer) != null) {
                    mediaPlayerCompat.setAudioMute(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setBgm(BgmOptions bgmOptions) {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.initBackgroundMusic(bgmOptions);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setColorCorrect() {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.setColorCorrect();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setIsPreview(boolean z) {
        this.mIsPreview = z;
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
        this.mState.setLogTag(obj);
        this.mRenderStart.setLogTag(obj);
        this.mSurfaceReady.setLogTag(obj);
    }

    public void setLooping(boolean z) {
        this.mLooping = z;
        synchronized (this.LOCK) {
            try {
                if (isPrepared()) {
                    this.mMediaPlayer.setLooping(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setPlaybackLoop(boolean z) {
        this.mPluginComposite.setPlaybackLoop(z);
    }

    public void setPlaybackRange(int i2, int i7) {
        this.mState.when(new C0468a(6)).then(new c(this, i2, i7, 1), "SetPlaybackRange");
    }

    public void setRenderingPosition(int i2) {
        this.mRenderingPosition = i2;
        log("setRenderingPosition p=" + i2);
        this.mState.when(new C0468a(7)).then(new b(this, 6), "SET_RENDER_POS");
    }

    public void setSlowMo(int i2, int i7, boolean z) {
        this.mPluginComposite.setSlowMo(i2, i7, z);
    }

    public void setSpeed(float f) {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    PlaybackParams playbackParam = mediaPlayerCompat.getPlaybackParam();
                    playbackParam.setSpeed(f);
                    this.mMediaPlayer.setPlaybackParam(playbackParam);
                    this.mSpeed = f;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setSubDataSource(long j2, long j3) {
        this.mSubDataSource = new long[]{j2, j3};
    }

    public void setSupportTimeTick(boolean z) {
        this.mSupportTimeTick = z;
    }

    public void setVideoFilter(String str, int i2) {
        synchronized (this.LOCK) {
            try {
                if (this.mMediaPlayer != null && !TextUtils.isEmpty(str)) {
                    this.mMediaPlayer.setVideoFilter(str, i2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setVideoFrc(int i2) {
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    mediaPlayerCompat.setVideoFrc(i2, 1.0f, false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setVolume(float f, float f5) {
        this.mState.when(new C0468a(5)).then(new g5.c(this, f, f5, 1), "setVolume");
    }

    public void updateItem(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }

    public void visualSeekTo(int i2) {
        if (!this.mVisualSeekStart) {
            seekBegin();
        }
        this.mState.when(new C0468a(3)).then(new d(this, i2, 1), "visualSeekTo");
    }

    public void close(boolean z) {
        log("close reset=".concat(z ? "T" : "F"));
        destroyMediaPlayer();
        this.mPluginComposite.close();
        this.mState.clear();
        this.mRenderStart.clear();
        this.mSurfaceReady.clearTrigger();
        this.mMediaItem = null;
        this.mSubDataSource = null;
        this.mDataSource = null;
        this.mVisualSeekStart = false;
        this.mRenderingPosition = 0;
        if (z) {
            this.mLooping = false;
            this.mAudioMute = true;
        }
        this.mSpeed = 1.0f;
    }

    public Bitmap getFrameBitmap(int i2, boolean z) {
        Bitmap bitmap;
        MediaMetadataRetriever mediaMetadataRetriever;
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        PlayState playState = this.mState.get();
        if (playState == PlayState.PLAY) {
            pause();
        }
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                if (mediaPlayerCompat != null) {
                    int videoWidth = mediaPlayerCompat.getVideoWidth();
                    int videoHeight = this.mMediaPlayer.getVideoHeight();
                    if (i2 > 0) {
                        while (true) {
                            if (videoWidth <= i2) {
                                if (videoHeight <= i2) {
                                    break;
                                }
                            }
                            videoWidth /= 2;
                            videoHeight /= 2;
                        }
                        int orientation = getOrientation();
                        if (!SdkConfig.atLeast(SdkConfig.SEM.V) && videoWidth != this.mMediaPlayer.getVideoWidth() && (orientation == 90 || orientation == 270)) {
                            int i7 = videoHeight;
                            videoHeight = videoWidth;
                            videoWidth = i7;
                        }
                    }
                    bitmap = z ? this.mMediaPlayer.getCurrentFrameSdr(videoWidth, videoHeight) : this.mMediaPlayer.getCurrentFrame(videoWidth, videoHeight);
                } else {
                    bitmap = null;
                }
            } finally {
                while (true) {
                    Throwable th3 = th;
                }
            }
        }
        if (bitmap == null && this.mMediaPlayer != null) {
            try {
                mediaMetadataRetriever = new MediaMetadataRetriever();
                if (this.mSubDataSource != null) {
                    fileInputStream = new FileInputStream(this.mDataSource);
                    FileDescriptor fd2 = fileInputStream.getFD();
                    long[] jArr = this.mSubDataSource;
                    mediaMetadataRetriever.setDataSource(fd2, jArr[0], jArr[1]);
                    fileInputStream.close();
                } else if (this.mMediaItem.isUriItem()) {
                    mediaMetadataRetriever.setDataSource(AppResources.getAppContext(), Uri.parse(this.mDataSource));
                } else {
                    mediaMetadataRetriever.setDataSource(this.mDataSource);
                }
                synchronized (this.LOCK) {
                    MediaPlayerCompat mediaPlayerCompat2 = this.mMediaPlayer;
                    if (mediaPlayerCompat2 != null) {
                        int videoWidth2 = mediaPlayerCompat2.getVideoWidth();
                        int videoHeight2 = this.mMediaPlayer.getVideoHeight();
                        if (i2 > 0) {
                            while (true) {
                                if (videoWidth2 <= i2) {
                                    if (videoHeight2 <= i2) {
                                        break;
                                    }
                                }
                                videoWidth2 /= 2;
                                videoHeight2 /= 2;
                            }
                            int orientation2 = getOrientation();
                            if (videoWidth2 != this.mMediaPlayer.getVideoWidth() && (orientation2 == 90 || orientation2 == 270)) {
                                int i8 = videoHeight2;
                                videoHeight2 = videoWidth2;
                                videoWidth2 = i8;
                            }
                            SeApiCompat.setVideoSize(mediaMetadataRetriever, videoWidth2, videoHeight2, true);
                        }
                        bitmap = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, ((long) this.mMediaPlayer.getCurrentPositionMs()) * 1000, 2);
                    }
                }
                mediaMetadataRetriever.close();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "getFrame failed", (Throwable) e);
            } catch (Throwable th4) {
                th.addSuppressed(th4);
            }
        }
        if (playState == PlayState.PLAY) {
            resume();
        }
        if (bitmap == null) {
            Log.e(this.TAG, "frameBitmap is null ".concat(this.mMediaPlayer == null ? "mediaPlayer is null" : ""));
        }
        return bitmap;
        throw th2;
        throw th;
    }
}
