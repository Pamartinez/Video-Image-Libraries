package com.samsung.android.gallery.widget.animator;

import A.a;
import H7.B;
import a6.C0418a;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$anim;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SimpleAnimator {
    private static final Animation.AnimationListener EMPTY_LISTENER = new SimpleAnimationListener();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ValueAnimatorApi {
        static final Method methodOverrideDurationScale = Reflector.getMethod(ValueAnimator.class, "overrideDurationScale", Float.TYPE);
    }

    public static void addListenerForHideAnimation(View view, Animation animation) {
        addListenerForHideAnimation(view, animation, (SimpleAnimationListener) null);
    }

    public static void alphaIn(View view, int i2) {
        if (view != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R$anim.alpha_in);
            loadAnimation.setDuration((long) i2);
            view.startAnimation(loadAnimation);
        }
    }

    public static void alphaOut(View view, int i2) {
        alphaOut(view, i2, 8);
    }

    public static void create(View view, int i2, Animation.AnimationListener animationListener) {
        if (view != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), i2);
            if (animationListener == null) {
                animationListener = EMPTY_LISTENER;
            }
            loadAnimation.setAnimationListener(animationListener);
            view.startAnimation(loadAnimation);
        }
    }

    public static void fadeIn(View view, int i2) {
        if (view != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R$anim.fade_in_x);
            loadAnimation.setDuration((long) i2);
            view.startAnimation(loadAnimation);
        }
    }

    public static void fadeOut(View view, int i2) {
        if (view != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R$anim.fadeout);
            loadAnimation.setDuration((long) i2);
            addListenerForHideAnimation(view, loadAnimation);
            view.startAnimation(loadAnimation);
        }
    }

    public static ValueAnimator ignoreDurationScaleIfDisabled(ValueAnimator valueAnimator) {
        try {
            ValueAnimatorApi.methodOverrideDurationScale.invoke(valueAnimator, new Object[]{Float.valueOf(1.0f)});
            return valueAnimator;
        } catch (Exception e) {
            a.s(e, new StringBuilder("ignoreDurationScaleIfDisabled failed. e="), "SimpleAnimator");
            return valueAnimator;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setVisibility$1(View view, int i2, Runnable runnable) {
        view.setVisibility(i2);
        view.setAlpha(1.0f);
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setVisibility$3(View[] viewArr, int i2, Runnable runnable) {
        setVisibility(viewArr, i2);
        if (runnable != null) {
            runnable.run();
        }
    }

    public static void setAlphaOutVisible(View view, int i2) {
        setAlphaOutVisible(view, i2, 8);
    }

    public static void setAlphaVisible(View view, int i2) {
        if (view != null && view.getVisibility() != 0) {
            view.setVisibility(0);
            if (view.isShown()) {
                alphaIn(view, i2);
            }
        }
    }

    public static void setVisibility(View view, int i2, int i7) {
        if (view != null) {
            ThreadUtil.runOnUiThread(view, new B(view, i2, i7, 4));
        }
    }

    public static void setVisibilityAni(View view, int i2) {
        if (view == null) {
            return;
        }
        if (i2 == 0 && view.getVisibility() != i2) {
            view.setVisibility(0);
            if (view.isShown()) {
                view.clearAnimation();
                fadeIn(view, 150);
            }
        } else if (!view.isShown() || i2 == 0 || view.getVisibility() != 0) {
            view.clearAnimation();
            view.setVisibility(i2);
        } else {
            view.clearAnimation();
            fadeOut(view, 150);
        }
    }

    public static void slideUp(View view, Animation.AnimationListener animationListener) {
        if (view != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R$anim.oneui50_viewer2_slide_up);
            loadAnimation.setInterpolator(PathInterpolator.create(0.5f, 0.0f, 0.0f, 1.0f));
            if (animationListener == null) {
                animationListener = EMPTY_LISTENER;
            }
            loadAnimation.setAnimationListener(animationListener);
            view.startAnimation(loadAnimation);
        }
    }

    public static void addListenerForHideAnimation(final View view, Animation animation, final SimpleAnimationListener simpleAnimationListener) {
        final AnonymousClass2 r0 = new View.OnAttachStateChangeListener() {
            public void onViewDetachedFromWindow(View view) {
                view.setVisibility(8);
            }

            public void onViewAttachedToWindow(View view) {
            }
        };
        animation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                SimpleAnimationListener simpleAnimationListener = SimpleAnimationListener.this;
                if (simpleAnimationListener != null) {
                    simpleAnimationListener.onAnimationEnd(animation);
                }
                view.setVisibility(8);
                view.removeOnAttachStateChangeListener(r0);
            }
        });
        view.addOnAttachStateChangeListener(r0);
    }

    public static void alphaOut(final View view, int i2, final int i7) {
        if (view != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R$anim.alpha_out);
            loadAnimation.setAnimationListener(new SimpleAnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(i7);
                }
            });
            loadAnimation.setDuration((long) i2);
            view.startAnimation(loadAnimation);
        }
    }

    public static void setAlphaOutVisible(View view, int i2, int i7) {
        if (view != null && view.getVisibility() != i7) {
            alphaOut(view, i2, i7);
        }
    }

    public static void setVisibility(View view, int i2, int i7, Interpolator interpolator, Runnable runnable) {
        if (view == null) {
            return;
        }
        if (i7 <= 0) {
            view.setVisibility(i2);
        } else if (i2 != 0) {
            new AlphaAnimator(view, 1.0f, 0.0f).setDuration(i7).setInterpolator(interpolator).withEndAction(new C0418a((Object) view, i2, (Object) runnable, 9)).start();
        } else if (view.getVisibility() != 0) {
            view.setVisibility(i2);
            new AlphaAnimator(view, 0.0f, 1.0f).setDuration(i7).setInterpolator(interpolator).withEndAction(runnable).start();
        }
    }

    public static void setAlphaVisible(View view) {
        setAlphaVisible(view, 150);
    }

    public static void setVisibility(View view, int i2, int i7, Runnable runnable) {
        setVisibility(view, i2, i7, (Interpolator) null, runnable);
    }

    public static void setVisibility(View[] viewArr, int i2, int i7) {
        if (ThreadUtil.isMainThread()) {
            setVisibility(viewArr, i2, i7, (Runnable) null);
        } else if (viewArr != null && viewArr.length > 0) {
            viewArr[0].post(new B(viewArr, i2, i7, 3));
        }
    }

    public static void setVisibility(View[] viewArr, int i2, int i7, Runnable runnable) {
        View view;
        if (viewArr != null && viewArr.length > 0 && (view = viewArr[0]) != null) {
            if (i7 <= 0) {
                setVisibility(viewArr, i2);
            } else if (i2 != 0) {
                new AlphaAnimator(viewArr, 1.0f, 0.0f).withEndAction(new C0418a((Object) viewArr, i2, (Object) runnable, 10)).start();
            } else if (view.getVisibility() != 0) {
                setVisibility(viewArr, i2);
                new AlphaAnimator(viewArr, 0.0f, 1.0f).setDuration(i7).withEndAction(runnable).start();
            }
        }
    }

    public static void setVisibility(View[] viewArr, int i2) {
        if (viewArr != null) {
            for (View view : viewArr) {
                if (view != null) {
                    view.setVisibility(i2);
                }
            }
        }
    }
}
