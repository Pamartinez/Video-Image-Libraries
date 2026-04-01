package com.samsung.android.gallery.app.ui.list.search.pictures.headerview.animation;

import A5.a;
import A5.b;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.c;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.Optional;
import z5.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FaceClusterAnimationHelper {
    private ScaleAnimator mCurScaleAnimator;
    /* access modifiers changed from: private */
    public ImageView mFakeMainImage;
    /* access modifiers changed from: private */
    public ImageView mFakeTipCardImageView;
    /* access modifiers changed from: private */
    public AnimatorSet mRepeatAnimatorSet;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EnteringCompletedAnimationListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MergeCompletedAnimationListener {
    }

    private ImageView createFakeImage(Context context, View view, ImageView imageView) {
        ImageView imageView2 = new ImageView(context);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(imageView.getWidth(), imageView.getHeight()));
        RectF rectFOnScreen = getRectFOnScreen(view, imageView);
        ViewUtils.setViewShape(imageView2, 0, 0.0f);
        imageView2.setVisibility(0);
        imageView2.setImageDrawable(imageView.getDrawable());
        imageView2.setX(rectFOnScreen.left);
        imageView2.setY(rectFOnScreen.top);
        return imageView2;
    }

    private RectF getRectFOnScreen(View view, View view2) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationInWindow(iArr2);
        int i2 = iArr2[0] - iArr[0];
        int i7 = iArr2[1] - iArr[1];
        return new RectF((float) i2, (float) i7, (float) (view2.getWidth() + i2), (float) (view2.getHeight() + i7));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Animation lambda$startTipCardAlphaAnimation$0(Animation.AnimationListener animationListener, View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(150);
        alphaAnimation.setAnimationListener(animationListener);
        return alphaAnimation;
    }

    private Animator[] prepareMainImageAnimator(Context context, View view, View view2) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.search_prev_face_layout_end_margin);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.search_prev_face_icon_size);
        ImageView createFakeImage = createFakeImage(context, view, (ImageView) view2);
        this.mFakeMainImage = createFakeImage;
        ((ViewGroup) view).addView(createFakeImage);
        ScaleAnimator scaleAnimator = new ScaleAnimator((View) this.mFakeMainImage, 1.0f, ((float) dimensionPixelSize2) / ((float) view2.getWidth()));
        TranslationAnimator translationAnimator = new TranslationAnimator(this.mFakeMainImage);
        translationAnimator.translateXRelative(-(((float) (((ViewGroup) view2.getParent()).getWidth() + dimensionPixelSize)) / 2.0f));
        translationAnimator.translateYRelative(((float) (view2.getHeight() - dimensionPixelSize2)) / 2.0f);
        return new Animator[]{scaleAnimator, translationAnimator};
    }

    private Animator[] prepareTipCardThumbAnimator(Context context, View view, View view2, View view3) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.search_prev_face_layout_end_margin);
        ImageView createFakeImage = createFakeImage(context, view, (ImageView) view2);
        this.mFakeTipCardImageView = createFakeImage;
        ((ViewGroup) view).addView(createFakeImage);
        ScaleAnimator scaleAnimator = new ScaleAnimator((View) this.mFakeTipCardImageView, 1.0f, ((float) view3.getWidth()) / ((float) view2.getWidth()));
        RectF rectFOnScreen = getRectFOnScreen(view, view2);
        RectF rectFOnScreen2 = getRectFOnScreen(view, view3);
        TranslationAnimator translationAnimator = new TranslationAnimator(this.mFakeTipCardImageView);
        translationAnimator.translateXRelative((rectFOnScreen2.left - rectFOnScreen.left) + ((float) ((context.getResources().getDimensionPixelSize(R.dimen.search_prev_face_layout_size) / 2) - dimensionPixelSize)));
        translationAnimator.translateYRelative(rectFOnScreen2.centerY() - rectFOnScreen.centerY());
        return new Animator[]{scaleAnimator, translationAnimator};
    }

    /* access modifiers changed from: private */
    public void repeatReverseAnimation(final View view, final View view2) {
        if (this.mRepeatAnimatorSet != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{-10.0f, 0.0f});
            ofFloat.setRepeatCount(1);
            ofFloat.setRepeatMode(2);
            ScaleAnimator scaleAnimator = new ScaleAnimator(view, 0.85f, 1.0f);
            scaleAnimator.setRepeatCount(1);
            scaleAnimator.setRepeatMode(2);
            ScaleAnimator scaleAnimator2 = new ScaleAnimator(view2, 0.85f, 1.0f);
            this.mCurScaleAnimator = scaleAnimator2;
            scaleAnimator2.setRepeatCount(1);
            this.mCurScaleAnimator.setRepeatMode(2);
            this.mRepeatAnimatorSet.playTogether(new Animator[]{ofFloat, scaleAnimator, this.mCurScaleAnimator});
            this.mRepeatAnimatorSet.removeAllListeners();
            this.mRepeatAnimatorSet.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    FaceClusterAnimationHelper.this.mRepeatAnimatorSet.start();
                }

                public void onAnimationStart(Animator animator) {
                    if (view2.getTranslationZ() < 0.0f) {
                        ViewUtils.setTranslationZ(view2, 0.0f);
                        ViewUtils.setTranslationZ(view, -1.0f);
                        return;
                    }
                    ViewUtils.setTranslationZ(view2, -1.0f);
                    ViewUtils.setTranslationZ(view, 0.0f);
                }
            });
            this.mRepeatAnimatorSet.start();
        }
    }

    public void cancelRepeatAnimation(View view) {
        if (this.mRepeatAnimatorSet != null) {
            this.mCurScaleAnimator.cancel();
            ViewUtils.setScale(view, 1.0f, 1.0f);
            this.mRepeatAnimatorSet.removeAllListeners();
            this.mRepeatAnimatorSet.cancel();
            this.mRepeatAnimatorSet = null;
        }
    }

    public void removeFakeAnimationView(ViewGroup viewGroup) {
        Optional ofNullable = Optional.ofNullable(this.mFakeMainImage);
        Objects.requireNonNull(viewGroup);
        ofNullable.ifPresent(new b(viewGroup, 0));
        Optional.ofNullable(this.mFakeTipCardImageView).ifPresent(new b(viewGroup, 0));
    }

    public void showFaceClusterMergeViewAnimation(final View view, final View view2) {
        AlphaAnimator alphaAnimator = new AlphaAnimator(view, 0.0f, 1.0f);
        AlphaAnimator alphaAnimator2 = new AlphaAnimator(view2, 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{alphaAnimator, alphaAnimator2});
        animatorSet.setDuration(150);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.setStartDelay(200);
        animatorSet.addListener(new SimpleAnimatorListener() {
            public void onAnimationStart(Animator animator, boolean z) {
                view.setAlpha(0.0f);
                view2.setAlpha(0.0f);
                ViewUtils.setVisibility(view, 0);
                ViewUtils.setVisibility(view2, 0);
            }
        });
        animatorSet.start();
    }

    public void startEnterFaceClusterMergeAnimation(Context context, AnimationParam animationParam, EnteringCompletedAnimationListener enteringCompletedAnimationListener) {
        final ImageView[] mainImageView = animationParam.getMainImageView();
        final ImageView[] tipCardThumbView = animationParam.getTipCardThumbView();
        final View headerView = animationParam.getHeaderView();
        ImageView imageView = mainImageView[1];
        ViewUtils.setTranslationX((ViewGroup) imageView.getParent(), 0.0f);
        ViewUtils.setTranslationZ((ViewGroup) imageView.getParent(), 0.0f);
        ViewUtils.setScale((ViewGroup) imageView.getParent(), 1.0f, 1.0f);
        ViewUtils.setVisibility(imageView, 8);
        ViewUtils.setTranslationZ((ViewGroup) mainImageView[0].getParent(), -1.0f);
        Animator[] prepareTipCardThumbAnimator = prepareTipCardThumbAnimator(context, headerView, tipCardThumbView[0], tipCardThumbView[1]);
        Animator[] prepareMainImageAnimator = prepareMainImageAnimator(context, headerView, mainImageView[0]);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500).setInterpolator(PathInterpolator.create(0.3f, 0.0f, 0.1f, 1.0f));
        animatorSet.playTogether(new Animator[]{prepareTipCardThumbAnimator[0], prepareTipCardThumbAnimator[1], prepareMainImageAnimator[0], prepareMainImageAnimator[1]});
        final EnteringCompletedAnimationListener enteringCompletedAnimationListener2 = enteringCompletedAnimationListener;
        animatorSet.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator, boolean z) {
                tipCardThumbView[1].setImageDrawable(FaceClusterAnimationHelper.this.mFakeTipCardImageView.getDrawable());
                tipCardThumbView[1].setVisibility(0);
                ImageView imageView = mainImageView[1];
                imageView.setImageDrawable(FaceClusterAnimationHelper.this.mFakeMainImage.getDrawable());
                imageView.setVisibility(0);
                ((ViewGroup) imageView.getParent()).setVisibility(0);
                ViewUtils.setTranslationZ((ViewGroup) imageView.getParent(), 0.0f);
                FaceClusterAnimationHelper.this.removeFakeAnimationView((ViewGroup) headerView);
                ((h) enteringCompletedAnimationListener2).e.lambda$startEnterFaceClusterMergeAnimation$10();
            }

            public void onAnimationStart(Animator animator, boolean z) {
                tipCardThumbView[0].setVisibility(4);
                mainImageView[0].setVisibility(4);
            }
        });
        animatorSet.start();
    }

    public void startMergeAnimation(AnimationParam animationParam, final MergeCompletedAnimationListener mergeCompletedAnimationListener) {
        final ImageView imageView = animationParam.getAnimImagePair()[0];
        ImageView imageView2 = animationParam.getAnimImagePair()[1];
        ImageView[] flipImageView = animationParam.getFlipImageView();
        Bitmap mergedFaceBitmap = animationParam.getMergedFaceBitmap();
        final ViewGroup viewGroup = (ViewGroup) imageView.getParent();
        cancelRepeatAnimation(viewGroup);
        final ImageView imageView3 = flipImageView[0];
        final ImageView imageView4 = flipImageView[1];
        if (mergedFaceBitmap != null) {
            imageView4.setImageBitmap(mergedFaceBitmap);
        }
        final ViewGroup viewGroup2 = (ViewGroup) imageView2.getParent();
        ViewUtils.setTranslationX(viewGroup2, 0.0f);
        ViewUtils.setTranslationZ(viewGroup2, 0.0f);
        ViewUtils.setScale(viewGroup2, 1.0f, 1.0f);
        ViewUtils.setScale(viewGroup, 1.0f, 1.0f);
        ViewUtils.setTranslationZ(viewGroup, -1.0f);
        FlipAnimation flipAnimation = new FlipAnimation();
        flipAnimation.setStartDegree(270.0f);
        flipAnimation.setDuration(150);
        flipAnimation.setInterpolator(PathInterpolator.create(0.22f, 0.95f, 0.4f, 1.1f));
        flipAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ViewUtils.setVisibility(imageView4, 8);
                ViewUtils.setVisibility(imageView, 0);
                ((c) mergeCompletedAnimationListener).a();
            }
        });
        final FlipAnimation flipAnimation2 = flipAnimation;
        final FlipAnimation flipAnimation3 = new FlipAnimation();
        flipAnimation3.setDuration(250);
        flipAnimation3.setInterpolator(PathInterpolator.create(0.66f, 0.0f, 0.8f, 1.0f));
        flipAnimation3.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ViewUtils.setVisibility(imageView3, 8);
                ViewUtils.setVisibility(imageView4, 0);
                imageView4.startAnimation(flipAnimation2);
            }
        });
        TranslationAnimator translationAnimator = new TranslationAnimator(viewGroup2);
        int marginEnd = ((ViewGroup.MarginLayoutParams) viewGroup2.getLayoutParams()).getMarginEnd();
        translationAnimator.translateXRelative(((float) (viewGroup.getWidth() + marginEnd)) / 2.0f);
        translationAnimator.translateYRelative(-(((float) (viewGroup.getHeight() - viewGroup2.getHeight())) / 2.0f));
        TranslationAnimator translationAnimator2 = new TranslationAnimator(viewGroup);
        translationAnimator2.translateXRelative(-(((float) (viewGroup2.getWidth() + marginEnd)) / 2.0f));
        ScaleAnimator scaleAnimator = new ScaleAnimator((View) viewGroup2, 1.0f, ((float) viewGroup.getWidth()) / ((float) viewGroup2.getWidth()));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{translationAnimator, translationAnimator2, scaleAnimator});
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(PathInterpolator.create(0.1f, 0.0f, 0.38f, 1.0f));
        animatorSet.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                ViewUtils.setTranslationX(viewGroup, 0.0f);
                ViewUtils.setVisibility(imageView, 8);
                ViewUtils.setVisibility(viewGroup2, 8);
                ViewUtils.setVisibility(imageView3, 0);
                imageView3.startAnimation(flipAnimation3);
            }
        });
        animatorSet.start();
    }

    public void startRepeatAnimation(final View view, final View view2) {
        if (this.mRepeatAnimatorSet == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{0.0f, -10.0f});
            ScaleAnimator scaleAnimator = new ScaleAnimator(view, 1.0f, 0.85f);
            this.mCurScaleAnimator = new ScaleAnimator(view2, 1.0f, 0.85f);
            AnimatorSet animatorSet = new AnimatorSet();
            this.mRepeatAnimatorSet = animatorSet;
            animatorSet.playTogether(new Animator[]{ofFloat, scaleAnimator, this.mCurScaleAnimator});
            this.mRepeatAnimatorSet.setDuration(2000);
            this.mRepeatAnimatorSet.setInterpolator(PathInterpolator.create(0.53f, 0.0f, 0.24f, 1.0f));
            this.mRepeatAnimatorSet.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    FaceClusterAnimationHelper.this.repeatReverseAnimation(view, view2);
                }
            });
            this.mRepeatAnimatorSet.start();
        }
    }

    public void startTipCardAlphaAnimation(View view, Animation.AnimationListener animationListener) {
        ViewUtils.startAnimation(view, new a(0, animationListener));
    }
}
