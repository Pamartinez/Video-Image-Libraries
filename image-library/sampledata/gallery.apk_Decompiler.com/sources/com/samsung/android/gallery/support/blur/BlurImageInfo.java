package com.samsung.android.gallery.support.blur;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BlurImageInfo {
    private final boolean mIsCustomCover;
    private final boolean mIsEmptyAlbum;
    private final boolean mIsFolder;
    private final boolean mIsGridType;
    private final boolean mIsListToGrid;
    private final boolean mIsMaxDepth;
    private final boolean mIsReverseOp;
    private final boolean mIsSkip;
    private final View mTargetView;
    private final int mTargetViewHeight;
    private final boolean mUseEmptyCoverBlur;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public boolean mIsCustomCover = false;
        /* access modifiers changed from: private */
        public boolean mIsEmptyAlbum = false;
        /* access modifiers changed from: private */
        public boolean mIsFolder = false;
        /* access modifiers changed from: private */
        public boolean mIsGridType = false;
        /* access modifiers changed from: private */
        public boolean mIsListToGrid = false;
        /* access modifiers changed from: private */
        public boolean mIsMaxDepth = false;
        /* access modifiers changed from: private */
        public boolean mIsReverseOp = false;
        /* access modifiers changed from: private */
        public boolean mIsSkip = false;
        /* access modifiers changed from: private */
        public View mTargetView;
        /* access modifiers changed from: private */
        public int mTargetViewHeight = 0;
        /* access modifiers changed from: private */
        public boolean mUseEmptyCoverBlur = false;

        public BlurImageInfo build() {
            return new BlurImageInfo(this, 0);
        }

        public Builder setCustomCover(boolean z) {
            this.mIsCustomCover = z;
            return this;
        }

        public Builder setEmptyAlbum(boolean z) {
            this.mIsEmptyAlbum = z;
            return this;
        }

        public Builder setFolder(boolean z) {
            this.mIsFolder = z;
            return this;
        }

        public Builder setGridType(boolean z) {
            this.mIsGridType = z;
            return this;
        }

        public Builder setListToGrid(boolean z) {
            this.mIsListToGrid = z;
            return this;
        }

        public Builder setMaxDepth(boolean z) {
            this.mIsMaxDepth = z;
            return this;
        }

        public Builder setReverseOperation(boolean z) {
            this.mIsReverseOp = z;
            return this;
        }

        public Builder setSkip(boolean z) {
            this.mIsSkip = z;
            return this;
        }

        public Builder setTargetView(View view) {
            this.mTargetView = view;
            return this;
        }

        public Builder setTargetViewHeight(int i2) {
            this.mTargetViewHeight = i2;
            return this;
        }

        public Builder setUseEmptyCoverBlur(boolean z) {
            this.mUseEmptyCoverBlur = z;
            return this;
        }
    }

    public /* synthetic */ BlurImageInfo(Builder builder, int i2) {
        this(builder);
    }

    public float getTargetViewHeight() {
        int i2;
        View view = this.mTargetView;
        if (view != null) {
            i2 = view.getHeight();
        } else {
            i2 = this.mTargetViewHeight;
        }
        if (!this.mIsFolder || this.mIsListToGrid) {
            return (float) i2;
        }
        return ((float) i2) * 1.9f;
    }

    public boolean isCustomCover() {
        return this.mIsCustomCover;
    }

    public boolean isEmptyAlbum() {
        return this.mIsEmptyAlbum;
    }

    public boolean isGridType() {
        return this.mIsGridType;
    }

    public boolean isMaxDepth() {
        return this.mIsMaxDepth;
    }

    public boolean isReverseOperation() {
        return this.mIsReverseOp;
    }

    public boolean isSkipBlur() {
        return this.mIsSkip;
    }

    public boolean isUseEmptyCoverBlur() {
        return this.mUseEmptyCoverBlur;
    }

    private BlurImageInfo(Builder builder) {
        this.mIsCustomCover = builder.mIsCustomCover;
        this.mIsEmptyAlbum = builder.mIsEmptyAlbum;
        this.mIsFolder = builder.mIsFolder;
        this.mIsGridType = builder.mIsGridType;
        this.mIsListToGrid = builder.mIsListToGrid;
        this.mIsMaxDepth = builder.mIsMaxDepth;
        this.mIsReverseOp = builder.mIsReverseOp;
        this.mIsSkip = builder.mIsSkip;
        this.mTargetViewHeight = builder.mTargetViewHeight;
        this.mTargetView = builder.mTargetView;
        this.mUseEmptyCoverBlur = builder.mUseEmptyCoverBlur;
    }
}
