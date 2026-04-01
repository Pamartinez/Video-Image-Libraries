package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Visibility extends Transition {
    private static final String[] sTransitionProperties = {"android:visibility:visibility", "android:visibility:parent"};
    private int mMode = 3;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        boolean mCanceled = false;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        public DisappearListener(View view, int i2, boolean z) {
            this.mView = view;
            this.mFinalVisibility = i2;
            this.mParent = (ViewGroup) view.getParent();
            this.mSuppressLayout = z;
            suppressLayout(true);
        }

        private void hideViewWhenNotCanceled() {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
                ViewGroup viewGroup = this.mParent;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            suppressLayout(false);
        }

        private void suppressLayout(boolean z) {
            ViewGroup viewGroup;
            if (this.mSuppressLayout && this.mLayoutSuppressed != z && (viewGroup = this.mParent) != null) {
                this.mLayoutSuppressed = z;
                ViewGroupUtils.suppressLayout(viewGroup, z);
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(Animator animator) {
            hideViewWhenNotCanceled();
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onTransitionEnd(Transition transition) {
            transition.removeListener(this);
        }

        public void onTransitionPause(Transition transition) {
            suppressLayout(false);
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            }
        }

        public void onTransitionResume(Transition transition) {
            suppressLayout(true);
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
            }
        }

        public void onAnimationEnd(Animator animator, boolean z) {
            if (!z) {
                hideViewWhenNotCanceled();
            }
        }

        public void onAnimationStart(Animator animator, boolean z) {
            if (z) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
                ViewGroup viewGroup = this.mParent;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onTransitionCancel(Transition transition) {
        }

        public void onTransitionStart(Transition transition) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class OverlayListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private boolean mHasOverlay = true;
        private final ViewGroup mOverlayHost;
        private final View mOverlayView;
        private final View mStartView;

        public OverlayListener(ViewGroup viewGroup, View view, View view2) {
            this.mOverlayHost = viewGroup;
            this.mOverlayView = view;
            this.mStartView = view2;
        }

        private void removeFromOverlay() {
            this.mStartView.setTag(R$id.save_overlay_view, (Object) null);
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
            this.mHasOverlay = false;
        }

        public void onAnimationEnd(Animator animator) {
            removeFromOverlay();
        }

        public void onAnimationPause(Animator animator) {
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
        }

        public void onAnimationResume(Animator animator) {
            if (this.mOverlayView.getParent() == null) {
                this.mOverlayHost.getOverlay().add(this.mOverlayView);
            } else {
                Visibility.this.cancel();
            }
        }

        public void onAnimationStart(Animator animator, boolean z) {
            if (z) {
                this.mStartView.setTag(R$id.save_overlay_view, this.mOverlayView);
                this.mOverlayHost.getOverlay().add(this.mOverlayView);
                this.mHasOverlay = true;
            }
        }

        public void onTransitionCancel(Transition transition) {
            if (this.mHasOverlay) {
                removeFromOverlay();
            }
        }

        public void onTransitionEnd(Transition transition) {
            transition.removeListener(this);
        }

        public void onAnimationEnd(Animator animator, boolean z) {
            if (!z) {
                removeFromOverlay();
            }
        }

        public void onTransitionPause(Transition transition) {
        }

        public void onTransitionResume(Transition transition) {
        }

        public void onTransitionStart(Transition transition) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VisibilityInfo {
        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put("android:visibility:visibility", Integer.valueOf(transitionValues.view.getVisibility()));
        transitionValues.values.put("android:visibility:parent", transitionValues.view.getParent());
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put("android:visibility:screenLocation", iArr);
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityInfo = new VisibilityInfo();
        visibilityInfo.mVisibilityChange = false;
        visibilityInfo.mFadeIn = false;
        if (transitionValues == null || !transitionValues.values.containsKey("android:visibility:visibility")) {
            visibilityInfo.mStartVisibility = -1;
            visibilityInfo.mStartParent = null;
        } else {
            visibilityInfo.mStartVisibility = ((Integer) transitionValues.values.get("android:visibility:visibility")).intValue();
            visibilityInfo.mStartParent = (ViewGroup) transitionValues.values.get("android:visibility:parent");
        }
        if (transitionValues2 == null || !transitionValues2.values.containsKey("android:visibility:visibility")) {
            visibilityInfo.mEndVisibility = -1;
            visibilityInfo.mEndParent = null;
        } else {
            visibilityInfo.mEndVisibility = ((Integer) transitionValues2.values.get("android:visibility:visibility")).intValue();
            visibilityInfo.mEndParent = (ViewGroup) transitionValues2.values.get("android:visibility:parent");
        }
        if (transitionValues != null && transitionValues2 != null) {
            int i2 = visibilityInfo.mStartVisibility;
            int i7 = visibilityInfo.mEndVisibility;
            if (!(i2 == i7 && visibilityInfo.mStartParent == visibilityInfo.mEndParent)) {
                if (i2 != i7) {
                    if (i2 == 0) {
                        visibilityInfo.mFadeIn = false;
                        visibilityInfo.mVisibilityChange = true;
                        return visibilityInfo;
                    } else if (i7 == 0) {
                        visibilityInfo.mFadeIn = true;
                        visibilityInfo.mVisibilityChange = true;
                        return visibilityInfo;
                    }
                } else if (visibilityInfo.mEndParent == null) {
                    visibilityInfo.mFadeIn = false;
                    visibilityInfo.mVisibilityChange = true;
                    return visibilityInfo;
                } else if (visibilityInfo.mStartParent == null) {
                    visibilityInfo.mFadeIn = true;
                    visibilityInfo.mVisibilityChange = true;
                    return visibilityInfo;
                }
            }
        } else if (transitionValues == null && visibilityInfo.mEndVisibility == 0) {
            visibilityInfo.mFadeIn = true;
            visibilityInfo.mVisibilityChange = true;
            return visibilityInfo;
        } else if (transitionValues2 == null && visibilityInfo.mStartVisibility == 0) {
            visibilityInfo.mFadeIn = false;
            visibilityInfo.mVisibilityChange = true;
        }
        return visibilityInfo;
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityChangeInfo = getVisibilityChangeInfo(transitionValues, transitionValues2);
        if (!visibilityChangeInfo.mVisibilityChange) {
            return null;
        }
        if (visibilityChangeInfo.mStartParent == null && visibilityChangeInfo.mEndParent == null) {
            return null;
        }
        if (visibilityChangeInfo.mFadeIn) {
            return onAppear(viewGroup, transitionValues, visibilityChangeInfo.mStartVisibility, transitionValues2, visibilityChangeInfo.mEndVisibility);
        }
        int i2 = visibilityChangeInfo.mStartVisibility;
        int i7 = visibilityChangeInfo.mEndVisibility;
        return onDisappear(viewGroup, transitionValues, i2, transitionValues2, i7);
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues != null && transitionValues2 != null && transitionValues2.values.containsKey("android:visibility:visibility") != transitionValues.values.containsKey("android:visibility:visibility")) {
            return false;
        }
        VisibilityInfo visibilityChangeInfo = getVisibilityChangeInfo(transitionValues, transitionValues2);
        if (!visibilityChangeInfo.mVisibilityChange || (visibilityChangeInfo.mStartVisibility != 0 && visibilityChangeInfo.mEndVisibility != 0)) {
            return false;
        }
        return true;
    }

    public abstract Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2);

    public Animator onAppear(ViewGroup viewGroup, TransitionValues transitionValues, int i2, TransitionValues transitionValues2, int i7) {
        if ((this.mMode & 1) != 1 || transitionValues2 == null) {
            return null;
        }
        if (transitionValues == null) {
            View view = (View) transitionValues2.view.getParent();
            if (getVisibilityChangeInfo(getMatchedTransitionValues(view, false), getTransitionValues(view, false)).mVisibilityChange) {
                return null;
            }
        }
        return onAppear(viewGroup, transitionValues2.view, transitionValues, transitionValues2);
    }

    public abstract Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2);

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0083, code lost:
        if (r10.mCanRemoveViews != false) goto L_0x0085;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator onDisappear(android.view.ViewGroup r11, androidx.transition.TransitionValues r12, int r13, androidx.transition.TransitionValues r14, int r15) {
        /*
            r10 = this;
            int r13 = r10.mMode
            r0 = 2
            r13 = r13 & r0
            r1 = 0
            if (r13 == r0) goto L_0x0008
            return r1
        L_0x0008:
            if (r12 != 0) goto L_0x000b
            return r1
        L_0x000b:
            android.view.View r13 = r12.view
            if (r14 == 0) goto L_0x0012
            android.view.View r2 = r14.view
            goto L_0x0013
        L_0x0012:
            r2 = r1
        L_0x0013:
            int r3 = androidx.transition.R$id.save_overlay_view
            java.lang.Object r4 = r13.getTag(r3)
            android.view.View r4 = (android.view.View) r4
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x0023
            r2 = r1
            r7 = r6
            goto L_0x0088
        L_0x0023:
            if (r2 == 0) goto L_0x003a
            android.view.ViewParent r4 = r2.getParent()
            if (r4 != 0) goto L_0x002c
            goto L_0x003a
        L_0x002c:
            r4 = 4
            if (r15 != r4) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            if (r13 != r2) goto L_0x0036
        L_0x0032:
            r4 = r2
            r7 = r5
            r2 = r1
            goto L_0x003e
        L_0x0036:
            r2 = r1
            r4 = r2
            r7 = r6
            goto L_0x003e
        L_0x003a:
            if (r2 == 0) goto L_0x0036
            r4 = r1
            r7 = r5
        L_0x003e:
            if (r7 == 0) goto L_0x0069
            android.view.ViewParent r7 = r13.getParent()
            if (r7 != 0) goto L_0x0047
            goto L_0x0085
        L_0x0047:
            android.view.ViewParent r7 = r13.getParent()
            boolean r7 = r7 instanceof android.view.View
            if (r7 == 0) goto L_0x0069
            android.view.ViewParent r7 = r13.getParent()
            android.view.View r7 = (android.view.View) r7
            androidx.transition.TransitionValues r8 = r10.getTransitionValues(r7, r6)
            androidx.transition.TransitionValues r9 = r10.getMatchedTransitionValues(r7, r6)
            androidx.transition.Visibility$VisibilityInfo r8 = r10.getVisibilityChangeInfo(r8, r9)
            boolean r8 = r8.mVisibilityChange
            if (r8 != 0) goto L_0x006e
            android.view.View r2 = androidx.transition.TransitionUtils.copyViewImage(r11, r13, r7)
        L_0x0069:
            r7 = r4
            r4 = r2
            r2 = r7
            r7 = r5
            goto L_0x0088
        L_0x006e:
            int r8 = r7.getId()
            android.view.ViewParent r7 = r7.getParent()
            if (r7 != 0) goto L_0x0069
            r7 = -1
            if (r8 == r7) goto L_0x0069
            android.view.View r7 = r11.findViewById(r8)
            if (r7 == 0) goto L_0x0069
            boolean r7 = r10.mCanRemoveViews
            if (r7 == 0) goto L_0x0069
        L_0x0085:
            r2 = r4
            r7 = r5
            r4 = r13
        L_0x0088:
            if (r4 == 0) goto L_0x00e2
            if (r7 != 0) goto L_0x00bc
            java.util.Map<java.lang.String, java.lang.Object> r15 = r12.values
            java.lang.String r1 = "android:visibility:screenLocation"
            java.lang.Object r15 = r15.get(r1)
            int[] r15 = (int[]) r15
            r1 = r15[r5]
            r15 = r15[r6]
            int[] r0 = new int[r0]
            r11.getLocationOnScreen(r0)
            r2 = r0[r5]
            int r1 = r1 - r2
            int r2 = r4.getLeft()
            int r1 = r1 - r2
            r4.offsetLeftAndRight(r1)
            r0 = r0[r6]
            int r15 = r15 - r0
            int r0 = r4.getTop()
            int r15 = r15 - r0
            r4.offsetTopAndBottom(r15)
            android.view.ViewGroupOverlay r15 = r11.getOverlay()
            r15.add(r4)
        L_0x00bc:
            android.animation.Animator r12 = r10.onDisappear(r11, r4, r12, r14)
            if (r7 != 0) goto L_0x00e1
            if (r12 != 0) goto L_0x00cc
            android.view.ViewGroupOverlay r10 = r11.getOverlay()
            r10.remove(r4)
            return r12
        L_0x00cc:
            r13.setTag(r3, r4)
            androidx.transition.Visibility$OverlayListener r14 = new androidx.transition.Visibility$OverlayListener
            r14.<init>(r11, r4, r13)
            r12.addListener(r14)
            r12.addPauseListener(r14)
            androidx.transition.Transition r10 = r10.getRootTransition()
            r10.addListener(r14)
        L_0x00e1:
            return r12
        L_0x00e2:
            if (r2 == 0) goto L_0x0105
            int r13 = r2.getVisibility()
            androidx.transition.ViewUtils.setTransitionVisibility(r2, r5)
            android.animation.Animator r11 = r10.onDisappear(r11, r2, r12, r14)
            if (r11 == 0) goto L_0x0101
            androidx.transition.Visibility$DisappearListener r12 = new androidx.transition.Visibility$DisappearListener
            r12.<init>(r2, r15, r6)
            r11.addListener(r12)
            androidx.transition.Transition r10 = r10.getRootTransition()
            r10.addListener(r12)
            return r11
        L_0x0101:
            androidx.transition.ViewUtils.setTransitionVisibility(r2, r13)
            return r11
        L_0x0105:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.onDisappear(android.view.ViewGroup, androidx.transition.TransitionValues, int, androidx.transition.TransitionValues, int):android.animation.Animator");
    }

    public void setMode(int i2) {
        if ((i2 & -4) == 0) {
            this.mMode = i2;
            return;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }
}
