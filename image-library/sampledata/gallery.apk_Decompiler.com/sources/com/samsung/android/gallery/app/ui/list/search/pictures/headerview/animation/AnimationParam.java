package com.samsung.android.gallery.app.ui.list.search.pictures.headerview.animation;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AnimationParam {
    private final ImageView[] mAnimImagePair;
    private final ImageView[] mFlipImageView;
    private final View mHeaderView;
    private final ImageView[] mMainImageView;
    private final Bitmap mMergedFaceBitmap;
    private final ImageView[] mTipCardThumbView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnimationParamBuilder {
        /* access modifiers changed from: private */
        public ImageView[] animImagePair;
        /* access modifiers changed from: private */
        public ImageView[] flipImageView;
        /* access modifiers changed from: private */
        public View headerView;
        /* access modifiers changed from: private */
        public ImageView[] mainImageView;
        /* access modifiers changed from: private */
        public Bitmap mergedFaceBitmap;
        /* access modifiers changed from: private */
        public ImageView[] tipCardThumbView;

        public AnimationParam build() {
            return new AnimationParam(this);
        }

        public AnimationParamBuilder setAnimImagePair(ImageView[] imageViewArr) {
            this.animImagePair = imageViewArr;
            return this;
        }

        public AnimationParamBuilder setFlipImageView(ImageView[] imageViewArr) {
            this.flipImageView = imageViewArr;
            return this;
        }

        public AnimationParamBuilder setHeaderView(View view) {
            this.headerView = view;
            return this;
        }

        public AnimationParamBuilder setMainImageView(ImageView[] imageViewArr) {
            this.mainImageView = imageViewArr;
            return this;
        }

        public AnimationParamBuilder setMergedFaceBitmap(Bitmap bitmap) {
            this.mergedFaceBitmap = bitmap;
            return this;
        }

        public AnimationParamBuilder setTipCardThumbView(ImageView[] imageViewArr) {
            this.tipCardThumbView = imageViewArr;
            return this;
        }
    }

    public AnimationParam(AnimationParamBuilder animationParamBuilder) {
        this.mAnimImagePair = animationParamBuilder.animImagePair;
        this.mFlipImageView = animationParamBuilder.flipImageView;
        this.mMergedFaceBitmap = animationParamBuilder.mergedFaceBitmap;
        this.mHeaderView = animationParamBuilder.headerView;
        this.mTipCardThumbView = animationParamBuilder.tipCardThumbView;
        this.mMainImageView = animationParamBuilder.mainImageView;
    }

    public ImageView[] getAnimImagePair() {
        return this.mAnimImagePair;
    }

    public ImageView[] getFlipImageView() {
        return this.mFlipImageView;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public ImageView[] getMainImageView() {
        return this.mMainImageView;
    }

    public Bitmap getMergedFaceBitmap() {
        return this.mMergedFaceBitmap;
    }

    public ImageView[] getTipCardThumbView() {
        return this.mTipCardThumbView;
    }
}
