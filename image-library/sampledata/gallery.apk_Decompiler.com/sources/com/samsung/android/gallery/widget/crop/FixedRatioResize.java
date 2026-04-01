package com.samsung.android.gallery.widget.crop;

import android.graphics.RectF;
import com.samsung.android.gallery.widget.crop.CropArea;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FixedRatioResize implements CropArea.Resize {
    private final float mHeightWidthRatio;
    private final RectF mMaximumBoundary;
    private final float mWidthHeightRatio;

    /* renamed from: com.samsung.android.gallery.widget.crop.FixedRatioResize$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.widget.crop.CropArea$TouchPosition[] r0 = com.samsung.android.gallery.widget.crop.CropArea.TouchPosition.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition = r0
                com.samsung.android.gallery.widget.crop.CropArea$TouchPosition r1 = com.samsung.android.gallery.widget.crop.CropArea.TouchPosition.LEFT_TOP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.widget.crop.CropArea$TouchPosition r1 = com.samsung.android.gallery.widget.crop.CropArea.TouchPosition.RIGHT_TOP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.widget.crop.CropArea$TouchPosition r1 = com.samsung.android.gallery.widget.crop.CropArea.TouchPosition.LEFT_BOTTOM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.widget.crop.CropArea$TouchPosition r1 = com.samsung.android.gallery.widget.crop.CropArea.TouchPosition.RIGHT_BOTTOM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.widget.crop.CropArea$TouchPosition r1 = com.samsung.android.gallery.widget.crop.CropArea.TouchPosition.LEFT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.gallery.widget.crop.CropArea$TouchPosition r1 = com.samsung.android.gallery.widget.crop.CropArea.TouchPosition.TOP     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.samsung.android.gallery.widget.crop.CropArea$TouchPosition r1 = com.samsung.android.gallery.widget.crop.CropArea.TouchPosition.RIGHT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.samsung.android.gallery.widget.crop.CropArea$TouchPosition r1 = com.samsung.android.gallery.widget.crop.CropArea.TouchPosition.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.crop.FixedRatioResize.AnonymousClass1.<clinit>():void");
        }
    }

    public FixedRatioResize(RectF rectF, float f, float f5) {
        this.mWidthHeightRatio = f;
        this.mHeightWidthRatio = f5;
        this.mMaximumBoundary = rectF;
    }

    private void checkBottomLeftRightBoundary(RectF rectF) {
        float f = rectF.bottom;
        RectF rectF2 = this.mMaximumBoundary;
        float f5 = rectF2.bottom;
        if (f > f5) {
            float f8 = f - f5;
            rectF.bottom = f - f8;
            float f10 = rectF.left;
            float f11 = this.mWidthHeightRatio;
            rectF.left = ((f8 * f11) / 2.0f) + f10;
            rectF.right -= (f8 * f11) / 2.0f;
        }
        float f12 = rectF.left;
        float f13 = rectF2.left;
        if (f12 < f13) {
            float f14 = f13 - f12;
            rectF.bottom -= (this.mHeightWidthRatio * f14) * 2.0f;
            rectF.left = f12 + f14;
            rectF.right -= f14;
        }
        float f15 = rectF.right;
        float f16 = rectF2.right;
        if (f15 > f16) {
            float f17 = f15 - f16;
            rectF.bottom -= (this.mHeightWidthRatio * f17) * 2.0f;
            rectF.left += f17;
            rectF.right = f15 - f17;
        }
        if (CropArea.shorterThanMinSize(rectF.height())) {
            float height = 200.0f - rectF.height();
            rectF.bottom += height;
            float f18 = rectF.left;
            float f19 = this.mWidthHeightRatio;
            rectF.left = f18 - ((height * f19) / 2.0f);
            rectF.right = ((height * f19) / 2.0f) + rectF.right;
        }
        if (CropArea.shorterThanMinSize(rectF.width())) {
            float width = 200.0f - rectF.width();
            rectF.bottom = (this.mHeightWidthRatio * width * 2.0f) + rectF.bottom;
            rectF.left -= width;
            rectF.right += width;
        }
    }

    private void checkLeftBottomBoundary(RectF rectF) {
        float f = rectF.left;
        RectF rectF2 = this.mMaximumBoundary;
        float f5 = rectF2.left;
        if (f < f5) {
            float f8 = f5 - f;
            rectF.left = f + f8;
            rectF.bottom -= f8 * this.mHeightWidthRatio;
        }
        float f10 = rectF.bottom;
        float f11 = rectF2.bottom;
        if (f10 > f11) {
            float f12 = f10 - f11;
            rectF.left = (this.mWidthHeightRatio * f12) + rectF.left;
            rectF.bottom = f10 - f12;
        }
        if (CropArea.shorterThanMinSize(rectF.width())) {
            float width = 200.0f - rectF.width();
            rectF.left -= width;
            rectF.bottom = (width * this.mHeightWidthRatio) + rectF.bottom;
        }
        if (CropArea.shorterThanMinSize(rectF.height())) {
            float height = 200.0f - rectF.height();
            rectF.left -= this.mWidthHeightRatio * height;
            rectF.bottom += height;
        }
    }

    private void checkLeftTopBottomBoundary(RectF rectF) {
        float f = rectF.left;
        RectF rectF2 = this.mMaximumBoundary;
        float f5 = rectF2.left;
        if (f < f5) {
            float f8 = f5 - f;
            rectF.left = f + f8;
            float f10 = rectF.top;
            float f11 = this.mHeightWidthRatio;
            rectF.top = ((f8 * f11) / 2.0f) + f10;
            rectF.bottom -= (f8 * f11) / 2.0f;
        }
        float f12 = rectF.top;
        float f13 = rectF2.top;
        if (f12 < f13) {
            float f14 = f13 - f12;
            rectF.left = (this.mWidthHeightRatio * f14 * 2.0f) + rectF.left;
            rectF.top = f12 + f14;
            rectF.bottom -= f14;
        }
        float f15 = rectF.bottom;
        if (f15 > rectF2.bottom) {
            float f16 = rectF2.top;
            float f17 = rectF.top;
            float f18 = f16 - f17;
            rectF.left = (this.mWidthHeightRatio * f18 * 2.0f) + rectF.left;
            rectF.top = f17 + f18;
            rectF.bottom = f15 - f18;
        }
        if (CropArea.shorterThanMinSize(rectF.width())) {
            float width = 200.0f - rectF.width();
            rectF.left -= width;
            float f19 = rectF.top;
            float f20 = this.mHeightWidthRatio;
            rectF.top = f19 - ((width * f20) / 2.0f);
            rectF.bottom = ((width * f20) / 2.0f) + rectF.bottom;
        }
        if (CropArea.shorterThanMinSize(rectF.height())) {
            float height = 200.0f - rectF.height();
            rectF.left -= (this.mWidthHeightRatio * height) * 2.0f;
            rectF.top -= height;
            rectF.bottom += height;
        }
    }

    private void checkLeftTopBoundary(RectF rectF) {
        float f = rectF.left;
        RectF rectF2 = this.mMaximumBoundary;
        float f5 = rectF2.left;
        if (f < f5) {
            float f8 = f5 - f;
            rectF.left = f + f8;
            rectF.top = (f8 * this.mHeightWidthRatio) + rectF.top;
        }
        float f10 = rectF.top;
        float f11 = rectF2.top;
        if (f10 < f11) {
            float f12 = f11 - f10;
            rectF.left = (this.mWidthHeightRatio * f12) + rectF.left;
            rectF.top = f10 + f12;
        }
        if (CropArea.shorterThanMinSize(rectF.width())) {
            float width = 200.0f - rectF.width();
            rectF.left -= width;
            rectF.top -= width * this.mHeightWidthRatio;
        }
        if (CropArea.shorterThanMinSize(rectF.height())) {
            float height = 200.0f - rectF.height();
            rectF.left -= this.mWidthHeightRatio * height;
            rectF.top -= height;
        }
    }

    private void checkRightBottomBoundary(RectF rectF) {
        float f = rectF.right;
        RectF rectF2 = this.mMaximumBoundary;
        float f5 = rectF2.right;
        if (f > f5) {
            float f8 = f - f5;
            rectF.right = f - f8;
            rectF.bottom -= f8 * this.mHeightWidthRatio;
        }
        float f10 = rectF.bottom;
        float f11 = rectF2.bottom;
        if (f10 > f11) {
            float f12 = f10 - f11;
            rectF.right -= this.mWidthHeightRatio * f12;
            rectF.bottom = f10 - f12;
        }
        if (CropArea.shorterThanMinSize(rectF.width())) {
            float width = 200.0f - rectF.width();
            rectF.right += width;
            rectF.bottom = (width * this.mHeightWidthRatio) + rectF.bottom;
        }
        if (CropArea.shorterThanMinSize(rectF.height())) {
            float height = 200.0f - rectF.height();
            rectF.right = (this.mWidthHeightRatio * height) + rectF.right;
            rectF.bottom += height;
        }
    }

    private void checkRightTopBottomBoundary(RectF rectF) {
        float f = rectF.right;
        RectF rectF2 = this.mMaximumBoundary;
        float f5 = rectF2.right;
        if (f > f5) {
            float f8 = f - f5;
            rectF.right = f - f8;
            float f10 = rectF.top;
            float f11 = this.mHeightWidthRatio;
            rectF.top = ((f8 * f11) / 2.0f) + f10;
            rectF.bottom -= (f8 * f11) / 2.0f;
        }
        float f12 = rectF.top;
        float f13 = rectF2.top;
        if (f12 < f13) {
            float f14 = f13 - f12;
            rectF.right -= (this.mWidthHeightRatio * f14) * 2.0f;
            rectF.top = f12 + f14;
            rectF.bottom -= f14;
        }
        float f15 = rectF.bottom;
        if (f15 > rectF2.bottom) {
            float f16 = rectF2.top;
            float f17 = rectF.top;
            float f18 = f16 - f17;
            rectF.right -= (this.mWidthHeightRatio * f18) * 2.0f;
            rectF.top = f17 + f18;
            rectF.bottom = f15 - f18;
        }
        if (CropArea.shorterThanMinSize(rectF.width())) {
            float width = 200.0f - rectF.width();
            rectF.right += width;
            float f19 = rectF.top;
            float f20 = this.mHeightWidthRatio;
            rectF.top = f19 - ((width * f20) / 2.0f);
            rectF.bottom = ((width * f20) / 2.0f) + rectF.bottom;
        }
        if (CropArea.shorterThanMinSize(rectF.height())) {
            float height = 200.0f - rectF.height();
            rectF.right = (this.mWidthHeightRatio * height * 2.0f) + rectF.right;
            rectF.top -= height;
            rectF.bottom += height;
        }
    }

    private void checkRightTopBoundary(RectF rectF) {
        float f = rectF.right;
        RectF rectF2 = this.mMaximumBoundary;
        float f5 = rectF2.right;
        if (f > f5) {
            float f8 = f - f5;
            rectF.right = f - f8;
            rectF.top = (f8 * this.mHeightWidthRatio) + rectF.top;
        }
        float f10 = rectF.top;
        float f11 = rectF2.top;
        if (f10 < f11) {
            float f12 = f11 - f10;
            rectF.right -= this.mWidthHeightRatio * f12;
            rectF.top = f10 + f12;
        }
        if (CropArea.shorterThanMinSize(rectF.width())) {
            float width = 200.0f - rectF.width();
            rectF.right += width;
            rectF.top -= width * this.mHeightWidthRatio;
        }
        if (CropArea.shorterThanMinSize(rectF.height())) {
            float height = 200.0f - rectF.height();
            rectF.right = (this.mWidthHeightRatio * height) + rectF.right;
            rectF.top -= height;
        }
    }

    private void checkTopLeftRightBoundary(RectF rectF) {
        float f = rectF.top;
        RectF rectF2 = this.mMaximumBoundary;
        float f5 = rectF2.top;
        if (f < f5) {
            float f8 = f5 - f;
            rectF.top = f + f8;
            float f10 = rectF.left;
            float f11 = this.mWidthHeightRatio;
            rectF.left = ((f8 * f11) / 2.0f) + f10;
            rectF.right -= (f8 * f11) / 2.0f;
        }
        float f12 = rectF.left;
        float f13 = rectF2.left;
        if (f12 < f13) {
            float f14 = f13 - f12;
            rectF.top = (this.mHeightWidthRatio * f14 * 2.0f) + rectF.top;
            rectF.left = f12 + f14;
            rectF.right -= f14;
        }
        float f15 = rectF.right;
        float f16 = rectF2.right;
        if (f15 > f16) {
            float f17 = f15 - f16;
            rectF.top = (this.mHeightWidthRatio * f17 * 2.0f) + rectF.top;
            rectF.left += f17;
            rectF.right = f15 - f17;
        }
        if (CropArea.shorterThanMinSize(rectF.height())) {
            float height = 200.0f - rectF.height();
            rectF.top -= height;
            float f18 = rectF.left;
            float f19 = this.mWidthHeightRatio;
            rectF.left = f18 - ((height * f19) / 2.0f);
            rectF.right = ((height * f19) / 2.0f) + rectF.right;
        }
        if (CropArea.shorterThanMinSize(rectF.width())) {
            float width = 200.0f - rectF.width();
            rectF.top -= (this.mHeightWidthRatio * width) * 2.0f;
            rectF.left -= width;
            rectF.right += width;
        }
    }

    private void resizeWithBottomLine(RectF rectF, float f, float f5) {
        float f8 = (this.mWidthHeightRatio * f5) / 2.0f;
        float f10 = rectF.left;
        RectF rectF2 = this.mMaximumBoundary;
        if (f10 - f8 < rectF2.left) {
            resizeWithRightBottomHandle(rectF, f, f5);
            return;
        }
        float f11 = rectF.right;
        if (f11 + f8 > rectF2.right) {
            resizeWithLeftBottomHandle(rectF, f, f5);
            return;
        }
        rectF.bottom += f5;
        rectF.left = f10 - f8;
        rectF.right = f11 + f8;
        checkBottomLeftRightBoundary(rectF);
    }

    private void resizeWithLeftBottomHandle(RectF rectF, float f, float f5) {
        if (Math.abs(f) > Math.abs(f5)) {
            rectF.left += f;
            rectF.bottom -= f * this.mHeightWidthRatio;
        } else {
            rectF.left -= this.mWidthHeightRatio * f5;
            rectF.bottom += f5;
        }
        checkLeftBottomBoundary(rectF);
    }

    private void resizeWithLeftLine(RectF rectF, float f, float f5) {
        float f8 = (this.mHeightWidthRatio * f) / 2.0f;
        float f10 = rectF.top;
        RectF rectF2 = this.mMaximumBoundary;
        if (f10 + f8 < rectF2.top) {
            resizeWithLeftBottomHandle(rectF, f, f5);
            return;
        }
        float f11 = rectF.bottom;
        if (f11 - f8 > rectF2.bottom) {
            resizeWithLeftTopHandle(rectF, f, f5);
            return;
        }
        rectF.left += f;
        rectF.top = f10 + f8;
        rectF.bottom = f11 - f8;
        checkLeftTopBottomBoundary(rectF);
    }

    private void resizeWithLeftTopHandle(RectF rectF, float f, float f5) {
        if (Math.abs(f) > Math.abs(f5)) {
            rectF.left += f;
            rectF.top = (f * this.mHeightWidthRatio) + rectF.top;
        } else {
            rectF.left = (this.mWidthHeightRatio * f5) + rectF.left;
            rectF.top += f5;
        }
        checkLeftTopBoundary(rectF);
    }

    private void resizeWithRightBottomHandle(RectF rectF, float f, float f5) {
        if (Math.abs(f) > Math.abs(f5)) {
            rectF.right += f;
            rectF.bottom = (f * this.mHeightWidthRatio) + rectF.bottom;
        } else {
            rectF.right = (this.mWidthHeightRatio * f5) + rectF.right;
            rectF.bottom += f5;
        }
        checkRightBottomBoundary(rectF);
    }

    private void resizeWithRightLine(RectF rectF, float f, float f5) {
        float f8 = (this.mHeightWidthRatio * f) / 2.0f;
        float f10 = rectF.top;
        RectF rectF2 = this.mMaximumBoundary;
        if (f10 - f8 < rectF2.top) {
            resizeWithRightBottomHandle(rectF, f, f5);
            return;
        }
        float f11 = rectF.bottom;
        if (f11 + f8 > rectF2.bottom) {
            resizeWithRightTopHandle(rectF, f, f5);
            return;
        }
        rectF.right += f;
        rectF.top = f10 - f8;
        rectF.bottom = f11 + f8;
        checkRightTopBottomBoundary(rectF);
    }

    private void resizeWithRightTopHandle(RectF rectF, float f, float f5) {
        if (Math.abs(f) > Math.abs(f5)) {
            rectF.right += f;
            rectF.top -= f * this.mHeightWidthRatio;
        } else {
            rectF.right -= this.mWidthHeightRatio * f5;
            rectF.top += f5;
        }
        checkRightTopBoundary(rectF);
    }

    private void resizeWithTopLine(RectF rectF, float f, float f5) {
        float f8 = (this.mWidthHeightRatio * f5) / 2.0f;
        float f10 = rectF.left;
        RectF rectF2 = this.mMaximumBoundary;
        if (f10 + f8 < rectF2.left) {
            resizeWithRightTopHandle(rectF, f, f5);
            return;
        }
        float f11 = rectF.right;
        if (f11 - f8 > rectF2.right) {
            resizeWithLeftTopHandle(rectF, f, f5);
            return;
        }
        rectF.top += f5;
        rectF.left = f10 + f8;
        rectF.right = f11 - f8;
        checkTopLeftRightBoundary(rectF);
    }

    public void resize(RectF rectF, CropArea.TouchPosition touchPosition, float f, float f5) {
        RectF rectF2 = new RectF(rectF);
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition[touchPosition.ordinal()]) {
            case 1:
                resizeWithLeftTopHandle(rectF2, f, f5);
                break;
            case 2:
                resizeWithRightTopHandle(rectF2, f, f5);
                break;
            case 3:
                resizeWithLeftBottomHandle(rectF2, f, f5);
                break;
            case 4:
                resizeWithRightBottomHandle(rectF2, f, f5);
                break;
            case 5:
                resizeWithLeftLine(rectF2, f, f5);
                break;
            case 6:
                resizeWithTopLine(rectF2, f, f5);
                break;
            case 7:
                resizeWithRightLine(rectF2, f, f5);
                break;
            case 8:
                resizeWithBottomLine(rectF2, f, f5);
                break;
        }
        rectF.set(rectF2);
    }
}
