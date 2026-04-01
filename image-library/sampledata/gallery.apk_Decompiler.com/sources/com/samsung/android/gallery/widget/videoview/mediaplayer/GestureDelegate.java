package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.content.Context;
import android.graphics.PointF;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewParent;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.capture.ClipViewDelegate;
import com.samsung.android.gallery.widget.gesture.GestureIdentifier;
import com.samsung.android.gallery.widget.photoview.OnFlingListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.DoubleTapSeekListener;
import ic.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GestureDelegate {
    private static final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);
    private static final boolean SUPPORT_DEBUG_VIEW = PocFeatures.isEnabled(PocFeatures.RegionDecodingInfo);
    /* access modifiers changed from: private */
    public final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    /* access modifiers changed from: private */
    public final IDelegateListener mDelegateListener;
    private DoubleTapSeekListener mDoubleTapSeekListener;
    private FlingAnimation mFlingAnimX;
    private FlingAnimation mFlingAnimY;
    private GestureIdentifier mGestureIdentifier;
    private OnFlingListener mOnFlingListener;
    /* access modifiers changed from: private */
    public View.OnLongClickListener mOnLongClickListener;
    private boolean mTouchDown;
    /* access modifiers changed from: private */
    public final IMediaPlayerInnerView mView;

    public GestureDelegate(IMediaPlayerInnerView iMediaPlayerInnerView, IDelegateListener iDelegateListener) {
        this.mView = iMediaPlayerInnerView;
        this.mDelegateListener = iDelegateListener;
        setGestureIdentifier(iMediaPlayerInnerView.getContext());
    }

    /* access modifiers changed from: private */
    public boolean doubleTapSeek(MotionEvent motionEvent) {
        if (this.mDoubleTapSeekListener == null || !this.mDelegateListener.getMediaPlayerDelegate().isSurfaceReady()) {
            return false;
        }
        if (motionEvent.getX() < ((float) this.mView.getView().getWidth()) * 0.2f) {
            if (IS_RTL) {
                this.mDoubleTapSeekListener.onDoubleTapFf();
            } else {
                this.mDoubleTapSeekListener.onDoubleTapRew();
            }
            return true;
        } else if (motionEvent.getX() <= ((float) this.mView.getView().getWidth()) * 0.8f) {
            return false;
        } else {
            if (IS_RTL) {
                this.mDoubleTapSeekListener.onDoubleTapRew();
            } else {
                this.mDoubleTapSeekListener.onDoubleTapFf();
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void flingTo(float f, float f5) {
        try {
            if (!isFlickToNext(f, f5)) {
                Pair<Float, Float> xRange = this.mDelegateListener.getScaleDelegate().getXRange(this.mView.getView().getScaleX());
                FlingAnimation flingAnimation = this.mFlingAnimX;
                if (flingAnimation == null) {
                    this.mFlingAnimX = new FlingAnimation(this.mView.getView(), DynamicAnimation.f986X);
                } else {
                    flingAnimation.cancel();
                }
                ((FlingAnimation) this.mFlingAnimX.setStartVelocity(f).setMinValue(((Float) xRange.first).floatValue()).setMaxValue(((Float) xRange.second).floatValue()).setFriction(2.0f).addUpdateListener(new a(this, 0))).start();
                Pair<Float, Float> yRange = this.mDelegateListener.getScaleDelegate().getYRange(this.mView.getView().getScaleY());
                FlingAnimation flingAnimation2 = this.mFlingAnimY;
                if (flingAnimation2 == null) {
                    this.mFlingAnimY = new FlingAnimation(this.mView.getView(), DynamicAnimation.Y);
                } else {
                    flingAnimation2.cancel();
                }
                ((FlingAnimation) this.mFlingAnimY.setStartVelocity(f5).setMinValue(((Float) yRange.first).floatValue()).setMaxValue(((Float) yRange.second).floatValue()).setFriction(2.0f).addUpdateListener(new a(this, 1))).start();
            }
        } catch (IllegalArgumentException e) {
            Log.e(this.TAG, e.toString());
        }
    }

    private boolean isFlickToNext(float f, float f5) {
        View view = this.mView.getView();
        Pair<Float, Float> xRange = this.mDelegateListener.getScaleDelegate().getXRange(view.getScaleX());
        boolean z = false;
        if ((view.getX() != ((Float) xRange.first).floatValue() && view.getX() != ((Float) xRange.second).floatValue()) || Math.abs(f) <= 900.0f || Math.abs(f) <= Math.abs(f5)) {
            return false;
        }
        if (f < 0.0f) {
            z = true;
        }
        onFling(z);
        return true;
    }

    private boolean isMoveOnZoomState(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 2 || !isZoomed()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$flingTo$0(DynamicAnimation dynamicAnimation, float f, float f5) {
        this.mDelegateListener.getClipViewDelegate().onDraw();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$flingTo$1(DynamicAnimation dynamicAnimation, float f, float f5) {
        this.mDelegateListener.getClipViewDelegate().onDraw();
    }

    private void onTouchEventInternal(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z = true;
        if (action != 0) {
            if (action != 1) {
                if (action == 3) {
                    this.mTouchDown = false;
                    return;
                } else if (action != 6) {
                    return;
                }
            }
            if (motionEvent.getPointerCount() - 1 <= 0) {
                z = false;
            }
            this.mTouchDown = z;
            return;
        }
        this.mTouchDown = true;
    }

    private boolean onTouchFrameRateControlPlay(MotionEvent motionEvent) {
        if (this.mDelegateListener.getInstantSlowMoPlayDelegate() == null || !this.mDelegateListener.getInstantSlowMoPlayDelegate().onTouch(motionEvent)) {
            return false;
        }
        return true;
    }

    public void cancelFlingAnimation() {
        FlingAnimation flingAnimation = this.mFlingAnimX;
        if (flingAnimation != null) {
            flingAnimation.cancel();
        }
        FlingAnimation flingAnimation2 = this.mFlingAnimY;
        if (flingAnimation2 != null) {
            flingAnimation2.cancel();
        }
    }

    public boolean handleTouchEvent(MotionEvent motionEvent) {
        GestureIdentifier gestureIdentifier;
        boolean isScaleAvailable = this.mDelegateListener.getScaleDelegate().isScaleAvailable();
        if (onTouchCapture(motionEvent)) {
            return true;
        }
        if ((!isScaleAvailable || (gestureIdentifier = this.mGestureIdentifier) == null || !gestureIdentifier.onTouchEvent(motionEvent)) && !isMoveOnZoomState(motionEvent)) {
            return false;
        }
        return true;
    }

    public boolean isAlreadyUp() {
        return !this.mTouchDown;
    }

    public boolean isZoomed() {
        return this.mDelegateListener.getScaleDelegate().isZoomed();
    }

    public void onFling(boolean z) {
        if (this.mOnFlingListener != null && ViewUtils.isVisible(this.mView.getView())) {
            this.mOnFlingListener.onFling(z);
        }
    }

    public boolean onTouchCapture(MotionEvent motionEvent) {
        ClipViewDelegate clipViewDelegate = this.mDelegateListener.getClipViewDelegate();
        boolean z = false;
        if (!clipViewDelegate.hasData()) {
            return false;
        }
        boolean onTouch = clipViewDelegate.onTouch(motionEvent);
        if (this.mView.getTouchInteractionViewParent() != null) {
            ViewParent touchInteractionViewParent = this.mView.getTouchInteractionViewParent();
            if (onTouch || isZoomed() || motionEvent.getPointerCount() > 1) {
                z = true;
            }
            touchInteractionViewParent.requestDisallowInterceptTouchEvent(z);
        }
        return onTouch;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (SUPPORT_DEBUG_VIEW) {
            this.mView.invalidate();
        }
        onTouchEventInternal(motionEvent);
        return onTouchFrameRateControlPlay(motionEvent);
    }

    public void setDoubleTapSeekListener(DoubleTapSeekListener doubleTapSeekListener) {
        this.mDoubleTapSeekListener = doubleTapSeekListener;
    }

    public void setGestureIdentifier(Context context) {
        GestureIdentifier gestureIdentifier = new GestureIdentifier(context, new GestureIdentifier.SimpleGestureListener() {
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (GestureDelegate.this.doubleTapSeek(motionEvent)) {
                    return true;
                }
                return GestureDelegate.this.mDelegateListener.getScaleDelegate().onDoubleTap(motionEvent);
            }

            public boolean onDown(MotionEvent motionEvent) {
                GestureDelegate.this.cancelFlingAnimation();
                if (GestureDelegate.this.mView.getTouchInteractionViewParent() == null) {
                    return true;
                }
                GestureDelegate.this.mView.getTouchInteractionViewParent().requestDisallowInterceptTouchEvent(GestureDelegate.this.isZoomed());
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
                if (!GestureDelegate.this.isZoomed()) {
                    return false;
                }
                GestureDelegate.this.flingTo(f, f5);
                return true;
            }

            public void onLongPress(MotionEvent motionEvent) {
                if (GestureDelegate.this.mDelegateListener.getClipViewDelegate().onLongPress(motionEvent.getX(), motionEvent.getY())) {
                    GestureDelegate.this.mView.getView().invalidate();
                } else if (GestureDelegate.this.mDelegateListener.getInstantSlowMoPlayDelegate() != null && GestureDelegate.this.mDelegateListener.getInstantSlowMoPlayDelegate().onLongPress(motionEvent)) {
                    Log.d(GestureDelegate.this.TAG, "InstantSlowMoPlayDelegate:: long pressed");
                } else if (GestureDelegate.this.mOnLongClickListener != null) {
                    GestureDelegate.this.mOnLongClickListener.onLongClick(GestureDelegate.this.mView.getView());
                }
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                return GestureDelegate.this.mDelegateListener.getScaleDelegate().onScale(scaleGestureDetector);
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return GestureDelegate.this.mDelegateListener.getScaleDelegate().onScaleBegin(scaleGestureDetector);
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
                if (!GestureDelegate.this.isZoomed()) {
                    return false;
                }
                PointF ensureBoundary = GestureDelegate.this.mDelegateListener.getScaleDelegate().ensureBoundary(GestureDelegate.this.mView.getView().getX() - f, GestureDelegate.this.mView.getView().getY() - f5, GestureDelegate.this.mView.getView().getScaleX());
                GestureDelegate.this.mView.getView().setX(ensureBoundary.x);
                GestureDelegate.this.mView.getView().setY(ensureBoundary.y);
                GestureDelegate.this.mDelegateListener.getClipViewDelegate().onDraw();
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                GestureDelegate.this.mView.getView().performClick();
                return true;
            }
        });
        this.mGestureIdentifier = gestureIdentifier;
        gestureIdentifier.setQuickScaleEnabled(false);
    }

    public void setOnFlingListener(OnFlingListener onFlingListener) {
        this.mOnFlingListener = onFlingListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }
}
