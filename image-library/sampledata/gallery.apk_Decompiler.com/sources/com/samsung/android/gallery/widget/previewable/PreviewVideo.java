package com.samsung.android.gallery.widget.previewable;

import A.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.media.PlaybackParams;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.previewable.Previewable;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class PreviewVideo implements Previewable {
    private final Object LOCK = new Object();
    protected final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private final String mDataPath;
    private int mFilterLevel;
    private String mFilterPath;
    protected final Previewable.PreviewListener mListener;
    protected MediaPlayerCompat mMediaPlayer;
    private int mPlaybackIndex = 0;
    private int mPreviewH;
    private final AtomicBoolean mPreviewState = new AtomicBoolean(false);
    private int mPreviewW;
    /* access modifiers changed from: private */
    public int mSurfaceH;
    /* access modifiers changed from: private */
    public int mSurfaceW;
    protected TextureView mTextureView;

    public PreviewVideo(String str, Previewable.PreviewListener previewListener) {
        this.mDataPath = str;
        this.mListener = previewListener;
    }

    private Matrix getPreviewViewScaleMatrix(int i2, int i7) {
        float f;
        float f5;
        int i8 = this.mPreviewW;
        int i10 = this.mPreviewH;
        if (i8 > i10) {
            float f8 = (((float) i8) / ((float) i10)) * ((float) i7);
            float f10 = (float) i2;
            f = f8 / f10;
            if (f8 < f10) {
                float f11 = f10 / f8;
                f *= f11;
                f5 = f11 * 1.0f;
            } else {
                f5 = 1.0f;
            }
        } else {
            float f12 = (((float) i10) / ((float) i8)) * ((float) i2);
            float f13 = (float) i7;
            float f14 = f12 / f13;
            if (f12 < f13) {
                float f15 = f13 / f12;
                float f16 = f15 * 1.0f;
                float f17 = f15 * f14;
                f = f16;
                f5 = f17;
            } else {
                f5 = f14;
                f = 1.0f;
            }
        }
        return getScalePivotMatrix(Math.max(f, 1.0f), Math.max(f5, 1.0f), ((float) i2) / 2.0f, ((float) i7) / 2.0f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyFilter$3(String str, int i2) {
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat != null) {
            try {
                mediaPlayerCompat.setVideoFilter(str, i2);
            } catch (Exception e) {
                a.r(e, new StringBuilder("setVideoFilterName failed e="), this.TAG);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$muteAudio$4(MediaPlayerCompat mediaPlayerCompat, boolean z) {
        if (mediaPlayerCompat != null) {
            try {
                mediaPlayerCompat.setAudioMute(z);
            } catch (Exception e) {
                a.r(e, new StringBuilder("muteAudio failed e="), this.TAG);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$pausePlayer$5(MediaPlayerCompat mediaPlayerCompat) {
        try {
            mediaPlayerCompat.pause();
        } catch (Exception e) {
            a.r(e, new StringBuilder("pause failed e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resumePlayer$6(MediaPlayerCompat mediaPlayerCompat) {
        try {
            mediaPlayerCompat.start();
        } catch (Exception e) {
            a.r(e, new StringBuilder("resume failed e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seekTo$1(int i2) {
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat != null) {
            try {
                mediaPlayerCompat.seekTo(i2);
            } catch (Exception e) {
                a.r(e, new StringBuilder("seekTo failed e="), this.TAG);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setLooping$2(boolean z) {
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat != null) {
            try {
                mediaPlayerCompat.setLooping(z);
            } catch (Exception e) {
                a.r(e, new StringBuilder("setLooping failed e="), this.TAG);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onPrepared(MediaPlayerCompat mediaPlayerCompat) {
        try {
            this.mListener.onPreviewReady();
            this.mPreviewW = mediaPlayerCompat.getVideoWidth();
            this.mPreviewH = mediaPlayerCompat.getVideoHeight();
            if (this.mListener.getVolume() > 0.0f) {
                mediaPlayerCompat.setAudioMute(this.mListener.isMuteAudio());
                mediaPlayerCompat.setVolume(this.mListener.getVolume(), this.mListener.getVolume());
            }
            preparePlayback(mediaPlayerCompat);
            mediaPlayerCompat.start();
            ThreadUtil.postOnUiThread(new d(1, this));
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "start failed e=" + e.getMessage() + " state:" + mediaPlayerCompat.getPlaybackState());
            lambda$stopPreview$0(mediaPlayerCompat);
            this.mListener.onPreviewFail(mediaPlayerCompat, 0, 0);
        }
    }

    /* access modifiers changed from: private */
    public boolean onVideoInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        return this.mListener.onVideoInfo(i2, i7);
    }

    /* access modifiers changed from: private */
    public void openVideoPlayer(SurfaceTexture surfaceTexture) {
        if (this.mPreviewState.get()) {
            MediaPlayerCompat createMediaPlayer = createMediaPlayer();
            try {
                createMediaPlayer.setTag(this.mDataPath);
                createMediaPlayer.setDataSource(this.mDataPath);
                createMediaPlayer.setWfdTcpDisable();
                createMediaPlayer.setAudioMute(this.mListener.isMuteAudio());
                createMediaPlayer.setSurface(new Surface(surfaceTexture));
                createMediaPlayer.setOnErrorListener(new l(this));
                createMediaPlayer.setOnCompletionListener(new l(this));
                createMediaPlayer.setOnPreparedListener(new l(this));
                if (this.mListener.listenVideoInfo()) {
                    createMediaPlayer.setOnInfoListener(new l(this));
                }
                if (this.mListener.enableTimeTick()) {
                    createMediaPlayer.setTimeTickEnabled(true);
                }
                if (!TextUtils.isEmpty(this.mFilterPath)) {
                    createMediaPlayer.setVideoFilter(this.mFilterPath, this.mFilterLevel);
                }
                createMediaPlayer.prepareAsync();
                synchronized (this.LOCK) {
                    if (!this.mPreviewState.get()) {
                        Log.e(this.TAG, "openVideoPlayer skip. already stop");
                        lambda$stopPreview$0(createMediaPlayer);
                        return;
                    }
                    this.mMediaPlayer = createMediaPlayer;
                }
            } catch (Exception e) {
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "openVideoPlayer failed e=" + Logger.getSimpleName((Object) e) + " " + e.getMessage());
                lambda$stopPreview$0(createMediaPlayer);
                this.mListener.onPreviewFail(createMediaPlayer, 0, 0);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private boolean preparePlaybackInternal(MediaPlayerCompat mediaPlayerCompat) {
        if (!ownPlaybackControl() && this.mListener.isPlaybackPreview()) {
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
                    a.r(e, new StringBuilder("resume failed e="), this.TAG);
                }
            }
        }
        return false;
    }

    private String toDebug(Object obj) {
        return "PreviewVideo@" + Integer.toHexString(hashCode()) + "{" + Logger.getSimpleName(obj) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mPreviewState + "}";
    }

    /* access modifiers changed from: private */
    public void updateTransformMatrix() {
        this.mTextureView.setTransform(getPreviewViewScaleMatrix(this.mSurfaceW, this.mSurfaceH));
    }

    public void applyFilter(String str, int i2) {
        this.mFilterPath = str;
        this.mFilterLevel = i2;
        ThreadUtil.postOnMediaPlayThread(new i(this, str, i2, 1));
    }

    /* renamed from: closeVideoPlayer */
    public void lambda$stopPreview$0(MediaPlayerCompat mediaPlayerCompat) {
        if (mediaPlayerCompat != null) {
            try {
                mediaPlayerCompat.reset();
                mediaPlayerCompat.release();
            } catch (Exception e) {
                a.r(e, new StringBuilder("closeVideoPlayer failed e="), this.TAG);
            }
        }
    }

    public abstract MediaPlayerCompat createMediaPlayer();

    public View createPreviewView(Context context, int i2) {
        if (this.mTextureView == null) {
            this.mTextureView = new TextureView(context, (AttributeSet) null, 0, i2);
        }
        return this.mTextureView;
    }

    public Bitmap getPreviewBitmap() {
        TextureView textureView = this.mTextureView;
        if (textureView != null) {
            return textureView.getBitmap();
        }
        return null;
    }

    public Matrix getPreviewMatrix() {
        TextureView textureView = this.mTextureView;
        if (textureView != null) {
            return getPreviewViewScaleMatrix(textureView.getWidth(), this.mTextureView.getHeight());
        }
        return null;
    }

    public Matrix getScalePivotMatrix(float f, float f5, float f8, float f10) {
        Matrix matrix = new Matrix();
        matrix.setScale(f, f5, f8, f10);
        return matrix;
    }

    public void muteAudio(boolean z) {
        ThreadUtil.postOnMediaPlayThread(new f(this, this.mMediaPlayer, z, 1));
    }

    public void onCompletion(MediaPlayerCompat mediaPlayerCompat) {
        if (!this.mPreviewState.get() || !preparePlaybackInternal(mediaPlayerCompat)) {
            stopPreview();
            Previewable.PreviewListener previewListener = this.mListener;
            Objects.requireNonNull(previewListener);
            ThreadUtil.postOnUiThread(new Qb.a(previewListener, 0));
            return;
        }
        mediaPlayerCompat.start();
    }

    public boolean onError(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        if (!(i2 == 1 && (i7 == -19 || i7 == -38))) {
            StringCompat stringCompat = this.TAG;
            StringBuilder h5 = a.h(i2, i7, "onError {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "} ");
            h5.append(toDebug(this.mMediaPlayer));
            Log.e(stringCompat, h5.toString());
        }
        this.mListener.onPreviewFail(mediaPlayerCompat, i2, i7);
        return false;
    }

    public abstract boolean ownPlaybackControl();

    public void pausePlayer() {
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat != null) {
            ThreadUtil.postOnMediaPlayThread(new k(this, mediaPlayerCompat, 2));
        }
    }

    public abstract boolean preparePlayback(MediaPlayerCompat mediaPlayerCompat);

    public void resumePlayer() {
        MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
        if (mediaPlayerCompat != null) {
            ThreadUtil.postOnMediaPlayThread(new k(this, mediaPlayerCompat, 1));
        }
    }

    public void seekTo(int i2) {
        ThreadUtil.postOnMediaPlayThread(new j(this, i2, 1));
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mTextureView != null) {
            Paint paint = new Paint();
            paint.setColorFilter(colorFilter);
            this.mTextureView.setLayerPaint(paint);
        }
    }

    public void setLooping(boolean z) {
        ThreadUtil.postOnMediaPlayThread(new h(this, z, 1));
    }

    public void setTag(Object obj) {
        this.TAG.setTag(obj);
    }

    public void startPreview() {
        this.mPreviewState.set(true);
        this.mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            boolean mPreviewStarted;

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onSurfaceTextureAvailable$0(SurfaceTexture surfaceTexture) {
                PreviewVideo.this.openVideoPlayer(surfaceTexture);
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i7) {
                PreviewVideo.this.mSurfaceW = i2;
                PreviewVideo.this.mSurfaceH = i7;
                ThreadUtil.postOnMediaPlayThread(new e(1, this, surfaceTexture));
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return true;
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i7) {
                PreviewVideo.this.mSurfaceW = i2;
                PreviewVideo.this.mSurfaceH = i7;
                PreviewVideo.this.updateTransformMatrix();
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                if (!this.mPreviewStarted) {
                    this.mPreviewStarted = true;
                    Previewable.PreviewListener previewListener = PreviewVideo.this.mListener;
                    Objects.requireNonNull(previewListener);
                    ThreadUtil.postOnUiThread(new Qb.a(previewListener, 1));
                }
            }
        });
    }

    public void stopPreview() {
        this.mPreviewState.set(false);
        synchronized (this.LOCK) {
            try {
                MediaPlayerCompat mediaPlayerCompat = this.mMediaPlayer;
                this.mMediaPlayer = null;
                if (mediaPlayerCompat != null) {
                    ThreadUtil.postOnMediaPlayThread(new k(this, mediaPlayerCompat, 0));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String toString() {
        return toDebug(this.mMediaPlayer);
    }
}
