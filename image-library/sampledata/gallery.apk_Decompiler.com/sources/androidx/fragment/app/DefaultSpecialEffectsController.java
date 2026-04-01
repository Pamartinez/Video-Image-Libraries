package androidx.fragment.app;

import Ae.a;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.BackEventCompat;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.R$animator;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.u;
import me.i;
import ne.C1194l;
import ne.C1196n;
import ne.C1200r;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u000f\b\u0001\u0018\u00002\u00020\u0001:\b%&'()*+,B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u000e\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0006H\u0003¢\u0006\u0004\b\u000e\u0010\u000bJ9\u0010\u0015\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00062\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J-\u0010\u001c\u001a\u00020\t*\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ+\u0010!\u001a\u00020\t2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u001e2\u0006\u0010 \u001a\u00020\u0019H\u0002¢\u0006\u0004\b!\u0010\"J%\u0010#\u001a\u00020\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b#\u0010$¨\u0006-"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController;", "Landroidx/fragment/app/SpecialEffectsController;", "Landroid/view/ViewGroup;", "container", "<init>", "(Landroid/view/ViewGroup;)V", "", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "operations", "Lme/x;", "syncAnimations", "(Ljava/util/List;)V", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "animationInfos", "collectAnimEffects", "Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "transitionInfos", "", "isPop", "firstOut", "lastIn", "createTransitionEffect", "(Ljava/util/List;ZLandroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/fragment/app/SpecialEffectsController$Operation;)V", "Landroidx/collection/ArrayMap;", "", "Landroid/view/View;", "", "names", "retainMatchingViews", "(Landroidx/collection/ArrayMap;Ljava/util/Collection;)V", "", "namedViews", "view", "findNamedViews", "(Ljava/util/Map;Landroid/view/View;)V", "collectEffects", "(Ljava/util/List;Z)V", "AnimationEffect", "AnimationInfo", "AnimatorEffect", "Api24Impl", "Api26Impl", "SpecialEffectsInfo", "TransitionEffect", "TransitionInfo", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultSpecialEffectsController extends SpecialEffectsController {

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationEffect;", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "animationInfo", "<init>", "(Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;)V", "Landroid/view/ViewGroup;", "container", "Lme/x;", "onCommit", "(Landroid/view/ViewGroup;)V", "onCancel", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "getAnimationInfo", "()Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnimationEffect extends SpecialEffectsController.Effect {
        private final AnimationInfo animationInfo;

        public AnimationEffect(AnimationInfo animationInfo2) {
            j.e(animationInfo2, "animationInfo");
            this.animationInfo = animationInfo2;
        }

        public final AnimationInfo getAnimationInfo() {
            return this.animationInfo;
        }

        public void onCancel(ViewGroup viewGroup) {
            j.e(viewGroup, "container");
            SpecialEffectsController.Operation operation = this.animationInfo.getOperation();
            View view = operation.getFragment().mView;
            view.clearAnimation();
            viewGroup.endViewTransition(view);
            this.animationInfo.getOperation().completeEffect(this);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Animation from operation " + operation + " has been cancelled.");
            }
        }

        public void onCommit(ViewGroup viewGroup) {
            j.e(viewGroup, "container");
            if (this.animationInfo.isVisibilityUnchanged()) {
                this.animationInfo.getOperation().completeEffect(this);
                return;
            }
            Context context = viewGroup.getContext();
            SpecialEffectsController.Operation operation = this.animationInfo.getOperation();
            View view = operation.getFragment().mView;
            AnimationInfo animationInfo2 = this.animationInfo;
            j.d(context, "context");
            FragmentAnim.AnimationOrAnimator animation = animationInfo2.getAnimation(context);
            if (animation != null) {
                Animation animation2 = animation.animation;
                if (animation2 == null) {
                    throw new IllegalStateException("Required value was null.");
                } else if (operation.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view.startAnimation(animation2);
                    this.animationInfo.getOperation().completeEffect(this);
                } else {
                    viewGroup.startViewTransition(view);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, viewGroup, view);
                    endViewTransitionAnimation.setAnimationListener(new DefaultSpecialEffectsController$AnimationEffect$onCommit$1(operation, viewGroup, view, this));
                    view.startAnimation(endViewTransitionAnimation);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Animation from operation " + operation + " has started.");
                    }
                }
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\u0004\u001a\u00020\u0005R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "isPop", "", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Z)V", "animation", "Landroidx/fragment/app/FragmentAnim$AnimationOrAnimator;", "isAnimLoaded", "getAnimation", "context", "Landroid/content/Context;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator animation;
        private boolean isAnimLoaded;
        private final boolean isPop;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AnimationInfo(SpecialEffectsController.Operation operation, boolean z) {
            super(operation);
            j.e(operation, "operation");
            this.isPop = z;
        }

        public final FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            boolean z;
            j.e(context, "context");
            if (this.isAnimLoaded) {
                return this.animation;
            }
            Fragment fragment = getOperation().getFragment();
            if (getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                z = true;
            } else {
                z = false;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, fragment, z, this.isPop);
            this.animation = loadAnimation;
            this.isAnimLoaded = true;
            return loadAnimation;
        }

        public final boolean isPop() {
            return this.isPop;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\nJ\u0017\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0010\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u0014\u0010\u001f\u001a\u00020\u001e8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimatorEffect;", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "animatorInfo", "<init>", "(Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;)V", "Landroid/view/ViewGroup;", "container", "Lme/x;", "onStart", "(Landroid/view/ViewGroup;)V", "Landroidx/activity/BackEventCompat;", "backEvent", "onProgress", "(Landroidx/activity/BackEventCompat;Landroid/view/ViewGroup;)V", "onCommit", "onCancel", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "getAnimatorInfo", "()Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "Landroid/animation/AnimatorSet;", "animator", "Landroid/animation/AnimatorSet;", "getAnimator", "()Landroid/animation/AnimatorSet;", "setAnimator", "(Landroid/animation/AnimatorSet;)V", "animatorForCommit", "getAnimatorForCommit", "setAnimatorForCommit", "", "isSeekingSupported", "()Z", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnimatorEffect extends SpecialEffectsController.Effect {
        private AnimatorSet animator;
        private AnimatorSet animatorForCommit;
        private final AnimationInfo animatorInfo;

        public AnimatorEffect(AnimationInfo animationInfo) {
            j.e(animationInfo, "animatorInfo");
            this.animatorInfo = animationInfo;
        }

        public final AnimationInfo getAnimatorInfo() {
            return this.animatorInfo;
        }

        public boolean isSeekingSupported() {
            return true;
        }

        public void onCancel(ViewGroup viewGroup) {
            String str;
            j.e(viewGroup, "container");
            AnimatorSet animatorSet = this.animator;
            if (animatorSet == null) {
                this.animatorInfo.getOperation().completeEffect(this);
                return;
            }
            SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            if (operation.isSeeking()) {
                Api26Impl.INSTANCE.reverse(animatorSet);
            } else {
                animatorSet.end();
                AnimatorSet animatorSet2 = this.animatorForCommit;
                if (animatorSet2 != null) {
                    animatorSet2.end();
                }
            }
            AnimationInfo animationInfo = this.animatorInfo;
            Context context = viewGroup.getContext();
            j.d(context, "container.context");
            FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
            if (animation != null && animation.isFragmentAnimationRes) {
                View view = operation.getFragment().mView;
                if (view != null && operation.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                    operation.getFinalState().applyState(view, viewGroup);
                }
                if (this.animatorInfo.isPop() && operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                    operation.getFragment().initTransition();
                }
            }
            operation.getFragment().seslGetOnTransitionCallback();
            if (FragmentManager.isLoggingEnabled(2)) {
                StringBuilder sb2 = new StringBuilder("Animator from operation ");
                sb2.append(operation);
                sb2.append(" has been canceled");
                if (operation.isSeeking()) {
                    str = " with seeking.";
                } else {
                    str = ".";
                }
                sb2.append(str);
                sb2.append(' ');
                Log.v("FragmentManager", sb2.toString());
            }
        }

        public void onCommit(ViewGroup viewGroup) {
            boolean z;
            AnimatorSet animatorSet;
            j.e(viewGroup, "container");
            SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            AnimatorSet animatorSet2 = this.animator;
            if (animatorSet2 == null) {
                this.animatorInfo.getOperation().completeEffect(this);
                return;
            }
            Fragment fragment = operation.getFragment();
            View view = fragment.mView;
            boolean z3 = false;
            if (animatorSet2.getCurrentPlayTime() != 0) {
                z = true;
            } else {
                z = false;
            }
            AnimationInfo animationInfo = this.animatorInfo;
            Context context = view.getContext();
            j.d(context, "viewToAnimate.context");
            FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
            if (animation != null && animation.isFragmentAnimationRes && this.animatorInfo.isPop()) {
                SpecialEffectsController.Operation.State finalState = operation.getFinalState();
                if (operation.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                    z3 = true;
                }
                if (!z) {
                    AnimationInfo animationInfo2 = this.animatorInfo;
                    Context context2 = view.getContext();
                    j.d(context2, "viewToAnimate.context");
                    FragmentAnim.AnimationOrAnimator animation2 = animationInfo2.getAnimation(context2);
                    if (animation2 != null) {
                        animatorSet = animation2.animatorForCommit;
                    } else {
                        animatorSet = null;
                    }
                } else if (finalState == SpecialEffectsController.Operation.State.REMOVED) {
                    animatorSet = (AnimatorSet) fragment.onCreateAnimator(R$animator.sesl_fragment_close_exit, true, true);
                } else {
                    animatorSet = (AnimatorSet) fragment.onCreateAnimator(R$animator.sesl_fragment_close_enter, true, true);
                }
                AnimatorSet animatorSet3 = animatorSet;
                this.animatorForCommit = animatorSet3;
                if (animatorSet3 != null) {
                    animatorSet3.addListener(new DefaultSpecialEffectsController$AnimatorEffect$onCommit$1$1(viewGroup, view, z3, operation, this));
                    animatorSet2.removeAllListeners();
                    animatorSet2.cancel();
                    animatorSet3.setTarget(view);
                    animatorSet3.start();
                    return;
                }
            }
            animatorSet2.start();
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Animator from operation " + operation + " has started.");
            }
        }

        public void onProgress(BackEventCompat backEventCompat, ViewGroup viewGroup) {
            j.e(backEventCompat, "backEvent");
            j.e(viewGroup, "container");
            SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            AnimatorSet animatorSet = this.animator;
            if (animatorSet == null) {
                this.animatorInfo.getOperation().completeEffect(this);
            } else if (Build.VERSION.SDK_INT >= 34 && operation.getFragment().seslIsPredictiveBackEnabled() && operation.getFragment().mTransitioning) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Adding BackProgressCallbacks for Animators to operation " + operation);
                }
                long j2 = Api24Impl.INSTANCE.totalDuration(animatorSet);
                View view = operation.getFragment().mView;
                float progress = backEventCompat.getProgress();
                AnimationInfo animationInfo = this.animatorInfo;
                Context context = view.getContext();
                j.d(context, "viewToAnimate.context");
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation != null && animation.isFragmentAnimationRes) {
                    progress = operation.getFragment().getProgress(backEventCompat.getProgress());
                }
                operation.getFragment().seslGetOnTransitionCallback();
                long j3 = (long) (progress * ((float) j2));
                if (j3 == 0) {
                    j3 = 1;
                }
                if (j3 == j2) {
                    j3 = j2 - 1;
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Setting currentPlayTime to " + j3 + " for Animator " + animatorSet + " on operation " + operation);
                }
                Api26Impl.INSTANCE.setCurrentPlayTime(animatorSet, j3);
            }
        }

        public void onStart(ViewGroup viewGroup) {
            AnimatorSet animatorSet;
            boolean z;
            boolean z3;
            AnimatorEffect animatorEffect;
            j.e(viewGroup, "container");
            if (!this.animatorInfo.isVisibilityUnchanged()) {
                Context context = viewGroup.getContext();
                AnimationInfo animationInfo = this.animatorInfo;
                j.d(context, "context");
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation != null) {
                    animatorSet = animation.animator;
                } else {
                    animatorSet = null;
                }
                this.animator = animatorSet;
                SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
                Fragment fragment = operation.getFragment();
                if (operation.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                    z3 = true;
                    z = true;
                } else {
                    z = false;
                    z3 = true;
                }
                View view = fragment.mView;
                AnimationInfo animationInfo2 = this.animatorInfo;
                Context context2 = view.getContext();
                j.d(context2, "viewToAnimate.context");
                FragmentAnim.AnimationOrAnimator animation2 = animationInfo2.getAnimation(context2);
                if (animation2 != null && animation2.isFragmentAnimationRes == z3 && operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                    view.setAlpha(1.0f);
                }
                fragment.seslGetOnTransitionCallback();
                viewGroup.startViewTransition(view);
                AnimatorSet animatorSet2 = this.animator;
                if (animatorSet2 != null) {
                    animatorEffect = this;
                    animatorSet2.addListener(new DefaultSpecialEffectsController$AnimatorEffect$onStart$2(viewGroup, view, z, operation, animatorEffect));
                } else {
                    animatorEffect = this;
                }
                AnimatorSet animatorSet3 = animatorEffect.animator;
                if (animatorSet3 != null) {
                    animatorSet3.setTarget(view);
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$Api24Impl;", "", "()V", "totalDuration", "", "animatorSet", "Landroid/animation/AnimatorSet;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api24Impl {
        public static final Api24Impl INSTANCE = new Api24Impl();

        private Api24Impl() {
        }

        public final long totalDuration(AnimatorSet animatorSet) {
            j.e(animatorSet, "animatorSet");
            return animatorSet.getTotalDuration();
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$Api26Impl;", "", "<init>", "()V", "Landroid/animation/AnimatorSet;", "animatorSet", "Lme/x;", "reverse", "(Landroid/animation/AnimatorSet;)V", "", "time", "setCurrentPlayTime", "(Landroid/animation/AnimatorSet;J)V", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api26Impl {
        public static final Api26Impl INSTANCE = new Api26Impl();

        private Api26Impl() {
        }

        public final void reverse(AnimatorSet animatorSet) {
            j.e(animatorSet, "animatorSet");
            animatorSet.reverse();
        }

        public final void setCurrentPlayTime(AnimatorSet animatorSet, long j2) {
            j.e(animatorSet, "animatorSet");
            animatorSet.setCurrentPlayTime(j2);
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "(Landroidx/fragment/app/SpecialEffectsController$Operation;)V", "isVisibilityUnchanged", "", "()Z", "getOperation", "()Landroidx/fragment/app/SpecialEffectsController$Operation;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation operation;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation2) {
            j.e(operation2, "operation");
            this.operation = operation2;
        }

        public final SpecialEffectsController.Operation getOperation() {
            return this.operation;
        }

        public final boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State state;
            View view = this.operation.getFragment().mView;
            if (view != null) {
                state = SpecialEffectsController.Operation.State.Companion.asOperationState(view);
            } else {
                state = null;
            }
            SpecialEffectsController.Operation.State finalState = this.operation.getFinalState();
            if (state == finalState) {
                return true;
            }
            SpecialEffectsController.Operation.State state2 = SpecialEffectsController.Operation.State.VISIBLE;
            if (state == state2 || finalState == state2) {
                return false;
            }
            return true;
        }
    }

    @Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0002\u0018\u00002\u00020\u0001Bß\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e\u0012\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e\u0012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00120\fj\b\u0012\u0004\u0012\u00020\u0012`\u000e\u0012\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00120\fj\b\u0012\u0004\u0012\u00020\u0012`\u000e\u0012\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010#\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b%\u0010 J\u0017\u0010&\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b&\u0010 JG\u0010(\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e\u0012\u0004\u0012\u00020\n0'2\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b(\u0010)J=\u0010-\u001a\u00020\u001e2\u0016\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e2\u0006\u0010\u001d\u001a\u00020\u001c2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001e0+H\u0002¢\u0006\u0004\b-\u0010.J/\u00101\u001a\u00020\u001e2\u0016\u0010/\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e2\u0006\u00100\u001a\u00020\rH\u0002¢\u0006\u0004\b1\u00102R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u00103\u001a\u0004\b4\u00105R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u00106\u001a\u0004\b7\u00108R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u00106\u001a\u0004\b9\u00108R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010:\u001a\u0004\b;\u0010<R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010=\u001a\u0004\b>\u0010?R'\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010@\u001a\u0004\bA\u0010BR'\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010@\u001a\u0004\bC\u0010BR#\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00118\u0006¢\u0006\f\n\u0004\b\u0013\u0010D\u001a\u0004\bE\u0010FR'\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00120\fj\b\u0012\u0004\u0012\u00020\u0012`\u000e8\u0006¢\u0006\f\n\u0004\b\u0014\u0010@\u001a\u0004\bG\u0010BR'\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00120\fj\b\u0012\u0004\u0012\u00020\u0012`\u000e8\u0006¢\u0006\f\n\u0004\b\u0015\u0010@\u001a\u0004\bH\u0010BR#\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u00118\u0006¢\u0006\f\n\u0004\b\u0016\u0010D\u001a\u0004\bI\u0010FR#\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u00118\u0006¢\u0006\f\n\u0004\b\u0017\u0010D\u001a\u0004\bJ\u0010FR\u0017\u0010\u0019\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010K\u001a\u0004\b\u0019\u0010LR\u001d\u0010N\u001a\u00020M8\u0006¢\u0006\u0012\n\u0004\bN\u0010O\u0012\u0004\bR\u0010S\u001a\u0004\bP\u0010QR$\u0010T\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bT\u0010=\u001a\u0004\bU\u0010?\"\u0004\bV\u0010WR\u0014\u0010X\u001a\u00020\u00188VX\u0004¢\u0006\u0006\u001a\u0004\bX\u0010LR\u0011\u0010Z\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\bY\u0010L¨\u0006["}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionEffect;", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "", "Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "transitionInfos", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "firstOut", "lastIn", "Landroidx/fragment/app/FragmentTransitionImpl;", "transitionImpl", "", "sharedElementTransition", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "sharedElementFirstOutViews", "sharedElementLastInViews", "Landroidx/collection/ArrayMap;", "", "sharedElementNameMapping", "enteringNames", "exitingNames", "firstOutViews", "lastInViews", "", "isPop", "<init>", "(Ljava/util/List;Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/fragment/app/FragmentTransitionImpl;Ljava/lang/Object;Ljava/util/ArrayList;Ljava/util/ArrayList;Landroidx/collection/ArrayMap;Ljava/util/ArrayList;Ljava/util/ArrayList;Landroidx/collection/ArrayMap;Landroidx/collection/ArrayMap;Z)V", "Landroid/view/ViewGroup;", "container", "Lme/x;", "onStart", "(Landroid/view/ViewGroup;)V", "Landroidx/activity/BackEventCompat;", "backEvent", "onProgress", "(Landroidx/activity/BackEventCompat;Landroid/view/ViewGroup;)V", "onCommit", "onCancel", "Lme/i;", "createMergedTransition", "(Landroid/view/ViewGroup;Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/fragment/app/SpecialEffectsController$Operation;)Lme/i;", "enteringViews", "Lkotlin/Function0;", "executeTransition", "runTransition", "(Ljava/util/ArrayList;Landroid/view/ViewGroup;LAe/a;)V", "transitioningViews", "view", "captureTransitioningViews", "(Ljava/util/ArrayList;Landroid/view/View;)V", "Ljava/util/List;", "getTransitionInfos", "()Ljava/util/List;", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "getFirstOut", "()Landroidx/fragment/app/SpecialEffectsController$Operation;", "getLastIn", "Landroidx/fragment/app/FragmentTransitionImpl;", "getTransitionImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "Ljava/lang/Object;", "getSharedElementTransition", "()Ljava/lang/Object;", "Ljava/util/ArrayList;", "getSharedElementFirstOutViews", "()Ljava/util/ArrayList;", "getSharedElementLastInViews", "Landroidx/collection/ArrayMap;", "getSharedElementNameMapping", "()Landroidx/collection/ArrayMap;", "getEnteringNames", "getExitingNames", "getFirstOutViews", "getLastInViews", "Z", "()Z", "Landroidx/core/os/CancellationSignal;", "transitionSignal", "Landroidx/core/os/CancellationSignal;", "getTransitionSignal", "()Landroidx/core/os/CancellationSignal;", "getTransitionSignal$annotations", "()V", "controller", "getController", "setController", "(Ljava/lang/Object;)V", "isSeekingSupported", "getTransitioning", "transitioning", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TransitionEffect extends SpecialEffectsController.Effect {
        private Object controller;
        private final ArrayList<String> enteringNames;
        private final ArrayList<String> exitingNames;
        private final SpecialEffectsController.Operation firstOut;
        private final ArrayMap<String, View> firstOutViews;
        private final boolean isPop;
        private final SpecialEffectsController.Operation lastIn;
        private final ArrayMap<String, View> lastInViews;
        private final ArrayList<View> sharedElementFirstOutViews;
        private final ArrayList<View> sharedElementLastInViews;
        private final ArrayMap<String, String> sharedElementNameMapping;
        private final Object sharedElementTransition;
        private final FragmentTransitionImpl transitionImpl;
        private final List<TransitionInfo> transitionInfos;
        private final CancellationSignal transitionSignal = new CancellationSignal();

        public TransitionEffect(List<TransitionInfo> list, SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, FragmentTransitionImpl fragmentTransitionImpl, Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2, ArrayMap<String, String> arrayMap, ArrayList<String> arrayList3, ArrayList<String> arrayList4, ArrayMap<String, View> arrayMap2, ArrayMap<String, View> arrayMap3, boolean z) {
            j.e(list, "transitionInfos");
            j.e(fragmentTransitionImpl, "transitionImpl");
            j.e(arrayList, "sharedElementFirstOutViews");
            j.e(arrayList2, "sharedElementLastInViews");
            j.e(arrayMap, "sharedElementNameMapping");
            j.e(arrayList3, "enteringNames");
            j.e(arrayList4, "exitingNames");
            j.e(arrayMap2, "firstOutViews");
            j.e(arrayMap3, "lastInViews");
            this.transitionInfos = list;
            this.firstOut = operation;
            this.lastIn = operation2;
            this.transitionImpl = fragmentTransitionImpl;
            this.sharedElementTransition = obj;
            this.sharedElementFirstOutViews = arrayList;
            this.sharedElementLastInViews = arrayList2;
            this.sharedElementNameMapping = arrayMap;
            this.enteringNames = arrayList3;
            this.exitingNames = arrayList4;
            this.firstOutViews = arrayMap2;
            this.lastInViews = arrayMap3;
            this.isPop = z;
        }

        private final void captureTransitioningViews(ArrayList<View> arrayList, View view) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (!ViewGroupCompat.isTransitionGroup(viewGroup)) {
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (childAt.getVisibility() == 0) {
                            captureTransitioningViews(arrayList, childAt);
                        }
                    }
                } else if (!arrayList.contains(view)) {
                    arrayList.add(view);
                }
            } else if (!arrayList.contains(view)) {
                arrayList.add(view);
            }
        }

        private final i createMergedTransition(ViewGroup viewGroup, SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2) {
            ViewGroup viewGroup2 = viewGroup;
            SpecialEffectsController.Operation operation3 = operation;
            SpecialEffectsController.Operation operation4 = operation2;
            View view = new View(viewGroup2.getContext());
            Rect rect = new Rect();
            boolean z = false;
            View view2 = null;
            for (TransitionInfo hasSharedElementTransition : this.transitionInfos) {
                if (!(!hasSharedElementTransition.hasSharedElementTransition() || operation4 == null || operation3 == null || this.sharedElementNameMapping.isEmpty() || this.sharedElementTransition == null)) {
                    FragmentTransition.callSharedElementStartEnd(operation3.getFragment(), operation4.getFragment(), this.isPop, this.firstOutViews, true);
                    OneShotPreDrawListener.add(viewGroup2, new a(operation3, operation4, this, 1));
                    this.sharedElementFirstOutViews.addAll(this.firstOutViews.values());
                    if (!this.exitingNames.isEmpty()) {
                        String str = this.exitingNames.get(0);
                        j.d(str, "exitingNames[0]");
                        view2 = this.firstOutViews.get(str);
                        this.transitionImpl.setEpicenter(this.sharedElementTransition, view2);
                    }
                    this.sharedElementLastInViews.addAll(this.lastInViews.values());
                    if (!this.enteringNames.isEmpty()) {
                        String str2 = this.enteringNames.get(0);
                        j.d(str2, "enteringNames[0]");
                        View view3 = this.lastInViews.get(str2);
                        if (view3 != null) {
                            OneShotPreDrawListener.add(viewGroup2, new a(this.transitionImpl, view3, rect, 2));
                            z = true;
                        }
                    }
                    this.transitionImpl.setSharedElementTargets(this.sharedElementTransition, view, this.sharedElementFirstOutViews);
                    FragmentTransitionImpl fragmentTransitionImpl = this.transitionImpl;
                    Object obj = this.sharedElementTransition;
                    fragmentTransitionImpl.scheduleRemoveTargets(obj, (Object) null, (ArrayList<View>) null, (Object) null, (ArrayList<View>) null, obj, this.sharedElementLastInViews);
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator<TransitionInfo> it = this.transitionInfos.iterator();
            Object obj2 = null;
            Object obj3 = null;
            while (it.hasNext()) {
                TransitionInfo next = it.next();
                SpecialEffectsController.Operation operation5 = next.getOperation();
                boolean z3 = z;
                Object cloneTransition = this.transitionImpl.cloneTransition(next.getTransition());
                if (cloneTransition != null) {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<TransitionInfo> it2 = it;
                    View view4 = operation5.getFragment().mView;
                    TransitionInfo transitionInfo = next;
                    j.d(view4, "operation.fragment.mView");
                    captureTransitioningViews(arrayList2, view4);
                    if (this.sharedElementTransition != null && (operation5 == operation4 || operation5 == operation3)) {
                        if (operation5 == operation4) {
                            arrayList2.removeAll(C1194l.p1(this.sharedElementFirstOutViews));
                        } else {
                            arrayList2.removeAll(C1194l.p1(this.sharedElementLastInViews));
                        }
                    }
                    if (arrayList2.isEmpty()) {
                        this.transitionImpl.addTarget(cloneTransition, view);
                    } else {
                        this.transitionImpl.addTargets(cloneTransition, arrayList2);
                        this.transitionImpl.scheduleRemoveTargets(cloneTransition, cloneTransition, arrayList2, (Object) null, (ArrayList<View>) null, (Object) null, (ArrayList<View>) null);
                        if (operation5.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation5.setAwaitingContainerChanges(false);
                            ArrayList arrayList3 = new ArrayList(arrayList2);
                            arrayList3.remove(operation5.getFragment().mView);
                            this.transitionImpl.scheduleHideFragmentView(cloneTransition, operation5.getFragment().mView, arrayList3);
                            OneShotPreDrawListener.add(viewGroup2, new b(1, arrayList2));
                        }
                    }
                    if (operation5.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList.addAll(arrayList2);
                        if (z3) {
                            this.transitionImpl.setEpicenter(cloneTransition, rect);
                        }
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "Entering Transition: " + cloneTransition);
                            Log.v("FragmentManager", ">>>>> EnteringViews <<<<<");
                            Iterator it3 = arrayList2.iterator();
                            while (it3.hasNext()) {
                                Object next2 = it3.next();
                                j.d(next2, "transitioningViews");
                                Log.v("FragmentManager", "View: " + ((View) next2));
                            }
                        }
                    } else {
                        this.transitionImpl.setEpicenter(cloneTransition, view2);
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "Exiting Transition: " + cloneTransition);
                            Log.v("FragmentManager", ">>>>> ExitingViews <<<<<");
                            Iterator it4 = arrayList2.iterator();
                            while (it4.hasNext()) {
                                Object next3 = it4.next();
                                j.d(next3, "transitioningViews");
                                Log.v("FragmentManager", "View: " + ((View) next3));
                            }
                        }
                    }
                    if (transitionInfo.isOverlapAllowed()) {
                        obj2 = this.transitionImpl.mergeTransitionsTogether(obj2, cloneTransition, (Object) null);
                    } else {
                        obj3 = this.transitionImpl.mergeTransitionsTogether(obj3, cloneTransition, (Object) null);
                    }
                    operation3 = operation;
                    z = z3;
                    it = it2;
                } else {
                    operation3 = operation;
                    z = z3;
                }
            }
            Object mergeTransitionsInSequence = this.transitionImpl.mergeTransitionsInSequence(obj2, obj3, this.sharedElementTransition);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Final merged transition: " + mergeTransitionsInSequence);
            }
            return new i(arrayList, mergeTransitionsInSequence);
        }

        /* access modifiers changed from: private */
        public static final void createMergedTransition$lambda$12(SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, TransitionEffect transitionEffect) {
            j.e(transitionEffect, "this$0");
            FragmentTransition.callSharedElementStartEnd(operation.getFragment(), operation2.getFragment(), transitionEffect.isPop, transitionEffect.lastInViews, false);
        }

        /* access modifiers changed from: private */
        public static final void createMergedTransition$lambda$13(FragmentTransitionImpl fragmentTransitionImpl, View view, Rect rect) {
            j.e(fragmentTransitionImpl, "$impl");
            j.e(rect, "$lastInEpicenterRect");
            fragmentTransitionImpl.getBoundsOnScreen(view, rect);
        }

        /* access modifiers changed from: private */
        public static final void createMergedTransition$lambda$14(ArrayList arrayList) {
            j.e(arrayList, "$transitioningViews");
            FragmentTransition.setViewVisibility(arrayList, 4);
        }

        /* access modifiers changed from: private */
        public static final void onCommit$lambda$11$lambda$10(SpecialEffectsController.Operation operation, TransitionEffect transitionEffect) {
            j.e(operation, "$operation");
            j.e(transitionEffect, "this$0");
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Transition for operation " + operation + " has completed");
            }
            operation.completeEffect(transitionEffect);
        }

        /* access modifiers changed from: private */
        public static final void onStart$lambda$6$lambda$4(u uVar) {
            j.e(uVar, "$seekCancelLambda");
            a aVar = (a) uVar.d;
            if (aVar != null) {
                aVar.invoke();
            }
        }

        /* access modifiers changed from: private */
        public static final void onStart$lambda$6$lambda$5(SpecialEffectsController.Operation operation, TransitionEffect transitionEffect) {
            j.e(operation, "$operation");
            j.e(transitionEffect, "this$0");
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Transition for operation " + operation + " has completed");
            }
            operation.completeEffect(transitionEffect);
        }

        private final void runTransition(ArrayList<View> arrayList, ViewGroup viewGroup, a aVar) {
            FragmentTransition.setViewVisibility(arrayList, 4);
            ArrayList<String> prepareSetNameOverridesReordered = this.transitionImpl.prepareSetNameOverridesReordered(this.sharedElementLastInViews);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", ">>>>> Beginning transition <<<<<");
                Log.v("FragmentManager", ">>>>> SharedElementFirstOutViews <<<<<");
                Iterator<View> it = this.sharedElementFirstOutViews.iterator();
                while (it.hasNext()) {
                    View next = it.next();
                    j.d(next, "sharedElementFirstOutViews");
                    View view = next;
                    Log.v("FragmentManager", "View: " + view + " Name: " + ViewCompat.getTransitionName(view));
                }
                Log.v("FragmentManager", ">>>>> SharedElementLastInViews <<<<<");
                Iterator<View> it2 = this.sharedElementLastInViews.iterator();
                while (it2.hasNext()) {
                    View next2 = it2.next();
                    j.d(next2, "sharedElementLastInViews");
                    View view2 = next2;
                    Log.v("FragmentManager", "View: " + view2 + " Name: " + ViewCompat.getTransitionName(view2));
                }
            }
            aVar.invoke();
            this.transitionImpl.setNameOverridesReordered(viewGroup, this.sharedElementFirstOutViews, this.sharedElementLastInViews, prepareSetNameOverridesReordered, this.sharedElementNameMapping);
            FragmentTransition.setViewVisibility(arrayList, 0);
            this.transitionImpl.swapSharedElementTargets(this.sharedElementTransition, this.sharedElementFirstOutViews, this.sharedElementLastInViews);
        }

        public final Object getController() {
            return this.controller;
        }

        public final SpecialEffectsController.Operation getFirstOut() {
            return this.firstOut;
        }

        public final SpecialEffectsController.Operation getLastIn() {
            return this.lastIn;
        }

        public final FragmentTransitionImpl getTransitionImpl() {
            return this.transitionImpl;
        }

        public final List<TransitionInfo> getTransitionInfos() {
            return this.transitionInfos;
        }

        public final boolean getTransitioning() {
            Iterable<TransitionInfo> iterable = this.transitionInfos;
            if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                return true;
            }
            for (TransitionInfo operation : iterable) {
                if (!operation.getOperation().getFragment().mTransitioning) {
                    return false;
                }
            }
            return true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:9:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isSeekingSupported() {
            /*
                r4 = this;
                androidx.fragment.app.FragmentTransitionImpl r0 = r4.transitionImpl
                boolean r0 = r0.isSeekingSupported()
                if (r0 == 0) goto L_0x0051
                java.util.List<androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo> r0 = r4.transitionInfos
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r1 = r0 instanceof java.util.Collection
                if (r1 == 0) goto L_0x001a
                r1 = r0
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x001a
                goto L_0x0043
            L_0x001a:
                java.util.Iterator r0 = r0.iterator()
            L_0x001e:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0043
                java.lang.Object r1 = r0.next()
                androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r1 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r1
                int r2 = android.os.Build.VERSION.SDK_INT
                r3 = 34
                if (r2 < r3) goto L_0x0051
                java.lang.Object r2 = r1.getTransition()
                if (r2 == 0) goto L_0x0051
                androidx.fragment.app.FragmentTransitionImpl r2 = r4.transitionImpl
                java.lang.Object r1 = r1.getTransition()
                boolean r1 = r2.isSeekingSupported(r1)
                if (r1 == 0) goto L_0x0051
                goto L_0x001e
            L_0x0043:
                java.lang.Object r0 = r4.sharedElementTransition
                if (r0 == 0) goto L_0x004f
                androidx.fragment.app.FragmentTransitionImpl r4 = r4.transitionImpl
                boolean r4 = r4.isSeekingSupported(r0)
                if (r4 == 0) goto L_0x0051
            L_0x004f:
                r4 = 1
                return r4
            L_0x0051:
                r4 = 0
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.TransitionEffect.isSeekingSupported():boolean");
        }

        public void onCancel(ViewGroup viewGroup) {
            j.e(viewGroup, "container");
            this.transitionSignal.cancel();
        }

        public void onCommit(ViewGroup viewGroup) {
            j.e(viewGroup, "container");
            if (!viewGroup.isLaidOut()) {
                for (TransitionInfo transitionInfo : this.transitionInfos) {
                    SpecialEffectsController.Operation operation = transitionInfo.getOperation();
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Container " + viewGroup + " has not been laid out. Completing operation " + operation);
                    }
                    transitionInfo.getOperation().completeEffect(this);
                }
                return;
            }
            Object obj = this.controller;
            if (obj != null) {
                this.transitionImpl.animateToEnd(obj);
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ending execution of operations from " + this.firstOut + " to " + this.lastIn);
                    return;
                }
                return;
            }
            i createMergedTransition = createMergedTransition(viewGroup, this.lastIn, this.firstOut);
            ArrayList arrayList = (ArrayList) createMergedTransition.d;
            Object obj2 = createMergedTransition.e;
            Iterable<TransitionInfo> iterable = this.transitionInfos;
            ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
            for (TransitionInfo operation2 : iterable) {
                arrayList2.add(operation2.getOperation());
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                SpecialEffectsController.Operation operation3 = (SpecialEffectsController.Operation) it.next();
                this.transitionImpl.setListenerForTransitionEnd(operation3.getFragment(), obj2, this.transitionSignal, new c(operation3, this, 1));
            }
            runTransition(arrayList, viewGroup, new DefaultSpecialEffectsController$TransitionEffect$onCommit$4(this, viewGroup, obj2));
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Completed executing operations from " + this.firstOut + " to " + this.lastIn);
            }
        }

        public void onProgress(BackEventCompat backEventCompat, ViewGroup viewGroup) {
            j.e(backEventCompat, "backEvent");
            j.e(viewGroup, "container");
            Object obj = this.controller;
            if (obj != null) {
                this.transitionImpl.setCurrentPlayTime(obj, backEventCompat.getProgress());
            }
        }

        /* JADX WARNING: type inference failed for: r0v5, types: [kotlin.jvm.internal.u, java.lang.Object] */
        public void onStart(ViewGroup viewGroup) {
            j.e(viewGroup, "container");
            if (!viewGroup.isLaidOut()) {
                for (TransitionInfo operation : this.transitionInfos) {
                    SpecialEffectsController.Operation operation2 = operation.getOperation();
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Container " + viewGroup + " has not been laid out. Skipping onStart for operation " + operation2);
                    }
                }
                return;
            }
            if (getTransitioning() && this.sharedElementTransition != null && !isSeekingSupported()) {
                Log.i("FragmentManager", "Ignoring shared elements transition " + this.sharedElementTransition + " between " + this.firstOut + " and " + this.lastIn + " as neither fragment has set a Transition. In order to run a SharedElementTransition, you must also set either an enter or exit transition on a fragment involved in the transaction. The sharedElementTransition will run after the back gesture has been committed.");
            }
            if (isSeekingSupported() && getTransitioning()) {
                ? obj = new Object();
                i createMergedTransition = createMergedTransition(viewGroup, this.lastIn, this.firstOut);
                ArrayList arrayList = (ArrayList) createMergedTransition.d;
                Object obj2 = createMergedTransition.e;
                Iterable<TransitionInfo> iterable = this.transitionInfos;
                ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
                for (TransitionInfo operation3 : iterable) {
                    arrayList2.add(operation3.getOperation());
                }
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    b bVar = new b(0, obj);
                    SpecialEffectsController.Operation operation4 = (SpecialEffectsController.Operation) it.next();
                    this.transitionImpl.setListenerForTransitionEnd(operation4.getFragment(), obj2, this.transitionSignal, bVar, new c(operation4, this, 0));
                }
                runTransition(arrayList, viewGroup, new DefaultSpecialEffectsController$TransitionEffect$onStart$4(this, viewGroup, obj2, obj));
            }
        }

        public final void setController(Object obj) {
            this.controller = obj;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
        j.e(viewGroup, "container");
    }

    private final void collectAnimEffects(List<AnimationInfo> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (AnimationInfo operation : list) {
            C1200r.A0(operation.getOperation().getEffects$fragment_release(), arrayList2);
        }
        boolean isEmpty = arrayList2.isEmpty();
        boolean z = false;
        for (AnimationInfo next : list) {
            Context context = getContainer().getContext();
            SpecialEffectsController.Operation operation2 = next.getOperation();
            j.d(context, "context");
            FragmentAnim.AnimationOrAnimator animation = next.getAnimation(context);
            if (animation != null) {
                if (animation.animator == null) {
                    arrayList.add(next);
                } else {
                    Fragment fragment = operation2.getFragment();
                    if (operation2.getEffects$fragment_release().isEmpty()) {
                        if (operation2.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation2.setAwaitingContainerChanges(false);
                        }
                        operation2.addEffect(new AnimatorEffect(next));
                        z = true;
                    } else if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                    }
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AnimationInfo animationInfo = (AnimationInfo) it.next();
            SpecialEffectsController.Operation operation3 = animationInfo.getOperation();
            Fragment fragment2 = operation3.getFragment();
            if (!isEmpty) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
            } else if (!z) {
                operation3.addEffect(new AnimationEffect(animationInfo));
            } else if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void collectEffects$lambda$2(DefaultSpecialEffectsController defaultSpecialEffectsController, SpecialEffectsController.Operation operation) {
        j.e(defaultSpecialEffectsController, "this$0");
        j.e(operation, "$operation");
        defaultSpecialEffectsController.applyContainerChangesToOperation$fragment_release(operation);
    }

    private final void createTransitionEffect(List<TransitionInfo> list, boolean z, SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2) {
        ArrayList arrayList;
        FragmentTransitionImpl fragmentTransitionImpl;
        i iVar;
        SpecialEffectsController.Operation operation3 = operation;
        SpecialEffectsController.Operation operation4 = operation2;
        ArrayList arrayList2 = new ArrayList();
        for (Object next : list) {
            if (!((TransitionInfo) next).isVisibilityUnchanged()) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (((TransitionInfo) next2).getHandlingImpl() != null) {
                arrayList3.add(next2);
            }
        }
        Iterator it2 = arrayList3.iterator();
        FragmentTransitionImpl fragmentTransitionImpl2 = null;
        while (it2.hasNext()) {
            TransitionInfo transitionInfo = (TransitionInfo) it2.next();
            FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
            if (fragmentTransitionImpl2 == null || handlingImpl == fragmentTransitionImpl2) {
                fragmentTransitionImpl2 = handlingImpl;
            } else {
                throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition type than other Fragments.").toString());
            }
        }
        if (fragmentTransitionImpl2 != null) {
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayMap arrayMap = new ArrayMap();
            ArrayList<String> arrayList6 = new ArrayList<>();
            ArrayList<String> arrayList7 = new ArrayList<>();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayMap arrayMap3 = new ArrayMap();
            Iterator it3 = arrayList3.iterator();
            while (true) {
                Object obj = null;
                while (it3.hasNext()) {
                    TransitionInfo transitionInfo2 = (TransitionInfo) it3.next();
                    if (!transitionInfo2.hasSharedElementTransition() || operation3 == null || operation4 == null) {
                        arrayList = arrayList3;
                        fragmentTransitionImpl = fragmentTransitionImpl2;
                    } else {
                        obj = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(transitionInfo2.getSharedElementTransition()));
                        arrayList7 = operation4.getFragment().getSharedElementSourceNames();
                        j.d(arrayList7, "lastIn.fragment.sharedElementSourceNames");
                        ArrayList<String> sharedElementSourceNames = operation3.getFragment().getSharedElementSourceNames();
                        j.d(sharedElementSourceNames, "firstOut.fragment.sharedElementSourceNames");
                        ArrayList<String> sharedElementTargetNames = operation3.getFragment().getSharedElementTargetNames();
                        j.d(sharedElementTargetNames, "firstOut.fragment.sharedElementTargetNames");
                        int size = sharedElementTargetNames.size();
                        arrayList = arrayList3;
                        int i2 = 0;
                        while (i2 < size) {
                            FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
                            int indexOf = arrayList7.indexOf(sharedElementTargetNames.get(i2));
                            int i7 = size;
                            if (indexOf != -1) {
                                arrayList7.set(indexOf, sharedElementSourceNames.get(i2));
                            }
                            i2++;
                            fragmentTransitionImpl2 = fragmentTransitionImpl3;
                            size = i7;
                        }
                        fragmentTransitionImpl = fragmentTransitionImpl2;
                        arrayList6 = operation4.getFragment().getSharedElementTargetNames();
                        j.d(arrayList6, "lastIn.fragment.sharedElementTargetNames");
                        if (!z) {
                            operation3.getFragment().getExitTransitionCallback();
                            operation4.getFragment().getEnterTransitionCallback();
                            iVar = new i((Object) null, (Object) null);
                        } else {
                            operation3.getFragment().getEnterTransitionCallback();
                            operation4.getFragment().getExitTransitionCallback();
                            iVar = new i((Object) null, (Object) null);
                        }
                        if (iVar.d != null) {
                            throw new ClassCastException();
                        } else if (iVar.e == null) {
                            int i8 = 0;
                            for (int size2 = arrayList7.size(); i8 < size2; size2 = size2) {
                                String str = arrayList7.get(i8);
                                j.d(str, "exitingNames[i]");
                                String str2 = arrayList6.get(i8);
                                j.d(str2, "enteringNames[i]");
                                arrayMap.put(str, str2);
                                i8++;
                            }
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", ">>> entering view names <<<");
                                for (Iterator<String> it4 = arrayList6.iterator(); it4.hasNext(); it4 = it4) {
                                    Log.v("FragmentManager", "Name: " + it4.next());
                                }
                                Log.v("FragmentManager", ">>> exiting view names <<<");
                                for (Iterator<String> it5 = arrayList7.iterator(); it5.hasNext(); it5 = it5) {
                                    Log.v("FragmentManager", "Name: " + it5.next());
                                }
                            }
                            View view = operation3.getFragment().mView;
                            j.d(view, "firstOut.fragment.mView");
                            findNamedViews(arrayMap2, view);
                            arrayMap2.retainAll(arrayList7);
                            arrayMap.retainAll(arrayMap2.keySet());
                            View view2 = operation4.getFragment().mView;
                            j.d(view2, "lastIn.fragment.mView");
                            findNamedViews(arrayMap3, view2);
                            arrayMap3.retainAll(arrayList6);
                            arrayMap3.retainAll(arrayMap.values());
                            FragmentTransition.retainValues(arrayMap, arrayMap3);
                            Set keySet = arrayMap.keySet();
                            j.d(keySet, "sharedElementNameMapping.keys");
                            retainMatchingViews(arrayMap2, keySet);
                            Collection values = arrayMap.values();
                            j.d(values, "sharedElementNameMapping.values");
                            retainMatchingViews(arrayMap3, values);
                            if (arrayMap.isEmpty()) {
                                Log.i("FragmentManager", "Ignoring shared elements transition " + obj + " between " + operation3 + " and " + operation4 + " as there are no matching elements in both the entering and exiting fragment. In order to run a SharedElementTransition, both fragments involved must have the element.");
                                arrayList4.clear();
                                arrayList5.clear();
                                arrayList3 = arrayList;
                                fragmentTransitionImpl2 = fragmentTransitionImpl;
                            }
                        } else {
                            throw new ClassCastException();
                        }
                    }
                    arrayList3 = arrayList;
                    fragmentTransitionImpl2 = fragmentTransitionImpl;
                }
                ArrayList arrayList8 = arrayList3;
                FragmentTransitionImpl fragmentTransitionImpl4 = fragmentTransitionImpl2;
                if (obj != null) {
                    ArrayList arrayList9 = arrayList8;
                    TransitionEffect transitionEffect = new TransitionEffect(arrayList9, operation3, operation4, fragmentTransitionImpl4, obj, arrayList4, arrayList5, arrayMap, arrayList6, arrayList7, arrayMap2, arrayMap3, z);
                    Iterator it6 = arrayList9.iterator();
                } else if (!arrayList8.isEmpty()) {
                    Iterator it7 = arrayList8.iterator();
                    while (it7.hasNext()) {
                        if (((TransitionInfo) it7.next()).getTransition() != null) {
                        }
                    }
                    return;
                } else {
                    return;
                }
                ArrayList arrayList92 = arrayList8;
                TransitionEffect transitionEffect2 = new TransitionEffect(arrayList92, operation3, operation4, fragmentTransitionImpl4, obj, arrayList4, arrayList5, arrayMap, arrayList6, arrayList7, arrayMap2, arrayMap3, z);
                Iterator it62 = arrayList92.iterator();
                while (it62.hasNext()) {
                    ((TransitionInfo) it62.next()).getOperation().addEffect(transitionEffect2);
                }
                return;
            }
        }
    }

    private final void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    private final void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Set<Map.Entry<String, View>> entrySet = arrayMap.entrySet();
        j.d(entrySet, "entries");
        DefaultSpecialEffectsController$retainMatchingViews$1 defaultSpecialEffectsController$retainMatchingViews$1 = new DefaultSpecialEffectsController$retainMatchingViews$1(collection);
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            if (!((Boolean) defaultSpecialEffectsController$retainMatchingViews$1.invoke(it.next())).booleanValue()) {
                it.remove();
            }
        }
    }

    private final void syncAnimations(List<? extends SpecialEffectsController.Operation> list) {
        Fragment fragment = ((SpecialEffectsController.Operation) C1194l.T0(list)).getFragment();
        for (SpecialEffectsController.Operation operation : list) {
            operation.getFragment().mAnimationInfo.mEnterAnim = fragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = fragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = fragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = fragment.mAnimationInfo.mPopExitAnim;
        }
    }

    public void collectEffects(List<? extends SpecialEffectsController.Operation> list, boolean z) {
        SpecialEffectsController.Operation operation;
        Object obj;
        j.e(list, "operations");
        Iterator it = list.iterator();
        while (true) {
            operation = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            SpecialEffectsController.Operation operation2 = (SpecialEffectsController.Operation) obj;
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.Companion;
            View view = operation2.getFragment().mView;
            j.d(view, "operation.fragment.mView");
            SpecialEffectsController.Operation.State asOperationState = companion.asOperationState(view);
            SpecialEffectsController.Operation.State state = SpecialEffectsController.Operation.State.VISIBLE;
            if (asOperationState == state && operation2.getFinalState() != state) {
                break;
            }
        }
        SpecialEffectsController.Operation operation3 = (SpecialEffectsController.Operation) obj;
        ListIterator<? extends SpecialEffectsController.Operation> listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                break;
            }
            Object previous = listIterator.previous();
            SpecialEffectsController.Operation operation4 = (SpecialEffectsController.Operation) previous;
            SpecialEffectsController.Operation.State.Companion companion2 = SpecialEffectsController.Operation.State.Companion;
            View view2 = operation4.getFragment().mView;
            j.d(view2, "operation.fragment.mView");
            SpecialEffectsController.Operation.State asOperationState2 = companion2.asOperationState(view2);
            SpecialEffectsController.Operation.State state2 = SpecialEffectsController.Operation.State.VISIBLE;
            if (asOperationState2 != state2 && operation4.getFinalState() == state2) {
                operation = previous;
                break;
            }
        }
        SpecialEffectsController.Operation operation5 = operation;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Executing operations from " + operation3 + " to " + operation5);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        syncAnimations(list);
        for (SpecialEffectsController.Operation operation6 : list) {
            arrayList.add(new AnimationInfo(operation6, z));
            boolean z3 = false;
            if (z) {
                if (operation6 != operation3) {
                    arrayList2.add(new TransitionInfo(operation6, z, z3));
                    operation6.addCompletionListener(new d(1, this, operation6));
                }
            } else if (operation6 != operation5) {
                arrayList2.add(new TransitionInfo(operation6, z, z3));
                operation6.addCompletionListener(new d(1, this, operation6));
            }
            z3 = true;
            arrayList2.add(new TransitionInfo(operation6, z, z3));
            operation6.addCompletionListener(new d(1, this, operation6));
        }
        createTransitionEffect(arrayList2, z, operation3, operation5);
        collectAnimEffects(arrayList);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000fH\u0002J\u0006\u0010\u0014\u001a\u00020\u0005R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0015"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "isPop", "", "providesSharedElementTransition", "(Landroidx/fragment/app/SpecialEffectsController$Operation;ZZ)V", "handlingImpl", "Landroidx/fragment/app/FragmentTransitionImpl;", "getHandlingImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "isOverlapAllowed", "()Z", "sharedElementTransition", "", "getSharedElementTransition", "()Ljava/lang/Object;", "transition", "getTransition", "hasSharedElementTransition", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TransitionInfo extends SpecialEffectsInfo {
        private final boolean isOverlapAllowed;
        private final Object sharedElementTransition;
        private final Object transition;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TransitionInfo(SpecialEffectsController.Operation operation, boolean z, boolean z3) {
            super(operation);
            Object obj;
            boolean z7;
            Object obj2;
            j.e(operation, "operation");
            SpecialEffectsController.Operation.State finalState = operation.getFinalState();
            SpecialEffectsController.Operation.State state = SpecialEffectsController.Operation.State.VISIBLE;
            if (finalState == state) {
                if (z) {
                    obj = operation.getFragment().getReenterTransition();
                } else {
                    obj = operation.getFragment().getEnterTransition();
                }
            } else if (z) {
                obj = operation.getFragment().getReturnTransition();
            } else {
                obj = operation.getFragment().getExitTransition();
            }
            this.transition = obj;
            if (operation.getFinalState() != state) {
                z7 = true;
            } else if (z) {
                z7 = operation.getFragment().getAllowReturnTransitionOverlap();
            } else {
                z7 = operation.getFragment().getAllowEnterTransitionOverlap();
            }
            this.isOverlapAllowed = z7;
            if (!z3) {
                obj2 = null;
            } else if (z) {
                obj2 = operation.getFragment().getSharedElementReturnTransition();
            } else {
                obj2 = operation.getFragment().getSharedElementEnterTransition();
            }
            this.sharedElementTransition = obj2;
        }

        public final FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.transition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.sharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl == null ? handlingImpl2 : handlingImpl;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition).toString());
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final Object getTransition() {
            return this.transition;
        }

        public final boolean hasSharedElementTransition() {
            if (this.sharedElementTransition != null) {
                return true;
            }
            return false;
        }

        public final boolean isOverlapAllowed() {
            return this.isOverlapAllowed;
        }

        private final FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = FragmentTransition.PLATFORM_IMPL;
            if (fragmentTransitionImpl != null && fragmentTransitionImpl.canHandle(obj)) {
                return fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = FragmentTransition.SUPPORT_IMPL;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.canHandle(obj)) {
                return fragmentTransitionImpl2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
