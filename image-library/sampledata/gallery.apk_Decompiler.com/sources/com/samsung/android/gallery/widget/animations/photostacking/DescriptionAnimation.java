package com.samsung.android.gallery.widget.animations.photostacking;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.widget.R$anim;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.Def;
import e5.C0451a;
import e6.C0453a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DescriptionAnimation {
    protected static final Handler mHandler = new Handler(Looper.getMainLooper());
    private final AnimatorSet mFadeInAnim;
    private final AnimatorSet mFadeOutAnim;
    private int mIndex;
    private boolean mIsPlaying;
    private final View mTextContainerView;
    private final ArrayList<TextView> mTextViews;
    private final int[] stringResIds = {R$string.stitching_story, R$string.curating_clips, R$string.finding_memorable_moments, R$string.finding_the_heart_of_your_story};

    public DescriptionAnimation(ViewGroup viewGroup) {
        ArrayList<TextView> arrayList = new ArrayList<>();
        this.mTextViews = arrayList;
        this.mIsPlaying = false;
        this.mTextContainerView = viewGroup.findViewById(R$id.description_text_layout);
        arrayList.add((TextView) viewGroup.findViewById(R$id.description_text1));
        arrayList.add((TextView) viewGroup.findViewById(R$id.description_text2));
        arrayList.forEach(new C0453a(8));
        this.mFadeInAnim = (AnimatorSet) AnimatorInflater.loadAnimator(viewGroup.getContext(), R$anim.fade_in_translation_anim);
        this.mFadeOutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(viewGroup.getContext(), R$anim.fade_out_translation_anim);
        this.mIndex = 0;
    }

    private AnimatorSet getAnimator() {
        TextView textView = this.mTextViews.get(this.mIndex % 2);
        TextView textView2 = this.mTextViews.get((this.mIndex + 1) % 2);
        if (textView == null || textView2 == null || this.mFadeInAnim == null || this.mFadeOutAnim == null) {
            return null;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        textView.setVisibility(0);
        textView.setAlpha(0.0f);
        int[] iArr = this.stringResIds;
        textView.setText(iArr[this.mIndex % iArr.length]);
        this.mFadeInAnim.setTarget(textView);
        if (textView2.getVisibility() == 0) {
            this.mFadeOutAnim.setTarget(textView2);
            animatorSet.play(this.mFadeInAnim).with(this.mFadeOutAnim);
            return animatorSet;
        }
        animatorSet.play(this.mFadeInAnim);
        return animatorSet;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playInternal$2() {
        AnimatorSet animator;
        if (this.mIsPlaying && (animator = getAnimator()) != null) {
            animator.start();
            this.mIndex++;
            playInternal();
        }
    }

    private void playInternal() {
        long j2;
        Handler handler = mHandler;
        C0451a aVar = new C0451a(13, this);
        if (this.mIndex == 0) {
            j2 = 0;
        } else {
            j2 = Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS;
        }
        handler.postDelayed(aVar, j2);
    }

    public boolean isPlaying() {
        if (!ViewUtils.isVisible(this.mTextContainerView) || !this.mIsPlaying) {
            return false;
        }
        return true;
    }

    public void play() {
        this.mTextViews.forEach(new C0453a(9));
        if (!ViewUtils.isVisible(this.mTextContainerView)) {
            this.mIsPlaying = false;
            return;
        }
        this.mIsPlaying = true;
        playInternal();
    }

    public void stop() {
        mHandler.removeCallbacksAndMessages((Object) null);
        ViewUtils.setText(this.mTextViews.get(0), this.stringResIds[0]);
        ViewUtils.setVisibility(this.mTextViews.get(1), 4);
        this.mIsPlaying = false;
        this.mIndex = 0;
    }
}
