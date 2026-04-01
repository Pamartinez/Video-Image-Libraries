package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture;

import android.content.res.Configuration;
import android.graphics.RectF;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import g5.c;
import java.util.Optional;
import tb.C0709a;
import v7.w;
import x7.i;
import x7.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureProcessingHandler extends ViewerObject implements FragmentLifeCycle {
    private boolean mIsVisible = false;
    private ImageView mLoadingView;
    private IMediaPlayerView mMediaPlayerView;
    private PhotoView mPhotoView;
    private float mStartRectWidth = 0.0f;
    private float mStartX = 0.0f;
    private float mStartY = 0.0f;

    private RectF getDisplayRect() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView == null || !iMediaPlayerView.isVisible()) {
            return this.mPhotoView.getDisplayRect();
        }
        return this.mMediaPlayerView.getDisplayRect();
    }

    private void hideLoadingView() {
        ViewUtils.startAnimation(this.mLoadingView, new C0709a(3, this));
        ViewUtils.setVisibility(this.mLoadingView, 8);
    }

    private boolean isValidInitValues() {
        if (this.mStartRectWidth <= 0.0f || this.mStartX <= 0.0f || this.mStartY <= 0.0f) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Animation lambda$hideLoadingView$2(ImageView imageView) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(150);
        alphaAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ObjectCaptureProcessingHandler.this.resetAnimatedVectorDrawable();
            }
        });
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onProgressEnd$3() {
        if (this.mIsVisible) {
            hideLoadingView();
            this.mIsVisible = false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onProgressStart$4(float f, float f5) {
        this.mIsVisible = true;
        showLoadingView(f, f5);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLoadingView$5() {
        RectF displayRect = getDisplayRect();
        if (displayRect != null && isValidInitValues()) {
            int dimensionPixelSize = this.mLoadingView.getResources().getDimensionPixelSize(R.dimen.object_capture_progress_icon_size);
            float width = this.mStartRectWidth / displayRect.width();
            float f = ((float) dimensionPixelSize) / 2.0f;
            this.mLoadingView.setX(((this.mStartX / width) + displayRect.left) - f);
            this.mLoadingView.setY(((this.mStartY / width) + displayRect.top) - f);
        }
    }

    /* access modifiers changed from: private */
    public void onProgressEnd(Object... objArr) {
        this.mThread.runOnUiThread(new i(this, 1));
    }

    /* access modifiers changed from: private */
    public void onProgressStart(Object... objArr) {
        this.mThread.runOnUiThread(new c(this, objArr[0].floatValue(), objArr[1].floatValue(), 3));
    }

    /* access modifiers changed from: private */
    public void onViewReady(Object... objArr) {
        ImageView imageView = (ImageView) objArr[0].findViewById(R.id.loading_icon);
        this.mLoadingView = imageView;
        ViewUtils.setVisibility(imageView, 8);
    }

    /* access modifiers changed from: private */
    public void resetAnimatedVectorDrawable() {
        ImageView imageView = this.mLoadingView;
        if (imageView != null) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) imageView.getDrawable();
            animatedVectorDrawable.stop();
            animatedVectorDrawable.clearAnimationCallbacks();
            this.mLoadingView.setVisibility(8);
        }
    }

    private void resetLoadingView() {
        this.mIsVisible = false;
        resetAnimatedVectorDrawable();
        Optional.ofNullable(this.mLoadingView).ifPresent(new w(13));
    }

    private void setStartPosition(float f, float f5) {
        RectF displayRect = getDisplayRect();
        if (displayRect != null) {
            this.mStartX = f - displayRect.left;
            this.mStartY = f5 - displayRect.top;
            this.mStartRectWidth = displayRect.width();
        }
    }

    private void showLoadingView(float f, float f5) {
        if (this.mLoadingView != null) {
            setStartPosition(f, f5);
            updateLoadingView();
            this.mLoadingView.setVisibility(0);
            final AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) this.mLoadingView.getDrawable();
            animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                public void onAnimationEnd(Drawable drawable) {
                    animatedVectorDrawable.start();
                }
            });
            animatedVectorDrawable.start();
        }
    }

    private void updateLoadingView() {
        this.mLoadingView.post(new i(this, 0));
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new j(this, 0));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_PROGRESS_END, new j(this, 1));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_PROGRESS_START, new j(this, 2));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_VIEW, new j(this, 3));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new j(this, 4));
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (ViewUtils.isVisible(this.mLoadingView)) {
            updateLoadingView();
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        resetLoadingView();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        resetLoadingView();
        this.mStartRectWidth = 0.0f;
        this.mStartX = 0.0f;
        this.mStartY = 0.0f;
    }
}
