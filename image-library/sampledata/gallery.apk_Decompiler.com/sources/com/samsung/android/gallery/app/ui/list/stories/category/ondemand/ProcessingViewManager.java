package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import A.a;
import Kb.b;
import U5.c;
import a6.g;
import a6.l;
import a6.m;
import a6.n;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Size;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemViewHolderFactory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animations.photostacking.ImageInfo;
import com.samsung.android.gallery.widget.animations.photostacking.PhotoStackingAnimView;
import com.samsung.android.gallery.widget.animations.photostacking.ThrowingValue;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProcessingViewManager {
    private static final boolean SUPPORT_BLURRED_BG;
    private static final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final PhotoStackingAnimView mAnimView;
    /* access modifiers changed from: private */
    public Runnable mFailRunnable;
    private MediaItem mGeneratedItem;
    /* access modifiers changed from: private */
    public String mKey = null;
    private Runnable mLaunchRunnable;
    private final OnDemandLoader mLoader = new OnDemandLoader();
    private Dialog mProgressDialog;
    private final IOnDemandFloatingView mView;
    private ListViewHolder mViewHolder;

    static {
        boolean z;
        if (!Features.isEnabled(Features.SUPPORT_REALTIME_BLUR) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY)) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT_BLURRED_BG = z;
    }

    public ProcessingViewManager(IOnDemandFloatingView iOnDemandFloatingView) {
        this.mView = iOnDemandFloatingView;
        PhotoStackingAnimView photoStackingAnimView = new PhotoStackingAnimView(iOnDemandFloatingView.getContext());
        this.mAnimView = photoStackingAnimView;
        photoStackingAnimView.setOnEndListener(new g(1, this));
    }

    /* access modifiers changed from: private */
    public void cancelAnimation() {
        Log.d("OnDemandProcessingManager", "cancel animation", this.mKey);
        this.mKey = null;
        mHandler.removeCallbacksAndMessages((Object) null);
        if (this.mAnimView.isRunning()) {
            this.mAnimView.cancel();
        } else {
            this.mAnimView.releaseView();
        }
        this.mView.setClearViewMode(false);
        hideGlowView();
        hideDialog();
    }

    /* access modifiers changed from: private */
    public void copyBlurredBackgroundView(View view, Runnable runnable) {
        view.post(new c(26, this, runnable));
    }

    /* access modifiers changed from: private */
    public View getBlurBgLayout() {
        if (!SUPPORT_BLURRED_BG || this.mView.getView() == null) {
            return null;
        }
        return this.mView.getView().findViewById(R.id.blur_background_layout);
    }

    private View getBlurTargetView() {
        if (!SUPPORT_BLURRED_BG || this.mView.getView() == null) {
            return null;
        }
        return this.mView.getView().findViewById(R.id.blur_effect_target_view);
    }

    /* access modifiers changed from: private */
    public ImageView getFakeBlurBgView() {
        if (!SUPPORT_BLURRED_BG || this.mView.getView() == null) {
            return null;
        }
        return (ImageView) this.mView.getView().findViewById(R.id.fake_blur_background_image);
    }

    private View getGlowBgView() {
        if (!SUPPORT_BLURRED_BG || this.mView.getView() == null) {
            return null;
        }
        return this.mView.getView().findViewById(R.id.background_glow_image);
    }

    private ValueAnimator getGlowViewAlphaInAnimator(SimpleAnimatorListener simpleAnimatorListener) {
        View glowBgView = getGlowBgView();
        View blurTargetView = getBlurTargetView();
        if (glowBgView == null || blurTargetView == null) {
            return null;
        }
        glowBgView.setVisibility(0);
        ValueAnimator duration = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(500);
        duration.addListener(simpleAnimatorListener);
        duration.addUpdateListener(new b(2, glowBgView, blurTargetView));
        return duration;
    }

    /* access modifiers changed from: private */
    public boolean hasFakeBlurBitmap() {
        ImageView fakeBlurBgView = getFakeBlurBgView();
        if (fakeBlurBgView == null || fakeBlurBgView.getDrawable() == null) {
            return false;
        }
        return true;
    }

    private void hideDialog() {
        if (this.mProgressDialog != null) {
            Log.d("OnDemandProcessingManager", "hide inputBlockDialog");
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    private void hideGlowView() {
        if (this.mView.getView() != null && SUPPORT_BLURRED_BG) {
            ViewUtils.setVisibility(getBlurBgLayout(), 0);
            ViewUtils.setVisibility(getGlowBgView(), 8);
            ThreadUtil.postOnUiThread(new m(this, 0));
        }
    }

    private void inflateGlowView() {
        View glowBgView = getGlowBgView();
        if (glowBgView instanceof ViewStub) {
            ((ViewStub) glowBgView).inflate();
            updateGlowViewMargin();
        }
    }

    private void initStoryViewHolder(MediaItem mediaItem, Bitmap bitmap) {
        if (this.mViewHolder == null) {
            ListViewHolder createItemViewHolder = StoriesCatItemViewHolderFactory.createItemViewHolder(this.mAnimView, 0, "location://stories/category/creation");
            this.mViewHolder = createItemViewHolder;
            ViewUtils.setVisibleOrInvisible(createItemViewHolder.itemView, false);
        }
        this.mViewHolder.recycle();
        ViewUtils.removeSelf(this.mViewHolder.itemView);
        ViewUtils.addView(this.mAnimView, this.mViewHolder.itemView);
        Size imageViewSize = ThrowingValue.getImageViewSize(this.mView.getContext());
        ViewUtils.setViewSize(this.mViewHolder.itemView, Integer.valueOf(imageViewSize.getWidth()), Integer.valueOf(imageViewSize.getHeight()));
        this.mViewHolder.bind(mediaItem);
        this.mViewHolder.bindImage(bitmap);
        this.mViewHolder.getImage().setTransitionName((String) null);
        this.mViewHolder.getTitleView().setTransitionName((String) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$copyBlurredBackgroundView$4(long j2, ImageView imageView, Bitmap bitmap, Runnable runnable, int i2) {
        boolean z;
        StringBuilder sb2 = new StringBuilder("copyBlurBackground ");
        if (i2 == 0) {
            z = true;
        } else {
            z = false;
        }
        a.A(new Object[]{Boolean.valueOf(z), Integer.valueOf(i2), Long.valueOf(j2)}, sb2, "OnDemandProcessingManager");
        if (i2 != 0) {
            bitmap = null;
        }
        imageView.setImageBitmap(bitmap);
        runnable.run();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$copyBlurredBackgroundView$5(Runnable runnable) {
        Runnable runnable2;
        View blurBgLayout = getBlurBgLayout();
        ImageView fakeBlurBgView = getFakeBlurBgView();
        if (blurBgLayout == null || fakeBlurBgView == null) {
            runnable.run();
            return;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Rect rect = new Rect();
            blurBgLayout.getGlobalVisibleRect(rect);
            Bitmap createBitmap = Bitmap.createBitmap(blurBgLayout.getWidth(), blurBgLayout.getHeight(), Bitmap.Config.ARGB_8888);
            runnable2 = runnable;
            try {
                PixelCopy.request(this.mView.getActivity().getWindow(), rect, createBitmap, new l(currentTimeMillis, fakeBlurBgView, createBitmap, runnable2), new Handler(Looper.getMainLooper()));
            } catch (Exception e) {
                e = e;
                Exception exc = e;
                Log.e("OnDemandProcessingManager", "failed copy blur background " + exc.getMessage());
                runnable2.run();
            }
        } catch (Exception e7) {
            e = e7;
            runnable2 = runnable;
            Exception exc2 = e;
            Log.e("OnDemandProcessingManager", "failed copy blur background " + exc2.getMessage());
            runnable2.run();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getGlowViewAlphaInAnimator$3(View view, View view2, ValueAnimator valueAnimator) {
        ViewUtils.setAlpha(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        view2.invalidate();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideGlowView$2() {
        ViewUtils.setVisibility(getFakeBlurBgView(), 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSuccess$6(Bitmap bitmap, MediaItem mediaItem, ImageView imageView) {
        this.mAnimView.requestStop(true, new ImageInfo(bitmap, mediaItem, imageView.getImageMatrix()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$0(String str, ArrayList arrayList, String str2) {
        if (!TextUtils.equals(str, this.mKey)) {
            Log.e((CharSequence) "OnDemandProcessingManager", "request is canceled. skip start animation", str);
        } else if (arrayList == null || arrayList.isEmpty()) {
            Log.e("OnDemandProcessingManager", "fail start animation. null data");
        } else {
            startAnimation(str, str2, arrayList);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$1(String str, String str2, ArrayList arrayList) {
        mHandler.post(new A6.a((Object) this, (Object) str, (Object) arrayList, (Object) str2, 29));
    }

    private void launch() {
        prepareSharedTransition();
        hideDialog();
        Runnable runnable = this.mLaunchRunnable;
        if (runnable != null) {
            runnable.run();
            this.mLaunchRunnable = null;
        }
    }

    /* access modifiers changed from: private */
    public void onAnimationFinished(boolean z) {
        Log.i("OnDemandProcessingManager", "onAnimationFinished " + z);
        if (z) {
            launch();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mView.getContext(), R.anim.fadeout);
        loadAnimation.setDuration(500);
        loadAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ProcessingViewManager.this.cancelAnimation();
                if (ProcessingViewManager.this.mFailRunnable != null) {
                    ProcessingViewManager.this.mFailRunnable.run();
                    ProcessingViewManager.this.mFailRunnable = null;
                }
            }
        });
        this.mAnimView.startAnimation(loadAnimation);
    }

    private void prepareSharedTransition() {
        if (this.mView.isViewResumed()) {
            ImageView transitionImage = this.mAnimView.getTransitionImage();
            TextView transitionText = this.mAnimView.getTransitionText();
            if (transitionImage != null && transitionText != null && this.mGeneratedItem != null) {
                StorySharedTransitionHelper.addStoryAlbumTransition(this.mView.getBlackboard(), transitionImage, transitionText, this.mGeneratedItem);
            }
        }
    }

    private void showDialog() {
        if (this.mProgressDialog == null && this.mView.getContext() != null) {
            Log.d("OnDemandProcessingManager", "show inputBlockDialog");
        }
    }

    private void startAnimation(String str, final String str2, final ArrayList<ImageInfo> arrayList) {
        final View view = this.mView.getView();
        if (view == null) {
            Log.e("OnDemandProcessingManager", "start animation failed. null view.");
            return;
        }
        Log.d("OnDemandProcessingManager", "start animation", str, Integer.valueOf(arrayList.size()));
        showDialog();
        inflateGlowView();
        this.mView.setClearViewMode(true);
        ViewUtils.addView((ViewGroup) view, this.mAnimView);
        this.mAnimView.updateLayout(this.mView.isInMultiWindowMode());
        if (SUPPORT_BLURRED_BG) {
            ValueAnimator glowViewAlphaInAnimator = getGlowViewAlphaInAnimator(new SimpleAnimatorListener() {
                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onAnimationEnd$0(String str, ArrayList arrayList) {
                    if (ProcessingViewManager.this.mKey != null) {
                        if (ProcessingViewManager.this.hasFakeBlurBitmap()) {
                            ViewUtils.setVisibility(ProcessingViewManager.this.getFakeBlurBgView(), 0);
                            ViewUtils.setVisibility(ProcessingViewManager.this.getBlurBgLayout(), 8);
                        }
                        ProcessingViewManager.this.mAnimView.start(str, arrayList);
                        return;
                    }
                    ProcessingViewManager.this.cancelAnimation();
                }

                public void onAnimationEnd(Animator animator) {
                    ProcessingViewManager.this.copyBlurredBackgroundView(view, new a(this, str2, arrayList, 1));
                }
            });
            if (glowViewAlphaInAnimator != null) {
                glowViewAlphaInAnimator.start();
            } else {
                this.mAnimView.start(str2, arrayList);
            }
        } else {
            this.mAnimView.start(str2, arrayList);
        }
    }

    private void updateGlowViewMargin() {
        if (this.mView.getView() != null) {
            ViewMarginUtils.setBottomMargin(getGlowBgView(), this.mView.getResources().getDimensionPixelSize(R.dimen.ondemand_story_vi_textview_margin_top));
        }
    }

    public void hide() {
        cancelAnimation();
    }

    public boolean isRunning() {
        if (this.mAnimView.isRunning() || this.mKey != null) {
            return true;
        }
        return false;
    }

    public void onConfigurationChanged() {
        if (this.mAnimView.isRunning()) {
            this.mAnimView.updateLayout(this.mView.isInMultiWindowMode());
            updateGlowViewMargin();
        }
    }

    public void onFail(Runnable runnable) {
        this.mFailRunnable = runnable;
        if (this.mAnimView.isRunning()) {
            this.mAnimView.requestStop(false, (ImageInfo) null);
            return;
        }
        if (this.mKey != null) {
            this.mFailRunnable.run();
            this.mFailRunnable = null;
        }
        cancelAnimation();
    }

    public void onPause() {
        if (this.mAnimView.isRunning()) {
            this.mAnimView.onPause();
        }
    }

    public void onResume() {
        if (this.mAnimView.isRunning()) {
            this.mAnimView.onResume();
        }
    }

    public void onSuccess(MediaItem mediaItem, Bitmap bitmap, Runnable runnable) {
        boolean z;
        ImageView imageView;
        if (mediaItem == null || bitmap == null) {
            Bitmap bitmap2 = bitmap;
            boolean z3 = false;
            if (mediaItem != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (bitmap2 != null) {
                z3 = true;
            }
            Log.d("OnDemandProcessingManager", "handle onSuccess failed", valueOf, Boolean.valueOf(z3));
            cancelAnimation();
            hideDialog();
            return;
        }
        this.mLaunchRunnable = runnable;
        this.mGeneratedItem = mediaItem;
        initStoryViewHolder(mediaItem, bitmap);
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder != null) {
            imageView = listViewHolder.getImage();
        } else {
            imageView = null;
        }
        ImageView imageView2 = imageView;
        if (imageView2 != null) {
            imageView2.postDelayed(new A6.a((Object) this, (Object) bitmap, (Object) mediaItem, (Object) imageView2, 28), 500);
        }
    }

    public void release() {
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder != null) {
            listViewHolder.recycle();
            ViewUtils.removeSelf(this.mViewHolder.itemView);
            this.mViewHolder = null;
        }
        this.mAnimView.releaseView();
        hideGlowView();
        this.mKey = null;
        this.mAnimView.setOnEndListener((PhotoStackingAnimView.onAnimationEndListener) null);
        this.mGeneratedItem = null;
        this.mFailRunnable = null;
        this.mLaunchRunnable = null;
    }

    public void show(String str, String str2) {
        if (isRunning()) {
            Log.e("OnDemandProcessingManager", "skip showing " + this.mKey);
            return;
        }
        this.mKey = str;
        this.mLoader.loadImageInfo(str, new n(this, str, str2));
    }
}
