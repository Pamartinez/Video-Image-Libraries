package com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand;

import Qa.a;
import Qb.c;
import U7.d;
import U7.e;
import U7.f;
import U7.g;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.remaster.ErrorReason;
import com.samsung.android.gallery.module.remaster.RemasterHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.AiProcessingEffectLayout;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterProcessingViewHandler extends ViewerObject implements FragmentLifeCycle {
    private final boolean SUPPORT_AI_VISUAL_EFFECT = Features.isEnabled(Features.SUPPORT_AI_PROCESSING_EFFECT);
    /* access modifiers changed from: private */
    public View mContainer;
    private final boolean mIsGif;
    private PhotoView mPhotoView;
    private View mProgressDimLayout;
    private ImageView mRemasterLoadingIcon;
    private TextView mRemasterProcessingText;
    private TextView mRemasterProcessingValue;
    private CoordinatorLayout mViewerLayout;

    public RemasterProcessingViewHandler(boolean z) {
        this.mIsGif = z;
    }

    private void fadeOutLoadingIcon() {
        ViewUtils.startAnimation(this.mRemasterLoadingIcon, new e(this, 0));
    }

    private int getAnalysingStringId(MediaItem mediaItem) {
        return RemasterHelper.getProcessingMessage(mediaItem);
    }

    private void hideProcessingBackgroundView() {
        updateEffect(false);
        ViewUtils.setVisibility(this.mProgressDimLayout, 8);
    }

    private void inflateProgressingView() {
        if (this.mIsGif) {
            View findViewById = this.mViewerLayout.findViewById(R.id.remaster_progressing_value_layout);
            if (findViewById instanceof ViewStub) {
                TextView textView = (TextView) ((ViewStub) findViewById).inflate();
                this.mRemasterProcessingValue = textView;
                textView.setText(StringCompat.toReadablePercentage(0));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Animation lambda$fadeOutLoadingIcon$7(final ImageView imageView) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200);
        alphaAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) imageView.getDrawable();
                animatedVectorDrawable.stop();
                animatedVectorDrawable.clearAnimationCallbacks();
                imageView.setVisibility(8);
            }
        });
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRemasterCancel$3(Object[] objArr) {
        String str;
        ErrorReason errorReason = objArr[0];
        fadeOutLoadingIcon();
        setCancelingText(errorReason);
        ViewUtils.setVisibility(this.mRemasterProcessingValue, 8);
        errorReason.showToastForErrorReason(this.mModel.getContext(), this.mModel.getMediaItem());
        Blackboard blackboard = getBlackboard();
        if (errorReason == ErrorReason.CANCEL) {
            str = "user_cancel";
        } else {
            str = "unsupported";
        }
        blackboard.post("data://bixby_command_done", new Object[]{str});
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRemasterProcessing$4(Object[] objArr) {
        ViewUtils.setText(this.mRemasterProcessingValue, StringCompat.toReadablePercentage(objArr[0].intValue()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Animation lambda$setCancelingText$8(final ErrorReason errorReason, final TextView textView) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200);
        alphaAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                textView.setText(errorReason.getErrorReasonText(RemasterProcessingViewHandler.this.mModel.getMediaItem()));
                textView.startAnimation(AnimationUtils.loadAnimation(RemasterProcessingViewHandler.this.mModel.getContext(), R.anim.remastering_transition_text_anim));
            }
        });
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Animation lambda$showBackgroundView$5(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setStartOffset(100);
        alphaAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (RemasterProcessingViewHandler.this.updateEffect(true)) {
                    RemasterProcessingViewHandler remasterProcessingViewHandler = RemasterProcessingViewHandler.this;
                    remasterProcessingViewHandler.updateViewSize(remasterProcessingViewHandler.mContainer);
                }
            }
        });
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showLoadingIcon$6(ImageView imageView) {
        imageView.setVisibility(0);
        final AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) imageView.getDrawable();
        animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
            public void onAnimationEnd(Drawable drawable) {
                animatedVectorDrawable.start();
            }
        });
        animatedVectorDrawable.start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEffect$2() {
        ((AiProcessingEffectLayout) this.mContainer).playEffect();
    }

    /* access modifiers changed from: private */
    public void onBitmapLoaded(Object... objArr) {
        if (MediaItemUtil.equals(objArr[1], this.mModel.getRemasteredMediaItem())) {
            this.mThread.runOnUiThread(new g(this, 1));
            this.mActionInvoker.invoke(ViewerAction.ON_READY_REMASTER_VIEW, new Object[0]);
        }
    }

    private void setAnalysingText() {
        MediaItem mediaItem;
        if (!(this.mRemasterProcessingText == null || (mediaItem = this.mModel.getMediaItem()) == null)) {
            ViewUtils.setText(this.mRemasterProcessingText, getAnalysingStringId(mediaItem));
            this.mRemasterProcessingText.startAnimation(AnimationUtils.loadAnimation(this.mModel.getContext(), R.anim.remastering_transition_text_anim));
        }
        TextView textView = this.mRemasterProcessingValue;
        if (textView != null) {
            textView.startAnimation(AnimationUtils.loadAnimation(this.mModel.getContext(), R.anim.remastering_transition_text_anim));
        }
    }

    private void setCancelingText(ErrorReason errorReason) {
        ViewUtils.startAnimation(this.mRemasterProcessingText, new a(1, (Object) this, (Object) errorReason));
    }

    private void showAnalysingText() {
        ViewUtils.setVisibility(this.mRemasterProcessingText, 0);
        ViewUtils.setVisibility(this.mRemasterProcessingValue, 0);
        setAnalysingText();
        if (this.SUPPORT_AI_VISUAL_EFFECT) {
            SimpleAnimator.create(this.mRemasterProcessingText, R.anim.viewer_ai_processing_text_anim, (Animation.AnimationListener) null);
        }
        updateAnalysingTextView();
    }

    private void showBackgroundView() {
        ViewUtils.startAnimation(this.mProgressDimLayout, new e(this, 1));
        ViewUtils.setVisibility(this.mProgressDimLayout, 0);
        updateViewSize(this.mContainer);
    }

    private void showLoadingIcon() {
        Optional.ofNullable(this.mRemasterLoadingIcon).ifPresent(new c(29, this));
    }

    private void updateAnalysingTextView() {
        if (ViewUtils.isVisible(this.mRemasterProcessingText)) {
            ViewGroup.LayoutParams layoutParams = this.mRemasterProcessingText.getLayoutParams();
            RectF displayRect = this.mPhotoView.getDisplayRect();
            if (displayRect != null) {
                layoutParams.width = (int) displayRect.width();
                this.mRemasterProcessingText.setLayoutParams(layoutParams);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean updateEffect(boolean z) {
        if (!this.SUPPORT_AI_VISUAL_EFFECT) {
            return false;
        }
        View view = this.mContainer;
        if (!(view instanceof AiProcessingEffectLayout)) {
            return false;
        }
        if (z) {
            ViewUtils.post(view, new g(this, 0));
            return true;
        }
        ((AiProcessingEffectLayout) view).stopEffect();
        return true;
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new f(this, 0));
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new f(this, 1));
        this.mActionInvoker.add(ViewerAction.ON_DEMAND_REMASTER_CANCEL, new f(this, 2));
        this.mActionInvoker.add(ViewerAction.ON_DEMAND_REMASTER_PROCESSING, new f(this, 3));
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new f(this, 4));
        this.mActionInvoker.add(ViewerAction.ON_REMASTER_PHOTO_POSITION_CHANGED, new f(this, 5));
    }

    public void hideLoadingView() {
        fadeOutLoadingIcon();
        hideProcessingBackgroundView();
        ViewUtils.setVisibility(this.mRemasterProcessingText, 8);
        ViewUtils.setVisibility(this.mRemasterProcessingValue, 8);
    }

    public void onInitialized() {
        this.mContainer = this.mViewerLayout.findViewById(R.id.processing_container_layout);
        this.mRemasterLoadingIcon = (ImageView) this.mViewerLayout.findViewById(R.id.remaster_loading_icon);
        this.mProgressDimLayout = this.mViewerLayout.findViewById(R.id.dim_layout);
        this.mRemasterProcessingText = (TextView) this.mViewerLayout.findViewById(R.id.remaster_processing_text);
        if (this.SUPPORT_AI_VISUAL_EFFECT) {
            View view = this.mProgressDimLayout;
            view.setBackgroundColor(view.getResources().getColor(R.color.ai_progress_bg_dim_color, (Resources.Theme) null));
        }
        inflateProgressingView();
    }

    public void onPause() {
        updateEffect(false);
    }

    public void onPhotoPositionChanged(Object... objArr) {
        updateViewSize(this.mContainer);
        updateAnalysingTextView();
    }

    public void onRemasterCancel(Object... objArr) {
        this.mThread.runOnUiThread(new d(this, objArr, 0));
    }

    public void onRemasterProcessing(Object... objArr) {
        this.mThread.runOnUiThread(new d(this, objArr, 1));
    }

    public void onResume() {
        if (ViewUtils.isVisible(this.mProgressDimLayout) && updateEffect(true)) {
            updateViewSize(this.mContainer);
        }
    }

    public void onViewAttached() {
        showLoadingView();
    }

    public void showLoadingView() {
        long j2;
        MediaItem mediaItem = this.mModel.getMediaItem();
        StringCompat stringCompat = this.TAG;
        if (mediaItem != null) {
            j2 = mediaItem.getFileId();
        } else {
            j2 = 0;
        }
        Log.d(stringCompat, "showLoadingView", Long.valueOf(j2), Long.valueOf(MediaItemSuggest.getRevitalizedType(mediaItem)));
        showBackgroundView();
        showAnalysingText();
        showLoadingIcon();
    }

    public void updateViewSize(View view) {
        if (ViewUtils.isVisible(view) && this.mPhotoView != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            RectF displayRect = this.mPhotoView.getDisplayRect();
            if (displayRect != null) {
                layoutParams.width = (int) displayRect.width();
                layoutParams.height = (int) displayRect.height();
                view.setLayoutParams(layoutParams);
            }
        }
    }
}
