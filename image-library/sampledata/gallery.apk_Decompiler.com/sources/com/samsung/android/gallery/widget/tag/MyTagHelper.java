package com.samsung.android.gallery.widget.tag;

import B2.h;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import bc.C0584a;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.animations.viewer.MyTagAnimation;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MyTagHelper {
    private ValueAnimator expandAnimation;
    private ObjectAnimator fadeInAnimation;
    int mCircleButtonSize;
    private View mContainerView;
    private boolean mIsPreviousTagExist;
    private RecyclerView mListView;
    private final Runnable mShowHandler = new C0584a(13, this);
    private View mTagAddButton;
    private View mTagAddButtonBg;
    private int mTagAddButtonWidth;
    private View mTagAddTextView;
    private LottieAnimationView mTagEditButton;

    private void hideTagButton() {
        setTagAddButton(8);
        resetTagEditButton();
    }

    private void initializeTagAddButtonWidth() {
        View view;
        if (this.mTagAddButtonWidth == -1 && (view = this.mTagAddTextView) != null) {
            view.measure(0, 0);
            this.mTagAddButtonWidth = this.mTagAddTextView.getMeasuredWidth();
            setTagAddButtonAnimation();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setTagAddButtonAnimation$0(ValueAnimator valueAnimator) {
        View view = this.mTagAddButtonBg;
        if (view != null) {
            view.getLayoutParams().width = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            this.mTagAddButtonBg.requestLayout();
        }
    }

    private void setTagAddButton(int i2) {
        if (this.mTagAddButton != null && this.mTagAddTextView != null) {
            this.mTagAddButtonBg.getLayoutParams().width = this.mCircleButtonSize;
            this.mTagAddButtonBg.requestLayout();
            this.mTagAddTextView.setAlpha(0.0f);
            ViewUtils.setVisibility(this.mTagAddButton, i2);
        }
    }

    private void setTagAddButtonAnimation() {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mCircleButtonSize, this.mTagAddButtonWidth});
        this.expandAnimation = ofInt;
        ofInt.setInterpolator(new PathInterpolator(0.22f, 0.24f, 0.0f, 1.0f));
        this.expandAnimation.addUpdateListener(new h(8, this));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mTagAddTextView, "alpha", new float[]{0.0f, 1.0f});
        this.fadeInAnimation = ofFloat;
        ofFloat.setStartDelay(100);
        this.fadeInAnimation.setInterpolator(new LinearInterpolator());
    }

    private void showAddTagButton() {
        if (this.mTagAddButton != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(400);
            animatorSet.playTogether(new Animator[]{this.expandAnimation, this.fadeInAnimation});
            animatorSet.start();
        }
    }

    private void showAsync() {
        if (this.mContainerView != null) {
            clearMessage();
            this.mContainerView.postDelayed(this.mShowHandler, 500);
        }
    }

    private void showEditTagButton() {
        LottieAnimationView lottieAnimationView = this.mTagEditButton;
        if (lottieAnimationView != null) {
            lottieAnimationView.setSpeed(1.0f);
            this.mTagEditButton.c();
        }
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            recyclerView.scrollToPosition(0);
            this.mListView.setAnimation(new MyTagAnimation(true));
            ViewUtils.setVisibility(this.mListView, 0);
        }
    }

    /* access modifiers changed from: private */
    public void showTag() {
        if (this.mIsPreviousTagExist) {
            showEditTagButton();
        } else {
            showAddTagButton();
        }
    }

    private void showTagButton(boolean z, boolean z3) {
        if (z) {
            if (z3) {
                this.mIsPreviousTagExist = true;
                setTagAddButton(8);
                ViewUtils.setVisibility(this.mTagEditButton, 0);
            } else {
                this.mIsPreviousTagExist = false;
                setTagAddButton(0);
                ViewUtils.setVisibility(this.mTagEditButton, 8);
                ViewUtils.setVisibility(this.mListView, 8);
            }
            showAsync();
        }
    }

    public void clearMessage() {
        View view = this.mContainerView;
        if (view != null) {
            view.removeCallbacks(this.mShowHandler);
        }
    }

    public void initTagButton(View view, RecyclerView recyclerView, View.OnClickListener onClickListener) {
        String str;
        this.mTagAddButtonWidth = -1;
        this.mIsPreviousTagExist = false;
        this.mContainerView = view;
        this.mListView = recyclerView;
        this.mCircleButtonSize = view.getResources().getDimensionPixelOffset(R$dimen.my_tag_button_size);
        this.mTagAddTextView = this.mContainerView.findViewById(R$id.tag_add_text);
        this.mTagAddButtonBg = this.mContainerView.findViewById(R$id.tag_add_button_bg);
        View findViewById = this.mContainerView.findViewById(R$id.tag_add_button);
        this.mTagAddButton = findViewById;
        findViewById.setOnClickListener(onClickListener);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) this.mContainerView.findViewById(R$id.tag_edit_button);
        this.mTagEditButton = lottieAnimationView;
        lottieAnimationView.setOnClickListener(onClickListener);
        LottieAnimationView lottieAnimationView2 = this.mTagEditButton;
        if (Features.isEnabled(Features.SUPPORT_TAG_EDITOR)) {
            str = "tag_edit.json";
        } else {
            str = "tag_icon.json";
        }
        lottieAnimationView2.setAnimation(str);
        LottieAnimationView lottieAnimationView3 = this.mTagEditButton;
        lottieAnimationView3.l = false;
        lottieAnimationView3.f1099h.j();
    }

    public boolean isTagButtonChanged(int i2) {
        if (i2 > 0 && !this.mIsPreviousTagExist) {
            return true;
        }
        if (i2 != 0 || !this.mIsPreviousTagExist) {
            return false;
        }
        return true;
    }

    public void resetTagEditButton() {
        this.mTagEditButton.a();
        this.mTagEditButton.setSpeed(1.0f);
        this.mTagEditButton.setProgress(0.0f);
        this.mListView.clearAnimation();
        ViewUtils.setVisibility(this.mListView, 8);
    }

    public void setVisibility(int i2, boolean z, boolean z3) {
        initializeTagAddButtonWidth();
        clearMessage();
        ViewUtils.setVisibility(this.mContainerView, i2);
        if (i2 == 0) {
            showTagButton(z, z3);
        } else {
            hideTagButton();
        }
    }
}
