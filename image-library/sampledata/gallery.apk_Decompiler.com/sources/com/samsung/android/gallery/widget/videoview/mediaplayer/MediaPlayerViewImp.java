package com.samsung.android.gallery.widget.videoview.mediaplayer;

import A.a;
import A4.J;
import A4.L;
import H7.x;
import K7.c;
import a6.C0418a;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import bc.d;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.gallery.widget.capture.ClipViewDelegate;
import com.samsung.android.gallery.widget.capture.IClipRootView;
import com.samsung.android.gallery.widget.photoview.OnFlingListener;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.videoview.DoubleTapSeekListener;
import com.samsung.android.gallery.widget.videoview.ScaleEndListener;
import com.samsung.android.sum.core.filter.f;
import com.samsung.android.sum.core.functional.g;
import e5.C0451a;
import e6.C0453a;
import i4.C0468a;
import ic.b;
import ic.h;
import ic.i;
import ic.j;
import ic.k;
import ic.l;
import ic.m;
import ic.n;
import ic.o;
import ic.p;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaPlayerViewImp implements MediaPlayerListener, IDelegateListener {
    private static final boolean SUPPORT_DEBUG_VIEW = PocFeatures.isEnabled(PocFeatures.RegionDecodingInfo);
    private final StringCompat TAG;
    /* access modifiers changed from: private */
    public Supplier<String> mAccessibilityUsageHint;
    private AudioManagerDelegate mAudioManagerDelegate;
    private CaptureDelegate mCaptureDelegate;
    private ClipViewDelegate mClipViewDelegate;
    /* access modifiers changed from: private */
    public Supplier<String> mContentDescription;
    private DebugInfoDelegate mDebugInfoDelegate;
    private boolean mFitContent;
    private GestureDelegate mGestureDelegate;
    private InstantSlowMoPlayDelegate mInstantSlowMoPlayDelegate;
    private MediaItem mMediaItem;
    private final MediaPlayerDelegate mMediaPlayerDelegate;
    private MediaSessionDelegate mMediaSessionDelegate;
    private final int mMode;
    private final MpViewThread mMpViewThread;
    private boolean mOpened;
    private RoundedCornerDelegate mRoundedCornerDelegate;
    private ScaleDelegate mScaleDelegate;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    private int mVideoHeight;
    private MediaHelper.VideoInfo mVideoInfo;
    private int mVideoWidth;
    private final IMediaPlayerInnerView mView;

    public MediaPlayerViewImp(IMediaPlayerInnerView iMediaPlayerInnerView, int i2) {
        this.TAG = new StringCompat(getClass().getSimpleName());
        this.mMpViewThread = new MpViewThread();
        this.mFitContent = false;
        this.mOpened = false;
        this.mView = iMediaPlayerInnerView;
        this.mMode = i2;
        if (i2 == 0) {
            this.mMediaPlayerDelegate = new MediaPlayerDelegate();
        } else {
            this.mMediaPlayerDelegate = new ListMediaPlayerDelegate();
        }
        addMediaPlayerListener(this);
        if (SUPPORT_DEBUG_VIEW) {
            iMediaPlayerInnerView.setWillNotDraw(false);
        }
    }

    private void computeVideo(MediaItem mediaItem, Consumer<MediaHelper.VideoInfo> consumer, VideoReqInfo videoReqInfo) {
        MediaHelper.VideoInfo videoInfo = MetadataManager.getInstance().get(mediaItem, videoReqInfo);
        this.mVideoInfo = videoInfo;
        if (videoInfo == null || isSharedMotionPhoto(mediaItem) || isSharedVideo(mediaItem)) {
            MetadataManager.getInstance().load(mediaItem, videoReqInfo, new g(this, videoReqInfo, consumer, 5));
        } else {
            consumer.accept(this.mVideoInfo);
        }
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

    private boolean isSupportItem(MediaItem mediaItem) {
        return MediaItemUtil.supportPreviewPlay(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$close$16(boolean z) {
        this.mMediaItem = null;
        if (z) {
            this.mVideoWidth = 0;
            this.mVideoHeight = 0;
        }
        this.mMediaPlayerDelegate.close(z);
        Optional.ofNullable(this.mAudioManagerDelegate).ifPresent(new l(5));
        Optional.ofNullable(this.mMediaSessionDelegate).ifPresent(new l(6));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$computeVideo$4(VideoReqInfo videoReqInfo, Consumer consumer, MediaHelper.VideoInfo videoInfo) {
        if (!videoReqInfo.isMotionSefPlay) {
            this.mVideoInfo = videoInfo;
        }
        consumer.accept(videoInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$disableWfdTcp$27() {
        this.mMediaPlayerDelegate.disableWfdTcp();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onComputeVideo$3() {
        if (this.mView.getVisibility() != 0) {
            this.mView.setVisibility(0);
            this.mView.setAlpha(0.0f);
        }
        this.mView.requestLayout();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onLayout$20(ViewParent viewParent, boolean z, int i2, int i7, int i8, int i10, ScaleDelegate scaleDelegate) {
        boolean z3 = z;
        ViewParent viewParent2 = viewParent;
        ScaleDelegate scaleDelegate2 = scaleDelegate;
        int i11 = i10;
        int i12 = i8;
        int i13 = i7;
        scaleDelegate2.onLayout(viewParent2, z3, i2, i13, i12, i11);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMeasure$19(RoundedCornerDelegate roundedCornerDelegate) {
        roundedCornerDelegate.setCoverViewSize(this.mSurfaceWidth, this.mSurfaceHeight);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPlay$17() {
        this.mView.setBackgroundColor(0);
        this.mView.setAlpha(1.0f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$open$0(MediaItem mediaItem, VideoReqInfo videoReqInfo) {
        if (this.mMode != 1) {
            Log.d(this.TAG, "open#run", Long.valueOf(mediaItem.getFileId()), Long.valueOf(mediaItem.getMediaId()), mediaItem.getMimeType(), Integer.valueOf(mediaItem.getFileDuration()));
        }
        this.mMediaItem = mediaItem;
        this.mVideoWidth = mediaItem.getWidth();
        this.mVideoHeight = mediaItem.getHeight();
        computeVideo(mediaItem, new k(this, 3), videoReqInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$open$1(AudioManagerDelegate audioManagerDelegate) {
        audioManagerDelegate.setAudioFocusEnabled(!this.mMediaPlayerDelegate.isAudioMute());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$pauseOnReady$5(int i2) {
        this.mMediaPlayerDelegate.pauseOnReady(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshCaptureView$14() {
        Optional.ofNullable(this.mClipViewDelegate).ifPresent(new l(7));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release3dEffect$46() {
        this.mMediaPlayerDelegate.release3dEffect();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resume$6(AudioManagerDelegate audioManagerDelegate) {
        audioManagerDelegate.setAudioFocusEnabled(!this.mMediaPlayerDelegate.isAudioMute());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resume$7() {
        this.mMediaPlayerDelegate.resume();
        Optional.ofNullable(this.mAudioManagerDelegate).ifPresent(new k(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$set3dEffectPosition$45(double d) {
        this.mMediaPlayerDelegate.set3dEffectPosition(d);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setAudioEraserOff$24(boolean z, String str) {
        this.mMediaPlayerDelegate.setAudioEraserOff(z, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setAudioMute$23(boolean z, boolean z3) {
        this.mMediaPlayerDelegate.setAudioMute(z);
        if (z3) {
            Optional.ofNullable(this.mAudioManagerDelegate).ifPresent(new L(z, 29));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setColorCorrect$50() {
        this.mMediaPlayerDelegate.setColorCorrect();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setLooping$25(boolean z) {
        this.mMediaPlayerDelegate.setLooping(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaybackLoop$47(boolean z) {
        this.mMediaPlayerDelegate.setPlaybackLoop(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setRenderingPosition$30(int i2) {
        this.mMediaPlayerDelegate.setRenderingPosition(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSlowMo$44(int i2, int i7, boolean z) {
        this.mMediaPlayerDelegate.setSlowMo(i2, i7, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSupportTimeTick$26(boolean z) {
        this.mMediaPlayerDelegate.setSupportTimeTick(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setVideoFilter$28(String str, int i2) {
        this.mMediaPlayerDelegate.setVideoFilter(str, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setVideoFrc$48(int i2) {
        this.mMediaPlayerDelegate.setVideoFrc(i2);
    }

    /* access modifiers changed from: private */
    public void onComputeVideo(MediaHelper.VideoInfo videoInfo) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            mediaItem.setVideoMetadataOrientation(videoInfo.orientation);
            Optional.ofNullable(this.mDebugInfoDelegate).ifPresent(new g6.g(7, videoInfo));
            int i2 = videoInfo.orientation;
            if (i2 == 90 || i2 == 270) {
                this.mVideoWidth = videoInfo.height;
                this.mVideoHeight = videoInfo.width;
            } else {
                this.mVideoWidth = videoInfo.width;
                this.mVideoHeight = videoInfo.height;
            }
            if (this.mMediaItem.isMotionPhoto()) {
                this.mMediaPlayerDelegate.setSubDataSource(videoInfo.offset, videoInfo.length);
            }
            this.mMediaPlayerDelegate.open(this.mMediaItem);
            this.mMpViewThread.runOnUiThread(new o(this, 1));
        }
    }

    private int resolveAdjustedSize(int i2, int i7) {
        int mode = View.MeasureSpec.getMode(i7);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(i2, View.MeasureSpec.getSize(i7));
        }
        if (mode != 1073741824) {
            return i2;
        }
        return View.MeasureSpec.getSize(i7);
    }

    private boolean supportInstantSlowMoPlay() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO || FoldUtils.isFlipCoverScreen(Utils.getActivity(this.mView.getView()))) {
            return false;
        }
        return true;
    }

    public void addContentDescription(Supplier<String> supplier) {
        this.mContentDescription = supplier;
    }

    public void addMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        this.mMediaPlayerDelegate.addMediaPlayerListener(mediaPlayerListener);
        Optional.ofNullable(this.mAudioManagerDelegate).ifPresent(new m(mediaPlayerListener, 0));
        Optional.ofNullable(this.mInstantSlowMoPlayDelegate).ifPresent(new m(mediaPlayerListener, 1));
    }

    public void addUsageHint(Supplier<String> supplier) {
        this.mAccessibilityUsageHint = supplier;
    }

    public void beginInstantSlowMoPlay() {
        MpViewThread mpViewThread = this.mMpViewThread;
        MediaPlayerDelegate mediaPlayerDelegate = this.mMediaPlayerDelegate;
        Objects.requireNonNull(mediaPlayerDelegate);
        mpViewThread.run(new b(mediaPlayerDelegate, 11));
    }

    public void bindCaptureView(View view, boolean z, boolean z3) {
        Optional.ofNullable(this.mClipViewDelegate).ifPresent(new p(view, z, z3));
        this.mView.invalidate();
    }

    public void captureFrame(Consumer<Bitmap> consumer, int i2, boolean z) {
        Optional.ofNullable(this.mCaptureDelegate).ifPresent(new c((Consumer) consumer, i2, z));
    }

    public Bitmap captureFrameInBackground(int i2, boolean z) {
        CaptureDelegate captureDelegate;
        try {
            if (!isInPlayState() || (captureDelegate = this.mCaptureDelegate) == null) {
                return null;
            }
            return captureDelegate.lambda$captureFrame$0((Consumer<Bitmap>) null, i2, z);
        } catch (Exception e) {
            a.r(e, new StringBuilder("captureFrameInBackground failed : "), this.TAG);
            return null;
        }
    }

    public void close() {
        close(false);
    }

    public void disableWfdTcp() {
        this.mMpViewThread.run(new o(this, 2));
    }

    public void endInstantSlowMoPlay() {
        MpViewThread mpViewThread = this.mMpViewThread;
        MediaPlayerDelegate mediaPlayerDelegate = this.mMediaPlayerDelegate;
        Objects.requireNonNull(mediaPlayerDelegate);
        mpViewThread.run(new b(mediaPlayerDelegate, 12));
    }

    public int getBottomMarginFromSupplier() {
        View view = (View) this.mView.getParent();
        if (view == null) {
            view = this.mView.getView();
        }
        return ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
    }

    public ClipViewDelegate getClipViewDelegate() {
        return this.mClipViewDelegate;
    }

    public int getCurrentPosition() {
        return this.mMediaPlayerDelegate.getCurrentPosition();
    }

    public RectF getDisplayMinRect() {
        float f;
        float f5;
        View view = this.mView.getView();
        View view2 = (View) Optional.ofNullable(this.mView.getParent()).map(new C0468a(11)).orElse((Object) null);
        if (view2 == null) {
            return new RectF((float) view.getLeft(), (float) view.getTop(), (float) view.getRight(), (float) view.getBottom());
        }
        float width = (float) view2.getWidth();
        float height = (float) view2.getHeight();
        float width2 = (float) view.getWidth();
        float height2 = (float) view.getHeight();
        if (width / height > width2 / height2) {
            f = (width2 * height) / height2;
            f5 = height;
        } else {
            f5 = (height2 * width) / width2;
            f = width;
        }
        float f8 = (width / 2.0f) - (f / 2.0f);
        float f10 = (height / 2.0f) - (f5 / 2.0f);
        return new RectF(f8, f10, f + f8, f5 + f10);
    }

    public RectF getDisplayRect() {
        View view = this.mView.getView();
        float right = ((float) (view.getRight() - view.getLeft())) / 2.0f;
        float bottom = ((float) (view.getBottom() - view.getTop())) / 2.0f;
        float scaleX = (view.getScaleX() * ((float) view.getWidth())) / 2.0f;
        float scaleX2 = (view.getScaleX() * ((float) view.getHeight())) / 2.0f;
        return new RectF(view.getX() + (right - scaleX), view.getY() + (bottom - scaleX2), view.getX() + right + scaleX, view.getY() + bottom + scaleX2);
    }

    public int getDuration() {
        return this.mMediaPlayerDelegate.getDurationMs();
    }

    public int getFrameRate() {
        return this.mMediaPlayerDelegate.getFrameRate();
    }

    public InstantSlowMoPlayDelegate getInstantSlowMoPlayDelegate() {
        return this.mInstantSlowMoPlayDelegate;
    }

    public void getLocation(int[] iArr) {
        ViewGroup viewGroup = (ViewGroup) this.mView.getParent();
        if (viewGroup != null) {
            viewGroup.getLocationInWindow(iArr);
        }
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public MediaPlayerDelegate getMediaPlayerDelegate() {
        return this.mMediaPlayerDelegate;
    }

    public ViewParent getParentView() {
        RoundedCornerDelegate roundedCornerDelegate = this.mRoundedCornerDelegate;
        if (roundedCornerDelegate != null) {
            return roundedCornerDelegate.getParentView();
        }
        return this.mView.getParent();
    }

    public PlayState getPlayState() {
        if (this.mOpened) {
            return this.mMediaPlayerDelegate.getPlayState();
        }
        return PlayState.NONE;
    }

    public int getRenderingPosition() {
        return this.mMediaPlayerDelegate.getRenderingPosition();
    }

    public ScaleDelegate getScaleDelegate() {
        return this.mScaleDelegate;
    }

    public float getScaleX() {
        RoundedCornerDelegate roundedCornerDelegate = this.mRoundedCornerDelegate;
        if (roundedCornerDelegate != null) {
            return roundedCornerDelegate.getScaleX();
        }
        return this.mView.getScaleXInner();
    }

    public float getScaleY() {
        RoundedCornerDelegate roundedCornerDelegate = this.mRoundedCornerDelegate;
        if (roundedCornerDelegate != null) {
            return roundedCornerDelegate.getScaleY();
        }
        return this.mView.getScaleYInner();
    }

    public float getSpeed() {
        return this.mMediaPlayerDelegate.getSpeed();
    }

    public int getSurfaceHeight() {
        return this.mSurfaceHeight;
    }

    public int getSurfaceWidth() {
        return this.mSurfaceWidth;
    }

    public int getTopMarginFromSupplier() {
        View view = (View) this.mView.getParent();
        if (view == null) {
            view = this.mView.getView();
        }
        return ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin;
    }

    public int getVideoHeight() {
        return this.mMediaPlayerDelegate.getVideoHeight();
    }

    public int getVideoWidth() {
        return this.mMediaPlayerDelegate.getVideoWidth();
    }

    public float getX() {
        RoundedCornerDelegate roundedCornerDelegate = this.mRoundedCornerDelegate;
        if (roundedCornerDelegate != null) {
            return roundedCornerDelegate.getX();
        }
        return this.mView.getXInner();
    }

    public float getY() {
        RoundedCornerDelegate roundedCornerDelegate = this.mRoundedCornerDelegate;
        if (roundedCornerDelegate != null) {
            return roundedCornerDelegate.getY();
        }
        return this.mView.getYInner();
    }

    public boolean handleTouchEvent(MotionEvent motionEvent) {
        GestureDelegate gestureDelegate = this.mGestureDelegate;
        if (gestureDelegate != null) {
            return gestureDelegate.handleTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean hasAudioTrack() {
        return this.mMediaPlayerDelegate.hasAudioTrack();
    }

    public boolean isAlreadyUp() {
        GestureDelegate gestureDelegate = this.mGestureDelegate;
        if (gestureDelegate != null) {
            return gestureDelegate.isAlreadyUp();
        }
        return false;
    }

    public boolean isAudioMute() {
        return this.mMediaPlayerDelegate.isAudioMute();
    }

    public boolean isInPlayState() {
        if (!this.mOpened || this.mMediaPlayerDelegate.getPlayState().value() < PlayState.PREPARED.value()) {
            return false;
        }
        return true;
    }

    public boolean isIncomingCall() {
        AudioManagerDelegate audioManagerDelegate = this.mAudioManagerDelegate;
        if (audioManagerDelegate == null || !audioManagerDelegate.isIncomingCall()) {
            return false;
        }
        return true;
    }

    public boolean isMaxScale() {
        ScaleDelegate scaleDelegate = this.mScaleDelegate;
        if (scaleDelegate != null) {
            return scaleDelegate.isMaxScale();
        }
        return false;
    }

    public boolean isMinScale() {
        ScaleDelegate scaleDelegate = this.mScaleDelegate;
        if (scaleDelegate != null) {
            return scaleDelegate.isMinScale();
        }
        return false;
    }

    public boolean isMovable() {
        ClipViewDelegate clipViewDelegate = this.mClipViewDelegate;
        if (clipViewDelegate != null) {
            return clipViewDelegate.isMovable();
        }
        return false;
    }

    public boolean isOpened() {
        return this.mOpened;
    }

    public boolean isPaused() {
        if (!this.mOpened || this.mMediaPlayerDelegate.getPlayState() != PlayState.PAUSE) {
            return false;
        }
        return true;
    }

    public boolean isPlaying() {
        if (!this.mOpened || this.mMediaPlayerDelegate.getPlayState() != PlayState.PLAY) {
            return false;
        }
        return true;
    }

    public boolean isToggleConsumable(float f, float f5) {
        ClipViewDelegate clipViewDelegate = this.mClipViewDelegate;
        if (clipViewDelegate == null) {
            return false;
        }
        if (clipViewDelegate.onSinglePress(f, f5) || this.mClipViewDelegate.isToggleConsumable()) {
            return true;
        }
        return false;
    }

    public boolean isVisualSeeking() {
        return this.mMediaPlayerDelegate.isVisualSeeking();
    }

    public boolean isZoomed() {
        ScaleDelegate scaleDelegate = this.mScaleDelegate;
        if (scaleDelegate != null) {
            return scaleDelegate.isZoomed();
        }
        return false;
    }

    public void onAttachedToWindow() {
        Optional.ofNullable(this.mRoundedCornerDelegate).ifPresent(new l(0));
    }

    public void onConfigurationChanged(Configuration configuration) {
        Optional.ofNullable(this.mRoundedCornerDelegate).ifPresent(new l(2));
    }

    public void onDrawForeground(Canvas canvas) {
        Optional.ofNullable(this.mDebugInfoDelegate).ifPresent(new Nb.a(canvas, 1));
    }

    public void onLayout(ViewParent viewParent, boolean z, int i2, int i7, int i8, int i10) {
        Optional.ofNullable(this.mScaleDelegate).ifPresent(new h(viewParent, z, i2, i7, i8, i10));
    }

    public boolean onMeasure(int i2, int i7) {
        if (!this.mFitContent) {
            return false;
        }
        int i8 = this.mVideoWidth;
        if (i8 == 0 || this.mVideoHeight == 0) {
            Optional.ofNullable(this.mRoundedCornerDelegate).ifPresent(new f(i2, i7, 2));
            return false;
        }
        this.mSurfaceWidth = resolveAdjustedSize(i8, i2);
        int resolveAdjustedSize = resolveAdjustedSize(this.mVideoHeight, i7);
        this.mSurfaceHeight = resolveAdjustedSize;
        int i10 = this.mVideoWidth;
        int i11 = resolveAdjustedSize * i10;
        int i12 = this.mVideoHeight;
        int i13 = this.mSurfaceWidth * i12;
        if (i11 > i13) {
            this.mSurfaceHeight = (int) Math.ceil(((double) i13) / ((double) i10));
        } else {
            this.mSurfaceWidth = (int) Math.ceil(((double) i11) / ((double) i12));
        }
        Optional.ofNullable(this.mRoundedCornerDelegate).ifPresent(new k(this, 0));
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        GestureDelegate gestureDelegate = this.mGestureDelegate;
        if (gestureDelegate != null) {
            return gestureDelegate.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void onVideoPlay(int i2) {
        this.mMpViewThread.runOnUiThread(new o(this, 6));
    }

    public void onVideoPrepared(int i2, int i7) {
        onVideoSizeChanged(i2, i7);
    }

    public void onVideoSizeChanged(int i2, int i7) {
        if (this.mVideoWidth != i2 || this.mVideoHeight != i7) {
            this.mVideoWidth = i2;
            this.mVideoHeight = i7;
            MpViewThread mpViewThread = this.mMpViewThread;
            IMediaPlayerInnerView iMediaPlayerInnerView = this.mView;
            Objects.requireNonNull(iMediaPlayerInnerView);
            mpViewThread.runOnUiThread(new C0451a(26, iMediaPlayerInnerView));
        }
    }

    public boolean open(MediaItem mediaItem, VideoReqInfo videoReqInfo) {
        if (this.mOpened) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "already opened " + MediaItemUtil.getSimpleLog(mediaItem));
            return false;
        } else if (!isSupportItem(mediaItem)) {
            StringCompat stringCompat2 = this.TAG;
            Log.e(stringCompat2, "open fail not supported " + MediaItemUtil.getSimpleLog(mediaItem));
            return false;
        } else {
            this.mMpViewThread.run(new d((Object) this, (Object) mediaItem, (Object) videoReqInfo, 22));
            this.mOpened = true;
            Optional.ofNullable(this.mMediaSessionDelegate).ifPresent(new l(4));
            if (videoReqInfo.requestAudioFocus) {
                Optional.ofNullable(this.mAudioManagerDelegate).ifPresent(new k(this, 2));
            }
            return true;
        }
    }

    public void pause() {
        this.mMediaPlayerDelegate.pause();
    }

    public Bitmap pauseAndCapture() {
        CaptureDelegate captureDelegate = this.mCaptureDelegate;
        if (captureDelegate != null) {
            return captureDelegate.pauseAndCapture();
        }
        return this.mView.getViewBitmap();
    }

    public void pauseOnReady(int i2) {
        this.mMpViewThread.run(new ic.f(this, i2, 2));
    }

    public void play() {
        MpViewThread mpViewThread = this.mMpViewThread;
        MediaPlayerDelegate mediaPlayerDelegate = this.mMediaPlayerDelegate;
        Objects.requireNonNull(mediaPlayerDelegate);
        mpViewThread.run(new b(mediaPlayerDelegate, 10));
    }

    public void refreshCaptureView() {
        ThreadUtil.postOnUiThread(new o(this, 3));
    }

    public void release3dEffect() {
        this.mMpViewThread.run(new o(this, 4));
    }

    public void removeMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        this.mMediaPlayerDelegate.removeMediaPlayerListener(mediaPlayerListener);
        Optional.ofNullable(this.mAudioManagerDelegate).ifPresent(new m(mediaPlayerListener, 2));
        Optional.ofNullable(this.mInstantSlowMoPlayDelegate).ifPresent(new m(mediaPlayerListener, 3));
    }

    public void resetAccessibility(View view) {
        this.mAccessibilityUsageHint = null;
        this.mContentDescription = null;
        view.setAccessibilityDelegate((View.AccessibilityDelegate) null);
    }

    public void resetItem() {
        this.mMediaItem = null;
        this.mVideoHeight = 0;
        this.mVideoWidth = 0;
    }

    public void resetScale() {
        Optional.ofNullable(this.mScaleDelegate).ifPresent(new C0453a(29));
    }

    public void resume() {
        this.mMpViewThread.run(new o(this, 0));
    }

    public void seekBegin() {
        this.mMediaPlayerDelegate.seekBegin();
    }

    public void seekFinish() {
        this.mMediaPlayerDelegate.seekFinish();
    }

    public void seekTo(int i2) {
        this.mMediaPlayerDelegate.seekTo(i2);
    }

    public void set3dEffectPosition(double d) {
        this.mMpViewThread.run(new i(this, d));
    }

    public void setAccessibility(View view) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onInitializeAccessibilityNodeInfo$0(AccessibilityNodeInfo accessibilityNodeInfo) {
                String str;
                if (MediaPlayerViewImp.this.mAccessibilityUsageHint != null) {
                    str = (String) MediaPlayerViewImp.this.mAccessibilityUsageHint.get();
                } else {
                    str = "";
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, str));
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onPopulateAccessibilityEvent$1(View view) {
                String str;
                if (MediaPlayerViewImp.this.mContentDescription != null) {
                    str = (String) MediaPlayerViewImp.this.mContentDescription.get();
                } else {
                    str = "";
                }
                view.setContentDescription(str);
            }

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                ThreadUtil.runOnBgThread(new a(this, accessibilityNodeInfo, 0));
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                if (accessibilityEvent.getEventType() == 32768) {
                    ThreadUtil.runOnBgThread(new a(this, view, 1));
                }
            }
        });
    }

    public void setAttribute(Context context, AttributeSet attributeSet, int i2) {
        boolean z;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MediaView);
        if (obtainStyledAttributes.getBoolean(R$styleable.MediaView_fit_content, false) || (i2 & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mFitContent = z;
        if ((i2 & 8) != 0) {
            this.mMediaPlayerDelegate.setIsPreview(true);
        }
        if (obtainStyledAttributes.getBoolean(R$styleable.MediaView_player_feature, false) || (i2 & 2) != 0) {
            MediaSessionDelegate mediaSessionDelegate = new MediaSessionDelegate(this.mView);
            this.mMediaSessionDelegate = mediaSessionDelegate;
            this.mMediaPlayerDelegate.addMediaPlayerListener(mediaSessionDelegate);
            this.mScaleDelegate = new ScaleDelegate(this.mView, this);
            this.mGestureDelegate = new GestureDelegate(this.mView, this);
            this.mCaptureDelegate = new CaptureDelegate(this.mView, this);
            this.mClipViewDelegate = new ClipViewDelegate((IClipRootView) this.mView.getView());
            if (!obtainStyledAttributes.getBoolean(R$styleable.MediaView_disable_audio_focus, false) && (i2 & 4) == 0) {
                this.mAudioManagerDelegate = new AudioManagerDelegate();
            }
            if (PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO && supportInstantSlowMoPlay()) {
                this.mInstantSlowMoPlayDelegate = new InstantSlowMoPlayDelegate(this.mView, this);
            }
            if (SUPPORT_DEBUG_VIEW) {
                this.mDebugInfoDelegate = new DebugInfoDelegate(this.mView, this);
            }
        }
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.MediaView_rounded_corner_radius, -1);
        if (dimensionPixelOffset > 0) {
            this.mRoundedCornerDelegate = new RoundedCornerDelegate(this.mView, dimensionPixelOffset);
        }
        obtainStyledAttributes.recycle();
    }

    public void setAudioEraserOff(boolean z, String str) {
        this.mMpViewThread.run(new B8.g((Object) this, z, (Object) str, 13));
    }

    public void setAudioMute(boolean z) {
        setAudioMute(z, true);
    }

    public void setBgm(BgmOptions bgmOptions) {
        this.mMediaPlayerDelegate.setBgm(bgmOptions);
    }

    public void setCenterCrop(boolean z) {
        Optional.ofNullable(this.mScaleDelegate).ifPresent(new L(z, 27));
    }

    public void setColorCorrect() {
        this.mMpViewThread.run(new o(this, 5));
    }

    public void setDefaultPosition(boolean z) {
        Optional.ofNullable(this.mGestureDelegate).ifPresent(new l(1));
        Optional.ofNullable(this.mScaleDelegate).ifPresent(new L(z, 28));
    }

    public void setDoubleTapSeekListener(DoubleTapSeekListener doubleTapSeekListener) {
        GestureDelegate gestureDelegate = this.mGestureDelegate;
        if (gestureDelegate != null) {
            gestureDelegate.setDoubleTapSeekListener(doubleTapSeekListener);
        }
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
        this.mMediaPlayerDelegate.setLogTag(obj);
        Optional.ofNullable(this.mMediaSessionDelegate).ifPresent(new ic.g(4, obj));
        Optional.ofNullable(this.mRoundedCornerDelegate).ifPresent(new ic.g(5, obj));
        Optional.ofNullable(this.mDebugInfoDelegate).ifPresent(new ic.g(0, obj));
        Optional.ofNullable(this.mAudioManagerDelegate).ifPresent(new ic.g(1, obj));
        Optional.ofNullable(this.mCaptureDelegate).ifPresent(new ic.g(2, obj));
        Optional.ofNullable(this.mClipViewDelegate).ifPresent(new ic.g(3, obj));
    }

    public void setLooping(boolean z) {
        this.mMpViewThread.run(new j(this, z, 0));
    }

    public void setOnFlingListener(OnFlingListener onFlingListener) {
        GestureDelegate gestureDelegate = this.mGestureDelegate;
        if (gestureDelegate != null) {
            gestureDelegate.setOnFlingListener(onFlingListener);
        }
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        GestureDelegate gestureDelegate = this.mGestureDelegate;
        if (gestureDelegate != null) {
            gestureDelegate.setOnLongClickListener(onLongClickListener);
        }
    }

    public void setPadding(int i2, int i7, int i8, int i10) {
        Optional.ofNullable(this.mScaleDelegate).ifPresent(new n(i2, i7, i8, i10));
    }

    public void setPinchShrinkSupport(OnViewerExitGestureListener onViewerExitGestureListener) {
        Optional.ofNullable(this.mScaleDelegate).ifPresent(new g6.g(9, onViewerExitGestureListener));
    }

    public void setPlaybackLoop(boolean z) {
        this.mMpViewThread.run(new j(this, z, 2));
    }

    public void setPlaybackRange(int i2, int i7) {
        this.mMediaPlayerDelegate.setPlaybackRange(i2, i7);
    }

    public void setRenderingPosition(int i2) {
        this.mMpViewThread.run(new ic.f(this, i2, 1));
    }

    public void setScaleEndListener(ScaleEndListener scaleEndListener) {
        Optional.ofNullable(this.mScaleDelegate).ifPresent(new g6.g(6, scaleEndListener));
    }

    public void setScaleRelative(float f) {
        Optional.ofNullable(this.mScaleDelegate).ifPresent(new x(f, 2));
    }

    public void setScaleX(float f) {
        RoundedCornerDelegate roundedCornerDelegate = this.mRoundedCornerDelegate;
        if (roundedCornerDelegate != null) {
            roundedCornerDelegate.setScaleX(f);
        } else {
            this.mView.setScaleXInner(f);
        }
    }

    public void setScaleY(float f) {
        RoundedCornerDelegate roundedCornerDelegate = this.mRoundedCornerDelegate;
        if (roundedCornerDelegate != null) {
            roundedCornerDelegate.setScaleY(f);
        } else {
            this.mView.setScaleYInner(f);
        }
    }

    public void setSlowMo(int i2, int i7, boolean z) {
        this.mMpViewThread.run(new J6.a(this, i2, i7, z));
    }

    public void setSpeed(float f) {
        this.mMediaPlayerDelegate.setSpeed(f);
    }

    public void setSupportTimeTick(boolean z) {
        this.mMpViewThread.run(new j(this, z, 3));
    }

    public void setTranslationListener(OnTranslationListener onTranslationListener) {
        Optional.ofNullable(this.mScaleDelegate).ifPresent(new g6.g(8, onTranslationListener));
    }

    public void setVideoCaptured(int i2, Bitmap bitmap) {
        Optional.ofNullable(this.mCaptureDelegate).ifPresent(new J(i2, (Object) bitmap, 11));
    }

    public void setVideoFilter(String str, int i2) {
        this.mMpViewThread.run(new C0418a((Object) this, (Object) str, i2, 8));
    }

    public void setVideoFrc(int i2) {
        this.mMpViewThread.run(new ic.f(this, i2, 0));
    }

    public void setVolume(float f, float f5) {
        this.mMediaPlayerDelegate.setVolume(f, f5);
    }

    public void setX(float f) {
        RoundedCornerDelegate roundedCornerDelegate = this.mRoundedCornerDelegate;
        if (roundedCornerDelegate != null) {
            roundedCornerDelegate.setX(f);
        } else {
            this.mView.setXInner(f);
        }
    }

    public void setY(float f) {
        RoundedCornerDelegate roundedCornerDelegate = this.mRoundedCornerDelegate;
        if (roundedCornerDelegate != null) {
            roundedCornerDelegate.setY(f);
        } else {
            this.mView.setYInner(f);
        }
    }

    public void surfaceCreated(Surface surface) {
        this.mMediaPlayerDelegate.onSurfaceCreated(surface);
    }

    public void surfaceDestroyed() {
        this.mMediaPlayerDelegate.onSurfaceDestroyed();
        Optional.ofNullable(this.mInstantSlowMoPlayDelegate).ifPresent(new l(3));
    }

    public void updateBackgroundColor(boolean z) {
        int i2;
        if (z) {
            i2 = 0;
        } else {
            i2 = this.mView.getContext().getColor(R$color.daynight_default_background);
        }
        this.mView.setBackgroundColor(i2);
    }

    public void updateItem(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        this.mMediaPlayerDelegate.updateItem(mediaItem);
    }

    public void visualSeekTo(int i2) {
        this.mMediaPlayerDelegate.visualSeekTo(i2);
    }

    public void close(boolean z) {
        if (this.mOpened) {
            this.mOpened = false;
            this.mMpViewThread.cancel();
            this.mMpViewThread.run(new j(this, z, 1));
        }
    }

    public void setAudioMute(boolean z, boolean z3) {
        this.mMpViewThread.run(new androidx.core.widget.a(this, z, z3, 4));
    }

    public MediaPlayerViewImp(IMediaPlayerInnerView iMediaPlayerInnerView) {
        this(iMediaPlayerInnerView, 0);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ListMediaPlayerDelegate extends MediaPlayerDelegate {
        public void log(String str) {
        }
    }
}
