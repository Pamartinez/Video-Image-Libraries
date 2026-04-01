package com.samsung.android.gallery.widget.crop;

import android.graphics.RectF;
import com.samsung.android.gallery.widget.crop.CropArea;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FreeRatioResize implements CropArea.Resize {
    private final RectF mMaximumBoundary;

    /* renamed from: com.samsung.android.gallery.widget.crop.FreeRatioResize$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.crop.FreeRatioResize.AnonymousClass1.<clinit>():void");
        }
    }

    public FreeRatioResize(RectF rectF) {
        this.mMaximumBoundary = rectF;
    }

    private void checkBottomBoundary(RectF rectF) {
        float f = rectF.bottom;
        float f5 = this.mMaximumBoundary.bottom;
        if (f > f5) {
            rectF.bottom = f - (f - f5);
        } else if (CropArea.shorterThanMinSize(rectF.height())) {
            rectF.bottom = (200.0f - rectF.height()) + rectF.bottom;
        }
    }

    private void checkLeftBoundary(RectF rectF) {
        float f = rectF.left;
        float f5 = this.mMaximumBoundary.left;
        if (f < f5) {
            rectF.left = (f5 - f) + f;
        } else if (CropArea.shorterThanMinSize(rectF.width())) {
            rectF.left -= 200.0f - rectF.width();
        }
    }

    private void checkRightBoundary(RectF rectF) {
        float f = rectF.right;
        float f5 = this.mMaximumBoundary.right;
        if (f > f5) {
            rectF.right = f - (f - f5);
        } else if (CropArea.shorterThanMinSize(rectF.width())) {
            rectF.right = (200.0f - rectF.width()) + rectF.right;
        }
    }

    private void checkTopBoundary(RectF rectF) {
        float f = rectF.top;
        float f5 = this.mMaximumBoundary.top;
        if (f < f5) {
            rectF.top = (f5 - f) + f;
        } else if (CropArea.shorterThanMinSize(rectF.height())) {
            rectF.top -= 200.0f - rectF.height();
        }
    }

    public void resize(RectF rectF, CropArea.TouchPosition touchPosition, float f, float f5) {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$gallery$widget$crop$CropArea$TouchPosition[touchPosition.ordinal()]) {
            case 1:
                rectF.left += f;
                rectF.top += f5;
                checkLeftBoundary(rectF);
                checkTopBoundary(rectF);
                return;
            case 2:
                rectF.right += f;
                rectF.top += f5;
                checkRightBoundary(rectF);
                checkTopBoundary(rectF);
                return;
            case 3:
                rectF.left += f;
                rectF.bottom += f5;
                checkLeftBoundary(rectF);
                checkBottomBoundary(rectF);
                return;
            case 4:
                rectF.right += f;
                rectF.bottom += f5;
                checkRightBoundary(rectF);
                checkBottomBoundary(rectF);
                return;
            case 5:
                rectF.left += f;
                checkLeftBoundary(rectF);
                return;
            case 6:
                rectF.top += f5;
                checkTopBoundary(rectF);
                return;
            case 7:
                rectF.right += f;
                checkRightBoundary(rectF);
                return;
            case 8:
                rectF.bottom += f5;
                checkBottomBoundary(rectF);
                return;
            default:
                return;
        }
    }
}
