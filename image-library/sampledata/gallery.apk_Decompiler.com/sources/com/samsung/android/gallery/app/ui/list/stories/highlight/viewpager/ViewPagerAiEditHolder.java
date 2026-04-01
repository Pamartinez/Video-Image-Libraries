package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Supplier;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerAiEditHolder extends ViewPagerHolder {
    private ImageView mAiEditImage;
    private Supplier<Integer> mCurrentPosition;
    private final Runnable mShowAnimRunnable = new e(28, this);
    /* access modifiers changed from: private */
    public Animator mShowAnimator;

    public ViewPagerAiEditHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public void cancelShowAiEditAnim() {
        Animator animator = this.mShowAnimator;
        if (animator != null) {
            this.mShowAnimator = null;
            animator.cancel();
        }
        this.mAiEditImage.removeCallbacks(this.mShowAnimRunnable);
        ViewUtils.setAlpha(this.mAiEditImage, 0.0f);
    }

    /* access modifiers changed from: private */
    public void fadeIn() {
        Supplier<Integer> supplier;
        if (ViewUtils.getAlpha(this.mAiEditImage) == 0.0f && this.mAiEditImage.getDrawable() != null && (supplier = this.mCurrentPosition) != null && supplier.get().intValue() == getAbsoluteAdapterPosition()) {
            showAiEditAnim();
            Log.d(this.TAG, "fadeIn", this.mCurrentPosition.get());
        }
    }

    private void showAiEditAnim() {
        AlphaAnimator alphaAnimator = new AlphaAnimator((View) this.mAiEditImage, 0.0f, 1.0f);
        this.mShowAnimator = alphaAnimator;
        alphaAnimator.setDuration(1000);
        this.mShowAnimator.setStartDelay(500);
        this.mShowAnimator.addListener(new SimpleAnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                ViewPagerAiEditHolder.this.cancelShowAiEditAnim();
            }

            public void onAnimationEnd(Animator animator) {
                ViewPagerAiEditHolder.this.mShowAnimator = null;
            }
        });
        this.mShowAnimator.start();
    }

    public void bind(MediaItem mediaItem) {
        cancelShowAiEditAnim();
        super.bind(mediaItem);
    }

    public void bindView(View view) {
        super.bindView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.ai_edit_thumbnail);
        this.mAiEditImage = imageView;
        ViewUtils.setVisibility(imageView, 0);
        ViewUtils.setAlpha(this.mAiEditImage, 0.0f);
    }

    public void onAiEditImageLoaded(MediaItem mediaItem, Bitmap bitmap) {
        if (mediaItem == this.mMediaItem) {
            this.mAiEditImage.setImageBitmap(bitmap);
            setViewMatrix(this.mAiEditImage);
            this.mAiEditImage.removeCallbacks(this.mShowAnimRunnable);
            this.mAiEditImage.postDelayed(this.mShowAnimRunnable, 100);
        }
    }

    public void onViewIn() {
        super.onViewIn();
        fadeIn();
    }

    public void onViewOut() {
        cancelShowAiEditAnim();
    }

    public void recycle() {
        super.recycle();
        cancelShowAiEditAnim();
        this.mAiEditImage.setImageBitmap((Bitmap) null);
        this.mCurrentPosition = null;
    }

    public void setPositionSupplier(Supplier<Integer> supplier) {
        this.mCurrentPosition = supplier;
    }
}
