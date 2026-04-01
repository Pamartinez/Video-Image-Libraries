package com.samsung.android.gallery.widget.previewable;

import A.a;
import A4.M;
import Bb.g;
import Qb.b;
import Qb.c;
import Qb.d;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.media.PlaybackParams;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.VideoView;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.previewable.Previewable;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerFactory;
import com.samsung.android.ocr.MOCRLang;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreviewHdrVideo implements Previewable {
    private final String mDataPath;
    private String mFilerPath;
    private int mFilterLevel;
    private final View.OnLayoutChangeListener mLayoutChanged = new g(4, this);
    private final Previewable.PreviewListener mListener;
    protected boolean mLooping;
    private final FileItemInterface mMediaItem;
    private final AtomicBoolean mPlayStop = new AtomicBoolean(false);
    private int mPlaybackIndex = 0;
    protected VideoViewPlayer mPlayer;
    protected MediaHelper.VideoInfo mVideoInfo;
    protected VideoView mVideoView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class VideoViewPlayer {
        private int mFilterLevel;
        private String mFilterPath;
        MediaPlayerCompat mMediaPlayer;
        private final String mPath;
        private final Previewable.PreviewListener mPreviewInterface;
        PreviewHdrVideo mVideoView;

        public VideoViewPlayer(PreviewHdrVideo previewHdrVideo, String str, Previewable.PreviewListener previewListener) {
            this.mVideoView = previewHdrVideo;
            this.mPath = str;
            this.mPreviewInterface = previewListener;
        }

        private MediaPlayerCompat createMediaPlayer() {
            return MediaPlayerFactory.createMediaPlayer(this.mPreviewInterface.useSecMediaPlayer());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$applyFilter$4(MediaPlayerCompat mediaPlayerCompat, String str, int i2) {
            try {
                mediaPlayerCompat.setVideoFilter(str, i2);
            } catch (Exception e) {
                a.s(e, new StringBuilder("fail applyFilter : "), "PreviewHdrVideo");
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$muteAudio$5(MediaPlayerCompat mediaPlayerCompat, boolean z) {
            if (mediaPlayerCompat != null) {
                try {
                    mediaPlayerCompat.setAudioMute(z);
                } catch (Exception e) {
                    a.s(e, new StringBuilder("muteAudio failed e="), "PreviewHdrVideo");
                }
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$pause$1(MediaPlayerCompat mediaPlayerCompat) {
            try {
                mediaPlayerCompat.pause();
            } catch (Exception e) {
                a.s(e, new StringBuilder("fail seekTo : "), "PreviewHdrVideo");
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$resume$0(MediaPlayerCompat mediaPlayerCompat) {
            try {
                mediaPlayerCompat.start();
            } catch (Exception e) {
                a.s(e, new StringBuilder("fail seekTo : "), "PreviewHdrVideo");
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$seekTo$3(MediaPlayerCompat mediaPlayerCompat, int i2) {
            try {
                mediaPlayerCompat.seekToAdaptively(i2);
            } catch (Exception e) {
                a.s(e, new StringBuilder("fail seekTo : "), "PreviewHdrVideo");
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$setLooping$6(MediaPlayerCompat mediaPlayerCompat, boolean z) {
            if (mediaPlayerCompat != null && !isDestroyed()) {
                try {
                    mediaPlayerCompat.setLooping(z);
                } catch (Exception e) {
                    a.s(e, new StringBuilder("setLooping failed e="), "PreviewHdrVideo");
                }
            }
        }

        public void applyFilter(String str, int i2) {
            this.mFilterPath = str;
            this.mFilterLevel = i2;
            MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
            if (mediaPlayerCompat != null) {
                ThreadUtil.postOnMediaPlayThread(new i(mediaPlayerCompat, str, i2, 0));
            }
        }

        public boolean isDestroyed() {
            if (this.mVideoView == null) {
                return true;
            }
            return false;
        }

        public void muteAudio(boolean z) {
            ThreadUtil.postOnMediaPlayThread(new h(this.mMediaPlayer, z, 0));
        }

        public void pause() {
            MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
            if (mediaPlayerCompat != null) {
                ThreadUtil.postOnMediaPlayThread(new g(mediaPlayerCompat, 0));
            }
        }

        public void prepareVideo() {
            Log.d("PreviewHdrVideo", "prepareVideo");
            lambda$stopPlayback$2(this.mMediaPlayer);
            if (!isDestroyed()) {
                long currentTimeMillis = System.currentTimeMillis();
                PreviewHdrVideo previewHdrVideo = this.mVideoView;
                MediaPlayerCompat createMediaPlayer = createMediaPlayer();
                try {
                    createMediaPlayer.setDataSource(this.mPath);
                    createMediaPlayer.setAudioMute(true);
                    createMediaPlayer.setWfdTcpDisable();
                    Objects.requireNonNull(previewHdrVideo);
                    createMediaPlayer.setOnInfoListener(new d(previewHdrVideo));
                    createMediaPlayer.setOnPreparedListener(new d(previewHdrVideo));
                    createMediaPlayer.setOnErrorListener(new d(previewHdrVideo));
                    createMediaPlayer.setOnCompletionListener(new d(previewHdrVideo));
                    if (this.mPreviewInterface.enableTimeTick()) {
                        createMediaPlayer.setTimeTickEnabled(true);
                    }
                    if (!TextUtils.isEmpty(this.mFilterPath)) {
                        createMediaPlayer.setVideoFilter(this.mFilterPath, this.mFilterLevel);
                    }
                    createMediaPlayer.prepareAsync();
                    if (!isDestroyed()) {
                        this.mMediaPlayer = createMediaPlayer;
                    } else {
                        lambda$stopPlayback$2(createMediaPlayer);
                    }
                    Log.d("PreviewHdrVideo", "prepareVideo +" + (System.currentTimeMillis() - currentTimeMillis));
                } catch (Exception e) {
                    Log.e((CharSequence) "PreviewHdrVideo", "prepareVideo failed", (Throwable) e);
                    lambda$stopPlayback$2(createMediaPlayer);
                }
            }
        }

        /* renamed from: releaseVideo */
        public void lambda$stopPlayback$2(MediaPlayerCompat mediaPlayerCompat) {
            if (mediaPlayerCompat != null) {
                try {
                    Log.v("PreviewHdrVideo", "releaseVideo");
                    mediaPlayerCompat.reset();
                    mediaPlayerCompat.release();
                } catch (Exception e) {
                    Log.e((CharSequence) "PreviewHdrVideo", "releaseVideo failed", (Throwable) e);
                }
            }
        }

        public void resume() {
            MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
            if (mediaPlayerCompat != null) {
                ThreadUtil.postOnMediaPlayThread(new g(mediaPlayerCompat, 1));
            } else if (!isDestroyed()) {
                ThreadUtil.postOnMediaPlayThread(new d(0, this));
            }
        }

        public void seekTo(int i2) {
            MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
            if (mediaPlayerCompat != null) {
                ThreadUtil.postOnMediaPlayThread(new j(mediaPlayerCompat, i2, 0));
            }
        }

        public void setLooping(boolean z) {
            ThreadUtil.postOnMediaPlayThread(new f(this, this.mMediaPlayer, z, 0));
        }

        public void stopPlayback() {
            MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
            this.mVideoView = null;
            this.mMediaPlayer = null;
            if (mediaPlayerCompat != null) {
                Log.d("PreviewHdrVideo", "stopPlayback");
                ThreadUtil.postOnMediaPlayThread(new e(0, this, mediaPlayerCompat));
            }
        }
    }

    public PreviewHdrVideo(FileItemInterface fileItemInterface, Previewable.PreviewListener previewListener) {
        this.mMediaItem = fileItemInterface;
        this.mDataPath = fileItemInterface.getPath();
        this.mListener = previewListener;
    }

    private int getVideoHeight() {
        MediaHelper.VideoInfo videoInfo = this.mVideoInfo;
        if (videoInfo != null) {
            return videoInfo.getSize().getHeight();
        }
        int orientation = this.mMediaItem.getOrientation() % MOCRLang.KHMER;
        FileItemInterface fileItemInterface = this.mMediaItem;
        if (orientation == 0) {
            return fileItemInterface.getHeight();
        }
        return fileItemInterface.getWidth();
    }

    private int getVideoWidth() {
        MediaHelper.VideoInfo videoInfo = this.mVideoInfo;
        if (videoInfo != null) {
            return videoInfo.getSize().getWidth();
        }
        int orientation = this.mMediaItem.getOrientation() % MOCRLang.KHMER;
        FileItemInterface fileItemInterface = this.mMediaItem;
        if (orientation == 0) {
            return fileItemInterface.getWidth();
        }
        return fileItemInterface.getHeight();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadVideoInfo$1(MediaHelper.VideoInfo videoInfo) {
        this.mVideoInfo = videoInfo;
        setPreviewViewScale();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadVideoInfo$2(MediaHelper.VideoInfo videoInfo) {
        ThreadUtil.postOnUiThread(new Ob.a(8, this, videoInfo));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadVideoInfo$3(FileItemInterface fileItemInterface) {
        MetadataManager.getInstance().load(fileItemInterface, new VideoReqInfo.Builder().build(), new c(0, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (i2 != i11 || i8 != i13 || i7 != i12 || i10 != i14) {
            setPreviewViewScale();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompletion$7() {
        stopPreview();
        this.mListener.onPreviewEnd();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onError$6(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        this.mListener.onPreviewFail(mediaPlayerCompat, i2, i7);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrepared$5() {
        if (!this.mPlayStop.get()) {
            setPreviewViewScale();
            this.mListener.onPreviewReady();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoInfo$4() {
        if (!this.mPlayStop.get()) {
            this.mListener.onPreviewStart();
            this.mVideoView.setAlpha(1.0f);
        }
    }

    private void loadVideoInfo(FileItemInterface fileItemInterface) {
        MediaHelper.VideoInfo videoInfo = MetadataManager.getInstance().get(fileItemInterface);
        this.mVideoInfo = videoInfo;
        if (videoInfo == null) {
            ThreadUtil.postOnMediaPlayThread(new Ob.a(7, this, fileItemInterface));
        }
    }

    public void applyFilter(String str, int i2) {
        this.mFilerPath = str;
        this.mFilterLevel = i2;
        VideoViewPlayer videoViewPlayer = this.mPlayer;
        if (videoViewPlayer != null) {
            videoViewPlayer.applyFilter(str, i2);
        }
    }

    public View createPreviewView(Context context, int i2) {
        this.mVideoView = new VideoView(context, (AttributeSet) null, 0, i2);
        VideoViewPlayer videoViewPlayer = new VideoViewPlayer(this, this.mDataPath, this.mListener);
        this.mPlayer = videoViewPlayer;
        videoViewPlayer.applyFilter(this.mFilerPath, this.mFilterLevel);
        return this.mVideoView;
    }

    public void muteAudio(boolean z) {
        VideoViewPlayer videoViewPlayer = this.mPlayer;
        if (videoViewPlayer != null) {
            videoViewPlayer.muteAudio(z);
        }
    }

    public void onCompletion(MediaPlayerCompat mediaPlayerCompat) {
        if (this.mPlayStop.get() || !preparePlayback(mediaPlayerCompat)) {
            ThreadUtil.runOnUiThread(new b(this, 1));
        } else {
            mediaPlayerCompat.start();
        }
    }

    public boolean onError(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        ThreadUtil.postOnUiThread(new M((Object) this, (Object) mediaPlayerCompat, i2, i7, 5));
        return true;
    }

    public void onPrepared(MediaPlayerCompat mediaPlayerCompat) {
        if (!this.mPlayStop.get()) {
            Log.d("PreviewHdrVideo", "onPrepared");
            try {
                mediaPlayerCompat.setDisplay(this.mVideoView.getHolder());
                mediaPlayerCompat.setLooping(this.mLooping);
                if (this.mListener.getVolume() > 0.0f) {
                    mediaPlayerCompat.setAudioMute(this.mListener.isMuteAudio());
                    mediaPlayerCompat.setVolume(this.mListener.getVolume(), this.mListener.getVolume());
                }
                preparePlayback(mediaPlayerCompat);
                mediaPlayerCompat.start();
            } catch (Exception e) {
                a.s(e, new StringBuilder("start fail : "), "PreviewHdrVideo");
            }
            ThreadUtil.postOnUiThread(new b(this, 2));
        }
    }

    public boolean onVideoInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        boolean z;
        if (i2 == 3) {
            Log.d("PreviewHdrVideo", "rendering started");
            ThreadUtil.runOnUiThread(new b(this, 0));
            z = true;
        } else {
            z = false;
        }
        if (this.mListener.onVideoInfo(i2, i7) || z) {
            return true;
        }
        return false;
    }

    public void pausePlayer() {
        if (!this.mPlayStop.get()) {
            this.mPlayer.pause();
        }
    }

    public boolean preparePlayback(MediaPlayerCompat mediaPlayerCompat) {
        if (!this.mListener.isPlaybackPreview()) {
            return false;
        }
        Previewable.PreviewListener previewListener = this.mListener;
        int i2 = this.mPlaybackIndex + 1;
        this.mPlaybackIndex = i2;
        PlaybackOption nextPlaybackOption = previewListener.getNextPlaybackOption(i2);
        if (nextPlaybackOption != null) {
            try {
                mediaPlayerCompat.seekTo(nextPlaybackOption.mStartMs);
                mediaPlayerCompat.setPlaybackRange(nextPlaybackOption.mStartMs, nextPlaybackOption.mEndMs);
                if (Float.compare(nextPlaybackOption.mSpeed, 1.0f) != 0) {
                    PlaybackParams playbackParam = mediaPlayerCompat.getPlaybackParam();
                    playbackParam.setSpeed(nextPlaybackOption.mSpeed);
                    mediaPlayerCompat.setPlaybackParam(playbackParam);
                }
                return true;
            } catch (Exception e) {
                a.s(e, new StringBuilder("resume failed e="), "PreviewHdrVideo");
            }
        }
        return false;
    }

    public void resumePlayer() {
        if (!this.mPlayStop.get()) {
            this.mPlayer.resume();
        }
    }

    public void seekTo(int i2) {
        this.mPlayer.seekTo(i2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Paint paint = new Paint();
        paint.setColorFilter(colorFilter);
        this.mVideoView.setLayerPaint(paint);
    }

    public void setLooping(boolean z) {
        this.mLooping = z;
        this.mPlayer.setLooping(z);
    }

    public void setPreviewViewScale() {
        if (this.mVideoView.getWidth() != 0) {
            int videoWidth = getVideoWidth();
            int videoHeight = getVideoHeight();
            float width = (((float) videoWidth) / ((float) videoHeight)) / (((float) this.mVideoView.getWidth()) / ((float) this.mVideoView.getHeight()));
            if (Float.isNaN(width)) {
                Log.w((CharSequence) "PreviewHdrVideo", "wrong video size", Float.valueOf(width), Integer.valueOf(videoWidth), Integer.valueOf(videoHeight), Long.valueOf(this.mMediaItem.getFileId()));
            } else if (width >= 1.0f) {
                this.mVideoView.setScaleX(width);
                this.mVideoView.setScaleY(1.0f);
            } else {
                this.mVideoView.setScaleX(1.0f);
                this.mVideoView.setScaleY(1.0f / width);
            }
        }
    }

    public void startPreview() {
        this.mPlayStop.set(false);
        this.mVideoView.setAlpha(0.0f);
        loadVideoInfo(this.mMediaItem);
        VideoViewPlayer videoViewPlayer = this.mPlayer;
        Objects.requireNonNull(videoViewPlayer);
        ThreadUtil.postOnMediaPlayThread(new d(0, videoViewPlayer));
        this.mVideoView.addOnLayoutChangeListener(this.mLayoutChanged);
    }

    public void stopPreview() {
        this.mPlayStop.set(true);
        this.mVideoView.removeOnLayoutChangeListener(this.mLayoutChanged);
        this.mPlayer.stopPlayback();
    }
}
