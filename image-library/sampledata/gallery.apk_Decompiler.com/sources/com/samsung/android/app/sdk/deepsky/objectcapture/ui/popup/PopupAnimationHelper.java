package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import com.samsung.android.sdk.scs.base.StatusCodes;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 J2\u00020\u0001:\u0001JB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ'\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J7\u0010 \u001a\u00020\u001f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u001f¢\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\u001f¢\u0006\u0004\b$\u0010#J\r\u0010%\u001a\u00020\u001f¢\u0006\u0004\b%\u0010#J\r\u0010&\u001a\u00020\u0013¢\u0006\u0004\b&\u0010\u0015J\r\u0010'\u001a\u00020\u001f¢\u0006\u0004\b'\u0010#JM\u00103\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u00062\u0006\u0010/\u001a\u00020.2\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u00020\u0013¢\u0006\u0004\b3\u00104JM\u00105\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u00062\u0006\u0010/\u001a\u00020.2\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u00020\u0013¢\u0006\u0004\b5\u00104J\u0015\u00107\u001a\u00020\u001f2\u0006\u00106\u001a\u00020\u000b¢\u0006\u0004\b7\u00108R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00109R\u0018\u0010;\u001a\u0004\u0018\u00010:8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0018\u0010=\u001a\u0004\u0018\u00010:8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u0010<R\u0018\u0010>\u001a\u0004\u0018\u00010:8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b>\u0010<R\u0018\u0010?\u001a\u0004\u0018\u00010:8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b?\u0010<R\u0018\u0010@\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0018\u0010B\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bB\u0010AR\u0018\u0010C\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bC\u0010AR\u0018\u0010E\u001a\u0004\u0018\u00010D8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0018\u0010G\u001a\u0004\u0018\u00010D8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bG\u0010FR\u0016\u0010H\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bH\u0010I¨\u0006K"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupAnimationHelper;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/view/View;", "view", "Landroid/animation/AnimatorSet;", "createEnterAnimation", "(Landroid/view/View;)Landroid/animation/AnimatorSet;", "", "startDelay", "Landroid/animation/Animator$AnimatorListener;", "listener", "createExitAnimation", "(Landroid/view/View;ILandroid/animation/Animator$AnimatorListener;)Landroid/animation/AnimatorSet;", "getAdjustedDuration", "()I", "", "isInRTLMode", "()Z", "Landroid/view/animation/Animation$AnimationListener;", "overflowListener", "Landroid/view/ViewGroup;", "contentContainer", "Landroid/widget/PopupWindow;", "popupWindow", "parentRoot", "Landroid/view/View$OnAttachStateChangeListener;", "anchorDetachedListener", "Lme/x;", "initAnimation", "(Landroid/view/animation/Animation$AnimationListener;Landroid/view/ViewGroup;Landroid/widget/PopupWindow;Landroid/view/View;Landroid/view/View$OnAttachStateChangeListener;)V", "runDismissAnimation", "()V", "runShowAnimation", "runHideAnimation", "isOverflowAnimating", "cancelAnimation", "Landroid/util/Size;", "mMainPanelSize", "mOverflowPanelSize", "mContentContainer", "mIsClosedOpposites", "mMainPanel", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/OverflowPanel;", "mOverflowPanel", "Landroid/widget/ImageButton;", "mOverflowButton", "mOpenOverflowUpwards", "openOverflowAnimation", "(Landroid/util/Size;Landroid/util/Size;Landroid/view/View;ZLandroid/view/View;Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/OverflowPanel;Landroid/widget/ImageButton;Z)V", "closeOverflowAnimation", "value", "setTransitionDurationScale", "(I)V", "Landroid/content/Context;", "Landroid/view/animation/Interpolator;", "mLogAccelerateInterpolator", "Landroid/view/animation/Interpolator;", "mFastOutSlowInInterpolator", "mLinearOutSlowInInterpolator", "mFastOutLinearInInterpolator", "mShowAnimation", "Landroid/animation/AnimatorSet;", "mDismissAnimation", "mHideAnimation", "Landroid/view/animation/AnimationSet;", "mOpenOverflowAnimation", "Landroid/view/animation/AnimationSet;", "mCloseOverflowAnimation", "mTransitionDurationScale", "I", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PopupAnimationHelper {
    public static final Companion Companion = new Companion((e) null);
    private static final int TRANSITION_DEFAULT_DURATION = 250;
    private final Context context;
    private AnimationSet mCloseOverflowAnimation;
    private AnimatorSet mDismissAnimation;
    public Interpolator mFastOutLinearInInterpolator;
    private Interpolator mFastOutSlowInInterpolator;
    private AnimatorSet mHideAnimation;
    public Interpolator mLinearOutSlowInInterpolator;
    private Interpolator mLogAccelerateInterpolator;
    private AnimationSet mOpenOverflowAnimation;
    private AnimatorSet mShowAnimation;
    private int mTransitionDurationScale;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupAnimationHelper$Companion;", "", "<init>", "()V", "TRANSITION_DEFAULT_DURATION", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public PopupAnimationHelper(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final AnimatorSet createEnterAnimation(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 1.0f}).setDuration(150)});
        return animatorSet;
    }

    private final AnimatorSet createExitAnimation(View view, int i2, Animator.AnimatorListener animatorListener) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(100)});
        animatorSet.setStartDelay((long) i2);
        animatorSet.addListener(animatorListener);
        return animatorSet;
    }

    private final int getAdjustedDuration() {
        int i2 = this.mTransitionDurationScale;
        if (i2 < 150) {
            return 200;
        }
        if (i2 > 300) {
            return StatusCodes.INPUT_MISSING;
        }
        return TRANSITION_DEFAULT_DURATION;
    }

    /* access modifiers changed from: private */
    public final boolean isInRTLMode() {
        if (this.context.getResources().getConfiguration().getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public final void cancelAnimation() {
        AnimatorSet animatorSet = this.mHideAnimation;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.mDismissAnimation;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
    }

    public final void closeOverflowAnimation(Size size, Size size2, View view, boolean z, View view2, OverflowPanel overflowPanel, ImageButton imageButton, boolean z3) {
        float width;
        View view3 = view;
        Size size3 = size;
        j.e(size3, "mMainPanelSize");
        Size size4 = size2;
        j.e(size4, "mOverflowPanelSize");
        j.e(view3, "mContentContainer");
        View view4 = view2;
        j.e(view4, "mMainPanel");
        OverflowPanel overflowPanel2 = overflowPanel;
        j.e(overflowPanel2, "mOverflowPanel");
        ImageButton imageButton2 = imageButton;
        j.e(imageButton2, "mOverflowButton");
        int width2 = size3.getWidth();
        int width3 = view3.getWidth();
        float x9 = view3.getX();
        PopupAnimationHelper$closeOverflowAnimation$widthAnimation$1 popupAnimationHelper$closeOverflowAnimation$widthAnimation$1 = new PopupAnimationHelper$closeOverflowAnimation$widthAnimation$1(width2, width3, view3, x9 + ((float) view3.getWidth()), this, z, x9, view4, overflowPanel2);
        int i2 = width3;
        boolean z7 = z3;
        PopupAnimationHelper$closeOverflowAnimation$heightAnimation$1 popupAnimationHelper$closeOverflowAnimation$heightAnimation$1 = new PopupAnimationHelper$closeOverflowAnimation$heightAnimation$1(size3.getHeight(), view.getHeight(), view, z7, view.getY() + ((float) view.getHeight()), view2, size3, imageButton2, overflowPanel2, size4);
        float x10 = imageButton.getX();
        if (isInRTLMode()) {
            width = (x10 - ((float) i2)) + ((float) imageButton.getWidth());
        } else {
            width = (((float) i2) + x10) - ((float) imageButton.getWidth());
        }
        View view5 = view;
        PopupAnimationHelper$closeOverflowAnimation$overflowButtonAnimation$1 popupAnimationHelper$closeOverflowAnimation$overflowButtonAnimation$1 = new PopupAnimationHelper$closeOverflowAnimation$overflowButtonAnimation$1(x10, width, this, view5, i2, imageButton);
        popupAnimationHelper$closeOverflowAnimation$widthAnimation$1.setInterpolator(this.mFastOutSlowInInterpolator);
        popupAnimationHelper$closeOverflowAnimation$widthAnimation$1.setDuration((long) getAdjustedDuration());
        popupAnimationHelper$closeOverflowAnimation$heightAnimation$1.setInterpolator(this.mLogAccelerateInterpolator);
        popupAnimationHelper$closeOverflowAnimation$heightAnimation$1.setDuration((long) getAdjustedDuration());
        popupAnimationHelper$closeOverflowAnimation$overflowButtonAnimation$1.setInterpolator(this.mFastOutSlowInInterpolator);
        popupAnimationHelper$closeOverflowAnimation$overflowButtonAnimation$1.setDuration((long) getAdjustedDuration());
        AnimationSet animationSet = this.mCloseOverflowAnimation;
        if (animationSet != null) {
            animationSet.getAnimations().clear();
            animationSet.addAnimation(popupAnimationHelper$closeOverflowAnimation$widthAnimation$1);
            animationSet.addAnimation(popupAnimationHelper$closeOverflowAnimation$heightAnimation$1);
            animationSet.addAnimation(popupAnimationHelper$closeOverflowAnimation$overflowButtonAnimation$1);
        }
        view5.startAnimation(this.mCloseOverflowAnimation);
        view2.animate().alpha(1.0f).withLayer().setInterpolator(this.mFastOutLinearInInterpolator).setDuration(100).start();
        overflowPanel.animate().alpha(0.0f).withLayer().setInterpolator(this.mLinearOutSlowInInterpolator).setDuration(150).start();
    }

    public final void initAnimation(Animation.AnimationListener animationListener, ViewGroup viewGroup, PopupWindow popupWindow, View view, View.OnAttachStateChangeListener onAttachStateChangeListener) {
        j.e(animationListener, "overflowListener");
        j.e(viewGroup, "contentContainer");
        j.e(popupWindow, "popupWindow");
        j.e(onAttachStateChangeListener, "anchorDetachedListener");
        this.mLogAccelerateInterpolator = new LogAccelerateInterpolator();
        this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(this.context, 17563661);
        this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(this.context, 17563662);
        this.mFastOutLinearInInterpolator = AnimationUtils.loadInterpolator(this.context, 17563663);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setAnimationListener(animationListener);
        this.mOpenOverflowAnimation = animationSet;
        AnimationSet animationSet2 = new AnimationSet(true);
        animationSet2.setAnimationListener(animationListener);
        this.mCloseOverflowAnimation = animationSet2;
        this.mShowAnimation = createEnterAnimation(viewGroup);
        this.mDismissAnimation = createExitAnimation(viewGroup, 150, new PopupAnimationHelper$initAnimation$3(popupWindow, viewGroup, view, onAttachStateChangeListener));
        this.mHideAnimation = createExitAnimation(viewGroup, 0, new PopupAnimationHelper$initAnimation$4(popupWindow));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0038 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isOverflowAnimating() {
        /*
            r4 = this;
            android.view.animation.AnimationSet r0 = r4.mOpenOverflowAnimation
            r1 = 0
            if (r0 == 0) goto L_0x000a
            boolean r0 = r0.hasStarted()
            goto L_0x000b
        L_0x000a:
            r0 = r1
        L_0x000b:
            r2 = 1
            if (r0 == 0) goto L_0x001b
            android.view.animation.AnimationSet r0 = r4.mOpenOverflowAnimation
            kotlin.jvm.internal.j.b(r0)
            boolean r0 = r0.hasEnded()
            if (r0 != 0) goto L_0x001b
            r0 = r2
            goto L_0x001c
        L_0x001b:
            r0 = r1
        L_0x001c:
            android.view.animation.AnimationSet r3 = r4.mCloseOverflowAnimation
            if (r3 == 0) goto L_0x0025
            boolean r3 = r3.hasStarted()
            goto L_0x0026
        L_0x0025:
            r3 = r1
        L_0x0026:
            if (r3 == 0) goto L_0x0035
            android.view.animation.AnimationSet r4 = r4.mCloseOverflowAnimation
            kotlin.jvm.internal.j.b(r4)
            boolean r4 = r4.hasEnded()
            if (r4 != 0) goto L_0x0035
            r4 = r2
            goto L_0x0036
        L_0x0035:
            r4 = r1
        L_0x0036:
            if (r0 != 0) goto L_0x003c
            if (r4 == 0) goto L_0x003b
            goto L_0x003c
        L_0x003b:
            return r1
        L_0x003c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.PopupAnimationHelper.isOverflowAnimating():boolean");
    }

    public final void openOverflowAnimation(Size size, Size size2, View view, boolean z, View view2, OverflowPanel overflowPanel, ImageButton imageButton, boolean z3) {
        float width;
        View view3 = view;
        OverflowPanel overflowPanel2 = overflowPanel;
        Size size3 = size;
        j.e(size3, "mMainPanelSize");
        Size size4 = size2;
        j.e(size4, "mOverflowPanelSize");
        j.e(view3, "mContentContainer");
        View view4 = view2;
        j.e(view4, "mMainPanel");
        j.e(overflowPanel2, "mOverflowPanel");
        ImageButton imageButton2 = imageButton;
        j.e(imageButton2, "mOverflowButton");
        int width2 = size4.getWidth();
        int height = size4.getHeight();
        int width3 = view3.getWidth();
        int height2 = view3.getHeight();
        float y = view3.getY();
        float x9 = view3.getX();
        PopupAnimationHelper$openOverflowAnimation$widthAnimation$1 popupAnimationHelper$openOverflowAnimation$widthAnimation$1 = new PopupAnimationHelper$openOverflowAnimation$widthAnimation$1(width2, width3, view3, this, z, x9, x9 + ((float) view3.getWidth()), view4, overflowPanel2);
        int i2 = width3;
        Size size5 = size3;
        Size size6 = size4;
        ImageButton imageButton3 = imageButton2;
        PopupAnimationHelper$openOverflowAnimation$widthAnimation$1 popupAnimationHelper$openOverflowAnimation$widthAnimation$12 = popupAnimationHelper$openOverflowAnimation$widthAnimation$1;
        int i7 = width2;
        int i8 = height;
        int i10 = height2;
        PopupAnimationHelper$openOverflowAnimation$heightAnimation$1 popupAnimationHelper$openOverflowAnimation$heightAnimation$1 = new PopupAnimationHelper$openOverflowAnimation$heightAnimation$1(i8, i10, view3, z3, y, view2, size5, imageButton3, overflowPanel2, size6);
        float x10 = imageButton.getX();
        if (isInRTLMode()) {
            width = (((float) i7) + x10) - ((float) imageButton.getWidth());
        } else {
            width = (x10 - ((float) i7)) + ((float) imageButton.getWidth());
        }
        View view5 = view;
        PopupAnimationHelper$openOverflowAnimation$overflowButtonAnimation$1 popupAnimationHelper$openOverflowAnimation$overflowButtonAnimation$1 = new PopupAnimationHelper$openOverflowAnimation$overflowButtonAnimation$1(x10, width, this, view5, i2, imageButton);
        popupAnimationHelper$openOverflowAnimation$widthAnimation$12.setInterpolator(this.mLogAccelerateInterpolator);
        popupAnimationHelper$openOverflowAnimation$widthAnimation$12.setDuration((long) getAdjustedDuration());
        popupAnimationHelper$openOverflowAnimation$heightAnimation$1.setInterpolator(this.mFastOutSlowInInterpolator);
        popupAnimationHelper$openOverflowAnimation$heightAnimation$1.setDuration((long) getAdjustedDuration());
        popupAnimationHelper$openOverflowAnimation$overflowButtonAnimation$1.setInterpolator(this.mFastOutSlowInInterpolator);
        popupAnimationHelper$openOverflowAnimation$overflowButtonAnimation$1.setDuration((long) getAdjustedDuration());
        AnimationSet animationSet = this.mOpenOverflowAnimation;
        if (animationSet != null) {
            animationSet.getAnimations().clear();
            animationSet.addAnimation(popupAnimationHelper$openOverflowAnimation$widthAnimation$12);
            animationSet.addAnimation(popupAnimationHelper$openOverflowAnimation$heightAnimation$1);
            animationSet.addAnimation(popupAnimationHelper$openOverflowAnimation$overflowButtonAnimation$1);
        }
        view5.startAnimation(this.mOpenOverflowAnimation);
        view2.animate().alpha(0.0f).withLayer().setInterpolator(this.mLinearOutSlowInInterpolator).setDuration(250).start();
        overflowPanel2.setAlpha(1.0f);
    }

    public final void runDismissAnimation() {
        AnimatorSet animatorSet = this.mHideAnimation;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.mDismissAnimation;
        if (animatorSet2 != null) {
            animatorSet2.start();
        }
    }

    public final void runHideAnimation() {
        AnimatorSet animatorSet = this.mHideAnimation;
        if (animatorSet != null) {
            animatorSet.start();
        }
    }

    public final void runShowAnimation() {
        AnimatorSet animatorSet = this.mDismissAnimation;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.mHideAnimation;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        AnimatorSet animatorSet3 = this.mShowAnimation;
        if (animatorSet3 != null) {
            animatorSet3.start();
        }
    }

    public final void setTransitionDurationScale(int i2) {
        this.mTransitionDurationScale = i2;
    }
}
