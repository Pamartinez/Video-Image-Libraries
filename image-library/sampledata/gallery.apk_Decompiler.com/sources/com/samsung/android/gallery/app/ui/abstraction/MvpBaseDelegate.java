package com.samsung.android.gallery.app.ui.abstraction;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.PathInterpolator;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.popover.PopoverHelper;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MvpBaseDelegate {
    Boolean mAdvancedMouseDragAndDrop;
    BoosterCompat mBoosterCompat;
    Boolean mDexMode;
    Boolean mIsLandscape;

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$customizeAnimation$0(int i2, AnimatorSet animatorSet, TimeInterpolator timeInterpolator, Animator animator) {
        animator.setDuration((long) (((float) animator.getDuration()) * (((float) i2) / ((float) animatorSet.getTotalDuration()))));
        if (animator.getInterpolator() instanceof PathInterpolator) {
            animator.setInterpolator(timeInterpolator);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$customizeAnimation$1(int i2, TimeInterpolator timeInterpolator, Animator animator) {
        animator.setDuration((long) i2);
        if (animator.getInterpolator() instanceof PathInterpolator) {
            animator.setInterpolator(timeInterpolator);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postOpenEnterAnimStartEvent$2(final MvpBaseFragment mvpBaseFragment, Animator animator) {
        animator.addListener(new SimpleAnimatorListener() {
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                MvpBaseFragment mvpBaseFragment = mvpBaseFragment;
                mvpBaseFragment.mBlackboard.postEvent(EventMessage.obtain(1147, mvpBaseFragment.hashTag()));
            }
        });
    }

    public void adjustContentAreaMargin(MvpBaseFragment mvpBaseFragment, View view, WindowInsets windowInsets, boolean z) {
        int i2;
        int i7;
        int i8;
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        int i10 = 0;
        if (!z || (mvpBaseFragment.isDrawerMode() && mvpBaseFragment.isDisplayWithDrawer())) {
            i2 = 0;
        } else {
            i2 = WindowUtils.getSystemInsetsStart(rootWindowInsets);
        }
        ViewMarginUtils.setStartPadding(view, i2);
        if (z) {
            i7 = WindowUtils.getSystemInsetsEnd(rootWindowInsets);
        } else {
            i7 = 0;
        }
        ViewMarginUtils.setEndPadding(view, i7);
        if (windowInsets == null) {
            windowInsets = rootWindowInsets;
        }
        if (mvpBaseFragment.supportFloatingUi()) {
            i8 = 0;
        } else {
            i8 = WindowUtils.getSystemInsetsTop(windowInsets);
        }
        ViewMarginUtils.setTopMargin(view, i8);
        if (!mvpBaseFragment.supportFloatingUi()) {
            i10 = WindowUtils.getSystemInsetsBottom(rootWindowInsets);
        }
        ViewMarginUtils.setBottomMargin(view, i10);
    }

    public void customizeAnimation(Context context, Animator animator) {
        int integer = ResourceCompat.getInteger(context, R.integer.fragment_trans_duration_translate, StatusCodes.INPUT_MISSING);
        PathInterpolator pathInterpolator = new PathInterpolator(0.8f, 0.0f, 0.33f, 1.0f);
        if (animator instanceof AnimatorSet) {
            AnimatorSet animatorSet = (AnimatorSet) animator;
            animatorSet.getChildAnimations().forEach(new c(integer, animatorSet, pathInterpolator));
            return;
        }
        Optional.ofNullable(animator).ifPresent(new d(integer, pathInterpolator));
    }

    public void dump(MvpBaseFragment mvpBaseFragment, PrintWriter printWriter, String str) {
        List<Fragment> childFragments = mvpBaseFragment.getChildFragments();
        if (childFragments != null) {
            Logger.dumpLog(printWriter, str + "====== dump of fragment (" + this + ") =========");
            mvpBaseFragment.onDump(printWriter, str);
            Logger.dumpLog(printWriter, str + "=================   end  ==================");
            String str2 = str + " + ";
            for (Fragment next : childFragments) {
                if (next instanceof IBaseFragment) {
                    ((IBaseFragment) next).dump(printWriter, str2);
                }
            }
        }
    }

    public BoosterCompat getBoosterCompat(MvpBaseFragment mvpBaseFragment) {
        if (this.mBoosterCompat == null) {
            this.mBoosterCompat = SeApiCompat.getBoosterCompat(mvpBaseFragment.getApplicationContext());
        }
        return this.mBoosterCompat;
    }

    public void handleOrientationChange(MvpBaseFragment mvpBaseFragment, int i2) {
        boolean z;
        if (i2 == 2) {
            z = true;
        } else {
            z = false;
        }
        this.mIsLandscape = Boolean.valueOf(z);
        PopoverHelper.notifyAnchorSelfChanged(mvpBaseFragment.getBlackboard());
    }

    public boolean isDexMode(MvpBaseFragment mvpBaseFragment) {
        if (this.mDexMode == null) {
            this.mDexMode = Boolean.valueOf(DeviceInfo.isDexMode(mvpBaseFragment.getContext()));
        }
        return this.mDexMode.booleanValue();
    }

    public boolean isLandscape(MvpBaseFragment mvpBaseFragment) {
        if (this.mIsLandscape == null) {
            this.mIsLandscape = Boolean.valueOf(ResourceCompat.isLandscape((Context) BlackboardUtils.readActivity(mvpBaseFragment.mBlackboard)));
        }
        return this.mIsLandscape.booleanValue();
    }

    public void onDump(MvpBaseFragment mvpBaseFragment, PrintWriter printWriter, String str) {
        Logger.dumpLog(printWriter, str + "this = " + mvpBaseFragment);
        Logger.dumpLog(printWriter, str + "mLocationKey = " + mvpBaseFragment.mLocationKey);
        Logger.dumpLog(printWriter, str + "mCallerKey = " + mvpBaseFragment.mCallerKey);
    }

    public void postOpenEnterAnimStartEvent(MvpBaseFragment mvpBaseFragment, Animator animator, int i2) {
        if (animator != null && i2 == R.animator.sesl_fragment_open_enter) {
            Optional.ofNullable(animator).ifPresent(new e(this, mvpBaseFragment));
        }
    }

    public void rollbackProperty(final MvpBaseFragment mvpBaseFragment, Animator animator) {
        if (animator != null) {
            animator.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ViewUtils.setTranslationZ(mvpBaseFragment.getView(), 0.0f);
                    ViewUtils.setTranslationX(mvpBaseFragment.getView(), 0.0f);
                    ViewUtils.setAlpha(mvpBaseFragment.getView(), 1.0f);
                }
            });
        }
    }

    public void setCurrentTransitioningAnim(final MvpBaseFragment mvpBaseFragment, Animator animator, final int i2) {
        if (animator != null) {
            final int integer = ResourceCompat.getInteger(mvpBaseFragment.getContext(), R.integer.fragment_trans_duration_translate, StatusCodes.INPUT_MISSING);
            animator.addListener(new SimpleAnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                    mvpBaseFragment.setCurrentTransitioningAnim(0);
                }

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    mvpBaseFragment.setCurrentTransitioningAnim(0);
                }

                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    mvpBaseFragment.setCurrentTransitioningAnim(i2);
                    ThreadUtil.postOnUiThreadDelayed(new a(1, mvpBaseFragment), (long) integer);
                }
            });
        }
    }

    public void showOrHide(Fragment fragment, boolean z) {
        FragmentActivity fragmentActivity;
        if (fragment != null) {
            fragmentActivity = fragment.getActivity();
        } else {
            fragmentActivity = null;
        }
        if (fragmentActivity != null) {
            FragmentTransaction beginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            if (z) {
                beginTransaction.show(fragment);
            } else {
                beginTransaction.hide(fragment);
            }
            beginTransaction.commit();
        }
    }

    public boolean useAdvancedMouseDragAndDrop(MvpBaseFragment mvpBaseFragment) {
        if (this.mAdvancedMouseDragAndDrop == null) {
            this.mAdvancedMouseDragAndDrop = Boolean.valueOf(DeviceInfo.isAdvancedMouseCompat(mvpBaseFragment.getContext()));
        }
        return this.mAdvancedMouseDragAndDrop.booleanValue();
    }
}
