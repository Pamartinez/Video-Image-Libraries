package com.samsung.android.gallery.plugins.panorama;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.o3dp.lib3dphotography.O3DPListener;
import com.samsung.o3dp.lib3dphotography.O3DPPanoramaView;
import i.C0212a;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PanoramaDelegate {
    private static final Interpolator QUAD_OUT_INTERPOLATOR = PathInterpolator.create(0.04f, 0.55f, 0.45f, 0.94f);
    /* access modifiers changed from: private */
    public O3DPPanoramaView m3DPanoramaView;
    Bitmap mBitmap;
    private ValueAnimator mIntroAnimator;
    private LottieAnimationView mLottieArrowLeft;
    private LottieAnimationView mLottieArrowRight;
    private ValueAnimator mOutroAnimator;

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$applyFlexModeTransition$1(View view) {
        ViewUtils.setVisibility(view, 0);
        AnimatorSet duration = new AnimatorSet().setDuration(200);
        duration.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60));
        duration.playTogether(new Animator[]{new AlphaAnimator(view, 0.0f, 1.0f), new ScaleAnimator(view, 0.6f, 1.0f)});
        duration.start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(MediaItem mediaItem, final BiConsumer biConsumer) {
        if (mediaItem.getOrientation() > 0) {
            this.mBitmap = BitmapUtils.rotateBitmap(this.mBitmap, mediaItem.getOrientation(), mediaItem.getOrientationTag());
        }
        this.m3DPanoramaView.setImageBitmap(this.mBitmap, new O3DPListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onFailed$0(BiConsumer biConsumer) {
                biConsumer.accept(PanoramaDelegate.this.m3DPanoramaView, Boolean.FALSE);
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onRenderStart$1(BiConsumer biConsumer) {
                biConsumer.accept(PanoramaDelegate.this.m3DPanoramaView, Boolean.TRUE);
            }

            public void onFailed(O3DPListener.ErrorType errorType, String str) {
                Log.e((CharSequence) "PanoramaDelegate", "onFailed", errorType, str);
                PanoramaDelegate.this.m3DPanoramaView.post(new f(this, biConsumer, 1));
            }

            public void onRenderStart() {
                Log.d("PanoramaDelegate", "onRenderStart");
                PanoramaDelegate.this.m3DPanoramaView.post(new f(this, biConsumer, 0));
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$introTransition$2(View view, float f, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        view.setAlpha(1.0f - normalizedFaction(floatValue, 0.0f, 0.1f));
        this.m3DPanoramaView.setPanoramaCurvature(normalizedFaction(floatValue, 0.1f, 1.0f));
        this.m3DPanoramaView.setCameraEyeZ((1.0f - floatValue) * f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$outroTransition$3(View view, int i2, int i7, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        view.getLayoutParams().height = (int) lerp((float) i2, (float) i7, floatValue);
        view.requestLayout();
    }

    private float lerp(float f, float f5, float f8) {
        return C0212a.a(f5, f, f8, f);
    }

    private float normalizedFaction(float f, float f5, float f8) {
        if (f <= f5) {
            return 0.0f;
        }
        if (f >= f8) {
            return 1.0f;
        }
        return (f - f5) / (f8 - f5);
    }

    /* access modifiers changed from: private */
    public void playLottieArrows() {
        this.mLottieArrowLeft.c();
        this.mLottieArrowRight.c();
    }

    public void applyFlexModeTransition(View view, int i2) {
        ViewUtils.setVisibility(view, 4);
        ThreadUtil.postOnUiThreadDelayed(new d(view), (long) i2);
    }

    public void bindView(ViewGroup viewGroup, MediaItem mediaItem, Bitmap bitmap, BiConsumer<View, Boolean> biConsumer) {
        this.m3DPanoramaView = (O3DPPanoramaView) viewGroup.findViewById(R$id.photo_3d_view);
        this.mLottieArrowLeft = (LottieAnimationView) viewGroup.findViewById(R$id.arrow_left);
        this.mLottieArrowRight = (LottieAnimationView) viewGroup.findViewById(R$id.arrow_right);
        this.mBitmap = bitmap;
        Log.d("PanoramaDelegate", "bindView", Long.valueOf(mediaItem.getFileId()), bitmap);
        SimpleThreadPool.getInstance().execute(new c(this, mediaItem, biConsumer));
    }

    public void introTransition(final View view) {
        if (this.m3DPanoramaView == null) {
            Log.w("PanoramaDelegate", "introTransition : 3DPanoramaView is null");
            return;
        }
        ValueAnimator valueAnimator = this.mIntroAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            float width = ((float) this.mBitmap.getWidth()) / ((float) this.m3DPanoramaView.getBitmapWidth());
            final float abs = Math.abs(1.0f - (((float) this.m3DPanoramaView.getHeight()) / ((float) Math.round(((float) view.getLayoutParams().height) * width)))) * 2.5f;
            if (this.mIntroAnimator == null) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                this.mIntroAnimator = ofFloat;
                ofFloat.setInterpolator(QUAD_OUT_INTERPOLATOR);
                this.mIntroAnimator.setDuration(600);
                this.mIntroAnimator.addUpdateListener(new e(this, abs, view));
                this.mIntroAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        PanoramaDelegate.this.m3DPanoramaView.setRenderMode(0);
                        PanoramaDelegate.this.m3DPanoramaView.resumePanoramaNavitation();
                        PanoramaDelegate.this.m3DPanoramaView.setPanoramaCurvature(1.0f);
                        PanoramaDelegate.this.m3DPanoramaView.setCameraEyeZ(0.0f);
                        view.setAlpha(1.0f);
                        view.setVisibility(8);
                        PanoramaDelegate.this.playLottieArrows();
                    }

                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        PanoramaDelegate.this.m3DPanoramaView.setRenderMode(1);
                        PanoramaDelegate.this.m3DPanoramaView.pausePanoramaNavigation();
                        PanoramaDelegate.this.m3DPanoramaView.setPanoramaCurvature(0.0f);
                        PanoramaDelegate.this.m3DPanoramaView.setCameraEyeZ(abs);
                        PanoramaDelegate.this.m3DPanoramaView.requestRender();
                        PanoramaDelegate.this.m3DPanoramaView.setVisibility(0);
                    }
                });
            }
            this.mIntroAnimator.start();
        }
    }

    public void outroTransition(final View view, final Consumer<Boolean> consumer) {
        if (this.m3DPanoramaView == null) {
            Log.w("PanoramaDelegate", "outroTransition : 3DPanoramaView is null");
            return;
        }
        ValueAnimator valueAnimator = this.mOutroAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            ValueAnimator valueAnimator2 = this.mIntroAnimator;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.mIntroAnimator.cancel();
            }
            int height = this.m3DPanoramaView.getHeight();
            int i2 = view.getLayoutParams().height;
            if (this.mOutroAnimator == null) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                this.mOutroAnimator = ofFloat;
                ofFloat.setInterpolator(QUAD_OUT_INTERPOLATOR);
                this.mOutroAnimator.setDuration(380);
                this.mOutroAnimator.addUpdateListener(new b(this, view, height, i2));
                this.mOutroAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        consumer.accept(Boolean.TRUE);
                    }

                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        PanoramaDelegate.this.m3DPanoramaView.pauseAnimation();
                        PanoramaDelegate.this.m3DPanoramaView.setVisibility(8);
                        view.setVisibility(0);
                    }
                });
            }
            this.mOutroAnimator.start();
        }
    }

    public void pauseAnim() {
        String str;
        if (this.m3DPanoramaView == null) {
            str = " skip";
        } else {
            str = "";
        }
        Log.d("PanoramaDelegate", "pauseAnim".concat(str));
        O3DPPanoramaView o3DPPanoramaView = this.m3DPanoramaView;
        if (o3DPPanoramaView != null) {
            o3DPPanoramaView.pauseAnimation();
        }
    }

    public void resumeAnim() {
        String str;
        if (this.m3DPanoramaView == null) {
            str = " skip";
        } else {
            str = "";
        }
        Log.d("PanoramaDelegate", "resumeAnim".concat(str));
        O3DPPanoramaView o3DPPanoramaView = this.m3DPanoramaView;
        if (o3DPPanoramaView != null) {
            o3DPPanoramaView.resumeAnimation();
        }
    }

    public void showToast(Context context, int i2) {
        showToast(context, context.getString(i2));
    }

    public void updateViewMargin(ViewGroup viewGroup, boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewGroup.findViewById(R$id.photo_3d_container).getLayoutParams();
        if (z) {
            layoutParams.bottomMargin = ViewUtils.getHeight(viewGroup) / 2;
        } else {
            layoutParams.bottomMargin = 0;
        }
    }

    private void showToast(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }
}
