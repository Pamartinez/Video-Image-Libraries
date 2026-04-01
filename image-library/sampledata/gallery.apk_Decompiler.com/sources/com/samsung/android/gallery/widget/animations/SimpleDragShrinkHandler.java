package com.samsung.android.gallery.widget.animations;

import A.a;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Pair;
import android.view.DragEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.abstraction.VideoBackupInfo;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.StatusCodes;
import i.C0212a;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SimpleDragShrinkHandler extends SimpleShrinkHandler {
    protected float TARGET_SHRINK_SCALE;
    protected float mCornerRadius = 0.0f;
    float mFromH;
    private boolean mFromLeft;
    private boolean mFromTop;
    float mFromW;
    float mImageViewBeginX;
    float mImageViewBeginY;
    float mPrevX;
    float mPrevY;
    double mScaleRatio;
    float mStartX;
    float mStartY;
    private float mTargetPositionX;
    private float mTargetPositionY;
    float mToH;
    float mToW;
    private SpringAnimation mTransXSpringAnim;
    private SpringAnimation mTransYSpringAnim;

    public SimpleDragShrinkHandler(SimpleShrinkView simpleShrinkView, ListViewHolder listViewHolder) {
        super(simpleShrinkView, listViewHolder);
        this.mDuration = StatusCodes.INPUT_MISSING;
        this.mTimeInterpolator = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
        this.TARGET_SHRINK_SCALE = 0.9f;
    }

    private void cancelSpringAnimation(SpringAnimation springAnimation) {
        if (springAnimation != null && springAnimation.isRunning()) {
            springAnimation.cancel();
        }
    }

    private void end() {
        Log.st(this.TAG, "onShrinkFinish");
        removeDragListener();
        this.mView.mBlackboard.postEvent(EventMessage.obtain(3017));
        clearShrinkData(true);
    }

    private double getDistance(float f, float f5) {
        float f8 = this.mStartY;
        if (f8 > f5) {
            return MapUtil.INVALID_LOCATION;
        }
        return Math.sqrt((double) ((f5 - f8) * (f5 - f8)));
    }

    private View.DragShadowBuilder getDragShadowBuilder() {
        return new View.DragShadowBuilder() {
            public void onProvideShadowMetrics(Point point, Point point2) {
                point.set(1, 1);
                point2.set(0, 0);
            }
        };
    }

    private double getScaleRatio(double d) {
        double min = Math.min(d / 50.0d, 1.0d);
        this.mScaleRatio = min;
        return min;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDragMove$3(View view, float f, double d) {
        boolean z;
        view.setAlpha(f);
        ViewUtils.setAlpha(this.mView.mTopView, f);
        if (this.mIsQuickView) {
            setSystemUiWithAlpha();
            return;
        }
        Window window = this.mView.getWindow();
        if (d <= 50.0d) {
            z = true;
        } else {
            z = false;
        }
        setSystemUi(window, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDragMove$4(View view) {
        view.setAlpha(0.0f);
        ViewUtils.setAlpha(this.mView.mTopView, 0.0f);
        setSystemUi(this.mView.getWindow(), false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onShrinkFinish$0() {
        super.onShrinkFinish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restartViewer$1(Bitmap bitmap) {
        SimpleShrinkView simpleShrinkView = this.mView;
        ImageView imageView = simpleShrinkView.mShrinkView;
        if (imageView != null) {
            simpleShrinkView.mShrinkView = null;
            if (simpleShrinkView.mMediaItem == null || bitmap == null || bitmap.isRecycled() || !this.mView.mMediaItem.isImage()) {
                Log.st(this.TAG, "restartViewer : publish bitmap failed");
            } else {
                Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                String str = this.TAG;
                Log.st(str, "restartViewer : publish bitmap : " + Logger.toString(copy));
                this.mView.mBlackboard.publish("data://image_viewer_return_info", new Pair(Integer.valueOf(this.mView.mMediaItem.getSimpleHashCode()), copy));
            }
            returnShrinkView(imageView);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$returnShrinkView$2(ImageView imageView, int i2, int i7, int i8, int i10, float f, float f5, float f8, float f10, float f11, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = (int) ((((float) i7) * animatedFraction) + ((float) i2));
        layoutParams.height = (int) ((((float) i10) * animatedFraction) + ((float) i8));
        imageView.setLayoutParams(layoutParams);
        float f12 = 1.0f - animatedFraction;
        imageView.setX(this.mImageViewBeginX + (f * f12));
        imageView.setY(this.mImageViewBeginY + (f5 * f12));
        float f13 = 1.0f - (f8 * f12);
        ViewUtils.setAlpha(this.mView.mShrinkBackgroundView, f13);
        ViewUtils.setAlpha(this.mView.mTopView, f13);
        updateRotation(imageView, f10, animatedFraction);
        SimpleShrinkView simpleShrinkView = this.mView;
        float f14 = this.mFromW;
        simpleShrinkView.scaleRatio = (double) ((((float) layoutParams.width) - f14) / (this.mToW - f14));
        this.mCornerRadius = C0212a.a(0.0f, f11, animatedFraction, f11);
        imageView.invalidateOutline();
    }

    /* access modifiers changed from: private */
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case 1:
                return onDragBegin(view, dragEvent.getX(), dragEvent.getY());
            case 2:
                return onDragMove(view, dragEvent.getX(), dragEvent.getY());
            case 3:
            case 4:
                return onDragEnd(view, dragEvent.getX(), dragEvent.getY());
            case 5:
                Log.stv(this.TAG, "onDragEntered");
                return true;
            case 6:
                Log.stv(this.TAG, "onDragExited");
                return true;
            default:
                return false;
        }
    }

    private boolean onDragMove(View view, float f, float f5) {
        boolean z;
        int i2;
        float f8;
        float f10 = f;
        float f11 = f5;
        SimpleShrinkView simpleShrinkView = this.mView;
        ImageView imageView = simpleShrinkView.mShrinkView;
        ImageView imageView2 = simpleShrinkView.mShrinkBackgroundView;
        if (imageView == null || imageView2 == null) {
            Log.ste(this.TAG, "onDragMove failed. null view");
            return true;
        }
        double distance = getDistance(f10, f11);
        double scaleRatio = getScaleRatio(distance);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        float f12 = this.mFromW;
        layoutParams.width = (int) ((((double) (this.mToW - f12)) * scaleRatio) + ((double) f12));
        float f13 = this.mFromH;
        layoutParams.height = (int) ((((double) (this.mToH - f13)) * scaleRatio) + ((double) f13));
        if (canReturn()) {
            this.mView.scaleRatio = scaleRatio;
            z = true;
            this.mCornerRadius = (float) ((scaleRatio * ((double) 65.0f)) + ((double) 0.0f));
            imageView.invalidateOutline();
        } else {
            z = true;
        }
        imageView.setLayoutParams(layoutParams);
        float width = ((float) (imageView.getWidth() - layoutParams.width)) / 2.0f;
        if (Features.isEnabled(Features.IS_RTL)) {
            i2 = -1;
        } else {
            i2 = z;
        }
        imageView.setX(((imageView.getX() + f10) - this.mPrevX) + (width * ((float) i2)));
        imageView.setY(((imageView.getY() + f11) - this.mPrevY) + (((float) (imageView.getHeight() - layoutParams.height)) / 2.0f));
        if (canReturn()) {
            if (distance <= 50.0d) {
                f8 = Math.max((float) Math.cos((((distance / 50.0d) * 3.141592653589793d) * 0.5d) / 0.6d), 0.0f);
            } else {
                f8 = 0.0f;
            }
            float f14 = this.mAlphaWeight;
            if (f14 > 0.0f) {
                f8 += f14;
            }
            float f15 = f8;
            ImageView imageView3 = imageView2;
            imageView3.post(new k(this, imageView3, f15, distance));
        } else {
            ImageView imageView4 = imageView2;
            imageView4.post(new g(1, this, imageView4));
        }
        this.mPrevY = f11;
        this.mPrevX = f10;
        return z;
    }

    private void startSpringAnimation(SpringAnimation springAnimation, float f, Runnable runnable) {
        SpringForce springForce = new SpringForce();
        springForce.setFinalPosition(f);
        springForce.setStiffness(200.0f);
        springForce.setDampingRatio(0.75f);
        springAnimation.setSpring(springForce);
        if (runnable != null) {
            this.mTransYSpringAnim.addEndListener(new h(runnable));
        }
        springAnimation.start();
    }

    public boolean canReturn() {
        boolean z;
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mView.mBlackboard);
        if (readLocationKeyCurrent != null) {
            readLocationKeyCurrent = ArgumentsUtil.removeArgs(readLocationKeyCurrent);
        }
        if (LocationKey.isPictures(readLocationKeyCurrent) || LocationKey.isTrash(readLocationKeyCurrent) || LocationKey.isMapFiltered(readLocationKeyCurrent) || LocationKey.isMapMatch(readLocationKeyCurrent) || (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85 && LocationKey.isStoryHighlight(readLocationKeyCurrent))) {
            z = true;
        } else {
            z = false;
        }
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder == null || !listViewHolder.itemView.isAttachedToWindow() || !z || this.mViewHolder.getViewPosition() == -1) {
            return false;
        }
        return true;
    }

    public void cancelAnimation() {
        super.cancelAnimation();
        cancelSpringAnimation(this.mTransXSpringAnim);
        cancelSpringAnimation(this.mTransYSpringAnim);
    }

    public ArrayList<Animator> createAnimatorSet(final View view, View view2) {
        ArrayList<Animator> createAnimatorSet = super.createAnimatorSet(view, view2);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            final float fromRadius;
            final float toRadius;

            {
                float f;
                this.fromRadius = SimpleDragShrinkHandler.this.mCornerRadius;
                ListViewHolder listViewHolder = SimpleDragShrinkHandler.this.mViewHolder;
                if (listViewHolder != null) {
                    f = listViewHolder.getCornerRadius();
                } else {
                    f = 0.0f;
                }
                this.toRadius = f;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                try {
                    SimpleDragShrinkHandler simpleDragShrinkHandler = SimpleDragShrinkHandler.this;
                    float f = this.fromRadius;
                    simpleDragShrinkHandler.mCornerRadius = C0212a.a(this.toRadius, this.fromRadius, valueAnimator.getAnimatedFraction(), f);
                    view.invalidateOutline();
                } catch (Exception e) {
                    a.s(e, new StringBuilder("onAnimationUpdate failed "), SimpleDragShrinkHandler.this.TAG);
                }
            }
        });
        createAnimatorSet.add(ofFloat);
        return createAnimatorSet;
    }

    public PropertyAnimator createBackgroundColorAnimator() {
        PropertyAnimator createBackgroundColorAnimator = super.createBackgroundColorAnimator();
        createBackgroundColorAnimator.setFloatValues(new float[]{(float) this.mScaleRatio, 1.0f});
        return createBackgroundColorAnimator;
    }

    public void enableDragMasking() {
        this.mView.enableDragMasking();
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public boolean isReturn(float f, float f5) {
        if (getDistance(f, f5) <= 100.0d) {
            return true;
        }
        return false;
    }

    public boolean onDragBegin(View view, float f, float f5) {
        this.mPrevX = f;
        this.mStartX = f;
        this.mPrevY = f5;
        this.mStartY = f5;
        this.mScaleRatio = MapUtil.INVALID_LOCATION;
        String str = this.TAG;
        Log.st(str, "onDragBegin {" + this.mStartX + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mStartY + "}");
        this.mState.set(1);
        enableDragMasking();
        this.mImageViewBeginX = this.mView.mShrinkView.getX();
        this.mImageViewBeginY = this.mView.mShrinkView.getY();
        setOutlineProvider();
        return true;
    }

    public boolean onDragEnd(View view, float f, float f5) {
        if (this.mState.getAndSet(2) == 2) {
            Log.ste(this.TAG, "onScaleEnd skip. multiple called");
            return true;
        }
        String str = this.TAG;
        Log.st(str, "onDragEnd {" + f + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f5 + "}");
        if (!isReturn(f, f5) || !canReturn()) {
            this.mView.disableDragMasking();
            startShrinkAnimation();
        } else {
            VideoBackupInfo videoBackupInfo = (VideoBackupInfo) this.mView.mBlackboard.read("data://video_viewer_return_info");
            Bitmap bitmap = this.mView.mBitmap;
            end();
            Consumer<Boolean> consumer = this.mFinishListener;
            if (consumer != null && !this.mIsQuickView) {
                consumer.accept(Boolean.TRUE);
            }
            restartViewer(bitmap);
            this.mView.mBlackboard.publish("data://video_viewer_return_info", videoBackupInfo);
        }
        return true;
    }

    public void onShrinkError() {
        startShrinkAnimation();
    }

    public void onShrinkFinish() {
        startSpringAnimation(new c(2, this));
    }

    public void removeDragListener() {
        this.mView.getDecorView().setOnDragListener((View.OnDragListener) null);
    }

    public void restartViewer(Bitmap bitmap) {
        ThreadUtil.postOnUiThreadDelayed(new g(0, this, bitmap), 150);
    }

    public void returnShrinkView(ImageView imageView) {
        String str = this.TAG;
        Log.majorEvent(str, "returnShrinkView" + Logger.v(Float.valueOf(this.mImageViewBeginX), Float.valueOf(this.mImageViewBeginY), Float.valueOf(this.mFromW), Float.valueOf(this.mFromH)));
        float x9 = imageView.getX() - this.mImageViewBeginX;
        float y = imageView.getY() - this.mImageViewBeginY;
        int height = imageView.getHeight();
        int i2 = (int) (this.mFromH - ((float) height));
        int width = imageView.getWidth();
        int i7 = (int) (this.mFromW - ((float) width));
        float alpha = 1.0f - this.mView.mShrinkBackgroundView.getAlpha();
        float f = this.mCornerRadius;
        float rotation = imageView.getRotation();
        ValueAnimator duration = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(200);
        final ImageView imageView2 = imageView;
        duration.addUpdateListener(new j(this, imageView2, width, i7, height, i2, x9, y, alpha, rotation, f));
        duration.addListener(new SimpleAnimatorListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onAnimationEnd$0(ImageView imageView, Object obj, Bundle bundle) {
                Log.v(SimpleDragShrinkHandler.this.TAG, "returnShrinkView = remove view ");
                ViewUtils.removeSelf(imageView);
                SimpleDragShrinkHandler.this.mView.removeExtraShrinkViews();
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onAnimationEnd$1(ImageView imageView) {
                Log.v(SimpleDragShrinkHandler.this.TAG, "returnShrinkView = remove view for Q");
                ViewUtils.removeSelf(imageView);
                SimpleDragShrinkHandler.this.mView.removeExtraShrinkViews();
                SeApiCompat.convertActivityFromTranslucent(BlackboardUtils.readActivity(SimpleDragShrinkHandler.this.mView.mBlackboard));
            }

            public void onAnimationEnd(Animator animator) {
                Bitmap bitmap;
                SimpleDragShrinkHandler simpleDragShrinkHandler = SimpleDragShrinkHandler.this;
                if (simpleDragShrinkHandler.mViewHolder != null) {
                    Log.v(simpleDragShrinkHandler.TAG, "returnShrinkView = onAnimationEnd ");
                    ViewUtils.setVisibility(SimpleDragShrinkHandler.this.mViewHolder.getScalableView(), 0);
                    if (!SimpleDragShrinkHandler.this.canReturn()) {
                        Log.v(SimpleDragShrinkHandler.this.TAG, "returnShrinkView Failed.");
                        ViewUtils.removeSelf(imageView2);
                        SimpleDragShrinkHandler.this.mView.removeExtraShrinkViews();
                        return;
                    }
                    SimpleDragShrinkHandler.this.mView.mBlackboard.subscribeOnUi("lifecycle://on_fragment_enter_transition_end", new l(this, imageView2));
                    MediaItem mediaItem = SimpleDragShrinkHandler.this.mViewHolder.getMediaItem();
                    if (!(mediaItem == null || !mediaItem.isImage() || (bitmap = SimpleDragShrinkHandler.this.mView.mBitmap) == null)) {
                        SimpleDragShrinkHandler.this.mView.mBlackboard.publish("data://bitmap/viewer/" + mediaItem.getSimpleHashCode(), bitmap.copy(Bitmap.Config.ARGB_8888, false));
                    }
                    if (SimpleDragShrinkHandler.this.mViewHolder.isCheckBoxEnabled()) {
                        View decoView = SimpleDragShrinkHandler.this.mViewHolder.getDecoView(12);
                        if (decoView != null) {
                            SimpleDragShrinkHandler.this.mView.mBlackboard.publish("command:///ReleaseInputBlock", (Object) null);
                            decoView.callOnClick();
                        }
                    } else {
                        if (DeviceInfo.isAdvancedMouseCompat(BlackboardUtils.readActivity(SimpleDragShrinkHandler.this.mView.mBlackboard)) && ((Boolean) SimpleDragShrinkHandler.this.mView.mBlackboard.pop("data://motion_event_tool_type_mouse", Boolean.FALSE)).booleanValue()) {
                            SimpleDragShrinkHandler.this.mView.mBlackboard.publish("data://gesture_on_double_tapped", Boolean.TRUE);
                        }
                        SimpleDragShrinkHandler.this.mView.mBlackboard.publish("command:///ReleaseInputBlock", (Object) null);
                        SimpleDragShrinkHandler.this.mViewHolder.itemView.callOnClick();
                    }
                    SimpleDragShrinkHandler.this.mViewHolder.setShowDeco(true);
                } else if (simpleDragShrinkHandler.mIsQuickView) {
                    simpleDragShrinkHandler.mFinishListener.accept(Boolean.FALSE);
                    ThreadUtil.postOnUiThreadDelayed(new g(2, this, imageView2), (long) QuickViewTransitionAnimation.RETURN_DELAY);
                }
            }
        });
        duration.start();
    }

    public void setSystemUiWithAlpha() {
        setSystemUi(this.mView.getWindow(), true);
    }

    public void showInternal() {
        ImageView imageView = this.mView.mShrinkView;
        if (imageView != null) {
            this.mFromW = (float) imageView.getWidth();
            float height = (float) imageView.getHeight();
            this.mFromH = height;
            float f = this.mFromW;
            float f5 = this.TARGET_SHRINK_SCALE;
            this.mToW = f * f5;
            this.mToH = height * f5;
            this.mView.getDecorView().setOnDragListener(new i(this));
            imageView.startDragAndDrop(ClipData.newPlainText("", ""), getDragShadowBuilder(), imageView, 0);
        }
    }

    public void startShrinkAnimation() {
        this.mView.getDecorView().setOnDragListener((View.OnDragListener) null);
        super.startShrinkAnimation();
    }

    private void startSpringAnimation(Runnable runnable) {
        float f;
        float f5;
        ImageView imageView = this.mView.mShrinkView;
        if (imageView != null) {
            this.mTransXSpringAnim = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_X);
            if (this.mFromLeft) {
                f = imageView.getTranslationX() - this.mTargetPositionX;
            } else {
                f = imageView.getTranslationX() + this.mTargetPositionX;
            }
            startSpringAnimation(this.mTransXSpringAnim, f, (Runnable) null);
            this.mTransYSpringAnim = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_Y);
            if (this.mFromTop) {
                f5 = imageView.getTranslationY() - this.mTargetPositionY;
            } else {
                f5 = imageView.getTranslationY() + this.mTargetPositionY;
            }
            startSpringAnimation(this.mTransYSpringAnim, f5, runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void updateRotation(ImageView imageView, float f, float f5) {
    }
}
