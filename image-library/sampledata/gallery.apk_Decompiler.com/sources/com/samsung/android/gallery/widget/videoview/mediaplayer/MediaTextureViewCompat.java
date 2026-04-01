package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewParent;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.photoview.OnFlingListener;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.DoubleTapSeekListener;
import com.samsung.android.gallery.widget.videoview.ScaleEndListener;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaTextureViewCompat extends TextureView implements IMediaPlayerView, IMediaPlayerInnerView, TextureView.SurfaceTextureListener {
    private final StringCompat TAG;
    private final MediaPlayerViewImp mMediaPlayerViewImp;
    private ViewParent mTouchInteractionViewParent;

    public MediaTextureViewCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private Matrix getPreviewViewScaleMatrix(int i2, int i7) {
        float f;
        float f5;
        int videoWidth = this.mMediaPlayerViewImp.getVideoWidth();
        int videoHeight = this.mMediaPlayerViewImp.getVideoHeight();
        if (videoWidth == 0 || videoHeight == 0) {
            Log.e((CharSequence) this.TAG, "video size is ", Integer.valueOf(videoWidth), Integer.valueOf(videoWidth));
            return null;
        }
        if (videoWidth > videoHeight) {
            float f8 = (((float) videoWidth) / ((float) videoHeight)) * ((float) i7);
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
            float f12 = (((float) videoHeight) / ((float) videoWidth)) * ((float) i2);
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

    private Matrix getScalePivotMatrix(float f, float f5, float f8, float f10) {
        Matrix matrix = new Matrix();
        matrix.setScale(f, f5, f8, f10);
        return matrix;
    }

    public void addContentDescription(Supplier<String> supplier) {
        this.mMediaPlayerViewImp.addContentDescription(supplier);
    }

    public void addMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        this.mMediaPlayerViewImp.addMediaPlayerListener(mediaPlayerListener);
    }

    public void addUsageHint(Supplier<String> supplier) {
        this.mMediaPlayerViewImp.addUsageHint(supplier);
    }

    public void beginInstantSlowMoPlay() {
        this.mMediaPlayerViewImp.beginInstantSlowMoPlay();
    }

    public void bindCaptureView(View view, boolean z, boolean z3) {
        this.mMediaPlayerViewImp.bindCaptureView(view, z, z3);
    }

    public void captureFrame(Consumer<Bitmap> consumer, int i2, boolean z) {
        this.mMediaPlayerViewImp.captureFrame(consumer, i2, z);
    }

    public Bitmap captureFrameInBackground(int i2, boolean z) {
        return this.mMediaPlayerViewImp.captureFrameInBackground(i2, z);
    }

    public void close() {
        this.mMediaPlayerViewImp.close();
    }

    public void disableWfdTcp() {
        this.mMediaPlayerViewImp.disableWfdTcp();
    }

    public void endInstantSlowMoPlay() {
        this.mMediaPlayerViewImp.endInstantSlowMoPlay();
    }

    public int getBottomMarginFromSupplier() {
        return this.mMediaPlayerViewImp.getBottomMarginFromSupplier();
    }

    public int getCurrentPosition() {
        return this.mMediaPlayerViewImp.getCurrentPosition();
    }

    public RectF getDisplayMinRect() {
        return this.mMediaPlayerViewImp.getDisplayMinRect();
    }

    public RectF getDisplayRect() {
        return this.mMediaPlayerViewImp.getDisplayRect();
    }

    public int getDuration() {
        return this.mMediaPlayerViewImp.getDuration();
    }

    public int getFrameRate() {
        return this.mMediaPlayerViewImp.getFrameRate();
    }

    public void getLocation(int[] iArr) {
        this.mMediaPlayerViewImp.getLocation(iArr);
    }

    public MediaItem getMediaItem() {
        return this.mMediaPlayerViewImp.getMediaItem();
    }

    public ViewParent getParentView() {
        return this.mMediaPlayerViewImp.getParentView();
    }

    public PlayState getPlayState() {
        return this.mMediaPlayerViewImp.getPlayState();
    }

    public int getRenderingPosition() {
        return this.mMediaPlayerViewImp.getRenderingPosition();
    }

    public float getScaleX() {
        return this.mMediaPlayerViewImp.getScaleX();
    }

    public float getScaleXInner() {
        return super.getScaleX();
    }

    public float getScaleY() {
        return this.mMediaPlayerViewImp.getScaleY();
    }

    public float getScaleYInner() {
        return super.getScaleY();
    }

    public float getSpeed() {
        return this.mMediaPlayerViewImp.getSpeed();
    }

    public int getTopMarginFromSupplier() {
        return this.mMediaPlayerViewImp.getTopMarginFromSupplier();
    }

    public ViewParent getTouchInteractionViewParent() {
        return this.mTouchInteractionViewParent;
    }

    public Bitmap getViewBitmap() {
        return getBitmap(getWidth(), getHeight());
    }

    public float getX() {
        return this.mMediaPlayerViewImp.getX();
    }

    public float getXInner() {
        return super.getX();
    }

    public float getY() {
        return this.mMediaPlayerViewImp.getY();
    }

    public float getYInner() {
        return super.getY();
    }

    public boolean handleTouchEvent(MotionEvent motionEvent) {
        return this.mMediaPlayerViewImp.handleTouchEvent(motionEvent);
    }

    public boolean hasAudioTrack() {
        return this.mMediaPlayerViewImp.hasAudioTrack();
    }

    public boolean isAlreadyUp() {
        return this.mMediaPlayerViewImp.isAlreadyUp();
    }

    public boolean isAudioMute() {
        return this.mMediaPlayerViewImp.isAudioMute();
    }

    public boolean isInPlayState() {
        return this.mMediaPlayerViewImp.isInPlayState();
    }

    public boolean isIncomingCall() {
        return this.mMediaPlayerViewImp.isIncomingCall();
    }

    public boolean isMaxScale() {
        return this.mMediaPlayerViewImp.isMaxScale();
    }

    public boolean isMinScale() {
        return this.mMediaPlayerViewImp.isMinScale();
    }

    public boolean isMovable() {
        return this.mMediaPlayerViewImp.isMovable();
    }

    public boolean isOpened() {
        return this.mMediaPlayerViewImp.isOpened();
    }

    public boolean isPaused() {
        return this.mMediaPlayerViewImp.isPaused();
    }

    public boolean isPlaying() {
        return this.mMediaPlayerViewImp.isPlaying();
    }

    public boolean isToggleConsumable(float f, float f5) {
        return this.mMediaPlayerViewImp.isToggleConsumable(f, f5);
    }

    public boolean isVisible() {
        return ViewUtils.isVisible(this);
    }

    public boolean isVisualSeeking() {
        return this.mMediaPlayerViewImp.isVisualSeeking();
    }

    public boolean isZoomed() {
        return this.mMediaPlayerViewImp.isZoomed();
    }

    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.mMediaPlayerViewImp.onDrawForeground(canvas);
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        this.mMediaPlayerViewImp.onLayout(getParent(), z, i2, i7, i8, i10);
    }

    public void onMeasure(int i2, int i7) {
        if (this.mMediaPlayerViewImp.onMeasure(i2, i7)) {
            super.setMeasuredDimension(this.mMediaPlayerViewImp.getSurfaceWidth(), this.mMediaPlayerViewImp.getSurfaceHeight());
        } else {
            super.onMeasure(i2, i7);
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i7) {
        this.mMediaPlayerViewImp.surfaceCreated(new Surface(surfaceTexture));
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mMediaPlayerViewImp.surfaceDestroyed();
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i7) {
        setTransform(getPreviewViewScaleMatrix(i2, i7));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mMediaPlayerViewImp.onTouchEvent(motionEvent);
    }

    public boolean open(MediaItem mediaItem, VideoReqInfo videoReqInfo) {
        return this.mMediaPlayerViewImp.open(mediaItem, videoReqInfo);
    }

    public Bitmap pauseAndCapture() {
        return this.mMediaPlayerViewImp.pauseAndCapture();
    }

    public void pauseOnReady(int i2) {
        this.mMediaPlayerViewImp.pauseOnReady(i2);
    }

    public void pauseVideo() {
        this.mMediaPlayerViewImp.pause();
    }

    public void play() {
        this.mMediaPlayerViewImp.play();
    }

    public void refreshCaptureView() {
        this.mMediaPlayerViewImp.refreshCaptureView();
    }

    public void release3dEffect() {
        this.mMediaPlayerViewImp.release3dEffect();
    }

    public void removeMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        this.mMediaPlayerViewImp.removeMediaPlayerListener(mediaPlayerListener);
    }

    public void resetAccessibility() {
        this.mMediaPlayerViewImp.resetAccessibility(this);
    }

    public void resetItem() {
        this.mMediaPlayerViewImp.resetItem();
    }

    public void resetScale() {
        this.mMediaPlayerViewImp.resetScale();
    }

    public void resumeVideo() {
        this.mMediaPlayerViewImp.resume();
    }

    public void seekBegin() {
        this.mMediaPlayerViewImp.seekBegin();
    }

    public void seekFinish() {
        this.mMediaPlayerViewImp.seekFinish();
    }

    public void seekTo(int i2) {
        this.mMediaPlayerViewImp.seekTo(i2);
    }

    public void set3dEffectPosition(double d) {
        this.mMediaPlayerViewImp.set3dEffectPosition(d);
    }

    public void setAudioEraserOff(boolean z, String str) {
        this.mMediaPlayerViewImp.setAudioEraserOff(z, str);
    }

    public void setAudioMute(boolean z) {
        this.mMediaPlayerViewImp.setAudioMute(z);
    }

    public void setBgm(BgmOptions bgmOptions) {
        this.mMediaPlayerViewImp.setBgm(bgmOptions);
    }

    public /* bridge */ /* synthetic */ void setCoverView(View view) {
        super.setCoverView(view);
    }

    public void setDefaultPosition(boolean z) {
        this.mMediaPlayerViewImp.setDefaultPosition(z);
    }

    public void setDoubleTapSeekListener(DoubleTapSeekListener doubleTapSeekListener) {
        this.mMediaPlayerViewImp.setDoubleTapSeekListener(doubleTapSeekListener);
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
        this.mMediaPlayerViewImp.setLogTag(obj);
    }

    public void setLooping(boolean z) {
        this.mMediaPlayerViewImp.setLooping(z);
    }

    public void setOnFlingListener(OnFlingListener onFlingListener) {
        this.mMediaPlayerViewImp.setOnFlingListener(onFlingListener);
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mMediaPlayerViewImp.setOnLongClickListener(onLongClickListener);
    }

    public void setPadding(int i2, int i7, int i8, int i10) {
        super.setPadding(i2, i7, i8, i10);
        this.mMediaPlayerViewImp.setPadding(i2, i7, i8, i10);
    }

    public void setPinchShrinkSupport(OnViewerExitGestureListener onViewerExitGestureListener) {
        this.mMediaPlayerViewImp.setPinchShrinkSupport(onViewerExitGestureListener);
    }

    public void setPlaybackLoop(boolean z) {
        this.mMediaPlayerViewImp.setPlaybackLoop(z);
    }

    public void setPlaybackRange(int i2, int i7) {
        this.mMediaPlayerViewImp.setPlaybackRange(i2, i7);
    }

    public void setRenderingPosition(int i2) {
        this.mMediaPlayerViewImp.setRenderingPosition(i2);
    }

    public void setScaleEndListener(ScaleEndListener scaleEndListener) {
        this.mMediaPlayerViewImp.setScaleEndListener(scaleEndListener);
    }

    public void setScaleRelative(float f) {
        this.mMediaPlayerViewImp.setScaleRelative(f);
    }

    public void setScaleX(float f) {
        this.mMediaPlayerViewImp.setScaleX(f);
    }

    public void setScaleXInner(float f) {
        super.setScaleX(f);
    }

    public void setScaleY(float f) {
        this.mMediaPlayerViewImp.setScaleY(f);
    }

    public void setScaleYInner(float f) {
        super.setScaleY(f);
    }

    public void setSlowMo(int i2, int i7, boolean z) {
        this.mMediaPlayerViewImp.setSlowMo(i2, i7, z);
    }

    public void setSpeed(float f) {
        this.mMediaPlayerViewImp.setSpeed(f);
    }

    public void setSupportTimeTick(boolean z) {
        this.mMediaPlayerViewImp.setSupportTimeTick(z);
    }

    public void setTouchInteractionViewParent(ViewParent viewParent) {
        this.mTouchInteractionViewParent = viewParent;
    }

    public void setTranslationListener(OnTranslationListener onTranslationListener) {
        this.mMediaPlayerViewImp.setTranslationListener(onTranslationListener);
    }

    public void setVideoCaptured(int i2, Bitmap bitmap) {
        this.mMediaPlayerViewImp.setVideoCaptured(i2, bitmap);
    }

    public void setVideoFilter(String str, int i2) {
        this.mMediaPlayerViewImp.setVideoFilter(str, i2);
    }

    public void setVideoFrc(int i2) {
        this.mMediaPlayerViewImp.setVideoFrc(i2);
    }

    public void setVisibility(int i2) {
        super.setVisibility(0);
        if (i2 == 0) {
            setAlpha(1.0f);
        } else {
            setAlpha(0.0f);
        }
    }

    public void setVolume(float f, float f5) {
        this.mMediaPlayerViewImp.setVolume(f, f5);
    }

    public void setX(float f) {
        this.mMediaPlayerViewImp.setX(f);
    }

    public void setXInner(float f) {
        super.setX(f);
    }

    public void setY(float f) {
        this.mMediaPlayerViewImp.setY(f);
    }

    public void setYInner(float f) {
        super.setY(f);
    }

    public void updateBackgroundColor(boolean z) {
        this.mMediaPlayerViewImp.updateBackgroundColor(z);
    }

    public void updateItem(MediaItem mediaItem) {
        this.mMediaPlayerViewImp.updateItem(mediaItem);
    }

    public void visualSeekTo(int i2) {
        this.mMediaPlayerViewImp.visualSeekTo(i2);
    }

    public MediaTextureViewCompat(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public void close(boolean z) {
        this.mMediaPlayerViewImp.close(z);
    }

    public void setAudioMute(boolean z, boolean z3) {
        this.mMediaPlayerViewImp.setAudioMute(z, z3);
    }

    public MediaTextureViewCompat(Context context, AttributeSet attributeSet, int i2, int i7) {
        this(context, attributeSet, i2, i7, 0);
    }

    public MediaTextureViewCompat(Context context, AttributeSet attributeSet, int i2, int i7, int i8) {
        super(context, attributeSet, i2, i7);
        this.TAG = new StringCompat(getClass().getSimpleName());
        MediaPlayerViewImp mediaPlayerViewImp = new MediaPlayerViewImp(this, 1);
        this.mMediaPlayerViewImp = mediaPlayerViewImp;
        setSurfaceTextureListener(this);
        mediaPlayerViewImp.setAttribute(context, attributeSet, i8);
    }

    public View getView() {
        return this;
    }

    public void setColorCorrect() {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void setBackgroundColor(int i2) {
    }

    public void setCenterCrop(boolean z) {
    }
}
