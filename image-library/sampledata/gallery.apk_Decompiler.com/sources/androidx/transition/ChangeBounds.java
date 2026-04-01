package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import com.samsung.android.sum.core.message.Message;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeBounds extends Transition {
    private static final Property<View, PointF> BOTTOM_RIGHT_ONLY_PROPERTY;
    private static final Property<ViewBounds, PointF> BOTTOM_RIGHT_PROPERTY;
    private static final Property<View, PointF> POSITION_PROPERTY;
    private static final Property<View, PointF> TOP_LEFT_ONLY_PROPERTY;
    private static final Property<ViewBounds, PointF> TOP_LEFT_PROPERTY;
    private static final RectEvaluator sRectEvaluator = new RectEvaluator();
    private static final String[] sTransitionProperties = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private boolean mResizeClip = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClipListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private final int mEndBottom;
        private final Rect mEndClip;
        private final boolean mEndClipIsNull;
        private final int mEndLeft;
        private final int mEndRight;
        private final int mEndTop;
        private boolean mIsCanceled;
        private final int mStartBottom;
        private final Rect mStartClip;
        private final boolean mStartClipIsNull;
        private final int mStartLeft;
        private final int mStartRight;
        private final int mStartTop;
        private final View mView;

        public ClipListener(View view, Rect rect, boolean z, Rect rect2, boolean z3, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
            this.mView = view;
            this.mStartClip = rect;
            this.mStartClipIsNull = z;
            this.mEndClip = rect2;
            this.mEndClipIsNull = z3;
            this.mStartLeft = i2;
            this.mStartTop = i7;
            this.mStartRight = i8;
            this.mStartBottom = i10;
            this.mEndLeft = i11;
            this.mEndTop = i12;
            this.mEndRight = i13;
            this.mEndBottom = i14;
        }

        public void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }

        public void onAnimationStart(Animator animator) {
            onAnimationStart(animator, false);
        }

        public void onTransitionCancel(Transition transition) {
            this.mIsCanceled = true;
        }

        public void onTransitionPause(Transition transition) {
            Rect rect;
            this.mView.setTag(R$id.transition_clip, this.mView.getClipBounds());
            if (this.mEndClipIsNull) {
                rect = null;
            } else {
                rect = this.mEndClip;
            }
            this.mView.setClipBounds(rect);
        }

        public void onTransitionResume(Transition transition) {
            View view = this.mView;
            int i2 = R$id.transition_clip;
            this.mView.setTag(i2, (Object) null);
            this.mView.setClipBounds((Rect) view.getTag(i2));
        }

        public void onAnimationEnd(Animator animator, boolean z) {
            if (!this.mIsCanceled) {
                Rect rect = null;
                if (z) {
                    if (!this.mStartClipIsNull) {
                        rect = this.mStartClip;
                    }
                } else if (!this.mEndClipIsNull) {
                    rect = this.mEndClip;
                }
                this.mView.setClipBounds(rect);
                if (z) {
                    ViewUtils.setLeftTopRightBottom(this.mView, this.mStartLeft, this.mStartTop, this.mStartRight, this.mStartBottom);
                } else {
                    ViewUtils.setLeftTopRightBottom(this.mView, this.mEndLeft, this.mEndTop, this.mEndRight, this.mEndBottom);
                }
            }
        }

        public void onAnimationStart(Animator animator, boolean z) {
            int max = Math.max(this.mStartRight - this.mStartLeft, this.mEndRight - this.mEndLeft);
            int max2 = Math.max(this.mStartBottom - this.mStartTop, this.mEndBottom - this.mEndTop);
            int i2 = z ? this.mEndLeft : this.mStartLeft;
            int i7 = z ? this.mEndTop : this.mStartTop;
            ViewUtils.setLeftTopRightBottom(this.mView, i2, i7, max + i2, max2 + i7);
            this.mView.setClipBounds(z ? this.mEndClip : this.mStartClip);
        }

        public void onTransitionEnd(Transition transition) {
        }

        public void onTransitionStart(Transition transition) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SuppressLayoutListener extends TransitionListenerAdapter {
        boolean mCanceled = false;
        final ViewGroup mParent;

        public SuppressLayoutListener(ViewGroup viewGroup) {
            this.mParent = viewGroup;
        }

        public void onTransitionCancel(Transition transition) {
            ViewGroupUtils.suppressLayout(this.mParent, false);
            this.mCanceled = true;
        }

        public void onTransitionEnd(Transition transition) {
            if (!this.mCanceled) {
                ViewGroupUtils.suppressLayout(this.mParent, false);
            }
            transition.removeListener(this);
        }

        public void onTransitionPause(Transition transition) {
            ViewGroupUtils.suppressLayout(this.mParent, false);
        }

        public void onTransitionResume(Transition transition) {
            ViewGroupUtils.suppressLayout(this.mParent, true);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewBounds {
        private int mBottom;
        private int mBottomRightCalls;
        private int mLeft;
        private int mRight;
        private int mTop;
        private int mTopLeftCalls;
        private final View mView;

        public ViewBounds(View view) {
            this.mView = view;
        }

        private void setLeftTopRightBottom() {
            ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }

        public void setBottomRight(PointF pointF) {
            this.mRight = Math.round(pointF.x);
            this.mBottom = Math.round(pointF.y);
            int i2 = this.mBottomRightCalls + 1;
            this.mBottomRightCalls = i2;
            if (this.mTopLeftCalls == i2) {
                setLeftTopRightBottom();
            }
        }

        public void setTopLeft(PointF pointF) {
            this.mLeft = Math.round(pointF.x);
            this.mTop = Math.round(pointF.y);
            int i2 = this.mTopLeftCalls + 1;
            this.mTopLeftCalls = i2;
            if (i2 == this.mBottomRightCalls) {
                setLeftTopRightBottom();
            }
        }
    }

    static {
        Class<PointF> cls = PointF.class;
        TOP_LEFT_PROPERTY = new Property<ViewBounds, PointF>(cls, "topLeft") {
            public PointF get(ViewBounds viewBounds) {
                return null;
            }

            public void set(ViewBounds viewBounds, PointF pointF) {
                viewBounds.setTopLeft(pointF);
            }
        };
        BOTTOM_RIGHT_PROPERTY = new Property<ViewBounds, PointF>(cls, "bottomRight") {
            public PointF get(ViewBounds viewBounds) {
                return null;
            }

            public void set(ViewBounds viewBounds, PointF pointF) {
                viewBounds.setBottomRight(pointF);
            }
        };
        BOTTOM_RIGHT_ONLY_PROPERTY = new Property<View, PointF>(cls, "bottomRight") {
            public PointF get(View view) {
                return null;
            }

            public void set(View view, PointF pointF) {
                ViewUtils.setLeftTopRightBottom(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
            }
        };
        TOP_LEFT_ONLY_PROPERTY = new Property<View, PointF>(cls, "topLeft") {
            public PointF get(View view) {
                return null;
            }

            public void set(View view, PointF pointF) {
                ViewUtils.setLeftTopRightBottom(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
            }
        };
        POSITION_PROPERTY = new Property<View, PointF>(cls, Message.KEY_POSITION) {
            public PointF get(View view) {
                return null;
            }

            public void set(View view, PointF pointF) {
                int round = Math.round(pointF.x);
                int round2 = Math.round(pointF.y);
                ViewUtils.setLeftTopRightBottom(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
            }
        };
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.isLaidOut() || view.getWidth() != 0 || view.getHeight() != 0) {
            transitionValues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            transitionValues.values.put("android:changeBounds:parent", transitionValues.view.getParent());
            if (this.mResizeClip) {
                transitionValues.values.put("android:changeBounds:clip", view.getClipBounds());
            }
        }
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureStartValues(TransitionValues transitionValues) {
        Rect rect;
        captureValues(transitionValues);
        if (this.mResizeClip && (rect = (Rect) transitionValues.view.getTag(R$id.transition_clip)) != null) {
            transitionValues.values.put("android:changeBounds:clip", rect);
        }
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i2;
        Animator animator;
        int i7;
        int i8;
        int i10;
        ObjectAnimator objectAnimator;
        boolean z;
        boolean z3;
        Rect rect;
        boolean z7;
        Rect rect2;
        ObjectAnimator objectAnimator2;
        TransitionValues transitionValues3 = transitionValues;
        TransitionValues transitionValues4 = transitionValues2;
        if (transitionValues3 == null || transitionValues4 == null) {
            return null;
        }
        Map<String, Object> map = transitionValues3.values;
        Map<String, Object> map2 = transitionValues4.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view = transitionValues4.view;
        Rect rect3 = (Rect) transitionValues3.values.get("android:changeBounds:bounds");
        Rect rect4 = (Rect) transitionValues4.values.get("android:changeBounds:bounds");
        int i11 = rect3.left;
        int i12 = rect4.left;
        int i13 = rect3.top;
        int i14 = rect4.top;
        int i15 = rect3.right;
        int i16 = rect4.right;
        int i17 = rect3.bottom;
        int i18 = rect4.bottom;
        int i19 = i15 - i11;
        int i20 = i17 - i13;
        int i21 = i16 - i12;
        int i22 = i18 - i14;
        Rect rect5 = (Rect) transitionValues3.values.get("android:changeBounds:clip");
        Rect rect6 = (Rect) transitionValues4.values.get("android:changeBounds:clip");
        if ((i19 == 0 || i20 == 0) && (i21 == 0 || i22 == 0)) {
            i2 = 0;
        } else {
            if (i11 == i12 && i13 == i14) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (!(i15 == i16 && i17 == i18)) {
                i2++;
            }
        }
        if ((rect5 != null && !rect5.equals(rect6)) || (rect5 == null && rect6 != null)) {
            i2++;
        }
        int i23 = i2;
        if (i23 <= 0) {
            return null;
        }
        Rect rect7 = rect5;
        if (!this.mResizeClip) {
            ViewUtils.setLeftTopRightBottom(view, i11, i13, i15, i17);
            if (i23 == 2) {
                if (i19 == i21 && i20 == i22) {
                    animator = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, getPathMotion().getPath((float) i11, (float) i13, (float) i12, (float) i14));
                } else {
                    ViewBounds viewBounds = new ViewBounds(view);
                    ObjectAnimator ofPointF = ObjectAnimatorUtils.ofPointF(viewBounds, TOP_LEFT_PROPERTY, getPathMotion().getPath((float) i11, (float) i13, (float) i12, (float) i14));
                    ObjectAnimator ofPointF2 = ObjectAnimatorUtils.ofPointF(viewBounds, BOTTOM_RIGHT_PROPERTY, getPathMotion().getPath((float) i15, (float) i17, (float) i16, (float) i18));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(new Animator[]{ofPointF, ofPointF2});
                    animatorSet.addListener(new AnimatorListenerAdapter(viewBounds) {
                        private final ViewBounds mViewBounds;
                        final /* synthetic */ ViewBounds val$viewBounds;

                        {
                            this.val$viewBounds = r2;
                            this.mViewBounds = r2;
                        }
                    });
                    animator = animatorSet;
                }
            } else if (i11 == i12 && i13 == i14) {
                animator = ObjectAnimatorUtils.ofPointF(view, BOTTOM_RIGHT_ONLY_PROPERTY, getPathMotion().getPath((float) i15, (float) i17, (float) i16, (float) i18));
            } else {
                animator = ObjectAnimatorUtils.ofPointF(view, TOP_LEFT_ONLY_PROPERTY, getPathMotion().getPath((float) i11, (float) i13, (float) i12, (float) i14));
            }
        } else {
            int i24 = i22;
            Rect rect8 = rect6;
            ViewUtils.setLeftTopRightBottom(view, i11, i13, Math.max(i19, i21) + i11, i13 + Math.max(i20, i24));
            if (i11 == i12 && i13 == i14) {
                objectAnimator = null;
                i8 = i18;
                i7 = i12;
                i10 = i16;
            } else {
                i8 = i18;
                i10 = i16;
                i7 = i12;
                objectAnimator = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, getPathMotion().getPath((float) i11, (float) i13, (float) i12, (float) i14));
            }
            if (rect7 == null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                z3 = false;
                rect = new Rect(0, 0, i19, i20);
            } else {
                z3 = false;
                rect = rect7;
            }
            if (rect8 == null) {
                z7 = true;
            } else {
                z7 = z3;
            }
            if (z7) {
                rect2 = new Rect(z3 ? 1 : 0, z3, i21, i24);
            } else {
                rect2 = rect8;
            }
            if (!rect.equals(rect2)) {
                view.setClipBounds(rect);
                objectAnimator2 = ObjectAnimator.ofObject(view, "clipBounds", sRectEvaluator, new Object[]{rect, rect2});
                ClipListener clipListener = new ClipListener(view, rect, z, rect2, z7, i11, i13, i15, i17, i7, i14, i10, i8);
                objectAnimator2.addListener(clipListener);
                addListener(clipListener);
            } else {
                objectAnimator2 = null;
            }
            animator = TransitionUtils.mergeAnimators(objectAnimator, objectAnimator2);
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            ViewGroupUtils.suppressLayout(viewGroup4, true);
            getRootTransition().addListener(new SuppressLayoutListener(viewGroup4));
        }
        return animator;
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public boolean isSeekingSupported() {
        return true;
    }
}
