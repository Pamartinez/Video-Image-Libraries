package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.R$animator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class FragmentAnim {
    private static int getNextAnim(Fragment fragment, boolean z, boolean z3) {
        if (z3) {
            if (z) {
                return fragment.getPopEnterAnim();
            }
            return fragment.getPopExitAnim();
        } else if (z) {
            return fragment.getEnterAnim();
        } else {
            return fragment.getExitAnim();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0069 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0069 A[SYNTHETIC, Splitter:B:31:0x0069] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.fragment.app.FragmentAnim.AnimationOrAnimator loadAnimation(android.content.Context r5, androidx.fragment.app.Fragment r6, boolean r7, boolean r8) {
        /*
            int r0 = r6.getNextTransition()
            int r8 = getNextAnim(r6, r7, r8)
            r1 = 0
            r6.setAnimations(r1, r1, r1, r1)
            android.view.ViewGroup r2 = r6.mContainer
            r3 = 0
            if (r2 == 0) goto L_0x001e
            int r4 = androidx.fragment.R$id.visible_removing_fragment_view_tag
            java.lang.Object r2 = r2.getTag(r4)
            if (r2 == 0) goto L_0x001e
            android.view.ViewGroup r2 = r6.mContainer
            r2.setTag(r4, r3)
        L_0x001e:
            android.view.ViewGroup r2 = r6.mContainer
            if (r2 == 0) goto L_0x0029
            android.animation.LayoutTransition r2 = r2.getLayoutTransition()
            if (r2 == 0) goto L_0x0029
            return r3
        L_0x0029:
            android.view.animation.Animation r2 = r6.onCreateAnimation(r0, r7, r8)
            if (r2 == 0) goto L_0x0035
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r5 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator
            r5.<init>((android.view.animation.Animation) r2)
            return r5
        L_0x0035:
            android.animation.Animator r2 = r6.onCreateAnimator((int) r0, (boolean) r7, (int) r8)
            if (r2 == 0) goto L_0x0041
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r5 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator
            r5.<init>((android.animation.Animator) r2)
            return r5
        L_0x0041:
            if (r8 != 0) goto L_0x0049
            if (r0 == 0) goto L_0x0049
            int r8 = transitToAnimResourceId(r5, r0, r7)
        L_0x0049:
            if (r8 == 0) goto L_0x00ac
            android.content.res.Resources r7 = r5.getResources()
            java.lang.String r7 = r7.getResourceTypeName(r8)
            java.lang.String r0 = "anim"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L_0x0069
            android.view.animation.Animation r0 = android.view.animation.AnimationUtils.loadAnimation(r5, r8)     // Catch:{ NotFoundException -> 0x0067, RuntimeException -> 0x0069 }
            if (r0 == 0) goto L_0x00ac
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r2 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator     // Catch:{ NotFoundException -> 0x0067, RuntimeException -> 0x0069 }
            r2.<init>((android.view.animation.Animation) r0)     // Catch:{ NotFoundException -> 0x0067, RuntimeException -> 0x0069 }
            return r2
        L_0x0067:
            r5 = move-exception
            throw r5
        L_0x0069:
            androidx.fragment.app.SeslFragmentTransactionAnimationSet$Companion r0 = androidx.fragment.app.SeslFragmentTransactionAnimationSet.Companion     // Catch:{ RuntimeException -> 0x0085 }
            boolean r0 = r0.isFragmentAnimationRes(r8)     // Catch:{ RuntimeException -> 0x0085 }
            if (r0 == 0) goto L_0x0091
            android.animation.Animator r0 = r6.onCreateAnimator((int) r8, (boolean) r1, (boolean) r1)     // Catch:{ RuntimeException -> 0x0085 }
            int r2 = androidx.fragment.R$animator.sesl_fragment_close_enter     // Catch:{ RuntimeException -> 0x0085 }
            r4 = 1
            if (r8 == r2) goto L_0x0087
            int r2 = androidx.fragment.R$animator.sesl_fragment_close_exit     // Catch:{ RuntimeException -> 0x0085 }
            if (r8 != r2) goto L_0x007f
            goto L_0x0087
        L_0x007f:
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r6 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator     // Catch:{ RuntimeException -> 0x0085 }
            r6.<init>((android.animation.Animator) r0, (boolean) r4)     // Catch:{ RuntimeException -> 0x0085 }
            return r6
        L_0x0085:
            r6 = move-exception
            goto L_0x009d
        L_0x0087:
            android.animation.Animator r6 = r6.onCreateAnimator((int) r8, (boolean) r4, (boolean) r1)     // Catch:{ RuntimeException -> 0x0085 }
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r1 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator     // Catch:{ RuntimeException -> 0x0085 }
            r1.<init>((android.animation.Animator) r0, (android.animation.Animator) r6)     // Catch:{ RuntimeException -> 0x0085 }
            return r1
        L_0x0091:
            android.animation.Animator r6 = android.animation.AnimatorInflater.loadAnimator(r5, r8)     // Catch:{ RuntimeException -> 0x0085 }
            if (r6 == 0) goto L_0x00ac
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r0 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator     // Catch:{ RuntimeException -> 0x0085 }
            r0.<init>((android.animation.Animator) r6)     // Catch:{ RuntimeException -> 0x0085 }
            return r0
        L_0x009d:
            if (r7 != 0) goto L_0x00ab
            android.view.animation.Animation r5 = android.view.animation.AnimationUtils.loadAnimation(r5, r8)
            if (r5 == 0) goto L_0x00ac
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r6 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator
            r6.<init>((android.view.animation.Animation) r5)
            return r6
        L_0x00ab:
            throw r6
        L_0x00ac:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentAnim.loadAnimation(android.content.Context, androidx.fragment.app.Fragment, boolean, boolean):androidx.fragment.app.FragmentAnim$AnimationOrAnimator");
    }

    private static int toActivityTransitResId(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(16973825, new int[]{i2});
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private static int transitToAnimResourceId(Context context, int i2, boolean z) {
        if (i2 != 4097) {
            if (i2 != 8194) {
                if (i2 != 8197) {
                    if (i2 != 4099) {
                        if (i2 != 4100) {
                            return -1;
                        }
                        if (z) {
                            return toActivityTransitResId(context, 16842936);
                        }
                        return toActivityTransitResId(context, 16842937);
                    } else if (z) {
                        return R$animator.fragment_fade_enter;
                    } else {
                        return R$animator.fragment_fade_exit;
                    }
                } else if (z) {
                    return toActivityTransitResId(context, 16842938);
                } else {
                    return toActivityTransitResId(context, 16842939);
                }
            } else if (z) {
                return R$animator.fragment_close_enter;
            } else {
                return R$animator.fragment_close_exit;
            }
        } else if (z) {
            return R$animator.fragment_open_enter;
        } else {
            return R$animator.fragment_open_exit;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AnimationOrAnimator {
        public final Animation animation;
        public final AnimatorSet animator;
        public final AnimatorSet animatorForCommit;
        public final boolean isFragmentAnimationRes;

        public AnimationOrAnimator(Animation animation2) {
            this.animation = animation2;
            this.animator = null;
            if (animation2 != null) {
                this.animatorForCommit = null;
                this.isFragmentAnimationRes = false;
                return;
            }
            throw new IllegalStateException("Animation cannot be null");
        }

        public AnimationOrAnimator(Animator animator2) {
            this.animation = null;
            AnimatorSet animatorSet = new AnimatorSet();
            this.animator = animatorSet;
            animatorSet.play(animator2);
            if (animator2 != null) {
                this.animatorForCommit = null;
                this.isFragmentAnimationRes = false;
                return;
            }
            throw new IllegalStateException("Animator cannot be null");
        }

        public AnimationOrAnimator(Animator animator2, boolean z) {
            this.animation = null;
            AnimatorSet animatorSet = new AnimatorSet();
            this.animator = animatorSet;
            animatorSet.play(animator2);
            if (animator2 != null) {
                this.animatorForCommit = null;
                this.isFragmentAnimationRes = z;
                return;
            }
            throw new IllegalStateException("Animator cannot be null");
        }

        public AnimationOrAnimator(Animator animator2, Animator animator3) {
            this.animation = null;
            AnimatorSet animatorSet = new AnimatorSet();
            this.animator = animatorSet;
            animatorSet.play(animator2);
            if (animator2 != null) {
                AnimatorSet animatorSet2 = new AnimatorSet();
                this.animatorForCommit = animatorSet2;
                animatorSet2.play(animator3);
                if (animator3 != null) {
                    this.isFragmentAnimationRes = true;
                    return;
                }
                throw new IllegalStateException("animatorForCommit cannot be null");
            }
            throw new IllegalStateException("Animator cannot be null");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EndViewTransitionAnimation extends AnimationSet implements Runnable {
        private boolean mAnimating = true;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        public EndViewTransitionAnimation(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.mParent = viewGroup;
            this.mChild = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        public boolean getTransformation(long j2, Transformation transformation) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j2, transformation)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public void run() {
            if (this.mEnded || !this.mAnimating) {
                this.mParent.endViewTransition(this.mChild);
                this.mTransitionEnded = true;
                return;
            }
            this.mAnimating = false;
            this.mParent.post(this);
        }

        public boolean getTransformation(long j2, Transformation transformation, float f) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j2, transformation, f)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }
    }
}
