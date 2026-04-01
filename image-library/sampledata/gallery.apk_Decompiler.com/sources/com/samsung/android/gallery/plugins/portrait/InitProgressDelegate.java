package com.samsung.android.gallery.plugins.portrait;

import A5.a;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.plugins.R$anim;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.AiProcessingEffectLayout;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.dialog.ProgressAvdCompat;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.utils.AnimatedVectorDrawableUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InitProgressDelegate {
    private final boolean SUPPORT_AI_PROCESSING = Features.isEnabled(Features.SUPPORT_AI_PROCESSING_EFFECT);
    private final String TAG = "InitProgressDelegate";
    /* access modifiers changed from: private */
    public View mAiProgressLayout;
    private final Context mContext;
    private final View mParentView;
    private final PhotoPreView mPreview;
    private ProgressAvdCompat mProgressDialog;

    public InitProgressDelegate(Context context, View view) {
        this.mContext = context;
        this.mParentView = view;
        this.mPreview = (PhotoPreView) view.findViewById(R$id.preview);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissInitProgress$1() {
        if (!this.SUPPORT_AI_PROCESSING) {
            ProgressAvdCompat progressAvdCompat = this.mProgressDialog;
            if (progressAvdCompat != null) {
                progressAvdCompat.dismiss();
                this.mProgressDialog = null;
            }
        } else if (this.mAiProgressLayout != null) {
            updateEffect(false);
            this.mAiProgressLayout.setVisibility(8);
            this.mAiProgressLayout = null;
        } else {
            Log.e("InitProgressDelegate", "dismiss ai progress failed");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Animation lambda$showDimAnimation$2(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_33));
        alphaAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (ViewUtils.isVisible(InitProgressDelegate.this.mAiProgressLayout)) {
                    InitProgressDelegate.this.updateEffect(true);
                }
            }
        });
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showInitProgress$0() {
        if (this.SUPPORT_AI_PROCESSING) {
            showAiProgress();
        } else {
            showProgressDialog();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEffectViewSize$3() {
        if (ViewUtils.isVisible(this.mAiProgressLayout)) {
            ViewGroup.LayoutParams layoutParams = this.mAiProgressLayout.getLayoutParams();
            layoutParams.width = this.mPreview.getMeasuredWidth();
            layoutParams.height = this.mPreview.getMeasuredHeight();
            this.mAiProgressLayout.setLayoutParams(layoutParams);
        }
    }

    private void showAiProgress() {
        if (this.mAiProgressLayout == null) {
            this.mAiProgressLayout = this.mParentView.findViewById(R$id.processing_effect_layout);
        }
        this.mAiProgressLayout.setVisibility(0);
        updateEffectViewText(R$string.adding_live_effect);
        AnimatedVectorDrawableUtils.play((ImageView) this.mAiProgressLayout.findViewById(R$id.effect_avd), true);
        updateEffectViewSize();
        showDimAnimation();
    }

    private void showDimAnimation() {
        if (ViewUtils.isVisible(this.mAiProgressLayout)) {
            ViewUtils.startAnimation(this.mAiProgressLayout.findViewById(R$id.dim_layout), new a(5, this));
        }
    }

    private void showProgressDialog() {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressAvdCompat(this.mContext).setProgressMessage(R$string.adding_live_effect).build();
        }
        this.mProgressDialog.show();
    }

    /* access modifiers changed from: private */
    public void updateEffect(boolean z) {
        View view;
        if (this.SUPPORT_AI_PROCESSING && (view = this.mAiProgressLayout) != null) {
            AiProcessingEffectLayout aiProcessingEffectLayout = (AiProcessingEffectLayout) view.findViewById(R$id.effect_view);
            if (z) {
                aiProcessingEffectLayout.playEffect();
            } else {
                aiProcessingEffectLayout.stopEffect();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateEffectViewSize() {
        if (this.SUPPORT_AI_PROCESSING) {
            ViewUtils.post(this.mPreview, new Da.a(this, 2));
        }
    }

    private void updateEffectViewText(int i2) {
        if (this.SUPPORT_AI_PROCESSING && ViewUtils.isVisible(this.mAiProgressLayout)) {
            TextView textView = (TextView) this.mAiProgressLayout.findViewById(R$id.processing_text);
            if (i2 > 0) {
                ViewUtils.setText(textView, i2);
                ViewUtils.setVisibility(textView, 0);
                SimpleAnimator.create(textView, R$anim.viewer_ai_processing_text_anim, (Animation.AnimationListener) null);
                return;
            }
            ViewUtils.setVisibility(textView, 8);
        }
    }

    public void dismissInitProgress() {
        ThreadUtil.runOnUiThread(new Da.a(this, 1));
    }

    public boolean isProcessing() {
        if (this.SUPPORT_AI_PROCESSING) {
            return ViewUtils.isVisible(this.mAiProgressLayout);
        }
        ProgressAvdCompat progressAvdCompat = this.mProgressDialog;
        if (progressAvdCompat == null || !progressAvdCompat.isShowing()) {
            return false;
        }
        return true;
    }

    public void onCancel() {
        if (this.SUPPORT_AI_PROCESSING) {
            updateEffectViewText(R$string.canceling_live_effect);
            return;
        }
        ProgressAvdCompat progressAvdCompat = this.mProgressDialog;
        if (progressAvdCompat != null && this.mContext != null) {
            progressAvdCompat.updateMessage(R$string.canceling_live_effect);
        }
    }

    public void onLayoutUpdated() {
        if (this.SUPPORT_AI_PROCESSING && this.mPreview != null && ViewUtils.isVisible(this.mAiProgressLayout)) {
            final ViewTreeObserver viewTreeObserver = this.mPreview.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    InitProgressDelegate.this.updateEffectViewSize();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }
    }

    public void pauseProcessing() {
        if (isProcessing()) {
            updateEffect(false);
        }
    }

    public void resumeProcessing() {
        if (isProcessing()) {
            updateEffect(true);
        }
    }

    public void showInitProgress() {
        ThreadUtil.runOnUiThread(new Da.a(this, 0));
    }
}
