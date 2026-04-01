package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.capture.IClipRootView;
import com.samsung.android.gallery.widget.photoview.OnFlingListener;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.DoubleTapSeekListener;
import com.samsung.android.gallery.widget.videoview.ScaleEndListener;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMediaPlayerView extends IClipRootView {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaViewMatrix extends Matrix {
        float[] mScale = new float[4];

        public float[] getScale() {
            return this.mScale;
        }

        public void setScale(float f, float f5, float f8, float f10) {
            super.setScale(f, f5, f8, f10);
            float[] fArr = this.mScale;
            fArr[0] = f;
            fArr[1] = f5;
            fArr[2] = f8;
            fArr[3] = f10;
        }
    }

    void addContentDescription(Supplier<String> supplier);

    void addMediaPlayerListener(MediaPlayerListener mediaPlayerListener);

    void addUsageHint(Supplier<String> supplier);

    void beginInstantSlowMoPlay();

    void bindCaptureView(View view, boolean z, boolean z3);

    void captureFrame(Consumer<Bitmap> consumer, int i2) {
        captureFrame(consumer, i2, true);
    }

    void captureFrame(Consumer<Bitmap> consumer, int i2, boolean z);

    Bitmap captureFrameInBackground(int i2, boolean z);

    void close();

    void close(boolean z);

    void disableWfdTcp();

    void endInstantSlowMoPlay();

    float getAlpha();

    Context getContext();

    int getCurrentPosition();

    int getDuration();

    int getFrameRate();

    int getHeight();

    ViewGroup.LayoutParams getLayoutParams();

    MediaItem getMediaItem();

    PlayState getPlayState();

    int getRenderingPosition();

    float getSpeed();

    View getView();

    Bitmap getViewBitmap();

    int getVisibility();

    int getWidth();

    boolean handleTouchEvent(MotionEvent motionEvent);

    boolean hasAudioTrack();

    boolean isAudioMute();

    boolean isInPlayState();

    boolean isIncomingCall();

    boolean isMaxScale();

    boolean isMinScale();

    boolean isMovable();

    boolean isOpened();

    boolean isPaused();

    boolean isPlaying();

    boolean isToggleConsumable(float f, float f5);

    boolean isVisible();

    boolean isVisualSeeking();

    boolean isZoomed();

    boolean onTouchEvent(MotionEvent motionEvent);

    boolean open(MediaItem mediaItem) {
        return open(mediaItem, new VideoReqInfo.Builder().build());
    }

    boolean open(MediaItem mediaItem, VideoReqInfo videoReqInfo);

    Bitmap pauseAndCapture();

    void pauseOnReady(int i2);

    void pauseVideo();

    void play();

    void recycle() {
        close(true);
        resetItem();
        setDefaultPosition(false);
        ViewUtils.setViewSize((View) getView().getParent(), -2, -2);
    }

    void refreshCaptureView();

    void release3dEffect();

    void removeMediaPlayerListener(MediaPlayerListener mediaPlayerListener);

    void resetAccessibility();

    void resetItem();

    void resetScale();

    void resumeVideo();

    void seekBegin();

    void seekFinish();

    void seekTo(int i2);

    void set3dEffectPosition(double d);

    void setAccessibilityDelegate(View.AccessibilityDelegate accessibilityDelegate);

    void setAlpha(float f);

    void setAudioEraserOff(boolean z, String str);

    void setAudioMute(boolean z);

    void setAudioMute(boolean z, boolean z3);

    void setBackgroundColor(int i2);

    void setBgm(BgmOptions bgmOptions);

    void setColorCorrect();

    void setContentDescription(CharSequence charSequence);

    void setDefaultPosition(boolean z);

    void setDoubleTapSeekListener(DoubleTapSeekListener doubleTapSeekListener);

    void setLogTag(Object obj);

    void setLooping(boolean z);

    void setOnFlingListener(OnFlingListener onFlingListener);

    void setPinchShrinkSupport(OnViewerExitGestureListener onViewerExitGestureListener);

    void setPlaybackLoop(boolean z);

    void setPlaybackRange(int i2, int i7);

    void setRenderingPosition(int i2);

    void setScaleEndListener(ScaleEndListener scaleEndListener);

    void setScaleRelative(float f);

    void setSlowMo(int i2, int i7, boolean z);

    void setSpeed(float f);

    void setSupportTimeTick(boolean z);

    void setTouchInteractionViewParent(ViewParent viewParent);

    void setTransform(Matrix matrix);

    void setTranslationListener(OnTranslationListener onTranslationListener);

    void setVideoCaptured(int i2, Bitmap bitmap);

    void setVideoFilter(String str, int i2);

    void setVideoFrc(int i2);

    void setVisibility(int i2);

    void setVolume(float f, float f5);

    void updateBackgroundColor(boolean z);

    void updateItem(MediaItem mediaItem);

    void visualSeekTo(int i2);
}
