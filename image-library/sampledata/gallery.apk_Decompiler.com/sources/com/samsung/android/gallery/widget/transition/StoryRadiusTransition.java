package com.samsung.android.gallery.widget.transition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Outline;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.story.CoverGradientBlur;
import fc.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryRadiusTransition extends Transition {
    public StoryRadiusTransition(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: private */
    public void applyGradientBlur(View view, boolean z, boolean z3) {
        String transitionName;
        if (PreferenceFeatures.OneUi8x.STORY_COVER_BLUR && view != null && (transitionName = view.getTransitionName()) != null && transitionName.startsWith("story/image/")) {
            if (!z3 || !z) {
                CoverGradientBlur.release(view);
            } else {
                CoverGradientBlur.apply(view);
            }
        }
    }

    private Animator createRadiusAnimator(View view) {
        Context context;
        final boolean z;
        if (view != null) {
            context = view.getContext();
        } else {
            context = null;
        }
        if (context != null && !TextUtils.isEmpty(view.getTransitionName()) && (view.getTransitionName().startsWith("story/image/") || view.getTransitionName().startsWith("story/gradient/"))) {
            try {
                final Blackboard instance = Blackboard.getInstance(context.toString());
                int[] iArr = (int[]) instance.read("data://story_transition_view_radius");
                if (iArr != null) {
                    if (iArr.length >= 2) {
                        final boolean supportGradientBlur = supportGradientBlur(instance);
                        if (((Integer) instance.read("data://story_transition_return_position", -1)).intValue() >= 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{iArr[0], iArr[1]});
                        view.setClipToOutline(true);
                        final a aVar = new a(this, view, z, supportGradientBlur);
                        ofInt.addUpdateListener(aVar);
                        final View view2 = view;
                        final ValueAnimator valueAnimator = ofInt;
                        valueAnimator.addListener(new SimpleAnimatorListener() {
                            public void onAnimationEnd(Animator animator) {
                                valueAnimator.removeUpdateListener(aVar);
                                view2.setOutlineProvider((ViewOutlineProvider) null);
                                instance.erase("data://story_transition_view_radius");
                                if (supportGradientBlur) {
                                    StoryRadiusTransition.this.applyGradientBlur(view2, z, false);
                                }
                            }
                        });
                        return valueAnimator;
                    }
                }
            } catch (Exception e) {
                Log.d("RadiusTransition", "fail to create radius animator " + e.getMessage());
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createRadiusAnimator$0(View view, boolean z, boolean z3, ValueAnimator valueAnimator) {
        onUpdate(view, (float) ((Integer) valueAnimator.getAnimatedValue()).intValue(), z, z3);
    }

    private void onUpdate(View view, final float f, boolean z, boolean z3) {
        if (view != null) {
            view.setOutlineProvider(new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), f);
                }
            });
            if (z3) {
                applyGradientBlur(view, z, true);
            }
        }
    }

    private boolean supportGradientBlur(Blackboard blackboard) {
        return !"location://stories/category/transitory".equals(ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(blackboard), "categoryKey", ""));
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        Animator createRadiusAnimator;
        if (transitionValues2 == null || (createRadiusAnimator = createRadiusAnimator(transitionValues2.view)) == null) {
            return super.createAnimator(viewGroup, transitionValues, transitionValues2);
        }
        return createRadiusAnimator;
    }

    public void captureEndValues(TransitionValues transitionValues) {
    }

    public void captureStartValues(TransitionValues transitionValues) {
    }
}
