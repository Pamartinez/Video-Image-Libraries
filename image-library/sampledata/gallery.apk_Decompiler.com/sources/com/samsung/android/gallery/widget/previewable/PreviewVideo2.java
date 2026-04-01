package com.samsung.android.gallery.widget.previewable;

import Qb.a;
import Qb.e;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.previewable.Previewable;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerListener;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewCompat;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaTextureViewCompat;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreviewVideo2 implements Previewable, MediaPlayerListener {
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private final MediaItem mData;
    private int mFilterLevel;
    private String mFilterPath;
    protected final Previewable.PreviewListener mListener;
    private IMediaPlayerView mMediaView;
    private int mPlaybackIndex = 0;
    private int mPreviewH;
    private int mPreviewW;

    public PreviewVideo2(MediaItem mediaItem, Previewable.PreviewListener previewListener) {
        this.mData = mediaItem;
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

    private void preparePlayback() {
        if (this.mListener.isPlaybackPreview() && this.mListener.getNextPlaybackOption(0) != null) {
            this.mPlaybackIndex = 0;
            preparePlayback(this.mListener);
        }
    }

    private void setPlayback(int i2, int i7, float f) {
        int max = Math.max(i2, this.mMediaView.getRenderingPosition());
        this.mMediaView.seekTo(max);
        this.mMediaView.setPlaybackRange(max, i7);
        this.mMediaView.setSpeed(f);
    }

    /* access modifiers changed from: private */
    public void updateTransformMatrix() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.setTransform(getPreviewViewScaleMatrix(iMediaPlayerView.getWidth(), this.mMediaView.getHeight()));
        }
    }

    public void applyFilter(String str, int i2) {
        this.mFilterPath = str;
        this.mFilterLevel = i2;
        this.mMediaView.setVideoFilter(str, i2);
    }

    public View createPreviewView(Context context, int i2) {
        int i7;
        if (this.mMediaView == null) {
            if (!this.mListener.useSecMediaPlayer()) {
                i7 = 8;
            } else {
                i7 = 0;
            }
            int i8 = i7;
            if (PocFeatures.THUMBNAIL_PREVIEW_HDR) {
                this.mMediaView = new MediaPlayerViewCompat(context, (AttributeSet) null, 0, i2, i8);
            } else {
                this.mMediaView = new MediaTextureViewCompat(context, (AttributeSet) null, 0, i2, i8);
            }
            this.mMediaView.addMediaPlayerListener(this);
        }
        return (View) this.mMediaView;
    }

    public Bitmap getPreviewBitmap() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            return iMediaPlayerView.getViewBitmap();
        }
        return null;
    }

    public Matrix getPreviewMatrix() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            return getPreviewViewScaleMatrix(iMediaPlayerView.getWidth(), this.mMediaView.getHeight());
        }
        return null;
    }

    public Matrix getScalePivotMatrix(float f, float f5, float f8, float f10) {
        IMediaPlayerView.MediaViewMatrix mediaViewMatrix = new IMediaPlayerView.MediaViewMatrix();
        mediaViewMatrix.setScale(f, f5, f8, f10);
        return mediaViewMatrix;
    }

    public void muteAudio(boolean z) {
        this.mMediaView.setAudioMute(z);
    }

    public void onSurfaceDestroyed() {
        stopPreview();
        this.mListener.onPreviewEnd();
    }

    public void onTimeTickUpdate(int i2, int i7) {
        this.mListener.onVideoInfo(9000001, i7);
    }

    public void onVideoCompleted() {
        if (preparePlayback(this.mListener)) {
            this.mMediaView.play();
        }
    }

    public void onVideoError(int i2, int i7) {
        try {
            this.mListener.onPreviewFail((Object) this.mMediaView.getMediaItem().getPath(), i2, i7);
        } catch (NullPointerException e) {
            Log.d(this.TAG, e.getMessage());
        }
    }

    public void onVideoPlay(int i2) {
        Previewable.PreviewListener previewListener = this.mListener;
        Objects.requireNonNull(previewListener);
        ThreadUtil.postOnUiThreadDelayed(new a(previewListener, 1), 52);
    }

    public void onVideoPrepared(int i2, int i7) {
        this.mPreviewW = i2;
        this.mPreviewH = i7;
        this.mListener.onPreviewReady();
        if (this.mListener.getVolume() > 0.0f) {
            this.mMediaView.setAudioMute(this.mListener.isMuteAudio());
            this.mMediaView.setVolume(this.mListener.getVolume(), this.mListener.getVolume());
        }
        preparePlayback();
        this.mMediaView.setVideoFilter(this.mFilterPath, this.mFilterLevel);
        ThreadUtil.postOnUiThread(new e(0, this));
    }

    public void pausePlayer() {
        this.mMediaView.pauseVideo();
    }

    public void resumePlayer() {
        this.mMediaView.resumeVideo();
    }

    public void seekTo(int i2) {
        this.mMediaView.seekTo(i2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mMediaView != null) {
            Paint paint = new Paint();
            paint.setColorFilter(colorFilter);
            ((View) this.mMediaView).setLayerPaint(paint);
        }
    }

    public void setLooping(boolean z) {
        this.mMediaView.setLooping(z);
    }

    public void setPlaybackRange(int i2, int i7) {
        this.mMediaView.setPlaybackRange(i2, i7);
    }

    public void setTag(Object obj) {
        this.TAG.setTag(obj);
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.setLogTag(obj);
        }
    }

    public void startPreview() {
        this.mMediaView.setAudioMute(this.mListener.isMuteAudio());
        this.mMediaView.disableWfdTcp();
        this.mMediaView.open(this.mData);
        this.mMediaView.play();
    }

    public void stopPreview() {
        this.mMediaView.close();
    }

    public boolean preparePlayback(Previewable.PreviewListener previewListener) {
        int i2 = this.mPlaybackIndex;
        this.mPlaybackIndex = i2 + 1;
        PlaybackOption nextPlaybackOption = previewListener.getNextPlaybackOption(i2);
        if (nextPlaybackOption != null) {
            setPlayback(nextPlaybackOption.mStartMs, nextPlaybackOption.mEndMs, nextPlaybackOption.mSpeed);
            return true;
        }
        Previewable.PreviewListener previewListener2 = this.mListener;
        Objects.requireNonNull(previewListener2);
        ThreadUtil.postOnUiThread(new a(previewListener2, 0));
        return false;
    }
}
