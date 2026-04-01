package com.samsung.android.gallery.widget.animations;

import A.a;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.widget.ImageView;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.Color2Animator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class SimpleShrinkHandler {
    protected final String TAG = getClass().getSimpleName();
    float mAlphaWeight;
    protected AnimatorSet mAnimatorSet;
    private boolean mAppbarVisible;
    int mBackgroundColor;
    protected float mBackgroundColorThreshold = 0.3f;
    protected int mDuration = 120;
    protected Consumer<Boolean> mFinishListener;
    protected boolean mIsQuickView = false;
    private boolean mLightTheme;
    private int mPosition;
    protected boolean mPostAnim;
    protected int mStartDelay = 0;
    final AtomicInteger mState = new AtomicInteger(0);
    private final AtomicBoolean mStatusBarThemeConfig = new AtomicBoolean(false);
    private final RectF mTargetRect;
    private IThemeColor mThemeColor;
    protected TimeInterpolator mTimeInterpolator;
    private Runnable mUpdateListener;
    protected final SimpleShrinkView mView;
    final ListViewHolder mViewHolder;
    protected boolean mWithRadius;

    public SimpleShrinkHandler(SimpleShrinkView simpleShrinkView, ListViewHolder listViewHolder) {
        ImageView imageView;
        this.mView = simpleShrinkView;
        this.mViewHolder = listViewHolder;
        this.mBackgroundColor = simpleShrinkView.mActivity.getColor(R$color.daynight_default_background);
        if (listViewHolder != null) {
            imageView = listViewHolder.getImage();
        } else {
            imageView = null;
        }
        this.mTargetRect = getTargetRect(imageView);
        this.mTimeInterpolator = PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_80);
    }

    private RectF getRectConsiderRotation(int i2, Rect rect) {
        int[] iArr;
        int displayRotation = DeviceInfo.getDisplayRotation(this.mView.mActivity);
        if (i2 == displayRotation) {
            return new RectF((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
        }
        Size displaySize = DeviceInfo.getDisplaySize(this.mView.mActivity);
        if (i2 == 0) {
            iArr = getRectRotation0(displayRotation, displaySize.getWidth(), displaySize.getHeight(), rect);
        } else if (i2 == 1) {
            iArr = getRectRotation90(displayRotation, displaySize.getWidth(), displaySize.getHeight(), rect);
        } else if (i2 == 3) {
            iArr = getRectRotation270(displayRotation, displaySize.getWidth(), displaySize.getHeight(), rect);
        } else {
            iArr = null;
        }
        if (iArr == null) {
            iArr = new int[]{rect.left, rect.top};
        }
        int i7 = iArr[0];
        return new RectF((float) i7, (float) iArr[1], (float) (rect.width() + i7), (float) (rect.height() + iArr[1]));
    }

    private int[] getRectRotation0(int i2, int i7, int i8, Rect rect) {
        if (i2 == 1) {
            return new int[]{rect.top, i8 - rect.right};
        }
        if (i2 == 3) {
            return new int[]{i7 - rect.bottom, rect.left};
        }
        return null;
    }

    private int[] getRectRotation270(int i2, int i7, int i8, Rect rect) {
        if (i2 == 0) {
            return new int[]{rect.top, i8 - rect.right};
        }
        if (i2 == 1) {
            return new int[]{i7 - rect.right, i8 - rect.bottom};
        }
        return null;
    }

    private int[] getRectRotation90(int i2, int i7, int i8, Rect rect) {
        if (i2 == 0) {
            return new int[]{i7 - rect.bottom, i8 - (i8 - rect.left)};
        }
        if (i2 == 3) {
            return new int[]{i7 - rect.right, i8 - rect.bottom};
        }
        return null;
    }

    private RectF getTargetRect(View view) {
        if (view != null) {
            return ViewUtils.getViewRect(view);
        }
        return null;
    }

    private boolean isViewHolderRecycled() {
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder == null || !listViewHolder.isRecycled(this.mPosition)) {
            return false;
        }
        String str = this.TAG;
        Log.w(str, "ViewHolderRecycled {" + this.mPosition + "} " + this.mViewHolder);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createBackgroundColorAnimator$1(ValueAnimator valueAnimator) {
        Runnable runnable = this.mUpdateListener;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$0(long j2) {
        if (this.mState.get() == 0) {
            a.x(new StringBuilder("show > shrink since ready +"), j2, this.TAG);
            onShrinkError();
        }
    }

    /* access modifiers changed from: private */
    public void onShrinkStart() {
        this.mView.mBlackboard.postEvent(EventMessage.obtain(3016));
    }

    private void setColorAndTheme() {
        boolean z;
        if (Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            z = AppResources.getBoolean(R$bool.is_light_status_bar);
        } else if (!AppResources.getBoolean(R$bool.isNightTheme)) {
            z = true;
        } else {
            z = false;
        }
        this.mLightTheme = z;
        String str = this.TAG;
        Log.st(str, "setColorAndTheme {theme=" + this.mLightTheme + ",appbar=" + this.mAppbarVisible + "}");
    }

    private void setStatusBarTheme(Window window, boolean z) {
        if (this.mStatusBarThemeConfig.getAndSet(z) != z) {
            SystemUi.setStatusBarTheme(window, z);
        }
    }

    public void cancel() {
        Log.st(this.TAG, Contract.COMMAND_ID_CANCEL);
        this.mView.cancel();
        cancelAnimation();
    }

    public void cancelAnimation() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mAnimatorSet.cancel();
        }
    }

    public void clearShrinkData(boolean z) {
        this.mView.clearShrinkData();
        if (z) {
            this.mView.clearBitmapReference();
        }
    }

    public ArrayList<Animator> createAnimatorSet(View view, View view2) {
        RectF animationFromRect = getAnimationFromRect(view);
        return getAnimatorList(view, animationFromRect, getAnimationToRect(view2, animationFromRect));
    }

    public PropertyAnimator createBackgroundColorAnimator() {
        Color2Animator lightTheme = new Color2Animator(this.mView.mShrinkBackgroundView, this.mBackgroundColor, 0).addView(this.mView.mTopView).setThreshold(this.mBackgroundColorThreshold).setLightTheme(this.mLightTheme);
        lightTheme.addUpdateListener(new n(1, this));
        return lightTheme;
    }

    public PropertyAnimator createScaleAnimator(View view, RectF rectF, RectF rectF2) {
        return new ScaleAnimator(view, rectF, rectF2).pivotX(((float) view.getWidth()) / 2.0f).pivotY(((float) view.getHeight()) / 2.0f).enableUpdateLayoutParam(true);
    }

    public PropertyAnimator createTranslationAnimator(View view, RectF rectF, RectF rectF2) {
        return new TranslationAnimator(view, rectF, rectF2);
    }

    public RectF getAnimationFromRect(View view) {
        return ViewUtils.getViewRect(view);
    }

    public RectF getAnimationToRect(View view, RectF rectF) {
        RectF rectF2;
        if (isViewHolderRecycled()) {
            rectF2 = this.mTargetRect;
        } else {
            rectF2 = getTargetRect(view);
        }
        if (rectF2 == null) {
            return makeDummyRect(rectF);
        }
        return rectF2;
    }

    public ArrayList<Animator> getAnimatorList(View view, RectF rectF, RectF rectF2) {
        ArrayList<Animator> arrayList = new ArrayList<>();
        arrayList.add(createScaleAnimator(view, rectF, rectF2));
        arrayList.add(createBackgroundColorAnimator());
        arrayList.add(createTranslationAnimator(view, rectF, rectF2));
        return arrayList;
    }

    public abstract float getCornerRadius();

    public RectF getDummyRectForQuickView(RectF rectF) {
        DisplayMetrics displayMetrics = ResourceCompat.getDisplayMetrics(this.mView.mActivity);
        float f = (float) (displayMetrics.widthPixels / 2);
        float f5 = (float) (displayMetrics.heightPixels / 2);
        return new RectF(f, f5, f, f5);
    }

    public boolean isBackToCamera() {
        return false;
    }

    public RectF makeDummyRect(RectF rectF) {
        float f = ((rectF.left + rectF.right) * 0.5f) - 100.0f;
        float f5 = ((rectF.top + rectF.bottom) * 0.5f) - 100.0f;
        return new RectF(f, f5, f + 200.0f, 200.0f + f5);
    }

    public RectF makeDummyRectForQuickView(RectF rectF) {
        LaunchIntent launchIntent = (LaunchIntent) this.mView.mBlackboard.read("data://launch_intent");
        if (launchIntent != null) {
            int quickViewThumbnailRotation = launchIntent.getQuickViewThumbnailRotation();
            Rect quickViewThumbnailRect = launchIntent.getQuickViewThumbnailRect();
            if (quickViewThumbnailRect != null) {
                return getRectConsiderRotation(quickViewThumbnailRotation, quickViewThumbnailRect);
            }
        }
        return getDummyRectForQuickView(rectF);
    }

    public void onShrinkError() {
        Log.st(this.TAG, "onShrinkError");
    }

    public void onShrinkFinish() {
        Log.st(this.TAG, "onShrinkFinish");
        this.mView.recycle();
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder != null) {
            ViewUtils.setVisibility(listViewHolder.getScalableView(), 0);
            this.mViewHolder.setShowDeco(true);
        }
        this.mView.mBlackboard.postEvent(EventMessage.obtain(3017));
        clearShrinkData(false);
        Consumer<Boolean> consumer = this.mFinishListener;
        if (consumer != null) {
            consumer.accept(Boolean.TRUE);
        }
    }

    public final SimpleShrinkHandler setAlphaWeight(float f) {
        this.mAlphaWeight = f;
        return this;
    }

    public final SimpleShrinkHandler setAppbarVisible(boolean z) {
        this.mAppbarVisible = z;
        return this;
    }

    public void setOutlineProvider() {
        ImageView imageView = this.mView.mShrinkView;
        if (imageView != null) {
            imageView.setClipToOutline(true);
            imageView.setOutlineProvider(new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), SimpleShrinkHandler.this.getCornerRadius());
                }
            });
        }
    }

    public final SimpleShrinkHandler setPosition(int i2) {
        this.mPosition = i2;
        return this;
    }

    public final SimpleShrinkHandler setPostAnim(boolean z) {
        this.mPostAnim = z;
        return this;
    }

    public final SimpleShrinkHandler setStartDelay(int i2) {
        this.mStartDelay = i2;
        return this;
    }

    public final void setSystemUi(Window window, boolean z) {
        if (z) {
            setStatusBarTheme(window, this.mLightTheme);
        } else {
            setStatusBarTheme(window, false);
        }
    }

    public final SimpleShrinkHandler setThemeColor(IThemeColor iThemeColor) {
        this.mThemeColor = iThemeColor;
        return this;
    }

    public final SimpleShrinkHandler setWithRadius(boolean z) {
        this.mWithRadius = z;
        return this;
    }

    public final void show() {
        long j2;
        setColorAndTheme();
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder != null) {
            ViewUtils.setVisibility(listViewHolder.getScalableView(), 4);
            this.mViewHolder.setShowDeco(false);
        }
        showInternal();
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mState.get() == 0) {
            o oVar = new o(this, currentTimeMillis);
            if (SystemUi.isInMultiWindowMode(this.mView.mActivity)) {
                j2 = 180;
            } else {
                j2 = 100;
            }
            ThreadUtil.postOnUiThreadDelayed(oVar, j2);
        }
    }

    public abstract void showInternal();

    public void startShrinkAnimation() {
        ImageView imageView;
        if (!this.mIsQuickView) {
            setSystemUi(this.mView.getWindow(), false);
        }
        SimpleShrinkView simpleShrinkView = this.mView;
        if (simpleShrinkView.mShrinkView == null) {
            onShrinkFinish();
            return;
        }
        ViewUtils.setVisibleOrGone(simpleShrinkView.mTopView, false);
        ViewUtils.setVisibleOrGone(this.mView.mShrinkBackgroundView, false);
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder != null) {
            imageView = listViewHolder.getImage();
        } else {
            imageView = null;
        }
        ArrayList arrayList = new ArrayList(createAnimatorSet(this.mView.mShrinkView, imageView));
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        animatorSet.playTogether(arrayList);
        this.mAnimatorSet.setInterpolator(this.mTimeInterpolator);
        this.mAnimatorSet.setDuration((long) this.mDuration);
        this.mAnimatorSet.addListener(new SimpleAnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                Log.stv(SimpleShrinkHandler.this.TAG, "onAnimationCancel");
                SimpleShrinkHandler.this.onShrinkFinish();
            }

            public void onAnimationEnd(Animator animator) {
                Log.stv(SimpleShrinkHandler.this.TAG, "onAnimationEnd");
                SimpleShrinkHandler.this.onShrinkFinish();
            }

            public void onAnimationStart(Animator animator) {
                Log.stv(SimpleShrinkHandler.this.TAG, "onAnimationStart");
                SimpleShrinkHandler.this.onShrinkStart();
            }
        });
        int i2 = this.mStartDelay;
        if (i2 > 0) {
            this.mAnimatorSet.setStartDelay((long) i2);
        }
        this.mAnimatorSet.start();
    }

    public final SimpleShrinkHandler withFinishAction(Consumer<Boolean> consumer) {
        this.mFinishListener = consumer;
        return this;
    }

    public final SimpleShrinkHandler withUpdateAction(Runnable runnable) {
        this.mUpdateListener = runnable;
        return this;
    }
}
