package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewParent;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.photoview.OnFlingListener;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.DoubleTapSeekListener;
import com.samsung.android.gallery.widget.videoview.ScaleEndListener;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaPlayerViewCompat extends SurfaceView implements IMediaPlayerView, IMediaPlayerInnerView, SurfaceHolder.Callback {
    private final StringCompat TAG;
    private final MediaPlayerViewImp mMediaPlayerViewImp;
    private ViewParent mTouchInteractionViewParent;

    public MediaPlayerViewCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
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
        if (isVisible()) {
            return ViewUtils.getBitmapFromSurfaceView(this);
        }
        return null;
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

    public void onAttachedToWindow() {
        this.mMediaPlayerViewImp.onAttachedToWindow();
        super.onAttachedToWindow();
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mMediaPlayerViewImp.onConfigurationChanged(configuration);
    }

    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.mMediaPlayerViewImp.onDrawForeground(canvas);
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        this.mMediaPlayerViewImp.onLayout(getParentView(), z, i2, i7, i8, i10);
    }

    public void onMeasure(int i2, int i7) {
        if (this.mMediaPlayerViewImp.onMeasure(i2, i7)) {
            super.setMeasuredDimension(this.mMediaPlayerViewImp.getSurfaceWidth(), this.mMediaPlayerViewImp.getSurfaceHeight());
        } else {
            super.onMeasure(i2, i7);
        }
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

    public void setAccessibility() {
        this.mMediaPlayerViewImp.setAccessibility(this);
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

    public void setCenterCrop(boolean z) {
        this.mMediaPlayerViewImp.setCenterCrop(z);
        this.mMediaPlayerViewImp.resetScale();
    }

    public void setColorCorrect() {
        this.mMediaPlayerViewImp.setColorCorrect();
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
        this.mMediaPlayerViewImp.setPadding(i2, i7, i8, i10);
        super.setPadding(i2, i7, i8, i10);
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
        if (!Float.isNaN(f)) {
            super.setScaleX(f);
        }
    }

    public void setScaleY(float f) {
        this.mMediaPlayerViewImp.setScaleY(f);
    }

    public void setScaleYInner(float f) {
        if (!Float.isNaN(f)) {
            super.setScaleY(f);
        }
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

    public void setTransform(Matrix matrix) {
        if (matrix instanceof IMediaPlayerView.MediaViewMatrix) {
            float[] scale = ((IMediaPlayerView.MediaViewMatrix) matrix).getScale();
            setScaleX(scale[0]);
            setScaleY(scale[1]);
            setPivotX(scale[2]);
            setPivotY(scale[3]);
        }
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
        super.setVisibility(i2);
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

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mMediaPlayerViewImp.surfaceCreated(surfaceHolder.getSurface());
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mMediaPlayerViewImp.surfaceDestroyed();
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

    public MediaPlayerViewCompat(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public void close(boolean z) {
        this.mMediaPlayerViewImp.close(z);
    }

    public void setAudioMute(boolean z, boolean z3) {
        this.mMediaPlayerViewImp.setAudioMute(z, z3);
    }

    public MediaPlayerViewCompat(Context context, AttributeSet attributeSet, int i2, int i7) {
        this(context, attributeSet, i2, i7, 0);
    }

    public MediaPlayerViewCompat(Context context, AttributeSet attributeSet, int i2, int i7, int i8) {
        super(context, attributeSet, i2, i7);
        this.TAG = new StringCompat(getClass().getSimpleName());
        MediaPlayerViewImp mediaPlayerViewImp = new MediaPlayerViewImp(this);
        this.mMediaPlayerViewImp = mediaPlayerViewImp;
        getHolder().addCallback(this);
        mediaPlayerViewImp.setAttribute(context, attributeSet, i8);
        setFocusable(true);
        setAccessibility();
    }

    public View getView() {
        return this;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i7, int i8) {
    }
}
