package com.samsung.android.gallery.widget.animations;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animator.ImageMatrixAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SimpleHighlightShrinkHandler extends SimpleBackShrinkHandler {
    private int[] mRadius;
    private int retryShrink = 0;

    public SimpleHighlightShrinkHandler(SimpleShrinkView simpleShrinkView, ListViewHolder listViewHolder) {
        super(simpleShrinkView, listViewHolder);
        this.mDuration = 400;
    }

    private void addGradientBlurAnimator(ArrayList<Animator> arrayList, View view) {
        if (PreferenceFeatures.OneUi8x.STORY_COVER_BLUR && view != null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.addUpdateListener(new n(0, view));
            arrayList.add(ofFloat);
        }
    }

    private boolean equalSize(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap.getWidth() == bitmap2.getWidth() && bitmap.getHeight() == bitmap2.getHeight()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createAnimatorSet$0(ImageView imageView, ListViewHolder listViewHolder) {
        updateBitmapIfNeeded(listViewHolder, imageView);
        listViewHolder.updateMatrix();
    }

    private void updateBitmapIfNeeded(ListViewHolder listViewHolder, ImageView imageView) {
        Bitmap bitmap;
        if (MediaItemUtil.equals(this.mView.mMediaItem, listViewHolder.getMediaItem())) {
            Bitmap bitmap2 = this.mView.mTransitionInfo.bitmap;
            if (imageView.getDrawable() instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            } else {
                bitmap = null;
            }
            if (bitmap2 != null && bitmap != null && !equalSize(bitmap2, bitmap)) {
                listViewHolder.bindImage(bitmap2);
                String str = this.TAG;
                Log.d(str, "updateBitmap to VH(" + listViewHolder.getAbsoluteAdapterPosition() + ") " + Logger.toSimpleString(bitmap) + " -> " + Logger.toSimpleString(bitmap2));
            }
        }
    }

    public ArrayList<Animator> createAnimatorSet(View view, View view2) {
        ArrayList<Animator> createAnimatorSet = super.createAnimatorSet(view, view2);
        if ((view2 instanceof ImageView) && this.mView.usePresetMatrix()) {
            ImageView imageView = (ImageView) view2;
            if (imageView.getDrawable() == null) {
                Log.w(this.TAG, "matrix animator fail : no drawable");
                this.mView.mTransitionInfo.presetMatrix = null;
                return createAnimatorSet;
            }
            Optional.ofNullable(this.mViewHolder).ifPresent(new m(this, imageView));
            createAnimatorSet.add(new ImageMatrixAnimator((ImageView) view, this.mView.mTransitionInfo.presetMatrix, imageView.getImageMatrix()));
            if (!MediaItemStory.isTransitory(this.mView.mMediaItem)) {
                addGradientBlurAnimator(createAnimatorSet, view);
            }
        }
        return createAnimatorSet;
    }

    public PropertyAnimator createScaleAnimator(View view, RectF rectF, RectF rectF2) {
        int[] iArr = this.mRadius;
        if (iArr == null || iArr.length < 2) {
            return super.createScaleAnimator(view, rectF, rectF2);
        }
        ScaleAnimator enableUpdateLayoutParam = new ScaleAnimator(view, rectF, rectF2).pivotX(((float) view.getWidth()) / 2.0f).pivotY(((float) view.getHeight()) / 2.0f).enableUpdateLayoutParam(true);
        int[] iArr2 = this.mRadius;
        return enableUpdateLayoutParam.setOutlineProvider((float) iArr2[0], (float) iArr2[1]);
    }

    public SimpleHighlightShrinkHandler setRadius(int[] iArr) {
        this.mRadius = iArr;
        return this;
    }

    public void startShrinkAnimation() {
        ImageView imageView;
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder != null) {
            imageView = listViewHolder.getImage();
        } else {
            imageView = null;
        }
        if (imageView == null || imageView.getDrawable() != null) {
            super.startShrinkAnimation();
        } else {
            int i2 = this.retryShrink + 1;
            this.retryShrink = i2;
            if (i2 < 10) {
                ThreadUtil.postOnUiThreadDelayed(new c(3, this), 50);
            } else {
                super.startShrinkAnimation();
            }
        }
        String str = this.TAG;
        Log.d(str, "startShrinkAnimation", "retry=" + this.retryShrink);
    }
}
