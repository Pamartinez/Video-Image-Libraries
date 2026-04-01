package com.samsung.android.gallery.module.crop;

import android.graphics.Rect;
import android.graphics.RectF;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SmartCropUtils {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CropInfo {
        int drawableHeight;
        float drawableRatio;
        int drawableWidth;
        int rotation;
        /* access modifiers changed from: private */
        public float smartCropRatio;
        RectF smartCropRectF;
        Rect targetCropRect;
        float viewHeight;
        float viewRatio;
        float viewWidth;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Builder {
            int drawableHeight;
            /* access modifiers changed from: private */
            public float drawableRatio;
            int drawableWidth;
            int rotation;
            float smartCropRatio;
            RectF smartCropRectF;
            Rect targetCropRect;
            float viewHeight;
            float viewRatio;
            float viewWidth;

            public Builder(RectF rectF) {
                this.smartCropRectF = rectF;
            }

            private void calcSmartCropRatio() {
                float width;
                int height;
                Rect rect = this.targetCropRect;
                if (rect != null) {
                    if (this.rotation % MOCRLang.KHMER == 0) {
                        width = (float) rect.height();
                        height = this.targetCropRect.width();
                    } else {
                        width = (float) rect.width();
                        height = this.targetCropRect.height();
                    }
                    this.smartCropRatio = width / ((float) height);
                }
            }

            private void calcSmartCropRect() {
                RectF rectF = this.smartCropRectF;
                if (rectF != null) {
                    int i2 = this.drawableWidth;
                    int i7 = this.drawableHeight;
                    this.targetCropRect = new Rect((int) (((float) i2) * rectF.left), (int) (((float) i7) * rectF.top), (int) (((float) i2) * rectF.right), (int) (((float) i7) * rectF.bottom));
                }
            }

            public CropInfo build() {
                calcSmartCropRatio();
                return new CropInfo(this, 0);
            }

            public Builder setDrawableSize(int i2, int i7) {
                this.drawableWidth = i2;
                this.drawableHeight = i7;
                this.drawableRatio = ((float) i7) / ((float) i2);
                calcSmartCropRect();
                return this;
            }

            public Builder setRotation(int i2) {
                this.rotation = i2;
                return this;
            }

            public Builder setViewSize(float f, float f5) {
                this.viewWidth = f;
                this.viewHeight = f5;
                this.viewRatio = f5 / f;
                return this;
            }
        }

        public /* synthetic */ CropInfo(Builder builder, int i2) {
            this(builder);
        }

        private CropInfo(Builder builder) {
            this.smartCropRatio = builder.smartCropRatio;
            this.smartCropRectF = builder.smartCropRectF;
            this.drawableRatio = builder.drawableRatio;
            this.viewRatio = builder.viewRatio;
            this.drawableWidth = builder.drawableWidth;
            this.drawableHeight = builder.drawableHeight;
            this.viewWidth = builder.viewWidth;
            this.viewHeight = builder.viewHeight;
            this.rotation = builder.rotation;
            this.targetCropRect = builder.targetCropRect;
        }
    }

    public static Rect calcSmartCropRectForStory(CropInfo cropInfo) {
        if (cropInfo.rotation % MOCRLang.KHMER == 0) {
            return getHorizontalCropRect(cropInfo);
        }
        return getVerticalCropRect(cropInfo);
    }

    public static Rect getCropRectBasedOnDrawableHeight(int i2, CropInfo cropInfo) {
        int i7;
        int i8;
        int exactCenterX = (int) cropInfo.targetCropRect.exactCenterX();
        int i10 = i2 / 2;
        int i11 = exactCenterX - i10;
        int i12 = i10 + exactCenterX;
        if (i11 < 0 || i12 > cropInfo.drawableWidth) {
            int i13 = cropInfo.drawableWidth;
            if (exactCenterX < i13 / 2) {
                Rect rect = cropInfo.targetCropRect;
                if (rect.left <= i11) {
                    rect.left = i11;
                    rect.right = Math.max(rect.right, i12);
                } else {
                    rect.left = 0;
                    int i14 = 0 - i11;
                    int i15 = rect.right;
                    if (i15 < i12) {
                        i8 = i12 + i14;
                    } else {
                        i8 = i15 + i14;
                    }
                    rect.right = i8;
                }
            } else {
                Rect rect2 = cropInfo.targetCropRect;
                if (rect2.right >= i12) {
                    rect2.right = i12;
                    rect2.left = Math.max(rect2.left, i11);
                } else {
                    rect2.right = i13;
                    int i16 = i12 - i13;
                    int i17 = rect2.left;
                    if (i17 > i11) {
                        i7 = i11 - i16;
                    } else {
                        i7 = i17 - i16;
                    }
                    rect2.left = i7;
                }
            }
        } else {
            Rect rect3 = cropInfo.targetCropRect;
            rect3.left = i11;
            rect3.right = i12;
        }
        return new Rect(cropInfo.targetCropRect);
    }

    public static Rect getCropRectBasedOnDrawableWidth(int i2, CropInfo cropInfo) {
        int i7;
        int i8;
        int exactCenterY = (int) cropInfo.targetCropRect.exactCenterY();
        int i10 = i2 / 2;
        int i11 = exactCenterY - i10;
        int i12 = i10 + exactCenterY;
        if (i11 < 0 || i12 > cropInfo.drawableHeight) {
            int i13 = cropInfo.drawableHeight;
            if (exactCenterY < i13 / 2) {
                Rect rect = cropInfo.targetCropRect;
                if (rect.top <= i11) {
                    rect.top = i11;
                    rect.bottom = Math.min(rect.bottom, i12);
                } else {
                    rect.top = 0;
                    int i14 = rect.bottom;
                    if (i14 < i12) {
                        i8 = i12 - i11;
                    } else {
                        i8 = i14 - i11;
                    }
                    rect.bottom = i8;
                }
            } else {
                Rect rect2 = cropInfo.targetCropRect;
                if (rect2.bottom >= i12) {
                    rect2.bottom = i12;
                    rect2.top = Math.max(rect2.top, i11);
                } else {
                    rect2.bottom = i13;
                    int i15 = i12 - i13;
                    int i16 = rect2.top;
                    if (i16 > i11) {
                        i7 = i11 - i15;
                    } else {
                        i7 = i16 - i15;
                    }
                    rect2.top = i7;
                }
            }
        } else {
            Rect rect3 = cropInfo.targetCropRect;
            rect3.top = i11;
            rect3.bottom = i12;
        }
        return new Rect(cropInfo.targetCropRect);
    }

    public static Rect getHorizontalCropRect(CropInfo cropInfo) {
        if (cropInfo.smartCropRatio >= cropInfo.viewRatio) {
            return getHorizontalCropRectBasedOnDrawableWidth(cropInfo);
        }
        if (cropInfo.drawableWidth >= cropInfo.drawableHeight) {
            return getHorizontalCropRectBasedOnDrawableHeightAndViewRatio(cropInfo);
        }
        return getCropRectBasedOnDrawableWidth((int) (((float) cropInfo.targetCropRect.width()) * cropInfo.viewRatio), cropInfo);
    }

    public static Rect getHorizontalCropRectBasedOnDrawableHeightAndViewRatio(CropInfo cropInfo) {
        if (cropInfo.drawableRatio > cropInfo.viewRatio) {
            return getCropRectBasedOnDrawableWidth((int) (((float) cropInfo.targetCropRect.width()) * cropInfo.viewRatio), cropInfo);
        }
        return getCropRectBasedOnDrawableHeight((int) (((float) cropInfo.targetCropRect.height()) / cropInfo.viewRatio), cropInfo);
    }

    public static Rect getHorizontalCropRectBasedOnDrawableWidth(CropInfo cropInfo) {
        int width = cropInfo.targetCropRect.width();
        int i2 = width / 2;
        int exactCenterX = (int) cropInfo.targetCropRect.exactCenterX();
        Rect rect = cropInfo.targetCropRect;
        int i7 = exactCenterX - i2;
        if (rect.left < i7) {
            rect.left = i7;
        }
        int i8 = exactCenterX + i2;
        if (rect.right > i8) {
            rect.right = i8;
        }
        int i10 = ((int) (((float) width) * cropInfo.viewRatio)) / 2;
        int exactCenterY = (int) rect.exactCenterY();
        Rect rect2 = cropInfo.targetCropRect;
        int i11 = exactCenterY - i10;
        if (rect2.top < i11) {
            rect2.top = i11;
        }
        int i12 = exactCenterY + i10;
        if (rect2.bottom > i12) {
            rect2.bottom = i12;
        }
        return new Rect(cropInfo.targetCropRect);
    }

    public static Rect getVerticalCropRect(CropInfo cropInfo) {
        if (cropInfo.smartCropRatio >= cropInfo.viewRatio) {
            return getVerticalCropRectBasedOnDrawableHeight(cropInfo);
        }
        if (cropInfo.drawableWidth >= cropInfo.drawableHeight) {
            return getCropRectBasedOnDrawableHeight((int) (((float) cropInfo.targetCropRect.height()) * cropInfo.viewRatio), cropInfo);
        }
        return getVerticalCropRectBasedOnDrawableWidthAndViewRatio(cropInfo);
    }

    public static Rect getVerticalCropRectBasedOnDrawableHeight(CropInfo cropInfo) {
        int height = cropInfo.targetCropRect.height();
        int i2 = height / 2;
        int exactCenterY = (int) cropInfo.targetCropRect.exactCenterY();
        if (height != cropInfo.drawableHeight) {
            Rect rect = cropInfo.targetCropRect;
            int i7 = exactCenterY - i2;
            if (rect.top < i7) {
                rect.top = i7;
            }
            int i8 = exactCenterY + i2;
            if (rect.bottom > i8) {
                rect.bottom = i8;
            }
        }
        int i10 = ((int) (((float) height) * cropInfo.viewRatio)) / 2;
        int exactCenterX = (int) cropInfo.targetCropRect.exactCenterX();
        Rect rect2 = cropInfo.targetCropRect;
        int i11 = exactCenterX - i10;
        if (rect2.left < i11) {
            rect2.left = i11;
        }
        int i12 = exactCenterX + i10;
        if (rect2.right > i12) {
            rect2.right = i12;
        }
        return new Rect(cropInfo.targetCropRect);
    }

    private static Rect getVerticalCropRectBasedOnDrawableWidthAndViewRatio(CropInfo cropInfo) {
        if (1.0f / cropInfo.drawableRatio > cropInfo.viewRatio) {
            return getCropRectBasedOnDrawableHeight((int) (((float) cropInfo.targetCropRect.height()) * cropInfo.viewRatio), cropInfo);
        }
        return getCropRectBasedOnDrawableWidth((int) (((float) cropInfo.targetCropRect.width()) / cropInfo.viewRatio), cropInfo);
    }

    public static boolean isValidRect(RectF rectF) {
        if (rectF == null || rectF.width() <= 0.0f || rectF.height() <= 0.0f) {
            return false;
        }
        return true;
    }
}
