package com.samsung.android.gallery.plugins.motionphoto;

import A.a;
import A2.d;
import A4.A;
import A4.H;
import A9.b;
import Ba.c;
import Ba.j;
import Ba.n;
import Ba.o;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import android.view.SurfaceHolder;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.media.AudioManagerHelper;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.HandlerThreadWatched;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerFactory;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MotionPhotoViewPlayer implements SurfaceHolder.Callback {
    private static final boolean PHOTO_STRIP_41 = PreferenceFeatures.isEnabled(PreferenceFeatures.PhotoStrip41);
    private static final boolean SUPPORT_CROP_VIDEO_CAPTURE = PreferenceFeatures.isEnabled(PreferenceFeatures.CropVideoCapture);
    protected final String HASH_TAG = ("VVP@" + Integer.toHexString(hashCode()));
    protected final Object LOCK = new Object();
    protected final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private boolean mAudioFocusSupported = true;
    private final AudioManagerHelper mAudioManager;
    protected boolean mAudioMute = true;
    private boolean mAutoReplay;
    protected Consumer<Bitmap> mCaptureListener;
    protected String mDataSource;
    private int mDuration = -1;
    private boolean mEnabled;
    protected MediaItem mMediaItem;
    protected MediaPlayerCompat mMediaPlayer;
    private final AtomicBoolean mOpenOnResume = new AtomicBoolean(false);
    private final AtomicBoolean mPlayPostponed = new AtomicBoolean(false);
    protected final AtomicBoolean mPlayState = new AtomicBoolean(false);
    private final Runnable mPrepareVideo = new n(this, 2);
    private int mRenderingPosition;
    private final AtomicBoolean mStartOnPrepared = new AtomicBoolean(true);
    private int mStopPosition;
    protected long[] mSubDataSource;
    protected boolean mSupportTimeTick;
    protected VideoCallback mVideoCallback;
    MediaHelper.VideoInfo mVideoInfo;
    MotionPhotoViewCompat mVideoView;
    protected boolean mWfdTcpDisabled;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface VideoCallback {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VideoThread {
        private static final Handler sHandler;
        private static final HandlerThread sThread;

        static {
            HandlerThread createVideoThread = createVideoThread();
            sThread = createVideoThread;
            sHandler = new Handler(createVideoThread.getLooper());
        }

        private static HandlerThread createVideoThread() {
            HandlerThreadWatched handlerThreadWatched = new HandlerThreadWatched("VideoThread", 10);
            handlerThreadWatched.start();
            return handlerThreadWatched;
        }

        public static void post(Runnable runnable) {
            sHandler.post(runnable);
        }

        public static void postDelayed(Runnable runnable, long j2) {
            sHandler.postDelayed(runnable, j2);
        }

        public static void removeCallbacks(Runnable runnable) {
            sHandler.removeCallbacks(runnable);
        }
    }

    public MotionPhotoViewPlayer(Context context) {
        this.mAudioManager = new AudioManagerHelper(context, new AudioManagerHelper.OnAudioFocusManagerListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onAudioDucked$0(boolean z) {
                MotionPhotoViewPlayer motionPhotoViewPlayer = MotionPhotoViewPlayer.this;
                motionPhotoViewPlayer.setAudioMute(motionPhotoViewPlayer.mMediaPlayer, z);
            }

            public void onAudioDucked(boolean z) {
                StringCompat stringCompat = MotionPhotoViewPlayer.this.TAG;
                Log.v(stringCompat, "onAudioDucked{" + z + "}");
                if (!MotionPhotoViewPlayer.this.mAudioMute) {
                    VideoThread.post(new a(this, z));
                    MotionPhotoViewPlayer motionPhotoViewPlayer = MotionPhotoViewPlayer.this;
                    motionPhotoViewPlayer.notifyCallback(motionPhotoViewPlayer.mMediaPlayer, 9000010, z ? 1 : 0);
                }
            }

            public void onAudioEnabled(boolean z) {
                StringCompat stringCompat = MotionPhotoViewPlayer.this.TAG;
                Log.v(stringCompat, "onAudioEnabled{" + z + "}");
                MotionPhotoViewPlayer motionPhotoViewPlayer = MotionPhotoViewPlayer.this;
                motionPhotoViewPlayer.notifyCallback(motionPhotoViewPlayer.mMediaPlayer, 9000009, z ? 1 : 0);
            }
        });
    }

    private void checkSharedVideoFailCase() {
        MediaItem mediaItem;
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY) && (mediaItem = this.mMediaItem) != null && mediaItem.isSharing() && MediaItemMde.isDownloadVideoVerified(this.mMediaItem)) {
            MediaItemMde.initializeVerifyStatus(this.mMediaItem);
            Blackboard.postEventGlobal(EventMessage.obtain(6011));
        }
    }

    private void initDynamicViewPlayback(MediaPlayerCompat mediaPlayerCompat) {
        DynamicViewPlayInfo dynamicViewPlayInfo;
        if (DynamicViewData.hasPlayInfo(this.mMediaItem) && (dynamicViewPlayInfo = DynamicViewData.of(this.mMediaItem).dynamicViewPlayInfo) != null) {
            dynamicViewPlayInfo.initPlayBack(mediaPlayerCompat, new H(1, (Object) this, (Object) mediaPlayerCompat), this.mRenderingPosition);
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "" + dynamicViewPlayInfo);
        }
    }

    private void initPlaybackRenderPosition(MediaItem mediaItem) {
        if (DynamicViewData.hasPlayInfo(mediaItem) && mediaItem.getVideoThumbStartTime() > 0) {
            this.mRenderingPosition = (int) (mediaItem.getVideoThumbStartTime() / 1000);
        }
        Integer num = VideoPropData.of(mediaItem).videoResumePos;
        if (num != null) {
            this.mRenderingPosition = num.intValue();
        }
    }

    private boolean isInPreviewState() {
        return this.mAutoReplay;
    }

    private boolean isSharedMotionPhoto(MediaItem mediaItem) {
        if (!mediaItem.isSharing() || !mediaItem.isMotionPhoto()) {
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

    private boolean isSurfaceReady() {
        MotionPhotoViewCompat motionPhotoViewCompat = this.mVideoView;
        if (motionPhotoViewCompat == null || !motionPhotoViewCompat.isSurfaceReady()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$computeVideo$1(Consumer consumer, MediaHelper.VideoInfo videoInfo) {
        this.mVideoInfo = videoInfo;
        ThreadUtil.postOnUiThread(new d(19, consumer, videoInfo));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$computeVideo$2(MediaItem mediaItem, Consumer consumer) {
        MetadataManager.getInstance().load(mediaItem, new VideoReqInfo.Builder().build(), new A(3, (Object) this, (Object) consumer));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initDynamicViewPlayback$10(MediaPlayerCompat mediaPlayerCompat) {
        if (notifyCallback(mediaPlayerCompat, 9000005, 0)) {
            stopPlayback();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoRendered$11() {
        ViewUtils.setVisibility(this.mVideoView, 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoRendered$12(MediaPlayerCompat mediaPlayerCompat, int i2) {
        if (this.mPlayState.get()) {
            notifyCallback(mediaPlayerCompat, i2, getCurrentPosition());
        } else {
            Log.w(this.TAG, "onVideoRendered skip");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setAudioMute$3(boolean z) {
        setAudioMute(this.mMediaPlayer, z);
        setAudioFocusEnabled(!z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$stopPlayback$5(MediaPlayerCompat mediaPlayerCompat, Runnable runnable) {
        notifyCallback(mediaPlayerCompat, 9000003, 0);
        releaseVideo(mediaPlayerCompat);
        if (runnable != null) {
            runnable.run();
        }
    }

    private boolean pauseVideo(MediaPlayerCompat mediaPlayerCompat) {
        if (mediaPlayerCompat == null) {
            return false;
        }
        try {
            mediaPlayerCompat.pause();
            int renderingPosition = mediaPlayerCompat.getRenderingPosition();
            this.mRenderingPosition = renderingPosition;
            if (renderingPosition == 0) {
                this.mRenderingPosition = mediaPlayerCompat.getCurrentPositionMs();
            }
            StringCompat stringCompat = this.TAG;
            Log.v(stringCompat, "pauseVideo " + this);
            return true;
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "pauseVideo failed", (Throwable) e);
            return false;
        }
    }

    private boolean resumeVideo(MediaPlayerCompat mediaPlayerCompat) {
        if (ensurePlayable() && mediaPlayerCompat != null) {
            try {
                if (mediaPlayerCompat.getCurrentPositionMs() >= mediaPlayerCompat.getDurationMs() - 100) {
                    mediaPlayerCompat.seekTo(0);
                }
                StringCompat stringCompat = this.TAG;
                Log.v(stringCompat, "resumeVideo " + this);
                mediaPlayerCompat.start();
                if (ensureAudioFocusable()) {
                    setAudioFocusEnabled(true);
                }
                return true;
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "resumeVideo failed", (Throwable) e);
            }
        }
        return false;
    }

    private void seekToAdaptively(MediaPlayerCompat mediaPlayerCompat, int i2) {
        if (mediaPlayerCompat != null) {
            try {
                mediaPlayerCompat.seekToAdaptively(i2);
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "seekToAdaptively failed", (Throwable) e);
            }
        }
    }

    private void seekToVideo(MediaPlayerCompat mediaPlayerCompat, int i2) {
        if (mediaPlayerCompat != null) {
            try {
                mediaPlayerCompat.seekTo(i2);
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "seekToVideo failed", (Throwable) e);
            }
        }
    }

    private void setDynamicViewConfig(MediaPlayerCompat mediaPlayerCompat, MediaItem mediaItem) {
        if (DynamicViewData.hasPlayInfo(mediaItem)) {
            DynamicViewPlayInfo dynamicViewPlayInfo = DynamicViewData.of(mediaItem).dynamicViewPlayInfo;
            if (dynamicViewPlayInfo != null) {
                mediaPlayerCompat.setDynamicViewConfiguration(dynamicViewPlayInfo.getDynamicViewConfig());
            } else {
                Log.e(this.TAG, "fail to setDynamicViewConfig");
            }
        }
    }

    private void setSurfaceFrameRate(SurfaceHolder surfaceHolder) {
        Surface surface = surfaceHolder.getSurface();
        int frameRate = this.mMediaPlayer.getFrameRate();
        if (Build.VERSION.SDK_INT < 31) {
            return;
        }
        if (frameRate >= 0) {
            StringCompat stringCompat = this.TAG;
            Log.v(stringCompat, "setFrameRate " + frameRate + " to " + surface);
            surface.setFrameRate((float) frameRate, 1, 0);
            return;
        }
        Log.w(this.TAG, "setFrameRate fail");
    }

    private boolean supportBgmVideo() {
        return DynamicViewData.hasPlayInfo(this.mMediaItem);
    }

    private boolean willStartOnPrepared() {
        if (this.mStartOnPrepared.get() || isInPreviewState()) {
            return true;
        }
        return false;
    }

    public void activatePlayback(boolean z) {
        this.mOpenOnResume.set(z);
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "activatePlayback " + this);
        if (z && this.mMediaPlayer == null && isSurfaceReady() && this.mDataSource != null && this.mRenderingPosition >= 0 && this.mOpenOnResume.getAndSet(false)) {
            startPlayback();
        }
    }

    public void captureFrame(Consumer<Bitmap> consumer) {
        if (isInPlayState()) {
            this.mCaptureListener = consumer;
            VideoThread.post(new n(this, 0));
        }
    }

    public Bitmap captureFrameInner() {
        Bitmap bitmap;
        MediaMetadataRetriever mediaMetadataRetriever;
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        long currentTimeMillis = System.currentTimeMillis();
        boolean isStarted = isStarted();
        if (isStarted) {
            pausePlayback();
        }
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat != null) {
            bitmap = mediaPlayerCompat.getCurrentFrame(mediaPlayerCompat.getVideoWidth(), mediaPlayerCompat.getVideoHeight());
        } else {
            bitmap = null;
        }
        if (bitmap == null && mediaPlayerCompat != null) {
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
                bitmap = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, ((long) mediaPlayerCompat.getCurrentPositionMs()) * 1000, 2);
                mediaMetadataRetriever.close();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "getFrame failed", (Throwable) e);
            } catch (Throwable th3) {
                th.addSuppressed(th3);
            }
        }
        if (isStarted) {
            resumePlayback();
        }
        Bitmap croppedBitmap = getCroppedBitmap(bitmap);
        synchronized (this.LOCK) {
            try {
                Consumer<Bitmap> consumer = this.mCaptureListener;
                if (consumer != null) {
                    consumer.accept(croppedBitmap);
                    this.mCaptureListener = null;
                }
            } catch (Throwable th4) {
                while (true) {
                    throw th4;
                }
            }
        }
        Log.d(this.TAG, "captureFrameInner {" + this.mRenderingPosition + "}, " + Logger.toSimpleString(bitmap) + " " + Logger.toSimpleString(croppedBitmap) + " +" + (System.currentTimeMillis() - currentTimeMillis));
        return croppedBitmap;
        throw th2;
        throw th;
    }

    public void completeSeekTo(boolean z) {
        if (isInPlayState()) {
            try {
                this.mMediaPlayer.completeSeekTo(z);
                this.mRenderingPosition = this.mMediaPlayer.getCurrentPositionMs();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "completeSeekTo failed", (Throwable) e);
            }
        }
    }

    public void computeVideo(MediaItem mediaItem, Consumer<MediaHelper.VideoInfo> consumer) {
        MediaHelper.VideoInfo videoInfo = MetadataManager.getInstance().get(mediaItem);
        this.mVideoInfo = videoInfo;
        if (videoInfo == null || isSharedMotionPhoto(mediaItem) || isSharedVideo(mediaItem)) {
            VideoThread.post(new b(this, mediaItem, consumer, 2));
        } else {
            consumer.accept(this.mVideoInfo);
        }
    }

    public MediaPlayerCompat createPlayer() {
        if (supportBgmVideo()) {
            return MediaPlayerFactory.createBgmVideoPlayer();
        }
        return MediaPlayerFactory.createMediaPlayer(MediaPlayerFactory.preferSecPlayer(this.mMediaItem));
    }

    public void enablePlay(boolean z) {
        this.mEnabled = z;
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "enablePlay " + this);
        if (z && this.mMediaPlayer == null && ensurePlayable()) {
            openVideo();
        }
    }

    public boolean ensureAudioFocusable() {
        return !this.mAudioMute;
    }

    public boolean ensurePlayable() {
        if (this.mDataSource == null || !this.mPlayState.get() || !isSurfaceReady()) {
            return false;
        }
        return true;
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        MotionPhotoViewCompat motionPhotoViewCompat;
        if (!SUPPORT_CROP_VIDEO_CAPTURE || (motionPhotoViewCompat = this.mVideoView) == null) {
            return bitmap;
        }
        Rect cropRectOnImage = motionPhotoViewCompat.getCropRectOnImage(bitmap.getWidth(), bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, cropRectOnImage.left, cropRectOnImage.top, cropRectOnImage.width(), cropRectOnImage.height());
        if (bitmap != createBitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public int getCurrentPosition() {
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat == null) {
            return 0;
        }
        try {
            return mediaPlayerCompat.getCurrentPositionMs();
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "getCurrentPosition failed", (Throwable) e);
            return 0;
        }
    }

    public int getDuration() {
        return this.mDuration;
    }

    public boolean isInPlayState() {
        if (!this.mPlayState.get() || this.mMediaPlayer == null) {
            return false;
        }
        return true;
    }

    public boolean isPlaying() {
        MediaPlayerCompat mediaPlayerCompat;
        if (!isInPlayState() || (mediaPlayerCompat = this.mMediaPlayer) == null || !mediaPlayerCompat.isPlaying()) {
            return false;
        }
        return true;
    }

    public boolean isPrepared() {
        MediaPlayerCompat mediaPlayerCompat;
        if (!isInPlayState() || (mediaPlayerCompat = this.mMediaPlayer) == null || !mediaPlayerCompat.isPrepared()) {
            return false;
        }
        return true;
    }

    public boolean isSeekable() {
        MediaPlayerCompat mediaPlayerCompat;
        if (!this.mPlayState.get() || (mediaPlayerCompat = this.mMediaPlayer) == null || !mediaPlayerCompat.isPrepared()) {
            return false;
        }
        return true;
    }

    public boolean isStarted() {
        MediaPlayerCompat mediaPlayerCompat;
        if (!isInPlayState() || (mediaPlayerCompat = this.mMediaPlayer) == null || mediaPlayerCompat.getPlaybackState() != 4) {
            return false;
        }
        return true;
    }

    public boolean notifyCallback(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        VideoCallback videoCallback = this.mVideoCallback;
        if (videoCallback == null || !((c) videoCallback).d.onMediaPlayerInfo(mediaPlayerCompat, i2, i7)) {
            return false;
        }
        return true;
    }

    public void onVideoCompleted(MediaPlayerCompat mediaPlayerCompat) {
        this.mRenderingPosition = 0;
        this.mStartOnPrepared.set(!PreferenceFeatures.VideoPlayerFeature.isSupportFilmSeeker());
        notifyCallback(mediaPlayerCompat, 9000005, 0);
    }

    public boolean onVideoError(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        Log.e(this.TAG, a.d(i2, i7, "onVideoError {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        if (notifyCallback(mediaPlayerCompat, 9000007, i7)) {
            return true;
        }
        stopPlayback();
        return true;
    }

    public boolean onVideoInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        if (i2 == 3) {
            onVideoRendered(mediaPlayerCompat, i2, i7);
            return true;
        }
        notifyCallback(mediaPlayerCompat, i2, i7);
        return true;
    }

    public void onVideoPrepared(MediaPlayerCompat mediaPlayerCompat) {
        SurfaceHolder surfaceHolder;
        MediaHelper.VideoInfo videoInfo;
        int videoWidth = mediaPlayerCompat.getVideoWidth();
        int videoHeight = mediaPlayerCompat.getVideoHeight();
        if (!(this.mVideoView == null || (((videoInfo = this.mVideoInfo) == null || !videoInfo.compareAndSet(mediaPlayerCompat.getVideoWidth(), mediaPlayerCompat.getVideoHeight())) && videoWidth == this.mVideoView.getWidth() && videoHeight == this.mVideoView.getHeight()))) {
            MotionPhotoViewCompat motionPhotoViewCompat = this.mVideoView;
            Log.w(this.TAG, "onVideoPrepared setVideoSize " + videoWidth + "x" + videoHeight);
            motionPhotoViewCompat.setVideoSize(mediaPlayerCompat.getVideoWidth(), mediaPlayerCompat.getVideoHeight());
            motionPhotoViewCompat.post(new j(motionPhotoViewCompat, 3));
        }
        this.mDuration = mediaPlayerCompat.getDurationMs();
        if (!this.mPlayState.get()) {
            Log.w(this.TAG, "onVideoPrepared skip. already stop");
            stopPlayback();
            return;
        }
        notifyCallback(mediaPlayerCompat, 9000008, 0);
        try {
            MotionPhotoViewCompat motionPhotoViewCompat2 = this.mVideoView;
            if (motionPhotoViewCompat2 != null) {
                surfaceHolder = motionPhotoViewCompat2.getHolder();
            } else {
                surfaceHolder = null;
            }
            if (surfaceHolder != null) {
                Log.v(this.TAG, "onVideoPrepared " + this);
                mediaPlayerCompat.setLooping(isInPreviewState());
                mediaPlayerCompat.setDisplay(surfaceHolder);
                int i2 = this.mRenderingPosition;
                if (i2 > -1) {
                    mediaPlayerCompat.seekToAdaptively(i2);
                    VideoPropData.of(this.mMediaItem).videoResumePos = null;
                }
                if (DynamicViewData.hasPlayInfo(this.mMediaItem)) {
                    mediaPlayerCompat.setLooping(false);
                    initDynamicViewPlayback(mediaPlayerCompat);
                }
                setSurfaceFrameRate(surfaceHolder);
                if (!this.mStartOnPrepared.getAndSet(false)) {
                    if (!isInPreviewState()) {
                        return;
                    }
                }
                mediaPlayerCompat.start();
                return;
            }
            Log.w(this.TAG, "onVideoPrepared no holder " + this);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "onVideoPrepared failed", (Throwable) e);
            stopPlayback();
        }
    }

    public void onVideoRendered(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        if (this.mVideoCallback != null) {
            if (!ViewUtils.isVisible(this.mVideoView)) {
                ViewUtils.post(this.mVideoView, new n(this, 1));
            }
            ThreadUtil.postOnUiThreadDelayed(new Ab.b((Object) this, (Object) mediaPlayerCompat, i2, 2), 52);
        }
    }

    public void openVideo() {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "openVideo " + this);
        if (ensurePlayable()) {
            VideoThread.removeCallbacks(this.mPrepareVideo);
            VideoThread.postDelayed(this.mPrepareVideo, 10);
            return;
        }
        Log.w(this.TAG, "openVideo skip");
    }

    public boolean pausePlayback() {
        if (!pauseVideo(this.mMediaPlayer)) {
            return false;
        }
        this.mOpenOnResume.set(true);
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "pausePlayback " + this);
        return true;
    }

    public void prepareSeekTo() {
        if (isInPlayState()) {
            try {
                this.mMediaPlayer.prepareSeekTo();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "prepareSeekTo failed", (Throwable) e);
            }
        }
    }

    public void prepareVideo() {
        Uri uri;
        Log.d(this.TAG, "prepareVideo");
        releaseVideo(this.mMediaPlayer);
        if (!ensurePlayable()) {
            Log.d(this.TAG, "prepareVideo skip");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (ensureAudioFocusable() && willStartOnPrepared()) {
            setAudioFocusEnabled(true);
        }
        MediaPlayerCompat createPlayer = createPlayer();
        try {
            if (!PocFeatures.isEnabled(PocFeatures.WifiGalleryClient) || this.mMediaItem.getStorageType() != StorageType.RemoteItem) {
                long[] jArr = this.mSubDataSource;
                if (jArr != null) {
                    createPlayer.setDataSource(this.mDataSource, jArr[0], jArr[1]);
                } else if (this.mMediaItem.isUriItem()) {
                    createPlayer.setDataSource(AppResources.getAppContext(), Uri.parse(this.mDataSource));
                } else if (SdkConfig.atLeast(SdkConfig.GED.S)) {
                    if (isSharedVideo(this.mMediaItem)) {
                        uri = MediaItemMde.getDownloadVideoUri(this.mMediaItem);
                    } else {
                        uri = ContentUri.getUri(this.mMediaItem);
                    }
                    if (uri != null) {
                        createPlayer.setDataSource(AppResources.getAppContext(), uri);
                    } else {
                        createPlayer.setDataSource(this.mDataSource);
                    }
                } else {
                    createPlayer.setDataSource(this.mDataSource);
                }
            } else if (this.mMediaItem.getFileSize() < 104857600) {
                String downloadToTemp = RemoteGalleryUtil.downloadToTemp(this.mMediaItem);
                if (downloadToTemp != null) {
                    this.mDataSource = downloadToTemp;
                    if (this.mSubDataSource != null) {
                        MediaItem clone = this.mMediaItem.clone();
                        clone.setPath(downloadToTemp);
                        MediaHelper.VideoInfo load = MetadataManager.getInstance().lambda$preload$0(clone);
                        long[] jArr2 = this.mSubDataSource;
                        long j2 = load.offset;
                        jArr2[0] = j2;
                        long j3 = load.length;
                        jArr2[1] = j3;
                        createPlayer.setDataSource(downloadToTemp, j2, j3);
                    } else {
                        createPlayer.setDataSource(downloadToTemp);
                    }
                } else {
                    Log.w(this.TAG, "download fail");
                }
            } else {
                Log.w(this.TAG, "too big to download and play");
            }
            createPlayer.setAudioMute(this.mAudioMute);
            if (this.mWfdTcpDisabled) {
                createPlayer.setWfdTcpDisable();
            }
            if (DynamicViewData.hasPlayInfo(this.mMediaItem)) {
                setDynamicViewConfig(createPlayer, this.mMediaItem);
            }
            createPlayer.setOnInfoListener(new o(this));
            createPlayer.setOnPreparedListener(new o(this));
            createPlayer.setOnErrorListener(new o(this));
            createPlayer.setOnCompletionListener(new o(this));
            createPlayer.setTimeTickEnabled(this.mSupportTimeTick);
            createPlayer.prepareAsync();
            synchronized (this.LOCK) {
                if (this.mPlayState.get()) {
                    this.mMediaPlayer = createPlayer;
                } else {
                    throw new IllegalStateException("player state stop already");
                }
            }
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "prepareVideo " + this + " +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "prepareVideo failed", (Throwable) e);
            releaseVideo(createPlayer);
            checkSharedVideoFailCase();
        }
    }

    public void releaseVideo(MediaPlayerCompat mediaPlayerCompat) {
        if (mediaPlayerCompat != null) {
            try {
                Log.v(this.TAG, "releaseVideo");
                mediaPlayerCompat.reset();
                mediaPlayerCompat.release();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "releaseVideo failed", (Throwable) e);
            }
            setAudioFocusEnabled(false);
        }
    }

    public boolean resumePlayback() {
        if (!resumeVideo(this.mMediaPlayer)) {
            return false;
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "resumePlayback " + this);
        return true;
    }

    public void seekTo(int i2) {
        if (isSeekable()) {
            seekToVideo(this.mMediaPlayer, i2);
        } else if (PHOTO_STRIP_41) {
            setRenderingPosition(i2);
        }
    }

    public void setAudioFocusEnabled(boolean z) {
        if (this.mAudioFocusSupported) {
            this.mAudioManager.setAudioFocusEnabled(z);
        }
    }

    public void setAudioMute(boolean z) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "setAudioMute {" + z + "}");
        this.mAudioMute = z;
        if (isInPlayState()) {
            VideoThread.post(new B4.c((Object) this, z, 2));
            return;
        }
        setAudioMute(this.mMediaPlayer, z);
        setAudioFocusEnabled(!z);
    }

    public void setLooping(boolean z) {
        this.mAutoReplay = z;
    }

    public void setRenderingPosition(int i2) {
        this.mRenderingPosition = i2;
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "setRenderingPosition " + this);
        if (isPrepared()) {
            seekToAdaptively(this.mMediaPlayer, i2);
        }
    }

    public void setStartOnPrepared(boolean z) {
        this.mStartOnPrepared.set(z);
    }

    public void setTimeTickEnabled(boolean z) {
        this.mSupportTimeTick = z;
    }

    public void setVideoCallback(VideoCallback videoCallback) {
        this.mVideoCallback = videoCallback;
    }

    public void setVideoData(MediaItem mediaItem, long j2, long j3) {
        setVideoData(mediaItem);
        this.mSubDataSource = new long[]{j2, j3};
    }

    public void setVideoPath(String str) {
        this.mDataSource = str;
        this.mRenderingPosition = 0;
        this.mStartOnPrepared.set(this.mEnabled);
    }

    public void startPlayback() {
        this.mPlayState.set(true);
        openVideo();
    }

    public void stopPlayback() {
        stopPlayback(0, (Runnable) null);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "surfaceCreated " + this);
        if (this.mMediaPlayer != null || !this.mEnabled) {
            Log.d(this.TAG, "disabled");
        } else {
            startPlayback();
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "surfaceDestroyed " + this);
        if (this.mMediaPlayer != null) {
            updateStopPosition();
            stopPlayback();
        }
    }

    public String toString() {
        int i2;
        char c5;
        char c6;
        char c8;
        char c10;
        char c11;
        char c12;
        char c13;
        char c14;
        char c15;
        String str;
        MotionPhotoViewCompat motionPhotoViewCompat = this.mVideoView;
        if (motionPhotoViewCompat != null) {
            i2 = motionPhotoViewCompat.getVisibility();
        } else {
            i2 = 8;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.HASH_TAG);
        sb2.append("{");
        if (i2 == 0) {
            c5 = 'V';
        } else if (i2 == 4) {
            c5 = 'I';
        } else {
            c5 = 'G';
        }
        sb2.append(c5);
        char c16 = 'o';
        if (ViewUtils.isOpaque(motionPhotoViewCompat)) {
            c6 = 'O';
        } else {
            c6 = 'o';
        }
        sb2.append(c6);
        char c17 = 's';
        if (isSurfaceReady()) {
            c8 = 'S';
        } else {
            c8 = 's';
        }
        sb2.append(c8);
        sb2.append(':');
        if (this.mPlayState.get()) {
            c10 = 'P';
        } else {
            c10 = 'p';
        }
        sb2.append(c10);
        if (this.mOpenOnResume.get()) {
            c16 = 'O';
        }
        sb2.append(c16);
        if (this.mStartOnPrepared.get()) {
            c17 = 'S';
        }
        sb2.append(c17);
        if (this.mMediaPlayer != null) {
            c11 = 'M';
        } else {
            c11 = 'm';
        }
        sb2.append(c11);
        if (this.mDataSource != null) {
            c12 = 'D';
        } else {
            c12 = 'd';
        }
        sb2.append(c12);
        if (this.mAudioMute) {
            c13 = 'a';
        } else {
            c13 = 'A';
        }
        sb2.append(c13);
        if (this.mEnabled) {
            c14 = 'E';
        } else {
            c14 = 'e';
        }
        sb2.append(c14);
        if (isInPreviewState()) {
            c15 = 'R';
        } else {
            c15 = 'r';
        }
        sb2.append(c15);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (motionPhotoViewCompat != null) {
            str = motionPhotoViewCompat.getLeft() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + motionPhotoViewCompat.getTop() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + motionPhotoViewCompat.getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + motionPhotoViewCompat.getHeight();
        } else {
            str = "null";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, this.mRenderingPosition, "}");
    }

    public void updateBackgroundColor(boolean z) {
        int i2;
        MotionPhotoViewCompat motionPhotoViewCompat = this.mVideoView;
        if (motionPhotoViewCompat != null) {
            if (z) {
                i2 = 0;
            } else {
                i2 = motionPhotoViewCompat.getContext().getColor(R$color.daynight_default_background);
            }
            this.mVideoView.setBackgroundColor(i2);
        }
    }

    public void updateStopPosition() {
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat != null) {
            this.mStopPosition = mediaPlayerCompat.getCurrentPositionMs();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "updateStopPosition: " + this.mStopPosition);
        }
    }

    public void stopPlayback(long j2) {
        stopPlayback(j2, (Runnable) null);
    }

    public void setVideoData(MediaItem mediaItem) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "setVideoData " + MediaItemUtil.getSimpleLog(mediaItem));
        this.mMediaItem = mediaItem;
        setVideoPath(mediaItem.isSharing() ? MediaItemMde.getDownloadVideoPath(mediaItem) : VideoPropData.getLongExposurePath(mediaItem));
        initPlaybackRenderPosition(mediaItem);
    }

    public void stopPlayback(long j2, Runnable runnable) {
        this.mPlayState.set(false);
        VideoThread.removeCallbacks(this.mPrepareVideo);
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                this.mMediaPlayer = null;
                if (mediaPlayerCompat != null) {
                    StringCompat stringCompat = this.TAG;
                    Log.d(stringCompat, "stopPlayback(" + j2 + ")");
                    VideoThread.postDelayed(new b(this, mediaPlayerCompat, runnable, 1), j2);
                } else if (runnable != null) {
                    runnable.run();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public void setAudioMute(MediaPlayerCompat mediaPlayerCompat, boolean z) {
        if (mediaPlayerCompat != null) {
            try {
                mediaPlayerCompat.setAudioMute(z);
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "setAudioMute failed", (Throwable) e);
            }
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i7, int i8) {
    }
}
